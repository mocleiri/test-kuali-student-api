package org.kuali.student.common.assembly.dictionary;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Map;

import org.junit.Test;
import org.kuali.student.core.assembly.data.ConstraintMetadata;
import org.kuali.student.core.assembly.data.Metadata;
import org.kuali.student.core.assembly.dictionary.MetadataServiceImpl;
import org.kuali.student.core.dictionary.service.impl.DictionaryServiceImpl;

public class TestMetadataServiceImpl {

	DictionaryServiceImpl dictionaryDelegate = new DictionaryServiceImpl("classpath:test-validator-context.xml");
    
    
    @Test
    public void testMetadataService(){
        MockDictionaryService mockDictionaryService = new MockDictionaryService();
        mockDictionaryService.setDictionaryServiceDelegate(dictionaryDelegate);
        
        MetadataServiceImpl metadataService = new MetadataServiceImpl(mockDictionaryService);
        Metadata metadata = metadataService.getMetadata("objectStructure1");
        
        Map<String, Metadata> properties = metadata.getProperties();
        assertTrue(properties.containsKey("firstName"));
        assertTrue(properties.containsKey("dob"));
        assertTrue(properties.containsKey("gpa"));
        
        assertEquals(1, properties.get("firstName").getConstraints().size());
        assertEquals(1, properties.get("dob").getConstraints().size());
        assertEquals(1, properties.get("gpa").getConstraints().size());
        
        ConstraintMetadata nameConstraints = properties.get("firstName").getConstraints().get(0);
        assertTrue(nameConstraints.getMinLength()==2);
        assertTrue(nameConstraints.getMaxLength()==20);
        
        //Check requiredness for default state of draft
        ConstraintMetadata gpaConstraints = properties.get("gpa").getConstraints().get(0);
        assertTrue(gpaConstraints.isRequiredForNextState());                

        //Check requiredness for state of RETIRED (there should be no next state)
        metadata = metadataService.getMetadata("objectStructure1", "RETIRED");
        gpaConstraints = metadata.getProperties().get("gpa").getConstraints().get(0);
        assertFalse(gpaConstraints.isRequiredForNextState());
        
        
        //Check type and nested state
        metadata = metadataService.getMetadata("addressInfo");
        ConstraintMetadata addrLineConstraint = metadata.getProperties().get("line1").getConstraints().get(0);
        assertEquals(2, addrLineConstraint.getMinLength().intValue());
        assertEquals(1, addrLineConstraint.getMinOccurs().intValue());
        assertEquals(20, addrLineConstraint.getMaxLength().intValue());

        metadata = metadataService.getMetadata("addressInfo", "US_ADDRESS", "SUBMITTED");
        addrLineConstraint = metadata.getProperties().get("line1").getConstraints().get(0);
        assertEquals(5, addrLineConstraint.getMinLength().intValue());
        assertEquals(1, addrLineConstraint.getMinOccurs().intValue());
        assertEquals(20, addrLineConstraint.getMaxLength().intValue());

        metadata = metadataService.getMetadata("addressInfo", "US_ADDRESS", "DRAFT");
        addrLineConstraint = metadata.getProperties().get("line1").getConstraints().get(0);
        assertEquals(5, addrLineConstraint.getMinLength().intValue());
        assertEquals(0, addrLineConstraint.getMinOccurs().intValue());
        assertEquals(20, addrLineConstraint.getMaxLength().intValue());

        metadata = metadataService.getMetadata("addressInfo", "FOREIGN_ADDRESS", "DRAFT");
        addrLineConstraint = metadata.getProperties().get("line1").getConstraints().get(0);
        assertEquals(2, addrLineConstraint.getMinLength().intValue());
        assertEquals(0, addrLineConstraint.getMinOccurs().intValue());
        assertEquals(100, addrLineConstraint.getMaxLength().intValue());
    }
    
    
}
