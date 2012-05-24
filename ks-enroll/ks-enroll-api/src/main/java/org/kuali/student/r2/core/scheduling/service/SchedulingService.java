/*
 * Copyright 2012 The Kuali Foundation Licensed under the
 *  Educational Community License, Version 2.0 (the "License"); you may
 *  not use this file except in compliance with the License. You may
 *  obtain a copy of the License at
 *
 *   http://www.osedu.org/licenses/ECL-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an "AS IS"
 *  BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 *  or implied. See the License for the specific language governing
 *  permissions and limitations under the License.
 */

package org.kuali.student.r2.core.scheduling.service;

import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.dto.StatusInfo;
import org.kuali.student.r2.common.dto.TimeOfDayInfo;
import org.kuali.student.r2.common.dto.ValidationResultInfo;
import org.kuali.student.r2.common.exceptions.DataValidationErrorException;
import org.kuali.student.r2.common.exceptions.DoesNotExistException;
import org.kuali.student.r2.common.exceptions.InvalidParameterException;
import org.kuali.student.r2.common.exceptions.MissingParameterException;
import org.kuali.student.r2.common.exceptions.OperationFailedException;
import org.kuali.student.r2.common.exceptions.PermissionDeniedException;
import org.kuali.student.r2.common.exceptions.ReadOnlyException;
import org.kuali.student.r2.common.exceptions.VersionMismatchException;
import org.kuali.student.r2.core.scheduling.constants.SchedulingServiceConstants;
import org.kuali.student.r2.core.scheduling.dto.ScheduleBatchInfo;
import org.kuali.student.r2.core.scheduling.dto.ScheduleBatchRespInfo;
import org.kuali.student.r2.core.scheduling.dto.ScheduleInfo;
import org.kuali.student.r2.core.scheduling.dto.ScheduleRequestInfo;
import org.kuali.student.r2.core.scheduling.dto.ScheduleRespInfo;
import org.kuali.student.r2.core.scheduling.dto.TimeSlotInfo;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * @Version 1.0
 * @Author Sri komandur@uw.edu
 */
