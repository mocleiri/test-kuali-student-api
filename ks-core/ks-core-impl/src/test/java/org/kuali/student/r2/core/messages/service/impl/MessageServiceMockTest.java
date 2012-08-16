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

package org.kuali.student.r2.core.messages.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.kuali.student.common.test.util.ContextInfoTestUtility;
import org.kuali.student.core.messages.service.impl.MessageServiceMock;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.dto.LocaleInfo;
import org.kuali.student.r2.common.exceptions.DoesNotExistException;
import org.kuali.student.r2.common.exceptions.InvalidParameterException;
import org.kuali.student.r2.common.exceptions.MissingParameterException;
import org.kuali.student.r2.common.exceptions.OperationFailedException;
import org.kuali.student.r2.common.exceptions.PermissionDeniedException;
import org.kuali.student.r2.common.exceptions.ReadOnlyException;
import org.kuali.student.r2.common.exceptions.VersionMismatchException;
import org.kuali.student.r2.common.messages.dto.MessageInfo;


/**
 * This is a test for the mock message service impl. 
 * 
 * @author Kuali Student Team
 *
 */
public class MessageServiceMockTest {
    MessageServiceMock messageService;
    ContextInfo contextInfo;
    
    @Before
    public void setup(){
        contextInfo = ContextInfoTestUtility.getEnglishContextInfo();
        messageService = new MessageServiceMock();
        List<String> messageFiles = Arrays.asList("classpath:messages-test-beans.xml");
        messageService.setMessageFiles(messageFiles);        
    }
    
    @Test
    public void testGetLocales() throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException
    {
        List<LocaleInfo> locales = messageService.getLocales(contextInfo);
        
        assertEquals(2, locales.size());
    }
    
    @Test
    public void testGetMessageGroup() throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException{
        List<String> groups = messageService.getMessageGroupKeys(contextInfo);

        assertEquals(2, groups.size());
    }
    
    @Test
    public void testGetMessage() throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException{
        LocaleInfo usLocale = new LocaleInfo();
        usLocale.setLocaleLanguage("US");
        
        LocaleInfo caLocale = new LocaleInfo();
        caLocale.setLocaleLanguage("CA");
        
        MessageInfo message = messageService.getMessage(usLocale, "Address", "State", contextInfo);
        assertEquals(message.getLocale().getLocaleLanguage(), "US");
        assertEquals(message.getGroupName(), "Address");
        assertEquals(message.getKey(), "State");
        assertEquals(message.getValue(), "State:");
        message = messageService.getMessage(caLocale, "Address", "State", contextInfo);
        assertEquals(message.getLocale().getLocaleLanguage(), "CA");
        assertEquals(message.getGroupName(), "Address");
        assertEquals(message.getKey(), "State");
        assertEquals(message.getValue(), "Province:");
    }
    
    @Test
    public void testGetMessages() throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException{
        LocaleInfo usLocale = new LocaleInfo();
        usLocale.setLocaleLanguage("US");
        
        LocaleInfo caLocale = new LocaleInfo();
        caLocale.setLocaleLanguage("CA");
        
        List<MessageInfo> messages = messageService.getMessages(usLocale, "Address", contextInfo);
        assertEquals(2, messages.size());
        for(MessageInfo m: messages){
            assertEquals(m.getLocale().getLocaleLanguage(), "US");
            assertEquals(m.getGroupName(), "Address");
            assertTrue(m.getKey().equals("State") ? ("State:".equals(m.getValue())):("Enter the US city where you live:".equals(m.getValue())));
        }
        messages = messageService.getMessages(caLocale, "Address", contextInfo);
        assertEquals(2, messages.size());
        for(MessageInfo m: messages){
            assertEquals(m.getLocale().getLocaleLanguage(), "CA");
            assertEquals(m.getGroupName(), "Address");
            assertTrue(m.getKey().equals("State") ? ("Province:".equals(m.getValue())):("Enter the Canadian city where you live:".equals(m.getValue())));
        }
        messages = messageService.getMessages(usLocale, "Name", contextInfo);
        assertEquals(1, messages.size());
        for(MessageInfo m: messages){
            assertEquals(m.getLocale().getLocaleLanguage(), "US");
            assertEquals(m.getGroupName(), "Name");
            assertEquals(m.getValue(), "Enter your last name:");
        }
    }
    
    @Test
    public void testGetMessagesByGroup() throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException{
        LocaleInfo usLocale = new LocaleInfo();
        usLocale.setLocaleLanguage("US");
        
        LocaleInfo caLocale = new LocaleInfo();
        caLocale.setLocaleLanguage("CA");
        
        List<String> groupKeys = new ArrayList<String>();
        groupKeys.add("Address");
        groupKeys.add("Name");
        
        List<MessageInfo> messages = messageService.getMessagesByGroups(usLocale, groupKeys, contextInfo);
        assertEquals(3, messages.size());
        for(MessageInfo m: messages){
            assertEquals(m.getLocale().getLocaleLanguage(), "US");
            assertTrue(m.getGroupName().equals("Address") || m.getGroupName().equals("Name"));
        }
        
        messages = messageService.getMessagesByGroups(caLocale, groupKeys, contextInfo);
        assertEquals(3, messages.size());
        for(MessageInfo m: messages){
            assertEquals(m.getLocale().getLocaleLanguage(), "CA");
            assertTrue(m.getGroupName().equals("Address") || m.getGroupName().equals("Name"));
        }
    }
    
    @Test
    @Ignore
    //This test (copied from service test impl) is updating message key fields in addition to message
    //Is this the behavior we want?
    public void testUpdateMessage() throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException, ReadOnlyException, VersionMismatchException{
        LocaleInfo localeInfo = new LocaleInfo();
        localeInfo.setLocaleLanguage("US");
        
        MessageInfo m = new MessageInfo();
        m.setGroupName("Course");
        m.setLocale(localeInfo);
        m.setKey("Grading");
        m.setValue("Grading Scale");
        
        messageService.updateMessage(localeInfo, "Name", m, contextInfo);
        MessageInfo result = messageService.getMessage(localeInfo, "Course", "Grading", contextInfo);
        assertEquals(result.getLocale().getLocaleLanguage(), m.getLocale());
        assertEquals(result.getKey(), m.getKey());
        assertEquals(result.getValue(), m.getValue());
        assertEquals(result.getGroupName(), m.getGroupName());
        result = messageService.getMessage(localeInfo, "Name", "Last", contextInfo);
        assertTrue(result == null);
    }
}
