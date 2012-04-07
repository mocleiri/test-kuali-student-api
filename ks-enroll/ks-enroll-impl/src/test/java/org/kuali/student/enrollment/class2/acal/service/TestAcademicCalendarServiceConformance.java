package org.kuali.student.enrollment.class2.acal.service;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.kuali.student.enrollment.acal.dto.AcademicCalendarInfo;
import org.kuali.student.enrollment.acal.service.AcademicCalendarService;
import org.kuali.student.mock.utilities.TestHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Ignore
public class TestAcademicCalendarServiceConformance {
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
    
    private AcademicCalendarService service = null;
    
    public AcademicCalendarService getService() {
        if (service == null) {
            ApplicationContext appContext =
                new ClassPathXmlApplicationContext(new String[]{ "classpath:testContext.xml"});
            service = (AcademicCalendarService) appContext.getBean("mockAcademicCalendarService");
        }
        return service;
    }
    
    @Test
    public void testCreateAcademicCalendar() throws Exception {
        AcademicCalendarInfo academicCalendarInfo = new AcademicCalendarInfo();
        
        academicCalendarInfo.setEndDate(new Date()) ;
        academicCalendarInfo.setStartDate(new Date() );
        academicCalendarInfo.setName("First AC");
	
        AcademicCalendarInfo newAC = new AcademicCalendarInfo(academicCalendarInfo) ;
	
        AcademicCalendarInfo createdAC  = getService().createAcademicCalendar("test1AC", academicCalendarInfo, TestHelper.getContext1());
        assertNotNull(createdAC);
        assertNotNull(createdAC.getId());
    }
    
    @Test
    public void testGetAcademicCalendar() throws Exception {
        AcademicCalendarInfo retrievedAC  = getService().getAcademicCalendar("test1AC",TestHelper.getContext1());
        assertNotNull(retrievedAC);
        assertNotNull(retrievedAC.getId());
    }
}
