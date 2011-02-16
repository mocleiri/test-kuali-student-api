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

package org.kuali.student.common.validator.old;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.kuali.student.common.dictionary.old.dto.ConstraintDescriptor;
import org.kuali.student.common.dictionary.old.dto.ConstraintSelector;
import org.kuali.student.common.dictionary.old.dto.Field;
import org.kuali.student.common.dictionary.old.dto.FieldDescriptor;
import org.kuali.student.common.dictionary.old.dto.ObjectStructure;
import org.kuali.student.common.dictionary.old.dto.RequireConstraint;
import org.kuali.student.common.dictionary.old.dto.State;
import org.kuali.student.common.dictionary.old.dto.Type;
import org.kuali.student.common.dictionary.old.dto.ValidCharsConstraint;
import org.kuali.student.common.validation.dto.ValidationResultInfo;

public class ValidatorTest {	

	Validator val = null;
	
	public ValidatorTest() {
		val = new Validator();
		val.setDateParser(new ServerDateParser());
		val.setMessageService(null);
	}
		
	@Test     
    public void testRequired() {
    	    	
    	List<ValidationResultInfo> results = val.validateTypeStateObject( buildTestPerson1(), buildObjectStructure1());    
    	assertEquals(results.size(), 1);

    	assertEquals(results.get(0).getErrorLevel(), ValidationResultInfo.ErrorLevel.ERROR);
    	assertEquals(results.get(0).getMessage(), "validation.required");
    }
    

    @Test     
    public void testLengthRange() {
    	
    	ConstraintMockPerson p = buildTestPerson1();
    	p.setFirstName("thisisaveryveryverylo");
    	
    	List<ValidationResultInfo> results = val.validateTypeStateObject( p, buildObjectStructure1());    
    	assertEquals(results.size(), 2);

    	assertEquals(results.get(0).getErrorLevel(), ValidationResultInfo.ErrorLevel.ERROR);
    	assertEquals(results.get(0).getMessage(), "validation.lengthOutOfRange");
    }
    
    @Test     
    public void testMinLength() {
    	
    	ConstraintMockPerson p = buildTestPerson1();
    	p.setFirstName("t");

    	ObjectStructure o1 = buildObjectStructure1();
    	o1.getType().get(0).getState().get(0).getField().get(0).getConstraintDescriptor().getConstraint().get(0).setMaxLength(null);
    	
    	List<ValidationResultInfo> results = val.validateTypeStateObject( p, o1);    
    	assertEquals(results.size(), 2);

    	assertEquals(results.get(0).getErrorLevel(), ValidationResultInfo.ErrorLevel.ERROR);
    	assertEquals(results.get(0).getMessage(), "validation.minLengthFailed");
    }
    

    @Test
    public void testMinDateValue() {
    	ConstraintMockPerson p = buildTestPerson1();
    	ServerDateParser sp = new ServerDateParser();
    	p.setDob(sp.parseDate("1960-01-01"));
    	ObjectStructure o1 = buildObjectStructure1();
    	
    	List<ValidationResultInfo> results = val.validateTypeStateObject( p, o1);    
    	assertEquals(results.size(), 1);

    	assertEquals(results.get(0).getErrorLevel(), ValidationResultInfo.ErrorLevel.ERROR);
    	assertEquals(results.get(0).getMessage(), "validation.minValueFailed");    	
    }
    
    @Test     
    public void testMaxLength() {
    	
    	ConstraintMockPerson p = buildTestPerson1();
    	p.setFirstName("thisisaveryveryverylo");

    	ObjectStructure o1 = buildObjectStructure1();
    	o1.getType().get(0).getState().get(0).getField().get(0).getConstraintDescriptor().getConstraint().get(0).setMinLength(0);
    	
    	List<ValidationResultInfo> results = val.validateTypeStateObject( p, o1);    
    	assertEquals(results.size(), 2);

    	assertEquals(results.get(0).getErrorLevel(), ValidationResultInfo.ErrorLevel.ERROR);
    	assertEquals(results.get(0).getMessage(), "validation.maxLengthFailed");
    }
    
    @Test     
    public void testValidChars() {
    	    	
    	ConstraintMockPerson p = buildTestPerson1();
    	p.setFirstName("in$#valid");

    	ObjectStructure o1 = buildObjectStructure1();
    	
    	List<ValidationResultInfo> results = val.validateTypeStateObject( p, o1);    
    	assertEquals(results.size(), 2);

    	assertEquals(results.get(0).getErrorLevel(), ValidationResultInfo.ErrorLevel.ERROR);
    	assertEquals(results.get(0).getMessage(), "validation.validCharsFailed");
    }


    @Test     
    public void testDoubleValueRange() {
    	
    	ConstraintMockPerson p = buildTestPerson2();
    	p.setGpa(5.0);

    	ObjectStructure o1 = buildObjectStructure1();
    	
    	List<ValidationResultInfo> results = val.validateTypeStateObject( p, o1);    
    	assertEquals(results.size(), 1);

    	assertEquals(results.get(0).getErrorLevel(), ValidationResultInfo.ErrorLevel.ERROR);
    	assertEquals(results.get(0).getMessage(), "validation.outOfRange");
    }
    
