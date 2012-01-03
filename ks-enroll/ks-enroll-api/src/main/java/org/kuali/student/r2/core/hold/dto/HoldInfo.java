/*
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
package org.kuali.student.r2.core.hold.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.kuali.student.r2.core.hold.infc.Hold;
import org.kuali.student.r2.common.dto.IdEntityInfo;
import org.w3c.dom.Element;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HoldInfo", propOrder = {"id", "typeKey", "stateKey", "name",
                "descr", "issueKey", "personId", "isWarning", "isOverridable",
                "effectiveDate", "releasedDate", "meta", "attributes",
                "_futureElements"})

public class HoldInfo 
    extends IdEntityInfo 
    implements Hold, Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement
    private String personId;

    @XmlElement
    private String issueKey;

    @XmlElement
    private Boolean isWarning;

    @XmlElement
    private Boolean isOverridable;

    @XmlElement
    private Date effectiveDate;

    @XmlElement
    private Date releasedDate;

    @XmlAnyElement
    private List<Element> _futureElements;

    public HoldInfo() {
        super();
        personId = null;
        isWarning = false;
        isOverridable = false;
        issueKey = null;
        effectiveDate = null;
        releasedDate = null;
        _futureElements = null;
    }

    public HoldInfo(Hold hold) {
        super(hold);
        if (null != hold) {
            this.personId = hold.getPersonId();
            this.isWarning = hold.getIsWarning();
            this.isOverridable = hold.getIsOverridable();
            this.issueKey = hold.getIssueKey();
            if (hold.getEffectiveDate() != null) {
                this.effectiveDate = new Date(hold.getEffectiveDate().getTime());
            }
            if (hold.getReleasedDate() != null) {
                this.releasedDate = new Date(hold.getReleasedDate().getTime());
            }
        }
    }

    @Override
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Override
    public String getIssueKey() {
        return issueKey;
    }

    public void setIssueKey(String issueKey) {
        this.issueKey = issueKey;
    }

    @Override
    public Boolean getIsWarning() {
        return isWarning;
    }

    public void setIsWarning(Boolean isWarning) {
        this.isWarning = isWarning;
    }

    @Override
    public Boolean getIsOverridable() {
        return isOverridable;
    }

    public void setIsOverridable(Boolean isOverridable) {
        this.isOverridable = isOverridable;
    }

    @Override
    public Date getEffectiveDate() {
        return effectiveDate != null ? new Date(effectiveDate.getTime()) : null;
    }

    public void setEffectiveDate(Date effectiveDate) {
        if (effectiveDate != null) {
            this.effectiveDate = new Date(effectiveDate.getTime());
        }
    }

    @Override
    public Date getReleasedDate() {
        return releasedDate != null ? new Date(releasedDate.getTime()) : null;
    }

    public void setReleasedDate(Date releasedDate) {
        if (releasedDate != null) {
            this.releasedDate = new Date(releasedDate.getTime());
        }
    }
}
