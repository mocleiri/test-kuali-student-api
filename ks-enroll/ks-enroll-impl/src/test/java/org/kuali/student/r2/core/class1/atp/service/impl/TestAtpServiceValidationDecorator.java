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
package org.kuali.student.r2.core.class1.atp.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.student.r2.common.datadictionary.DataDictionaryValidator.ValidationType;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.dto.RichTextInfo;
import org.kuali.student.r2.common.dto.ValidationResultInfo;
import org.kuali.student.r2.common.exceptions.DoesNotExistException;
import org.kuali.student.r2.common.exceptions.InvalidParameterException;
import org.kuali.student.r2.common.exceptions.MissingParameterException;
import org.kuali.student.r2.common.exceptions.OperationFailedException;
import org.kuali.student.r2.common.exceptions.PermissionDeniedException;
import org.kuali.student.r2.common.util.constants.AtpServiceConstants;
import org.kuali.student.r2.core.atp.dto.AtpInfo;
import org.kuali.student.r2.core.atp.dto.AtpMilestoneRelationInfo;
import org.kuali.student.r2.core.atp.dto.MilestoneInfo;
import org.kuali.student.r2.core.atp.service.AtpService;
import org.kuali.student.r2.core.class1.atp.service.decorators.AtpServiceValidationDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * This class holds tests for the validation methods in AtpService that are implemented in AtpServiceValidationDecorator
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:acal-test-context.xml"})
@TransactionConfiguration(transactionManager = "JtaTxManager", defaultRollback = true)
@Transactional
public class TestAtpServiceValidationDecorator {

    @Autowired
    private AtpServiceValidationDecorator atpService;

    //public ApplicationContext appContext = new ClassPathXmlApplicationContext(new String[]{"acal-test-context.xml"});
    public static String principalId = "123";
    public ContextInfo callContext = ContextInfo.newInstance();
    
    @Before
    public void setUp() {
        callContext = ContextInfo.getInstance(callContext);
        callContext.setPrincipalId(principalId);        
        //atpService = appContext.getBean(AtpServiceValidationDecorator.class);
    }
    
    @Test
    public void testValidateAtpMilestoneRelation()
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        AtpMilestoneRelationInfo rel = new AtpMilestoneRelationInfo();
        
        rel.setId("newRelId");
        rel.setAtpKey("testAtpId1");
        rel.setMilestoneKey("testId");
        rel.setEffectiveDate(new Date());
        rel.setStateKey(AtpServiceConstants.ATP_MILESTONE_RELATION_ACTIVE_STATE_KEY);
        rel.setTypeKey("kuali.atp.milestone.relation.owns");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2100);
        rel.setExpirationDate(cal.getTime());

        List<ValidationResultInfo> existingResults =
                atpService.validateAtpMilestoneRelation(ValidationType.FULL_VALIDATION.toString(), rel, callContext);
        assertTrue("validateAtpMilestoneRelation() should have returned an empty list.", existingResults.isEmpty());
        
        // make sure validation catches an incomplete AMR info object
        AtpMilestoneRelationInfo invalid = new AtpMilestoneRelationInfo();
        List<ValidationResultInfo> invalidResults =
                atpService.validateAtpMilestoneRelation("FULL_VALIDATION", invalid, callContext);
        assertFalse("Validation errors are expected.", invalidResults.isEmpty());
    }
    
    @Test
    public void testValidateMilestone()
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        MilestoneInfo milestone = new MilestoneInfo();

        // validation should have problems with a new, incomplete milestone
        List<ValidationResultInfo> validationResults =
                atpService.validateMilestone("FULL_VALIDATION", milestone, callContext);
        assertEquals("Three validation errors are expected.", 3, validationResults.size());

        // populate two of the three required fields (key, type, state) and validation
        // should now return a list with only one error, for the "stateKey" field
        milestone.setKey("newId");
        milestone.setTypeKey("kuali.atp.milestone.RegistrationPeriod");
        validationResults = atpService.validateMilestone("FULL_VALIDATION", milestone, callContext);
        assertEquals("validateMilestone() should have returned one error.", 1, validationResults.size());
        assertEquals("stateKey", validationResults.get(0).getElement());

        // validation should pass once the stateKey is provided
        milestone.setStateKey("kuali.atp.state.Draft");
        validationResults = atpService.validateMilestone("FULL_VALIDATION", milestone, callContext);
        assertNotNull("validateMilestone() should return an empty list, not null.", milestone);
        assertEquals("validateMilestone() should have returned zero errors.", 0, validationResults.size());
    }
    
    @Test
    public void testValidateAtp()
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        AtpInfo atpInfo = new AtpInfo();

        // validation should have problems with a new, incomplete ATP
        List<ValidationResultInfo> validationResults =
                atpService.validateAtp("FULL_VALIDATION", atpInfo, callContext);
        assertEquals("Three validation errors are expected.", 3, validationResults.size());

        // populate two of the three required fields (key, type, state) and validation
        // should now return a list with only one error, for the "stateKey" field
        atpInfo.setKey("newId");
        atpInfo.setTypeKey("kuali.atp.type.AcademicCalendar");
        validationResults = atpService.validateAtp("FULL_VALIDATION", atpInfo, callContext);
        assertEquals("validateAtp() should have returned one error.", 1, validationResults.size());
        assertEquals("stateKey", validationResults.get(0).getElement());

        // validation should pass once the stateKey is provided
        atpInfo.setStateKey("kuali.atp.state.Draft");
        validationResults = atpService.validateAtp("FULL_VALIDATION", atpInfo, callContext);
        assertNotNull("validateAtp() should return an empty list, not null.", atpInfo);
        assertEquals("validateAtp() should have returned zero errors.", 0, validationResults.size());
    }
}
