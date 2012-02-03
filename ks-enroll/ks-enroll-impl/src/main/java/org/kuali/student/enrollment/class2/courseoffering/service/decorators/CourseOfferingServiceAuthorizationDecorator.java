package org.kuali.student.enrollment.class2.courseoffering.service.decorators;

import java.util.List;

import org.kuali.rice.core.api.criteria.QueryByCriteria;
//import org.kuali.rice.kim.service.PermissionService;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.student.enrollment.courseoffering.dto.ActivityOfferingInfo;
import org.kuali.student.enrollment.courseoffering.dto.CourseOfferingInfo;
import org.kuali.student.enrollment.courseoffering.dto.RegistrationGroupInfo;
import org.kuali.student.enrollment.courseoffering.dto.SeatPoolDefinitionInfo;
import org.kuali.student.enrollment.courseoffering.service.CourseOfferingServiceDecorator;
import org.kuali.student.enrollment.courseregistration.dto.CourseRegistrationInfo;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.dto.StatusInfo;
import org.kuali.student.r2.common.dto.ValidationResultInfo;
import org.kuali.student.r2.common.exceptions.AlreadyExistsException;
import org.kuali.student.r2.common.exceptions.DataValidationErrorException;
import org.kuali.student.r2.common.exceptions.DoesNotExistException;
import org.kuali.student.r2.common.exceptions.InvalidParameterException;
import org.kuali.student.r2.common.exceptions.MissingParameterException;
import org.kuali.student.r2.common.exceptions.OperationFailedException;
import org.kuali.student.r2.common.exceptions.PermissionDeniedException;
import org.kuali.student.r2.common.exceptions.VersionMismatchException;
import org.kuali.student.r2.common.infc.HoldsPermissionService;
import org.kuali.student.r2.core.type.dto.TypeInfo;

public class CourseOfferingServiceAuthorizationDecorator extends CourseOfferingServiceDecorator implements HoldsPermissionService{
    public static final String ENRLLMENT_NAMESPACE = "KS-ENROLL";
    public static final String SERVICE_NAME = "CourseOfferingService.";
    
	private PermissionService permissionService;
		
	@Override
	public PermissionService getPermissionService() {
		return permissionService;
	}

