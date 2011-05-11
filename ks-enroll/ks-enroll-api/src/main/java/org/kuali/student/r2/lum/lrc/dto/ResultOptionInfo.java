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

package org.kuali.student.r2.lum.lrc.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.kuali.student.r2.common.dto.KeyEntityInfo;
import org.kuali.student.r2.common.infc.Meta;
import org.kuali.student.r2.common.infc.RichText;
import org.kuali.student.r2.lum.lrc.ResultOption;

/**
 * Information about a result option.
 * 
 * @author Sambit
 * @since
 * 
 */
@XmlType(name = "ResultOptionInfo", propOrder = { "key", "typeKey", "stateKey",
		"name", "descr", "resultUsageTypeKey", "resultComponentId",
		"effectiveDate", "expirationDate", "id", "metaInfo", "attributes",
		"_futureElements" })
public class ResultOptionInfo extends KeyEntityInfo implements ResultOption {

	private static final long serialVersionUID = 1L;

	@XmlAttribute
	private String resultUsageTypeKey;

	@XmlAttribute
	private String resultComponentId;

	@XmlElement
	private Date effectiveDate;

	@XmlElement
	private Date expirationDate;

	@XmlAttribute
	private String id;
	
	
	public static ResultOptionInfo getInstance(String name, RichText descr, String typeKey, String stateKey, Meta metaInfo, String resultUsageTypeKey, String resultComponentId, Date effectiveDate, Date expirationDate, String id) {
        return new ResultOptionInfo(name, descr, typeKey, stateKey, metaInfo, resultUsageTypeKey, resultComponentId, effectiveDate, expirationDate, id);
    }

	private ResultOptionInfo() {

	}

	private ResultOptionInfo(String name, RichText descr, String typeKey,
			String stateKey, Meta meta, String resultUsageTypeKey,
			String resultComponentId, Date effectiveDate, Date expirationDate,
			String id) {
		// TODO - after devs create new constructor
		super(name, descr, typeKey, stateKey, meta);
		this.resultUsageTypeKey = resultUsageTypeKey;
		this.resultComponentId = resultComponentId;
		this.effectiveDate = new Date(effectiveDate.getTime());
		this.expirationDate = new Date(expirationDate.getTime());
		this.id = id;
	}

	public static ResultOptionInfo createNewResultInfoFromResultInfo(
			ResultOptionInfo resultOptionInfo) {
		return ResultOptionInfo.getInstance(resultOptionInfo.getName(),
				resultOptionInfo.getDescr(), resultOptionInfo.getTypeKey(),
				resultOptionInfo.getStateKey(), resultOptionInfo.getMetaInfo(),
				resultOptionInfo.getResultUsageTypeKey(),
				resultOptionInfo.getResultComponentId(),
				resultOptionInfo.getEffectiveDate(),
				resultOptionInfo.getExpirationDate(), resultOptionInfo.getId());
	}

    public String getResultUsageTypeKey() {
		return resultUsageTypeKey;
	}

	public void setResultUsageTypeKey(String resultUsageTypeKey) {
		this.resultUsageTypeKey = resultUsageTypeKey;
	}

	/**
	 * Unique identifier for a result component.
	 */
	public String getResultComponentId() {
		return resultComponentId;
	}

	public void setResultComponentId(String resultComponentId) {
		this.resultComponentId = resultComponentId;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}