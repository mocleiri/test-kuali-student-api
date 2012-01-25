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

package org.kuali.student.r2.lum.lu.dto;

import org.kuali.student.r2.core.type.dto.TypeInfo;
import org.kuali.student.r2.lum.lu.infc.LuType;

import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LuTypeInfo", propOrder = {
        "instructionalFormat", "deliveryMethod", "_futureElements"})
public class LuTypeInfo extends TypeInfo implements LuType {

    private static final long serialVersionUID = 1L;

    @XmlElement
    private String instructionalFormat;

    @XmlElement
    private String deliveryMethod;

    @XmlAnyElement
    private List<Element> _futureElements;

    public LuTypeInfo() {

    }

    public LuTypeInfo(LuType luType) {
        super(luType);
        if (null != luType) {
            this.instructionalFormat = luType.getInstructionalFormat();
            this.deliveryMethod = luType.getDeliveryMethod();
        }
    }

    @Override
    public String getInstructionalFormat() {
        return instructionalFormat;
    }

    public void setInstructionalFormat(String instructionalFormat) {
        this.instructionalFormat = instructionalFormat;
    }

    @Override
    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    @Override
    public String toString() {
        return "LuTypeInfo[id=" + this.getKey() + "]";
    }
}
