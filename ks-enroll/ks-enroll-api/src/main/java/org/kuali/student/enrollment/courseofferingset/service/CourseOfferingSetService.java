/**
 * Copyright 2010 The Kuali Foundation
 *
 * Licensed under the the Educational Community License, Version 1.0
 * (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.student.enrollment.courseofferingset.service;

import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.dto.StatusInfo;
import org.kuali.student.r2.common.dto.ValidationResultInfo;

import org.kuali.student.r2.common.exceptions.DataValidationErrorException;
import org.kuali.student.r2.common.exceptions.DoesNotExistException;
import org.kuali.student.r2.common.exceptions.InvalidParameterException;
import org.kuali.student.r2.common.exceptions.MissingParameterException;
import org.kuali.student.r2.common.exceptions.OperationFailedException;
import org.kuali.student.r2.common.exceptions.PermissionDeniedException;
import org.kuali.student.r2.common.exceptions.ReadOnlyException;
import org.kuali.student.r2.common.exceptions.VersionMismatchException;


import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;
import org.kuali.student.enrollment.courseofferingset.dto.SocInfo;
import org.kuali.student.enrollment.courseofferingset.dto.SocRolloverResultItemInfo;
import org.kuali.student.enrollment.courseofferingset.dto.SocRolloverResultsInfo;
import org.kuali.student.r2.common.util.constants.CourseOfferingSetServiceConstants;

/**
 * Course Offering Set Service allows the user to create Sets of 
 * Offered Courses or SOCs.  These sets are all dynamic.
 * 
 * By default there is one main Soc per term that includes all the courses 
 * in that term.
 * 
 * @version 1.0
 *
 * @author nwright
 */
