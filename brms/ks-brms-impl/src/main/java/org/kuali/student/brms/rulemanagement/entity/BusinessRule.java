/**
 * Copyright 2010 The Kuali Foundation Licensed under the
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
 */

package org.kuali.student.brms.rulemanagement.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.kuali.student.brms.internal.common.entity.BusinessRuleStatus;
import org.kuali.student.brms.internal.common.entity.RuleElementType;
import org.kuali.student.common.util.UUIDHelper;
import org.kuali.student.core.entity.AttributeOwner;
import org.kuali.student.core.entity.MetaEntity;

/**
 * Contains meta data about a functional business rule. Since a functional business rule is composed of one or more Rule
 * Elements, this class is associated with one or more LUIPerson instances. The class also contains MetaEntity instance.
 * 
 * @author Kuali Student Team (kamal.kuali@gmail.com)
 */
@Entity
@Table(name = "BUS_RULE")
@NamedQueries({@NamedQuery(name = "BusinessRule.findIdsByBusinessRuleType", query = "SELECT c.id FROM BusinessRule c WHERE c.businessRuleType.businessRuleTypeKey = :businessRuleTypeKey"),
               @NamedQuery(name = "BusinessRule.findByBusinessRuleTypeAndAnchor", query = "SELECT c FROM BusinessRule c WHERE  c.anchor = :anchor AND c.businessRuleType.businessRuleTypeKey = :businessRuleTypeKey"),
               @NamedQuery(name = "BusinessRule.findByState", query = "SELECT c FROM BusinessRule c WHERE  c.anchor = :anchor AND c.businessRuleType.businessRuleTypeKey = :businessRuleTypeKey AND c.state = :state AND c.effectiveDate <= :today AND c.expirationDate >= :today"),               
               @NamedQuery(name = "BusinessRule.findAllVersions", query = "SELECT c FROM BusinessRule c WHERE  c.originalRuleId = :originalRuleId"),
               @NamedQuery(name = "BusinessRule.findAnchorsByAnchorType", query = "SELECT a.anchor FROM BusinessRule a WHERE a.businessRuleType.anchorTypeKey = :anchorTypeKey")})
public class BusinessRule extends MetaEntity implements AttributeOwner<BusinessRuleAttribute>  {
    
    public static final String PROPOSITION_LABEL_PREFIX = "P";
    public static final int INITIAL_PROPOSITION_PLACEHOLDER = 1;
    public static final String VALIDATION_OUTCOME = "validationResultOutcome";

    @Id
    @Column(name="ID")
    private String id;
    
    @Column(name="ORIG_ID")
    private String originalRuleId;
    
    @Column(name="NAME")
    private String name;
    
    @Column(name="DESCR")
    private String desc;

    @ManyToOne
    @JoinColumn(name = "PARENT_BUS_RULE_ID")
    private BusinessRuleType businessRuleType;
    
    @Column(name="ANCHOR", nullable = false)
    private String anchor;
    
    @Column(name="SUCCESS_MSG")
    private String successMessage;

    @Column(name="FAILURE_MSG")
    private String failureMessage;

    @Column(name="STATE", nullable = false)
    BusinessRuleStatus state;    

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EFF_DT")
    private Date effectiveDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EXPIR_DT")
    private Date expirationDate;    
        
    /* Repository Variables */
    @Column(name="COMPILED_ID")
    private String compiledId;

