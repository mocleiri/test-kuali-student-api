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

package org.kuali.student.core.enumerationmanagement.bo;

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.kuali.student.core.bo.KsInactivatableFromToBase;


@Entity
@Table(name="KSEM_ENUM_VAL_T")
public class EnumeratedValue extends KsInactivatableFromToBase {
    
    public static final String ENUMERATION_KEY = "enumerationId";
    
    private static final long serialVersionUID = 1L;

    
    @Column(name="enum_key")
    String enumerationId;
    
    @Column(name="CD")
    String code;
    
    @Column(name="ABBREV_VAL")
    String abbrevValue;
    
    @Column(name="VAL")
    String value;
    
    @Column(name="SORT_KEY")
    int sortKey;
    
    @Transient
    transient Enumeration enumeration;
    
    
//    @Override
//    protected LinkedHashMap<String, Object> toStringMapper() {
//        LinkedHashMap<String, Object> map = super.toStringMapper();
//
//        map.put("enumerationId", enumerationId);
//        map.put("code", code);
//        map.put("abbrevValue", abbrevValue);
//        map.put("value", value);
//        map.put("activeFromDate", activeFromDate);
//        map.put("activeToDate", activeToDate);
//        map.put("sortKey", sortKey);
//
//        return map;
//    }
    

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAbbrevValue() {
        return abbrevValue;
    }

    public void setAbbrevValue(String abbrevValue) {
        this.abbrevValue = abbrevValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getSortKey() {
        return sortKey;
    }

    public void setSortKey(int sortKey) {
        this.sortKey = sortKey;
    }

    public String getEnumerationId() {
        return enumerationId;
    }

    public void setEnumerationId(String enumerationId) {
        this.enumerationId = enumerationId;
    }

    public Enumeration getEnumeration() {
        return enumeration;
    }

    public void setEnumeration(Enumeration enumeration) {
        this.enumeration = enumeration;
    }

}
