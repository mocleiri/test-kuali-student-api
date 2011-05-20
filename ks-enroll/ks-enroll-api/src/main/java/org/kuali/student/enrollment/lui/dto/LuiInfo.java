/*
 * Copyright 2010 The Kuali Foundation 
 *
 * Licensed under the the Educational Community License, Version 1.0
 * (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.student.enrollment.lui.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.kuali.student.enrollment.lui.infc.Lui;
import org.kuali.student.r2.common.dto.IdEntityInfo;
import org.w3c.dom.Element;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LuiInfo", propOrder = { "id", "typeKey", "stateKey", "name",
		"descr", "luiCode", "cluId", "atpKey", "maxSeats", "effectiveDate",
		"expirationDate", "meta", "attributes", "_futureElements" })
public class LuiInfo extends IdEntityInfo implements Serializable, Lui {

	private static final long serialVersionUID = 1L;

	@XmlElement
	private String luiCode;

	@XmlElement
	private String cluId;

	@XmlElement
	private String atpKey;

	@XmlElement
	private Integer maxSeats;

	@XmlElement
	private Date effectiveDate;

	@XmlElement
	private Date expirationDate;

	@XmlAnyElement
	private List<Element> _futureElements;

	private LuiInfo() {
		super();
		luiCode = null;
		cluId = null;
		atpKey = null;
		maxSeats = null;
		effectiveDate = null;
		expirationDate = null;
		_futureElements = null;
	}

	public LuiInfo(Lui lui) {
		super(lui);
		this.luiCode = lui.getLuiCode();
		this.cluId = lui.getCluId();
		this.atpKey = lui.getAtpKey();
		this.maxSeats = lui.getMaxSeats();
		this.effectiveDate = null != lui.getEffectiveDate() ? new Date(lui
				.getEffectiveDate().getTime()) : null;
		this.expirationDate = null != lui.getExpirationDate() ? new Date(lui
				.getExpirationDate().getTime()) : null;
		this._futureElements = null;
	}

	@Override
	public String getLuiCode() {
		return luiCode;
	}

	public void setLuiCode(String luiCode) {
		this.luiCode = luiCode;
	}

	@Override
	public String getCluId() {
		return cluId;
	}

	public void setCluId(String cluId) {
		this.cluId = cluId;
	}

	@Override
	public String getAtpKey() {
		return atpKey;
	}

	public void setAtpKey(String atpKey) {
		this.atpKey = atpKey;
	}

	@Override
	public Integer getMaxSeats() {
		return maxSeats;
	}

	public void setMaxSeats(int maxSeats) {
		this.maxSeats = maxSeats;
	}

	@Override
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = new Date(effectiveDate.getTime());
	}

	@Override
	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = new Date(expirationDate.getTime());
	}
}