    @Column(name="REPO_SNAPSHOT_NAME")
    private String repositorySnapshotName;
    
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "businessRule", fetch = FetchType.EAGER)
    @OrderBy("ordinalPosition ASC")
    private List<RuleElement> ruleElements = new ArrayList<RuleElement>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "OWNER")
    private List<BusinessRuleAttribute> attributes;
    
    /**
     * Generates a HashMap of <unique alphabet character, proposition> pair from a functional business rule.
     * 
     * @param rule
     *            Functional business rule used to generate HashMap
     * @return Returns HashMap
     */
    public HashMap<String, RuleProposition> getRulePropositions() {

        HashMap<String, RuleProposition> propositions = new HashMap<String, RuleProposition>();

        int key = INITIAL_PROPOSITION_PLACEHOLDER;
        for (RuleElement ruleElement : ruleElements) {
            if (ruleElement.getBusinessRuleElemnetTypeKey() == RuleElementType.PROPOSITION) {
                propositions.put(PROPOSITION_LABEL_PREFIX + String.valueOf(key), ruleElement.getRuleProposition());
                key++;
            }
        }
        return propositions;
    }

    /**
     * Adds a new LUIPerson to the list of rule ruleEmelemnts that the business rule is composed of
     * 
     * @param ruleElement
     *            a new Rule Element to add to this business rule object
     */
    public void addRuleElement(RuleElement ruleElement) {
        ruleElements.add(ruleElement);
    }

    /**
     * AutoGenerate the Id
     */
    @PrePersist
    public void prePersist() {
        this.id = UUIDHelper.genStringUUID(this.id);
    }

    /**
     * @return the id
     */
    public final String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public final void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public final String getDesc() {
        return desc;
    }

    /**
     * @param description
     *            the description to set
     */
    public final void setDesc(String description) {
        this.desc = description;
    }

    /**
     * @return the successMessage
     */
    public final String getSuccessMessage() {
        return successMessage;
    }

    /**
     * @param successMessage
     *            the successMessage to set
     */
    public final void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    /**
     * @return the failureMessage
     */
    public final String getFailureMessage() {
        return failureMessage;
    }

    /**
     * @param failureMessage
     *            the failureMessage to set
     */
    public final void setFailureMessage(String failureMessage) {
        this.failureMessage = failureMessage;
    }

    /**
     * @return the compiledRuleId
     */
    public String getCompiledId() {
        return compiledId;
    }

    /**
     * @param compiledRuleId
     *            the compiledRuleId to set
     */
    public void setCompiledId(String compiledRuleId) {
        this.compiledId = compiledRuleId;
    }

    /**
     * @return the businessRuleType
     */
    public final BusinessRuleType getBusinessRuleType() {
        return businessRuleType;
    }

    /**
     * @param businessRuleTypeKey
     *            the businessRuleTypeKey to set
     */
    public final void setBusinessRuleType(BusinessRuleType businessRuleType) {
        this.businessRuleType = businessRuleType;
    }

    /**
     * @return the anchor
     */
    public final String getAnchor() {
        return anchor;
    }

    /**
     * @param anchor
     *            the anchor to set
     */
    public final void setAnchor(String anchor) {
        this.anchor = anchor;
    }

    /**
     * @return the ruleEmelemnts
     */
    public final List<RuleElement> getRuleElements() {
        if(null == ruleElements) {
            return new ArrayList<RuleElement>();
        }
        
        return ruleElements;
    }

    /**
     * @param ruleEmelemnts
     *            the ruleEmelemnts to set
     */
    public final void setRuleElements(List<RuleElement> elements) {
        this.ruleElements = elements;
    }

    /**
     * @return the originalRuleId
     */
    public String getOriginalRuleId() {
        return originalRuleId;
    }

    /**
     * @param originalRuleId the originalRuleId to set
     */
    public void setOriginalRuleId(String originalRuleId) {
        this.originalRuleId = originalRuleId;
    }

    /**
     * @return the repositorySnapshotName
     */
    public String getRepositorySnapshotName() {
        return repositorySnapshotName;
    }

    /**
     * @param repositorySnapshotName the repositorySnapshotName to set
     */
    public void setRepositorySnapshotName(String repositorySnapshotName) {
        this.repositorySnapshotName = repositorySnapshotName;
    }

    /**
     * @return the state
     */
    public BusinessRuleStatus getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(BusinessRuleStatus state) {
        this.state = state;
    }                
        
    /**
     * @return the effectiveDate
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * @param effectiveDate the effectiveDate to set
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * @return the expirationDate
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * @param expirationDate the expirationDate to set
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * @return the attributes
     */
    public List<BusinessRuleAttribute> getAttributes() {
        if(null == attributes) {
            return new ArrayList<BusinessRuleAttribute>();
        }
        
        return attributes;
    }

    /**
     * @param attributes the attributes to set
     */
    public void setAttributes(List<BusinessRuleAttribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
        sb.append("ID:" + this.id);
        sb.append("\nName:" + this.name);
        sb.append("\nState:" + this.state.toString());
        sb.append("\nBusinessRuleType:" + this.businessRuleType.getBusinessRuleTypeKey().toString());
        sb.append("\nAnchorType:" + this.businessRuleType.getAnchorTypeKey().toString());
        sb.append("\nAnchorValue:" + this.anchor);
        sb.append("\nEffectiveDate:" + this.getEffectiveDate());
        sb.append("\nExpirationDate:" + this.getExpirationDate());
        sb.append("\nOriginalRuleId:" + this.originalRuleId);
        sb.append("\nCompiledRuleId:" + this.compiledId);
        sb.append("\nRepositorySnapshotName:" + this.repositorySnapshotName);
        sb.append("\nRuleElement...");
        int i = 1;
        for(RuleElement element : ruleElements) {
            sb.append("\nElement" + i++ + ":" + element.toString());
        }
    
        
        return sb.toString();
    }
}
