/**
 * Copyright 2010 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.student.r2.core.atp.service;

import java.util.Date;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.kuali.rice.core.api.criteria.QueryByCriteria;

import org.kuali.student.r2.core.atp.dto.AtpAtpRelationInfo;
import org.kuali.student.r2.core.atp.dto.AtpInfo;
import org.kuali.student.r2.core.atp.dto.AtpMilestoneRelationInfo;
import org.kuali.student.r2.core.atp.dto.MilestoneInfo;

import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.dto.StatusInfo;
import org.kuali.student.r2.common.dto.ValidationResultInfo;

import org.kuali.student.r2.common.service.StateService;
import org.kuali.student.r2.common.service.TypeService;
import org.kuali.student.r2.common.datadictionary.service.DataDictionaryService;

import org.kuali.student.r2.common.exceptions.AlreadyExistsException;
import org.kuali.student.r2.common.exceptions.DataValidationErrorException;
import org.kuali.student.r2.common.exceptions.DoesNotExistException;
import org.kuali.student.r2.common.exceptions.InvalidParameterException;
import org.kuali.student.r2.common.exceptions.MissingParameterException;
import org.kuali.student.r2.common.exceptions.OperationFailedException;
import org.kuali.student.r2.common.exceptions.PermissionDeniedException;
import org.kuali.student.r2.common.exceptions.VersionMismatchException;

import org.kuali.student.r2.common.util.constants.AtpServiceConstants;

/**
 * Academic Time Period Service Description and Assumptions.
 *
 * This service supports the management of Academic Time Periods and
 * their associated Milestones. The intent is to provide a flexible
 * but structured way to define the various time frames that are used
 * throughout the definition, offering and scheduling of Learning
 * Units. This is a catalogue service with basic operations.
 *
 * Version: 1.0 (Dev)
 *
 * @Author tom
 * @Since Tue Apr 05 14:22:34 EDT 2011
 */

//@WebService(name = "AtpService", targetNamespace = AtpServiceConstants.NAMESPACE)
@WebService(name = "AtpService", serviceName = "AtpService", portName = "AtpService", targetNamespace = "http://student.kuali.org/wsdl/atp")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)

public interface AtpService extends DataDictionaryService, TypeService, StateService {

