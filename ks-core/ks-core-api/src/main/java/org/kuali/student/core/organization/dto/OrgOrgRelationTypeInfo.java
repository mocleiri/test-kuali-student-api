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

package org.kuali.student.core.organization.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.kuali.student.common.dto.TypeInfo;

/**
 *Information about an organization to organization relationship type.
 */ 
@XmlAccessorType(XmlAccessType.FIELD)
public class OrgOrgRelationTypeInfo extends TypeInfo {

    private static final long serialVersionUID = 1L;

    @XmlElement
    private String revName;

    @XmlElement
    private String revDesc;

    @XmlElement
    private String orgHierarchyKey;

    /**
     * Name for the reverse of the organization to organization relationship type. This is primarily to be used by developers and may end up translated in the end system.
     */
    public String getRevName() {
        return revName;
    }

    public void setRevName(String revName) {
        this.revName = revName;
    }

    /**
     * Narrative description of the reverse of the organization to organization relationship type.
     */
    public String getRevDesc() {
        return revDesc;
    }

    public void setRevDesc(String revDesc) {
        this.revDesc = revDesc;
    }

    /**
     * The organization hierarchy relationships of this type belong to.
     */
    public String getOrgHierarchyKey() {
        return orgHierarchyKey;
    }

    public void setOrgHierarchyKey(String orgHierarchyKey) {
        this.orgHierarchyKey = orgHierarchyKey;
    }
}
