package org.kuali.student.r2.lum.lrc.dto;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.kuali.student.r2.common.dto.KeyEntityInfo;
import org.kuali.student.r2.common.infc.Attribute;
import org.kuali.student.r2.common.infc.Meta;
import org.kuali.student.r2.common.infc.RichText;
import org.kuali.student.r2.lum.lrc.infc.ResultValueRange;

@XmlType(name = "ResultValueRangeInfo", propOrder = { "key", "id", "typeKey",
		"stateKey", "resultTypeKey", "minValue", "maxValue", "increment",
		"metaInfo", "effectiveDate", "expirationDate", "attributes" })
public class ResultValueRangeInfo extends KeyEntityInfo implements
		ResultValueRange {

	private static final long serialVersionUID = 1L;

	@XmlAttribute
	private String resultTypeKey;
	@XmlAttribute
	private String id;
	@XmlAttribute
	private String minValue;
	@XmlAttribute
	private String maxValue;
	@XmlAttribute
	private float increment;

	@XmlElement
	private Date effectiveDate;
	@XmlElement
	private Date expirationDate;

	public ResultValueRangeInfo() {
	}

	private ResultValueRangeInfo(String key, String name, RichText descr,
			String typeKey, String stateKey, String resultTypeKey, String id,
			String minValue, String maxValue, float increment,
			Date effectiveDate, Date expirationDate, Meta meta,
			List<? extends Attribute> attributes) {
		super();
		this.resultTypeKey = resultTypeKey;
		this.id = id;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.increment = increment;
		this.effectiveDate = effectiveDate;
		this.expirationDate = expirationDate;
	}

	public static ResultValueRangeInfo newInstance(String key, String name,
			RichText descr, String typeKey, String stateKey,
			String resultTypeKey, String id, String minValue, String maxValue,
			float increment, Date effectiveDate, Date expirationDate,
			Meta meta, List<? extends Attribute> attributes) {
		return new ResultValueRangeInfo(key, name, descr, typeKey, stateKey,
				resultTypeKey, id, minValue, maxValue, increment,
				effectiveDate, expirationDate, meta, attributes);
	}

	public ResultValueRangeInfo createNewResultValueRangeInfoFromResultValueRangeInfo(
			ResultValueRangeInfo resultValueRangeInfo) {

		return new ResultValueRangeInfo(resultValueRangeInfo.getKey(),
				resultValueRangeInfo.getName(),
				resultValueRangeInfo.getDescr(),
				resultValueRangeInfo.getTypeKey(),
				resultValueRangeInfo.getStateKey(),
				resultValueRangeInfo.getResultTypeKey(),
				resultValueRangeInfo.getId(),
				resultValueRangeInfo.getMinValue(),
				resultValueRangeInfo.getMaxValue(),
				resultValueRangeInfo.getIncrement(),
				resultValueRangeInfo.getEffectiveDate(),
				resultValueRangeInfo.getExpirationDate(),
				resultValueRangeInfo.getMetaInfo(),
				resultValueRangeInfo.getAttributes());
	}

	@Override
	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	@Override
	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	@Override
	public float getIncrement() {
		return increment;
	}

	public void setIncrement(float increment) {
		this.increment = increment;
	}

	@Override
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@Override
	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public void setType(String type) {
		this.resultTypeKey = type;
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getResultTypeKey() {
		return resultTypeKey;
	}

}
