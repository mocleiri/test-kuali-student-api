/*
 * Copyright 2011 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 1.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.kuali.student.enrollment.class1.check.controller;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;
import org.kuali.rice.krad.datadictionary.DataObjectEntry;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.uif.util.ObjectPropertyUtils;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.web.controller.LookupController;
import org.kuali.rice.krad.web.controller.UifControllerBase;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.kuali.student.enrollment.class1.check.form.CheckInfoForm;
import org.kuali.student.enrollment.uif.view.KSLookupView;
import org.kuali.student.mock.utilities.TestHelper;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.dto.RichTextInfo;
import org.kuali.student.r2.core.constants.ProcessServiceConstants;
import org.kuali.student.r2.core.process.dto.CheckInfo;
import org.kuali.student.r2.core.process.service.ProcessService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.kuali.rice.krad.web.form.LookupForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import java.util.Properties;
import java.util.ArrayList;
import java.util.List;

/**
 * This controller handles all the request from Academic calendar UI.
 *
 * @author Kuali Student Team
 */

@Controller
@RequestMapping(value = "/createCheck")
public class CheckInfoController extends UifControllerBase {

    private transient ProcessService processService;
    private ContextInfo contextInfo;
    private CheckInfo checkInfo;
    private LookupController lookupController;
    @Override
    protected UifFormBase createInitialForm(HttpServletRequest request) {
        return new CheckInfoForm();
    }

