package org.kuali.student.enrollment.classII.acal.service.impl;

import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.kuali.student.enrollment.acal.dto.AcademicCalendarInfo;
import org.kuali.student.enrollment.acal.dto.CampusCalendarInfo;
import org.kuali.student.enrollment.acal.dto.HolidayInfo;
import org.kuali.student.enrollment.acal.dto.KeyDateInfo;
import org.kuali.student.enrollment.acal.dto.RegistrationDateGroupInfo;
import org.kuali.student.enrollment.acal.dto.TermInfo;
import org.kuali.student.enrollment.acal.service.AcademicCalendarService;
import org.kuali.student.enrollment.classII.acal.service.assembler.AcademicCalendarAssembler;
import org.kuali.student.enrollment.classII.acal.service.assembler.TermAssembler;
import org.kuali.student.r2.common.datadictionary.dto.DictionaryEntryInfo;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.dto.StateInfo;
import org.kuali.student.r2.common.dto.StatusInfo;
import org.kuali.student.r2.common.dto.TypeInfo;
import org.kuali.student.r2.common.dto.ValidationResultInfo;
import org.kuali.student.r2.common.exceptions.AlreadyExistsException;
import org.kuali.student.r2.common.exceptions.DataValidationErrorException;
import org.kuali.student.r2.common.exceptions.DoesNotExistException;
import org.kuali.student.r2.common.exceptions.InvalidParameterException;
import org.kuali.student.r2.common.exceptions.MissingParameterException;
import org.kuali.student.r2.common.exceptions.OperationFailedException;
import org.kuali.student.r2.common.exceptions.PermissionDeniedException;
import org.kuali.student.r2.common.exceptions.VersionMismatchException;
import org.kuali.student.r2.core.atp.dto.AtpAtpRelationInfo;
import org.kuali.student.r2.core.atp.dto.AtpInfo;
import org.kuali.student.r2.core.atp.service.AtpService;
import org.springframework.transaction.annotation.Transactional;

@WebService(name = "AcademicCalendarService", serviceName = "AcademicCalendarService", portName = "AcademicCalendarService", targetNamespace = "http://student.kuali.org/wsdl/acal")
@Transactional(readOnly=true,noRollbackFor={DoesNotExistException.class},rollbackFor={Throwable.class})
public class AcademicCalendarServiceImpl implements AcademicCalendarService{
    private AtpService atpService;
    private AcademicCalendarAssembler acalAssembler;
    private TermAssembler termAssembler;
    
