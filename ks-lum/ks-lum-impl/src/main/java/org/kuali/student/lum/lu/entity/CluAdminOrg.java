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

package org.kuali.student.lum.lu.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.kuali.student.common.util.UUIDHelper;
import org.kuali.student.core.entity.AttributeOwner;

/**
 * This is a description of what this class does - hjohnson don't forget to fill this in. 
 * 
 * @author Kuali Student Team (kuali-student@googlegroups.com)
 *
 */

@Entity
@Table(name = "KSLU_CLU_ADMIN_ORG")

public class CluAdminOrg implements AttributeOwner<CluAdminOrgAttribute>  {
    
    @Id
    @Column(name = "ID")
    private String id;
    
    @Column(name = "ORG_ID")
    private String orgId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<CluAdminOrgAttribute> attributes;

    @PrePersist
    public  void prePersist() {
        this.id = UUIDHelper.genStringUUID(this.id);
    }
    
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    
    public List<CluAdminOrgAttribute> getAttributes() {
        if (attributes == null) {
            attributes = new ArrayList<CluAdminOrgAttribute>();
        }
        return attributes;
    }

    public void setAttributes(List<CluAdminOrgAttribute> attributes) {
        this.attributes = attributes;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
