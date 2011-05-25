package org.kuali.student.enrollment.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.kuali.student.common.test.spring.AbstractTransactionalDaoTest;
import org.kuali.student.common.test.spring.Dao;
import org.kuali.student.common.test.spring.PersistenceFileLocation;
import org.kuali.student.r2.core.class1.atp.dao.AtpDao;
import org.kuali.student.r2.core.class1.atp.model.AtpEntity;
import org.kuali.student.r2.core.class1.atp.model.AtpRichTextEntity;

@PersistenceFileLocation("classpath:META-INF/persistence_jta.xml")
public class TestAtpDao extends AbstractTransactionalDaoTest {
    @Dao(value = "org.kuali.student.r2.core.class1.atp.dao.AtpDao", testSqlFile = "classpath:ks-atp.sql")
    private AtpDao dao;
    
    @Test
    public void testGetAtp() {
        AtpEntity atp = dao.find("testAtpId1");
        assertNotNull(atp);
        assertEquals("testAtp1", atp.getName());         
        assertEquals("Desc", atp.getDescr().getPlain());   
    }
    
    @Test
    public void testCreateAtp() 
    {
        AtpEntity existingEntity = dao.find("testAtpId1");
        
        AtpEntity atp = new AtpEntity();
        atp.setName("atpTest");
        atp.setDescr(new AtpRichTextEntity("plain", "formatted"));
        atp.setAtpState(existingEntity.getAtpState());
        atp.setAtpType(existingEntity.getAtpType());
        dao.persist(atp);
        assertNotNull(atp.getId());
        AtpEntity atp2 = dao.find(atp.getId());
        assertEquals("atpTest", atp2.getName());         
        assertEquals("plain", atp2.getDescr().getPlain());   
    }
    
    @Test
    public void testDeleteAtp() 
    {
        AtpEntity atp = dao.find("testAtpId2");
        assertNotNull(atp);
        dao.remove(atp);
        atp = dao.find("testAtpId2");
        assertNull(atp);
    }
}
