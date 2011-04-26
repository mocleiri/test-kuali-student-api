/*
 * Copyright 2010 The Kuali Foundation Licensed under the Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.osedu.org/licenses/ECL-2.0 Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and limitations under the License.
 */

package org.kuali.student.r2.common.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.kuali.student.r2.common.infc.ModelBuilder;
import org.kuali.student.r2.common.infc.State;
import org.w3c.dom.Element;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StateInfo", propOrder = {"key", "name", "descr", "effectiveDate", "expirationDate", "attributes", "_futureElements"})
public class StateInfo extends HasAttributesInfo implements State, Serializable {

    @XmlAttribute
    private final String key;

    @XmlElement
    private final String name;

    @XmlElement
    private final String descr;

    @XmlElement
    private final Date effectiveDate;

    @XmlElement
    private final Date expirationDate;

    @XmlAnyElement
    private final List<Element> _futureElements;

    private StateInfo() {
        key = null;
        name = null;
        descr = null;
        effectiveDate = null;
        expirationDate = null;
        _futureElements = null;
    }

    private StateInfo(State builder) {
        super(builder);
        this.key = builder.getKey();
        this.name = builder.getName();
        this.descr = builder.getDescr();
        this.effectiveDate = null != builder.getEffectiveDate() ? new Date(builder.getEffectiveDate().getTime()) : null;
        this.expirationDate = null != builder.getExpirationDate() ? new Date(builder.getExpirationDate().getTime()) : null;
        this._futureElements = null;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescr() {
        return descr;
    }

    @Override
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    @Override
    public Date getExpirationDate() {
        return expirationDate;
    }

    public static class Builder extends HasAttributesInfo.Builder implements ModelBuilder<StateInfo>, State {
        private String key;
        private String name;
        private String descr;
        private Date effectiveDate;
        private Date expirationDate;

        public Builder() {}

        public Builder(State stateInfo) {
            this.key = stateInfo.getKey();
            this.name = stateInfo.getName();
            this.descr = stateInfo.getDescr();
            this.effectiveDate = stateInfo.getEffectiveDate();
            this.expirationDate = stateInfo.getExpirationDate();
        }

        public StateInfo build() {
            return new StateInfo(this);
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescr() {
            return descr;
        }

        public void setDescr(String descr) {
            this.descr = descr;
        }

        public Date getEffectiveDate() {
            return effectiveDate;
        }

        public void setEffectiveDate(Date effectiveDate) {
            this.effectiveDate = effectiveDate;
        }

        public Date getExpirationDate() {
            return expirationDate;
        }

        public void setExpirationDate(Date expirationDate) {
            this.expirationDate = expirationDate;
        }

    }
}
