package org.kuali.student.enrollment.class1.lui.model;

import org.kuali.student.common.entity.KSEntityConstants;
import org.kuali.student.enrollment.lui.dto.LuiLuiRelationInfo;
import org.kuali.student.enrollment.lui.infc.LuiLuiRelation;
import org.kuali.student.r2.common.assembler.TransformUtility;
import org.kuali.student.r2.common.dto.AttributeInfo;
import org.kuali.student.r2.common.entity.AttributeOwner;
import org.kuali.student.r2.common.entity.MetaEntity;
import org.kuali.student.r2.common.infc.Attribute;
import org.kuali.student.r2.common.util.RichTextHelper;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "KSEN_LUILUI_RELTN")
public class LuiLuiRelationEntity extends MetaEntity implements AttributeOwner<LuiLuiRelationAttributeEntity> {

    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCR_FORMATTED", length = KSEntityConstants.EXTRA_LONG_TEXT_LENGTH)
    private String formatted;
    @Column(name = "DESCR_PLAIN", length = KSEntityConstants.EXTRA_LONG_TEXT_LENGTH)
    private String plain;
    @ManyToOne
    @JoinColumn(name = "LUI_ID")
    private LuiEntity lui;
    @ManyToOne
    @JoinColumn(name = "RELATED_LUI_ID")
    private LuiEntity relatedLui;
    @Column(name = "LUILUI_RELTN_TYPE")
    private String luiLuiRelationType;
    @Column(name = "LUILUI_RELTN_STATE")
    private String luiLuiRelationState;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EFF_DT")
    private Date effectiveDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EXPIR_DT")
    private Date expirationDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<LuiLuiRelationAttributeEntity> attributes = new HashSet<LuiLuiRelationAttributeEntity>();

    public LuiLuiRelationEntity() {
    }

    public LuiLuiRelationEntity(LuiLuiRelation luiLuiRelation) {
        this.setId(luiLuiRelation.getId());
        this.setLuiLuiRelationType(luiLuiRelation.getTypeKey());
        fromDto(luiLuiRelation);
    }

    public List<Object> fromDto(LuiLuiRelation luiLuiRelation) {
        List<Object> orphansToDelete = new ArrayList<Object>();

        this.setEffectiveDate(luiLuiRelation.getEffectiveDate());
        this.setExpirationDate(luiLuiRelation.getExpirationDate());
        this.setLuiLuiRelationState(luiLuiRelation.getStateKey());
        this.setName(luiLuiRelation.getName());
        if (luiLuiRelation.getDescr() == null) {
            this.setDescrFormatted(null);
            this.setDescrPlain(null);
        } else {
            this.setDescrFormatted(luiLuiRelation.getDescr().getFormatted());
            this.setDescrPlain(luiLuiRelation.getDescr().getPlain());
        }

        //Attributes
        orphansToDelete.addAll(TransformUtility.mergeToEntityAttributes(LuiLuiRelationAttributeEntity.class, luiLuiRelation, this));

        return orphansToDelete;
     }

    public LuiLuiRelationInfo toDto() {
        LuiLuiRelationInfo info = new LuiLuiRelationInfo();
        info.setId(getId());
        if (lui != null) {
            info.setLuiId(lui.getId());
        }
        if (relatedLui != null) {
            info.setRelatedLuiId(relatedLui.getId());
        }
        info.setEffectiveDate(effectiveDate);
        info.setExpirationDate(expirationDate);
        info.setStateKey(luiLuiRelationState);
        info.setTypeKey(luiLuiRelationType);
        info.setMeta(super.toDTO());
        info.setDescr(new RichTextHelper().toRichTextInfo(plain, formatted));

        //Attributes
        info.setAttributes(TransformUtility.toAttributeInfoList(this));

        return info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescrFormatted() {
        return formatted;
    }

    public void setDescrFormatted(String formatted) {
        this.formatted = formatted;
    }

    public String getDescrPlain() {
        return plain;
    }

    public void setDescrPlain(String plain) {
        this.plain = plain;
    }

    public LuiEntity getLui() {
        return lui;
    }

    public void setLui(LuiEntity lui) {
        this.lui = lui;
    }

    public LuiEntity getRelatedLui() {
        return relatedLui;
    }

    public void setRelatedLui(LuiEntity relatedLui) {
        this.relatedLui = relatedLui;
    }

    public String getLuiLuiRelationType() {
        return luiLuiRelationType;
    }

    public void setLuiLuiRelationType(String luiLuiRelationType) {
        this.luiLuiRelationType = luiLuiRelationType;
    }

    public String getLuiLuiRelationState() {
        return luiLuiRelationState;
    }

    public void setLuiLuiRelationState(String luiLuiRelationState) {
        this.luiLuiRelationState = luiLuiRelationState;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setAttributes(Set<LuiLuiRelationAttributeEntity> attributes) {
        this.attributes = attributes;

    }

    public Set<LuiLuiRelationAttributeEntity> getAttributes() {
        return attributes;
    }
}
