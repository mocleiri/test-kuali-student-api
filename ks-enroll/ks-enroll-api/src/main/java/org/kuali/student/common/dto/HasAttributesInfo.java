/*
 * Copyright 2010 The Kuali Foundation Licensed under the Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.osedu.org/licenses/ECL-2.0 Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and limitations under the License.
 */

package org.kuali.student.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.kuali.student.common.infc.Attribute;
import org.kuali.student.common.infc.HasAttributes;

@SuppressWarnings("serial")
@XmlTransient
public abstract class HasAttributesInfo implements HasAttributes, Serializable {

    @XmlElement
    protected final List<AttributeInfo> attributes;

    protected HasAttributesInfo() {
        attributes = null;
    }

    protected HasAttributesInfo(HasAttributes builder) {
        attributes = new ArrayList<AttributeInfo>();

        AttributeInfo.Builder attBuilder = new AttributeInfo.Builder();
        for (Attribute att : builder.getAttributes()) {
            attBuilder.setKey(att.getKey());
            attBuilder.setValue(att.getValue());
            attBuilder.setId(att.getId());
            attributes.add(attBuilder.build());
        }
    }

    /**
     * @return the attributes
     */
    @Override
    public List<AttributeInfo> getAttributes() {
        return attributes;
    }

    public static class Builder implements HasAttributes {
        private List<? extends Attribute> attributes = new ArrayList<AttributeInfo>();

        public Builder() {}

        public Builder(HasAttributes hasAtts) {
            this.attributes = hasAtts.getAttributes();
        }

        @Override
        public List<? extends Attribute> getAttributes() {
            return attributes;
        }

        public void setAttributes(List<? extends Attribute> attributes) {
            this.attributes = attributes;
        }
    }
}
