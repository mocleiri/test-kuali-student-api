package org.kuali.student.enrollment.class2.acal.dto;

import org.kuali.student.enrollment.acal.dto.AcalEventInfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AcalEventWrapper extends TimeSetWrapper{

    private AcalEventInfo acalEventInfo;
    private String eventType;

    public AcalEventWrapper() {
        acalEventInfo = new AcalEventInfo();
        setAllDay(false);
        setDateRange(true);
    }

    public AcalEventWrapper(AcalEventInfo acalEventInfo){
        this.setAcalEventInfo(acalEventInfo);
        this.setStartDate(acalEventInfo.getStartDate());
        this.setAllDay(acalEventInfo.getIsAllDay());
        this.setDateRange(acalEventInfo.getIsDateRange());
        this.setEventType(acalEventInfo.getTypeKey());
        this.setEndDate(acalEventInfo.getEndDate());

        //This is needed to display enddate for readonly view.
        endDateUI = acalEventInfo.getEndDate();

        // If not all day, set start/end time in the wrapper
        if (!isAllDay()){
            DateFormat dfm = new SimpleDateFormat("hh:mm");

            setStartTime(dfm.format(acalEventInfo.getStartDate()));
            setEndTime(dfm.format(acalEventInfo.getEndDate()));

            dfm = new SimpleDateFormat("a");
            setStartTimeAmPm(dfm.format(acalEventInfo.getStartDate()));
            setEndTimeAmPm(dfm.format(acalEventInfo.getEndDate()));

            if (!isDateRange()) {
                setEndDate(null);
            }
        }

    }

    public void copy(AcalEventInfo acalEventInfo){
           AcalEventInfo newEventInfo = new AcalEventInfo();
           newEventInfo.setTypeKey(acalEventInfo.getTypeKey());
           newEventInfo.setIsDateRange(acalEventInfo.getIsDateRange());
           newEventInfo.setIsAllDay(acalEventInfo.getIsAllDay());
           setDateRange(acalEventInfo.getIsDateRange());
           setAllDay(acalEventInfo.getIsAllDay());
           setAcalEventInfo(newEventInfo);
           setEventType(acalEventInfo.getTypeKey());
           setStartDate(null);
           setEndDate(null);

        //Copy only start/end time
        if (!isAllDay()){
            DateFormat dfm = new SimpleDateFormat("hh:mm");

            setStartTime(dfm.format(acalEventInfo.getStartDate()));
            setEndTime(dfm.format(acalEventInfo.getEndDate()));

            dfm = new SimpleDateFormat("a");
            setStartTimeAmPm(dfm.format(acalEventInfo.getStartDate()));
            setEndTimeAmPm(dfm.format(acalEventInfo.getEndDate()));

        }
    }

    public AcalEventInfo getAcalEventInfo(){
        return acalEventInfo;
    }

    public void setAcalEventInfo(AcalEventInfo acalEventInfo) {
        this.acalEventInfo =  acalEventInfo;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }


}
