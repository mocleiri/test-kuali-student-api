/*
 * Copyright 2012 The Kuali Foundation Licensed under the
 *  Educational Community License, Version 2.0 (the "License"); you may
 *  not use this file except in compliance with the License. You may
 *  obtain a copy of the License at
 *
 *   http://www.osedu.org/licenses/ECL-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an "AS IS"
 *  BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 *  or implied. See the License for the specific language governing
 *  permissions and limitations under the License.
 */

package org.kuali.student.r2.core.scheduling.dto;

import org.kuali.student.r2.core.scheduling.infc.ScheduleRespItem;
//import org.w3c.dom.Element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Version 2.0
 * @Author Sri komandur@uw.edu
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ScheduleRespItemInfo", propOrder = {"id", "scheduleIds", "scheduleRequestItemId", "scheduleResponseId"})//, "_futureElements" }) TODO KSCM-372: Non-GWT translatable code
public class ScheduleRespItemInfo implements ScheduleRespItem, Serializable {

    @XmlElement
    private String id;
    @XmlElement
    private List<String> scheduleIds;
    @XmlElement
    private String scheduleRequestItemId;
    @XmlElement
    private String scheduleResponseId;
//    TODO KSCM-372: Non-GWT translatable code
//    @XmlAnyElement
//    private List<Element> _futureElements;

    public ScheduleRespItemInfo() {
    }

    public ScheduleRespItemInfo(ScheduleRespItem scheduleRespItem) {
        if (null != scheduleRespItem) {
            this.id = scheduleRespItem.getId();
            this.scheduleIds = new ArrayList<String>(scheduleRespItem.getScheduleIds());
            this.scheduleRequestItemId = scheduleRespItem.getScheduleRequestItemId();
            this.scheduleResponseId = scheduleRespItem.getScheduleRespId();
        }
    }

    @Override
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public List<String> getScheduleIds() {
        if (null == this.scheduleIds) {
            return new ArrayList<String>();
        }
        else {
            return this.scheduleIds;
        }
    }

    public void setScheduleIds(List<String> scheduleIds) {
        this.scheduleIds = scheduleIds;
    }

    @Override
    public String getScheduleRequestItemId() {
        return this.scheduleRequestItemId;
    }

    public void setScheduleRequestItemId(String scheduleRequestItemId) {
        this.scheduleRequestItemId = scheduleRequestItemId;
    }

    @Override
    public String getScheduleRespId() {
        return this.scheduleResponseId;
    }

    public void setScheduleResponseId(String scheduleResponseId) {
        this.scheduleResponseId = scheduleResponseId;
    }
}