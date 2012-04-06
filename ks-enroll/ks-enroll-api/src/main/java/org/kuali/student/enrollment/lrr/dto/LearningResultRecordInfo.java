/*
 * Copyright 2009 The Kuali Foundation Licensed under the Educational Community
 * License, Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.opensource.org/licenses/ecl1.php Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.kuali.student.enrollment.lrr.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.kuali.student.enrollment.lrr.infc.LearningResultRecord;
import org.kuali.student.r2.common.dto.IdEntityInfo;
import org.w3c.dom.Element;

/**
 * Information about the Learning Result Record Info.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LearningResultRecordInfo", propOrder = {"id", "name", "descr", "typeKey", "stateKey", "lprId", "resultValueKey", "resultSourceIdList", "meta", "attributes", "_futureElements"})
public class LearningResultRecordInfo extends IdEntityInfo implements LearningResultRecord, Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement
    private String lprId;

    @XmlElement
    private String resultValueKey;

    @XmlElement
    private List<String> resultSourceIdList;

    @XmlAnyElement
    private List<Element> _futureElements;

    public LearningResultRecordInfo() {
        super();
        this.lprId = null;
        this.resultValueKey = null;
        this.resultSourceIdList = new ArrayList<String>();
    }

    /**
     * This constructs a copy from LearningResultRecord infc
     * 
     * @param lrr
     */
    public LearningResultRecordInfo(LearningResultRecord lrr) {
        super(lrr);

        this.lprId = lrr.getLprId();
        this.resultValueKey = lrr.getResultValueKey();

        resultSourceIdList = new ArrayList<String>();

        Collections.copy(resultSourceIdList, lrr.getResultSourceIdList());

    }

    @Override
    public String getLprId() {
        return lprId;
    }

    @Override
    public String getResultValueKey() {
        return resultValueKey;
    }

    @Override
    public List<String> getResultSourceIdList() {
        if (null == resultSourceIdList) {
            resultSourceIdList = new ArrayList<String>(0);
        }
        return resultSourceIdList;
    }

    public void setLprId(String lprId) {
        this.lprId = lprId;
    }

    public void setResultValueKey(String resultValueId) {
        this.resultValueKey = resultValueId;
    }

    public void setResultSourceIdList(List<String> resultSourceIdList) {
        this.resultSourceIdList = resultSourceIdList;
    }
}