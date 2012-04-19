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

    public static final String APPOINTMENT_WINDOW_TYPE_SLOTTED_UNIFORM_KEY = "kuali.appointment.window.type.slotted.uniform";
    public static final String APPOINTMENT_WINDOW_TYPE_SLOTTED_MAX_KEY = "kuali.appointment.window.type.slotted.max";
    public static final String APPOINTMENT_WINDOW_TYPE_ONE_SLOT_KEY = "kuali.appointment.window.type.one.slot";
    public static final String APPOINTMENT_WINDOW_TYPE_MANUAL = "kuali.appointment.window.type.manual";

    public static final String APPOINTMENT_SLOT_TYPE_CLOSED_KEY = "kuali.appointment.slot.type.closed";
    public static final String APPOINTMENT_SLOT_TYPE_OPEN_KEY = "kuali.appointment.slot.type.open";

    public static final String APPOINTMENT_TYPE_REGISTRATION = "kuali.appointment.type.registration";
    public static final String APPOINTMENT_TYPE_ADVISING = "kuali.appointment.type.advising";

    public static final String APPOINTMENT_WINDOW_LIFECYCLE = "kuali.appointment.window.lifecycle";
    // public static final String APPOINTMENT_WINDOW_STATE_ACTIVE_KEY = "kuali.appointment.window.state.active";
    public static final String APPOINTMENT_WINDOW_STATE_DRAFT_KEY = "kuali.appointment.window.state.draft";
    public static final String APPOINTMENT_WINDOW_STATE_ASSIGNED_KEY = "kuali.appointment.window.state.assigned";
    public static final String[] APPOINTMENT_WINDOW_LIFECYCLE_STATES = {APPOINTMENT_WINDOW_STATE_DRAFT_KEY, APPOINTMENT_WINDOW_STATE_ASSIGNED_KEY};

    public static final String APPOINTMENT_SLOTS_LIFECYCLE = "kuali.appointment.slots.lifecycle";
    public static final String APPOINTMENT_SLOTS_STATE_ACTIVE_KEY = "kuali.appointment.slots.state.active";
    public static final String[] APPOINTMENT_SLOTS_LIFECYCLE_STATES = {APPOINTMENT_SLOTS_STATE_ACTIVE_KEY};

    public static final String APPOINTMENT_LIFECYCLE = "kuali.appointment.lifecycle";
    public static final String APPOINTMENT_STATE_ACTIVE_KEY = "kuali.appointment.state.active";
    public static final String[] APPOINTMENT_LIFECYCLE_STATES = {APPOINTMENT_STATE_ACTIVE_KEY};

    //Message keys //TODO move these out of API to UI IMPL
    public static final String APPOINTMENT_MSG_INFO_SAVED ="info.enroll.appointment.saved";
    public static final String APPOINTMENT_MSG_INFO_ASSIGNED ="info.enroll.appointment.assigned";
    public static final String APPOINTMENT_MSG_ERROR_TOO_MANY_STUDENTS = "error.enroll.appointment.tooManyStudents";
    public static final String APPOINTMENT_MSG_INFO_BREAK_APPOINTMENTS_SUCCESS = "info.enroll.appointment.breakAppointmentsSuccess";
    public static final String APPOINTMENT_MSG_ERROR_BREAK_APPOINTMENTS_FAILURE = "error.enroll.appointment.breakAppointmentsFailure";
    public static final String APPOINTMENT_MSG_ERROR_NO_TERMS_FOUND = "error.enroll.appointment.noTermsFound";
    public static final String APPOINTMENT_MSG_ERROR_NO_REG_PERIODS_FOR_TERM = "error.enroll.appointment.noRegPeriodsForTerm";
}
