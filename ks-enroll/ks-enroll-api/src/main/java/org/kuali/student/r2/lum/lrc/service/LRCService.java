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

package org.kuali.student.r2.lum.lrc.service;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.kuali.student.common.dto.StatusInfo;
import org.kuali.student.common.exceptions.AlreadyExistsException;
import org.kuali.student.common.exceptions.DataValidationErrorException;
import org.kuali.student.common.exceptions.DoesNotExistException;
import org.kuali.student.common.exceptions.InvalidParameterException;
import org.kuali.student.common.exceptions.MissingParameterException;
import org.kuali.student.common.exceptions.OperationFailedException;
import org.kuali.student.common.exceptions.PermissionDeniedException;
import org.kuali.student.common.exceptions.VersionMismatchException;

import org.kuali.student.r2.common.datadictionary.service.DataDictionaryService;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.service.StateService;
import org.kuali.student.r2.common.service.TypeService;
import org.kuali.student.r2.lum.lrc.dto.ResultValueGroupInfo;
import org.kuali.student.r2.lum.lrc.dto.ResultValueInfo;
import org.kuali.student.r2.lum.lrc.dto.ResultValueRangeInfo;

/**
 * The Learning Result Catalog Service is a Class I service which gives a set of
 * operations to manage a learning result. A learning result can be of various
 * types e.g grades, credits etc. This service has basic CRUD operations to
 * touch various concepts that exist to model learning results e.g Result Value,
 * Result Value Group, and Result Value Range.
 * 
 * @Author sambit
 * @Since Tue May 10 14:09:46 PDT 2011
 * @See <a href=
 *      "https://test.kuali.org/confluence/display/KULSTU/Learning+Result+Catalog+Service"
 *      >LrcService</>
 * 
 */
