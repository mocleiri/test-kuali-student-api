package org.kuali.student.r2.core.classI.atp.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.kuali.student.enrollment.dao.GenericEntityDao;
import org.kuali.student.r2.core.classI.atp.model.MilestoneEntity;

public class MilestoneDao extends GenericEntityDao<MilestoneEntity> {

    @SuppressWarnings("unchecked")
    public List<MilestoneEntity> getByMilestoneTypeId(String milestoneTypeId) {
        return em.createQuery("from MilestoneEntity m where m.milestoneType.id=:mstoneTypeId").setParameter("mstoneTypeId", milestoneTypeId).getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public List<MilestoneEntity> getByDateRange(Date startRange, Date endRange) {
        return em.createQuery("from MilestoneEntity m where m.startDate between startRange and endRange or m.endDate between startRange and endRange").setParameter("startRange", startRange).setParameter("endRange", endRange).getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public List<MilestoneEntity> getByDateRangeAndType(Date startRange, Date endRange, String milestoneTypeId) {
        Query query = em.createQuery("from MilestoneEntity m where (m.startDate between startRange and endRange or m.endDate between startRange and endRange) and (m.milestoneType.id=:mstoneTypeId)");
        query.setParameter("startRange", startRange);
        query.setParameter("endRange", endRange);
        query.setParameter("mstoneTypeId", milestoneTypeId);
        
        return query.getResultList();
    }
    
}
