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
package org.kuali.student.enrollment.class2.registration.controller;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.uif.container.CollectionGroup;
import org.kuali.rice.krad.uif.container.View;
import org.kuali.rice.krad.uif.util.ObjectPropertyUtils;
import org.kuali.rice.krad.web.controller.UifControllerBase;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.kuali.student.enrollment.class2.registration.dto.CourseOfferingWrapper;
import org.kuali.student.enrollment.class2.registration.dto.RegistrationGroupWrapper;
import org.kuali.student.enrollment.class2.registration.form.RegistrationForm;
import org.kuali.student.enrollment.courseoffering.dto.ActivityOfferingInfo;
import org.kuali.student.enrollment.courseoffering.dto.RegistrationGroupInfo;
import org.kuali.student.enrollment.courseoffering.infc.RegistrationGroup;
import org.kuali.student.enrollment.courseoffering.service.CourseOfferingService;
import org.kuali.student.enrollment.courseregistration.dto.RegRequestInfo;
import org.kuali.student.enrollment.courseregistration.dto.RegRequestItemInfo;
import org.kuali.student.enrollment.courseregistration.dto.RegResponseInfo;
import org.kuali.student.enrollment.courseregistration.infc.RegRequest;
import org.kuali.student.enrollment.courseregistration.service.CourseRegistrationService;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.dto.ValidationResultInfo;
import org.kuali.student.r2.common.exceptions.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController extends UifControllerBase {

    private transient CourseOfferingService courseOfferingService;
    private transient CourseRegistrationService courseRegistrationService;

    public UifFormBase createInitialForm(HttpServletRequest httpServletRequest) {
        return new RegistrationForm();
    }

    protected RegRequestInfo createRegRequest(ContextInfo context) throws InvalidParameterException, DataValidationErrorException, MissingParameterException, AlreadyExistsException, PermissionDeniedException, OperationFailedException {
        if (getCourseRegistrationService() != null) {
            return getCourseRegistrationService().createRegRequest(new RegRequestInfo(), context);
        }
        RegRequestInfo regRequest = new RegRequestInfo();
        regRequest.setRegRequestItems(new ArrayList<RegRequestItemInfo>());
        return new RegRequestInfo();
    }

    protected List<ValidationResultInfo> validateRegRequest(RegRequestInfo regRequest, ContextInfo context) throws InvalidParameterException, DataValidationErrorException, MissingParameterException, PermissionDeniedException, OperationFailedException {
        if (getCourseRegistrationService() != null) {
            return getCourseRegistrationService().validateRegRequest(regRequest, context);
        }
        return null;
    }

    protected RegRequestInfo saveRegRequest(RegRequestInfo regRequest, ContextInfo context) throws InvalidParameterException, DataValidationErrorException, MissingParameterException, DoesNotExistException, VersionMismatchException, PermissionDeniedException, OperationFailedException {
        if (getCourseRegistrationService() != null) {
            return getCourseRegistrationService().updateRegRequest(regRequest.getId(), regRequest, context);
        }
        return regRequest;
    }

    protected RegResponseInfo submitRegRequest(RegRequestInfo regRequest, ContextInfo context) throws InvalidParameterException, DataValidationErrorException, MissingParameterException, DoesNotExistException, VersionMismatchException, OperationFailedException, PermissionDeniedException, AlreadyExistsException {
        if (getCourseRegistrationService() != null) {
            return getCourseRegistrationService().submitRegRequest(regRequest.getId(), context);
        }
        return new RegResponseInfo();
    }

    /**
     * Initial method called when requesting a new view instance which forwards
     * the view for rendering
     */
    @RequestMapping(method = RequestMethod.GET, params = "methodToCall=start")
    public ModelAndView start(@ModelAttribute("KualiForm") UifFormBase formBase, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) {
        ContextInfo context = ContextInfo.newInstance();
        RegistrationForm regForm = (RegistrationForm) formBase;
        try {
            RegRequestInfo regRequest = createRegRequest(context);
            regForm.setRegRequest(regRequest);
            return getUIFModelAndView(regForm);
        } catch (AlreadyExistsException e) {
            throw new RuntimeException(e);
        } catch (DataValidationErrorException e) {
            throw new RuntimeException(e);
        } catch (InvalidParameterException e) {
            throw new RuntimeException(e);
        } catch (MissingParameterException e) {
            throw new RuntimeException(e);
        } catch (OperationFailedException e) {
            throw new RuntimeException(e);
        } catch (PermissionDeniedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method used to search for course offerings based on criteria entered
     */
    @RequestMapping(params = "methodToCall=searchCourseOfferings")
    public ModelAndView searchCourseOfferings(@ModelAttribute("KualiForm") RegistrationForm registrationForm, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) {
//        RegistrationForm registrationForm = (RegistrationForm) formBase;
        ContextInfo context = ContextInfo.newInstance();

//        List<CourseOfferingWrapper> courseOfferingWrappers;

        try {
            List<String> courseOfferingIds = getCourseOfferingIds(registrationForm, context);
            registrationForm.setCourseOfferingWrappers(new ArrayList<CourseOfferingWrapper>(courseOfferingIds.size()));

            for(String coId : courseOfferingIds) {
                CourseOfferingWrapper courseOfferingWrapper = new CourseOfferingWrapper();
                courseOfferingWrapper.setCourseOffering(getCourseOfferingService().getCourseOffering(coId, context));
                List<RegistrationGroupInfo> regGroups = getRegistrationGroupInfos(coId, context);

                List<RegistrationGroupWrapper> registrationGroupWrappers = new ArrayList<RegistrationGroupWrapper>(regGroups.size());
                for(RegistrationGroupInfo regGroup : regGroups) {
                    RegistrationGroupWrapper registrationGroupWrapper = new RegistrationGroupWrapper();
                    registrationGroupWrapper.setRegistrationGroup(regGroup);
                    registrationGroupWrapper.setCourseOffering(courseOfferingWrapper.getCourseOffering());
                    registrationGroupWrapper.setActivityOfferings(getActivityOfferingInfos(regGroup, context));
                    registrationGroupWrappers.add(registrationGroupWrapper);
                }
                courseOfferingWrapper.setRegistrationGroupWrappers(registrationGroupWrappers);
                registrationForm.getCourseOfferingWrappers().add(courseOfferingWrapper);
            }

        } catch (DoesNotExistException e) {
            throw new RuntimeException(e);
        } catch (InvalidParameterException e) {
            throw new RuntimeException(e);
        } catch (MissingParameterException e) {
            throw new RuntimeException(e);
        } catch (OperationFailedException e) {
            throw new RuntimeException(e);
        } catch (PermissionDeniedException e) {
            throw new RuntimeException(e);
        }

        return getUIFModelAndView(registrationForm);
    }

    protected List<String> getCourseOfferingIds(RegistrationForm registrationForm, ContextInfo context) throws InvalidParameterException, MissingParameterException, DoesNotExistException, PermissionDeniedException, OperationFailedException {
        return getCourseOfferingService().getCourseOfferingIdsByTermAndSubjectArea(registrationForm.getTermKey(), registrationForm.getSubjectArea(), context);
    }

    protected List<RegistrationGroupInfo> getRegistrationGroupInfos(String coId, ContextInfo context) throws InvalidParameterException, MissingParameterException, DoesNotExistException, PermissionDeniedException, OperationFailedException {
        return getCourseOfferingService().getRegGroupsForCourseOffering(coId, context);
    }

    protected List<ActivityOfferingInfo> getActivityOfferingInfos(RegistrationGroup regGroup, ContextInfo context) throws InvalidParameterException, MissingParameterException, DoesNotExistException, PermissionDeniedException, OperationFailedException {
        // TODO right now getOfferingsByIdList throws a not supported exception
//        return getCourseOfferingService().getActivityOfferingsByIdList(regGroup.getActivityOfferingIds(), context);
        List<ActivityOfferingInfo> activityOfferingInfos = new ArrayList<ActivityOfferingInfo>(regGroup.getActivityOfferingIds().size());
        for(String activityId : regGroup.getActivityOfferingIds()) {
            activityOfferingInfos.add(getCourseOfferingService().getActivityOffering(activityId, context));
        }
        return activityOfferingInfos;
    }

    /**
     * After the document is loaded calls method to setup the maintenance object
     */
    @RequestMapping(params = "methodToCall=submitRegistration")
    public ModelAndView submitRegistration(@ModelAttribute("KualiForm") RegistrationForm registrationForm, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) {
        ContextInfo context = ContextInfo.newInstance();
        try {
            List<ValidationResultInfo> validationResultInfos = validateRegRequest(registrationForm.getRegRequest(), context);
            if (CollectionUtils.isEmpty(validationResultInfos)) {
                RegRequestInfo regRequest = saveRegRequest(registrationForm.getRegRequest(), context);
                // TODO - what to do with the RegResponseInfo object?
                RegResponseInfo regResponse = submitRegRequest(regRequest, context);
            } else {
                StringBuffer buffer = new StringBuffer("Found multiple ValidationResultInfo objects after Registration Request validation:\n");
                for (ValidationResultInfo resultInfo : validationResultInfos) {
                    buffer.append(resultInfo.getMessage()).append("\n");
                }
                throw new RuntimeException(buffer.toString());
            }
        } catch (DataValidationErrorException e) {
            throw new RuntimeException(e);
        } catch (DoesNotExistException e) {
            throw new RuntimeException(e);
        } catch (InvalidParameterException e) {
            throw new RuntimeException(e);
        } catch (MissingParameterException e) {
            throw new RuntimeException(e);
        } catch (OperationFailedException e) {
            throw new RuntimeException(e);
        } catch (PermissionDeniedException e) {
            throw new RuntimeException(e);
        } catch (VersionMismatchException e) {
            throw new RuntimeException(e);
        } catch (AlreadyExistsException e) {
            throw new RuntimeException(e);
        }
        return getUIFModelAndView(registrationForm);
    }

    /**
     * After the document is loaded calls method to setup the maintenance object
     */
    @RequestMapping(params = "methodToCall=registerClass")
    public ModelAndView registerClass(@ModelAttribute("KualiForm") RegistrationForm registrationForm, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) {
        ContextInfo context = ContextInfo.newInstance();

        // Code copied roughly from UifControllerBase.deleteLine() method
        String selectedCollectionPath = registrationForm.getActionParamaterValue(UifParameters.SELLECTED_COLLECTION_PATH);
        if (StringUtils.isBlank(selectedCollectionPath)) {
            throw new RuntimeException("Selected collection was not set for registration line action, cannot register line");
        }

        int selectedLineIndex = -1;
        String selectedLine = registrationForm.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);
        if (StringUtils.isNotBlank(selectedLine)) {
            selectedLineIndex = Integer.parseInt(selectedLine);
        }

        if (selectedLineIndex == -1) {
            throw new RuntimeException("Selected line index was not set for registration line action, cannot register line");
        }

        View previousView = registrationForm.getPreviousView();
        CollectionGroup collectionGroup = previousView.getViewIndex().getCollectionGroupByPath(selectedCollectionPath);
        if (collectionGroup == null) {
            throw new RuntimeException("Unable to get registration group collection component for path: " + selectedCollectionPath);
        }

        // get the collection instance for adding the new line
        Collection<Object> collection = ObjectPropertyUtils.getPropertyValue(registrationForm, selectedCollectionPath);
        if (collection == null) {
            throw new RuntimeException("Unable to get registration group collection property from RegistrationForm for path: " + selectedCollectionPath);
        }

        if (collection instanceof List) {
            RegistrationGroupWrapper regGroupWrapper = (RegistrationGroupWrapper)((List<Object>) collection).get(selectedLineIndex);

            try {
                // TODO - check to make sure this is the proper process to register a new course
                RegRequestItemInfo regRequestItem = new RegRequestItemInfo();
                regRequestItem.setTypeKey("");
                regRequestItem.setNewRegGroupId(regGroupWrapper.getRegistrationGroup().getId());
                registrationForm.getRegRequest().getRegRequestItems().add(regRequestItem);
                List<ValidationResultInfo> validationResultInfos = validateRegRequest(registrationForm.getRegRequest(), context);
                if (CollectionUtils.isEmpty(validationResultInfos)) {
                    registrationForm.setRegRequest(saveRegRequest(registrationForm.getRegRequest(), context));
                    // TODO - should we remove the registration group from the collection?
//                    ((List<Object>) collection).remove(selectedLineIndex);
                } else {
                    StringBuffer buffer = new StringBuffer("Found multiple ValidationResultInfo objects after Registration Request validation:\n");
                    for (ValidationResultInfo resultInfo : validationResultInfos) {
                        buffer.append(resultInfo.getMessage()).append("\n");
                    }
                    throw new RuntimeException(buffer.toString());
                }
            } catch (InvalidParameterException e) {
                throw new RuntimeException(e);
            } catch (DoesNotExistException e) {
                throw new RuntimeException(e);
            } catch (DataValidationErrorException e) {
                throw new RuntimeException(e);
            } catch (PermissionDeniedException e) {
                throw new RuntimeException(e);
            } catch (VersionMismatchException e) {
                throw new RuntimeException(e);
            } catch (OperationFailedException e) {
                throw new RuntimeException(e);
            } catch (MissingParameterException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Only List collection implementations are supported for the register by index method");
        }

        return getUIFModelAndView(registrationForm);
    }

    protected CourseOfferingService getCourseOfferingService() {
        if (courseOfferingService == null) {
            courseOfferingService = (CourseOfferingService) GlobalResourceLoader.getService(new QName("http://student.kuali.org/wsdl/courseOffering", "CourseOfferingService"));
        }

        return courseOfferingService;
    }

    protected CourseRegistrationService getCourseRegistrationService() {
        if (courseRegistrationService == null) {
            courseRegistrationService = (CourseRegistrationService) GlobalResourceLoader.getService(new QName("http://student.kuali.org/wsdl/courseRegistrationService", "CourseRegistrationService"));
        }

        return courseRegistrationService;
    }
}
