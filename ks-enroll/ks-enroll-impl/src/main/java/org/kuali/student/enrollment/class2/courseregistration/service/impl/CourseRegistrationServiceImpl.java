package org.kuali.student.enrollment.class2.courseregistration.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.student.enrollment.class2.courseregistration.service.assembler.CourseRegistrationAssembler;
import org.kuali.student.enrollment.class2.courseregistration.service.assembler.RegRequestAssembler;
import org.kuali.student.enrollment.class2.courseregistration.service.assembler.RegResponseAssembler;
import org.kuali.student.enrollment.courseoffering.dto.RegistrationGroupInfo;
import org.kuali.student.enrollment.courseoffering.service.CourseOfferingService;
import org.kuali.student.enrollment.courseregistration.dto.ActivityRegistrationInfo;
import org.kuali.student.enrollment.courseregistration.dto.CourseRegistrationInfo;

import org.kuali.student.enrollment.courseregistration.dto.RegGroupRegistrationInfo;
import org.kuali.student.enrollment.courseregistration.dto.RegRequestInfo;
import org.kuali.student.enrollment.courseregistration.dto.RegRequestItemInfo;
import org.kuali.student.enrollment.courseregistration.dto.RegResponseInfo;
import org.kuali.student.enrollment.courseregistration.service.CourseRegistrationService;
import org.kuali.student.enrollment.coursewaitlist.dto.CourseWaitlistEntryInfo;
import org.kuali.student.enrollment.lpr.dto.LPRTransactionInfo;
import org.kuali.student.enrollment.lpr.dto.LPRTransactionItemInfo;
import org.kuali.student.enrollment.lpr.service.LuiPersonRelationService;
import org.kuali.student.r2.common.datadictionary.dto.DictionaryEntryInfo;
import org.kuali.student.r2.common.datadictionary.service.DataDictionaryService;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.dto.DateRangeInfo;
import org.kuali.student.r2.common.dto.StateInfo;
import org.kuali.student.r2.common.dto.StateProcessInfo;
import org.kuali.student.r2.common.dto.StatusInfo;
import org.kuali.student.r2.common.dto.TypeInfo;
import org.kuali.student.r2.common.dto.TypeTypeRelationInfo;
import org.kuali.student.r2.common.dto.ValidationResultInfo;
import org.kuali.student.r2.common.exceptions.AlreadyExistsException;
import org.kuali.student.r2.common.exceptions.DataValidationErrorException;
import org.kuali.student.r2.common.exceptions.DoesNotExistException;
import org.kuali.student.r2.common.exceptions.InvalidParameterException;
import org.kuali.student.r2.common.exceptions.MissingParameterException;
import org.kuali.student.r2.common.exceptions.OperationFailedException;
import org.kuali.student.r2.common.exceptions.PermissionDeniedException;
import org.kuali.student.r2.common.exceptions.VersionMismatchException;
import org.kuali.student.r2.common.util.constants.LuiPersonRelationServiceConstants;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true, noRollbackFor = {DoesNotExistException.class}, rollbackFor = {Throwable.class})
public class CourseRegistrationServiceImpl implements CourseRegistrationService {

    private LuiPersonRelationService lprService;
    private CourseOfferingService courseOfferingService;
    private RegRequestAssembler regRequestAssembler;
    private RegResponseAssembler regResponseAssembler; 
    private CourseRegistrationAssembler courseregistrationAssembler;

    private DataDictionaryService dataDictionaryService;

