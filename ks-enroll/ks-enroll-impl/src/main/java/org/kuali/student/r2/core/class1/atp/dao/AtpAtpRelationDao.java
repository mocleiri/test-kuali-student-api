package org.kuali.student.r2.core.class1.atp.dao;

import java.util.List;

import org.kuali.student.enrollment.dao.GenericEntityDao;
import org.kuali.student.r2.core.class1.atp.model.AtpAtpRelationEntity;

public class AtpAtpRelationDao extends GenericEntityDao<AtpAtpRelationEntity>{
    
    @SuppressWarnings({"unchecked"})
    public List<AtpAtpRelationEntity> getAtpAtpRelationsByAtp(String atpKey) {
    	return (List<AtpAtpRelationEntity>) em.createQuery("from AtpAtpRelationEntity rel where rel.atp.id=:atpKey OR rel.relatedAtp.id=:atpKey").setParameter("atpKey", atpKey).getResultList();
    }
    
    @SuppressWarnings({"unchecked"})
    public List<AtpAtpRelationEntity> getAtpAtpRelationsByAtpAndRelationType(String atpKey, String relationTypeId) {
    	return (List<AtpAtpRelationEntity>) em.createQuery("from AtpAtpRelationEntity rel where rel.atp.id=:atpKey AND rel.atpType.id=:relationTypeId").setParameter("atpKey", atpKey).setParameter("relationTypeId", relationTypeId).getResultList();
    }
}