    /**
     * This GET method loads an academic calendar based on the parameters passed into the request.
     *
     * These are the supported request parameters
     * 1. id - Academic Calendar Id to load in to UI
     * 2. readOnlyView - If true, sets the view as read only
     * 3. selectTab - can be 'info' or 'term'
     *
     */
    @Override
    @RequestMapping(method = RequestMethod.GET, params = "methodToCall=start")
    public ModelAndView start(@ModelAttribute("KualiForm") UifFormBase form, BindingResult result,
                              HttpServletRequest request, HttpServletResponse response) {
        CheckInfoForm checkForm = (CheckInfoForm) form;
        ((CheckInfoForm) form).setTypeKey("select");
        return super.start(form, result, request, response);
    }
    @RequestMapping(params = "methodToCall=startLookUp")
    public ModelAndView startLookUp(@ModelAttribute("KualiForm") LookupForm lookupForm, BindingResult result,
                               HttpServletRequest request, HttpServletResponse response) {
        lookupForm.setRenderedInLightBox(true);
        ModelAndView modelAndView = lookupController.search(lookupForm, result, request, response);
        if(lookupForm.getPostedView() instanceof KSLookupView){
            KSLookupView ksLookupView = (KSLookupView)lookupForm.getPostedView();
            String defaultAction = ksLookupView.getDefaultSingleLookupResultAction();
            if (StringUtils.isNotBlank(defaultAction) && lookupForm.getLookupResults() != null && lookupForm.getLookupResults().size() == 1){
                Object object = lookupForm.getLookupResults().iterator().next();

                Properties props = new Properties();

                DataObjectEntry ddEntry = KRADServiceLocatorWeb.getDataDictionaryService().getDataDictionary().getDataObjectEntry(lookupForm.getDataObjectClassName());

                List<String> pkKeys = ddEntry.getPrimaryKeys();
                for (String pkKey : pkKeys) {
                    props.put(pkKey, ObjectPropertyUtils.getPropertyValue(object, pkKey));
                }

                if(StringUtils.equals(defaultAction,KRADConstants.PARAM_MAINTENANCE_VIEW_MODE_MAINTENANCE)){
                    props.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, KRADConstants.Maintenance.METHOD_TO_CALL_EDIT);
                }  else{
                    props.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, UifConstants.MethodToCallNames.START);
                }
                props.put(UifConstants.UrlParams.SHOW_HISTORY, BooleanUtils.toStringTrueFalse(false));
                props.put(UifConstants.UrlParams.SHOW_HOME,BooleanUtils.toStringTrueFalse(false));
                props.put(KRADConstants.DATA_OBJECT_CLASS_ATTRIBUTE,lookupForm.getDataObjectClassName());

                return performRedirect(lookupForm,defaultAction,props );
            }
        }

        return modelAndView;
    }
   @RequestMapping(params = "methodToCall=create")
    public ModelAndView create(@ModelAttribute("KualiForm") UifFormBase form, BindingResult result,
                               HttpServletRequest request, HttpServletResponse response) throws Exception {
       CheckInfoForm createForm = (CheckInfoForm) form;
       checkInfo = new CheckInfo();
       checkInfo.setName(createForm.getName());
       checkInfo.setTypeKey(createForm.getTypeKey());
       checkInfo.setStateKey(createForm.getStateKey());
        RichTextInfo richTextInfo = new RichTextInfo();
        richTextInfo.setPlain(createForm.getDescr());
       checkInfo.setDescr(richTextInfo);


        try {
            processService = getProcessService();
           CheckInfo createCheckInfo = processService.createCheck(checkInfo.getTypeKey(), checkInfo, getContextInfo());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Create new failed. ", e);
        }

       return close(createForm, result, request, response);
    }
    @RequestMapping(params = "methodToCall=view")
    public ModelAndView view(@ModelAttribute("KualiForm") CheckInfoForm searchForm, BindingResult result,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        String controllerPath;
        List<String> type = new ArrayList(); //getProcessService().getCheckIdsByType(searchForm.getTypeKey(), getContextInfo());
        Properties urlParameters = new Properties();

        urlParameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, "start");
        urlParameters.put("type", type);
        urlParameters.put(UifParameters.VIEW_ID, "checkInfoView");

        controllerPath = "createCheck";

        return performRedirect(searchForm, controllerPath, urlParameters);
    }
    @RequestMapping(params = "methodToCall=openCreateForm")
    public ModelAndView openCreateForm(@ModelAttribute("KualiForm") CheckInfoForm searchForm, BindingResult result,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        String controllerPath;
        Properties urlParameters = new Properties();

        urlParameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, "start");
        urlParameters.put(UifParameters.VIEW_ID, "checkCreateView");

        controllerPath = "createCheck";

        return performRedirect(searchForm, controllerPath, urlParameters);
       // return getUIFModelAndView(searchForm,  "checkCreateView");
    }
 /* @RequestMapping(params = "methodToCall=modify")
 public ModelAndView modity(@ModelAttribute("KualiForm") UifFormBase form, BindingResult result,
                            HttpServletRequest request, HttpServletResponse response) throws Exception {
      ProcessInfoCreateForm modifyForm = (ProcessInfoCreateForm) form;
      processInfo = new ProcessInfo();
      processInfo.setKey(modifyForm.getKey());
      processInfo.setName(modifyForm.getName());
      processInfo.setTypeKey(modifyForm.getTypeKey());
      processInfo.setStateKey(modifyForm.getStateKey());
      processInfo.setOwnerOrgId(modifyForm.getOwnerOrgId());
     RichTextInfo richTextInfo = new RichTextInfo();
     richTextInfo.setPlain(modifyForm.getDescr());
      processInfo.setDescr(richTextInfo);


     try {
         processService = getProcessService();
         ProcessService modifyProcessInfo = processService.updateProcess(processInfo.getKey(), processInfo, getContextInfo());
     } catch (Exception e) {
         e.printStackTrace();
         throw new RuntimeException("Modify Hold failed. ", e);
     }


     return close(modifyForm, result, request, response);
 }  */


    private ContextInfo getContextInfo() {
        if (null == contextInfo) {
            //TODO - get real ContextInfo
            contextInfo = TestHelper.getContext1();
        }
        return contextInfo;
    }

    protected ProcessService getProcessService(){
        if(processService == null) {
            processService = (ProcessService) GlobalResourceLoader.getService(new QName(ProcessServiceConstants.NAMESPACE, ProcessServiceConstants.SERVICE_NAME_LOCAL_PART));
        }
        return processService;
    }

}