    @Test
    public void testNestedStructures() {    	
    	ConstraintMockPerson p = buildTestPerson3();

    	ObjectStructure o = buildObjectStructure2();
    	
    	List<ValidationResultInfo> results = val.validateTypeStateObject( p, o);    
    	assertEquals(results.size(), 3);

    	assertEquals(results.get(0).getErrorLevel(), ValidationResultInfo.ErrorLevel.ERROR);
    	assertEquals(results.get(0).getMessage(), "validation.required");

    	assertEquals(results.get(1).getErrorLevel(), ValidationResultInfo.ErrorLevel.ERROR);
    	assertEquals(results.get(1).getMessage(), "validation.requiresField");
    	assertEquals(results.get(2).getMessage(), "validation.validCharsFailed");    	
    }
    
    
    public ConstraintMockPerson buildTestPerson1() {
    	ConstraintMockPerson person = new ConstraintMockPerson();
    	
    	person.setFirstName("first");
    	person.setLastName("last");
    	person.setEmail("first@test.com");
    	person.setType("STUDENT");
    	person.setState("CREATE");
    	person.setId("P1");
    	    	
    	return person;
    }

    
    public ConstraintMockPerson buildTestPerson2() {
    	ConstraintMockPerson person = new ConstraintMockPerson();
    	
    	person.setFirstName("first");
    	person.setLastName("last");
    	person.setEmail("first@test.com");
    	person.setType("STUDENT");
    	person.setState("CREATE");
    	person.setId("P1");
    	person.setGpa(3.5);
    	ServerDateParser dp = new ServerDateParser();    	
    	person.setDob(dp.parseDate("1978-01-01"));
    	
    	
    	return person;
    }

    
    public ConstraintMockPerson buildTestPerson3() {
    	ConstraintMockPerson person = new ConstraintMockPerson();
    	
    	person.setFirstName("first");
    	person.setLastName("last");
    	person.setEmail("first@test.com");
    	person.setType("STUDENT");
    	person.setState("CREATE");
    	person.setId("P1");
    	ServerDateParser dp = new ServerDateParser();    	
    	person.setDob(dp.parseDate("1978-01-01"));
    	
    	ConstraintMockAddress address = new ConstraintMockAddress();
    	address.setState("ACTIVE");
    	address.setType("MAILING");
    	address.setId("A1");
    	address.setCity("TLH");
    	address.setStateCode("FL");
    	address.setCountry("US");
    	address.setLine1("");
    	address.setLine2("line2value");
    	address.setPostalCode("32306");
    	    	
    	List<ConstraintMockAddress> addressL = new ArrayList<ConstraintMockAddress>();
    	addressL.add(address);
    	
    	person.setAddress(addressL);

    	return person;
    }
    
    public ObjectStructure buildObjectStructure2() {
    	
    	ObjectStructure addrStruct = new ObjectStructure();
    	addrStruct.setKey("addressInfo");
    	
    	List<Field> fields = new ArrayList<Field>();
    	    	
    	// Line 1 Field
    	Field f1 = new Field();
    	f1.setKey("line1");

    	FieldDescriptor fd1 = new FieldDescriptor();
    	fd1.setDataType("string");

    	ConstraintDescriptor cd1 = new ConstraintDescriptor();
    	ConstraintSelector cs1 = new ConstraintSelector();
    	cs1.setMaxLength("20");
    	cs1.setMinLength(2);
    	cs1.setMinOccurs(1);
    	
    	ValidCharsConstraint vcs1 = new ValidCharsConstraint();
    	vcs1.setValue("regex:[a-z]*");

    	ConstraintSelector cs12 = new ConstraintSelector();
    	cs12.setValidChars(vcs1);
    	
    	List<ConstraintSelector> csList1 = new ArrayList<ConstraintSelector>();
    	csList1.add(cs1);
    	csList1.add(cs12);
    	cd1.setConstraint(csList1);
    	
    	f1.setFieldDescriptor(fd1);
    	f1.setConstraintDescriptor(cd1);
    	
    	fields.add(f1);
    	

    	// Line 2 Field
    	Field f2 = new Field();
    	f2.setKey("line2");

    	FieldDescriptor fd2 = new FieldDescriptor();
    	fd2.setDataType("string");

    	ConstraintDescriptor cd2 = new ConstraintDescriptor();
    	ConstraintSelector cs2 = new ConstraintSelector();
    	cs2.setMaxLength("20");
    	cs2.setMinLength(2);
    	cs2.setMinOccurs(0);
    	
    	RequireConstraint rc2 = new RequireConstraint();
    	rc2.setField("line1");
    	List<RequireConstraint> rcList2 = new ArrayList<RequireConstraint>();
    	rcList2.add(rc2);
    	cs2.setRequireConstraint(rcList2);
    	
    	ValidCharsConstraint vcs2 = new ValidCharsConstraint();
    	vcs2.setValue("regex:[a-z]*");

    	ConstraintSelector cs22 = new ConstraintSelector();
    	cs22.setValidChars(vcs1);
    	
    	List<ConstraintSelector> csList2 = new ArrayList<ConstraintSelector>();
    	csList2.add(cs2);
    	csList2.add(cs22);
    	cd2.setConstraint(csList2);
    	
    	f2.setFieldDescriptor(fd2);
    	f2.setConstraintDescriptor(cd2);
    	
    	fields.add(f2);
    	
    	List<State> states = new ArrayList<State>();
    	State s = new State();
    	s.setKey("ACTIVE");
    	s.setField(fields);
    	
    	states.add(s);
    	
    	List<Type> types = new ArrayList<Type>();
    	Type t = new Type();    	
    	t.setKey("MAILING");
    	t.setState(states);
    	types.add(t);    	    	    	    	
    	addrStruct.setType(types);
    	
    	
    	ObjectStructure objStruct = buildObjectStructure1();
    	
    	// Complex nested obj struct
    	Field f3 = new Field();
    	f3.setKey("address");
    	
    	FieldDescriptor fd3 = new FieldDescriptor();
    	fd3.setDataType("complex");
    	fd3.setObjectStructure(addrStruct);
    	f3.setFieldDescriptor(fd3);
    	
    	List<Field> fieldList = objStruct.getType().get(0).getState().get(0).getField();
    	fieldList.add(f3);
    	objStruct.getType().get(0).getState().get(0).setField(fieldList);
    	
    	
    	return objStruct;
    }

