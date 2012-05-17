package org.kuali.student.enrollment.class2.courseoffering.service.impl;

import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;
import org.kuali.rice.krad.maintenance.MaintainableImpl;
import org.kuali.rice.krad.maintenance.MaintenanceDocument;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.student.enrollment.class2.courseoffering.dto.EnrollmentFeeFormObject;
import org.kuali.student.enrollment.class2.courseoffering.service.EnrollmentFeeMaintainable;
import org.kuali.student.r2.common.util.constants.*;
import org.kuali.student.r2.core.fee.dto.EnrollmentFeeInfo;

import org.kuali.student.enrollment.courseoffering.dto.ActivityOfferingInfo;
import org.kuali.student.enrollment.courseoffering.service.CourseOfferingService;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.dto.LocaleInfo;
import org.kuali.student.r2.core.state.service.StateService;
import org.kuali.student.r2.core.type.service.TypeService;


import org.kuali.student.r2.core.fee.service.FeeService;

import javax.xml.namespace.QName;
import java.util.Locale;
import java.util.Map;

public class EnrollmentFeeMaintainableImpl extends MaintainableImpl implements EnrollmentFeeMaintainable {

    private transient CourseOfferingService courseOfferingService;
    private ContextInfo contextInfo;
    private transient TypeService typeService;
    private transient StateService stateService;


    private FeeService feeService;



    @Override
    public void saveDataObject() {
        if(getMaintenanceAction().equals(KRADConstants.MAINTENANCE_NEW_ACTION) ||
                getMaintenanceAction().equals(KRADConstants.MAINTENANCE_COPY_ACTION)) {
            try {
                EnrollmentFeeFormObject enrollmentFeeFormObject = (EnrollmentFeeFormObject) getDataObject();
                EnrollmentFeeInfo  feeInfo  = getFeeService().createFee(enrollmentFeeFormObject.getEfInfo().getTypeKey(), enrollmentFeeFormObject.getEfInfo(),getContextInfo() );

                setDataObject(new EnrollmentFeeFormObject(feeInfo));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else {   //should be edit action
            EnrollmentFeeFormObject enrollmentFeeFormObject = (EnrollmentFeeFormObject) getDataObject();
            try {
                EnrollmentFeeInfo  feeInfo  = getFeeService().updateFee(enrollmentFeeFormObject.getEfInfo().getId(), enrollmentFeeFormObject.getEfInfo(),getContextInfo());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Object retrieveObjectForEditOrCopy(MaintenanceDocument document, Map<String, String> dataObjectKeys) {
        try {
            EnrollmentFeeInfo info = getFeeService().getFee(dataObjectKeys.get("efInfo.id"),getContextInfo());
            EnrollmentFeeFormObject formObject = new EnrollmentFeeFormObject(info);
            document.getNewMaintainableObject().setDataObject(formObject);
            document.getOldMaintainableObject().setDataObject(formObject);
//            StateInfo state = getStateService().getState(formObject.getDto().getStateKey(), getContextInfo());
//            formObject.setStateName(state.getName());
            return formObject;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void processAfterNew(MaintenanceDocument document, Map<String, String[]> requestParameters) {
        EnrollmentFeeFormObject formObject = (EnrollmentFeeFormObject)document.getNewMaintainableObject().getDataObject();
        document.getDocumentHeader().setDocumentDescription("Activity Offering");
        try {
//            StateInfo state = getStateService().getState(formObject.getDto().getStateKey(), getContextInfo());
//            formObject.setStateName(state.getName());
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public ContextInfo getContextInfo() {
        if (null == contextInfo) {
            contextInfo = new ContextInfo();
            contextInfo.setAuthenticatedPrincipalId(GlobalVariables.getUserSession().getPrincipalId());
            contextInfo.setPrincipalId(GlobalVariables.getUserSession().getPrincipalId());
            LocaleInfo localeInfo = new LocaleInfo();
            localeInfo.setLocaleLanguage(Locale.getDefault().getLanguage());
            localeInfo.setLocaleRegion(Locale.getDefault().getCountry());
            contextInfo.setLocale(localeInfo);
        }
        return contextInfo;
    }

    public TypeService getTypeService() {
        if(typeService == null) {
            typeService = (TypeService) GlobalResourceLoader.getService(new QName(TypeServiceConstants.NAMESPACE, TypeServiceConstants.SERVICE_NAME_LOCAL_PART));
        }
        return this.typeService;
    }

    protected FeeService getFeeService() {
        if (feeService == null) {
            feeService = (FeeService) GlobalResourceLoader.getService(new QName(CourseOfferingServiceConstants.NAMESPACE, FeeServiceConstants.SERVICE_NAME_LOCAL_PART));
        }
        return feeService;
    }
}
