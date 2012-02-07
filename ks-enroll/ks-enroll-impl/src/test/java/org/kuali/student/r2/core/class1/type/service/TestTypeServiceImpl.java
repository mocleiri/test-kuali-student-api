package org.kuali.student.r2.core.class1.type.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.student.common.dto.StatusInfo;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.exceptions.*;
import org.kuali.student.r2.common.util.constants.AtpServiceConstants;
import org.kuali.student.r2.core.type.dto.TypeInfo;
import org.kuali.student.r2.core.type.service.TypeService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static junit.framework.Assert.*;

/**
 * This class //TODO ...
 *
 * @author Kuali Student Team
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:em-test-context.xml"})
@TransactionConfiguration(transactionManager = "JtaTxManager", defaultRollback = true)
@Transactional
public class TestTypeServiceImpl {
     private TypeService typeService;

    public TypeService getTypeService() {
        return typeService;
    }

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    @Test
    public void testGetType() throws InvalidParameterException, MissingParameterException, PermissionDeniedException, OperationFailedException {

        TypeInfo exitingType = null;
        try {
            exitingType = typeService.getType("kuali.atp.type.FallSpring", new ContextInfo());
            assertNotNull(exitingType);

            TypeInfo nonExistingType =  typeService.getType("kuali.atp.type.FallSpring1", new ContextInfo());

            assertNotNull(nonExistingType);

        } catch (DoesNotExistException e) {
                fail("Failed with exception");
        }



    }

    @Test
    public void  testGetTypesByRefObjectUri() throws InvalidParameterException,  PermissionDeniedException, OperationFailedException {

        List<TypeInfo> exitingType = null;
        try {
            exitingType = typeService.getTypesByRefObjectUri(AtpServiceConstants.REF_OBJECT_URI_ATP, new ContextInfo());

            assertNotNull(exitingType);

            List<TypeInfo> nullType =  typeService.getTypesByRefObjectUri(null, new ContextInfo());

            } catch (DoesNotExistException e) {
                fail("Failed with exception");
            } catch (MissingParameterException e) {

            }

    }

    public void testCreateType() throws InvalidParameterException, DataValidationErrorException, MissingParameterException, AlreadyExistsException, ReadOnlyException, PermissionDeniedException, OperationFailedException {

        TypeInfo typeToCreate = new TypeInfo();
        typeToCreate.setKey("kuali.atp.type.TestAtp");
        typeToCreate.setEffectiveDate(new Date());
        typeToCreate.setName("ATP test create");
        typeToCreate.setRefObjectUri(AtpServiceConstants.REF_OBJECT_URI_ATP);

        TypeInfo createdType = typeService.createType("kuali.atp.type.TestAtp", typeToCreate, new ContextInfo());

        assertNotNull(createdType);

        try{
                    TypeInfo typeToCreateFail = new TypeInfo();
                    typeToCreateFail.setKey("kuali.atp.type.TestAtp");
                    typeToCreateFail.setEffectiveDate(new Date());
                    typeToCreateFail.setName("ATP test create");
                    typeToCreateFail.setRefObjectUri(AtpServiceConstants.REF_OBJECT_URI_ATP);
                    TypeInfo noType = typeService.createType("kuali.atp.type.TestAtp", typeToCreate, new ContextInfo());

        } catch (MissingParameterException ex){
                 assertNotNull(ex);
        }

    }

    public void testDeleteType() throws InvalidParameterException, DataValidationErrorException, MissingParameterException, AlreadyExistsException, ReadOnlyException, PermissionDeniedException, OperationFailedException {
            TypeInfo typeToCreate = new TypeInfo();
            typeToCreate.setKey("kuali.atp.type.TestAtp");
            typeToCreate.setEffectiveDate(new Date());
            typeToCreate.setName("ATP test create");
            typeToCreate.setRefObjectUri(AtpServiceConstants.REF_OBJECT_URI_ATP);

            TypeInfo createdType = typeService.createType("kuali.atp.type.TestAtp", typeToCreate, new ContextInfo());
            assertNotNull(createdType);

                try {
                    org.kuali.student.r2.common.dto.StatusInfo status = typeService.deleteType("kuali.atp.type.TestAtp", new ContextInfo());
                    assertEquals( status.getIsSuccess(), new Boolean(true));

                } catch (DoesNotExistException e) {
                    fail();
                }

    }

}
