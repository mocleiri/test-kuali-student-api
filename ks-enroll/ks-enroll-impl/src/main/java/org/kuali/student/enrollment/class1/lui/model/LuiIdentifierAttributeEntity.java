package org.kuali.student.enrollment.class1.lui.model;

import org.kuali.student.r2.common.entity.BaseAttributeEntity;
import org.kuali.student.r2.common.infc.Attribute;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "KSEN_LUI_IDENT_ATTR")
public class LuiIdentifierAttributeEntity extends BaseAttributeEntity<LuiIdentifierEntity> {
    
    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private LuiIdentifierEntity owner;

    public LuiIdentifierAttributeEntity(){}
    
    public LuiIdentifierAttributeEntity(Attribute att) {
        super(att);
    }
    
    public LuiIdentifierAttributeEntity(String key, String value) {
        super(key, value);
    }

    @Override
    public void setOwner(LuiIdentifierEntity owner) {
        this.owner = owner;
    }

    @Override
    public LuiIdentifierEntity getOwner() {
        return owner;
    }
}