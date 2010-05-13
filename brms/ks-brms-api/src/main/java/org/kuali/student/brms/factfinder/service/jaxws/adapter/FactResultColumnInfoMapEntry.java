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

package org.kuali.student.brms.factfinder.service.jaxws.adapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.kuali.student.brms.factfinder.dto.FactResultColumnInfo;

@XmlAccessorType(XmlAccessType.FIELD)
public class FactResultColumnInfoMapEntry {
	@XmlAttribute
	private String key;
	
    @XmlElement
	private FactResultColumnInfo value;

    public FactResultColumnInfoMapEntry() {}

	public FactResultColumnInfoMapEntry(String key, FactResultColumnInfo value) {
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return this.key;
	}
	
	public FactResultColumnInfo getValue() {
		return this.value;
	}
}
