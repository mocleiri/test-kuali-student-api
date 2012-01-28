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
import org.kuali.student.r2.core.class1.state.model.StateEntity;
import org.kuali.student.r2.core.atp.dto.MilestoneInfo;
import org.kuali.student.r2.core.atp.infc.Milestone;

@Entity
@Table(name = "KSEN_MSTONE")
public class MilestoneEntity extends MetaEntity implements AttributeOwner<MilestoneAttributeEntity> {

    @Column(name = "NAME")
    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "RT_DESCR_ID")
    private AtpRichTextEntity descr;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_DT")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_DT")
    private Date endDate;
    
    @ManyToOne
    @JoinColumn(name = "MILESTONE_TYPE")
    private String milestoneType;

    @ManyToOne
    @JoinColumn(name = "MILESTONE_STATE")
    private String milestoneState;
    
    @Column(name="IS_ALL_DAY")
    private boolean isAllDay;
    
    @Column(name="IS_DATE_RANGE")
    private boolean isDateRange;

    @Column(name="IS_RELATIVE")
    private boolean isRelative;

    @ManyToOne
    @JoinColumn(name="RELATIVE_MILESTONE_ID")
    private MilestoneEntity relativeAnchorMilestone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<MilestoneAttributeEntity> attributes;

    public MilestoneEntity() {
    }

    public MilestoneEntity(Milestone milestone) {
        super(milestone);
        this.setId(milestone.getId());
        this.setAllDay(milestone.getIsAllDay());
        this.setDateRange(milestone.getIsDateRange());
        this.setDescr(new AtpRichTextEntity(milestone.getDescr()));
        StateEntity state = new StateEntity();
        state.setId(milestone.getStateKey());
        this.setMilestoneState(milestone.getStateKey());
        this.setMilestoneType(milestone.getTypeKey());
        this.name = milestone.getName();
        this.descr = null != milestone.getDescr() ? new AtpRichTextEntity(milestone.getDescr()) : null;
        this.startDate = null != milestone.getStartDate() ? new Date(milestone.getStartDate().getTime()) : null;
        this.endDate = null != milestone.getEndDate() ? new Date(milestone.getEndDate().getTime()) : null;
        this.isRelative = (null != milestone.getIsRelative()) ? milestone.getIsRelative() : false;

        this.setAttributes(new ArrayList<MilestoneAttributeEntity>());
        if (null != milestone.getAttributes()) {
            for (Attribute att : milestone.getAttributes()) {
                this.getAttributes().add(new MilestoneAttributeEntity(att, this));
            }
        }
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AtpRichTextEntity getDescr() {
        return descr;
    }

    public void setDescr(AtpRichTextEntity descr) {
        this.descr = descr;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getMilestoneType() {
        return milestoneType;
    }

    public void setMilestoneType(String milestoneType) {
        this.milestoneType = milestoneType;
    }

    public String getMilestoneState() {
        return milestoneState;
    }

    public void setMilestoneState(String milestoneState) {
        this.milestoneState = milestoneState;
    }

    public boolean isAllDay() {
        return isAllDay;
    }

    public void setAllDay(boolean isAllDay) {
        this.isAllDay = isAllDay;
    }

    public boolean isDateRange() {
        return isDateRange;
    }

    public void setDateRange(boolean isDateRange) {
        this.isDateRange = isDateRange;
    }

    public boolean isRelative() {
        return isRelative;
    }

    public void setRelative(boolean relative) {
        isRelative = relative;
    }

    public MilestoneEntity getRelativeAnchorMilestone() {
        return relativeAnchorMilestone;
    }

    public void setRelativeAnchorMilestone(MilestoneEntity relativeAnchorMilestone) {
        this.relativeAnchorMilestone = relativeAnchorMilestone;
    }

    @Override
    public void setAttributes(List<MilestoneAttributeEntity> attributes) {
       this.attributes = attributes;
        
    }

    @Override
    public List<MilestoneAttributeEntity> getAttributes() {
        return attributes;
    }
    
    public MilestoneInfo toDto() {
        MilestoneInfo info = new MilestoneInfo();
        
        info.setId(getId());
        info.setName(getName());
        info.setTypeKey(milestoneType);
        info.setStateKey(milestoneState);
        info.setStartDate(getStartDate());
        info.setEndDate(getEndDate());
        info.setIsAllDay(isAllDay());
        info.setIsDateRange(isDateRange());
        info.setIsRelative(isRelative);
        info.setRelativeAnchorMilestoneId(null != relativeAnchorMilestone ? relativeAnchorMilestone.getId() : null);
        info.setMeta(super.toDTO());
        info.setDescr((getDescr()!= null) ? getDescr().toDto() : null);
        
        if(getAttributes() != null) {
            List<AttributeInfo> atts = new ArrayList<AttributeInfo>(getAttributes().size());
            for (MilestoneAttributeEntity att : getAttributes()) {
                AttributeInfo attInfo = att.toDto();
                atts.add(attInfo);
            }
            
            info.setAttributes(atts);
        }
        
        return info;
    }

}