    public ObjectStructure buildObjectStructure1() {    
    	ObjectStructure objStruct = new ObjectStructure();
    	objStruct.setKey("personInfo");
    	    	
    	List<Field> fields = new ArrayList<Field>();

    	
    	// Line 1 Field
    	Field f1 = new Field();
    	f1.setKey("firstName");

    	FieldDescriptor fd1 = new FieldDescriptor();
    	fd1.setDataType("string");

    	ConstraintDescriptor cd1 = new ConstraintDescriptor();
    	ConstraintSelector cs1 = new ConstraintSelector();
    	cs1.setMaxLength("20");
    	cs1.setMinLength(2);
    	cs1.setMinOccurs(1);
    	
    	ValidCharsConstraint vcs1 = new ValidCharsConstraint();
    	vcs1.setValue("regex:[a-z]*");

    	ConstraintSelector cs12 = new ConstraintSelector();
    	cs12.setValidChars(vcs1);
    	
    	List<ConstraintSelector> csList1 = new ArrayList<ConstraintSelector>();
    	csList1.add(cs1);
    	csList1.add(cs12);
    	cd1.setConstraint(csList1);
    	
    	f1.setFieldDescriptor(fd1);
    	f1.setConstraintDescriptor(cd1);
    	
    	fields.add(f1);
    	    	    	
    	// DOB Field
    	Field f2 = new Field();
    	f2.setKey("dob");

    	FieldDescriptor fd2 = new FieldDescriptor();
    	fd2.setDataType("date");

    	ConstraintDescriptor cd2 = new ConstraintDescriptor();
    	ConstraintSelector cs2 = new ConstraintSelector();
    	cs2.setMinValue("1970-01-01");
    	cs2.setMinOccurs(1);
    	   	
    	List<ConstraintSelector> csList2 = new ArrayList<ConstraintSelector>();
    	csList2.add(cs2);
    	cd2.setConstraint(csList2);
    	
    	f2.setFieldDescriptor(fd2);
    	f2.setConstraintDescriptor(cd2);
    	
    	fields.add(f2);
    	    	

    	// GAP
    	Field f3 = new Field();
    	f3.setKey("gpa");
    	
    	FieldDescriptor fd3 = new FieldDescriptor();
    	fd3.setDataType("double");
    	
    	ConstraintDescriptor cd3 = new ConstraintDescriptor();
    	ConstraintSelector cs3 = new ConstraintSelector();
    	cs3.setMinValue("1.0");
    	cs3.setMaxValue("4.0");
    	List<ConstraintSelector> csList3 = new ArrayList<ConstraintSelector>();
    	csList3.add(cs3);    	
    	cd3.setConstraint(csList3);
    	f3.setFieldDescriptor(fd3);
    	f3.setConstraintDescriptor(cd3);
    	fields.add(f3);
    	
    	List<State> states = new ArrayList<State>();
    	State s = new State();
    	s.setKey("CREATE");
    	s.setField(fields);
    	
    	states.add(s);
    	
    	List<Type> types = new ArrayList<Type>();
    	Type t = new Type();    	
    	t.setKey("STUDENT");
    	t.setState(states);
    	types.add(t);
    	
    	    	
    	objStruct.setType(types);

    	return objStruct;    	
    }
}
