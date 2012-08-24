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
import org.kuali.student.r2.common.datadictionary.infc.AttributeDefinitionInfc;

/**
 *
 * @author nwright
 */
public class Student2RiceAttributeDefinitionConverter {

    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(Student2RiceAttributeDefinitionConverter.class);

    public AttributeDefinition convert(AttributeDefinitionInfc student) {
        AttributeDefinition rice = new AttributeDefinition();
        rice.setName(student.getName());
        rice.setChildEntryName(student.getChildEntryName());
        rice.setDataType(student.getDataType());
        rice.setRequired(student.getIsRequired());
//        rice.setMinOccurs(student.getMinOccurs());
//        rice.setMaxOccurs(student.getMaxOccurs());
        rice.setMinLength(student.getMinLength());
        rice.setMaxLength(student.getMaxLength());
        rice.setForceUppercase(student.getIsForceUppercase());
        rice.setShortLabel(student.getShortLabel());
        rice.setSummary(student.getSummary());
        rice.setLabel(student.getLabel());
        rice.setDescription(student.getDescription());
        rice.setExclusiveMin(student.getExclusiveMin());
        rice.setInclusiveMax(student.getInclusiveMax());
        rice.setDisplayLabelAttribute(student.getDisplayLabelAttribute());
        rice.setUnique(student.getIsUnique());
        rice.setCustomValidatorClass(student.getCustomValidatorClass());
        if (student.getFormatterClass() != null) {
            rice.setFormatterClass(student.getFormatterClass());
        }
        if (student.getValidCharactersConstraint() != null) {
            rice.setValidCharactersConstraint(new Student2RiceValidCharactersConstraintConverter().convert(student.getValidCharactersConstraint()));
        }
        return rice;
    }
}
