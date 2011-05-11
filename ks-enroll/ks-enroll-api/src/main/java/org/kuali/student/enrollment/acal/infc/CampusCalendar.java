/*
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

package org.kuali.student.enrollment.acal.infc;

import java.util.Date;

import org.kuali.student.r2.common.infc.KeyEntity;


/**
 * Information about a Campus Calendar.
 *
 * @Author tom
 * @Since Tue Apr 05 14:22:34 EDT 2011
 */ 

public interface CampusCalendar extends KeyEntity {

    /**
     * Name: Start Date
     * Date and time the campus calendar became effective. This
     * does not provide a bound on date ranges or milestones
     * associated with this calendar, but instead indicates the
     * calendar proper. This is a similar concept to the effective
     * date on enumerated values. When an expiration date has been
     * specified, this field must be less than or equal to the
     * expiration date.
     */
    public Date getStartDate();
    public void setStartDate(Date startDate);

    /**
     * Name: Start Date
     * Date and time the campus calendarexpires. This does not
     * provide a bound on date ranges or milestones associated with
     * this calendar, but instead indicates the calendar
     * proper. If specified, this must be greater than or equal to the
     * effective date. If this field is not specified, then no
     * expiration date has been currently defined and should
     * automatically be considered greater than the effective date.
     */
    public Date getEndDate();
    public void setEndDate(Date endDate);

    /**
     * Name: Location
     * The campus or location to which this calendar pertains.
     */
    public String getLocation();
    public void setLocation(String location);
}
