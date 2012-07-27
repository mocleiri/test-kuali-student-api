package org.kuali.student.enrollment.courseregistration.infc;


import org.kuali.student.r2.common.infc.HasAttributesAndMeta;
import org.kuali.student.r2.common.infc.OperationStatus;

/**
 * A more granular status object than the Registration response which gives the
 * response status of the individual registration item, i.e., a course reg
 * group. It has a reference to the Course Registration in case of a successful
 * registration. The response contains status such as SUCCESS or FAILURE and
 * then necessary messages, warning or errors. The state of the response in case
 * of a success can be registered, waitlisted, or holdlisted.
 * 
 * @author Kuali Student Team (sambit)
 */
public interface RegResponseItem {
    
    /**
     * 
     * Get the operation status info for the registration item.
     * 
     * @return
     */
    public OperationStatus getOperationStatus();
    /**
     * 
     * This method ...
     * 
     * @return
     */
    public String getRegRequestItemId();
    /**
     * 
     * Gets the course registration (if any) that resulted from this   registration transaction
     * 
     * @return
     */
    public String getCourseRegistrationId();

    /**
     * Gets the waitlist entry (if any) that resulted from this registration transaction
     * 
     * @return
     */
    public String getCourseWaitlistEntryId();
    
    
}
