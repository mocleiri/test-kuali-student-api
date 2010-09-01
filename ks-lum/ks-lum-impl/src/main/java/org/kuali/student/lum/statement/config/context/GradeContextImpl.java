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

package org.kuali.student.lum.statement.config.context;

import java.util.HashMap;
import java.util.Map;

import org.kuali.student.core.statement.entity.ReqComponent;
import org.kuali.student.core.exceptions.OperationFailedException;
import org.kuali.student.lum.statement.typekey.ReqComponentFieldTypes;

/**
 * This class creates the template context for grade condition type.
 */
public class GradeContextImpl extends BasicContextImpl {
	/** Total credits template token */ 
	private final static String GRADE_TOKEN = "grade";
    private final static String GRADE_TYPE_TOKEN = "gradeType";	

    /**
     * Creates the context map (template data) for the requirement component.
     * 
     * @param reqComponent Requirement component
     * @throws OperationFailedException Creating context map fails
     */
    public Map<String, Object> createContextMap(ReqComponent reqComponent) throws OperationFailedException {
        Map<String, Object> contextMap = new HashMap<String, Object>();
        contextMap.put(GRADE_TYPE_TOKEN, getReqComponentFieldValue(reqComponent, ReqComponentFieldTypes.GRADE_TYPE_KEY.getId()));
        contextMap.put(GRADE_TOKEN, getReqComponentFieldValue(reqComponent, ReqComponentFieldTypes.GRADE_KEY.getId()));        
        return contextMap;
    }
}
