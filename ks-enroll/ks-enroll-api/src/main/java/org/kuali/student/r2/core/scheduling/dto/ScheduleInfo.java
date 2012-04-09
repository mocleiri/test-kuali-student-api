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

import org.kuali.student.r2.common.dto.DateRangeInfo;
import org.kuali.student.r2.common.dto.IdEntityInfo;
import org.kuali.student.r2.common.infc.DateRange;
import org.kuali.student.r2.core.scheduling.infc.MeetingTime;
import org.kuali.student.r2.core.scheduling.infc.Schedule;
import org.w3c.dom.Element;

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
@XmlType(name = "ScheduleInfo", propOrder = {"id", "typeKey", "stateKey", "name", "descr",
        "atpId", "scheduleComponentIds", "blackoutDates", "blackoutMilestoneIds", "additionalMeetingTimes",
        "meta", "attributes", "_futureElements"})
public class ScheduleInfo extends IdEntityInfo implements Schedule, Serializable {

    @XmlElement
    private String atpId;
    @XmlElement
    private List<String> scheduleComponentIds;
    @XmlElement
    private List<DateRangeInfo> blackoutDates;
    @XmlElement
    private List<String> blackoutMilestoneIds;
    @XmlElement
    private List<MeetingTimeInfo> additionalMeetingTimes;
    @XmlAnyElement
    private List<Element> _futureElements;

    public ScheduleInfo() {
    }
    
    public ScheduleInfo(Schedule schedule) {
        if (null != schedule) {
            this.atpId = schedule.getAtpId();
            this.scheduleComponentIds = new ArrayList<String>(schedule.getScheduleComponentIds());
            this.blackoutDates = new ArrayList<DateRangeInfo>();
            for (DateRange dateRange : schedule.getBlackoutDates()) {
                this.blackoutDates.add(new DateRangeInfo(dateRange));
            }
            this.blackoutMilestoneIds = new ArrayList<String>(schedule.getScheduleComponentIds());
            this.additionalMeetingTimes = new ArrayList<MeetingTimeInfo>();
            for (MeetingTime meetingTime : schedule.getAdditionalMeetingTimes()) {
                this.additionalMeetingTimes.add(new MeetingTimeInfo(meetingTime));
            }
        }
    }

    @Override
    public String getAtpId() {
        return this.atpId;
    }

    public void setAtpId(String atpId) {
        this.atpId = atpId;
    }

    @Override
    public List<String> getScheduleComponentIds() {
        if (null == this.scheduleComponentIds) {
            return new ArrayList<String>();
        }
        else {
            return this.scheduleComponentIds;
        }
    }

    public void setScheduleComponentIds(List<String> scheduleComponentIds) {
        this.scheduleComponentIds = scheduleComponentIds;
    }

    @Override
    public List<DateRangeInfo> getBlackoutDates() {
        if (null == this.blackoutDates) {
            return new ArrayList<DateRangeInfo>();
        }
        else {
            return this.blackoutDates;
        }
    }

    public void setBlackoutDates(List<DateRangeInfo> blackoutDates) {
        this.blackoutDates = blackoutDates;
    }

    @Override
    public List<String> getBlackoutMilestoneIds() {
        if (null == this.blackoutMilestoneIds) {
            return new ArrayList<String>();
        }
        else {
            return this.blackoutMilestoneIds;
        }
    }

    public void setBlackoutMilestoneIds(List<String> blackoutMilestoneIds) {
        this.blackoutMilestoneIds = blackoutMilestoneIds;
    }

    @Override
    public List<MeetingTimeInfo> getAdditionalMeetingTimes() {
        if (null == this.additionalMeetingTimes) {
            return new ArrayList<MeetingTimeInfo>();
        }
        else {
            return this.additionalMeetingTimes;
        }
    }

    public void setAdditionalMeetingTimes(List<MeetingTimeInfo> additionalMeetingTimes) {
        this.additionalMeetingTimes = additionalMeetingTimes;
    }

}