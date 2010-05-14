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

package org.kuali.student.brms.rulemanagement.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


/**
 * Contains meta data about the WHEN part of Drool rules. The Rule Proposition consists of left hand side, operator and right
 * hand side of a given rule.
 * 
 * @author Kuali Student Team (kamal.kuali@gmail.com)
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class RulePropositionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement
    private String name;
    
    @XmlElement
    private String desc;
    
    @XmlElement
    private String successMessage;
    
    @XmlElement
    private String failureMessage;
    
    @XmlElement
    private String comparisonDataTypeKey;
    
    @XmlElement
    private LeftHandSideInfo leftHandSide;
    
    @XmlElement
    private String comparisonOperatorTypeKey;
    
    @XmlElement
    private RightHandSideInfo rightHandSide;


    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public final void setName(String name) {
        this.name = name;
    }

    /**
     * @return the desc
     */
    public final String getDesc() {
        return desc;
    }

    /**
     * @param desc
     *            the desc to set
     */
    public final void setDesc(String description) {
        this.desc = description;
    }

    /**
     * @return the leftHandSide
     */
    public final LeftHandSideInfo getLeftHandSide() {
        return leftHandSide;
    }

    /**
     * @param leftHandSide
     *            the leftHandSide to set
     */
    public final void setLeftHandSide(LeftHandSideInfo leftHandSide) {
        this.leftHandSide = leftHandSide;
    }

    /**
     * @return the rightHandSide
     */
    public final RightHandSideInfo getRightHandSide() {
        return rightHandSide;
    }

    /**
     * @param rightHandSide
     *            the rightHandSide to set
     */
    public final void setRightHandSide(RightHandSideInfo rightHandSide) {
        this.rightHandSide = rightHandSide;
    }

    /**
     * Returns the success message.
     * 
     * @return Success message
     */
	public String getSuccessMessage() {
		return successMessage;
	}

	/**
     * Sets the success message.
	 * 
	 * @param successMessage Success message
	 */
	public void setSuccessMessage(String successMessage) {
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
     * @return the comparisonDataTypeKey
     */
    public String getComparisonDataTypeKey() {
        return comparisonDataTypeKey;
    }

    /**
     * @param comparisonDataTypeKey the comparisonDataTypeKey to set
     */
    public void setComparisonDataTypeKey(String comparisonDataTypeKey) {
        this.comparisonDataTypeKey = comparisonDataTypeKey;
    }

    /**
     * @return the comparisonOperatorTypeKey
     */
    public String getComparisonOperatorTypeKey() {
        return comparisonOperatorTypeKey;
    }

    /**
     * @param comparisonOperatorTypeKey the comparisonOperatorTypeKey to set
     */
    public void setComparisonOperatorTypeKey(String comparisonOperatorTypeKey) {
        this.comparisonOperatorTypeKey = comparisonOperatorTypeKey;
    }
}