    private LPRTransactionInfo createModifiedTransactionItems(LPRTransactionInfo storedLprTransaction,
            RegRequestInfo storedRegRequest, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException,
            DataValidationErrorException {
        List<LPRTransactionItemInfo> newTransactionItems = new ArrayList<LPRTransactionItemInfo>();
        List<String> regGroupsInRequest = new ArrayList<String>();

        List<RegRequestItemInfo> regRequestItems = storedRegRequest.getRegRequestItems();
        boolean isTransactionModified = false;
        for (RegRequestItemInfo regRequestItem : regRequestItems) {
            if (regRequestItem.getTypeKey().equals(LuiPersonRelationServiceConstants.LPRTRANS_ITEM_ADD_TYPE_KEY)
                    || regRequestItem.getTypeKey()
                            .equals(LuiPersonRelationServiceConstants.LPRTRANS_ITEM_DROP_TYPE_KEY)
                    || regRequestItem.getTypeKey()
                            .equals(LuiPersonRelationServiceConstants.LPRTRANS_ITEM_SAVE_TYPE_KEY)) {
                if (regRequestItem.getTypeKey().equals(LuiPersonRelationServiceConstants.LPRTRANS_ITEM_ADD_TYPE_KEY)) {

                    String regGroupId = regRequestItem.getNewRegGroupId();
                    RegistrationGroupInfo regGroup = courseOfferingService.getRegistrationGroup(regGroupId, context);

                    if (getAvailableSeatsForStudentInRegGroup(storedRegRequest.getStudentId(), regGroupId, context) > 0) {
                        List<LPRTransactionItemInfo> lprActivityTransactionItems = new ArrayList<LPRTransactionItemInfo>();
                        for (String activityOfferingId : regGroup.getActivityOfferingIds()) {
                            LPRTransactionItemInfo activtyItemInfo = regRequestAssembler.disassembleItem(
                                    regRequestItem, context);
                            activtyItemInfo.setNewLuiId(activityOfferingId);
                            activtyItemInfo.setStateKey(LuiPersonRelationServiceConstants.LPRTRANS_ITEM_NEW_STATE_KEY);
                            newTransactionItems.add(activtyItemInfo);

                        }

                        String courseOfferingId = regGroup.getCourseOfferingId();
                        LPRTransactionItemInfo courseOfferingItemInfo = regRequestAssembler.disassembleItem(
                                regRequestItem, context);
                        courseOfferingItemInfo.setNewLuiId(courseOfferingId);
                        courseOfferingItemInfo
                                .setStateKey(LuiPersonRelationServiceConstants.LPRTRANS_ITEM_NEW_STATE_KEY);
                        lprActivityTransactionItems.add(courseOfferingItemInfo);
                        newTransactionItems.add(courseOfferingItemInfo);

                    } else {
                        LPRTransactionItemInfo lprTransactionItem = regRequestAssembler.disassembleItem(regRequestItem,
                                context);
                        lprTransactionItem
                                .setTypeKey(LuiPersonRelationServiceConstants.LPRTRANS_ITEM_WAITLIST_TYPE_KEY);
                        lprTransactionItem.setStateKey(LuiPersonRelationServiceConstants.LPRTRANS_ITEM_NEW_STATE_KEY);
                    }

                    isTransactionModified = true;
                } else if (regRequestItem.getTypeKey().equals(
                        LuiPersonRelationServiceConstants.LPRTRANS_ITEM_DROP_TYPE_KEY)) {

                    String regGroupId = regRequestItem.getExistingRegGroupId();
                    RegistrationGroupInfo regGroup = courseOfferingService.getRegistrationGroup(regGroupId, context);
                    List<LPRTransactionItemInfo> lprActivityTransactionItems = new ArrayList<LPRTransactionItemInfo>();
                    for (String activityOfferingId : regGroup.getActivityOfferingIds()) {
                        LPRTransactionItemInfo activtyItemInfo = regRequestAssembler.disassembleItem(regRequestItem,
                                context);
                        activtyItemInfo.setExistingLuiId(activityOfferingId);
                        activtyItemInfo.setStateKey(LuiPersonRelationServiceConstants.LPRTRANS_ITEM_DROP_TYPE_KEY);
                        newTransactionItems.add(activtyItemInfo);

                    }

                    String courseOfferingId = regGroup.getCourseOfferingId();
                    LPRTransactionItemInfo courseOfferingItemInfo = regRequestAssembler.disassembleItem(regRequestItem,
                            context);
                    courseOfferingItemInfo.setExistingLuiId(courseOfferingId);
                    courseOfferingItemInfo.setStateKey(LuiPersonRelationServiceConstants.LPRTRANS_ITEM_DROP_TYPE_KEY);
                    lprActivityTransactionItems.add(courseOfferingItemInfo);
                    newTransactionItems.add(courseOfferingItemInfo);

                }
                storedLprTransaction.getLprTransactionItems().addAll(newTransactionItems);
                isTransactionModified = true;
            }

        }
        if (isTransactionModified) {
            storedLprTransaction = lprService.updateLprTransaction(storedLprTransaction.getId(), storedLprTransaction,
                    context);
        }
        return storedLprTransaction;
    }

    public DataDictionaryService getDataDictionaryService() {
        return dataDictionaryService;
    }

    public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
        this.dataDictionaryService = dataDictionaryService;
    }

