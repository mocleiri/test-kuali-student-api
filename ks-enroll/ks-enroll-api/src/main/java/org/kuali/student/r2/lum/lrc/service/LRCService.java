/**
 * Copyright 2010 The Kuali Foundation 
 *
 * Licensed under the Educational Community License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.osedu.org/licenses/ECL-2.0 Unless required by applicable
 * law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License
 * for the specific language governing permissions and limitations
 * under the License.
 */
package org.kuali.student.r2.lum.lrc.service;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.kuali.student.r2.common.dto.StatusInfo;
import org.kuali.student.r2.common.exceptions.PermissionDeniedException;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.dto.ValidationResultInfo;
import org.kuali.student.r2.common.exceptions.AlreadyExistsException;
import org.kuali.student.r2.common.exceptions.DataValidationErrorException;
import org.kuali.student.r2.common.exceptions.DoesNotExistException;
import org.kuali.student.r2.common.exceptions.InvalidParameterException;
import org.kuali.student.r2.common.exceptions.MissingParameterException;
import org.kuali.student.r2.common.exceptions.OperationFailedException;
import org.kuali.student.r2.common.exceptions.VersionMismatchException;
import org.kuali.student.r2.common.util.constants.LrcServiceConstants;
import org.kuali.student.r2.lum.lrc.dto.ResultValuesGroupInfo;
import org.kuali.student.r2.lum.lrc.dto.ResultScaleInfo;
import org.kuali.student.r2.lum.lrc.dto.ResultValueInfo;

/**
 * The Learning Result Catalog Service is a Class I service which
 * gives a set of operations to manage a learning result. A learning
 * result can be of various types e.g grades, credits etc. This
 * service has basic CRUD operations to touch various concepts that
 * exist to model learning results e.g Result Value, Result Value
 * Group, and Result Value Range.
 * 
 * @Author sambit
 * @Since Tue May 10 14:09:46 PDT 2011
 */
