package org.kuali.student.enrollment.class1.lui.model;

import org.kuali.student.enrollment.lui.dto.LuiInfo;
import org.kuali.student.enrollment.lui.dto.LuiInstructorInfo;
import org.kuali.student.enrollment.lui.infc.Lui;
import org.kuali.student.enrollment.lui.infc.LuiInstructor;
import org.kuali.student.r2.common.dto.AttributeInfo;
import org.kuali.student.r2.common.entity.AttributeOwner;
import org.kuali.student.r2.common.entity.MetaEntity;
import org.kuali.student.r2.common.infc.Attribute;
import org.kuali.student.r2.common.model.StateEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "KSEN_LUI")
public class LuiEntity extends MetaEntity implements AttributeOwner<LuiAttributeEntity>{
    @Column(name = "NAME")
    private String name;
    
	@Column(name = "LUI_CODE")
    private String luiCode;

	//TODO: use CLU?
//	@ManyToOne
//	@JoinColumn(name = "CLU_ID")
//	private Clu clu;
    
	@Column(name = "CLU_ID")
	private String cluId;
	
//    @ManyToOne
//    @JoinColumn(name="ATP_ID")
//    private AtpEntity atp;

	@Column(name="ATP_ID")
	private String atpKey;
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RT_DESCR_ID")
    private LuiRichTextEntity descr;   
    
    @ManyToOne(optional=false)
    @JoinColumn(name = "TYPE_ID")
    private LuiTypeEntity luiType;

    @ManyToOne(optional=false)
    @JoinColumn(name = "STATE_ID")
    private StateEntity luiState;
    
	@Column(name = "MAX_SEATS")
	private Integer maxSeats;
	
	@Column(name = "MIN_SEATS")
	private Integer minSeats;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EFF_DT")
	private Date effectiveDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXP_DT")
	private Date expirationDate;

	@Column(name = "STDY_SUBJ_AREA")
    private String studySubjectArea;
	
	@OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "KSEN_LUI_JN_LUI_INSTR", joinColumns = @JoinColumn(name = "LUI_ID"), inverseJoinColumns = @JoinColumn(name = "LUI_INSTR_ID"))
    private List<LuiInstructorEntity> instructors;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<LuiAttributeEntity> attributes;
    
    public LuiEntity (){}
    
    public LuiEntity(Lui lui){
    	super(lui); 
        try {   
        	this.setId(lui.getId());
        	this.setName(lui.getName());
        	this.setLuiCode(lui.getLuiCode());
        	this.setAtpKey(lui.getAtpKey());
        	this.setCluId(lui.getCluId());
        	this.setMaxSeats(lui.getMaximumEnrollment());
        	this.setMinSeats(lui.getMinimumEnrollment());
        	this.setStudySubjectArea(lui.getStudySubjectArea());
        	if(lui.getEffectiveDate() != null)
        		this.setEffectiveDate(lui.getEffectiveDate());
        	if(lui.getExpirationDate() != null)
        		this.setExpirationDate(lui.getExpirationDate());
        	
	        if(lui.getDescr() != null)
	            this.setDescr(new LuiRichTextEntity(lui.getDescr()));
	        
	        this.setInstructors(new ArrayList<LuiInstructorEntity>());
	        if (null != lui.getInstructors()){
	        	for(LuiInstructor instructor : lui.getInstructors()){
	        		LuiInstructorEntity instrEntity = new LuiInstructorEntity(instructor);
	        		this.getInstructors().add(instrEntity);
	        	}
	        }
	        
	        this.setAttributes(new ArrayList<LuiAttributeEntity>());
	        if (null != lui.getAttributes()) {
	            for (Attribute att : lui.getAttributes()) {
	            	LuiAttributeEntity attEntity = new LuiAttributeEntity(att);
	                this.getAttributes().add(attEntity);
	            }
	        }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public LuiInfo toDto(){
    	LuiInfo obj = new LuiInfo();
    	obj.setId(getId());
    	obj.setName(name);
    	obj.setLuiCode(luiCode);
    	obj.setAtpKey(atpKey);
    	obj.setCluId(cluId);
    	obj.setStudySubjectArea(studySubjectArea);
    	if(maxSeats != null)
    		obj.setMaximumEnrollment(maxSeats);
    	if(minSeats != null)
    		obj.setMinimumEnrollment(minSeats);
        obj.setEffectiveDate(effectiveDate);
        obj.setExpirationDate(expirationDate);
        if(luiType != null)
            obj.setTypeKey(luiType.getId());
        if(luiState != null)
            obj.setStateKey(luiState.getId());
        obj.setMeta(super.toDTO());
        if(descr != null)
            obj.setDescr(descr.toDto());

        List<LuiInstructorInfo> instructors = new ArrayList<LuiInstructorInfo>();
        for (LuiInstructorEntity instructor : getInstructors()) {
        	LuiInstructorInfo instructorInfo = instructor.toDto();
        	instructors.add(instructorInfo);
        }
        obj.setInstructors(instructors);
        
        List<AttributeInfo> atts = new ArrayList<AttributeInfo>();
        for (LuiAttributeEntity att : getAttributes()) {
            AttributeInfo attInfo = att.toDto();
            atts.add(attInfo);
        }
        obj.setAttributes(atts);
        
        return obj;
    }
    
    public String getLuiCode() {
        return luiCode;
    }

    public void setLuiCode(String luiCode) {
        this.luiCode = luiCode;
    }

    public Integer getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(Integer maxSeats) {
        this.maxSeats = maxSeats;
    }

    public Integer getMinSeats() {
		return minSeats;
	}

	public void setMinSeats(Integer minSeats) {
		this.minSeats = minSeats;
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

	public String getCluId() {
		return cluId;
	}

	public void setCluId(String cluId) {
		this.cluId = cluId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAtpKey() {
		return atpKey;
	}

	public void setAtpKey(String atpKey) {
		this.atpKey = atpKey;
	}

	public LuiRichTextEntity getDescr() {
		return descr;
	}

	public void setDescr(LuiRichTextEntity descr) {
		this.descr = descr;
	}

	public LuiTypeEntity getLuiType() {
		return luiType;
	}

	public void setLuiType(LuiTypeEntity luiType) {
		this.luiType = luiType;
	}

	public StateEntity getLuiState() {
		return luiState;
	}

	public void setLuiState(StateEntity luiState) {
		this.luiState = luiState;
	}

	public String getStudySubjectArea() {
		return studySubjectArea;
	}

	public void setStudySubjectArea(String studySubjectArea) {
		this.studySubjectArea = studySubjectArea;
	}

	public List<LuiInstructorEntity> getInstructors() {
		return instructors;
	}

	public void setInstructors(List<LuiInstructorEntity> instructors) {
		this.instructors = instructors;
	}

	@Override
	public void setAttributes(List<LuiAttributeEntity> attributes) {
		this.attributes = attributes;			
	}

	@Override
	public List<LuiAttributeEntity> getAttributes() {
		return attributes;
	}
    
    
}
