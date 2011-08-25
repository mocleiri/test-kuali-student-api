/*
 * Copyright 2011 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.student.r2.common.datadictionary;

import org.kuali.rice.krad.datadictionary.AttributeDefinition;
import org.kuali.student.r2.common.datadictionary.dto.AttributeDefinitionInfo;

/**
 *
 * @author nwright
 */
public class Rice2StudentAttributeDefinitionConverter {

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Rice2StudentAttributeDefinitionConverter.class);

    public AttributeDefinitionInfo convert(AttributeDefinition rice) {
        AttributeDefinitionInfo.Builder bldr = new AttributeDefinitionInfo.Builder();
        bldr.setName (rice.getName());
        bldr.setChildEntryName (rice.getChildEntryName());
        bldr.setDataType (rice.getDataType());
        bldr.setRequired (rice.isRequired());
//        bldr.setMinOccurs (rice.getMustOccurConstraints().get(0).getMin());
//        bldr.setMaxOccurs (rice.getMustOccurConstraints().get (0).getMax());
        bldr.setMinLength (rice.getMinLength());
        bldr.setMaxLength (rice.getMaxLength());
        bldr.setForceUppercase (rice.getForceUppercase());
        bldr.setShortLabel (rice.getShortLabel());
        bldr.setSummary (rice.getSummary());
        bldr.setLabel (rice.getLabel());
        bldr.setDescription (rice.getDescription());
        bldr.setExclusiveMin (rice.getExclusiveMin());
        bldr.setInclusiveMax (rice.getInclusiveMax());
        bldr.setDisplayLabelAttribute (rice.getDisplayLabelAttribute());
        bldr.setUnique (rice.getUnique());
        bldr.setCustomValidatorClass (rice.getCustomValidatorClass());
        bldr.setFormatterClass (rice.getFormatterClass());
        if (rice.getValidCharactersConstraint() != null) {
            bldr.setValidCharactersConstraint (new Rice2StudentValidCharactersConstraintConverter ().convert(rice.getValidCharactersConstraint()));
        }
        return bldr.build();
    }
}
