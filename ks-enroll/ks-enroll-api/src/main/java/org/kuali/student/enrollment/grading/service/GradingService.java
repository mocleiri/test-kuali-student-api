/*
 * Copyright 2007 The Kuali Foundation Licensed under the Educational Community
 * License, Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.opensource.org/licenses/ecl1.php Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.kuali.student.enrollment.grading.service;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.kuali.student.enrollment.grading.dto.GradeRosterEntryInfo;
import org.kuali.student.enrollment.grading.dto.GradeRosterInfo;
import org.kuali.student.r2.common.datadictionary.service.DataDictionaryService;
import org.kuali.student.r2.common.dto.ContextInfo;
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
import org.kuali.student.r2.common.util.constants.GradingServiceConstants;
import org.kuali.student.r2.lum.lrc.dto.ResultValuesGroupInfo;

/**
 * Version: DRAFT - NOT READY FOR RELEASE. This service manages grade and credit
 * submission for activity/course offerings. The service can be used for
 * capturing final grades and any number of interim grades. The service provides
 * the following primary functions:
 * <ol>
 * <li>Build Grade Rosters
 * <li>Capture Assigned Grades for students in various rosters
 * <li>Provides valid grading options for a student in an activity
 * <li>Calculate grade translation for the grades within that offering (for e.g
 * A-F grades to Pass/No-pass)
 * <li>Calculate credits earned within that offering
 * <li>Submission of final grades for the offering
 * <li>Students are clubbed together into grade rosters for the purpose of
 * grading. For any assessment event, one or more grade rosters of a certain
 * type is created. Instructors (and/or delegates) are then allowed to enter
 * grades for students on the roster. Only the final grade roster is submittable
 * and the submission is tracked using the state of the grade roster.
 * </ol>
 * The service automatically creates the grade roster for the final grades. This
 * roster(s) is created only for the offerings of the primary activity type. The
 * structure and breakdown of the students in the final grade roster can be
 * configured and student can exist only once in the final grade roster(s).
 * Interim rosters can be created for activity offerings based on a assessment
 * event like mid term, weekly quiz etc. Version: 1.0 (Dev)
 * 
 * @author Kuali Student Team (Kamal)
 */
