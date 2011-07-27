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
package org.kuali.student.r2.core.atp.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.kuali.student.r2.common.dto.KeyEntityInfo;
import org.kuali.student.r2.common.infc.KeyDateOrMilestone;
import org.kuali.student.r2.core.atp.infc.Milestone;
import org.w3c.dom.Element;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MilestoneInfo", propOrder = { "key", "typeKey", "stateKey",
		"name", "descr", "isAllDay", "isDateRange", "startDate", "endDate",
		"meta", "attributes", "_futureElements" })
public class MilestoneInfo extends KeyEntityInfo implements Milestone,
		Serializable {

	private static final long serialVersionUID = 1L;
	@XmlElement
	private Boolean isAllDay;
	@XmlElement
	private Boolean isDateRange;
	@XmlElement
	private Date startDate;
	@XmlElement
	private Date endDate;
	@XmlAnyElement
	private List<Element> _futureElements;

	public MilestoneInfo() {
		super();
		isAllDay = false;
		isDateRange = false;
		startDate = null;
		endDate = null; 
		_futureElements = null;
	}

	/**
	 * Constructs a new MilestoneInfo from another Milestone.
	 * 
	 * @param milestone
	 *            the Milestone to copy
	 */
	public MilestoneInfo(KeyDateOrMilestone milestone) {
		super(milestone);
		if (null != milestone) {
			this.isAllDay = milestone.getIsAllDay();
			this.isDateRange = milestone.getIsDateRange();
			this.startDate = null != milestone.getStartDate() ? new Date(
					milestone.getStartDate().getTime()) : null;
			this.endDate = null != milestone.getEndDate() ? new Date(milestone
					.getEndDate().getTime()) : null;
			_futureElements = null;
		}
	}

	@Override
	public Boolean getIsAllDay() {
		return isAllDay;
	}

	public void setIsAllDay(Boolean isAllDay) {
		this.isAllDay = isAllDay;
	}

	@Override
	public Boolean getIsDateRange() {
		return isDateRange;
	}

	public void setIsDateRange(Boolean isDateRange) {
		this.isDateRange = isDateRange;
	}

	@Override
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = null != startDate ? new Date(startDate.getTime())
				: null;
	}

	@Override
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = null != endDate ? new Date(endDate.getTime()) : null;
	}
}
