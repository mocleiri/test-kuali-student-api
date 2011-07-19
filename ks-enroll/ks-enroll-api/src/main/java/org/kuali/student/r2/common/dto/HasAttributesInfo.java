/*
 * Copyright 2010 The Kuali Foundation Licensed under the Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.osedu.org/licenses/ECL-2.0 Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and limitations under the License.
 */

package org.kuali.student.r2.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.kuali.student.r2.common.infc.Attribute;
import org.kuali.student.r2.common.infc.HasAttributes;

@SuppressWarnings("serial")
@XmlTransient
public abstract class HasAttributesInfo implements HasAttributes, Serializable {

	@XmlElement
	private List<AttributeInfo> attributes;

	public HasAttributesInfo() {
		attributes = null;
	}

	public HasAttributesInfo(HasAttributes hasAtts) {
		if (null != hasAtts) {
			attributes = new ArrayList<AttributeInfo>();

			if (null != hasAtts.getAttributes()) {
				for (Attribute att : hasAtts.getAttributes()) {
					attributes.add(new AttributeInfo (att));
				}
			}
		}
	}

	/**
	 * @return the attributes
	 */
	@Override
	public List<AttributeInfo> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributeInfo> attributes) {

		this.attributes = attributes;

	}
}
