/*
 * Copyright 2007 The Kuali Foundation
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
package org.kuali.student.common.validator;

import java.util.List;
import java.util.Stack;

import org.kuali.student.common.dictionary.dto.FieldDefinition;
import org.kuali.student.common.dictionary.dto.ObjectStructureDefinition;
import org.kuali.student.common.validation.dto.ValidationResultInfo;

/**
 * This is an abstract class that allows the sub implementations an option to reference ValidatorFactory.
 * Any custom validator code that requires validatorFactory should extend this abstract class. 
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 *
 */
public abstract class BaseAbstractValidator implements Validator {

    protected ValidatorFactory validatorFactory;    
    
    public ValidatorFactory getValidatorFactory() {
        return validatorFactory;
    }

    public void setValidatorFactory(ValidatorFactory validatorFactory) {
        this.validatorFactory = validatorFactory;
    }

    /**
     * @see org.kuali.student.common.validator.Validator#validateObject(java.lang.Object, org.kuali.student.common.dictionary.dto.ObjectStructureDefinition)
     */
    @Override
    public abstract List<ValidationResultInfo> validateObject(Object o, ObjectStructureDefinition objStructure);

    /**
     * @see org.kuali.student.common.validator.Validator#validateObject(org.kuali.student.common.dictionary.dto.FieldDefinition, java.lang.Object, org.kuali.student.common.dictionary.dto.ObjectStructureDefinition, java.util.Stack)
     */
    @Override
    public abstract List<ValidationResultInfo> validateObject(FieldDefinition field, Object o, ObjectStructureDefinition objStructure, Stack<String> elementStack);
}
