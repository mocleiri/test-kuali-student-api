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


import java.util.Date;

import org.kuali.student.core.assembly.data.Data;
import org.kuali.student.core.assembly.helper.PropertyEnum;
import org.kuali.student.lum.common.client.lo.MetaInfoHelper;


public class CluPublicationInfoHelper
{
	private static final long serialVersionUID = 1;
	
	public enum Properties implements PropertyEnum
	{
		CLU_ID ("cluId"),
		VARIANTS ("variants"),
		START_CYCLE ("startCycle"),
		END_CYCLE ("endCycle"),
		EFFECTIVE_DATE ("effectiveDate"),
		EXPIRATION_DATE ("expirationDate"),
		ATTRIBUTES ("attributes"),
		META_INFO ("metaInfo"),
		TYPE ("type"),
		STATE ("state"),
		ID ("id");
		
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
	
	private CluPublicationInfoHelper (Data data)
	{
		this.data = data;
	}
	
	public static CluPublicationInfoHelper wrap (Data data)
	{
		if (data == null)
		{
			 return null;
		}
		return new CluPublicationInfoHelper (data);
	}
	
	public Data getData ()
	{
		return data;
	}
	
	
	public void setCluId (String value)
	{
		data.set (Properties.CLU_ID.getKey (), value);
	}
	
	
	public String getCluId ()
	{
		return (String) data.get (Properties.CLU_ID.getKey ());
	}
	
	
	public void setVariants (Data value)
	{
		data.set (Properties.VARIANTS.getKey (), value);
	}
	
	
	public Data getVariants ()
	{
		return (Data) data.get (Properties.VARIANTS.getKey ());
	}
	
	
	public void setStartCycle (String value)
	{
		data.set (Properties.START_CYCLE.getKey (), value);
	}
	
	
	public String getStartCycle ()
	{
		return (String) data.get (Properties.START_CYCLE.getKey ());
	}
	
	
	public void setEndCycle (String value)
	{
		data.set (Properties.END_CYCLE.getKey (), value);
	}
	
	
	public String getEndCycle ()
	{
		return (String) data.get (Properties.END_CYCLE.getKey ());
	}
	
	
	public void setEffectiveDate (Date value)
	{
		data.set (Properties.EFFECTIVE_DATE.getKey (), value);
	}
	
	
	public Date getEffectiveDate ()
	{
		return (Date) data.get (Properties.EFFECTIVE_DATE.getKey ());
	}
	
	
	public void setExpirationDate (Date value)
	{
		data.set (Properties.EXPIRATION_DATE.getKey (), value);
	}
	
	
	public Date getExpirationDate ()
	{
		return (Date) data.get (Properties.EXPIRATION_DATE.getKey ());
	}
	
	
	public void setAttributes (Data value)
	{
		data.set (Properties.ATTRIBUTES.getKey (), value);
	}
	
	
	public Data getAttributes ()
	{
		return (Data) data.get (Properties.ATTRIBUTES.getKey ());
	}
	
	
	public void setMetaInfo (MetaInfoHelper value)
	{
		data.set (Properties.META_INFO.getKey (), (value == null) ? null : value.getData ());
	}
	
	
	public MetaInfoHelper getMetaInfo ()
	{
		return MetaInfoHelper.wrap ((Data) data.get (Properties.META_INFO.getKey ()));
	}
	
	
	public void setType (String value)
	{
		data.set (Properties.TYPE.getKey (), value);
	}
	
	
	public String getType ()
	{
		return (String) data.get (Properties.TYPE.getKey ());
	}
	
	
	public void setState (String value)
	{
		data.set (Properties.STATE.getKey (), value);
	}
	
	
	public String getState ()
	{
		return (String) data.get (Properties.STATE.getKey ());
	}
	
	
	public void setId (String value)
	{
		data.set (Properties.ID.getKey (), value);
	}
	
	
	public String getId ()
	{
		return (String) data.get (Properties.ID.getKey ());
	}
	
}