    @Override
    public List<String> getDataDictionaryEntryKeys(ContextInfo context) throws OperationFailedException,
            MissingParameterException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public DictionaryEntryInfo getDataDictionaryEntry(String entryKey, ContextInfo context)
            throws OperationFailedException, MissingParameterException, PermissionDeniedException,
            DoesNotExistException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public TypeInfo getAcademicCalendarType(String academicCalendarTypeKey, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<TypeInfo> getAcademicCalendarTypes(ContextInfo context) throws InvalidParameterException,
            MissingParameterException, OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public StateInfo getAcademicCalendarState(String academicCalendarStateKey, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<StateInfo> getAcademicCalendarStates(ContextInfo context) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, DoesNotExistException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public AcademicCalendarInfo getAcademicCalendar(String academicCalendarKey, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        AtpInfo atp = atpService.getAtp(academicCalendarKey, context);

        return acalAssembler.assemble(atp, context);
    }

    @Override
    public List<AcademicCalendarInfo> getAcademicCalendarsByKeyList(List<String> academicCalendarKeyList,
            ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<String> getAcademicCalendarKeysByType(String academicCalendarTypeKey, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<AcademicCalendarInfo> getAcademicCalendarsByYear(Integer year, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<AcademicCalendarInfo> getAcademicCalendarsByCredentialProgramType(String credentialProgramTypeKey,
            ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<AcademicCalendarInfo> getAcademicCalendarsByCredentialProgramTypeForYear(
            String credentialProgramTypeKey, Integer year, ContextInfo context) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<ValidationResultInfo> validateAcademicCalendar(String validationType,
            AcademicCalendarInfo academicCalendarInfo, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    @Transactional(readOnly=false)
    public AcademicCalendarInfo createAcademicCalendar(String academicCalendarKey,
            AcademicCalendarInfo academicCalendarInfo, ContextInfo context) throws AlreadyExistsException,
            DataValidationErrorException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {

        AtpInfo atp = acalAssembler.disassemble(academicCalendarInfo, context);
        try {
            AtpInfo existing = atpService.getAtp(academicCalendarKey, context);
            if(existing == null)
                atpService.createAtp(academicCalendarKey, atp, context);
        } catch (DoesNotExistException e1) {
            atpService.createAtp(academicCalendarKey, atp, context);
        }
       
        return academicCalendarInfo;
    }

    @Override
    @Transactional(readOnly=false)
    public AcademicCalendarInfo updateAcademicCalendar(String academicCalendarKey,
            AcademicCalendarInfo academicCalendarInfo, ContextInfo context) throws DataValidationErrorException,
            DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException, VersionMismatchException {
        
        try{
            atpService.getAtp(academicCalendarKey, context);
            AtpInfo atp;

            atp = acalAssembler.disassemble(academicCalendarInfo, context);

            if(atp != null)
                atpService.updateAtp(academicCalendarKey, atp, context);
                    
        } catch (DoesNotExistException e1) {
            return null;
        }
        
        return academicCalendarInfo;
    }

    @Override
    @Transactional(readOnly=false)
    public StatusInfo deleteAcademicCalendar(String academicCalendarKey, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        
        StatusInfo status = StatusInfo.newInstance();
        status.setSuccess(Boolean.TRUE);
        
         AtpInfo atp =  atpService.getAtp(academicCalendarKey, context);
            
           if(atp != null){
               //delete atp/acal
               atpService.deleteAtp(academicCalendarKey, context);
               
               //delete atpatpRelation
               List<AtpAtpRelationInfo > atpRels = atpService.getAtpAtpRelationsByAtp(academicCalendarKey, context);
               
               for(AtpAtpRelationInfo atpRelInfo : atpRels){
                       atpService.deleteAtpAtpRelation(atpRelInfo.getId(), context);
               }              
           }
        
        return status;
    }

    @Override
    public AcademicCalendarInfo copyAcademicCalendar(String academicCalendarKey, String newAcademicCalendarKey,
            ContextInfo context) throws AlreadyExistsException, DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public String getAcademicCalendarData(String academicCalendarKey, String calendarDataFormatTypeKey,
            ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public TypeInfo getCampusCalendarType(String campusCalendarTypeKey, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<TypeInfo> getCampusCalendarTypes(ContextInfo context) throws InvalidParameterException,
            MissingParameterException, OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public StateInfo getCampusCalendarState(String campusCalendarStateKey, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<StateInfo> getCampusCalendarStates(ContextInfo context) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, DoesNotExistException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public CampusCalendarInfo getCampusCalendar(String campusCalendarKey, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<CampusCalendarInfo> getCampusCalendarsByKeyList(List<String> campusCalendarKeyList, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<String> getCampusCalendarKeysByType(String campusCalendarTypeKey, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<CampusCalendarInfo> getCampusCalendarsByYear(Integer year, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<ValidationResultInfo> validateCampusCalendar(String validationType,
            CampusCalendarInfo campusCalendarInfo, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public CampusCalendarInfo createCampusCalendar(String campusCalendarKey, CampusCalendarInfo campusCalendarInfo,
            ContextInfo context) throws AlreadyExistsException, DataValidationErrorException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public CampusCalendarInfo updateCampusCalendar(String campusCalendarKey, CampusCalendarInfo campusCalendarInfo,
            ContextInfo context) throws DataValidationErrorException, DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException, VersionMismatchException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public StatusInfo deleteCampusCalendar(String campusCalendarKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public TypeInfo getTermType(String termTypeKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<TypeInfo> getTermTypes(ContextInfo context) throws InvalidParameterException,
            MissingParameterException, OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<TypeInfo> getTermTypesForAcademicCalendarType(String academicCalendarTypeKey, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<TypeInfo> getTermTypesForTermType(String termTypeKey, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public StateInfo getTermState(String termStateKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<StateInfo> getTermStates(ContextInfo context) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, DoesNotExistException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public TermInfo getTerm(String termKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        AtpInfo atp = atpService.getAtp(termKey, context);

        return termAssembler.assemble(atp, context);
    }

    @Override
    public List<TermInfo> getTermsByKeyList(List<String> termKeyList, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<String> getTermKeysByType(String termTypeKey, ContextInfo context) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<TermInfo> getTermsForAcademicCalendar(String academicCalendarKey, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<TermInfo> getIncludedTermsInTerm(String termKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<TermInfo> getContainingTerms(String termKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<ValidationResultInfo> validateTerm(String validationType, TermInfo termInfo, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    @Transactional(readOnly=false)
    public TermInfo createTerm(String termKey, TermInfo termInfo, ContextInfo context) throws AlreadyExistsException,
            DataValidationErrorException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        AtpInfo atp = termAssembler.disassemble(termInfo, context);
        try {
                AtpInfo existing = atpService.getAtp(termKey, context);
                if(existing == null)
                    atpService.createAtp(termKey, atp, context);
        } catch (DoesNotExistException e1) {
            atpService.createAtp(termKey, atp, context);
        }
        return termInfo;
    }

    @Override
    @Transactional(readOnly=false)
    public TermInfo updateTerm(String termKey, TermInfo termInfo, ContextInfo context)
            throws DataValidationErrorException, DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException, VersionMismatchException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    @Transactional(readOnly=false)
    public StatusInfo deleteTerm(String termKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public StatusInfo addTermToAcademicCalendar(String academicCalendarKey, String termKey, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException, AlreadyExistsException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public StatusInfo removeTermFromAcademicCalendar(String academicCalendarKey, String termKey, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public StatusInfo addTermToTerm(String termKey, String includedTermKey, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public StatusInfo removeTermFromTerm(String termKey, String includedTermKey, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public TypeInfo getKeyDateType(String keyDateTypeKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<TypeInfo> getKeyDateTypesForTermType(String termTypeKey, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public KeyDateInfo getKeyDate(String keyDateKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<KeyDateInfo> getKeyDatesByKeyList(List<String> keyDateKeyList, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<String> getKeyDateKeysByType(String keyDateTypeKey, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<KeyDateInfo> getKeyDatesForAcademicCalendar(String academicCalendarKey, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<KeyDateInfo> getKeyDatesForAcademicCalendarByDate(String academicCalendarKey, Date startDate,
            Date endDate, ContextInfo context) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<KeyDateInfo> getKeyDatesForTerm(String termKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<KeyDateInfo> getKeyDatesForTermByDate(String termKey, Date startDate, Date endDate, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<KeyDateInfo> getAllKeyDatesForTerm(String termKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<KeyDateInfo> getKeyDatesForAllTermsByDate(String termKey, Date startDate, Date endDate,
            ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<ValidationResultInfo> validateKeyDate(String validationType, KeyDateInfo keyDateInfo,
            ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public KeyDateInfo createKeyDateForTerm(String termKey, String keyDateKey, KeyDateInfo keyDateInfo,
            ContextInfo context) throws AlreadyExistsException, DataValidationErrorException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public KeyDateInfo updateKeyDate(String keyDateKey, KeyDateInfo keyDateInfo, ContextInfo context)
            throws DataValidationErrorException, DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException, VersionMismatchException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public StatusInfo deleteKeyDate(String keyDateKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public TypeInfo getHolidayType(String holidayTypeKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<TypeInfo> getHolidayTypesForCampusCalendarType(String campusCalendarTypeKey, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<HolidayInfo> getHolidaysForAcademicCalendar(String academicCalendarKey, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<ValidationResultInfo> validateHoliday(String validationType, HolidayInfo holidayInfo,
            ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public HolidayInfo createHolidayForCampusCalendar(String campusCalendarKey, String holidayKey,
            HolidayInfo holidayInfo, ContextInfo context) throws AlreadyExistsException, DataValidationErrorException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public HolidayInfo updateHoliday(String holidayKey, HolidayInfo holidayInfo, ContextInfo context)
            throws DataValidationErrorException, DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException, VersionMismatchException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public StatusInfo deleteHoliday(String holidayKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public RegistrationDateGroupInfo getRegistrationDateGroup(String termKey, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<ValidationResultInfo> validateRegistrationDateGroup(String validationType,
            RegistrationDateGroupInfo registrationDateGroupInfo, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public RegistrationDateGroupInfo updateRegistrationDateGroup(String termKey,
            RegistrationDateGroupInfo registrationDateGroupInfo, ContextInfo context)
            throws DataValidationErrorException, DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException, VersionMismatchException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public Integer getInstructionalDaysForTerm(String termKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    public AtpService getAtpService() {
        return atpService;
    }

    public void setAtpService(AtpService atpService) {
        this.atpService = atpService;
    }

    public AcademicCalendarAssembler getAcalAssembler() {
        return acalAssembler;
    }

    public void setAcalAssembler(AcademicCalendarAssembler acalAssembler) {
        this.acalAssembler = acalAssembler;
    }

    public TermAssembler getTermAssembler() {
        return termAssembler;
    }

    public void setTermAssembler(TermAssembler termAssembler) {
        this.termAssembler = termAssembler;
    }

}
