package org.kuali.student.enrollment.class2.courseoffering.service.impl;

import junit.framework.Assert;
import org.junit.Ignore;
import org.kuali.student.enrollment.class1.lui.service.impl.LuiServiceDataLoader;
import org.kuali.student.enrollment.class2.courseoffering.dao.SeatPoolDefinitionDao;
import org.kuali.student.enrollment.courseoffering.dto.ActivityOfferingInfo;
import org.kuali.student.enrollment.courseoffering.dto.CourseOfferingInfo;
import org.kuali.student.enrollment.courseoffering.dto.FormatOfferingInfo;
import org.kuali.student.enrollment.courseoffering.dto.RegistrationGroupInfo;
import org.kuali.student.enrollment.courseoffering.dto.SeatPoolDefinitionInfo;
import org.kuali.student.enrollment.lui.dto.LuiInfo;
import org.kuali.student.enrollment.lui.dto.LuiLuiRelationInfo;
import org.kuali.student.enrollment.lui.service.LuiService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.kuali.rice.core.api.criteria.PredicateFactory;
import org.kuali.rice.core.api.criteria.QueryByCriteria;

import org.kuali.student.enrollment.courseoffering.service.CourseOfferingService;
import org.kuali.student.r2.common.dto.ContextInfo;

import org.kuali.student.r2.common.dto.RichTextInfo;
import org.kuali.student.r2.common.dto.StatusInfo;
import org.kuali.student.r2.common.exceptions.AlreadyExistsException;
import org.kuali.student.r2.common.exceptions.DataValidationErrorException;
import org.kuali.student.r2.common.exceptions.DoesNotExistException;
import org.kuali.student.r2.common.exceptions.InvalidParameterException;
import org.kuali.student.r2.common.exceptions.MissingParameterException;
import org.kuali.student.r2.common.exceptions.OperationFailedException;
import org.kuali.student.r2.common.exceptions.PermissionDeniedException;
import org.kuali.student.r2.common.exceptions.ReadOnlyException;
import org.kuali.student.r2.common.exceptions.VersionMismatchException;

import org.kuali.student.r2.common.util.ContextUtils;
import org.kuali.student.r2.core.class1.type.service.TypeServiceMockImpl;
import org.kuali.student.r2.core.constants.AtpServiceConstants;
import org.kuali.student.r2.common.util.constants.LuiServiceConstants;
import org.kuali.student.r2.core.constants.PopulationServiceConstants;
import org.kuali.student.r2.core.population.dto.PopulationInfo;
import org.kuali.student.r2.core.population.dto.PopulationRuleInfo;
import org.kuali.student.r2.core.population.infc.Population;
import org.kuali.student.r2.core.population.service.PopulationService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