@WebService(name = "LrcService", targetNamespace = "http://student.kuali.org/wsdl/lrc")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface LRCService extends DataDictionaryService, TypeService,
		StateService {
	/**
	 * Retrieves information on all result types.
	 * 
	 * @return list of result type information
	 * @throws OperationFailedException
	 *             unable to complete request
	 */
	public List<String> getAllResultValueGroupTypes(
			@WebParam(name = "context") ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException;;

	/**
	 * Retrieves information on a specific result type.
	 * 
	 * @param resultValueGroupTypeKey
	 *            identifier for the results type
	 * @return information about a results type
	 * @throws DoesNotExistException
	 *             resultValueGroupTypeKey not found
	 * @throws InvalidParameterException
	 *             invalid resultValueGroupTypeKey
	 * @throws MissingParameterException
	 *             missing resultValueGroupTypeKey
	 * @throws OperationFailedException
	 *             unable to complete request
	 */
	public String getResultValueGroupType(
			@WebParam(name = "resultTypeKey") String resultValueGroupTypeKey,
			@WebParam(name = "context") ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException;;

	/**
	 * Retrieves existing result group by an identifier.
	 * 
	 * @param resultValueGroupKey
	 *            identifiers for resultValueGroup to be retrieved
	 * @return details of the results for these ids
	 * @throws DoesNotExistException
	 *             resultValueGroupKey not found
	 * @throws InvalidParameterException
	 *             invalid resultValueGroupKey
	 * @throws MissingParameterException
	 *             invalid resultValueGroupKey
	 * @throws OperationFailedException
	 *             unable to complete request
	 */
	public ResultValueGroupInfo getResultValueGroup(
			@WebParam(name = "resultValueGroupKey") String resultValueGroupKey,
			@WebParam(name = "context") ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException;;

	/**
	 * Retrieves a list of existing results by a list of identifiers.
	 * 
	 * @param resultValueGroupKeyList
	 *            identifiers for results to be retrieved
	 * @return details of the results for these ids
	 * @throws DoesNotExistException
	 *             credentialKey not found
	 * @throws InvalidParameterException
	 *             invalid credentialKeyList
	 * @throws MissingParameterException
	 *             invalid credentialKeyList
	 * @throws OperationFailedException
	 *             unable to complete request
	 */
	public List<ResultValueGroupInfo> getResultValueGroupList(
			@WebParam(name = "resultValueGroupKeyList") List<String> resultValueGroupKeyList,
			@WebParam(name = "context") ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException;;

	/**
	 * Retrieves a list of existing result value groups that a result value is
	 * tied to.
	 * 
	 * @param resultValueKey
	 *            identifier for result value to be retrieved
	 * @return details of the results for these ids
	 * @throws DoesNotExistException
	 *             credentialKey not found
	 * @throws InvalidParameterException
	 *             invalid credentialKeyList
	 * @throws MissingParameterException
	 *             invalid credentialKeyList
	 * @throws OperationFailedException
	 *             unable to complete request
	 */
	public List<ResultValueGroupInfo> getResultValueGroupByResultValue(
			@WebParam(name = "resultValueKey") String resultValueKey,
			@WebParam(name = "context") ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException;

	/**
	 * Retrieves a list of result values identifiers for a specified result
	 * type.
	 * 
	 * @param resultTypeKey
	 *            identifier for the result type
	 * @return list of result identifiers
	 * @throws DoesNotExistException
	 *             resultTypeKey not found
	 * @throws InvalidParameterException
	 *             invalid resultTypeKey
	 * @throws MissingParameterException
	 *             missing resultTypeKey
	 * @throws OperationFailedException
	 *             unable to complete request
	 */
	public List<String> getResultValueKeysByResultType(
			@WebParam(name = "resultTypeKey") String resultTypeKey,
			@WebParam(name = "context") ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException;

	/**
	 * Retrieves information on a specific result group based on key
	 * 
	 * @param resultValueGroupId
	 *            result component identifier
	 * @return information about a result component
	 * @throws DoesNotExistException
	 *             resultComponentId not found
	 * @throws InvalidParameterException
	 *             invalid resultComponentId
	 * @throws MissingParameterException
	 *             missing resultComponentId
	 * @throws OperationFailedException
	 *             unable to complete request
	 */

	public ResultValueGroupInfo getResultValueGroupByKey(
			@WebParam(name = "resultGroupKey") String resultGroupKey,
			@WebParam(name = "context") ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException;

	/**
	 * Retrieves information on a specific result group based on identifier
	 * 
	 * @param resultValueGroupId
	 *            result component identifier
	 * @return information about a result component
	 * @throws DoesNotExistException
	 *             resultComponentId not found
	 * @throws InvalidParameterException
	 *             invalid resultComponentId
	 * @throws MissingParameterException
	 *             missing resultComponentId
	 * @throws OperationFailedException
	 *             unable to complete request
	 */
	public ResultValueGroupInfo getResultValueGroupById(
			@WebParam(name = "resultGroupId") String resultGroupId,
			@WebParam(name = "context") ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException;

	/**
	 * Retrieves a list of result group identifiers for a specified result
	 * component type.
	 * 
	 * @param resultValueGroupTypeKey
	 *            identifier for the result group type
	 * @return list of result group identifiers
	 * @throws DoesNotExistException
	 *             resultgroupTypeKey not found
	 * @throws InvalidParameterException
	 *             invalid resultgroupTypeKey
	 * @throws MissingParameterException
	 *             missing resultComponentTypeKey
	 * @throws OperationFailedException
	 *             unable to complete request
	 */
	public List<String> getResultValueGroupIdsByResultGroupType(
			@WebParam(name = "resultGroupTypeKey") String resultValueGroupTypeKey,
			@WebParam(name = "context") ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException;

	/**
	 * Retrieves a list of result group identifiers for a specified result. The
	 * result group type is specified as well to provide an indication of the id
	 * space.
	 * 
	 * @param resultValueId
	 *            identifier for the result value
	 * @param resultComponentTypeKey
	 *            identifier for the result group type
	 * @return list of result group identifiers
	 * @throws DoesNotExistException
	 *             resultValueId, resultGroupTypeKey not found
	 * @throws InvalidParameterException
	 *             invalid resultValueId, resultGroupTypeKey
	 * @throws MissingParameterException
	 *             missing resultValueId, resultGroupTypeKey
	 * @throws OperationFailedException
	 *             unable to complete request
	 */
	public List<String> getResultValueGroupIdsByResultValue(
			@WebParam(name = "resultValueId") String resultValueId,
			@WebParam(name = "resultGroupTypeKey") String resultGroupTypeKey,
			@WebParam(name = "context") ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException;


	/**
	 * Creates a new result group.
	 * 
	 * @param resultValueGroupKey
	 *            identifier of the result group
	 * @param resultValueGroupInfo
	 *            information about the result value group being created
	 * @return create result group information
	 * @throws AlreadyExistsException
	 *             result group already exists
	 * @throws DataValidationErrorException
	 *             one or more values invalid for this operation
	 * @throws DoesNotExistException
	 *             resultGroupTypeKey not found
	 * @throws InvalidParameterException
	 *             invalid resultGroupTypeKey, resultGroupInfo
	 * @throws MissingParameterException
	 *             missing resultGroupTypeKey, resultGroupInfo
	 * @throws OperationFailedException
	 *             unable to complete request
	 * @throws PermissionDeniedException
	 *             authorization failure
	 */
	public ResultValueGroupInfo createResultValueGroup(
			@WebParam(name = "resultValueGroupKey") String resultValueGroupKey,
			@WebParam(name = "resultGroupInfo") ResultValueGroupInfo resultValueGroupInfo,
			@WebParam(name = "context") ContextInfo context)
			throws AlreadyExistsException, DataValidationErrorException,
			DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException;

	/**
	 * Updates an existing result component.
	 * 
	 * @param resultGroupId
	 *            identifier of the result Group to update
	 * @param resultGroupInfo
	 *            updated information about the result group
	 * @return updated result group information
	 * @throws DataValidationErrorException
	 *             one or more values invalid for this operation
	 * @throws DoesNotExistException
	 *             resultGroupId not found
	 * @throws InvalidParameterException
	 *             invalid resultGroupId, resultGroupInfo
	 * @throws MissingParameterException
	 *             missing resultGroupId, resultGroupInfo
	 * @throws OperationFailedException
	 *             unable to complete request
	 * @throws PermissionDeniedException
	 *             authorization failure
	 * @throws VersionMismatchException
	 *             action was attempted on an out of date version.
	 */
	public ResultValueGroupInfo updateResultValueGroup(
			@WebParam(name = "resultValueGroupId") String resultValueGroupId,
			@WebParam(name = "resultValueGroupInfo") ResultValueGroupInfo resultValueGroupInfo,
			@WebParam(name = "context") ContextInfo context)
			throws DataValidationErrorException, DoesNotExistException,
			InvalidParameterException, MissingParameterException,
			OperationFailedException, PermissionDeniedException,
			VersionMismatchException;

	
	
	/**
	 * Deletes an existing result group.
	 * 
	 * @param resultGroupId
	 *            identifier of the result group to update
	 * @return status of the operation
	 * @throws DoesNotExistException
	 *             resultComponentId not found
	 * @throws InvalidParameterException
	 *             invalid resultComponentId
	 * @throws MissingParameterException
	 *             missing resultComponentId
	 * @throws OperationFailedException
	 *             unable to complete request
	 * @throws PermissionDeniedException
	 *             authorization failure
	 */
	public StatusInfo deleteResultValueGroup(
			@WebParam(name = "resultValueGroupId") String resultValueGroupId,
			@WebParam(name = "context") ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException;

	
	/**
	 * Gets all the result value ranges for a result value group.
	 * 
	 * 
	 * @return ResultValueGroupTypeInfo which are tied to a particular
	 *         ResultValuegroup
	 * @throws DoesNotExistException
	 * @throws InvalidParameterException
	 * @throws MissingParameterException
	 * @throws OperationFailedException
	 * @throws PermissionDeniedException
	 */

	public List<String> getResultValueRangesForResultValueGroupById(
			@WebParam(name = "resultValueGroupId") String resultValueGroupId,
			@WebParam(name = "context") ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException;

	/**
	 * Gets all the result value ranges for a result value group.
	 * 
	 * 
	 * @return ResultValueGroupTypeInfo which are tied to a particular
	 *         ResultValuegroup
	 * @throws DoesNotExistException
	 * @throws InvalidParameterException
	 * @throws MissingParameterException
	 * @throws OperationFailedException
	 * @throws PermissionDeniedException
	 */

	public List<String> getResultValueRangesForResultValueGroup(
			@WebParam(name = "resultValueGroupKey") String resultValueGroupKey,
			@WebParam(name = "context") ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException;

	/**
	 * Gets all result value ranges.
	 * 
	 * 
	 * @return ResultValueGroupTypeInfo which are tied to a particular
	 *         ResultValuegroup
	 * @throws DoesNotExistException
	 * @throws InvalidParameterException
	 * @throws MissingParameterException
	 * @throws OperationFailedException
	 * @throws PermissionDeniedException
	 */

	public List<String> getAllResultValueRanges(
			@WebParam(name = "context") ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException;
	/**
	 * Gets all result value ranges.
	 * 
	 * 
	 * @return ResultValueGroupTypeInfo which are tied to a particular
	 *         ResultValuegroup
	 * @throws DoesNotExistException
	 * @throws InvalidParameterException
	 * @throws MissingParameterException
	 * @throws OperationFailedException
	 * @throws PermissionDeniedException
	 */

	public List<ResultValueRangeInfo> getAllResultValueRangesByRangeType(
			@WebParam(name = "resultValueRangeType") String resultValueRangeTypeKey,
			@WebParam(name = "context") ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException;
	
	/**
	 * 
	 * @param resultValueKey
	 * @param resultValueInfo
	 * @return
	 * @throws AlreadyExistsException
	 * @throws DataValidationErrorException
	 * @throws DoesNotExistException
	 * @throws InvalidParameterException
	 * @throws MissingParameterException
	 * @throws OperationFailedException
	 * @throws PermissionDeniedException
	 */
	public ResultValueInfo createResultValueRange(
			@WebParam(name = "resultValueRangeKey") String resultValueRangeKey,
			@WebParam(name = "resultValueRangeInfo") ResultValueRangeInfo resultValueRangeInfo,
			@WebParam(name = "context") ContextInfo context)
			throws AlreadyExistsException, DataValidationErrorException,
			DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException;
	
	
	/**
	 * Updates an existing result range.
	 * 
	 * @param resultGroupId
	 *            identifier of the result Group to update
	 * @param resultGroupInfo
	 *            updated information about the result group
	 * @return updated result group information
	 * @throws DataValidationErrorException
	 *             one or more values invalid for this operation
	 * @throws DoesNotExistException
	 *             resultGroupId not found
	 * @throws InvalidParameterException
	 *             invalid resultGroupId, resultGroupInfo
	 * @throws MissingParameterException
	 *             missing resultGroupId, resultGroupInfo
	 * @throws OperationFailedException
	 *             unable to complete request
	 * @throws PermissionDeniedException
	 *             authorization failure
	 * @throws VersionMismatchException
	 *             action was attempted on an out of date version.
	 */
	public ResultValueGroupInfo updateResultValueRange(
			@WebParam(name = "resultValueRangeId") String resultValueRangeInfoId,
			@WebParam(name = "resultValueGroupInfo") ResultValueRangeInfo resultValueRangeInfo,
			@WebParam(name = "context") ContextInfo context)
			throws DataValidationErrorException, DoesNotExistException,
			InvalidParameterException, MissingParameterException,
			OperationFailedException, PermissionDeniedException,
			VersionMismatchException;

	
	/**
	 * 
	 * @param resultValueRangeInfoId
	 * @return
	 * @throws DoesNotExistException
	 * @throws InvalidParameterException
	 * @throws MissingParameterException
	 * @throws OperationFailedException
	 * @throws PermissionDeniedException
	 */
	public StatusInfo deleteResultValueRange(
			@WebParam(name = "resultValueId") String resultValueRangeInfoId,
			@WebParam(name = "context") ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException;
	/**
	 * Retrieves a list of result value objects for a specified result value
	 * group id.
	 * 
	 * @param resultValueId
	 *            identifier for the result value
	 * @param resultComponentTypeKey
	 *            identifier for the result group type
	 * @return list of result group identifiers
	 * @throws DoesNotExistException
	 *             resultValueId, resultGroupTypeKey not found
	 * @throws InvalidParameterException
	 *             invalid resultValueId, resultGroupTypeKey
	 * @throws MissingParameterException
	 *             missing resultValueId, resultGroupTypeKey
	 * @throws OperationFailedException
	 *             unable to complete request
	 */
	public List<ResultValueInfo> getResultValuesForResultValueGroupById(
			@WebParam(name = "resultValueGroupId") String resultValueGroupId,
			@WebParam(name = "context") ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException;

	/**
	 * Retrieves a list of result value objects for a specified result value
	 * group key.
	 * 
	 * @param resultValueId
	 *            identifier for the result value
	 * @param resultComponentTypeKey
	 *            identifier for the result group type
	 * @return list of result group identifiers
	 * @throws DoesNotExistException
	 *             resultValueId, resultGroupTypeKey not found
	 * @throws InvalidParameterException
	 *             invalid resultValueId, resultGroupTypeKey
	 * @throws MissingParameterException
	 *             missing resultValueId, resultGroupTypeKey
	 * @throws OperationFailedException
	 *             unable to complete request
	 */
	public List<ResultValueInfo> getResultValuesForResultValueGroup(
			@WebParam(name = "resultValueGroupKey") String resultValueGroupKey,
			@WebParam(name = "context") ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException;

	/**
	 * 
	 * @param resultValueKey
	 * @param resultValueInfo
	 * @return
	 * @throws AlreadyExistsException
	 * @throws DataValidationErrorException
	 * @throws DoesNotExistException
	 * @throws InvalidParameterException
	 * @throws MissingParameterException
	 * @throws OperationFailedException
	 * @throws PermissionDeniedException
	 */
	public ResultValueInfo createResultValue(
			@WebParam(name = "resultValueGroupKey") String resultValueKey,
			@WebParam(name = "resultValueInfo") ResultValueInfo resultValueInfo,
			@WebParam(name = "context") ContextInfo context)
			throws AlreadyExistsException, DataValidationErrorException,
			DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException;

	/**
	 * 
	 * @param resultValueId
	 * @param resultValueInfo
	 * @return
	 * @throws DataValidationErrorException
	 * @throws DoesNotExistException
	 * @throws InvalidParameterException
	 * @throws MissingParameterException
	 * @throws OperationFailedException
	 * @throws PermissionDeniedException
	 * @throws VersionMismatchException
	 */
	public ResultValueInfo updateResultValue(
			@WebParam(name = "resultValueId") String resultValueId,
			@WebParam(name = "resultValueInfo") ResultValueInfo resultValueInfo,
			@WebParam(name = "context") ContextInfo context)
			throws DataValidationErrorException, DoesNotExistException,
			InvalidParameterException, MissingParameterException,
			OperationFailedException, PermissionDeniedException,
			VersionMismatchException;

	/**
	 * 
	 * @param resultValueId
	 * @return
	 * @throws DoesNotExistException
	 * @throws InvalidParameterException
	 * @throws MissingParameterException
	 * @throws OperationFailedException
	 * @throws PermissionDeniedException
	 */
	public StatusInfo deleteResultValue(
			@WebParam(name = "resultValueId") String resultValueId,
			@WebParam(name = "context") ContextInfo context)
			throws DoesNotExistException, InvalidParameterException,
			MissingParameterException, OperationFailedException,
			PermissionDeniedException;

}
