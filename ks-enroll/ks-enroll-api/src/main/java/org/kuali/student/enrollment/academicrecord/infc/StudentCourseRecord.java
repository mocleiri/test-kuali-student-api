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

package org.kuali.student.enrollment.academicrecord.infc;

import java.util.Date;

/**
 * Information about a Student Course Record. A Student Course Record
 * contains information on the courses a student has taken.
 *
 * @author tom
 * @since Tue Sep 06 14:22:34 EDT 2011
 */ 

public interface StudentCourseRecord {
    
    /**
     * The Id of the Student.
     *
     * @name Person Id
     * @readOnly
     * @required
     * @impl retrieved from the Course Registration
     */
    public String getPersonId();

    /**
     * The title of the course that was in effect at the time
     * the student took the course. 
     *
     * @name Course Title
     * @readOnly
     * @required
     * @impl retrieved from the CourseOffering related to the Course 
     *       Registration
     */
    public String getCourseTitle();

    /**
     * The code or number of the course that was in effect at the time
     * the student took the course. 
     *
     * @name Course Code
     * @readOnly
     * @required
     * @impl retrieved from the Course Offering related to the Course 
     *       Registration
     */
    public String getCourseCode();

    /**
     * The code or number of the primary activity or section that was
     * in effect at the time the student took the course.
     *
     * @name Course Code
     * @readOnly
     * @required
     * @impl retrieved from the Course Offering related to the Course 
     *       Registration
     */
    public String getActivityCode();
    
    /**
     * The name of the term in which the student took the offering.
     *
     * @name Term Name
     * @readonly
     * @impl retrieved from the Term related to the Course Offering 
     *       in the Course Registration
     */
    public String getTermName();

    /**
     * The start date of the course.
     *
     * @name Course Begin Date
     * @readonly
     * @impl retrieved from the Term related to the Course Offering 
     *       in the Course Registration
     */
    public Date getCourseBeginDate();

    /**
     * The end date of the course.
     *
     * @name Course End Date
     * @readonly
     * @impl retrieved from the Term related to the Course Offering 
     *       in the Course Registration
     */
    public Date getCourseEndDate();

    /**
     * The grade the student was assigned for the course.
     *
     * @name Assigned Grade Value
     * @readonly
     */
    public String getAssignedGradeValue();

    /**
     * The Id for the grading scale for the assigned grade.
     *
     * @name Assigned Grade Scale
     * @readonly
     * @impl the Id of the Result Values Group
     */
    public String getAssignedGradeScaleId();

    /**
     * The grade the student was assigned for the course.
     *
     * @name Administrative Grade Value
     * @readonly
     */
    public String getAdministrativeGradeValue();

    /**
     * The Id for the grading scale for the administrative grade.
     *
     * @name Administrative Grade Scale
     * @readonly
     * @impl the Id of the Result Values Group
     */
    public String getAdministrativeGradeScaleId();

    /**
     * The calculated grade the student earned for the course.
     *
     * @name Calculated Grade Value
     * @readonly
     */
    public String getCalculatedGradeValue();

    /**
     * The Id for the grading scale for the calculated grade.
     *
     * @name Calculated Grade Scale
     * @readonly
     * @impl the Id of the Result Values Group
     */
    public String getCalculatedGradeScaleId();

    /**
     * The number of credits the student attempted for this course.
     *
     * @return a string representing a floating point decimal number
     * @name Credits Attempted
     * @required
     * @readonly
     */
    public String getCreditsAttempted();

    /**
     * The number of credits the student earned for this course.
     *
     * @return a string representing a floating point decimal number
     * @name Credits Earned
     * @readonly
     */
    public String getCreditsEarned();

    /**
     * The number of credits to be applied for the GPA calculation.
     * This is provides a weighting to this course for the GPA.
     *
     * @return a string representing a floating point decimal number
     * @name Credits For GPA
     */
    public String getCreditsForGPA();

    /**
     * If this student record counts toward the cumultive credits.
     *
     * @name Counts Toward Credits
     */
    public Boolean countsTowardCredits();

    /**
     * If this course is a repeat of a previous offering. the student
     * took.
     *
     * @name Is Repeated
     */
    public Boolean isRepeated();
}
