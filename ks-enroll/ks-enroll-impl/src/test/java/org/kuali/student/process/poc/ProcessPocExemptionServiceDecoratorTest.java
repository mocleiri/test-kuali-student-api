/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kuali.student.process.poc;

import java.text.SimpleDateFormat;
import org.kuali.student.r2.core.exemption.dto.ExemptionInfo;
import java.util.List;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.core.exemption.service.ExemptionService;
import org.kuali.student.r2.core.exemption.service.ExemptionServiceMockImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kuali.student.r2.common.util.constants.AtpServiceConstants;
import org.kuali.student.r2.common.util.constants.ExemptionServiceConstants;
import org.kuali.student.r2.common.util.constants.ProcessServiceConstants;
import static org.junit.Assert.*;

/**
 *
 * @author nwright
 */
public class ProcessPocExemptionServiceDecoratorTest {

    public ProcessPocExemptionServiceDecoratorTest() {
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

    @Test
    public void testSomeMethod() throws Exception {
        ContextInfo context = new ContextInfo();
        context.setPrincipalId("POC-tester");

        ExemptionService exemptionService = new ExemptionServiceMockImpl();
        exemptionService = new ProcessPocExemptionServiceDecorator(exemptionService);
        List<ExemptionInfo> exemptions = null;
        exemptions = exemptionService.getExemptionsForPerson(ProcessPocConstants.PERSON_ID_JOHNNY_MANNING_2374, context);
        assertEquals(1, exemptions.size());

        exemptions = exemptionService.getExemptionsForPerson(ProcessPocConstants.PERSON_ID_EDDIE_PITTMAN_2406, context);
        assertEquals(1, exemptions.size());

        exemptions = exemptionService.getExemptionsForPerson(ProcessPocConstants.PERSON_ID_TRACY_BURTON_2132, context);
        assertEquals(1, exemptions.size());

        exemptions = exemptionService.getActiveExemptionsByTypeProcessAndCheckForPerson(
                ExemptionServiceConstants.CHECK_EXEMPTION_TYPE_KEY,
                ProcessServiceConstants.PROCESS_KEY_ELIGIBILITY_FOR_TERM,
                ProcessServiceConstants.CHECK_KEY_REGISTRATION_PERIOD_IS_OPEN,
                ProcessPocConstants.PERSON_ID_JOHNNY_MANNING_2374, context);
        assertEquals(1, exemptions.size()); 
        
        exemptions = exemptionService.getActiveExemptionsByTypeProcessAndCheckForPerson(
                ExemptionServiceConstants.MILESTONE_DATE_EXEMPTION_TYPE_KEY,
                ProcessServiceConstants.PROCESS_KEY_ELIGIBILITY_FOR_TERM,
                ProcessServiceConstants.CHECK_KEY_REGISTRATION_PERIOD_IS_NOT_CLOSED,
                ProcessPocConstants.PERSON_ID_EDDIE_PITTMAN_2406, context);
        assertEquals(1, exemptions.size()); 
        assertEquals (exemptions.get(0).getDateOverride().getEffectiveEndDate(), new SimpleDateFormat ("yyyy-MM-dd").parse("2011-12-31"));
        assertEquals (exemptions.get(0).getDateOverride().getMilestoneId(), AtpServiceConstants.MILESTONE_REGISTRATION_PERIOD_TYPE_KEY);
        
        exemptions = exemptionService.getActiveExemptionsByTypeProcessAndCheckForPerson(
                ExemptionServiceConstants.MILESTONE_DATE_EXEMPTION_TYPE_KEY,
                ProcessServiceConstants.PROCESS_KEY_ELIGIBILITY_FOR_TERM,
                ProcessServiceConstants.CHECK_KEY_REGISTRATION_PERIOD_IS_NOT_CLOSED,
                ProcessPocConstants.PERSON_ID_TRACY_BURTON_2132, context);
        assertEquals(1, exemptions.size()); 
        assertEquals (exemptions.get(0).getDateOverride().getEffectiveEndDate(), new SimpleDateFormat ("yyyy-MM-dd").parse("2011-11-30"));
        assertEquals (exemptions.get(0).getDateOverride().getMilestoneId(), AtpServiceConstants.MILESTONE_REGISTRATION_PERIOD_TYPE_KEY);        
    }
}