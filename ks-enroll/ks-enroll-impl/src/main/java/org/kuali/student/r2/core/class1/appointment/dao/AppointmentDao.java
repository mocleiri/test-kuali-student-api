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
 *
 */
package org.kuali.student.r2.core.class1.appointment.dao;

import org.kuali.student.enrollment.dao.GenericEntityDao;
import org.kuali.student.r2.core.class1.appointment.model.AppointmentEntity;
import org.kuali.student.r2.core.class1.appointment.model.AppointmentSlotEntity;

import java.util.List;

/**
 * This class //TODO ...
 *
 * @author Kuali Student Team
 */
public class AppointmentDao extends GenericEntityDao<AppointmentEntity> {
    public List<AppointmentEntity> getAppointmentsBySlotId(String apptSlotId) {
        return em.createQuery("from AppointmentEntity a where a.slotEntity.id = :apptSlotId")
                .setParameter("apptSlotId", apptSlotId).getResultList();
    }
}