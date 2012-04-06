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


package org.kuali.student.lum.lu.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.2
 * Thu Jan 21 10:05:23 PST 2010
 * Generated source version: 2.2
 */

@XmlRootElement(name = "getClusByRelation", namespace = "http://student.kuali.org/wsdl/lu")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getClusByRelation", namespace = "http://student.kuali.org/wsdl/lu", propOrder = {"relatedCluId","luLuRelationType"})

public class GetClusByRelation {

    @XmlElement(name = "relatedCluId")
    private java.lang.String relatedCluId;
    @XmlElement(name = "luLuRelationType")
    private java.lang.String luLuRelationType;

    public java.lang.String getRelatedCluId() {
        return this.relatedCluId;
    }

    public void setRelatedCluId(java.lang.String newRelatedCluId)  {
        this.relatedCluId = newRelatedCluId;
    }

    public java.lang.String getLuLuRelationType() {
        return this.luLuRelationType;
    }

    public void setLuLuRelationType(java.lang.String newLuLuRelationType)  {
        this.luLuRelationType = newLuLuRelationType;
    }

}

