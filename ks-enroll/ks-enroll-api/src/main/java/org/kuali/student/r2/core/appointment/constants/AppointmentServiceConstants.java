/**
 * Copyright 2012 The Kuali Foundation Licensed under the
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
package org.kuali.student.r2.core.appointment.constants;

import org.kuali.student.r2.common.constants.CommonServiceConstants;
import org.kuali.student.r2.core.appointment.dto.AppointmentInfo;
import org.kuali.student.r2.core.appointment.dto.AppointmentSlotInfo;
import org.kuali.student.r2.core.appointment.dto.AppointmentWindowInfo;


/**
 * This class holds the constants used by the Appointment service
 *
 * @Version 1.0
 * @Author Sri komandur@uw.edu
 */
public class AppointmentServiceConstants {

    /**
     * Reference Object URI's
     */
    public static final String NAMESPACE = CommonServiceConstants.REF_OBJECT_URI_GLOBAL_PREFIX + "appointment";
    public static final String SERVICE_NAME_LOCAL_PART = "AppointmentService";
    public static final String REF_OBJECT_URI_APPOINTMENT = NAMESPACE + "/" + AppointmentInfo.class.getSimpleName();
    public static final String REF_OBJECT_URI_APPOINTMENT_SLOT = NAMESPACE + "/" + AppointmentSlotInfo.class.getSimpleName();
    public static final String REF_OBJECT_URI_APPOINTMENT_WINDOW = NAMESPACE + "/" + AppointmentWindowInfo.class.getSimpleName();

}