/*
 * This class was used to test the class1 backed implementation of CourseOfferingService for CourseOffering, FormatOffering and ActivityOffering.
 *
 * For M4 it has been refactored.  Most of the test are now in TestCourseOfferingServiceMockImpl and only db dependent tests go here.
 *
 * See TestLprServiceImpl for an example.
 *
 * Once the tests can be run this should be unignored.
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:co-test-context.xml"})
//@ContextConfiguration(locations = {"co-test-with-class2-mock-context.xml"})

@TransactionConfiguration(transactionManager = "JtaTxManager", defaultRollback = true)
@Transactional
public class TestCourseOfferingServiceImplM4 {
    @Resource
    private CourseOfferingService coServiceImpl;
    @Resource
    private PopulationService populationService;
    @Resource
    private LuiService luiService;

    //private SeatPoolDefinitionDao seatPoolDefinitionDao;
    private ContextInfo contextInfo;

    @Resource
    protected LuiServiceDataLoader dataLoader = new LuiServiceDataLoader();

    private void before()  {
        contextInfo = ContextUtils.createDefaultContextInfo();
        contextInfo.setPrincipalId("admin");
        contextInfo.setAuthenticatedPrincipalId("admin");
        try {
            dataLoader.loadData();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private SeatPoolDefinitionInfo _constructSeatPoolDefinitionInfoById (Integer val) {
        String extension = "";
        if (val != null) {
            extension += val;
        }
        SeatPoolDefinitionInfo seatPoolDefinitionInfo = new SeatPoolDefinitionInfo();
        seatPoolDefinitionInfo.setName("TestSeatPoolDefinitionInfo-Id" + extension);
        seatPoolDefinitionInfo.setStateKey("TestSeatPoolDefinitionInfo-StateKey1" + extension);
        seatPoolDefinitionInfo.setTypeKey("TestSeatPoolDefinitionInfo-TypeKey1" + extension);
        seatPoolDefinitionInfo.setExpirationMilestoneTypeKey("TestSeatPoolDefinitionInfo-MilestoneKey1" + extension);
        seatPoolDefinitionInfo.setIsPercentage(false);
        seatPoolDefinitionInfo.setSeatLimit(50);
        seatPoolDefinitionInfo.setProcessingPriority(3);
        return seatPoolDefinitionInfo;
    }

    private List<SeatPoolDefinitionInfo> _constructSeatPoolDefinitionInfoByIdList() {
        SeatPoolDefinitionInfo ref = _constructSeatPoolDefinitionInfoById(2);
        SeatPoolDefinitionInfo three = _constructSeatPoolDefinitionInfoById(3);
        SeatPoolDefinitionInfo four = _constructSeatPoolDefinitionInfoById(4);
        SeatPoolDefinitionInfo five = _constructSeatPoolDefinitionInfoById(5);
        List<SeatPoolDefinitionInfo> poolList = new ArrayList<SeatPoolDefinitionInfo>();
        poolList.add(ref);
        poolList.add(three);
        poolList.add(four);
        poolList.add(five);
        return poolList;
    }

    private PopulationInfo _constructPopulationInfo(Integer val) {
        String extension = "";
        if (val != null) {
            extension += val;
        }
        PopulationInfo populationInfo = new PopulationInfo();
        populationInfo.setName("TestPop" + extension);
        RichTextInfo richTextInfo = new RichTextInfo();
        richTextInfo.setPlain("plain" + extension);
        richTextInfo.setFormatted("formatted" + extension);
        populationInfo.setDescr(richTextInfo);
        populationInfo.setStateKey(PopulationServiceConstants.POPULATION_ACTIVE_STATE_KEY);
        populationInfo.setTypeKey(PopulationServiceConstants.POPULATION_STUDENT_TYPE_KEY);
        return populationInfo;
    }

    private List<PopulationInfo> _constructPopulationList() {
        PopulationInfo ref = _constructPopulationInfo(2);
        PopulationInfo three = _constructPopulationInfo(3);
        PopulationInfo four = _constructPopulationInfo(4);
        PopulationInfo five = _constructPopulationInfo(5);
        List<PopulationInfo> popList = new ArrayList<PopulationInfo>();
        popList.add(ref);
        popList.add(three);
        popList.add(four);
        popList.add(five);
        return popList;
    }

    private PopulationRuleInfo _constructExclusionPopulationRuleInfo() {
        PopulationRuleInfo populationRuleInfo = new PopulationRuleInfo();
        populationRuleInfo.setName("TestPopRule");
        RichTextInfo richTextInfo = new RichTextInfo();
        richTextInfo.setPlain("rule-plain");
        richTextInfo.setFormatted("rule-formatted");
        populationRuleInfo.setDescr(richTextInfo);
        populationRuleInfo.setStateKey(PopulationServiceConstants.POPULATION_RULE_ACTIVE_STATE_KEY);
        populationRuleInfo.setTypeKey(PopulationServiceConstants.POPULATION_RULE_TYPE_EXCLUSION_KEY);
        return populationRuleInfo;
    }

    private RegistrationGroupInfo _constructRegistrationGroupInfoById (Integer val) {
        String extension = "";
        if (val != null) {
            extension += val;
        }
        RegistrationGroupInfo registrationGroupInfo = new RegistrationGroupInfo();
        registrationGroupInfo.setName("TestRegistrationGroupInfo-Id" + extension);
        registrationGroupInfo.setStateKey("TestRegistrationGroupInfo-StateKey1" + extension);
        registrationGroupInfo.setTypeKey("TestRegistrationGroupInfo-TypeKey1" + extension);
        registrationGroupInfo.setFormatOfferingId("Lui-6");
        registrationGroupInfo.setCourseOfferingId("Lui-1");
        registrationGroupInfo.setTermId("20122");
        registrationGroupInfo.setRegistrationCode("02" + extension);

        List<String> activityOfferingIds = new ArrayList<String>();
        activityOfferingIds.add("Lui-2");
        activityOfferingIds.add("Lui-5");

        registrationGroupInfo.setActivityOfferingIds(activityOfferingIds);
        registrationGroupInfo.setIsGenerated(true);
        registrationGroupInfo.setTypeKey(LuiServiceConstants.REGISTRATION_GROUP_TYPE_KEY);
        registrationGroupInfo.setStateKey(LuiServiceConstants.REGISTRATION_GROUP_OPEN_STATE_KEY);
        return registrationGroupInfo;
    }

    private RegistrationGroupInfo _constructRegistrationGroupInfo2() {
        String extension = "-foo";
        RegistrationGroupInfo registrationGroupInfo = new RegistrationGroupInfo();
        registrationGroupInfo.setName("TestRegistrationGroupInfo-Id" + extension);
        registrationGroupInfo.setStateKey("TestRegistrationGroupInfo-StateKey1" + extension);
        registrationGroupInfo.setTypeKey("TestRegistrationGroupInfo-TypeKey1" + extension);
        registrationGroupInfo.setFormatOfferingId("Lui-6");
        registrationGroupInfo.setCourseOfferingId("Lui-1");
        registrationGroupInfo.setTermId("20122");
        registrationGroupInfo.setRegistrationCode("02" + extension);

        List<String> activityOfferingIds = new ArrayList<String>();
        activityOfferingIds.add("Lui-2");
        activityOfferingIds.add("Lui-Lab2");

        registrationGroupInfo.setActivityOfferingIds(activityOfferingIds);
        registrationGroupInfo.setIsGenerated(true);
        registrationGroupInfo.setTypeKey(LuiServiceConstants.REGISTRATION_GROUP_TYPE_KEY);
        registrationGroupInfo.setStateKey(LuiServiceConstants.REGISTRATION_GROUP_OPEN_STATE_KEY);
        return registrationGroupInfo;
    }

    private List<RegistrationGroupInfo> _constructRegistrationGroupInfoByIdList() {
        RegistrationGroupInfo ref = _constructRegistrationGroupInfoById(2);
        RegistrationGroupInfo three = _constructRegistrationGroupInfoById(3);
        RegistrationGroupInfo four = _constructRegistrationGroupInfoById(4);
        RegistrationGroupInfo five = _constructRegistrationGroupInfoById(5);
        List<RegistrationGroupInfo> rgList = new ArrayList<RegistrationGroupInfo>();
        rgList.add(ref);
        rgList.add(three);
        rgList.add(four);
        rgList.add(five);
        return rgList;
    }

    // ============================================== TESTS ======================================================
    @Test
    public void testGetAndRemoveRegistrationGroupsByFormatOffering() {
        before();

        RegistrationGroupInfo info = _constructRegistrationGroupInfoById(null);
        RegistrationGroupInfo info2 = _constructRegistrationGroupInfo2();
        try {
            String foId = "Lui-6";
            RegistrationGroupInfo created = coServiceImpl.createRegistrationGroup(foId, LuiServiceConstants.REGISTRATION_GROUP_TYPE_KEY, info, contextInfo);
            RegistrationGroupInfo created2 = coServiceImpl.createRegistrationGroup(foId, LuiServiceConstants.REGISTRATION_GROUP_TYPE_KEY, info2, contextInfo);

            List<RegistrationGroupInfo> rgInfos = coServiceImpl.getRegistrationGroupsByFormatOffering(foId, contextInfo);
            assertEquals(2, rgInfos.size());
            for (RegistrationGroupInfo rgInfo: rgInfos) {
                List<String> aoIds = rgInfo.getActivityOfferingIds();
                for (String aoId: aoIds) {
                    // I would prefer to get AO via the coService, but the Lui Loader only handles LUIs
                    LuiInfo luiInfo = luiService.getLui(aoId, contextInfo);
                    assertNotNull(luiInfo); // Should be trivially true
                }
            }
            // Now remove the reg groups
            coServiceImpl.deleteRegistrationGroupsByFormatOffering(foId, contextInfo);
            List<RegistrationGroupInfo> rgInfos2 = coServiceImpl.getRegistrationGroupsByFormatOffering(foId, contextInfo);
            assertEquals(0, rgInfos2.size());
            for (RegistrationGroupInfo rgInfo: rgInfos) {
                boolean found = true;
                try {
                    // Should not be able to find the old registration groups
                    coServiceImpl.getRegistrationGroup(rgInfo.getId(), contextInfo);
                } catch (DoesNotExistException e) { // Should use DoesNot
                    found = false;
                }
                if (found) {
                    assert(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }
    }
    @Test
    public void testCreateUpdateRegistrationGroupInfoGet() {
        before();

        RegistrationGroupInfo info = _constructRegistrationGroupInfoById(null);
        try {
            RegistrationGroupInfo created = coServiceImpl.createRegistrationGroup("Lui-6", LuiServiceConstants.REGISTRATION_GROUP_TYPE_KEY, info, contextInfo);
            RegistrationGroupInfo fetched = coServiceImpl.getRegistrationGroup(created.getId(), contextInfo);
            Assert.assertEquals(created.getName(), fetched.getName());
            Assert.assertEquals(created.getStateKey(), fetched.getStateKey());
            Assert.assertEquals(created.getTypeKey(), fetched.getTypeKey());
            Assert.assertEquals(created.getFormatOfferingId(), fetched.getFormatOfferingId());
            Assert.assertEquals(created.getRegistrationCode(), fetched.getRegistrationCode());
            Assert.assertEquals(created.getCourseOfferingId(), fetched.getCourseOfferingId());
            Assert.assertEquals(created.getId(), fetched.getId());

            List<LuiLuiRelationInfo> llrs = luiService.getLuiLuiRelationsByLui(fetched.getId(), contextInfo);

            List<String> activityOfferingIds = new ArrayList<String>();
            activityOfferingIds.add("Lui-2");
            activityOfferingIds.add("Lui-Lab2");
            fetched.setActivityOfferingIds(null);
            fetched.setActivityOfferingIds(activityOfferingIds);
            fetched.setFormatOfferingId(null);
            fetched.setFormatOfferingId("Lui-7");
            RegistrationGroupInfo updated = coServiceImpl.updateRegistrationGroup(fetched.getId(), fetched, contextInfo);

            List<LuiLuiRelationInfo> llrs1 = luiService.getLuiLuiRelationsByLui(updated.getId(), contextInfo);
            coServiceImpl.deleteRegistrationGroup(updated.getId(), contextInfo);

            List<LuiLuiRelationInfo> llrsAfter = luiService.getLuiLuiRelationsByLui(updated.getId(), contextInfo);
            try{
                RegistrationGroupInfo fetchedAfterDelete = coServiceImpl.getRegistrationGroup(updated.getId(), contextInfo);
                //This should throw an exception since the reg group was deleted
                assert(false);
            }catch(DoesNotExistException e){
                assert(true);
            }
            System.out.println("here");


        } catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }
    }

    @Test
    public void testPopulation() {
        before();
        List<PopulationInfo> popList = _constructPopulationList();
        try {
            PopulationInfo refCreated = populationService.createPopulation(popList.get(0), contextInfo);
            PopulationInfo threeCreated = populationService.createPopulation(popList.get(1), contextInfo);
            PopulationInfo fourCreated = populationService.createPopulation(popList.get(2), contextInfo);
            // Now the pop rule
            PopulationRuleInfo ruleInfo = _constructExclusionPopulationRuleInfo();
            ruleInfo.setReferencePopulationId(refCreated.getId());
            List<String> childIds = new ArrayList<String>();
            childIds.add(threeCreated.getId());
            childIds.add(fourCreated.getId());
            ruleInfo.setChildPopulationIds(childIds);
            // Create the rule info
            PopulationRuleInfo ruleInfoCreated = populationService.createPopulationRule(ruleInfo, contextInfo);
            // Fetch it
            PopulationRuleInfo ruleInfoFetched = populationService.getPopulationRule(ruleInfoCreated.getId(), contextInfo);
            PopulationInfo combined = populationService.createPopulation(popList.get(3), contextInfo);
            populationService.applyPopulationRuleToPopulation(ruleInfoFetched.getId(), combined.getId(), contextInfo);
            SeatPoolDefinitionInfo info = _constructSeatPoolDefinitionInfoById(null);
            info.setPopulationId(combined.getId());
            SeatPoolDefinitionInfo created = coServiceImpl.createSeatPoolDefinition(info, contextInfo);
            PopulationInfo retrieved = populationService.getPopulation(created.getPopulationId(), contextInfo);
            assertEquals(combined.getId(), retrieved.getId());
        } catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }
    }

    @Test
    public void testCreateSeatPoolDefinitionGet() {
        before();
        SeatPoolDefinitionInfo info = _constructSeatPoolDefinitionInfoById(null);
        try {
            SeatPoolDefinitionInfo created = coServiceImpl.createSeatPoolDefinition(info, contextInfo);
            SeatPoolDefinitionInfo fetched = coServiceImpl.getSeatPoolDefinition(created.getId(), contextInfo);
            Assert.assertEquals(info.getName(), fetched.getName());
            Assert.assertEquals(info.getStateKey(), fetched.getStateKey());
            Assert.assertEquals(info.getTypeKey(), fetched.getTypeKey());
            Assert.assertEquals(info.getExpirationMilestoneTypeKey(), fetched.getExpirationMilestoneTypeKey());
            Assert.assertEquals(info.getIsPercentage(), fetched.getIsPercentage());
            Assert.assertEquals(info.getSeatLimit(), fetched.getSeatLimit());
            Assert.assertEquals(info.getProcessingPriority(), fetched.getProcessingPriority());
        } catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }
    }

    @Test
    public void testCreateSeatPoolDefinitionUpdateDelete() {
        before();
        SeatPoolDefinitionInfo info = _constructSeatPoolDefinitionInfoById(null);
        try {
            SeatPoolDefinitionInfo created = coServiceImpl.createSeatPoolDefinition(info, contextInfo);
            SeatPoolDefinitionInfo fetched = coServiceImpl.getSeatPoolDefinition(created.getId(), contextInfo);
            fetched.setSeatLimit(5);
            fetched.setExpirationMilestoneTypeKey(AtpServiceConstants.MILESTONE_SEATPOOL_FIRST_DAY_OF_CLASSES_TYPE_KEY);
            coServiceImpl.updateSeatPoolDefinition(fetched.getId(), fetched, contextInfo);
            SeatPoolDefinitionInfo fetched2 = coServiceImpl.getSeatPoolDefinition(created.getId(), contextInfo);
            assertEquals(new Integer(5), fetched2.getSeatLimit());
            assertEquals(AtpServiceConstants.MILESTONE_SEATPOOL_FIRST_DAY_OF_CLASSES_TYPE_KEY, fetched2.getExpirationMilestoneTypeKey());
            coServiceImpl.deleteSeatPoolDefinition(fetched.getId(), contextInfo);
            boolean found = true;
            try {
                coServiceImpl.getSeatPoolDefinition(fetched.getId(), contextInfo);
            } catch (DoesNotExistException e) {
                found = false;
            }
            if (found) {
                assert(false); // Exception should have been thrown
            }
        } catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }
    }

    //@Ignore
    @Test
    public void testGenerateRegistrationGroupsSimple() throws DoesNotExistException,
       			InvalidParameterException, MissingParameterException,
       			OperationFailedException, PermissionDeniedException, AlreadyExistsException {

        before();
        TypeServiceMockImpl instance = new TypeServiceMockImpl();   //?

        List<RegistrationGroupInfo> rgList = coServiceImpl.generateRegistrationGroupsForFormatOffering("Lui-6", contextInfo);

       	org.junit.Assert.assertEquals(1, rgList.size());

    }

}