	@Override
	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}
	
	@Override
	public CourseOfferingInfo getCourseOffering(String courseOfferingId,
			ContextInfo context) throws DoesNotExistException,
			InvalidParameterException, MissingParameterException,
			OperationFailedException, PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "getCourseOffering", null, null)) {
	        return getNextDecorator().getCourseOffering(courseOfferingId, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public List<CourseOfferingInfo> getCourseOfferingsByIds(
            List<String> courseOfferingIds, ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "getCourseOfferingsByIds", null, null)) {
	        return getNextDecorator().getCourseOfferingsByIds(courseOfferingIds, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public List<CourseOfferingInfo> getCourseOfferingsForCourseAndTerm(
			String courseId, String termId, ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "getCourseOfferingsForCourseAndTerm", null, null)) {
	        return getNextDecorator().getCourseOfferingsForCourseAndTerm(courseId, termId, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public List<String> getCourseOfferingIdsForTerm(String termId,
			Boolean useIncludedTerm, ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "getCourseOfferingIdsForTerm", null, null)) {
	        return getNextDecorator().getCourseOfferingIdsForTerm(termId, useIncludedTerm, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public List<String> getCourseOfferingIdsByTermAndSubjectArea(
			String termId, String subjectArea, ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "getCourseOfferingIdsByTermAndSubjectArea", null, null)) {
	        return getNextDecorator().getCourseOfferingIdsByTermAndSubjectArea(termId, subjectArea, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public List<CourseOfferingInfo> getCourseOfferingsByTermAndInstructor(
            String termId, String instructorId, ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "getCourseOfferingsByTermAndInstructor", null, null)) {
	        return getNextDecorator().getCourseOfferingsByTermAndInstructor(termId, instructorId, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public List<String> getCourseOfferingIdsByTermAndUnitContentOwner(
			String termId, String unitOwnerId, ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "getCourseOfferingIdsByTermAndUnitContentOwner", null, null)) {
	        return getNextDecorator().getCourseOfferingIdsByTermAndUnitContentOwner(termId, unitOwnerId, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public CourseOfferingInfo createCourseOffering(
            String courseId, String termId, String courseOfferingTypeKey,
            CourseOfferingInfo courseOfferingInfo,
            ContextInfo context) throws AlreadyExistsException,
			DoesNotExistException, DataValidationErrorException,
			InvalidParameterException, MissingParameterException,
			OperationFailedException, PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "createCourseOffering", null, null)) {
	        return getNextDecorator().createCourseOffering(courseId, termId, courseOfferingTypeKey, courseOfferingInfo, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public CourseOfferingInfo updateCourseOffering(String courseOfferingId,
			CourseOfferingInfo courseOfferingInfo, ContextInfo context)
			throws DataValidationErrorException, DoesNotExistException,
			InvalidParameterException, MissingParameterException,
			OperationFailedException, PermissionDeniedException,
			VersionMismatchException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "updateCourseOffering", null, null)) {
	        return getNextDecorator().updateCourseOffering(courseOfferingId, courseOfferingInfo, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public CourseOfferingInfo updateCourseOfferingFromCanonical(
			String courseOfferingId, ContextInfo context)
			throws DataValidationErrorException, DoesNotExistException,
			InvalidParameterException, MissingParameterException,
			OperationFailedException, PermissionDeniedException,
			VersionMismatchException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "updateCourseOfferingFromCanonical", null, null)) {
	        return getNextDecorator().updateCourseOfferingFromCanonical(courseOfferingId, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public StatusInfo deleteCourseOffering(String courseOfferingId,
			ContextInfo context) throws DoesNotExistException,
			InvalidParameterException, MissingParameterException,
			OperationFailedException, PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "deleteCourseOffering", null, null)) {
	        return getNextDecorator().deleteCourseOffering(courseOfferingId, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public List<ValidationResultInfo> validateCourseOffering(
			String validationType, CourseOfferingInfo courseOfferingInfo,
			ContextInfo context) throws DoesNotExistException,
			InvalidParameterException, MissingParameterException,
			OperationFailedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "validateCourseOffering", null, null)) {
	        return getNextDecorator().validateCourseOffering(validationType, courseOfferingInfo, context);
        }
        else {
        	throw new OperationFailedException ("Permission Denied");
        }
	}

    @Override
	public TypeInfo getActivityOfferingType(String activityOfferingTypeKey,
			ContextInfo context) throws DoesNotExistException,
			InvalidParameterException, MissingParameterException,
			OperationFailedException, PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "getActivityOfferingType", null, null)) {
	        return getNextDecorator().getActivityOfferingType(activityOfferingTypeKey, context);
        }
        else {
        	throw new PermissionDeniedException ();
        }
	}

	@Override
	public List<TypeInfo> getAllActivityOfferingTypes(ContextInfo context)
			throws InvalidParameterException, MissingParameterException,
			OperationFailedException, PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "getAllActivityOfferingTypes", null, null)) {
	        return getNextDecorator().getAllActivityOfferingTypes(context);
        }
        else {
        	throw new PermissionDeniedException ();
        }
	}

	@Override
	public List<TypeInfo> getActivityOfferingTypesForActivityType(
			String activityTypeKey, ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException, PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "getActivityOfferingTypesForActivityType", null, null)) {
	        return getNextDecorator().getActivityOfferingTypesForActivityType(activityTypeKey, context);
        }
        else {
        	throw new PermissionDeniedException ();
        }
	}

	@Override
	public ActivityOfferingInfo getActivityOffering(String activityOfferingId,
			ContextInfo context) throws DoesNotExistException,
			InvalidParameterException, MissingParameterException,
			OperationFailedException, PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "getActivityOffering", null, null)) {
	        return getNextDecorator().getActivityOffering(activityOfferingId, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public List<ActivityOfferingInfo> getActivityOfferingsByIds(
            List<String> activityOfferingIds, ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "getActivityOfferingsByIds", null, null)) {
	        return getNextDecorator().getActivityOfferingsByIds(activityOfferingIds, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public List<ActivityOfferingInfo> getActivityOfferingsByCourseOffering(
            String courseOfferingId, ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "getActivityOfferingsByCourseOffering", null, null)) {
	        return getNextDecorator().getActivityOfferingsByCourseOffering(courseOfferingId, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public ActivityOfferingInfo createActivityOffering(
			String courseOfferingId,  String activityOfferingTypeKey,
			ActivityOfferingInfo activityOfferingInfo, ContextInfo context)
			throws AlreadyExistsException, DataValidationErrorException,
			InvalidParameterException, MissingParameterException,
			OperationFailedException, PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "createActivityOffering", null, null)) {
	        return getNextDecorator().createActivityOffering(courseOfferingId, activityOfferingTypeKey, activityOfferingInfo, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

    @Override
	public ActivityOfferingInfo updateActivityOffering(
			String activityOfferingId,
			ActivityOfferingInfo activityOfferingInfo, ContextInfo context)
			throws DataValidationErrorException, DoesNotExistException,
			InvalidParameterException, MissingParameterException,
			OperationFailedException, PermissionDeniedException,
			VersionMismatchException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "updateActivityOffering", null, null)) {
	        return getNextDecorator().updateActivityOffering(activityOfferingId, activityOfferingInfo, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public StatusInfo deleteActivityOffering(String activityOfferingId,
			ContextInfo context) throws DoesNotExistException,
			InvalidParameterException, MissingParameterException,
			OperationFailedException, PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "deleteActivityOffering", null, null)) {
	        return getNextDecorator().deleteActivityOffering(activityOfferingId, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public List<ValidationResultInfo> validateActivityOffering(
			String validationType, ActivityOfferingInfo activityOfferingInfo,
			ContextInfo context) throws DoesNotExistException,
			InvalidParameterException, MissingParameterException,
			OperationFailedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "validateActivityOffering", null, null)) {
	        return getNextDecorator().validateActivityOffering(validationType, activityOfferingInfo, context);
        }
        else {
        	throw new OperationFailedException ("Permission Denied");
        }
	}

    @Override
	public Float calculateInClassContactHoursForTerm(String activityOfferingId,
			ContextInfo context) throws DoesNotExistException,
			InvalidParameterException, MissingParameterException,
			OperationFailedException, PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "calculateInClassContactHoursForTerm", null, null)) {
	        return getNextDecorator().calculateInClassContactHoursForTerm(activityOfferingId, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public Float calculateOutofClassContactHoursForTerm(
			String activityOfferingId, ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "calculateOutofClassContactHoursForTerm", null, null)) {
	        return getNextDecorator().calculateOutofClassContactHoursForTerm(activityOfferingId, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public Float calculateTotalContactHoursForTerm(String activityOfferingId,
			ContextInfo context) throws DoesNotExistException,
			InvalidParameterException, MissingParameterException,
			OperationFailedException, PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "calculateTotalContactHoursForTerm", null, null)) {
	        return getNextDecorator().calculateTotalContactHoursForTerm(activityOfferingId, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}


	@Override
	public RegistrationGroupInfo getRegistrationGroup(
			String registrationGroupId, ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "getRegistrationGroup", null, null)) {
	        return getNextDecorator().getRegistrationGroup(registrationGroupId, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public List<RegistrationGroupInfo> getRegistrationGroupsByIdList(
			List<String> registrationGroupIds, ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "getRegistrationGroupsByIdList", null, null)) {
	        return getNextDecorator().getRegistrationGroupsByIdList(registrationGroupIds, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public List<RegistrationGroupInfo> getRegGroupsForCourseOffering(
			String courseOfferingId, ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "getRegGroupsForCourseOffering", null, null)) {
	        return getNextDecorator().getRegGroupsForCourseOffering(courseOfferingId, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public List<RegistrationGroupInfo> getRegGroupsByFormatForCourse(
			String courseOfferingId, String formatTypeKey, ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "getRegGroupsByFormatForCourse", null, null)) {
	        return getNextDecorator().getRegGroupsByFormatForCourse(courseOfferingId, formatTypeKey, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public RegistrationGroupInfo createRegistrationGroup(
			String courseOfferingId,
			RegistrationGroupInfo registrationGroupInfo, ContextInfo context)
			throws AlreadyExistsException, DoesNotExistException,
			DataValidationErrorException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "createRegistrationGroup", null, null)) {
	        return getNextDecorator().createRegistrationGroup(courseOfferingId, registrationGroupInfo, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public RegistrationGroupInfo updateRegistrationGroup(
			String registrationGroupId,
			RegistrationGroupInfo registrationGroupInfo, ContextInfo context)
			throws DataValidationErrorException, DoesNotExistException,
			InvalidParameterException, MissingParameterException,
			OperationFailedException, PermissionDeniedException,
			VersionMismatchException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "updateRegistrationGroup", null, null)) {
	        return getNextDecorator().updateRegistrationGroup(registrationGroupId, registrationGroupInfo, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public StatusInfo deleteRegistrationGroup(String registrationGroupId,
			ContextInfo context) throws DoesNotExistException,
			InvalidParameterException, MissingParameterException,
			OperationFailedException, PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "deleteRegistrationGroup", null, null)) {
	        return getNextDecorator().deleteRegistrationGroup(registrationGroupId, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public List<ValidationResultInfo> validateRegistrationGroup(
			String validationType, RegistrationGroupInfo registrationGroupInfo,
			ContextInfo context) throws DoesNotExistException,
			InvalidParameterException, MissingParameterException,
			OperationFailedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "validateRegistrationGroup", null, null)) {
	        return getNextDecorator().validateRegistrationGroup(validationType, registrationGroupInfo, context);
        }
        else {
        	throw new OperationFailedException ("Permission Denied");
        }
	}

	@Override
	public SeatPoolDefinitionInfo getSeatPoolDefinition(
			String seatPoolDefinitionId, ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "getSeatPoolDefinition", null, null)) {
	        return getNextDecorator().getSeatPoolDefinition(seatPoolDefinitionId, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public List<SeatPoolDefinitionInfo> getSeatPoolsForCourseOffering(
			String courseOfferingId, ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "getSeatPoolsForCourseOffering", null, null)) {
	        return getNextDecorator().getSeatPoolsForCourseOffering(courseOfferingId, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public List<SeatPoolDefinitionInfo> getSeatPoolsForRegGroup(
			String registrationGroupId, ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "getSeatPoolsForRegGroup", null, null)) {
	        return getNextDecorator().getSeatPoolsForRegGroup(registrationGroupId, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public SeatPoolDefinitionInfo createSeatPoolDefinition(
			SeatPoolDefinitionInfo seatPoolDefinitionInfo, ContextInfo context)
			throws AlreadyExistsException, DataValidationErrorException,
			InvalidParameterException, MissingParameterException,
			OperationFailedException, PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "createSeatPoolDefinition", null, null)) {
	        return getNextDecorator().createSeatPoolDefinition(seatPoolDefinitionInfo, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public SeatPoolDefinitionInfo updateSeatPoolDefinition(
			String seatPoolDefinitionId,
			SeatPoolDefinitionInfo seatPoolDefinitionInfo, ContextInfo context)
			throws DataValidationErrorException, DoesNotExistException,
			InvalidParameterException, MissingParameterException,
			OperationFailedException, PermissionDeniedException,
			VersionMismatchException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "updateSeatPoolDefinition", null, null)) {
	        return getNextDecorator().updateSeatPoolDefinition(seatPoolDefinitionId, seatPoolDefinitionInfo, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public StatusInfo deleteSeatPoolDefinition(String seatPoolDefinitionId,
			ContextInfo context) throws DoesNotExistException,
			InvalidParameterException, MissingParameterException,
			OperationFailedException, PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "deleteSeatPoolDefinition", null, null)) {
	        return getNextDecorator().deleteSeatPoolDefinition(seatPoolDefinitionId, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public List<CourseOfferingInfo> searchForCourseOfferings(
			QueryByCriteria criteria, ContextInfo context)
			throws InvalidParameterException, MissingParameterException,
			OperationFailedException, PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "searchForCourseOfferings", null, null)) {
	        return getNextDecorator().searchForCourseOfferings(criteria, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public List<String> searchForCourseOfferingIds(QueryByCriteria criteria,
			ContextInfo context) throws InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "searchForCourseOfferingIds", null, null)) {
	        return getNextDecorator().searchForCourseOfferingIds(criteria, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public List<ActivityOfferingInfo> searchForActivityOfferings(
			QueryByCriteria criteria, ContextInfo context)
			throws InvalidParameterException, MissingParameterException,
			OperationFailedException, PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "searchForActivityOfferings", null, null)) {
	        return getNextDecorator().searchForActivityOfferings(criteria, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public List<String> searchForActivityOfferingIds(QueryByCriteria criteria,
			ContextInfo context) throws InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "searchForActivityOfferingIds", null, null)) {
	        return getNextDecorator().searchForActivityOfferingIds(criteria, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public List<CourseRegistrationInfo> searchForRegistrationGroups(
			QueryByCriteria criteria, ContextInfo context)
			throws InvalidParameterException, MissingParameterException,
			OperationFailedException, PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "searchForRegistrationGroups", null, null)) {
	        return getNextDecorator().searchForRegistrationGroups(criteria, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public List<String> searchForRegistrationGroupIds(QueryByCriteria criteria,
			ContextInfo context) throws InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "searchForRegistrationGroupIds", null, null)) {
	        return getNextDecorator().searchForRegistrationGroupIds(criteria, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public List<SeatPoolDefinitionInfo> searchForSeatpoolDefintions(
			QueryByCriteria criteria, ContextInfo context)
			throws InvalidParameterException, MissingParameterException,
			OperationFailedException, PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "searchForSeatpoolDefintions", null, null)) {
	        return getNextDecorator().searchForSeatpoolDefintions(criteria, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}

	@Override
	public List<String> searchForSeatpoolDefintionIds(QueryByCriteria criteria,
			ContextInfo context) throws InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException {
        if (null == context) {
            throw new MissingParameterException();
        }
           
        if (permissionService.isAuthorized(context.getPrincipalId(), ENRLLMENT_NAMESPACE, SERVICE_NAME + "searchForSeatpoolDefintionIds", null, null)) {
	        return getNextDecorator().searchForSeatpoolDefintionIds(criteria, context);
        }
        else {
           throw new PermissionDeniedException();
        }
	}
}