@WebService(name = "GradingService", serviceName = "GradingService", portName = "GradingService", targetNamespace = GradingServiceConstants.NAMESPACE)
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface GradingService extends DataDictionaryService {

    /**
     * This method returns the TypeInfo for a given grade roster type key.
     * 
     * @param gradeRosterTypeKey
     *            Key of the type
     * @param context
     *            Context information containing the principalId and locale
     *            information about the caller of service operation
     * @return Information about the Type
     * @throws DoesNotExistException
     *             gradeRosterTypeKey not found
     * @throws InvalidParameterException
     *             invalid gradeRosterTypeKey
     * @throws MissingParameterException
     *             missing gradeRosterTypeKey
     * @throws OperationFailedException
     *             unable to complete request
     */
    public TypeInfo getGradeRosterType(@WebParam(name = "gradeRosterTypeKey") String gradeRosterTypeKey,
            @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException;

    /**
     * Retrieve information about a grade roster
     * 
     * @param gradeRosterId
     * @param context
     *            Context information containing the principalId and locale
     *            information about the caller of service operation
     * @return
     * @throws DoesNotExistException
     * @throws InvalidParameterException
     * @throws MissingParameterException
     * @throws OperationFailedException
     * @throws PermissionDeniedException
     *             authorization failure
     */
    public GradeRosterInfo getGradeRoster(@WebParam(name = "gradeRosterId") String gradeRosterId,
            @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieve information about grade rosters by grader and term
     * 
     * @param graderId
     * @param termKey
     * @param context
     *            Context information containing the principalId and locale
     *            information about the caller of service operation
     * @return
     * @throws DoesNotExistException
     * @throws InvalidParameterException
     * @throws MissingParameterException
     * @throws OperationFailedException
     * @throws PermissionDeniedException
     *             authorization failure
     */
    public List<GradeRosterInfo> getGradeRostersByGraderAndTerm(@WebParam(name = "graderId") String graderId,
            @WebParam(name = "termKey") String termKey, @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves rosters of final grades for a course offering
     * 
     * @param courseOfferingId
     * @param context
     *            Context information containing the principalId and locale
     *            information about the caller of service operation
     * @return
     * @throws DoesNotExistException
     * @throws InvalidParameterException
     * @throws MissingParameterException
     * @throws OperationFailedException
     * @throws PermissionDeniedException
     *             authorization failure
     */
    public List<GradeRosterInfo> getFinalGradeRostersForCourseOffering(
            @WebParam(name = "courseOfferingId") String courseOfferingId,
            @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves rosters of final grade by actvity offerings
     * 
     * @param activityOfferingId
     * @param context
     *            Context information containing the principalId and locale
     *            information about the caller of service operation
     * @return
     * @throws DoesNotExistException
     * @throws InvalidParameterException
     * @throws MissingParameterException
     * @throws OperationFailedException
     * @throws PermissionDeniedException
     *             authorization failure
     */
    public List<GradeRosterInfo> getFinalGradeRostersForActivityOffering(
            @WebParam(name = "activityOfferingId") String activityOfferingId,
            @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves all rosters for an activity offering
     * 
     * @param activityOfferingId
     * @param context
     *            Context information containing the principalId and locale
     *            information about the caller of service operation
     * @return
     * @throws DoesNotExistException
     * @throws InvalidParameterException
     * @throws MissingParameterException
     * @throws OperationFailedException
     * @throws PermissionDeniedException
     *             authorization failure
     */
    public List<GradeRosterInfo> getGradeRostersForActivityOffering(
            @WebParam(name = "activityOfferingId") String activityOfferingId,
            @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Build an interim roster of given type. Roster type should be used to
     * figure out which students from the activity offerings will be in the
     * roster
     * 
     * @param activityOfferingIdList
     * @param rosterTypeKey
     * @param context
     *            Context information containing the principalId and locale
     *            information about the caller of service operation
     * @return
     * @throws AlreadyExistsException
     * @throws InvalidParameterException
     * @throws MissingParameterException
     * @throws OperationFailedException
     * @throws PermissionDeniedException
     *             authorization failure
     */
    public GradeRosterInfo buildInterimGradeRosterByType(
            @WebParam(name = "activityOfferingIdList") String courseOfferingId,
            @WebParam(name = "activityOfferingIdList") List<String> activityOfferingIdList,
            @WebParam(name = "rosterTypeKey") String rosterTypeKey, @WebParam(name = "context") ContextInfo context)
            throws AlreadyExistsException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException;

    /**
     * Update interim grade roster information
     * 
     * @param gradeRoster
     * @param context
     *            Context information containing the principalId and locale
     *            information about the caller of service operation
     * @return
     * @throws DataValidationErrorException
     *             One or more values invalid for this operation
     * @throws DoesNotExistException
     * @throws InvalidParameterException
     * @throws MissingParameterException
     * @throws OperationFailedException
     * @throws PermissionDeniedException
     *             authorization failure
     * @throws VersionMismatchException
     */
    public GradeRosterInfo updateInterimGradeRoster(@WebParam(name = "gradeRoster") GradeRosterInfo gradeRoster,
            @WebParam(name = "context") ContextInfo context) throws DataValidationErrorException,
            DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException, VersionMismatchException;

    /**
     * Delete an interim grade roster
     * 
     * @param gradeRosterId
     * @param context
     *            Context information containing the principalId and locale
     *            information about the caller of service operation
     * @return
     * @throws DoesNotExistException
     * @throws InvalidParameterException
     * @throws MissingParameterException
     * @throws OperationFailedException
     * @throws PermissionDeniedException
     *             authorization failure
     */
    public StatusInfo deleteInterimGradeRoster(@WebParam(name = "gradeRosterId") String gradeRosterId,
            @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Update state of final grade roster. Only state can be changed for the
     * final grade roster. Final grade submission is tracked through state
     * change on the roster.
     * 
     * @param gradeRosterId
     * @param stateKey
     * @param context
     *            Context information containing the principalId and locale
     *            information about the caller of service operation
     * @return
     * @throws DataValidationErrorException
     *             One or more values invalid for this operation
     * @throws DoesNotExistException
     * @throws InvalidParameterException
     * @throws MissingParameterException
     * @throws OperationFailedException
     * @throws PermissionDeniedException
     *             authorization failure
     * @throws VersionMismatchException
     */
    public GradeRosterInfo updateFinalGradeRosterState(@WebParam(name = "gradeRosterId") String gradeRosterId,
            @WebParam(name = "newStateKey") String stateKey, @WebParam(name = "context") ContextInfo context)
            throws DataValidationErrorException, DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException, VersionMismatchException;

    /**
     * Validate a grade roster information
     * 
     * @param gradeRoster
     * @param context
     *            Context information containing the principalId and locale
     *            information about the caller of service operation
     * @return
     * @throws DoesNotExistException
     * @throws InvalidParameterException
     * @throws MissingParameterException
     * @throws OperationFailedException
     */
    public List<ValidationResultInfo> validateGradeRoster(@WebParam(name = "gradeRoster") GradeRosterInfo gradeRoster,
            @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException;

    /**
     * Retrieve information about a grade roster entry
     * 
     * @param gradeRosterEntryId
     * @param context
     *            Context information containing the principalId and locale
     *            information about the caller of service operation
     * @return
     * @throws DoesNotExistException
     * @throws InvalidParameterException
     * @throws MissingParameterException
     * @throws OperationFailedException
     * @throws PermissionDeniedException
     *             authorization failure
     */
    public GradeRosterEntryInfo getGradeRosterEntry(@WebParam(name = "gradeRosterEntryId") String gradeRosterEntryId,
            @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieve a list of grade roster entries based on their ids. The method
     * should fail if there is an error in retrieving any id from the list.
     * 
     * @param gradeRosterEntryIdList
     * @param context
     *            Context information containing the principalId and locale
     *            information about the caller of service operation
     * @return
     * @throws DoesNotExistException
     * @throws InvalidParameterException
     * @throws MissingParameterException
     * @throws OperationFailedException
     * @throws PermissionDeniedException
     *             authorization failure
     */
    public List<GradeRosterEntryInfo> getGradeRosterEntriesByIdList(
            @WebParam(name = "gradeRosterEntryIdList") List<String> gradeRosterEntryIdList,
            @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieve grade roster entries by roster
     * 
     * @param gradeRosterId
     * @param context
     *            Context information containing the principalId and locale
     *            information about the caller of service operation
     * @return
     * @throws DoesNotExistException
     * @throws InvalidParameterException
     * @throws MissingParameterException
     * @throws OperationFailedException
     * @throws PermissionDeniedException
     *             authorization failure
     */
    public List<GradeRosterEntryInfo> getGradeRosterEntriesByRosterId(
            @WebParam(name = "gradeRosterId") String gradeRosterId, @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException;

    /**
     * This method ...
     * 
     * @param studentId
     * @param rosterId
     * @param context
     *            Context information containing the principalId and locale
     *            information about the caller of service operation
     * @return
     * @throws DoesNotExistException
     * @throws InvalidParameterException
     * @throws MissingParameterException
     * @throws OperationFailedException
     * @throws PermissionDeniedException
     *             authorization failure
     */
    public List<ResultValuesGroupInfo> getValidGradesForStudentByRoster(@WebParam(name = "studentId") String studentId,
            @WebParam(name = "rosterId") String rosterId, @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException;

    /**
     * This method ...
     * 
     * @param studentId
     * @param courseOfferingId
     * @param context
     *            Context information containing the principalId and locale
     *            information about the caller of service operation
     * @return
     * @throws DoesNotExistException
     * @throws InvalidParameterException
     * @throws MissingParameterException
     * @throws OperationFailedException
     * @throws PermissionDeniedException
     *             authorization failure
     */
    public GradeRosterEntryInfo getFinalGradeForStudentInCourseOffering(@WebParam(name = "studentId") String studentId,
            @WebParam(name = "courseOfferingId") String courseOfferingId,
            @WebParam(name = "context") ContextInfo context) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * This method ...
     * 
     * @param gradeRosterEntry
     * @param gradeRosterId
     * @param context
     *            Context information containing the principalId and locale
     *            information about the caller of service operation
     * @return
     * @throws AlreadyExistsException
     * @throws InvalidParameterException
     * @throws MissingParameterException
     * @throws OperationFailedException
     * @throws PermissionDeniedException
     *             authorization failure
     */
    public GradeRosterEntryInfo addEntrytoInterimRoster(
            @WebParam(name = "gradeRosterEntry") GradeRosterEntryInfo gradeRosterEntry,
            @WebParam(name = "gradeRosterId") String gradeRosterId, @WebParam(name = "context") ContextInfo context)
            throws AlreadyExistsException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException;

    /**
     * This method ...
     * 
     * @param gradeRosterEntryId
     * @param gradeRosterId
     * @param context
     *            Context information containing the principalId and locale
     *            information about the caller of service operation
     * @return
     * @throws DoesNotExistException
     * @throws InvalidParameterException
     * @throws MissingParameterException
     * @throws OperationFailedException
     * @throws PermissionDeniedException
     *             authorization failure
     */
    public StatusInfo removeEntryFromInterimRoster(@WebParam(name = "gradeRosterEntryId") String gradeRosterEntryId,
            @WebParam(name = "gradeRosterId") String gradeRosterId, @WebParam(name = "context") ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException;

    /**
     * This method ... TODO - do we need to change the output to an object
     * instead of boolean?
     * 
     * @param gradeRosterEntryId
     * @param assignedGradeId
     * @param context
     *            Context information containing the principalId and locale
     *            information about the caller of service operation
     * @return
     * @throws DataValidationErrorException
     *             One or more values invalid for this operation
     * @throws DoesNotExistException
     * @throws InvalidParameterException
     * @throws MissingParameterException
     * @throws OperationFailedException
     * @throws PermissionDeniedException
     *             authorization failure
     * @throws VersionMismatchException
     */
    public boolean updateAssignedGrade(@WebParam(name = "gradeRosterEntryId") String gradeRosterEntryId,
            @WebParam(name = "assignedGradeId") String assignedGradeId, @WebParam(name = "context") ContextInfo context)
            throws DataValidationErrorException, DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException, VersionMismatchException;

    /**
     * This method ... TODO - do we need to change the output to an object
     * instead of boolean?
     * 
     * @param gradeRosterEntryId
     * @param assignedGrade
     * @param context
     *            Context information containing the principalId and locale
     *            information about the caller of service operation
     * @return
     * @throws DataValidationErrorException
     *             One or more values invalid for this operation
     * @throws DoesNotExistException
     * @throws InvalidParameterException
     * @throws MissingParameterException
     * @throws OperationFailedException
     * @throws PermissionDeniedException
     *             authorization failure
     * @throws VersionMismatchException
     */
    public boolean updateCredit(@WebParam(name = "gradeRosterEntryId") String gradeRosterEntryId,
            @WebParam(name = "creditsEarned") String assignedGrade, @WebParam(name = "context") ContextInfo context)
            throws DataValidationErrorException, DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException, VersionMismatchException;
}
