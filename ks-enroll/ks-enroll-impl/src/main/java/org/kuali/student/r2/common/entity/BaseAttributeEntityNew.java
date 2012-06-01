package org.kuali.student.r2.common.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.kuali.student.common.entity.KSEntityConstants;
import org.kuali.student.r2.common.dto.AttributeInfo;
import org.kuali.student.r2.common.infc.Attribute;

@MappedSuperclass
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"ATTR_KEY", "OWNER_ID"})})
public abstract class BaseAttributeEntityNew<T> extends BaseEntity {

    @Column(name = "ATTR_KEY")
    private String key;

    @Column(name = "ATTR_VALUE", length = KSEntityConstants.EXTRA_LONG_TEXT_LENGTH)
    private String value;

    public BaseAttributeEntityNew() {}

    public BaseAttributeEntityNew(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public BaseAttributeEntityNew(Attribute att) {
        this.setId(att.getId());
       
        this.fromDto(att);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public abstract void setOwner(T owner);

    public abstract T getOwner();

    public void fromDto(Attribute info) {
		
		this.setKey(info.getKey());
		this.setValue(info.getValue());
		
	}

    public AttributeInfo toDto() {
        AttributeInfo attributeInfo = new AttributeInfo();
        attributeInfo.setId(this.getId());
        attributeInfo.setKey(this.getKey());
        attributeInfo.setValue(this.getValue());
        return attributeInfo;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BaseAttributeEntityNew [id=");
		builder.append(getId());
		builder.append(", key=");
		builder.append(key);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}
    
    

}
