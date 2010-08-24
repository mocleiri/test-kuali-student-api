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

package org.kuali.student.lum.statement.config.context.lu;

import java.util.Map;

import org.kuali.student.core.statement.entity.ReqComponent;
import org.kuali.student.core.statement.naturallanguage.AbstractContext;
import org.kuali.student.core.exceptions.DoesNotExistException;
import org.kuali.student.core.exceptions.OperationFailedException;
import org.kuali.student.lum.statement.typekey.ReqComponentFieldTypes;

/**
 * This class creates the template context for grade condition type.
 */
public class DurationContextImpl extends AbstractContext<ReqComponent> {
	/** Duration template token */ 
    private final static String DURATION_TYPE_TOKEN = "durationType";	

    /**
     * Creates the context map (template data) for the requirement component.
     * 
     * @param reqComponent Requirement component
     * @throws DoesNotExistException
     * @throws DoesNotExistException If CLU, CluSet or relation does not exist
     */
    public Map<String, Object> createContextMap(ReqComponent reqComponent) throws OperationFailedException {
        Map<String, Object> contextMap = super.createContextMap(reqComponent);
        contextMap.put(CommonTemplateTokens.EXPECTED_VALUE_TOKEN, getReqComponentFieldValue(reqComponent, ReqComponentFieldTypes.REQUIRED_COUNT_KEY.getId()));
        contextMap.put(DURATION_TYPE_TOKEN, getReqComponentFieldValue(reqComponent, ReqComponentFieldTypes.DURATION_TYPE_KEY.getId()));
        return contextMap;
    }
}
