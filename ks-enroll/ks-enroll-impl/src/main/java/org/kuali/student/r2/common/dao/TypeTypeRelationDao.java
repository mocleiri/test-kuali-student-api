package org.kuali.student.r2.common.dao;

import java.util.List;

import org.kuali.student.enrollment.dao.GenericEntityDao;
import org.kuali.student.r2.core.class1.type.entity.TypeTypeRelationEntity;

public class TypeTypeRelationDao extends GenericEntityDao<TypeTypeRelationEntity>{

    @SuppressWarnings("unchecked")
    public List<TypeTypeRelationEntity> getTypeTypeRelationsByOwnerAndRelationTypes(String ownerTypeId, String relationTypeId) {
        return (List<TypeTypeRelationEntity>) em.createQuery("from TypeTypeRelationEntity rel where rel.type=:relationTypeId AND rel.ownerTypeId=:ownerTypeId").setParameter("relationTypeId", relationTypeId).setParameter("ownerTypeId", ownerTypeId).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<TypeTypeRelationEntity> getTypeTypeRelationsByOwnerType(String ownerTypeId) {
        return (List<TypeTypeRelationEntity>) em.createQuery("from TypeTypeRelationEntity rel where rel.ownerTypeId=:ownerTypeId").setParameter("ownerTypeId", ownerTypeId).getResultList();
    }
}
