/**
 * Copyright 2010 The Kuali Foundation Licensed under the Educational Community
 * License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.osedu.org/licenses/ECL-2.0 Unless required by applicable law or
 * agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.student.enrollment.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.kuali.student.common.test.spring.AbstractTransactionalDaoTest;
import org.kuali.student.common.test.spring.Dao;
import org.kuali.student.common.test.spring.PersistenceFileLocation;
import org.kuali.student.enrollment.class1.lpr.dao.LprDao;
import org.kuali.student.enrollment.class1.lpr.dao.LprTypeDao;
import org.kuali.student.enrollment.class1.lpr.model.LuiPersonRelationEntity;
import org.kuali.student.enrollment.class1.lpr.service.utilities.Constants;
import org.kuali.student.r2.core.class1.state.dao.StateDao;
import org.kuali.student.r2.common.util.constants.LuiPersonRelationServiceConstants;

@PersistenceFileLocation("classpath:META-INF/acal-persistence.xml")
public class TestLprDao extends AbstractTransactionalDaoTest {
    @Dao(value = "org.kuali.student.enrollment.class1.lpr.dao.LprDao", testSqlFile = "classpath:ks-lpr.sql")
    private LprDao dao;

    @Dao(value = "org.kuali.student.r2.core.class1.state.dao.StateDao")
    private StateDao stateDao;

    @Dao(value = "org.kuali.student.enrollment.class1.lpr.dao.LprTypeDao")
    private LprTypeDao lprTypeDao;

    private static String LUIID2 = "testLuiId2";
    private static String PERSONID2 = "testPersonId2";


    @Test
    public void testGetLpr() {
        LuiPersonRelationEntity lpr = dao.find("testLprId1");
        assertNotNull(lpr);
        assertEquals("testLuiId1", lpr.getLuiId());
        assertEquals("testPersonId1", lpr.getPersonId());
    }

    @Test
    public void testCreateLpr() {
        LuiPersonRelationEntity lpr = createEmptyLPREntity();
        dao.persist(lpr);
        assertNotNull(lpr.getId());
        LuiPersonRelationEntity lpr2 = dao.find(lpr.getId());
        assertEquals(LUIID2, lpr2.getLuiId());
        assertEquals(PERSONID2, lpr2.getPersonId());
    }

    @Test
    public void testMergeLpr() {
        LuiPersonRelationEntity lpr = createEmptyLPREntity();
        dao.persist(lpr);
        assertNotNull(lpr.getId());
        LuiPersonRelationEntity lpr2 = dao.find(lpr.getId());
        assertEquals(LUIID2, lpr2.getLuiId());
        assertEquals(PERSONID2, lpr2.getPersonId());
        lpr2.setLuiId(Constants.LUI_ID1);
        dao.merge(lpr2);
        assertEquals(Constants.LUI_ID1, lpr2.getLuiId());
    }

    @Test
    public void testDeleteLpr() {
        LuiPersonRelationEntity lpr = createEmptyLPREntity();
        dao.persist(lpr);
        assertNotNull(lpr.getId());
        LuiPersonRelationEntity lpr2 = dao.find(lpr.getId());
        assertNotNull(lpr2);
        String id = lpr2.getId();
        dao.remove(lpr2);
        lpr2 = dao.find(id);
        assertNull(lpr2);
    }

    @Test
    public void testGetByLuiId() {
        List<LuiPersonRelationEntity> lprs = dao.getByLuiId("testLuiId1");
        assertNotNull(lprs);
        assertEquals(1, lprs.size());
        LuiPersonRelationEntity lpr = lprs.get(0);
        //assertEquals("testPersonId1", lprs.get(0).getPersonId());
        assertEquals("testLuiId1", lpr.getLuiId());
        assertEquals("testPersonId1", lpr.getPersonId());
    }


    private LuiPersonRelationEntity createEmptyLPREntity() {
        LuiPersonRelationEntity returnLpr = new LuiPersonRelationEntity();
        returnLpr.setLuiId(LUIID2);
        returnLpr.setPersonId(PERSONID2);
        returnLpr.setPersonRelationState(stateDao.find("kuali.lpr.state.registered"));
        returnLpr.setPersonRelationType(lprTypeDao.find("kuali.lpr.type.registrant"));
        return returnLpr;
    }

}