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

package org.kuali.student.brms.translators.drools;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.drools.compiler.DrlParser;
import org.drools.compiler.DroolsParserException;
import org.drools.compiler.ParserError;
import org.drools.lang.descr.PackageDescr;
import org.kuali.student.brms.repository.rule.RuleSet;
import org.kuali.student.brms.translators.RuleSetValidator;
import org.kuali.student.brms.translators.RuleSetVerificationResult;
import org.kuali.student.brms.translators.exceptions.RuleSetTranslatorException;

enum Severity {ERROR, WARN, INFO, NONE}

public class RuleSetValidatorDroolsImpl implements RuleSetValidator {

    //private Verifier verifier = new Verifier();
    private final List<String> errorMessages = new ArrayList<String>();

    /**
     * Constructs a new rule verifier.
     */
    public RuleSetValidatorDroolsImpl() {
    }

    /**
     * Verifies that a rule set is valid
     * 
     * @param ruleSet Rule set
     * @return Rule set verification result
     */
    public RuleSetVerificationResult verify(RuleSet ruleSet) throws RuleSetTranslatorException {
    	RuleSetVerificationResult result = new RuleSetVerificationResult();
        errorMessages.clear();
    	boolean valid = verify(new StringReader(ruleSet.getContent()));
    	result.setRuleSetValid(valid);
    	result.setMessages(getMessages());
    	return result;
    }

    /**
     * Verify that a rule package is valid.
     * 
     * @param source
     * @throws RuleSetTranslatorException Any errors verifying a rule set
     * @return True if rule set was successfully verified otherwise false
     */
    public boolean verify(Reader source) throws RuleSetTranslatorException {
        DrlParser p = new DrlParser();
        boolean valid = true;
        
        try {
            p.parse(source);
        } catch (DroolsParserException e) {
            throw new RuleSetTranslatorException("Parsing rule failed", e);
        }

        if (p.hasErrors()) {
        	valid = false;
        	// Drools 4 does not use generics so we have to suppress warning
        	@SuppressWarnings("unchecked") 
            List<ParserError> errors = p.getErrors();
            for(ParserError error : errors) {
                errorMessages.add(error.toString());
            }
        }

        //verifier.addPackageDescr(pkg);
        //verifier.fireAnalysis();
        //VerifierResult result = anal.getResult();
        
        return valid;
    }

    /**
     * Gets rule verification severity.
     * 
     * @return Severity
     */
    public Severity getSeverity() {
        return Severity.NONE;
    }

    /**
     * Gets any verification messages.
     * 
     * @return
     */
    public List<String> getMessages() {
        return errorMessages;
    }
    
    public String getMessage() {
    	StringBuilder errorMessage = new StringBuilder();
    	for(String msg : this.errorMessages) {
    		errorMessage.append(msg);
    	}
    	return errorMessage.toString();
    }
}
