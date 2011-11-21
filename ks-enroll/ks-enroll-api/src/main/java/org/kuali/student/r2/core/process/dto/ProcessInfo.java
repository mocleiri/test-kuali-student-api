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

package org.kuali.student.r2.core.process.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.kuali.student.r2.common.dto.KeyEntityInfo;
import org.kuali.student.r2.core.process.infc.Process;

import org.w3c.dom.Element;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcessInfo", propOrder = { "key", "typeKey", "stateKey", 
                "name", "descr", "meta", "attributes",
		"_futureElements" })

public class ProcessInfo extends KeyEntityInfo 
    implements Process, Serializable {

    private static final long serialVersionUID = 1L;
    
    @XmlElement 
    private String orgId;

    @XmlAnyElement
    private List<Element> _futureElements;
    
    public ProcessInfo() {
    }

    /**
     * Constructs a new ProcessInfo from another Process.
     * 
     * @param process the Process to copy
     */
    public ProcessInfo(Process process) {
        super(process);

        if (process != null) {
            this.orgId = process.getOrgId();
        }
    }

    @Override
    public String getOrgId() {
        return this.orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
