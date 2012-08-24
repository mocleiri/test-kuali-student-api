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
import org.kuali.rice.core.api.criteria.Predicate;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
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
import org.kuali.student.enrollment.class1.process.form.ProcessInfoForm;
import org.kuali.student.enrollment.uif.view.KSLookupView;
import org.kuali.student.mock.utilities.TestHelper;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.dto.RichTextInfo;
import org.kuali.student.r2.core.constants.ProcessServiceConstants;
import org.kuali.student.r2.core.process.dto.CheckInfo;
import org.kuali.student.r2.core.process.dto.ProcessInfo;
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
import java.util.Collection;
import java.util.Properties;
import java.util.ArrayList;
import java.util.List;

import static org.kuali.rice.core.api.criteria.PredicateFactory.and;
import static org.kuali.rice.core.api.criteria.PredicateFactory.equal;
import static org.kuali.rice.core.api.criteria.PredicateFactory.like;

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

    private boolean isEdit;

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
        CheckInfoForm checkForm = (CheckInfoForm) form;;
        return super.start(form, result, request, response);
    }

    @RequestMapping(method = RequestMethod.POST, params = "methodToCall=search")
    public ModelAndView search(@ModelAttribute("KualiForm") CheckInfoForm form, BindingResult result,
                               HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<CheckInfo> results = new ArrayList<CheckInfo>();
        String name = form.getName();
        String descr = form.getDescr();
        String type = form.getTypeKey();

        try {
            QueryByCriteria.Builder query = buildQueryByCriteria(name, descr, type);

            processService = getProcessService();


            List<CheckInfo> checkInfoList = processService.searchForChecks(query.build(), getContextInfo());
            if (!checkInfoList.isEmpty()){
                results.addAll(checkInfoList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error Performing Search",e); //To change body of catch statement use File | Settings | File Templates.
        }

        resetForm(form);

        form.setCheckInfoList(results);

        return getUIFModelAndView(form);
    }

   @RequestMapping(params = "methodToCall=create")
    public ModelAndView create(@ModelAttribute("KualiForm") UifFormBase form, BindingResult result,
                               HttpServletRequest request, HttpServletResponse response) throws Exception {
       CheckInfoForm createForm = (CheckInfoForm) form;
       CheckInfo checkInfo = new CheckInfo();
       checkInfo.setName(createForm.getName());
       checkInfo.setTypeKey(createForm.getTypeKey());
       checkInfo.setStateKey(createForm.getStateKey());
        RichTextInfo richTextInfo = new RichTextInfo();
        richTextInfo.setPlain(createForm.getDescr());
       checkInfo.setDescr(richTextInfo);


        try {
           processService = getProcessService();
           processService.createCheck(checkInfo.getTypeKey(), checkInfo, getContextInfo());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Create new failed. ", e);
        }

       return close(createForm, result, request, response);
    }

    @RequestMapping(method = RequestMethod.POST, params = "methodToCall=edit")
    public ModelAndView edit(@ModelAttribute("KualiForm") CheckInfoForm form, BindingResult result,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        CheckInfo checkInfo = getSelectedCheckInfo(form, "edit");
        isEdit = true;

        if ((checkInfo.getId() != null) && !checkInfo.getId().trim().isEmpty()) {
            try {
                CheckInfo check = getProcessService().getCheck(checkInfo.getId(), getContextInfo());
                form.setName(check.getName());
                form.setTypeKey(check.getTypeKey());
                form.setDescr(check.getDescr().getPlain());
                form.setStateKey(check.getStateKey());
            } catch (Exception ex) {
                throw new RuntimeException("unable to get hold issue");
            }
        }

        return getUIFModelAndView(form, "checkInfoSearch-EditPage");
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
    }

    @RequestMapping(method = RequestMethod.POST, params = "methodToCall=clear")
    public ModelAndView clear(@ModelAttribute("KualiForm") CheckInfoForm form, BindingResult result,
                              HttpServletRequest request, HttpServletResponse response) throws Exception {
        clearValues(form);
        return getUIFModelAndView(form);
    }

    @RequestMapping(method = RequestMethod.POST, params = "methodToCall=back")
    public ModelAndView back(@ModelAttribute("KualiForm") CheckInfoForm form, BindingResult result,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        clearValues(form);
        return getUIFModelAndView(form, "checkInfoSearch-SearchPage");
    }

    private void clearValues(CheckInfoForm form) {
        form.setName("");
        form.setTypeKey("");
        form.setDescr("");
    }

    private void resetForm(CheckInfoForm form) {
        form.setCheckInfoList(new ArrayList<CheckInfo>());
    }

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

    private static QueryByCriteria.Builder buildQueryByCriteria(String name, String descr, String type){

        QueryByCriteria.Builder qBuilder = QueryByCriteria.Builder.create();
        List<Predicate> pList = new ArrayList<Predicate>();
        Predicate p;

        qBuilder.setPredicates();
        if (StringUtils.isNotBlank(name)){
            p = like("name", "%" + name + "%");
            pList.add(p);
        }

        if (StringUtils.isNotBlank(type)){
            p = like("checkType", "%" + type + "%");
            pList.add(p);
        }

        if (StringUtils.isNotBlank(descr)){
            p = like("descrPlain", "%" + descr + "%");
            pList.add(p);
        }

        if (!pList.isEmpty()){
            Predicate[] preds = new Predicate[pList.size()];
            pList.toArray(preds);
            qBuilder.setPredicates(and(preds));
        }
        return qBuilder;
    }

    private CheckInfo getSelectedCheckInfo(CheckInfoForm form, String actionLink){
        String selectedCollectionPath = form.getActionParamaterValue(UifParameters.SELLECTED_COLLECTION_PATH);
        if (StringUtils.isBlank(selectedCollectionPath)) {
            throw new RuntimeException("Selected collection was not set for " + actionLink);
        }

        int selectedLineIndex = -1;
        String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);
        if (StringUtils.isNotBlank(selectedLine)) {
            selectedLineIndex = Integer.parseInt(selectedLine);
        }

        if (selectedLineIndex == -1) {
            throw new RuntimeException("Selected line index was not set");
        }

        Collection<CheckInfo> collection = ObjectPropertyUtils.getPropertyValue(form, selectedCollectionPath);
        CheckInfo checkInfo = ((List<CheckInfo>) collection).get(selectedLineIndex);

        return checkInfo;
    }

}
