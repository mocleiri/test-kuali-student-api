/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kuali.student.r2.core.exemption.service;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.util.constants.ExemptionServiceConstants;
import org.kuali.student.r2.core.exemption.dto.ExemptionInfo;
import org.kuali.student.r2.core.exemption.dto.ExemptionRequestInfo;

/**
 *
 * @author nwright
 */
public class ExemptionServicePersistenceConformanceTest {

    public ExemptionServicePersistenceConformanceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    private static final String TEST_PRINCIPAL_ID1 = "testPrincipalId1";
    private static final String TEST_PRINCIPAL_ID2 = "testPrincipalId2";
    private ContextInfo getContext() {
        ContextInfo context = new ContextInfo();
        context.setPrincipalId("testPrincipalId1");
        return context;
    }
    private ExemptionService instance = new ExemptionServiceMockImpl();

    /**
     * Test of createExemption method, of class ExemptionServiceMockImpl.
     */
    @Test
    public void testExemptionCrud() throws Exception {
        System.out.println("createExemption");
        ContextInfo context = getContext();
        // create
        String exemptionRequestId = "request1";
        ExemptionInfo info = new ExemptionInfo();
        info.setTypeKey(ExemptionServiceConstants.DATE_EXEMPTION_TYPE_KEY);
        info.setStateKey(ExemptionServiceConstants.EXEMPTION_ACTIVE_STATE_KEY);
        info.setExemptedPersonId("person1");
        Date before = new Date();
        ExemptionInfo result = instance.createExemption(exemptionRequestId, info, context);
        Date after = new Date();
        if (result == info) {
            fail("returned object should not be the same as the one passed in");
        }
        assertNotNull(result.getId());
        assertEquals(info.getTypeKey(), result.getTypeKey());
        assertEquals(info.getStateKey(), result.getStateKey());
        assertEquals(info.getExemptedPersonId(), result.getExemptedPersonId());
        assertEquals(TEST_PRINCIPAL_ID1, result.getMeta().getCreateId());
        if (result.getMeta().getCreateTime().before(before)) {
            fail("create time should not be before the call");
        }
        if (result.getMeta().getCreateTime().after(after)) {
            fail("create time should not be after the call");
        }
        if (result.getMeta().getUpdateTime().before(before)) {
            fail("update time should not be before the call");
        }
        if (result.getMeta().getUpdateTime().after(after)) {
            fail("update time should not be after the call");
        }
        assertEquals(TEST_PRINCIPAL_ID1, result.getMeta().getUpdateId());
        assertNotNull(result.getMeta().getVersionInd());

        // READ/get
        info = new ExemptionInfo(result);

        result = instance.getExemption(info.getId(), context);
        assertEquals(result.getId(), info.getId());
        assertEquals(result.getTypeKey(), info.getTypeKey());
        assertEquals(result.getStateKey(), info.getStateKey());
        assertEquals(result.getMeta().getCreateId(), info.getMeta().getCreateId());
        assertEquals(result.getMeta().getUpdateId(), info.getMeta().getUpdateId());
        assertEquals(result.getMeta().getCreateTime(), info.getMeta().getCreateTime());
        assertEquals(result.getMeta().getUpdateTime(), info.getMeta().getUpdateTime());
        assertEquals(result.getMeta().getVersionInd(), info.getMeta().getVersionInd());
        assertEquals(result.getMeta().getCreateId(), info.getMeta().getCreateId());

        // update
        info = new ExemptionInfo(result);
        info.setEffectiveDate(new Date());
        context.setPrincipalId(TEST_PRINCIPAL_ID2);
        before = new Date();
        result = instance.updateExemption(info.getId(), info, context);
        after = new Date();
        if (result == info) {
            fail("returned object should not be the same as the one passed in");
        }
        assertEquals (info.getId(), result.getId());
        assertEquals(info.getTypeKey(), result.getTypeKey());
        assertEquals(info.getStateKey(), result.getStateKey());
        assertEquals(info.getExemptedPersonId(), result.getExemptedPersonId());
        assertEquals(TEST_PRINCIPAL_ID1, result.getMeta().getCreateId());
        if (result.getMeta().getCreateTime().after(before)) {
            fail("create time should be before the update call");
        }
        if (result.getMeta().getUpdateTime().before(before)) {
            fail("update time should not be before the call");
        }
        if (result.getMeta().getUpdateTime().after(after)) {
            fail("update time should not be after the call");
        }
        assertEquals(TEST_PRINCIPAL_ID2, result.getMeta().getUpdateId());
        if (info.getMeta().getVersionInd().compareTo(result.getMeta().getVersionInd())>= 0) {
            fail ("version ind should be lexically greater than the old version id");
        }
        
        // delete
    }

    /**
     * Test of createExemptionRequest method, of class ExemptionServiceMockImpl.
     */
    @Test
    public void testExemptionRequestCrud() throws Exception {
        System.out.println("createExemptionRequest");
        ExemptionRequestInfo exemptionRequestInfo = new ExemptionRequestInfo();
        exemptionRequestInfo.setTypeKey(ExemptionServiceConstants.DATE_EXEMPTION_REQUEST_TYPE_KEY);
        exemptionRequestInfo.setStateKey(ExemptionServiceConstants.EXEMPTION_REQUEST_APPROVED_STATE_KEY);
        exemptionRequestInfo.setPersonId("person1");
        ContextInfo context = getContext();
        Date before = new Date();
        ExemptionRequestInfo result = instance.createExemptionRequest(exemptionRequestInfo, context);
        Date after = new Date();
        if (result == exemptionRequestInfo) {
            fail("returned object should not be the same as the one passed in");
        }
        assertNotNull(result.getId());
        assertEquals(exemptionRequestInfo.getTypeKey(), result.getTypeKey());
        assertEquals(exemptionRequestInfo.getStateKey(), result.getStateKey());
        assertEquals(exemptionRequestInfo.getPersonId(), result.getPersonId());
        assertEquals(TEST_PRINCIPAL_ID1, result.getMeta().getCreateId());
        if (result.getMeta().getCreateTime().before(before)) {
            fail("create time should not be before the call");
        }
        if (result.getMeta().getCreateTime().after(after)) {
            fail("create time should not be after the call");
        }
        if (result.getMeta().getUpdateTime().before(before)) {
            fail("update time should not be before the call");
        }
        if (result.getMeta().getUpdateTime().after(after)) {
            fail("update time should not be after the call");
        }
        assertEquals(TEST_PRINCIPAL_ID1, result.getMeta().getUpdateId());
        assertNotNull(result.getMeta().getVersionInd());
    }

}