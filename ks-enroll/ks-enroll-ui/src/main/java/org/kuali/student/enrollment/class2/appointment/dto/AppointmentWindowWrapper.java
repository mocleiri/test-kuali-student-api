package org.kuali.student.enrollment.class2.appointment.dto;

import org.kuali.student.enrollment.class2.acal.dto.TimeSetWrapper;
import org.kuali.student.r2.core.appointment.dto.AppointmentWindowInfo;
import java.util.Date;

public class AppointmentWindowWrapper extends TimeSetWrapper {
    String termType;
    String termYear;
    String id;
    AppointmentWindowInfo appointmentWindowInfo;
    String periodName;
    String periodKey;
    String assignedPopulationName;
    String windowTypeKey;
    String windowTypeName;

    //Assignment Info
    Integer numberOfStudents;
    Integer numberOfSlots;
    Float meanStudentsPerSlot;
    Date lastSlotPopulated;
    Date assignmentsCreated;

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }

    public String getTermYear() {
        return termYear;
    }

    public void setTermYear(String termYear) {
        this.termYear = termYear;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AppointmentWindowWrapper() {
        appointmentWindowInfo = new AppointmentWindowInfo();
    }

    public AppointmentWindowInfo getAppointmentWindowInfo() {
        return appointmentWindowInfo;
    }

    public void setAppointmentWindowInfo(AppointmentWindowInfo appointmentWindowInfo) {
        this.appointmentWindowInfo = appointmentWindowInfo;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public String getPeriodKey() {
        return periodKey;
    }

    public void setPeriodKey(String periodKey) {
        this.periodKey = periodKey;
    }

    public String getAssignedPopulationName() {
        return assignedPopulationName;
    }

    public void setAssignedPopulationName(String assignedPopulationName) {
        this.assignedPopulationName = assignedPopulationName;
    }

    public String getWindowTypeKey() {
        return windowTypeKey;
    }

    public void setWindowTypeKey(String windowTypeKey) {
        this.windowTypeKey = windowTypeKey;
    }

    public String getWindowTypeName() {
        return windowTypeName;
    }

    public void setWindowTypeName(String windowTypeName) {
        this.windowTypeName = windowTypeName;
    }

    public Integer getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(Integer numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public Integer getNumberOfSlots() {
        return numberOfSlots;
    }

    public void setNumberOfSlots(Integer numberOfSlots) {
        this.numberOfSlots = numberOfSlots;
    }

    public Float getMeanStudentsPerSlot() {
        return meanStudentsPerSlot;
    }

    public void setMeanStudentsPerSlot(Float meanStudentsPerSlot) {
        this.meanStudentsPerSlot = meanStudentsPerSlot;
    }

    public Date getLastSlotPopulated() {
        return lastSlotPopulated;
    }

    public void setLastSlotPopulated(Date lastSlotPopulated) {
        this.lastSlotPopulated = lastSlotPopulated;
    }

    public Date getAssignmentsCreated() {
        return assignmentsCreated;
    }

    public void setAssignmentsCreated(Date assignmentsCreated) {
        this.assignmentsCreated = assignmentsCreated;
    }
}
