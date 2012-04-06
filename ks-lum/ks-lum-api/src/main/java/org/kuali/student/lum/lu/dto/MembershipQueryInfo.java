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

package org.kuali.student.lum.lu.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.kuali.student.common.search.dto.SearchParam;

/**
 * Specifies a search for CLU identifiers.
 *
 * @Author KSContractMojo
 * @Author Kamal
 * @Since Mon Jan 11 15:21:50 PST 2010
 * @See <a href="https://test.kuali.org/confluence/display/KULSTU/membershipQueryInfo+Structure+v1.0-rc3">MembershipQueryInfo</>
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class MembershipQueryInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlAttribute
    private String id;
    
    @XmlElement
    private String searchTypeKey;

    @XmlElement
    private List<SearchParam> queryParamValueList;

    /**
     * Identifier for a search type.
     */
    public String getSearchTypeKey() {
        return searchTypeKey;
    }

    public void setSearchTypeKey(String searchTypeKey) {
        this.searchTypeKey = searchTypeKey;
    }

    /**
     * List of query parameter values. Not required if the search doesn't extend beyond the included object.
     */
    public List<SearchParam> getQueryParamValueList() {
        if (queryParamValueList == null) {
            queryParamValueList = new ArrayList<SearchParam>(0);
        }
        return queryParamValueList;
    }

    public void setQueryParamValueList(List<SearchParam> queryParamValueList) {
        this.queryParamValueList = queryParamValueList;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}    
}