@WebService(name = "LrcService", targetNamespace = LrcServiceConstants.NAMESPACE)
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface LRCService {

    /**
     * Retrieves existing result values group by an identifier.
     * 
     * @param resultValuesGroupKey identifiers for resultValuesGroup to be retrieved
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation     
     * @return details of the results for these Ids
     * @throws DoesNotExistException  resultValuesGroupKey not found
     * @throws InvalidParameterException invalid resultValuesGroupKey
     * @throws MissingParameterException invalid resultValuesGroupKey
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public ResultValuesGroupInfo getResultValuesGroup(@WebParam(name = "resultValuesGroupKey") String resultValuesGroupKey,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException;

    /**
     * Retrieves result values groups by a list of identifiers.
     * 
     * @param resultValuesGroupKeys  identifiers for result values group
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation     
     * @return result values group list
     * @throws DoesNotExistException resultValuesGroup not found
     * @throws InvalidParameterException invalid resultValuesGroupKeys
     * @throws MissingParameterException invalid resultValuesGroupKeys
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<ResultValuesGroupInfo> getResultValuesGroupsByKeys(@WebParam(name = "resultValuesGroupKeys") List<String> resultValuesGroupKeys,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException;

    /**
     * Retrieves a list of existing result values groups that a result value is tied to.
     * 
     * @param resultValueKey identifier for result value
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return details of the results for these keys
     * @throws DoesNotExistException resultValue not found
     * @throws InvalidParameterException invalid resultValueKey
     * @throws MissingParameterException invalid resultValueKey
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<ResultValuesGroupInfo> getResultValuesGroupsByResultValue(@WebParam(name = "resultValueKey") String resultValueKey,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException;

    /**
     * Retrieves a list of result group identifiers for a specified
     * result values group type.
     * 
     * @param resultValueGroupTypeKey identifier for the result group type
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation     
     * @return list of result group identifiers
     * @throws DoesNotExistException resultValuesGroupTypeKey not found
     * @throws InvalidParameterException invalid resultValuesGroupTypeKey
     * @throws MissingParameterException missing resultValuesGroupTypeKey
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure     
     */
    public List<String> getResultValuesGroupKeysByType(@WebParam(name = "resultValuesGroupTypeKey") String resultValuesGroupTypeKey,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException;

    /**
     * Creates a new result Values Group.
     * 
     * @param gradeValuesGroupInfo information about the result values group 
     *        being created
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation     
     * @return create result values group information
     * @throws AlreadyExistsException result values group already exists
     * @throws DataValidationErrorException one or more values invalid for 
     *         this operation
     * @throws InvalidParameterException invalid resultValuesGroupInfo
     * @throws MissingParameterException missing resultValuesGroupInfo
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public ResultValuesGroupInfo createResultValuesGroup(@WebParam(name = "resultGroupInfo") ResultValuesGroupInfo gradeValuesGroupInfo,
            @WebParam(name = "context") ContextInfo context)
            throws AlreadyExistsException,
            DataValidationErrorException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException;

    /**
     * Updates an existing result values group.
     * 
     * @param resultValuesGroupKey identifier of the result values group to update
     * @param resultGroupInfo updated information about the result values group
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation     
     * @return updated result values group information
     * @throws DataValidationErrorException one or more values invalid for 
     *                                      this operation
     * @throws DoesNotExistException resultValuesGroupKey not found
     * @throws InvalidParameterExceptioninvalid resultValuesGroupKey or 
     *                                          resultValuesGroupInfo
     * @throws MissingParameterException missing resultValuesGroupKey or
     *                                   resultValuesGroupInfo
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     * @throws VersionMismatchException action was attempted on an out of 
     *                                  date version
     */
    public ResultValuesGroupInfo updateResultValuesGroup(@WebParam(name = "resultValuesGroupKey") String resultValuesGroupKey,
            @WebParam(name = "resultValuesGroupInfo") ResultValuesGroupInfo gradeValuesGroupInfo,
            @WebParam(name = "context") ContextInfo context)
            throws DataValidationErrorException,
            DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException,
            VersionMismatchException;

    /**
     * Deletes an existing result values group.
     * 
     * @param resultValuesGroupKey identifier of the result values group to update
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation     
     * @return status of the operation
     * @throws DoesNotExistException resultValuesGroupKey not found
     * @throws InvalidParameterException invalid resultValuesGroupKey
     * @throws MissingParameterException missing resultValuesGroupKey
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public StatusInfo deleteResultValuesGroup(@WebParam(name = "resultValuesGroupKey") String resultValuesGroupKey,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException;

    /**
     * Validates a result values group. Depending on the value of
     * validationType, this validation could be limited to tests on
     * just the current object and its directly contained sub-objects
     * or expanded to perform all tests related to this object. 
     * 
     * @param validationType Identifier of the extent of validation
     * @param gradeValuesGroupInfo Result values group to be validated
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return
     * @throws DoesNotExistException resultValuesGroupInfo does not exist
     * @throws InvalidParameterException validationType or 
     *                                   resultValuesGroupInfo does not exist
     * @throws MissingParameterException missing validationType, resultValuesGroupInfo
     * @throws OperationFailedException unable to complete request
     */
    public List<ValidationResultInfo> validateResultValuesGroup(@WebParam(name = "validationType") String validationType,
            @WebParam(name = "resultGroupInfo") ResultValuesGroupInfo gradeValuesGroupInfo,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException;

    /**
     * Get or create a new result values group holding the specified number of credits
     * 
     * The resulting RVG should have a type of FIXED
     * 
     * May also create the corresponding credit value.
     * 
     * @param creditValue the credit value to be found/created
     * @param scaleId the scale associated with this type of credit (regular or remedial or continuing ed, etc)
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation     
     * @return create result values group information
     * @throws AlreadyExistsException result values group already exists
     * @throws DataValidationErrorException one or more values invalid for 
     *         this operation
     * @throws InvalidParameterException invalid resultValuesGroupInfo
     * @throws MissingParameterException missing resultValuesGroupInfo
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     * @impl the parameters hold string representations of floating points to avoid rounding issues
     */
    public ResultValuesGroupInfo getCreateFixedCreditResultValuesGroup(@WebParam(name = "creditValue") String creditValue,
            @WebParam(name = "scaleId") String scaleId,
            @WebParam(name = "context") ContextInfo context)
            throws InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException;

    /**
     * Get or create a new result values group holding the specified number of credits
     * 
     * The resulting RVG will have a type of RANGE
     * 
     * @param creditValueMin the minimum credit value of the range to be found/created
     * @param creditValueMax the maximum credit value to be found/created
     * @param creditValueIncrement the credit value increment of the range to be found/created
     * @param scaleId the scale associated with this type of credit (regular or remedial or continuing ed, etc)
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation     
     * @return create result values group information
     * @throws AlreadyExistsException result values group already exists
     * @throws DataValidationErrorException one or more values invalid for 
     *         this operation
     * @throws InvalidParameterException invalid resultValuesGroupInfo
     * @throws MissingParameterException missing resultValuesGroupInfo
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     * @impl the parameters hold string representations of floating points to avoid rounding issues
     */
    public ResultValuesGroupInfo getCreateRangeCreditResultValuesGroup(@WebParam(name = "creditValueMin") String creditValueMin,
            @WebParam(name = "creditValueMax") String creditValueMax,
            @WebParam(name = "creditValueIncrement") String creditValueIncrement,
            @WebParam(name = "scaleId") String scaleId,
            @WebParam(name = "context") ContextInfo context)
            throws InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException;

    /**
     * Get or create a new result values group holding the specified number of credits
     * 
     * The resulting RVG should have the type of MULTIPLE
     * 
     * @param creditValues the minimum credit value of the range to be found/created
     * @param scaleId the scale associated with this type of credit (regular or remedial or continuing ed, etc)
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation     
     * @return create result values group information
     * @throws AlreadyExistsException result values group already exists
     * @throws DataValidationErrorException one or more values invalid for 
     *         this operation
     * @throws InvalidParameterException invalid resultValuesGroupInfo
     * @throws MissingParameterException missing resultValuesGroupInfo
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     * @impl the parameters hold string representations of floating points to avoid rounding issues
     */
    public ResultValuesGroupInfo getCreateMultipleCreditResultValuesGroup(@WebParam(name = "creditValues") List<String> creditValues,
            @WebParam(name = "scaleId") String scaleId,
            @WebParam(name = "context") ContextInfo context)
            throws InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException;

    /**
     * Retrieves result value by its id.
     * 
     * @param resultValueKey identifier for the result
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation      
     * @return details about a result value
     * @throws DoesNotExistException the resultValueKey is not found
     * @throws InvalidParameterException invalid resultValueKey
     * @throws MissingParameterException missing parameter
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure 
     */
    public ResultValueInfo getResultValue(@WebParam(name = "resultValueKey") String resultValueKey,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException;

    /**
     * Retrieves a list of result value objects for a list of identifiers. 
     * 
     * @param resultValueKeys identifier for the result
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation      
     * @return list of result group identifiers
     * @throws DoesNotExistException a resultValueKey from the list is not found
     * @throws InvalidParameterException invalid resultValueKeys
     * @throws MissingParameterException missing resultValueKeys
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure 
     */
    public List<ResultValueInfo> getResultValuesByIds(@WebParam(name = "resultValueKeys") List<String> resultValueKeys,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException;

    /**
     * Retrieves a list of result value objects for a specified result
     * values group. It is sorted by the scale inside the values group.
     * 
     * @param resultValuesGroupKey identifier for the result values group
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation     
     * @return list of result group identifiers
     * @throws DoesNotExistException resultValueKey not found
     * @throws InvalidParameterException invalid resultValuesGroupKey
     * @throws MissingParameterException missing resultValuesGroupKey
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure     
     */
    public List<ResultValueInfo> getResultValuesForResultValuesGroup(@WebParam(name = "resultValuesGroupKey") String resultValuesGroupKey,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException;

    /**
     * Create a new result value 
     * @param resultValueInfo
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation     
     * @return newly created resultValue
     * @throws AlreadyExistsException resultValue already exists
     * @throws DataValidationErrorException one or more values invalid for 
     *                                      this operation 
     * @throws InvalidParameterException invalid resultValueInfo
     * @throws MissingParameterException missing resultValueInfo
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public ResultValueInfo createResultValue(@WebParam(name = "resultValueInfo") ResultValueInfo resultValueInfo,
            @WebParam(name = "context") ContextInfo context)
            throws AlreadyExistsException,
            DataValidationErrorException,
            DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException;

    /**
     * Update a result value
     * @param resultValueKey resultValueKey to be updated
     * @param resultValueInfo update information for the result value
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation     
     * @return updated information about the result value
     * @throws DataValidationErrorException one or more values invalid for 
     *                                      this operation
     * @throws DoesNotExistException resultValueKey does not exist
     * @throws InvalidParameterException invalid resultValueKey, resultValueInfo
     * @throws MissingParameterException missing resultValueKey, resultValueInfo
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     * @throws VersionMismatchException action was attempted on an out of 
     *                                  date version
     */
    public ResultValueInfo updateResultValue(@WebParam(name = "resultValueKey") String resultValueKey,
            @WebParam(name = "resultValueInfo") ResultValueInfo resultValueInfo,
            @WebParam(name = "context") ContextInfo context)
            throws DataValidationErrorException,
            DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException,
            VersionMismatchException;

    /**
     * Delete a result value. This should not be allowed if any result values group is still referencing the result value.
     * @param resultValueKey result value to be deleted
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return status of the delete operation
     * @throws DoesNotExistException resultValueKey does not exist
     * @throws InvalidParameterException  invalid resultValueKey
     * @throws MissingParameterException missing resultValueKey
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public StatusInfo deleteResultValue(@WebParam(name = "resultValueKey") String resultValueKey,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException;

    /**
     * Validates a Result Value. Depending on the value of
     * validationType, this validation could be limited to tests on
     * just the current object and its directly contained subobjects
     * or expanded to perform all tests related to this object. If an
     * identifier is present for the academic calendar and a record
     * is found for that identifier, the validation checks if the
     * academic calendar can be shifted to the new values. If a
     * record cannot be found for the identifier, it is assumed that
     * the record does not exist and as such, the checks performed
     * will be much shallower, typically mimicking those performed by
     * setting the validationType to the current object. This is a
     * slightly different pattern from the standard validation as the
     * caller provides the identifier in the create statement instead
     * of the server assigning an identifier.
     *
     * @param validationType  Identifier of the extent of validation
     * @param resultValueInfo Result value to be validated
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return a ValidationResultInfo
     * @throws DoesNotExistException resultValueInfo does not exist
     * @throws InvalidParameterException validationType or resultValueInfo 
     *                                   does not exist
     * @throws MissingParameterException missing validationType or resultValueInfo
     * @throws OperationFailedException unable to complete request
     */
    public List<ValidationResultInfo> validateResultValue(@WebParam(name = "validationType") String validationType,
            @WebParam(name = "resultValueInfo") ResultValueInfo resultValueInfo,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException;

    /**
     * Get or create a new result value holding the specified numeric value within the range
     * 
     * The resulting result value should be attached to the specified group and must be within 
     * the range of the group
     * 
     * @param resultValue the result value within the specified group range to be found/created
     * @param resultValuesGroupKey the group associated with this grade 
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation     
     * @return create result values group information
     * @throws AlreadyExistsException result values group already exists
     * @throws InvalidParameterException invalid rvg id or rvg is not a range
     * @throws MissingParameterException missing resultValuesGroupInfo
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     * @impl the parameters hold string representations of floating points to avoid rounding issues
     */
    public ResultValuesGroupInfo getCreateResultValueWithinRange(@WebParam(name = "resultValue") String resultValue,
            @WebParam(name = "resultValuesGroupKey") String resultValuesGroupKey,
            @WebParam(name = "context") ContextInfo context)
            throws InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException;

    /**
     * Retrieves result scale by an identifier.
     * 
     * @param resultScaleId identifiers for result scale to be retrieved
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation     
     * @return details of the result scale for the id
     * @throws DoesNotExistException  resultValuesGroupKey not found
     * @throws InvalidParameterException invalid resultValuesGroupKey
     * @throws MissingParameterException invalid resultValuesGroupKey
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public ResultScaleInfo getResultScale(@WebParam(name = "resultScaleKey") String resultScaleKey,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException;

    /**
     * Retrieves result values by result scale key.
     * 
     * @param resultScaleKey key to the scale 
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return a list of result values for the scale
     * @throws DoesNotExistException resultScaleKey is not found
     * @throws InvalidParameterException invalid resultScaleKey
     * @throws MissingParameterException null resultScaleKey
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<ResultValueInfo> getResultValuesForScale(@WebParam(name = "resultScaleKey") String resultScaleKey,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException;

    /**
     * Retrieves result values by result scale key.
     * 
     * @param resultValuesGroupKeys list of result value groups for which to return values
     * @param context Context information containing the principalId
     *                and locale information about the caller of service
     *                operation
     * @return a list of unique result values for that set of groups
     * @throws DoesNotExistException resultScaleKey is not found
     * @throws InvalidParameterException invalid resultScaleKey
     * @throws MissingParameterException null resultScaleKey
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<ResultValueInfo> getResultValuesForResultValuesGroups(@WebParam(name = "resultValuesGroupKeys") List<String> resultValuesGroupKeys,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException;
}
