/*
 * Copyright 2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may	obtain a copy of the License at
 *
 * 	http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.student.lum.lu.assembly.data.client.refactorme.base;


import org.kuali.student.core.assembly.data.Data;
import org.kuali.student.core.assembly.helper.PropertyEnum;


public class MembershipQueryInfoHelper
{
	private static final long serialVersionUID = 1;
	
	public enum Properties implements PropertyEnum
	{
		SEARCH_TYPE_KEY ("searchTypeKey"),
		QUERY_PARAM_VALUE_LIST ("queryParamValueList");
		
		private final String key;
		
		private Properties (final String key)
		{
			this.key = key;
		}
		
		@Override
		public String getKey ()
		{
			return this.key;
		}
	}
	private Data data;
	
	private MembershipQueryInfoHelper (Data data)
	{
		this.data = data;
	}
	
	public static MembershipQueryInfoHelper wrap (Data data)
	{
		if (data == null)
		{
			 return null;
		}
		return new MembershipQueryInfoHelper (data);
	}
	
	public Data getData ()
	{
		return data;
	}
	
	
	public void setSearchTypeKey (String value)
	{
		data.set (Properties.SEARCH_TYPE_KEY.getKey (), value);
	}
	
	
	public String getSearchTypeKey ()
	{
		return (String) data.get (Properties.SEARCH_TYPE_KEY.getKey ());
	}
	
	
	public void setQueryParamValueList (Data value)
	{
		data.set (Properties.QUERY_PARAM_VALUE_LIST.getKey (), value);
	}
	
	
	public Data getQueryParamValueList ()
	{
		return (Data) data.get (Properties.QUERY_PARAM_VALUE_LIST.getKey ());
	}
	
}

