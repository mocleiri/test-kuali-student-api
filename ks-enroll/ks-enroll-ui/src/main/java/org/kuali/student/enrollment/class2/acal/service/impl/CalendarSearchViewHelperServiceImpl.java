package org.kuali.student.enrollment.class2.acal.service.impl;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.core.api.criteria.Predicate;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.uif.service.impl.ViewHelperServiceImpl;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.student.enrollment.acal.constants.AcademicCalendarServiceConstants;
import org.kuali.student.enrollment.acal.dto.AcademicCalendarInfo;
import org.kuali.student.enrollment.acal.dto.HolidayCalendarInfo;
import org.kuali.student.enrollment.acal.dto.TermInfo;
import org.kuali.student.enrollment.acal.service.AcademicCalendarService;
import org.kuali.student.enrollment.class2.acal.service.CalendarSearchViewHelperService;
import org.kuali.student.enrollment.class2.acal.util.CalendarConstants;
import org.kuali.student.r2.common.dto.ContextInfo;

import javax.xml.namespace.QName;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.kuali.rice.core.api.criteria.PredicateFactory.*;

public class CalendarSearchViewHelperServiceImpl extends ViewHelperServiceImpl implements CalendarSearchViewHelperService {

    private transient AcademicCalendarService academicCalendarService;

    public final static String NAME = "name";
    public final static String START_DATE = "startDate";
    public final static String END_DATE = "endDate";
    public final static String CALENDAR_TYPE = "atpType";


    public List<TermInfo> searchForTerms(String name, String year,ContextInfo context)throws Exception {

    	List<TermInfo> termInfoList = new ArrayList<TermInfo>();

        QueryByCriteria.Builder query = buildQueryByCriteria(name,year,"Term");

        List<TermInfo> terms = getAcademicCalendarService().searchForTerms(query.build(),context);
        for (TermInfo term : terms) {
            termInfoList.add(term);
        }

        return termInfoList;

    }

    public List<AcademicCalendarInfo> searchForAcademicCalendars(String name, String year,ContextInfo context)throws Exception {

        List<AcademicCalendarInfo> acalInfoList = new ArrayList<AcademicCalendarInfo>();

        QueryByCriteria.Builder query = buildQueryByCriteria(name,year,AcademicCalendarServiceConstants.ACADEMIC_CALENDAR_TYPE_KEY);

        List<AcademicCalendarInfo> acals = getAcademicCalendarService().searchForAcademicCalendars(query.build(), context);
        for (AcademicCalendarInfo acal : acals) {
            acalInfoList.add(acal);
        }

        return acalInfoList;


    }

    public List<HolidayCalendarInfo> searchForHolidayCalendars(String name, String year,ContextInfo context)throws Exception {

        List<HolidayCalendarInfo> hCals = new ArrayList<HolidayCalendarInfo>();

        QueryByCriteria.Builder query = buildQueryByCriteria(name,year,AcademicCalendarServiceConstants.HOLIDAY_CALENDAR_TYPE_KEY);

        List<HolidayCalendarInfo> hcs = getAcademicCalendarService().searchForHolidayCalendars(query.build(), context);
        for (HolidayCalendarInfo hc : hcs) {
            hCals.add(hc);
        }

        return hCals;


    }