@WebService(name = "CourseOfferingSetService", serviceName = "CourseOfferingSetService", portName = "CourseOfferingSetService", targetNamespace = CourseOfferingSetServiceConstants.NAMESPACE)
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface CourseOfferingSetService {

    /**
     * Retrieve information about a Soc
     *
     * @param socId Unique Id of the Soc
     * @param context          Context information containing the principalId and locale
     *                         information about the caller of service operation
     * @throws DoesNotExistException     socId not found
     * @throws InvalidParameterException invalid parameter
     * @throws MissingParameterException missing parameter
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public SocInfo getSoc(@WebParam(name = "socId") String socId,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a list of course offerings by id list.
     *
     * @param socIds List of unique Ids of Soc
     * @param context           Context information containing the principalId and locale
     *                          information about the caller of service operation
     * @throws DoesNotExistException     socId in the list not found
     * @throws InvalidParameterException invalid socIds
     * @throws MissingParameterException missing socIds
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<SocInfo> getSocsByIds(@WebParam(name = "socIds") List<String> socIds,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException;

    /**
     * Retrieve ids of the Soc for the term
     * 
     * This could return multiple Socs but should always return the default main 
     * SOC for that term.
     *
     * @param termId Unique Id of the term
     * @param context  Context information containing the principalId and locale
     *                 information about the caller of service operation
     * @throws DoesNotExistException     termId not found
     * @throws InvalidParameterException invalid parameter
     * @throws MissingParameterException missing parameter
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<String> getSocIdsByTerm(@WebParam(name = "termId") String termId,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException;

    /**
     * Retrieve Soc Ids for a given term and subject area.
     * 
     * A Soc will have an official and "other" subject areas, this
     * operation will the course offering ids with either official or other subject area
     * that match.
     * 
     *
     * THIS IS A PLACEHOLDER for DEPARTMENTAL SOCS
     *
     *
     * @param termId      Unique key of the term in which the course is being offered
     * @param subjectArea subject area
     * @param context     Context information containing the principalId and locale
     *                    information about the caller of service operation
     * @return List of Soc Ids
     * @throws DoesNotExistException     termId or subject area not found
     * @throws InvalidParameterException invalid parameter
     * @throws MissingParameterException missing parameter
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    @Deprecated
    public List<String> getSocIdsByTermAndSubjectArea(@WebParam(name = "termId") String termId,
            @WebParam(name = "subjectArea") String subjectArea,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException,
            PermissionDeniedException;

    /**
     * Retrieve Soc Ids for a given term and unit content owner
     *
     * THIS IS A PLACEHOLDER for DEPARTMENTAL SOCS
     *
     * @param termId      Unique key of the term in which the course is being offered
     * @param unitsContentOwnerId Org Id of the Units content owner
     * @param context     Context information containing the principalId and locale
     *                    information about the caller of service operation
     * @return List of Soc Ids
     * @throws DoesNotExistException     termId or unitsContentOwnerid not found
     * @throws InvalidParameterException invalid parameter
     * @throws MissingParameterException missing parameter
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    @Deprecated
    public List<String> getSocIdsByTermAndUnitsContentOwner(@WebParam(name = "termId") String termId,
            @WebParam(name = "unitsContentOwnerId") String unitsContentOwnerId,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException,
            PermissionDeniedException;

    /**
     * Retrieve Soc Ids by type
     *
     * @param typeKey      Unique key type of Soc
     * @param context     Context information containing the principalId and locale
     *                    information about the caller of service operation
     * @return List of Soc Ids
     * @throws DoesNotExistException     typeKey not found
     * @throws InvalidParameterException invalid parameter
     * @throws MissingParameterException missing parameter
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<String> getSocIdsByType(@WebParam(name = "typeKey") String typeKey,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException;

    /**
     * Creates a new Soc for a term
     * 
     * Fields in course offering will be initialized with data from the canonical.
     *
     * @param courseId     Canonical course Id of soc Id that the
     *                     ActivityOffering will belong to
     * @param termId       Unique key of the term in which the course is being offered
     *                     course offering
     * @param context      Context information containing the principalId and locale
     *                     information about the caller of service operation
     * @return newly created SocInfo
     * @throws DoesNotExistException        termId or socTypeKey not found
     * @throws DataValidationErrorException One or more values invalid for this operation
     * @throws InvalidParameterException    One or more parameters invalid
     * @throws MissingParameterException    One or more parameters missing
     * @throws OperationFailedException     unable to complete request
     * @throws PermissionDeniedException    authorization failure
     */
    public SocInfo createSoc(@WebParam(name = "termId") String termId,
            @WebParam(name = "socTypeKey") String socTypeKey,
            @WebParam(name = "socInfo") SocInfo socInfo,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, DataValidationErrorException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException, ReadOnlyException;

    /**
     * Updates an existing Soc.
     *
     * @param socId   Id of Soc to be updated
     * @param socInfo Details of updates to the Soc
     * @param context            Context information containing the principalId and locale
     *                           information about the caller of service operation
     * @return updated Soc
     * @throws DataValidationErrorException One or more values invalid for this operation
     * @throws DoesNotExistException      the Soc does not exist
     * @throws InvalidParameterException    One or more parameters invalid
     * @throws MissingParameterException    One or more parameters missing
     * @throws OperationFailedException     unable to complete request
     * @throws PermissionDeniedException    authorization failure
     * @throws VersionMismatchException     The action was attempted on an out of date version.
     */
    public SocInfo updateSoc(@WebParam(name = "socId") String socId,
            @WebParam(name = "socInfo") SocInfo socInfo,
            @WebParam(name = "context") ContextInfo context)
            throws DataValidationErrorException, DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException,
            ReadOnlyException, VersionMismatchException;

    /**
     * Deletes an existing Soc. Deleting a course offering should
     * also delete all the activity offerings and registrations groups within
     * it. Cross listed course offerings should also be deleted along with
     * passed in socId.
     *
     * @param socId the Id of the ActivityOffering to be deleted
     * @param context          Context information containing the principalId and locale
     *                         information about the caller of service operation
     * @return status of the operation (success, failed)
     * @throws DoesNotExistException     the SeatPoolDefinition does not exist
     * @throws InvalidParameterException One or more parameters invalid
     * @throws MissingParameterException One or more parameters missing
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public StatusInfo deleteSoc(@WebParam(name = "socId") String socId,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Validates a Soc. Depending on the value of validationType,
     * this validation could be limited to tests on just the current object and
     * its directly contained sub-objects or expanded to perform all tests
     * related to this object. If an identifier is present for the academic
     * calendar and a record is found for that identifier, the validation checks
     * if the academic calendar can be shifted to the new values. If a record
     * cannot be found for the identifier, it is assumed that the record does
     * not exist and as such, the checks performed will be much shallower,
     * typically mimicking those performed by setting the validationType to the
     * current object. This is a slightly different pattern from the standard
     * validation as the caller provides the identifier in the create statement
     * instead of the server assigning an identifier.
     *
     * @param validationType     Identifier of the extent of validation
     * @param socInfo the soc information to be tested.
     * @param context            Context information containing the principalId and locale
     *                           information about the caller of service operation
     * @return the results from performing the validation
     * @throws DoesNotExistException     validationTypeKey not found
     * @throws InvalidParameterException invalid validationTypeKey, socInfo
     * @throws MissingParameterException missing validationTypeKey, socInfo
     * @throws OperationFailedException  unable to complete request
     */
    public List<ValidationResultInfo> validateSoc(@WebParam(name = "validationType") String validationType,
            @WebParam(name = "socInfo") SocInfo socInfo,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException;

    /**
     * Retrieve Soc Ids that contain the specified course offering
     * 
     * This could return multiple Soc ids but should always return the default main 
     * SOC for the term for the course offering was created.
     *
     * @param courseOfferingId Unique Id of the course offering
     * @param context  Context information containing the principalId and locale
     *                 information about the caller of service operation
     * @throws DoesNotExistException     courseId or termId not found
     * @throws InvalidParameterException invalid courseId or termId
     * @throws MissingParameterException missing courseId or termId
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<String> getSocIdsByCourseOffering(@WebParam(name = "courseOfferingId") String courseOfferingId,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException;

    /**
     * Retrieve course offering ids associated with the soc
     * 
     * @return list of course offering ids
     * @param socId Unique Id of the soc
     * @param context  Context information containing the principalId and locale
     *                 information about the caller of service operation
     * @throws DoesNotExistException     courseId or termId not found
     * @throws InvalidParameterException invalid courseId or termId
     * @throws MissingParameterException missing courseId or termId
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<String> getCourseOfferingIdsBySoc(@WebParam(name = "socId") String socId,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException;

    /**
     * Checks if the specified course offering is or is not in the specified soc.
     * 
     * @return true if the course offering is in the soc else false.
     * @param socId Unique Id of the soc
     * @param courseOfferingId Unique id of the course offering
     * @param context  Context information containing the principalId and locale
     *                 information about the caller of service operation
     * @throws DoesNotExistException     either id is not found
     * @throws InvalidParameterException invalid parameter
     * @throws MissingParameterException missing parameter
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public boolean isCourseOfferingInSoc(@WebParam(name = "socId") String socId,
            @WebParam(name = "courseOfferingId") String courseOfferingId,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException;

    /**
     * Retrieve published Course offering ids in this soc
     * 
     * THIS IS A PLACEHOLDER FOR M5 AND NEEDS TO BE REVIEWED
     * Not sure what it means to be "published"
     * 
     * @return list of Course offering ids
     * @param socId Unique Id of the soc
     * @param context  Context information containing the principalId and locale
     *                 information about the caller of service operation
     * @throws DoesNotExistException     courseId or termId not found
     * @throws InvalidParameterException invalid courseId or termId
     * @throws MissingParameterException missing courseId or termId
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    @Deprecated
    public List<String> getPublishedCourseOfferingIdsBySoc(@WebParam(name = "socId") String socId,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException;

    /**
     * Retrieve unpublished Course offering ids in this soc
     * 
     * THIS IS A PLACEHOLDER FOR M5 AND NEEDS TO BE REVIEWED
     * Not sure what it means to be "unpublished"
     * 
     * @return list of Activity offering ids
     * @param socId Unique Id of the soc
     * @param context  Context information containing the principalId and locale
     *                 information about the caller of service operation
     * @throws DoesNotExistException     courseId or termId not found
     * @throws InvalidParameterException invalid courseId or termId
     * @throws MissingParameterException missing courseId or termId
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    @Deprecated
    public List<String> getUnpublishedCourseOfferingIdsBySoc(@WebParam(name = "socId") String socId,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException;

    /**
     * Retrieve unpublished Activity offering ids associated with the course 
     * offerings in this soc
     * 
     * THIS IS A PLACEHOLDER FOR M5 AND NEEDS TO BE REVIEWED
     * Not sure what it means to be "unpublished"
     * 
     * @return list of Activity offering ids
     * @param socId Unique Id of the soc
     * @param context  Context information containing the principalId and locale
     *                 information about the caller of service operation
     * @throws DoesNotExistException     courseId or termId not found
     * @throws InvalidParameterException invalid courseId or termId
     * @throws MissingParameterException missing courseId or termId
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    @Deprecated
    public List<String> getUnpublishedActivityOfferingIdsBySoc(@WebParam(name = "socId") String socId,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException;

    /**
     * Retrieve unscheduled Activity offering ids associated with the course 
     * offerings in this soc
     * 
     * THIS IS A PLACEHOLDER FOR M5 AND NEEDS TO BE REVIEWED
     * Not sure what it means to be "unscheduled"
     * 
     * @return list of Activity offering ids
     * @param socId Unique Id of the soc
     * @param context  Context information containing the principalId and locale
     *                 information about the caller of service operation
     * @throws DoesNotExistException     courseId or termId not found
     * @throws InvalidParameterException invalid courseId or termId
     * @throws MissingParameterException missing courseId or termId
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    @Deprecated
    public List<String> getUnscheduledActivityOfferingIdsBySoc(@WebParam(name = "socId") String socId,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException;

    /**
     * Get course offering ids associated with the soc that have an unscheduled final exam
     * 
     * THIS IS A PLACEHOLDER FOR M5 AND NEEDS TO BE REVIEWED
     * Not sure what it means to be "unscheduled final exam"
     * 
     * @return list of course offering ids
     * @param socId Unique Id of the soc
     * @param context  Context information containing the principalId and locale
     *                 information about the caller of service operation
     * @throws DoesNotExistException     courseId or termId not found
     * @throws InvalidParameterException invalid courseId or termId
     * @throws MissingParameterException missing courseId or termId
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    @Deprecated
    public List<String> getCourseOfferingIdsWithUnscheduledFinalExamsBySoc(@WebParam(name = "socId") String socId,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException;

    /**
     * Requests that the course offerings in this Soc be submitted to the scheduler.
     * 
     * THIS IS A PLACEHOLDER FOR M5 DEVELOPMENT
     * NOTE: we don't know yet with this should return and how to do ASYNC calls.
     *
     * @param socId Unique Id of the soc
     * @param context  Context information containing the principalId and locale
     *                 information about the caller of service operation
     * @throws DoesNotExistException     courseId or termId not found
     * @throws InvalidParameterException invalid courseId or termId
     * @throws MissingParameterException missing courseId or termId
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    @Deprecated
    public StatusInfo scheduleSoc(@WebParam(name = "socId") String socId,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException;

    /**
     * Requests that the course offerings in this Soc be rolled over from one term
     * to the next.
     *
     * @param sourceSocId Unique Id of the source Soc
     * @param targetSocId Unique Id of the source Soc
     * @param optionKeys keys identifying optional processing to occur
     * @return Soc Rollover Results
     * @param context  Context information containing the principalId and locale
     *                 information about the caller of service operation
     * @throws DoesNotExistException     courseId or termId not found
     * @throws InvalidParameterException invalid courseId or termId
     * @throws MissingParameterException missing courseId or termId
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public SocRolloverResultsInfo rolloverSoc(@WebParam(name = "sourceSocId") String sourceSocId,
            @WebParam(name = "targetTermId") String targetTermId,
            @WebParam(name = "optionKeys") String optionKeys,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves the results of a rollover.
     *
     * @param rolloverResultsId unique Id of the rollover results
     * @param context           Context information containing the principalId and locale
     *                          information about the caller of service operation
     * @throws DoesNotExistException     rolloverResultsId in the list not found
     * @throws InvalidParameterException invalid parameter
     * @throws MissingParameterException missing parameter
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public SocRolloverResultsInfo getRolloverResults(@WebParam(name = "rolloverResultsId") String rolloverResultsId,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a list of rollover results by id list.
     *
     * @param rolloverResultsIds List of unique Ids of the rollover results to be fetched
     * @param context           Context information containing the principalId and locale
     *                          information about the caller of service operation
     * @throws DoesNotExistException     rolloverResultsId in the list not found
     * @throws InvalidParameterException invalid parameter
     * @throws MissingParameterException missing parameter
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<SocRolloverResultsInfo> getRolloverResultsByIds(@WebParam(name = "rolloverResultsIds") List<String> rolloverResultsIds,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException;

    
    /**
     * Retrieves a list of rollover result items by results id
     *
     * @param rolloverResultsId Unique Ids of the rollover results for which the items are to be fetched
     * @param context           Context information containing the principalId and locale
     *                          information about the caller of service operation
     * @throws DoesNotExistException     rolloverResultsId in the list not found
     * @throws InvalidParameterException invalid parameter
     * @throws MissingParameterException missing parameter
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<SocRolloverResultItemInfo> getRolloverResultItemsByResultsId(@WebParam(name = "rolloverResultsId") String rolloverResultsId,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException;
    
    /**
     * Retrieves the rollover Results associated with the specified target Soc id
     *
     * @param targetSocId     target Soc Id
     * @param context     Context information containing the principalId and locale
     *                    information about the caller of service operation
     * @return List of Rollover Results Ids
     * @throws DoesNotExistException     courseId or termId not found
     * @throws InvalidParameterException invalid courseId or termId
     * @throws MissingParameterException missing courseId or termId
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<String> getRolloverResultsIdsByTargetSoc(@WebParam(name = "targetSocId") String targetSocId,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException,
            PermissionDeniedException;

    /**
     * Retrieves the rollover Results associated with the specified source Soc id
     *
     * @param sourceSocId      Unique id of the source Soc
     * @param context     Context information containing the principalId and locale
     *                    information about the caller of service operation
     * @return List of Rollover Results Ids
     * @throws DoesNotExistException     courseId or termId not found
     * @throws InvalidParameterException invalid courseId or termId
     * @throws MissingParameterException missing courseId or termId
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<String> getRolloverResultsIdsBySourceSoc(@WebParam(name = "targetSocId") String sourceSocId,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException,
            PermissionDeniedException;

    /**
     * Requests that the rollover identified by the results be cleared.
     * 
     * This attempts to clear or reverse the actions applied by the specified rollover results.
     * Depending on whether or not the resulting course offerings have been updated
     * and the optional processing flags a full reversal is not guaranteed.
     *
     * @param rolloverResultsId Unique Id of the rollover results
     * @param optionKeys keys identifying optional processing to happen when clearing the results
     * @return a Rollover Results indicating what reversal actions were successful or not
     * @param context  Context information containing the principalId and locale
     *                 information about the caller of service operation
     * @throws DoesNotExistException     courseId or termId not found
     * @throws InvalidParameterException invalid courseId or termId
     * @throws MissingParameterException missing courseId or termId
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public SocRolloverResultsInfo reverseRollover(@WebParam(name = "rolloverResultsId") String rolloverResultsId,
            @WebParam(name = "optionKeys") String optionKeys,
            @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException;
}