@WebService(name = "SchedulingService", serviceName = "SchedulingService", portName = "SchedulingService", targetNamespace = SchedulingServiceConstants.NAMESPACE)
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface SchedulingService {

    /**
     * Retrieves a Schedule
     *
     * @param scheduleId  unique Id of a Schedule
     * @param contextInfo Context information containing the principalId and
     *                    locale information about the caller of service
     *                    operation
     * @return the Schedule
     * @throws DoesNotExistException     scheduleId not found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException scheduleId or contextInfo is missing or
     *                                   null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public ScheduleInfo getSchedule(@WebParam(name = "scheduleId") String scheduleId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a list of Schedules corresponding to the given list of Schedule
     * Ids.
     *
     * @param scheduleIds list of Schedules to be retrieved
     * @param contextInfo Context information containing the principalId and
     *                    locale information about the caller of service
     *                    operation
     * @return a list of Schedules
     * @throws DoesNotExistException     a scheduleId in list not found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException missing scheduleId or contextInfo is
     *                                   missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<ScheduleInfo> getSchedulesByIds(@WebParam(name = "scheduleIds") List<String> scheduleIds, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a list of Schedule Ids by Schedule Type.
     *
     * @param scheduleTypeKey an identifier for a Schedule Type
     * @param contextInfo     Context information containing the principalId and
     *                        locale information about the caller of service
     *                        operation
     * @return a list of Schedule identifiers matching scheduleTypeKey or an
     *         empty list if none found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException scheduleTypeKey or contextInfo is
     *                                   missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<String> getScheduleIdsByType(@WebParam(name = "scheduleTypeKey") String scheduleTypeKey, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Searches for Schedules based on the criteria and returns a list of
     * Schedule identifiers which match the search criteria.
     *
     * @param criteria    the search criteria
     * @param contextInfo Context information containing the principalId and
     *                    locale information about the caller of service
     *                    operation
     * @return list of Schedule Ids
     * @throws InvalidParameterException invalid criteria or contextInfo
     * @throws MissingParameterException missing criteria or contextInfo
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<String> searchForScheduleIds(@WebParam(name = "criteria") QueryByCriteria criteria, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Searches for Schedules based on the criteria and returns a list of
     * Schedules which match the search criteria.
     *
     * @param criteria    the search criteria
     * @param contextInfo Context information containing the principalId and
     *                    locale information about the caller of service
     *                    operation
     * @return list of Schedules
     * @throws InvalidParameterException invalid criteria or contextInfo
     * @throws MissingParameterException missing criteria or contextInfo
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<ScheduleInfo> searchForSchedules(@WebParam(name = "criteria") QueryByCriteria criteria, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Validates a Schedule. Depending on the value of validationType, this
     * validation could be limited to tests on just the current Schedule and its
     * directly contained sub-objects or expanded to perform all tests related
     * to this Schedule. If an identifier is present for the Schedule (and/or
     * one of its contained sub-objects) and a record is found for that
     * identifier, the validation checks if the Schedule can be updated to the
     * new values. If an identifier is not present or a record does not exist,
     * the validation checks if the object with the given data can be created.
     *
     * @param validationTypeKey the identifier for the validation Type
     * @param scheduleTypeKey   the identifier for the schedule Type
     * @param scheduleInfo      detailed information about the schedule
     * @param contextInfo       Context information containing the principalId
     *                          and locale information about the caller of
     *                          service operation
     * @return a list of validation results or an empty list if validation
     *         succeeded
     * @throws DoesNotExistException     validationTypeKey, scheduleId, not
     *                                   found
     * @throws InvalidParameterException invalid scheduleInfo or contextInfo
     * @throws MissingParameterException validationTypeKey, scheduleId or
     *                                   contextInfo is missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<ValidationResultInfo> validateSchedule(@WebParam(name = "validationTypeKey") String validationTypeKey, @WebParam(name = "scheduleTypeKey") String scheduleTypeKey, @WebParam(name = "scheduleInfo") ScheduleInfo scheduleInfo, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Creates a Schedule
     *
     * @param scheduleTypeKey the identifier for the schedule Type
     * @param scheduleInfo    detailed information about the schedule
     * @param contextInfo     Context information containing the principalId and
     *                        locale information about the caller of service
     *                        operation
     * @return detailed information about the schedule
     * @throws DataValidationErrorException supplied data is invalid
     * @throws DoesNotExistException        scheduleId does not exist
     * @throws InvalidParameterException    invalid scheduleInfo or contextInfo
     * @throws MissingParameterException    scheduleId or contextInfo is missing
     *                                      or null
     * @throws OperationFailedException     unable to complete request
     * @throws PermissionDeniedException    an authorization failure occurred
     * @throws ReadOnlyException            an attempt at supplying information
     *                                      designated as read only
     */
    public ScheduleInfo createSchedule(@WebParam(name = "scheduleTypeKey") String scheduleTypeKey, @WebParam(name = "scheduleInfo") ScheduleInfo scheduleInfo, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DataValidationErrorException, DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException, ReadOnlyException;

    /**
     * Updates a schedule.
     *
     * @param scheduleId   identifier of the schedule  to be updated
     * @param scheduleInfo information about the object scheduleInfo to be
     *                     updated
     * @param contextInfo  context information containing the principalId and
     *                     locale information about the caller of service
     *                     operation
     * @return updated schedule information
     * @throws DataValidationErrorException one or more values invalid for this
     *                                      operation
     * @throws DoesNotExistException        scheduleId not found
     * @throws InvalidParameterException    invalid scheduleInfo or contextInfo
     * @throws MissingParameterException    scheduleId, scheduleInfo or
     *                                      contextInfo is missing or null
     * @throws OperationFailedException     unable to complete request
     * @throws PermissionDeniedException    an authorization failure occurred
     * @throws ReadOnlyException            an attempt at supplying information
     *                                      designated as read-only
     * @throws VersionMismatchException     optimistic locking failure or the
     *                                      action was attempted on an out of
     *                                      date version
     */
    public ScheduleInfo updateSchedule(@WebParam(name = "scheduleId") String scheduleId, @WebParam(name = "scheduleInfo") ScheduleInfo scheduleInfo, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DataValidationErrorException, DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException, ReadOnlyException, VersionMismatchException;

    /**
     * Removes schedule relationship between a person and a slot.
     *
     * @param scheduleId  Schedule  identifier
     * @param contextInfo context information containing the principalId and
     *                    locale information about the caller of service
     *                    operation
     * @return status of the operation (success, failed)
     * @throws DoesNotExistException     scheduleId not found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException scheduleId or contextInfo is missing or
     *                                   null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public StatusInfo deleteSchedule(@WebParam(name = "scheduleId") String scheduleId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a ScheduleBatch
     *
     * @param scheduleBatchId a unique Id of a ScheduleBatch
     * @param contextInfo     Context information containing the principalId and
     *                        locale information about the caller of service
     *                        operation
     * @return the ScheduleBatch
     * @throws DoesNotExistException     scheduleBatchId not found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException scheduleBatchId or contextInfo is
     *                                   missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public ScheduleBatchInfo getScheduleBatch(@WebParam(name = "scheduleBatchId") String scheduleBatchId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a list of ScheduleBatches corresponding to the given list of
     * ScheduleBatch Ids.
     *
     * @param scheduleBatchIds list of ScheduleBatches to be retrieved
     * @param contextInfo      Context information containing the principalId
     *                         and locale information about the caller of
     *                         service operation
     * @return a list of ScheduleBatches
     * @throws DoesNotExistException     a scheduleBatchId in list not found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException missing scheduleBatchId or contextInfo
     *                                   is missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<ScheduleBatchInfo> getScheduleBatchesByIds(@WebParam(name = "scheduleBatchIds") List<String> scheduleBatchIds, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a list of ScheduleBatch Ids by ScheduleBatch Type.
     *
     * @param scheduleBatchTypeKey an identifier for a ScheduleBatch Type
     * @param contextInfo          Context information containing the
     *                             principalId and locale information about the
     *                             caller of service operation
     * @return a list of ScheduleBatch identifiers matching scheduleBatchTypeKey
     *         or an empty list if none found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException scheduleBatchTypeKey or contextInfo is
     *                                   missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<String> getScheduleBatchIdsByType(@WebParam(name = "scheduleBatchTypeKey") String scheduleBatchTypeKey, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a list of ScheduleBatches associated with a ScheduleRequest
     *
     * @param scheduleRequestId an identifier for a ScheduleBatch
     * @param contextInfo       Context information containing the principalId
     *                          and locale information about the caller of
     *                          service operation
     * @return a list of ScheduleRequest identifiers matching
     *         scheduleRequestTypeKey or an empty list if none found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException scheduleRequestTypeKey or contextInfo
     *                                   is missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<ScheduleBatchInfo> getScheduleBatchesForScheduleRequest(@WebParam(name = "scheduleRequestId") String scheduleRequestId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Searches for ScheduleBatches based on the criteria and returns a list of
     * ScheduleBatch identifiers which match the search criteria.
     *
     * @param criteria    the search criteria
     * @param contextInfo Context information containing the principalId and
     *                    locale information about the caller of service
     *                    operation
     * @return list of ScheduleBatch Ids
     * @throws InvalidParameterException invalid criteria or contextInfo
     * @throws MissingParameterException missing criteria or contextInfo
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<String> searchForScheduleBatchIds(@WebParam(name = "criteria") QueryByCriteria criteria, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Searches for ScheduleBatches based on the criteria and returns a list of
     * ScheduleBatches which match the search criteria.
     *
     * @param criteria    the search criteria
     * @param contextInfo Context information containing the principalId and
     *                    locale information about the caller of service
     *                    operation
     * @return list of ScheduleBatches
     * @throws InvalidParameterException invalid criteria or contextInfo
     * @throws MissingParameterException missing criteria or contextInfo
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<ScheduleBatchInfo> searchForScheduleBatches(@WebParam(name = "criteria") QueryByCriteria criteria, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Validates a ScheduleBatch. Depending on the value of validationType, this
     * validation could be limited to tests on just the current ScheduleBatch
     * and its directly contained sub-objects or expanded to perform all tests
     * related to this ScheduleBatch. If an identifier is present for the
     * ScheduleBatch (and/or one of its contained sub-objects) and a record is
     * found for that identifier, the validation checks if the ScheduleBatch can
     * be updated to the new values. If an identifier is not present or a record
     * does not exist, the validation checks if the object with the given data
     * can be created.
     *
     * @param validationTypeKey    the identifier for the validation Type
     * @param scheduleBatchTypeKey the identifier for the scheduleBatch Type
     * @param scheduleBatchInfo    detailed information about the scheduleBatch
     * @param contextInfo          Context information containing the
     *                             principalId and locale information about the
     *                             caller of service operation
     * @return a list of validation results or an empty list if validation
     *         succeeded
     * @throws DoesNotExistException     validationTypeKey, scheduleBatchId, not
     *                                   found
     * @throws InvalidParameterException invalid scheduleBatchInfo or
     *                                   contextInfo
     * @throws MissingParameterException validationTypeKey, scheduleBatchId or
     *                                   contextInfo is missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<ValidationResultInfo> validateScheduleBatch(@WebParam(name = "validationTypeKey") String validationTypeKey, @WebParam(name = "scheduleBatchTypeKey") String scheduleBatchTypeKey, @WebParam(name = "scheduleBatchInfo") ScheduleBatchInfo scheduleBatchInfo, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Creates a ScheduleBatch
     *
     * @param scheduleBatchTypeKey the identifier for the scheduleBatch Type
     * @param scheduleBatchInfo    detailed information about the scheduleBatch
     * @param contextInfo          Context information containing the
     *                             principalId and locale information about the
     *                             caller of service operation
     * @return detailed information about the scheduleBatch
     * @throws DataValidationErrorException supplied data is invalid
     * @throws DoesNotExistException        scheduleBatchId does not exist
     * @throws InvalidParameterException    invalid scheduleBatchInfo or
     *                                      contextInfo
     * @throws MissingParameterException    scheduleBatchId or contextInfo is
     *                                      missing or null
     * @throws OperationFailedException     unable to complete request
     * @throws PermissionDeniedException    an authorization failure occurred
     * @throws ReadOnlyException            an attempt at supplying information
     *                                      designated as read only
     */
    public ScheduleBatchInfo createScheduleBatch(@WebParam(name = "scheduleBatchTypeKey") String scheduleBatchTypeKey, @WebParam(name = "scheduleBatchInfo") ScheduleBatchInfo scheduleBatchInfo, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DataValidationErrorException, DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException, ReadOnlyException;

    /**
     * Updates a scheduleBatch.
     *
     * @param scheduleBatchId   identifier of the scheduleBatch to be updated
     * @param scheduleBatchInfo information about the object scheduleBatchInfo
     *                          to be updated
     * @param contextInfo       context information containing the principalId
     *                          and locale information about the caller of
     *                          service operation
     * @return updated scheduleBatch information
     * @throws DataValidationErrorException one or more values invalid for this
     *                                      operation
     * @throws DoesNotExistException        scheduleBatchId not found
     * @throws InvalidParameterException    invalid scheduleBatchInfo or
     *                                      contextInfo
     * @throws MissingParameterException    scheduleBatchId, scheduleBatchInfo
     *                                      or contextInfo is missing or null
     * @throws OperationFailedException     unable to complete request
     * @throws PermissionDeniedException    an authorization failure occurred
     * @throws ReadOnlyException            an attempt at supplying information
     *                                      designated as read-only
     * @throws VersionMismatchException     optimistic locking failure or the
     *                                      action was attempted on an out of
     *                                      date version
     */
    public ScheduleBatchInfo updateScheduleBatch(@WebParam(name = "scheduleBatchId") String scheduleBatchId, @WebParam(name = "scheduleBatchInfo") ScheduleBatchInfo scheduleBatchInfo, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DataValidationErrorException, DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException, ReadOnlyException, VersionMismatchException;

    /**
     * Removes scheduleBatch relationship between a person and a slot.
     *
     * @param scheduleBatchId ScheduleBatch  identifier
     * @param contextInfo     context information containing the principalId and
     *                        locale information about the caller of service
     *                        operation
     * @return status of the operation (success, failed)
     * @throws DoesNotExistException     scheduleBatchId not found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException scheduleBatchId or contextInfo is
     *                                   missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public StatusInfo deleteScheduleBatch(@WebParam(name = "scheduleBatchId") String scheduleBatchId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Adds schedule requests to the batch
     *
     * @param scheduleRequestIds schedule requests to be added to the batch
     * @param scheduleBatchId    batch identifier
     * @param contextInfo        information containing the principalId and
     *                           locale information about the caller of service
     *                           operation
     * @return status of the operation (success, failed)
     * @throws DoesNotExistException     one of the scheduleRequestIds or
     *                                   scheduleBatchId is not found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException scheduleRequestIds, scheduleBatchId or
     *                                   contextInfo is missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public StatusInfo addScheduleRequestsToScheduleBatch(@WebParam(name = "scheduleRequestIds") List<String> scheduleRequestIds, @WebParam(name = "scheduleBatchId") List<String> scheduleBatchId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Removes schedule requests from the batch
     *
     * @param scheduleRequestIds schedule requests to be added to the batch
     * @param scheduleBatchId    batch identifier
     * @param contextInfo        information containing the principalId and
     *                           locale information about the caller of service
     *                           operation
     * @return status of the operation (success, failed)
     * @throws DoesNotExistException     one of the scheduleRequestIds or
     *                                   scheduleBatchId is not found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException scheduleRequestIds, scheduleBatchId or
     *                                   contextInfo is missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public StatusInfo removeScheduleRequestsFromScheduleBatch(@WebParam(name = "scheduleRequestIds") List<String> scheduleRequestIds, @WebParam(name = "scheduleBatchId") List<String> scheduleBatchId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a ScheduleBatchResp
     *
     * @param scheduleBatchRespId a unique Id of a ScheduleBatchResp
     * @param contextInfo             Context information containing the
     *                                principalId and locale information about
     *                                the caller of service operation
     * @return the ScheduleBatchResp
     * @throws DoesNotExistException     scheduleBatchRespId not found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException scheduleBatchRespId or contextInfo
     *                                   is missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public ScheduleBatchRespInfo getScheduleBatchResp(@WebParam(name = "scheduleBatchRespId") String scheduleBatchRespId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a list of ScheduleBatchResps corresponding to the given
     * list of ScheduleBatchResp Ids.
     *
     * @param scheduleBatchRespIds list of ScheduleBatchResps to be
     *                                 retrieved
     * @param contextInfo              Context information containing the
     *                                 principalId and locale information about
     *                                 the caller of service operation
     * @return a list of ScheduleBatchResps
     * @throws DoesNotExistException     a scheduleBatchRespId in list not
     *                                   found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException missing scheduleBatchRespId or
     *                                   contextInfo is missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<ScheduleBatchRespInfo> getScheduleBatchRespsByIds(@WebParam(name = "scheduleBatchRespIds") List<String> scheduleBatchRespIds, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a list of ScheduleBatchResp Ids by ScheduleBatchResp
     * Type.
     *
     * @param scheduleBatchRespTypeKey an identifier for a ScheduleBatchResp
     *                                     Type
     * @param contextInfo                  Context information containing the
     *                                     principalId and locale information
     *                                     about the caller of service
     *                                     operation
     * @return a list of ScheduleBatchResp identifiers matching
     *         scheduleBatchRespTypeKey or an empty list if none found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException scheduleBatchRespTypeKey or
     *                                   contextInfo is missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<String> getScheduleBatchRespIdsByType(@WebParam(name = "scheduleBatchRespTypeKey") String scheduleBatchRespTypeKey, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a list of ScheduleBatchResps corresponding to the given
     * ScheduleBatch
     *
     * @param scheduleBatchId batch identifier
     * @param contextInfo     Context information containing the principalId and
     *                        locale information about the caller of service
     *                        operation
     * @return a list of ScheduleBatchResps
     * @throws DoesNotExistException     scheduleBatchId not found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException scheduleBatchId or contextInfo is
     *                                   missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<ScheduleBatchRespInfo> getScheduleBatchRespsByScheduleBatchRequest(@WebParam(name = "scheduleBatchId") String scheduleBatchId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a ScheduleRequest
     *
     * @param scheduleRequestId a unique Id of a ScheduleRequest
     * @param contextInfo       Context information containing the principalId
     *                          and locale information about the caller of
     *                          service operation
     * @return the ScheduleRequest
     * @throws DoesNotExistException     scheduleRequestId not found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException scheduleRequestId or contextInfo is
     *                                   missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public ScheduleRequestInfo getScheduleRequest(@WebParam(name = "scheduleRequestId") String scheduleRequestId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a list of ScheduleRequests corresponding to the given list of
     * ScheduleRequest Ids.
     *
     * @param scheduleRequestIds list of ScheduleRequests to be retrieved
     * @param contextInfo        Context information containing the principalId
     *                           and locale information about the caller of
     *                           service operation
     * @return a list of ScheduleRequests
     * @throws DoesNotExistException     a scheduleRequestId in list not found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException missing scheduleRequestId or
     *                                   contextInfo is missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<ScheduleRequestInfo> getScheduleRequestsByIds(@WebParam(name = "scheduleRequestIds") List<String> scheduleRequestIds, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a list of ScheduleRequest Ids by ScheduleRequest Type.
     *
     * @param scheduleRequestTypeKey an identifier for a ScheduleRequest Type
     * @param contextInfo            Context information containing the
     *                               principalId and locale information about
     *                               the caller of service operation
     * @return a list of ScheduleRequest identifiers matching
     *         scheduleRequestTypeKey or an empty list if none found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException scheduleRequestTypeKey or contextInfo
     *                                   is missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<String> getScheduleRequestIdsByType(@WebParam(name = "scheduleRequestTypeKey") String scheduleRequestTypeKey, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a list of ScheduleRequest Ids by Ref Object Type.
     *
     * @param refObjectType an identifier for a ref object Type
     * @param refObjectId   an
     * @param contextInfo   Context information containing the principalId and
     *                      locale information about the caller of service
     *                      operation
     * @return a list of ScheduleRequest identifiers matching
     *         scheduleRequestTypeKey or an empty list if none found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException scheduleRequestTypeKey or contextInfo
     *                                   is missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */

    public List<String> getScheduleRequestsByRefObject(@WebParam(name = "refObjectType") String refObjectType, @WebParam(name = "refObjectId") String refObjectId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a list of ScheduleRequests associated with a ScheduleBatch
     *
     * @param scheduleBatchId an identifier for a ScheduleBatch
     * @param contextInfo     Context information containing the principalId and
     *                        locale information about the caller of service
     *                        operation
     * @return a list of ScheduleRequest identifiers matching
     *         scheduleRequestTypeKey or an empty list if none found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException scheduleRequestTypeKey or contextInfo
     *                                   is missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<ScheduleRequestInfo> getScheduleRequestsForScheduleBatch(@WebParam(name = "scheduleBatchId") String scheduleBatchId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;


    /**
     * Searches for ScheduleRequests based on the criteria and returns a list of
     * ScheduleRequest identifiers which match the search criteria.
     *
     * @param criteria    the search criteria
     * @param contextInfo Context information containing the principalId and
     *                    locale information about the caller of service
     *                    operation
     * @return list of ScheduleRequest Ids
     * @throws InvalidParameterException invalid criteria or contextInfo
     * @throws MissingParameterException missing criteria or contextInfo
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<String> searchForScheduleRequestIds(@WebParam(name = "criteria") QueryByCriteria criteria, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Searches for ScheduleRequests based on the criteria and returns a list of
     * ScheduleRequests which match the search criteria.
     *
     * @param criteria    the search criteria
     * @param contextInfo Context information containing the principalId and
     *                    locale information about the caller of service
     *                    operation
     * @return list of ScheduleRequests
     * @throws InvalidParameterException invalid criteria or contextInfo
     * @throws MissingParameterException missing criteria or contextInfo
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<ScheduleRequestInfo> searchForScheduleRequests(@WebParam(name = "criteria") QueryByCriteria criteria, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Validates a ScheduleRequest. Depending on the value of validationType,
     * this validation could be limited to tests on just the current
     * ScheduleRequest and its directly contained sub-objects or expanded to
     * perform all tests related to this ScheduleRequest. If an identifier is
     * present for the ScheduleRequest (and/or one of its contained sub-objects)
     * and a record is found for that identifier, the validation checks if the
     * ScheduleRequest can be updated to the new values. If an identifier is not
     * present or a record does not exist, the validation checks if the object
     * with the given data can be created.
     *
     * @param validationTypeKey      the identifier for the validation Type
     * @param scheduleRequestTypeKey the identifier for the scheduleRequest
     *                               Type
     * @param scheduleRequestInfo    detailed information about the
     *                               scheduleRequest
     * @param contextInfo            Context information containing the
     *                               principalId and locale information about
     *                               the caller of service operation
     * @return a list of validation results or an empty list if validation
     *         succeeded
     * @throws DoesNotExistException     validationTypeKey, scheduleRequestId,
     *                                   not found
     * @throws InvalidParameterException invalid scheduleRequestInfo or
     *                                   contextInfo
     * @throws MissingParameterException validationTypeKey, scheduleRequestId or
     *                                   contextInfo is missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<ValidationResultInfo> validateScheduleRequest(@WebParam(name = "validationTypeKey") String validationTypeKey, @WebParam(name = "scheduleRequestTypeKey") String scheduleRequestTypeKey, @WebParam(name = "scheduleRequestInfo") ScheduleRequestInfo scheduleRequestInfo, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Creates a ScheduleRequest
     *
     * @param scheduleRequestTypeKey the identifier for the scheduleRequest
     *                               Type
     * @param scheduleRequestInfo    detailed information about the
     *                               scheduleRequest
     * @param contextInfo            Context information containing the
     *                               principalId and locale information about
     *                               the caller of service operation
     * @return detailed information about the scheduleRequest
     * @throws DataValidationErrorException supplied data is invalid
     * @throws DoesNotExistException        scheduleRequestId does not exist
     * @throws InvalidParameterException    invalid scheduleRequestInfo or
     *                                      contextInfo
     * @throws MissingParameterException    scheduleRequestId or contextInfo is
     *                                      missing or null
     * @throws OperationFailedException     unable to complete request
     * @throws PermissionDeniedException    an authorization failure occurred
     * @throws ReadOnlyException            an attempt at supplying information
     *                                      designated as read only
     */
    public ScheduleRequestInfo createScheduleRequest(@WebParam(name = "scheduleRequestTypeKey") String scheduleRequestTypeKey, @WebParam(name = "scheduleRequestInfo") ScheduleRequestInfo scheduleRequestInfo, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DataValidationErrorException, DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException, ReadOnlyException;

    /**
     * Updates a scheduleRequest.
     *
     * @param scheduleRequestId   identifier of the scheduleRequest to be
     *                            updated
     * @param scheduleRequestInfo information about the object scheduleRequestInfo
     *                            to be updated
     * @param contextInfo         context information containing the principalId
     *                            and locale information about the caller of
     *                            service operation
     * @return updated scheduleRequest information
     * @throws DataValidationErrorException one or more values invalid for this
     *                                      operation
     * @throws DoesNotExistException        scheduleRequestId not found
     * @throws InvalidParameterException    invalid scheduleRequestInfo or
     *                                      contextInfo
     * @throws MissingParameterException    scheduleRequestId, scheduleRequestInfo
     *                                      or contextInfo is missing or null
     * @throws OperationFailedException     unable to complete request
     * @throws PermissionDeniedException    an authorization failure occurred
     * @throws ReadOnlyException            an attempt at supplying information
     *                                      designated as read-only
     * @throws VersionMismatchException     optimistic locking failure or the
     *                                      action was attempted on an out of
     *                                      date version
     */
    public ScheduleRequestInfo updateScheduleRequest(@WebParam(name = "scheduleRequestId") String scheduleRequestId, @WebParam(name = "scheduleRequestInfo") ScheduleRequestInfo scheduleRequestInfo, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DataValidationErrorException, DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException, ReadOnlyException, VersionMismatchException;

    /**
     * Removes scheduleRequest relationship between a person and a slot.
     *
     * @param scheduleRequestId ScheduleRequest  identifier
     * @param contextInfo       context information containing the principalId
     *                          and locale information about the caller of
     *                          service operation
     * @return status of the operation (success, failed)
     * @throws DoesNotExistException     scheduleRequestId not found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException scheduleRequestId or contextInfo is
     *                                   missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public StatusInfo deleteScheduleRequest(@WebParam(name = "scheduleRequestId") String scheduleRequestId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;


    /**
     * Retrieves a TimeSlot
     *
     * @param timeSlotId  a unique Id of a TimeSlot
     * @param contextInfo Context information containing the principalId and
     *                    locale information about the caller of service
     *                    operation
     * @return the TimeSlot
     * @throws DoesNotExistException     timeSlotId not found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException timeSlotId or contextInfo is missing or
     *                                   null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public TimeSlotInfo getTimeSlot(@WebParam(name = "timeSlotId") String timeSlotId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a list of TimeSlots corresponding to the given list of TimeSlot
     * Ids.
     *
     * @param timeSlotIds list of TimeSlots to be retrieved
     * @param contextInfo Context information containing the principalId and
     *                    locale information about the caller of service
     *                    operation
     * @return a list of TimeSlots
     * @throws DoesNotExistException     a timeSlotId in list not found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException missing timeSlotId or contextInfo is
     *                                   missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<TimeSlotInfo> getTimeSlotsByIds(@WebParam(name = "timeSlotIds") List<String> timeSlotIds, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a list of TimeSlot Ids by TimeSlot Type.
     *
     * @param timeSlotTypeKey an identifier for an TimeSlot Type
     * @param contextInfo     Context information containing the principalId and
     *                        locale information about the caller of service
     *                        operation
     * @return a list of TimeSlot identifiers matching timeSlotTypeKey or an
     *         empty list if none found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException timeSlotTypeKey or contextInfo is
     *                                   missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<String> getTimeSlotIdsByType(@WebParam(name = "timeSlotTypeKey") String timeSlotTypeKey, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a list of TimeSlots by TimeSlot Type, days of week and start
     * time. Parameter daysOfWeek follows the Java standard: Sunday=1 to
     * Saturday=7
     *
     * @param timeSlotTypeKey identifier for the given slot type
     * @param daysOfWeek      days of the week of interest
     * @param startTime       start time of interest
     * @param contextInfo     Context information containing the principalId and
     *                        locale information about the caller of service
     *                        operation
     * @return a list of TimeSlots matching timeSlotTypeKey, daysOfWeek and
     *         startTime; empty list if none found
     * @throws InvalidParameterException invalid daysOfWeek, startTime or
     *                                   contextInfo
     * @throws MissingParameterException timeSlotTypeKey, daysOfWeek, startTime
     *                                   or contextInfo is missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<TimeSlotInfo> getTimeSlotsByDaysAndStartTime(@WebParam(name = "timeSlotTypeKey") String timeSlotTypeKey, @WebParam(name = "daysOfWeek") List<Integer> daysOfWeek, @WebParam(name = "startTime") TimeOfDayInfo startTime, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a list of TimeSlots by TimeSlot Type, days of week, start time
     * and end time. Parameter daysOfWeek follows the Java standard: Sunday=1 to
     * Saturday=7
     *
     * @param timeSlotTypeKey identifier for the given slot type
     * @param daysOfWeek      days of the week of interest
     * @param startTime       start time of interest
     * @param endTime        end time of interest
     * @param contextInfo     Context information containing the principalId and
     *                        locale information about the caller of service
     *                        operation
     * @return a list of TimeSlots matching timeSlotTypeKey, daysOfWeek,
     *         startTime and endTime; empty list if none found
     * @throws InvalidParameterException invalid daysOfWeek, startTime, end time
     *                                   or contextInfo
     * @throws MissingParameterException timeSlotTypeKey, daysOfWeek, startTime,
     *                                   endTime or contextInfo is missing or
     *                                   null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<TimeSlotInfo> getTimeSlotsByDaysAndStartTimeAndEndTime(@WebParam(name = "timeSlotTypeKey") String timeSlotTypeKey, @WebParam(name = "daysOfWeek") List<Integer> daysOfWeek, @WebParam(name = "startTime") TimeOfDayInfo startTime, @WebParam(name = "endTime") TimeOfDayInfo endTime, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Searches for TimeSlots based on the criteria and returns a list of
     * TimeSlot identifiers which match the search criteria.
     *
     * @param criteria    the search criteria
     * @param contextInfo Context information containing the principalId and
     *                    locale information about the caller of service
     *                    operation
     * @return list of TimeSlot Ids
     * @throws InvalidParameterException invalid criteria or contextInfo
     * @throws MissingParameterException missing criteria or contextInfo
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<String> searchForTimeSlotIds(@WebParam(name = "criteria") QueryByCriteria criteria, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Searches for TimeSlots based on the criteria and returns a list of
     * TimeSlots which match the search criteria.
     *
     * @param criteria    the search criteria
     * @param contextInfo Context information containing the principalId and
     *                    locale information about the caller of service
     *                    operation
     * @return list of TimeSlots
     * @throws InvalidParameterException invalid criteria or contextInfo
     * @throws MissingParameterException missing criteria or contextInfo
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<TimeSlotInfo> searchForTimeSlots(@WebParam(name = "criteria") QueryByCriteria criteria, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Validates an TimeSlot. Depending on the value of validationType, this
     * validation could be limited to tests on just the current TimeSlot and its
     * directly contained sub-objects or expanded to perform all tests related
     * to this TimeSlot. If an identifier is present for the TimeSlot (and/or
     * one of its contained sub-objects) and a record is found for that
     * identifier, the validation checks if the TimeSlot can be updated to the
     * new values. If an identifier is not present or a record does not exist,
     * the validation checks if the object with the given data can be created.
     *
     * @param validationTypeKey the identifier for the validation Type
     * @param timeSlotTypeKey   the identifier for the timeSlot Type
     * @param timeSlotInfo      detailed information about the timeSlot
     * @param contextInfo       Context information containing the principalId
     *                          and locale information about the caller of
     *                          service operation
     * @return a list of validation results or an empty list if validation
     *         succeeded
     * @throws DoesNotExistException     validationTypeKey, timeSlotId, not
     *                                   found
     * @throws InvalidParameterException invalid timeSlotInfo or contextInfo
     * @throws MissingParameterException validationTypeKey, timeSlotId or
     *                                   contextInfo is missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<ValidationResultInfo> validateTimeSlot(@WebParam(name = "validationTypeKey") String validationTypeKey, @WebParam(name = "timeSlotTypeKey") String timeSlotTypeKey, @WebParam(name = "timeSlotInfo") TimeSlotInfo timeSlotInfo, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Creates a TimeSlot
     *
     * @param timeSlotTypeKey the identifier for the timeSlot Type
     * @param timeSlotInfo    detailed information about the timeSlot
     * @param contextInfo     Context information containing the principalId and
     *                        locale information about the caller of service
     *                        operation
     * @return detailed information about the timeSlot
     * @throws DataValidationErrorException supplied data is invalid
     * @throws DoesNotExistException        timeSlotId does not exist
     * @throws InvalidParameterException    invalid timeSlotInfo or contextInfo
     * @throws MissingParameterException    timeSlotId or contextInfo is missing
     *                                      or null
     * @throws OperationFailedException     unable to complete request
     * @throws PermissionDeniedException    an authorization failure occurred
     * @throws ReadOnlyException            an attempt at supplying information
     *                                      designated as read only
     */
    public TimeSlotInfo createTimeSlot(@WebParam(name = "timeSlotTypeKey") String timeSlotTypeKey, @WebParam(name = "timeSlotInfo") TimeSlotInfo timeSlotInfo, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DataValidationErrorException, DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException, ReadOnlyException;

    /**
     * Updates a timeSlot.
     *
     * @param timeSlotId   identifier of the timeSlot  to be updated
     * @param timeSlotInfo information about the object timeSlotInfo to be
     *                     updated
     * @param contextInfo  context information containing the principalId and
     *                     locale information about the caller of service
     *                     operation
     * @return updated timeSlot information
     * @throws DataValidationErrorException one or more values invalid for this
     *                                      operation
     * @throws DoesNotExistException        timeSlotId not found
     * @throws InvalidParameterException    invalid timeSlotInfo or contextInfo
     * @throws MissingParameterException    timeSlotId, timeSlotInfo or
     *                                      contextInfo is missing or null
     * @throws OperationFailedException     unable to complete request
     * @throws PermissionDeniedException    an authorization failure occurred
     * @throws ReadOnlyException            an attempt at supplying information
     *                                      designated as read-only
     * @throws VersionMismatchException     optimistic locking failure or the
     *                                      action was attempted on an out of
     *                                      date version
     */
    public TimeSlotInfo updateTimeSlot(@WebParam(name = "timeSlotId") String timeSlotId, @WebParam(name = "timeSlotInfo") TimeSlotInfo timeSlotInfo, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DataValidationErrorException, DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException, ReadOnlyException, VersionMismatchException;

    /**
     * Removes scheduleResp relationship between a person and a slot.
     *
     * @param timeSlotId  TimeSlot  identifier
     * @param contextInfo context information containing the principalId and
     *                    locale information about the caller of service
     *                    operation
     * @return status of the operation (success, failed)
     * @throws DoesNotExistException     timeSlotId not found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException timeSlotId or contextInfo is missing or
     *                                   null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public StatusInfo deleteTimeSlot(@WebParam(name = "timeSlotId") String timeSlotId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a ScheduleResp
     *
     * @param scheduleRespId a unique Id of a ScheduleResp
     * @param contextInfo        Context information containing the principalId
     *                           and locale information about the caller of
     *                           service operation
     * @return the ScheduleResp
     * @throws DoesNotExistException     scheduleRespId not found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException scheduleRespId or contextInfo is
     *                                   missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public ScheduleRespInfo getScheduleResp(@WebParam(name = "scheduleRespId") String scheduleRespId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a list of ScheduleResps corresponding to the given list of
     * ScheduleResp Ids Ids.
     *
     * @param scheduleRespIds list of ScheduleResps to be retrieved
     * @param contextInfo         Context information containing the principalId
     *                            and locale information about the caller of
     *                            service operation
     * @return a list of ScheduleResps
     * @throws DoesNotExistException     a scheduleRespId in list not found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException missing scheduleRespId or
     *                                   contextInfo is missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<ScheduleRespInfo> getScheduleRespsByIds(@WebParam(name = "scheduleRespIds") List<String> scheduleRespIds, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a list of ScheduleResp Ids by ScheduleResp Type.
     *
     * @param scheduleRespTypeKey an identifier for a ScheduleResp Type
     * @param contextInfo             Context information containing the
     *                                principalId and locale information about
     *                                the caller of service operation
     * @return a list of ScheduleResp identifiers matching
     *         scheduleRespTypeKey or an empty list if none found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException scheduleRespTypeKey or contextInfo
     *                                   is missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<String> getScheduleRespIdsByType(@WebParam(name = "scheduleRespTypeKey") String scheduleRespTypeKey, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a list of ScheduleResps corresponding to the given list of
     * Schedule Ids.
     *
     * @param scheduleRequestIds list of ScheduleResps to be retrieved
     * @param contextInfo        Context information containing the principalId
     *                           and locale information about the caller of
     *                           service operation
     * @return a list of ScheduleResps
     * @throws DoesNotExistException     a scheduleRequestId in list not found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException scheduleRequestIds or contextInfo is
     *                                   missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<ScheduleRespInfo> getScheduleRespsByScheduleRequestIds(@WebParam(name = "scheduleRequestIds") List<String> scheduleRequestIds, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a list of ScheduleResps corresponding to the given list of
     * ScheduleResp Ids.
     *
     * @param scheduleBatchRespId ScheduleBatchResp identifier
     * @param contextInfo             Context information containing the
     *                                principalId and locale information about
     *                                the caller of service operation
     * @return a list of ScheduleResps associated with a
     *         ScheduleBatchResp identifier
     * @throws DoesNotExistException     scheduleBatchRespId not found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException scheduleBatchRespId or contextInfo
     *                                   is missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<ScheduleRespInfo> getScheduleRespsByScheduleBatchResp(@WebParam(name = "scheduleBatchRespId") String scheduleBatchRespId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Calls R25 in our implementation ... the scheduled results may not be
     * available when this method returns. The method for scheduling
     * ActivityOfferings may be in the CourseOffering service.
     *
     * @param scheduleBatchId ScheduleRequest  identifier
     * @param contextInfo     context information containing the principalId and
     *                        locale information about the caller of service
     *                        operation
     * @return ScheduleResp after submitting the Batch
     * @throws DoesNotExistException     scheduleRequestId not found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException scheduleRequestId or contextInfo is
     *                                   missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public ScheduleRespInfo submitScheduleBatch(@WebParam(name = "scheduleBatchId") String scheduleBatchId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Saves the scheduleIds to the referenced objects
     *
     * @param scheduleBatchRespId ScheduleBatchResp identifier
     * @param contextInfo             context information containing the
     *                                principalId and locale information about
     *                                the caller of service operation
     * @return status of the operation (success, failed)
     * @throws DoesNotExistException     scheduleBatchRespId not found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException scheduleBatchRespId or contextInfo
     *                                   is missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public StatusInfo commitSchedules(@WebParam(name = "scheduleBatchRespId") String scheduleBatchRespId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves valid days of the week for the given slot type. Parameter
     * daysOfWeek follows the Java standard: Sunday=1 to Saturday=7
     *
     * @param timeSlotTypeKey identifier for the given slot type
     * @param contextInfo     Context information containing the principalId and
     *                        locale information about the caller of service
     *                        operation
     * @return Days of the week for the slot type; empty list if none found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException timeSlotTypeKey or contextInfo is
     *                                   missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<Integer> getValidDaysOfWeekByTimeSlotType(@WebParam(name = "timeSlotTypeKey") String timeSlotTypeKey, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;
}

