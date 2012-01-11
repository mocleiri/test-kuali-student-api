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

package org.kuali.student.r2.core.enumerationmanagement.dto;

import org.kuali.student.r2.common.dto.KeyEntityInfo;
import org.kuali.student.r2.core.enumerationmanagement.infc.Enumeration;

import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnumerationInfo", propOrder = {"key", "typeKey", "stateKey",
        "name", "descr", "contextDescriptors", "meta", "attributes", "_futureElements"})
public class EnumerationInfo extends KeyEntityInfo implements Enumeration, Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement
    private List<String> contextDescriptors;
    @XmlAnyElement
    private List<Element> _futureElements;

    @Override
    public List<String> getContextDescriptors() {
        if (contextDescriptors == null) {
            contextDescriptors = new ArrayList<String>();
        }
        return contextDescriptors;
    }

    public void setContextDescriptors(List<String> contextDescriptors) {
        this.contextDescriptors = contextDescriptors;
    }
}