    /** 
     * Retrieves the details of a single Academic Time Period by atpKey.
     *
     * @param atpKey Unique key of the Academic Time Period to be retrieved
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return Details of the Academic Time Period requested
     * @throws DoesNotExistException atpKey not found
     * @throws InvalidParameterException invalid atpKey
     * @throws MissingParameterException missing atpKey
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public AtpInfo getAtp(@WebParam(name = "atpKey") String atpKey, @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves a list of Academic Time Periods corresponding to the
     * given list of ATP keys.
     *
     * @param atpKeyList list of ATPs to be retrieved
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return List of Academic Time Period keys of the given type
     * @throws DoesNotExistException an atpKey in list not found
     * @throws InvalidParameterException invalid atpKey
     * @throws MissingParameterException missing atpKey
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<AtpInfo> getAtpsByKeyList(@WebParam(name = "atpKeyList") List<String> atpKeyList, @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves a list of Academic Time Periods of the specified type.
     *
     * @param atpTypeKey ATP type to be retrieved
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return  a list of Academic Time Period keys 
     * @throws InvalidParameterException invalid atpTypeKey
     * @throws MissingParameterException missing atpTypeKey
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<String> getAtpKeysByType(@WebParam(name = "atpTypeKey") String atpTypeKey, @WebParam(name = "context") ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves the list of Academic Time Periods that the supplied
     * date falls within inclusive of the start end end date of the
     * ATP.
     *
     * @param searchDate Timestamp to be matched
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return List of Academic Time Periods that contain the supplied 
     *         searchDate
     * @throws InvalidParameterException invalid searchDate
     * @throws MissingParameterException missing searchDate
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<AtpInfo> getAtpsByDate(@WebParam(name = "searchDate") Date searchDate, @WebParam(name = "context") ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves the list of Academic Time Periods that the supplied
     * date falls within inclusive of the start end end date of the
     * ATP and whose type matches the specified type key.
     *
     * @param searchDate Timestamp to be matched
     * @param searchTypeKey typeKey to be matched
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return List of Academic Time Periods
     * @throws InvalidParameterException invalid searchDate
     * @throws MissingParameterException missing searchDate
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<AtpInfo> getAtpsByDateAndType(@WebParam(name = "searchDate") Date searchDate, @WebParam(name = "searchTypeKey") String searchTypeKey, @WebParam(name = "context") ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves the list of Academic Time Periods that are totally
     * contained within the supplied dates. The entire Atp falls
     * within the supplied dates inclusive of the dates.
     *
     * @param startDate start date of range
     * @param endDate end date of range
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return a list of Academic Time Periods 
     * @throws InvalidParameterException invalid parameter
     * @throws MissingParameterException missing parameter
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<AtpInfo> getAtpsByDates(@WebParam(name = "startDate") Date startDate, @WebParam(name = "endDate") Date endDate, @WebParam(name = "context") ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves the list of Academic Time Periods that are totally
     * contained within the supplied dates. The entire Atp falls
     * within the supplied dates inclusive of the dates and whose
     * typeKey matches the specified type.
     *
     * @param startDate start date of range
     * @param endDate end date of range
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return a list of Academic Time Periods 
     * @throws InvalidParameterException invalid parameter
     * @throws MissingParameterException missing parameter
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<AtpInfo> getAtpsByDatesAndType(@WebParam(name = "startDate") Date startDate, @WebParam(name = "endDate") Date endDate, @WebParam(name = "searchTypeKey") String searchTypeKey, @WebParam(name = "context") ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves the list of Academic Time Periods whose start dates
     * fall within the supplied date range, inclusive of the start and
     * end dates on the range.
     *
     * @param searchDateRangeStart start date of range
     * @param searchDateRangeEnd end date of range
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return List of Academic Time Periods
     * @throws InvalidParameterException invalid searchDate
     * @throws MissingParameterException missing searchDate
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<AtpInfo> getAtpsByStartDateRange(@WebParam(name = "searchDateRangeStart") Date searchDateRangeStart, @WebParam(name = "searchDateRangeEnd") Date searchDateRangeEnd,
            @WebParam(name = "context") ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves the list of Academic Time Periods whose start dates
     * fall within the supplied date range, inclusive of the start and
     * end dates on the range and whose type matches the specified
     * type.
     *
     * @param searchDateRangeStart start date of range
     * @param searchDateRangeEnd end date of range
     * @param searchTypeKey typeKey to be matched
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return List of Academic Time Periods
     * @throws InvalidParameterException invalid searchDate
     * @throws MissingParameterException missing searchDate
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<AtpInfo> getAtpsByStartDateRangeAndType(@WebParam(name = "searchDateRangeStart") Date searchDateRangeStart, @WebParam(name = "searchDateRangeEnd") Date searchDateRangeEnd, @WebParam(name = "searchTypeKey") String searchTypeKey, @WebParam(name = "context") ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;    

    /**
     * Searches for Atps based on the criteria and returns a list
     * of Atp identifiers which match the search criteria.
     *
     * @param criteria the search criteria
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return list of Atp Ids
     * @throws InvalidParameterException invalid parameter
     * @throws MissingParameterException parameter is missing
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<String> searchForAtpKeys(@WebParam(name = "criteria") QueryByCriteria criteria, @WebParam(name = "context") ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Searches for Atps based on the criteria and returns a list of
     * Atps which match the search criteria.
     * 
     * @param criteria the search criteria
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return list of Atps
     * @throws InvalidParameterException invalid parameter
     * @throws MissingParameterException parameter is missing
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<AtpInfo> searchForAtps(@WebParam(name = "criteria") QueryByCriteria criteria, @WebParam(name = "context") ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Validates an academic time period. Depending on the value of
     * validationType, this validation could be limited to tests on
     * just the current object and its directly contained subobjects
     * or expanded to perform all tests related to this object. If an
     * identifier is present for the academic time period and a record
     * is found for that identifier, the validation checks if the
     * academic time period can be shifted to the new values. If a
     * record cannot be found for the identifier, it is assumed that
     * the record does not exist and as such, the checks performed
     * will be much shallower, typically mimicking those performed by
     * setting the validationType to the current object. This is a
     * slightly different pattern from the standard validation as the
     * caller provides the identifier in the create statement instead
     * of the server assigning an identifier.
     *
     * @param validationType Identifier of the extent of validation
     * @param atpInfo The academic time period information to be tested.
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return Results from performing the validation
     * @throws DoesNotExistException validationTypeKey not found
     * @throws InvalidParameterException invalid validationTypeKey, atpInfo
     * @throws MissingParameterException missing validationTypeKey, atpInfo
     * @throws OperationFailedException unable to complete request
     */
    public List<ValidationResultInfo> validateAtp(@WebParam(name = "validationType") String validationType, @WebParam(name = "atpInfo") AtpInfo atpInfo, @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException;

    /** 
     * Creates a new Academic Time Period.
     *
     * @param atpKey Key of ATP to be created
     * @param atpInfo Details of ATP to be created
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return Details of ATP just created
     * @throws AlreadyExistsException ATP being created already exists
     * @throws DataValidationErrorException One or more values invalid for this operation
     * @throws InvalidParameterException One or more parameters invalid
     * @throws MissingParameterException One or more parameters missing
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public AtpInfo createAtp(@WebParam(name = "atpKey") String atpKey, @WebParam(name = "atpInfo") AtpInfo atpInfo, @WebParam(name = "context") ContextInfo context) throws AlreadyExistsException, DataValidationErrorException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Updates an existing Academic Time Period.
     *
     * @param atpKey Key of ATP to be updated
     * @param atpInfo Details of updates to ATP being updated
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return Details of ATP just updated
     * @throws DataValidationErrorException One or more values invalid 
     *         for this operation
     * @throws DoesNotExistException ATP being updated does not exist
     * @throws InvalidParameterException One or more parameters invalid
     * @throws MissingParameterException One or more parameters missing
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     * @throws VersionMismatchException The action was attempted on an out 
     *         of date version.
     */
    public AtpInfo updateAtp(@WebParam(name = "atpKey") String atpKey, @WebParam(name = "atpInfo") AtpInfo atpInfo, @WebParam(name = "context") ContextInfo context) throws DataValidationErrorException, DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException, VersionMismatchException;

    /** 
     * Deletes an existing Academic Time Period.
     *
     * @param atpKey the key of the ATP to be deleted
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return status of the operation (success, failed)
     * @throws DoesNotExistException ATP being deleted does not exist
     * @throws InvalidParameterException One or more parameters invalid
     * @throws MissingParameterException One or more parameters missing
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public StatusInfo deleteAtp(@WebParam(name = "atpKey") String atpKey, @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves the details of the specified milestone.
     *
     * @param milestoneKey Unique id of the milestone to be retrieved
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return Details of requested milestone
     * @throws DoesNotExistException milestoneKey not found
     * @throws InvalidParameterException invalid milestoneKey
     * @throws MissingParameterException missing milestoneKey
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public MilestoneInfo getMilestone(@WebParam(name = "milestoneKey") String milestoneKey, @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves a list of Milestones corresponding to the given list
     * of Milestone keys.
     *
     * @param milestoneKeyList list of Milestones to be retrieved
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return the list of milestone keys 
     * @throws DoesNotExistException a milestoneKey in list not found
     * @throws InvalidParameterException invalid milestoneKey
     * @throws MissingParameterException missing milestoneKey
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<MilestoneInfo> getMilestonesByKeyList(@WebParam(name = "milestoneKeyList") List<String> milestoneKeyList, @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves a list of Milestones of the specified type.
     *
     * @param milestoneTypeKey Milestone type to be retrieved
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return  a list of Milestone keys 
     * @throws InvalidParameterException invalid milestoneTypeKey
     * @throws MissingParameterException missing milestoneTypeKey
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<String> getMilestoneKeysByType(@WebParam(name = "milestoneTypeKey") String milestoneTypeKey, @WebParam(name = "context") ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves the list of milestones for a specified Academic Time
     * Period.
     *
     * @param atpKey Unique key of the Academic Time Period to be retieved
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return List of milestones for this Academic Time Period
     * @throws InvalidParameterException invalid atpKey
     * @throws MissingParameterException missing atpKey
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<MilestoneInfo> getMilestonesByAtp(@WebParam(name = "atpKey") String atpKey, @WebParam(name = "context") ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves the list of milestones that fall within a specified
     * set of dates inclusive of the dates.
     *
     * @param startDate Start Date for date span
     * @param endDate End Date for date span
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return List of milestones that fall within this set of dates
     * @throws InvalidParameterException One or more parameters invalid
     * @throws MissingParameterException One or more parameters missing
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<MilestoneInfo> getMilestonesByDates(@WebParam(name = "startDate") Date startDate, @WebParam(name = "endDate") Date endDate, @WebParam(name = "context") ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves a list of milestones of a specified type that fall
     * within a specified set of dates inclusive of the dates.
     *
     * @param milestoneTypeKey Milestone type to be retrieved
     * @param startDate Start Date for date range
     * @param endDate End Date for date range
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return List of milestones of this milestone type within this set 
     *         of dates
     * @throws InvalidParameterException One or more parameters invalid
     * @throws MissingParameterException One or more parameters missing
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<MilestoneInfo> getMilestonesByDatesAndType(@WebParam(name = "milestoneTypeKey") String milestoneTypeKey, @WebParam(name = "startDate") Date startDate, @WebParam(name = "endDate") Date endDate, @WebParam(name = "context") ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Searches for Milestones based on the criteria and returns a list
     * of Milestone identifiers which match the search criteria.
     *
     * @param criteria the search criteria
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return list of Milestone Ids
     * @throws InvalidParameterException invalid parameter
     * @throws MissingParameterException parameter is missing
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<String> searchForMilestoneKeys(@WebParam(name = "criteria") QueryByCriteria criteria, @WebParam(name = "context") ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Searches for Milestones based on the criteria and returns a list of
     * Milestones which match the search criteria.
     * 
     * @param criteria the search criteria
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return list of Milestones
     * @throws InvalidParameterException invalid parameter
     * @throws MissingParameterException parameter is missing
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<MilestoneInfo> searchForMilestones(@WebParam(name = "criteria") QueryByCriteria criteria, @WebParam(name = "context") ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Validates a milestone. Depending on the value of
     * validationType, this validation could be limited to tests on
     * just the current object and its directly contained subobjects
     * or expanded to perform all tests related to this object. If an
     * identifier is present for the milestone and a record is found
     * for that identifier, the validation checks if the milestone can
     * be shifted to the new values. If a record cannot be found for
     * the identifier, it is assumed that the record does not exist
     * and as such, the checks performed will be much shallower,
     * typically mimicking those performed by setting the
     * validationType to the current object. This is a slightly
     * different pattern from the standard validation as the caller
     * provides the identifier in the create statement instead of the
     * server assigning an identifier.
     *
     * @param validationType Identifier of the extent of validation
     * @param milestoneInfo The milestone information to be tested.
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return Results from performing the validation
     * @throws DoesNotExistException validationTypeKey not found
     * @throws InvalidParameterException invalid parameter
     * @throws MissingParameterException missing parameter
     * @throws OperationFailedException unable to complete request
     */
    public List<ValidationResultInfo> validateMilestone(@WebParam(name = "validationType") String validationType, @WebParam(name = "milestoneInfo") MilestoneInfo milestoneInfo, @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException;

    /** 
     * Create a new milestone.
     *
     * @param milestoneKey the Id of milestone to be added
     * @param milestoneInfo Details of milestone to be added
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return Details of the newly created milestone
     * @throws AlreadyExistsException Milestone already exists
     * @throws DataValidationErrorException One or more values invalid 
     *         for this operation
     * @throws InvalidParameterException One or more parameters invalid
     * @throws MissingParameterException One or more parameters missing
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public MilestoneInfo createMilestone(@WebParam(name = "milestoneKey") String milestoneKey, @WebParam(name = "milestoneInfo") MilestoneInfo milestoneInfo, @WebParam(name = "context") ContextInfo context) throws AlreadyExistsException, DataValidationErrorException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Updates an existing milestone.
     *
     * @param milestoneKey the Id of milestone to be updated
     * @param milestoneInfo Details of milestone to be updated
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return Details of the updated milestone
     * @throws DataValidationErrorException One or more values invalid 
     *         for this operation
     * @throws DoesNotExistException Milestone being updated does not exist
     * @throws InvalidParameterException One or more parameters invalid
     * @throws MissingParameterException One or more parameters missing
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     * @throws VersionMismatchException The action was attempted on an out of 
     *         date version
     */
    public MilestoneInfo updateMilestone(@WebParam(name = "milestoneKey") String milestoneKey, @WebParam(name = "milestoneInfo") MilestoneInfo milestoneInfo, @WebParam(name = "context") ContextInfo context) throws DataValidationErrorException, DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException, VersionMismatchException;

    /** 
     * Deletes an existing milestone from all ATPs.
     *
     * @param milestoneKey the Id of milestone to be removed
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return status of the operation (success, failed)
     * @throws DoesNotExistException Milestone being removed does not exist
     * @throws InvalidParameterException One or more parameters invalid
     * @throws MissingParameterException One or more parameters missing
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public StatusInfo deleteMilestone(@WebParam(name = "milestoneKey") String milestoneKey, @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves an ATP Relationship.
     *
     * @param atpAtpRelationId a unique id of the atp atp relation 
     *        to be retrieved
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return Details of requested Atp atp relation
     * @throws DoesNotExistException atpAtpRelationId not found
     * @throws InvalidParameterException invalid atpAtprelationId
     * @throws MissingParameterException invalid atpAtpRelationId
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public AtpAtpRelationInfo getAtpAtpRelation(@WebParam(name = "atpAtpRelationId") String atpAtpRelationId, @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves a list of AtpAtpRelations corresponding to the given list
     * of identifiers.
     *
     * @param atpAtpRelationIdList list of AtpAtpRelations to be retrieved
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return the list of AtpAtpRelations
     * @throws DoesNotExistException an atpAtpRelationId in list not found
     * @throws InvalidParameterException invalid atpAtpRelationId
     * @throws MissingParameterException missing atpAtpRelationId
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<AtpAtpRelationInfo> getAtpAtpRelationsByIdList(@WebParam(name = "atpAtpRelationIdList") List<String> atpAtpRelationIdList, @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves a list of AtpAtpRelation Ids of the specified type.
     *
     * @param atpAtpRelationTypeKey Atp atp relation type to be retrieved
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return  a list of AtpAtpRelation identifiers
     * @throws InvalidParameterException invalid atpRelationTypeKey
     * @throws MissingParameterException missing atpRelationTypeKey
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<String> getAtpAtpRelationIdsByType(@WebParam(name = "atpAtpRelationTypeKey") String atpAtpRelationTypeKey, @WebParam(name = "context") ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves all ATP Relationships by ATP. Any relationship to the
     * given ATP is retrieved independent of which side of the
     * relationship the ATP resides.
     *
     * @param atpKey  a unique key of an ATP
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return a list of Atp atp relationships
     * @throws DoesNotExistException atpKey not found
     * @throws InvalidParameterException invalid atpKey
     * @throws MissingParameterException missing atpKey
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<AtpAtpRelationInfo> getAtpAtpRelationsByAtp(@WebParam(name = "atpKey") String atpKey, @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves ATP relationships by both Atp and the AtpAtpRelation
     * Type.
     * 
     * @param atpKey a unique key for an ATP
     * @param relationTypeKey a unique key for an ATP ATP relation
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return a list of ATP-ATP relations
     * @throws DoesNotExistException atpKey does not exist
     * @throws InvalidParameterException a parameter is invalid
     * @throws MissingParameterException a parameter is missing
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure occurred
     */
    public List<AtpAtpRelationInfo> getAtpAtpRelationsByAtpAndRelationType(@WebParam(name = "atpKey") String atpKey, @WebParam(name = "relationTypeKey") String relationTypeKey, @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException; 

    /**
     * Searches for AtpAtpRelations based on the criteria and returns
     * a list of AtpAtpRelation identifiers which match the search
     * criteria.
     *
     * @param criteria the search criteria
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return list of AtpAtpRelation Ids
     * @throws InvalidParameterException invalid parameter
     * @throws MissingParameterException parameter is missing
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<String> searchForAtpAtpRelationIds(@WebParam(name = "criteria") QueryByCriteria criteria, @WebParam(name = "context") ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Searches for AtpAtpRelations based on the criteria and returns
     * a list of AtpAtpRelations which match the search criteria.
     * 
     * @param criteria the search criteria
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return list of AtpAtpRelations
     * @throws InvalidParameterException invalid parameter
     * @throws MissingParameterException parameter is missing
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<AtpAtpRelationInfo> searchForAtpAtpRelations(@WebParam(name = "criteria") QueryByCriteria criteria, @WebParam(name = "context") ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Validates an ATP/ATP relationship. Depending on the value
     * of validationType, this validation could be limited to tests on
     * just the current object and its directly contained subobjects
     * or expanded to perform all tests related to this object. If an
     * identifier is present for the relationship and a record is
     * found for that identifier, the validation checks if the
     * relationship can be shifted to the new values. If a
     * record cannot be found for the identifier, it is assumed that
     * the record does not exist and as such, the checks performed
     * will be much shallower, typically mimicking those performed by
     * setting the validationType to the current object. This is a
     * slightly different pattern from the standard validation as the
     * caller provides the identifier in the create statement instead
     * of the server assigning an identifier.
     *
     * @param validationType Identifier of the extent of validation
     * @param atpAtpRelationInfo The ATP Relationship
     *        to be tested.
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return Results from performing the validation
     * @throws DoesNotExistException validationTypeKey not found
     * @throws InvalidParameterException invalid parameter
     * @throws MissingParameterException missing parameter
     * @throws OperationFailedException unable to complete request
     */
    public List<ValidationResultInfo> validateAtpAtpRelation(@WebParam(name = "validationType") String validationType, @WebParam(name = "atpAtpRelationInfo") AtpAtpRelationInfo atpAtpRelationInfo, @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException;

    /** 
     * Creates an ATP relationship.
     *
     * @param atpAtpRelationInfo the relationship to be created
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return the atp atp relation that was created
     * @throws AlreadyExistsException atp relation added already exists
     * @throws InvalidParameterException One or more parameters invalid
     * @throws MissingParameterException One or more parameters missing
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     * @throws DataValidationErrorException if the relation fails validation 
     *         checks
     */
    public AtpAtpRelationInfo createAtpAtpRelation(@WebParam(name = "atpAtpRelationInfo") AtpAtpRelationInfo atpAtpRelationInfo, @WebParam(name = "context") ContextInfo context) throws AlreadyExistsException, DataValidationErrorException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Updates an ATP Mielstone Relationship.
     *
     * @param atpAtpRelationId the Id of the ATP Relation to be 
     *        updated
     * @param atpAtpRelationInfo the ATP relation to be updated
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return status
     * @throws DataValidationErrorException One or more values invalid 
     *         for this operation
     * @throws DoesNotExistException atp relation does not exist
     * @throws InvalidParameterException One or more parameters invalid
     * @throws MissingParameterException One or more parameters missing
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     * @throws VersionMismatchException The action was attempted on an out of 
     *         date version
     */
    public AtpAtpRelationInfo updateAtpAtpRelation(@WebParam(name = "atpAtpRelationId") String atpAtpRelationId, @WebParam(name = "atpAtpRelationInfo") AtpAtpRelationInfo atpAtpRelationInfo, @WebParam(name = "context") ContextInfo context) throws DataValidationErrorException, DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException, VersionMismatchException;

    /** 
     * Removes an existing ATP relationship.
     *
     * @param atpAtpRelationId the Id of relatiosnhip
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return status of the operation (success, failed)
     * @throws DoesNotExistException atp relation being removed does not exist
     * @throws InvalidParameterException One or more parameters invalid
     * @throws MissingParameterException One or more parameters missing
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public StatusInfo deleteAtpAtpRelation(@WebParam(name = "atpAtpRelationId") String atpAtpRelationId, @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves an ATP Milestone Relationship.
     *
     * @param atpMilestoneRelationId Unique id of the atp milestone relation 
     *        to be retrieved
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return Details of requested Atp milestone relation
     * @throws DoesNotExistException atpMilestoneRelationId not found
     * @throws InvalidParameterException invalid atpMilestonerelationId
     * @throws MissingParameterException invalid atpMilestoneRelationId
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public AtpMilestoneRelationInfo getAtpMilestoneRelation(@WebParam(name = "atpMilestoneRelationId") String atpMilestoneRelationId, @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves a list of AtpMilestoneRelations corresponding to the
     * given list of identifiers.
     *
     * @param atpMilestoneRelationIdList list of AtpMilestoneRelations to be 
     *        retrieved
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return the list of AtpMilestoneRelations
     * @throws DoesNotExistException an atpMilestoneRelationId in list 
     *         not found
     * @throws InvalidParameterException invalid atpMilestoneRelationId
     * @throws MissingParameterException missing atpMilestoneRelationId
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<AtpMilestoneRelationInfo> getAtpMilestoneRelationsByIdList(@WebParam(name = "atpMilestoneRelationIdList") List<String> atpMilestoneRelationIdList, @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves a list of AtpMilestoneRelations of the specified type.
     *
     * @param atpMilestoneRelationTypeKey Atp to Milestone relation type 
     *        to use to search
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return a list of AtpMilestoneRelation identifiers
     * @throws InvalidParameterException invalid atpMilestoneRelationTypeKey
     * @throws MissingParameterException missing atpMilestoneRelationTypeKey
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<String> getAtpMilestoneRelationIdsByType(@WebParam(name = "atpMilestoneRelationTypeKey") String atpMilestoneRelationTypeKey, @WebParam(name = "context") ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves all ATP Milestone Relationships by ATP.
     *
     * @param atpKey Unique key of an ATP
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return a list of Atp milestone relationships
     * @throws DoesNotExistException atpKey not found
     * @throws InvalidParameterException invalid atpKey
     * @throws MissingParameterException missing atpKey
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<AtpMilestoneRelationInfo> getAtpMilestoneRelationsByAtp(@WebParam(name = "atpKey") String atpKey, @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves all ATP Milestone Relationships by Milestone.
     *
     * @param milestoneKey Unique key of a Milestone
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return a list of Atp milestone relationships
     * @throws DoesNotExistException atpKey not found
     * @throws InvalidParameterException invalid milestoneKey
     * @throws MissingParameterException missing milestoneKey
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<AtpMilestoneRelationInfo> getAtpMilestoneRelationsByMilestone(@WebParam(name = "milestoneKey") String milestoneKey, @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Searches for AtpMilestoneRelations based on the criteria and
     * returns a list of AtpMilestoneRelation identifiers which match
     * the search criteria.
     *
     * @param criteria the search criteria
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return list of AtpMilestoneRelation Ids
     * @throws InvalidParameterException invalid parameter
     * @throws MissingParameterException parameter is missing
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<String> searchForAtpMilestoneRelationIds(@WebParam(name = "criteria") QueryByCriteria criteria, @WebParam(name = "context") ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Searches for AtpMilestoneRelations based on the criteria and
     * returns a list of AtpMilestoneRelations which match the search
     * criteria.
     * 
     * @param criteria the search criteria
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return list of AtpMilestoneRelations
     * @throws InvalidParameterException invalid parameter
     * @throws MissingParameterException parameter is missing
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<AtpMilestoneRelationInfo> searchForAtpMilestoneRelations(@WebParam(name = "criteria") QueryByCriteria criteria, @WebParam(name = "context") ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Validates an ATP/Milestone relationship. Depending on the value
     * of validationType, this validation could be limited to tests on
     * just the current object and its directly contained subobjects
     * or expanded to perform all tests related to this object. If an
     * identifier is present for the relationship and a record is
     * found for that identifier, the validation checks if the
     * relationship can be shifted to the new values. If a
     * record cannot be found for the identifier, it is assumed that
     * the record does not exist and as such, the checks performed
     * will be much shallower, typically mimicking those performed by
     * setting the validationType to the current object. This is a
     * slightly different pattern from the standard validation as the
     * caller provides the identifier in the create statement instead
     * of the server assigning an identifier.
     *
     * @param validationType Identifier of the extent of validation
     * @param atpMilestoneRelationInfo The ATP Milestone Relationship
     *        to be tested.
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return Results from performing the validation
     * @throws DoesNotExistException validationTypeKey not found
     * @throws InvalidParameterException invalid validationTypeKey, atpInfo
     * @throws MissingParameterException missing validationTypeKey, atpInfo
     * @throws OperationFailedException unable to complete request
     */
    public List<ValidationResultInfo> validateAtpMilestoneRelation(@WebParam(name = "validationType") String validationType, @WebParam(name = "atpMilestoneRelationInfo") AtpMilestoneRelationInfo atpMilestoneRelationInfo, @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException;

    /** 
     * Adds a Milestone to an ATP by creating a relationship.
     *
     * @param atpMilestoneRelationInfo the relationship
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return status
     * @throws AlreadyExistsException Milestone being added already exists
     * @throws InvalidParameterException One or more parameters invalid
     * @throws MissingParameterException One or more parameters missing
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     * @throws DataValidationErrorException if the atp milestone relation has 
     *         validation errors
     */
    public AtpMilestoneRelationInfo createAtpMilestoneRelation(@WebParam(name = "atpMilestoneRelationInfo") AtpMilestoneRelationInfo atpMilestoneRelationInfo, @WebParam(name = "context") ContextInfo context) throws AlreadyExistsException, DataValidationErrorException,  InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Updates an ATP Mielstone Relationship.
     *
     * @param atpMilestoneRelationId the Id of the ATP Milestone Relation to be 
     *        updated
     * @param atpMilestoneRelationInfo the ATP Milestone relation to be updated
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return status
     * @throws DataValidationErrorException One or more values invalid for this 
     *         operation
     * @throws DoesNotExistException Milestone does not exist
     * @throws InvalidParameterException One or more parameters invalid
     * @throws MissingParameterException One or more parameters missing
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     * @throws VersionMismatchException The action was attempted on an out of 
     *         date version
     */
    public AtpMilestoneRelationInfo updateAtpMilestoneRelation(@WebParam(name = "atpMilestoneRelationId") String atpMilestoneRelationId, @WebParam(name = "atpMilestoneRelationInfo") AtpMilestoneRelationInfo atpMilestoneRelationInfo, @WebParam(name = "context") ContextInfo context) throws DataValidationErrorException, DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException, VersionMismatchException;

    /** 
     * Removes an existing milestone from an ATP by deleting the relationship.
     *
     * @param atpMilestoneRelationId the Id of ATP Milestone Relatiosnhip
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return status of the operation (success, failed)
     * @throws DoesNotExistException Milestone being removed does not exist
     * @throws InvalidParameterException One or more parameters invalid
     * @throws MissingParameterException One or more parameters missing
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public StatusInfo deleteAtpMilestoneRelation(@WebParam(name = "atpMilestoneRelationId") String atpMilestoneRelationId, @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;
}
