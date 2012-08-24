 /*
 * Copyright 2011 The Kuali Foundation
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
package org.kuali.student.mock.utilities;


import java.util.Date;

import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.dto.MetaInfo;
import org.kuali.student.r2.common.infc.*;


/**
 * A helper class for the Mock implementation
 * @author nwright
 */
public class MockHelper {

    /**
     * Create a new MetaInfo with the create id and time as well as the update
     * date and time and version ind set
     * @param context
     * @return
     */
    public MetaInfo createMeta(Context context) {
        MetaInfo mInfo = MetaInfo.newInstance();
        Date now = new Date();
        mInfo.setCreateId(context.getPrincipalId());
        mInfo.setCreateTime(now);
        mInfo.setUpdateId(context.getPrincipalId());
        mInfo.setUpdateTime(now);
        mInfo.setVersionInd("1");
        return mInfo;
    }

    /**
     * Updates the MetaInfo with new user id and date stamp
     * Also increments the verssion indicator
     * @param orig
     * @param context
     * @return new metainfo
     */
    public MetaInfo updateMeta(Meta orig, Context context) {
        Date now = new Date();
        int oldVersionInd = Integer.parseInt(orig.getVersionInd());
        MetaInfo mInfo = MetaInfo.getInstance(orig);
        mInfo.setUpdateId(context.getPrincipalId());
        mInfo.setUpdateTime(now);
        mInfo.setVersionInd("" + (oldVersionInd + 1));
        return mInfo;
    }
    
    
  
}

