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

package org.kuali.student.core.statement.naturallanguage.translators;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.kuali.student.core.exceptions.DoesNotExistException;
import org.kuali.student.core.exceptions.OperationFailedException;
import org.kuali.student.core.statement.entity.ReqComponent;
import org.kuali.student.core.statement.entity.ReqComponentType;
import org.kuali.student.core.statement.entity.ReqComponentTypeNLTemplate;
import org.kuali.student.core.statement.naturallanguage.Context;
import org.kuali.student.core.statement.naturallanguage.ContextRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class translates requirement components into a specific 
 * natural language. This class is not thread safe.
 */
public class ReqComponentTranslator {
    /** SLF4J logging framework */
    final static Logger logger = LoggerFactory.getLogger(ReqComponentTranslator.class);

    private String language;
    private ContextRegistry<Context<ReqComponent>> contextRegistry;
    private TemplateTranslator templateTranslator = new TemplateTranslator();

    /**
	 * Constructs a new natural language translator in the 
	 * default language locale.
     */
    public ReqComponentTranslator() {
		this.language = Locale.getDefault().getLanguage();
    }

	/**
	 * Sets requirement component translation language.
	 * 
	 * @param language Language translation
	 */
    public void setLanguage(final String language) {
		this.language = language;
	}

    /**
     * Sets the template context registry.
     * 
     * @param contextRegistry Template context registry
     */
    public void setContextRegistry(final ContextRegistry<Context<ReqComponent>> contextRegistry) {
    	this.contextRegistry = contextRegistry;
    }

    /**
     * Translates an <code>reqComponent</code> for a specific <code>nlUsageTypeKey</code> into natural language.
     * This method is not thread safe.
     *  
     * @param reqComponent
     *            Requirement component to translate
     * @param nlUsageTypeKey
     *            Natural language usuage type key (context)
     * @return Natural language translation
     * @throws DoesNotExistException
     *             Natural language usuage type key does not exist
     * @throws OperationFailedException
     *             Translation failed
     */
    public String translate(final ReqComponent reqComponent, final String nlUsageTypeKey) throws DoesNotExistException, OperationFailedException {
    	if(reqComponent == null) {
    		throw new DoesNotExistException("ReqComponent cannot be null");
    	}
    	
    	ReqComponentType reqComponentType = reqComponent.getRequiredComponentType();
        Map<String, Object> contextMap = buildContextMap(reqComponent);
        ReqComponentTypeNLTemplate template = getTemplate(reqComponentType, nlUsageTypeKey);

        try {
			return this.templateTranslator.translate(contextMap, template.getTemplate());
		} catch (OperationFailedException e) {
			String msg = "Generating template for requirement component failed: "+reqComponent;
			logger.error(msg, e);
			throw new OperationFailedException(msg);
		}
    }

    /**
     * Builds a requirement component type context map.
     * 
     * @param reqComponent Requirement component
     * @throws DoesNotExistException Requirement component context not found in registry
     * @throws OperationFailedException Creating context map failed
     */
    private Map<String, Object> buildContextMap(ReqComponent reqComponent) throws DoesNotExistException, OperationFailedException {
    	String reqComponentTypeId = reqComponent.getRequiredComponentType().getId();
        List<Context<ReqComponent>> contextList = this.contextRegistry.get(reqComponentTypeId);
        if(contextList == null || contextList.isEmpty()) {
        	throw new DoesNotExistException("Requirement component context not found in registry for requirement component type id: " + reqComponentTypeId);
        }
        Map<String, Object> contextMap = new HashMap<String, Object>();
        for(Context<ReqComponent> context : contextList) {
    		Map<String, Object> cm = context.createContextMap(reqComponent);
    		contextMap.putAll(cm);
    	}

        return contextMap;
    }

    /**
     * Gets the requirement component type template for the <code>nlUsageTypeKey</code>.
     * 
     * @param reqComponentType
     *            Requirement component type
     * @param nlUsageTypeKey
     *            Natural language usuage type key (context)
     * @return Requirement component type template
     * @throws DoesNotExistException Template does not exist
     */
    private ReqComponentTypeNLTemplate getTemplate(ReqComponentType reqComponentType, String nlUsageTypeKey) throws DoesNotExistException {
        List<ReqComponentTypeNLTemplate> templateList = reqComponentType.getNlUsageTemplates();
        for (ReqComponentTypeNLTemplate template : templateList) {
            if (nlUsageTypeKey.equals(template.getNlUsageTypeKey()) && this.language.equals(template.getLanguage())) {
                return template;
            }
        }
        throw new DoesNotExistException("Natural language usage type key '" + nlUsageTypeKey + "'" +
        		" and language code '" + this.language + "' for requirement component type template not found");
    }
}