    @Override
    public List<String> getDataDictionaryEntryKeys(ContextInfo context) throws OperationFailedException,
            MissingParameterException, PermissionDeniedException {
        return dataDictionaryService.getDataDictionaryEntryKeys(context);
    }

    @Override
    public DictionaryEntryInfo getDataDictionaryEntry(String entryKey, ContextInfo context)
            throws OperationFailedException, MissingParameterException, PermissionDeniedException,
            DoesNotExistException {
        return dataDictionaryService.getDataDictionaryEntry(entryKey, context);
    }

    @Override
    public TypeInfo getType(String typeKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<TypeInfo> getTypesByRefObjectURI(String refObjectURI, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<TypeInfo> getAllowedTypesForType(String ownerTypeKey, String relatedRefObjectURI, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<TypeTypeRelationInfo> getTypeRelationsByOwnerType(String ownerTypeKey, String relationTypeKey,
            ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public StateProcessInfo getProcessByKey(String processKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<String> getProcessByObjectType(String refObjectUri, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public StateInfo getState(String processKey, String stateKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<StateInfo> getStatesByProcess(String processKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<StateInfo> getInitialValidStates(String processKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public StateInfo getNextHappyState(String processKey, String currentStateKey, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public Boolean checkStudentEligibility(String studentId, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<org.kuali.student.r2.common.dto.ValidationResultInfo> checkStudentEligibilityForTerm(String studentId,
            String termKey, ContextInfo context) throws InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<DateRangeInfo> getAppointmentWindows(String studentId, String termKey, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<org.kuali.student.r2.common.dto.ValidationResultInfo> checkStudentEligibiltyForCourseOffering(
            String studentId, String courseOfferingId, ContextInfo context) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<org.kuali.student.r2.common.dto.ValidationResultInfo> checkStudentEligibiltyForRegGroup(
            String studentId, String regGroupId, ContextInfo context) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<RegistrationGroupInfo> getEligibleRegGroupsForStudentInCourseOffering(String studentId,
            String courseOfferingId, ContextInfo context) throws InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public String calculateCreditLoadForTerm(String studentId, String termKey, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public String calculateCreditLoadForRegRequest(String studentId, RegRequestInfo regRequestInfo, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public Integer getAvailableSeatsForCourseOffering(String courseOfferingId, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public Integer getAvailableSeatsForRegGroup(String regGroupId, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public Integer getAvailableSeatsForStudentInRegGroup(String studentId, String regGroupId, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        return null;

    }

    @Override
    public Integer getAvailableSeatsInSeatpool(String seatpoolId, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public RegRequestInfo updateRegRequest(String regRequestId, RegRequestInfo regRequestInfo, ContextInfo context)
            throws DataValidationErrorException, DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException, VersionMismatchException {

        LPRTransactionInfo lprTransactionInfo = regRequestAssembler.disassemble(regRequestInfo, context);
        return regRequestAssembler.assemble(lprService.updateLprTransaction(regRequestId, lprTransactionInfo, context),
                context);
    }

    @Override
    public StatusInfo deleteRegRequest(String regRequestId, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        return lprService.deleteLprTransaction(regRequestId, context);
    }

    @Override
    public List<ValidationResultInfo> validateRegRequest(RegRequestInfo regRequestInfo, ContextInfo context)
            throws DataValidationErrorException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<ValidationResultInfo> verifyRegRequest(RegRequestInfo regRequestInfo, ContextInfo context)
            throws DataValidationErrorException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public RegResponseInfo verifySavedReqRequest(String regRequestId, ContextInfo context)
            throws DataValidationErrorException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public RegRequestInfo createRegRequest(RegRequestInfo regRequestInfo, ContextInfo context)
            throws AlreadyExistsException, DataValidationErrorException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {

        LPRTransactionInfo lprTransaction = regRequestAssembler.disassemble(regRequestInfo, context);
        LPRTransactionInfo newLprTransaction = lprService.createLprTransaction(lprTransaction, context);
        RegRequestInfo createdRegRequestInfo = regRequestAssembler.assemble(newLprTransaction, context);
        return createdRegRequestInfo;
    }

    @Override
    public RegRequestInfo createRegRequestFromExisting(String existingRegRequestId, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {

        LPRTransactionInfo newLprTransaction = lprService.createLprTransactionFromExisting(existingRegRequestId,
                context);
        RegRequestInfo createdRegRequestInfo = regRequestAssembler.assemble(newLprTransaction, context);
        return createdRegRequestInfo;
    }

    @Override
    public RegResponseInfo submitRegRequest(String regRequestId, ContextInfo context) throws DoesNotExistException,
            DataValidationErrorException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException, AlreadyExistsException {
        LPRTransactionInfo storedLprTransaction = lprService.getLprTransaction(regRequestId, context);

        RegRequestInfo storedRegRequest = regRequestAssembler.assemble(storedLprTransaction, context);
        try {
            List<ValidationResultInfo> listValidationResult = validateRegRequest(storedRegRequest, context);

            if (listValidationResult != null && listValidationResult.size() > 0) {
                String message = "";
                String newline = System.getProperty("line.separator");
                for (ValidationResultInfo validationResultInfo : listValidationResult) {
                    if (validationResultInfo.getIsError()) {
                        message += newline + validationResultInfo.getMessage();
                        throw new OperationFailedException(message);
                    }
                }

            }
            List<ValidationResultInfo> verificationResultList = verifyRegRequest(storedRegRequest, context);
            for (ValidationResultInfo verificationResult : verificationResultList) {
                if (!verificationResult.getIsOk()) {
                    throw new DataValidationErrorException("Error while verifying registration request: "
                            + verificationResult.getMessage());
                }
            }
        } catch (DataValidationErrorException dataValidException) {
            throw new OperationFailedException(dataValidException.getMessage(), dataValidException);
        }

        LPRTransactionInfo multpleItemsTransaction = createModifiedTransactionItems(storedLprTransaction,
                storedRegRequest, context);

        LPRTransactionInfo submittedLprTransaction = lprService.processLprTransaction(
                multpleItemsTransaction.getId(), context);

        return regResponseAssembler.assemble(submittedLprTransaction, context);

    }

    @Override
    public StatusInfo cancelRegRequest(String regRequestId, ContextInfo context) throws DataValidationErrorException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<RegRequestInfo> getRegRequestsByIdList(List<String> regRequestIds, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<RegRequestInfo> getRegRequestsForStudentByTerm(String studentId, String termKey,
            List<String> requestStates, ContextInfo context) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        List<LPRTransactionInfo> retrievedLprTransactions = lprService.getLprTransactionsForPersonByAtp(termKey,
                studentId, requestStates, context);
        List<RegRequestInfo> regRequestInfos = new ArrayList<RegRequestInfo>();
        for (LPRTransactionInfo retrievedLprTransaction : retrievedLprTransactions) {
            regRequestInfos.add(regRequestAssembler.assemble(retrievedLprTransaction, context));
        }

        return regRequestInfos;
    }

    @Override
    public CourseWaitlistEntryInfo getCourseWaitlistEntry(String courseWaitlistEntryId, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public StatusInfo updateCourseWaitlistEntry(String courseWaitlistEntryId,
            CourseWaitlistEntryInfo courseWaitlistEntryInfo, ContextInfo context) throws DoesNotExistException,
            DataValidationErrorException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public StatusInfo reorderCourseWaitlistEntries(List<String> courseWaitlistEntryIds, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public StatusInfo insertCourseWaitlistEntryAtPosition(String courseWaitlistEntryId, Integer position,
            ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public StatusInfo removeCourseWaitlistEntry(String courseWaitlistEntryId, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public StatusInfo validateCourseWaitlistEntry(String validateTypeKey,
            CourseWaitlistEntryInfo courseWaitlistEntryInfo, ContextInfo context) throws DataValidationErrorException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public RegResponseInfo registerStudentFromWaitlist(String courseWaitlistEntryId, ContextInfo context)
            throws AlreadyExistsException, DataValidationErrorException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<CourseWaitlistEntryInfo> getCourseWaitlistEntriesForCourseOffering(String courseOfferingId,
            ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<CourseWaitlistEntryInfo> getCourseWaitlistEntriesForRegGroup(String regGroupId, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<CourseWaitlistEntryInfo> getCourseWaitlistEntriesForStudentInCourseOffering(String courseOfferingId,
            String studentId, ContextInfo context) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public CourseWaitlistEntryInfo getCourseWaitlistEntryForStudentInRegGroup(String regGroupId, String studentId,
            ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<CourseWaitlistEntryInfo> getCourseWaitlistEntriesForStudentByTerm(String studentId, String termKey,
            ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public CourseRegistrationInfo getCourseRegistration(String courseRegistrationId, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        return courseregistrationAssembler.assemble(lprService.getLuiPersonRelation(courseRegistrationId, context),
                context);

    }

    @Override
    public List<CourseRegistrationInfo> getCourseRegistrationsByIdList(List<String> courseRegistrationIds,
            ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {

        return courseregistrationAssembler.assembleList(
                lprService.getLuiPersonRelationsByIdList(courseRegistrationIds, context), context);

    }

    @Override
    public CourseRegistrationInfo getCourseRegistrationForStudentByCourseOffering(String studentId,
            String courseOfferingId, ContextInfo context) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        CourseRegistrationInfo courseRegInfo = courseregistrationAssembler.assemble(lprService
                .getLuiPersonRelationByState(studentId, courseOfferingId,
                        LuiPersonRelationServiceConstants.ENROLLED_STATE_KEY, context), context);
        return courseRegInfo;
    }

    @Override
    public List<CourseRegistrationInfo> getCourseRegistrationsForStudentByTerm(String studentId, String termKey,
            ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        List<CourseRegistrationInfo> courseRegInfoList = courseregistrationAssembler.assembleList(
                lprService.getLuiPersonRelationsForPersonAndAtp(studentId, termKey, context), context);

        return courseRegInfoList;
    }

//    @Override
//    public CourseScheduleViewInfo getRegisteredCoursesScheduleForStudentByTerm(String studentId, String termKey,
//            ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
//            OperationFailedException, PermissionDeniedException {
//
//        List<CourseScheduleEntryViewInfo> courseScheduleEntryViewInfoList = new ArrayList<CourseScheduleEntryViewInfo>();
//        List<CourseRegistrationInfo> courseRegistrationsInfoList = getCourseRegistrationsForStudentByTerm(studentId,
//                termKey, context);
//
//        for (CourseRegistrationInfo courseRegistrationInfo : courseRegistrationsInfoList) {
//
//            CourseOfferingInfo courseOfferingInfo = courseOfferingService.getCourseOffering(
//                    courseRegistrationInfo.getCourseOfferingId(), context);
//            RegGroupRegistrationInfo regGroupRegInfo = getRegGroupRegistrationForCourseRegistration(
//                    courseRegistrationInfo.getId(), context);
//            RegistrationGroupInfo regGroup = courseOfferingService.getRegistrationGroup(
//                    regGroupRegInfo.getRegGroupId(), context);
//
//            List<ActivityRegistrationInfo> activityGroupRegInfo = getActivityRegistrationsForCourseRegistration(
//                    courseRegistrationInfo.getId(), context);
//            List<String> registeredActivityOfferingIds = new ArrayList<String>();
//            for (ActivityRegistrationInfo activityReg : activityGroupRegInfo) {
//                registeredActivityOfferingIds.add(activityReg.getActivityOfferingId());
//            }
//
//            List<ActivityOfferingInfo> registeredActivityOfferings = courseOfferingService
//                    .getActivityOfferingsByIdList(registeredActivityOfferingIds, context);
//            CourseScheduleEntryViewInfo courseScheduleEntryViewInfo = new CourseScheduleEntryViewInfo(regGroup,
//                    courseOfferingInfo, registeredActivityOfferings, courseRegistrationInfo.getId(),
//                    courseRegistrationInfo.getCreditCount());
//            courseScheduleEntryViewInfoList.add(courseScheduleEntryViewInfo);
//        }
//        return new CourseScheduleViewInfo(courseScheduleEntryViewInfoList, studentId);
//    }

    @Override
    public List<CourseRegistrationInfo> getCourseRegistrationsByCourseOfferingId(String courseOfferingId,
            ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        List<CourseRegistrationInfo> courseRegInfoList = courseregistrationAssembler.assembleList(
                lprService.getLuiPersonRelationsForLui(courseOfferingId, context), context);
        return courseRegInfoList;
    }

    @Override
    public RegRequestInfo getRegRequestForCourseRegistration(String courseRegistrationId, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        RegRequestInfo regRequest = regRequestAssembler.assemble(
                lprService.getLprTransactionForLpr(courseRegistrationId, context), context);

        return regRequest;
    }

    @Override
    public List<RegRequestInfo> getRegRequestsForCourseOffering(String courseOfferingId, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        return regRequestAssembler
                .assembleList(lprService.getLprTransactionsForLui(courseOfferingId, context), context);

    }

    @Override
    public List<RegRequestInfo> getRegRequestsForCourseOfferingByStudent(String courseOfferingId, String studentId,
            ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        return regRequestAssembler.assembleList(
                lprService.getLprTransactionsForPersonByLui(studentId, courseOfferingId, context), context);
    }

 

    @Override
    public ActivityRegistrationInfo getActivityRegistration(String activityRegistrationId, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public ActivityRegistrationInfo getActivityRegistrationsByIdList(List<String> activityRegistrationIds,
            ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<ActivityRegistrationInfo> getActivityRegistrationsForActivityOffering(String courseRegistrationId,
            ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<ActivityRegistrationInfo> getActivityRegistrationsForStudentByTerm(String studentId, String termId,
            ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public RegGroupRegistrationInfo getRegGroupRegistration(String regGroupRegistrationId, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    

    @Override
    public List<RegGroupRegistrationInfo> getRegGroupRegistrationsByIdList(List<String> regGroupIds, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<RegGroupRegistrationInfo> getRegGroupRegistrationsByRegGroupId(String regGroupId, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public RegGroupRegistrationInfo getRegGroupRegistrationsForStudentByTerm(String studentId, String termId,
            ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<CourseRegistrationInfo> searchForCourseRegistrations(QueryByCriteria criteria, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<String> searchForCourseOfferingRegistrationIds(QueryByCriteria criteria, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<ActivityRegistrationInfo> searchForActivityRegistrations(QueryByCriteria criteria, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<String> searchForActivityRegistrationIds(QueryByCriteria criteria, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<RegGroupRegistrationInfo> searchForRegGroupRegistrations(QueryByCriteria criteria, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<String> searchForRegGroupRegistrationIds(QueryByCriteria criteria, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<CourseWaitlistEntryInfo> searchForCourseWaitlistEntries(QueryByCriteria criteria, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<String> searchForCourseWaitlistEntryIds(QueryByCriteria criteria, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public RegRequestInfo getRegRequest(String regRequestId, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        LPRTransactionInfo lprTransaction = lprService.getLprTransaction(regRequestId, context);
        RegRequestInfo regRequest = regRequestAssembler.assemble(lprTransaction, context);
        return regRequest;
    }

    @Override
    public StatusInfo deleteCourseWaitlistEntry(String courseWaitlistEntryId, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public RegResponseInfo dropStudentsFromRegGroups(List<String> regGroupIdList, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public RegResponseInfo moveStudentsBetweenRegGroups(String sourceRegGroupId, String destinationRegGroupId,
            ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

}
