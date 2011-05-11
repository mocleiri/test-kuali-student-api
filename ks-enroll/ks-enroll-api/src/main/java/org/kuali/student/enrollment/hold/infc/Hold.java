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

package org.kuali.student.enrollment.hold.infc;

import java.util.Date;

import org.kuali.student.r2.common.infc.IdEntity;


/**
 * Information about a Hold.
 *
 * @Author tom
 * @Since Sun May 1 14:22:34 EDT 2011
 */ 

public interface Hold extends IdEntity {

    /**
     * Name: Hold Category Id
     * The kind of Hold.
     */
    public String getHoldCategoryId();
    public void setHoldCategoryId(String holdCategoryId);

    /**
     * Name: Person Id
     * The Id of the Person.
     */
    public String getPersonId();
    public void setPersonId(String personId);

    /**
     * Name: Is Warning
     * Indicates whether this hold is a warning or should result in a
     * block.
     */
    public Boolean isWarning();
    public void setWarning(boolean isWarning);
    
    /**
     * Name: Is Overridable
     * Indicates whether an exception can override this hold.
     */
    public Boolean isOverridable();
    public void setOverridable(boolean isOverridable);

    /**
     * Name: Effective Date
     * The date this hold becomes effective.
     */
    public Date getEffectiveDate();
    public void setEffectiveDate(Date effectiveDate);

    /**
     * Name: Released Date
     * The date this hold was released, of null if not yet released.
     */
    public Date getReleasedDate();
    public void setReleasedDate(Date releasedDate);
}