    private QueryByCriteria.Builder buildQueryByCriteria(String name, String year,String typeKey){

        QueryByCriteria.Builder qBuilder = QueryByCriteria.Builder.create();
        List<Predicate> pList = new ArrayList<Predicate>();
        Predicate p;

        qBuilder.setPredicates();
        if (StringUtils.isNotBlank(name)){
            p = like(NAME, "%" + name + "%");
    		pList.add(p);
        }

        if (StringUtils.isNotBlank(year)){
            try {
                //FIXME: Find some better way to check the year
                Predicate startDatePredicate = and(greaterThanOrEqual(START_DATE, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/" + year)),
                                                   lessThanOrEqual(START_DATE, new SimpleDateFormat("MM/dd/yyyy").parse("12/31/" + year)));


                Predicate endDatePredicate = and(greaterThanOrEqual(END_DATE, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/" + year)),
                                                lessThanOrEqual(END_DATE, new SimpleDateFormat("MM/dd/yyyy").parse("12/31/" + year)));

                pList.add(or(startDatePredicate, endDatePredicate));

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

        if (StringUtils.equalsIgnoreCase(typeKey, "Term")){
            p = notIn(CALENDAR_TYPE,AcademicCalendarServiceConstants.ACADEMIC_CALENDAR_TYPE_KEY,AcademicCalendarServiceConstants.HOLIDAY_CALENDAR_TYPE_KEY);
        }else{
            p = equal(CALENDAR_TYPE,typeKey);
        }

        pList.add(p);

        if (!pList.isEmpty()){
            Predicate[] preds = new Predicate[pList.size()];
            pList.toArray(preds);
            qBuilder.setPredicates(and(preds));
        }
        return qBuilder;
    }

    public Properties buildTermURLParameters(TermInfo term, String methodToCall, boolean readOnlyView, ContextInfo context){

        String acalId = null;
        try {
            List<AcademicCalendarInfo> atps = getAcademicCalendarService().getAcademicCalendarsForTerm(term.getId(), context);
            if (!atps.isEmpty()){
                acalId = atps.get(0).getId();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Properties props = new Properties();
        props.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, methodToCall);
        props.put(CalendarConstants.CALENDAR_ID,acalId);
        props.put(UifParameters.VIEW_ID, CalendarConstants.ACAL_VIEW);
        props.put(CalendarConstants.PAGE_ID,CalendarConstants.ACADEMIC_CALENDAR_EDIT_PAGE);
        props.put(CalendarConstants.SELECT_TAB,CalendarConstants.ACAL_TERM_TAB);

        if (readOnlyView){
            props.put(CalendarConstants.READ_ONLY_VIEW,""+ true);
        }

        return props;

    }

    public Properties buildACalURLParameters(AcademicCalendarInfo acal, String methodToCall, boolean readOnlyView, ContextInfo context){

        Properties props = new Properties();
        props.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, methodToCall);
        props.put(CalendarConstants.CALENDAR_ID,acal.getId());
        props.put(UifParameters.VIEW_ID, CalendarConstants.ACAL_VIEW);

        if (StringUtils.equals(methodToCall,CalendarConstants.AC_COPY_METHOD)){
           props.put(CalendarConstants.PAGE_ID,CalendarConstants.ACADEMIC_CALENDAR_COPY_PAGE);
        } else {
           props.put(CalendarConstants.PAGE_ID,CalendarConstants.ACADEMIC_CALENDAR_EDIT_PAGE);
        }

        if (readOnlyView){
            props.put(CalendarConstants.READ_ONLY_VIEW,""+ true);
        }

        return props;

    }

    public Properties buildHCalURLParameters(HolidayCalendarInfo hcInfo, String methodToCall, boolean readOnlyView, ContextInfo context){

        Properties props = new Properties();
        props.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, methodToCall);
        props.put(CalendarConstants.CALENDAR_ID, hcInfo.getId());
        props.put(UifParameters.VIEW_ID, CalendarConstants.HOLIDAYCALENDAR_FLOWVIEW);

        if (StringUtils.equals(methodToCall,CalendarConstants.HC_COPY_METHOD)){
           props.put(CalendarConstants.PAGE_ID,CalendarConstants.HOLIDAYCALENDAR_COPYPAGE);
        }else if (StringUtils.equals(methodToCall,CalendarConstants.HC_VIEW_METHOD) && readOnlyView){
            props.put(CalendarConstants.PAGE_ID,CalendarConstants.HOLIDAYCALENDAR_VIEWPAGE);
        } else {
           props.put(CalendarConstants.PAGE_ID,CalendarConstants.HOLIDAYCALENDAR_EDITPAGE);
        }

        return props;

    }

    protected AcademicCalendarService getAcademicCalendarService() {
         if(academicCalendarService == null) {
             academicCalendarService = (AcademicCalendarService) GlobalResourceLoader.getService(new QName(AcademicCalendarServiceConstants.NAMESPACE, AcademicCalendarServiceConstants.SERVICE_NAME_LOCAL_PART));
        }
        return this.academicCalendarService;
    }
}
