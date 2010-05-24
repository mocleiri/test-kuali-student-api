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

package org.kuali.student.common.ui.server.mvc.dto;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.kuali.student.common.ui.client.mvc.DataModel;
import org.kuali.student.common.ui.client.mvc.DataModelDefinition;
import org.kuali.student.core.assembly.data.Data;
import org.kuali.student.core.assembly.data.Metadata;
import org.kuali.student.core.assembly.data.QueryPath;
import org.kuali.student.core.assembly.data.Data.DataType;


public class TestDataModel {

	DataModelDefinition dataModelDefinition;
	
	@Before
	public void setup(){
		Metadata metadataRoot = new Metadata();
		
		Map<String, Metadata> properties; 
		
		Metadata metaFormat = new Metadata();
		
		properties = new HashMap<String, Metadata>();
		
		Metadata metaTitle = new Metadata();
		metaTitle.setDataType(DataType.STRING);
		Metadata metaType = new Metadata();
		metaType.setDataType(DataType.STRING);
		properties.put("title", metaTitle);
		properties.put("type", metaType);
		
		metaFormat.setProperties(properties);
		metaFormat.setDataType(DataType.DATA);
		
		Metadata metaFormats = new Metadata();
		properties = new HashMap<String, Metadata>();
		properties.put("*", metaFormat);
		metaFormats.setDataType(DataType.LIST);
		metaFormats.setProperties(properties);
		
		properties = new HashMap<String, Metadata>();
		properties.put("formats", metaFormats);
		
		metadataRoot.setProperties(properties);
	
		dataModelDefinition = new DataModelDefinition();
		dataModelDefinition.setMetadata(metadataRoot);	
	}
	
	@Test
	public void testQueryWildpath(){
		DataModel dataModel = new DataModel();
		dataModel.setRoot(new Data());
		dataModel.setDefinition(dataModelDefinition);
		
		dataModel.set(QueryPath.parse("formats/0/title"), "Format 1");
		dataModel.set(QueryPath.parse("formats/1/title"), "Format 2");
		
		Map<QueryPath, Object> values = dataModel.query("formats");	
		Map<QueryPath, Object> formatValues = dataModel.query("formats/*");
		
		assertTrue(values.size()==1);
		assertTrue(formatValues.size()==2);
	}
	
}
