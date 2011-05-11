package org.kuali.student.r2.lum.classI.lrc.dto;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.kuali.student.r2.common.dto.KeyEntityInfo;
import org.kuali.student.r2.common.dto.MetaInfo;
import org.kuali.student.r2.common.infc.RichText;
import org.w3c.dom.Element;
import org.kuali.student.r2.lum.classI.lrc.ResultValue;

@XmlType(name = "ResultValueInfo", propOrder = { "key", "typeKey", "stateKey",
		"name", "descr", "effectiveDate", "expirationDate", "value", "rank",
		"metaInfo", "attributes", "_futureElements" })
public class ResultValueInfo extends KeyEntityInfo implements ResultValue {

	private static final long serialVersionUID = 1L;

	@XmlAttribute
	private String value;

	@XmlAttribute
	private String rank;

	@XmlElement
	private Date effectiveDate;

	@XmlElement
	private Date expirationDate;

	@XmlAnyElement
	private List<Element> _futureElements;

	@XmlAttribute
	private String id;

	public ResultValueInfo() {

	}

	public ResultValueInfo(String name, String value, String rank,
			String typeKey, String stateKey, MetaInfo metaInfo, RichText descr,
			Date effectiveDate, Date expirationDate,
			List<Element> futureElements, String id) {
		// TODO once devs make objects mutable
		super();
		this.value = value;
		this.rank = rank;
		this.effectiveDate = effectiveDate;
		this.expirationDate = expirationDate;
		this._futureElements = futureElements;
		this.id = id;
	}

	public static ResultValueInfo createNewResultValueInfoFromResultValueInfo(
			ResultValueInfo resultValueInfo) {
		return new ResultValueInfo(resultValueInfo.getName(),
				resultValueInfo.getValue(), resultValueInfo.getRank(),
				resultValueInfo.getTypeKey(), resultValueInfo.getStateKey(),
				resultValueInfo.getMetaInfo(), resultValueInfo.getDescr(),
				resultValueInfo.getEffectiveDate(),
				resultValueInfo.getEffectiveDate(),
				resultValueInfo.get_futureElements(), resultValueInfo.getId());
	}


	@Override
	public String getValue() {
		return value;
	}
	@Override
	public String getRank() {
		return rank;
	}
	@Override
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	@Override
	public Date getExpirationDate() {
		return expirationDate;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public List<Element> get_futureElements() {

		return _futureElements;
	}

}
