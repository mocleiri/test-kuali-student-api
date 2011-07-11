package org.kuali.student.enrollment.class1.hold.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.kuali.student.enrollment.hold.dto.RestrictionInfo;
import org.kuali.student.enrollment.hold.infc.Restriction;
import org.kuali.student.r2.common.dto.AttributeInfo;
import org.kuali.student.r2.common.entity.AttributeOwner;
import org.kuali.student.r2.common.entity.MetaEntity;
import org.kuali.student.r2.common.infc.Attribute;
import org.kuali.student.r2.common.model.StateEntity;

@Entity
@Table(name = "KSEN_RESTRICTION")
public class RestrictionEntity extends MetaEntity implements AttributeOwner<RestrictionAttributeEntity>{
    @Column(name = "NAME")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RT_DESCR_ID")
    private HoldRichTextEntity descr;   
   
    @ManyToOne(optional=false)
    @JoinColumn(name = "TYPE_ID")
    private HoldTypeEntity restrictionType;

    @ManyToOne(optional=false)
    @JoinColumn(name = "STATE_ID")
    private StateEntity restrictionState;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<RestrictionAttributeEntity> attributes;

    public RestrictionEntity(){}
    
    public RestrictionEntity(Restriction restriction){
        super(restriction); 
        try {
	        this.setId(restriction.getKey());
	        this.setName(restriction.getName());
	        
	        if(restriction.getDescr() != null) {
	            this.setDescr(new HoldRichTextEntity(restriction.getDescr()));
	        }
	        this.setAttributes(new ArrayList<RestrictionAttributeEntity>());
	        if (null != restriction.getAttributes()) {
	            for (Attribute att : restriction.getAttributes()) {
	            	RestrictionAttributeEntity attEntity = new RestrictionAttributeEntity(att);
	                this.getAttributes().add(attEntity);
	            }
	        }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public RestrictionInfo toDto() {
    	RestrictionInfo obj = new RestrictionInfo();
    	obj.setKey(getId());
    	obj.setName(name);
        if(restrictionType != null)
            obj.setTypeKey(restrictionType.getId());
        if(restrictionState != null)
            obj.setStateKey(restrictionState.getId());
        obj.setMeta(super.toDTO());
        if(descr != null)
            obj.setDescr(descr.toDto());

        List<AttributeInfo> atts = new ArrayList<AttributeInfo>();
        for (RestrictionAttributeEntity att : getAttributes()) {
            AttributeInfo attInfo = att.toDto();
            atts.add(attInfo);
        }
        obj.setAttributes(atts);
        
        return obj;
    }

	public HoldTypeEntity getRestrictionType() {
		return restrictionType;
	}

	public void setRestrictionType(HoldTypeEntity restrictionType) {
		this.restrictionType = restrictionType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HoldRichTextEntity getDescr() {
		return descr;
	}

	public void setDescr(HoldRichTextEntity descr) {
		this.descr = descr;
	}

	public StateEntity getRestrictionState() {
		return restrictionState;
	}

	public void setRestrictionState(StateEntity restrictionState) {
		this.restrictionState = restrictionState;
	}

	@Override
	public void setAttributes(List<RestrictionAttributeEntity> attributes) {
		this.attributes = attributes;		
	}

	@Override
	public List<RestrictionAttributeEntity> getAttributes() {
		 return attributes;
	}

}
