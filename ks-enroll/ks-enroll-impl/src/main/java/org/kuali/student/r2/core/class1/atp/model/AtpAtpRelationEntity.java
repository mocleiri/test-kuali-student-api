package org.kuali.student.r2.core.class1.atp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.kuali.student.r2.common.dto.AttributeInfo;
import org.kuali.student.r2.common.entity.AttributeOwner;
import org.kuali.student.r2.common.entity.MetaEntity;
import org.kuali.student.r2.common.infc.Attribute;
import org.kuali.student.r2.common.model.StateEntity;
import org.kuali.student.r2.core.atp.dto.AtpAtpRelationInfo;
import org.kuali.student.r2.core.atp.infc.AtpAtpRelation;

@Entity
@Table(name = "KSEN_ATPATP_RELTN")
public class AtpAtpRelationEntity extends MetaEntity implements AttributeOwner<AtpAtpRelationAttributeEntity> {
    @ManyToOne
    @JoinColumn(name="ATP_ID")
    private AtpEntity atp;
    
    @ManyToOne
    @JoinColumn(name="RELATED_ATP_ID")
    private AtpEntity relatedAtp;
    
    @ManyToOne
    @JoinColumn(name="ATP_RELTN_TYPE_ID")
    private AtpTypeEntity atpType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EFF_DT")
    private Date effectiveDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EXPIR_DT")
    private Date expirationDate;

    @ManyToOne
    @JoinColumn(name = "ATP_STATE_ID")
    private StateEntity atpState;
 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<AtpAtpRelationAttributeEntity> attributes;

   public AtpAtpRelationEntity(){}
    
    public AtpAtpRelationEntity(AtpAtpRelation atpAtpRelation){
        this.setId(atpAtpRelation.getId());
        this.setEffectiveDate(atpAtpRelation.getEffectiveDate());
        this.setExpirationDate(atpAtpRelation.getExpirationDate());
        
        this.setAttributes(new ArrayList<AtpAtpRelationAttributeEntity>());
        if (null != atpAtpRelation.getAttributes()) {
            for (Attribute att : atpAtpRelation.getAttributes()) {
                this.getAttributes().add(new AtpAtpRelationAttributeEntity(att));
            }
        }
    }

    public AtpEntity getAtp() {
        return atp;
    }

    public void setAtp(AtpEntity atp) {
        this.atp = atp;
    }

    public AtpEntity getRelatedAtp() {
        return relatedAtp;
    }

    public void setRelatedAtp(AtpEntity relatedAtp) {
        this.relatedAtp = relatedAtp;
    }

    public AtpTypeEntity getAtpType() {
        return atpType;
    }

    public void setAtpType(AtpTypeEntity atpType) {
        this.atpType = atpType;
    }

    public Date getEffectiveDate() {
    	return effectiveDate != null ? new Date(effectiveDate.getTime()) : null;
    }

    public void setEffectiveDate(Date effectiveDate) {
    	if(effectiveDate != null)
    		this.effectiveDate = new Date(effectiveDate.getTime());
    }

    public Date getExpirationDate() {
    	return expirationDate != null ? new Date(expirationDate.getTime()) : null;
    }

    public void setExpirationDate(Date expirationDate) {
    	if(expirationDate != null)
    		this.expirationDate = new Date(expirationDate.getTime());
    }


    public StateEntity getAtpState() {
        return atpState;
    }

    public void setAtpState(StateEntity atpState) {
        this.atpState = atpState;
    }

    @Override
    public void setAttributes(List<AtpAtpRelationAttributeEntity> attributes) {
        this.attributes = attributes;       
    }

    @Override
    public List<AtpAtpRelationAttributeEntity> getAttributes() {
        return attributes;
    }

    public AtpAtpRelationInfo toDto() {
        AtpAtpRelationInfo aarInfo = new AtpAtpRelationInfo();
        aarInfo.setId(getId());
        aarInfo.setAtpId(atp.getId());
        aarInfo.setRelatedAtpId(relatedAtp.getId());
        aarInfo.setEffectiveDate(effectiveDate);
        aarInfo.setExpirationDate(expirationDate);
        aarInfo.setStateKey(atpState.getId());
        aarInfo.setTypeKey(atpType.getId());
        aarInfo.setMeta(super.toDTO());
        
        List<AttributeInfo> atts = new ArrayList<AttributeInfo>();
        for (AtpAtpRelationAttributeEntity att : getAttributes()) {
        	AttributeInfo attInfo = att.toDto();
            atts.add(attInfo);
        }
        aarInfo.setAttributes(atts);
        
        return aarInfo;
    }
}
