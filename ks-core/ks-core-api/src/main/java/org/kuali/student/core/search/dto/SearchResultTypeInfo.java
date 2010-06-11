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

package org.kuali.student.core.search.dto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchResultTypeInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String desc;
	private List<ResultColumnInfo> resultColumns;
	private String key; 
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getDesc(){
		return desc;
	}
	public void setDesc(String desc){
		this.desc = desc;
	}
	public List<ResultColumnInfo> getResultColumns(){
		if(resultColumns == null){
			resultColumns = new ArrayList<ResultColumnInfo>();
		}
		return resultColumns;
	}
	public void setResultColumns(List<ResultColumnInfo> resultColumns){
		this.resultColumns = resultColumns;
	}
	public String getKey(){
		return key;
	}
	public void setKey(String key){
		this.key = key;
	}
}
