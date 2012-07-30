package org.kuali.student.enrollment.class2.courseoffering.dto;

import org.kuali.student.enrollment.acal.dto.TermInfo;
import org.kuali.student.enrollment.courseoffering.dto.CourseOfferingInfo;
import org.kuali.student.enrollment.courseoffering.dto.FormatOfferingInfo;
import org.kuali.student.lum.course.dto.CourseInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CourseOfferingCreateWrapper implements Serializable{

    private String targetTermCode;
    private String catalogCourseCode;
    private boolean createFromCatalog;

    private String creditCount;

    private boolean showTermOfferingLink;
    private boolean showCatalogLink;
    private boolean showAllSections;
    private boolean enableCreateButton;

    private String courseOfferingSuffix;

    private int noOfTermOfferings;

    private CourseInfo course;
    private CourseOfferingInfo coInfo;
    private TermInfo term;

    private List<FormatOfferingInfo> formatOfferingList;
    private List<ExistingCourseOffering> existingCourseOfferings;
    private List<ExistingCourseOffering> existingTermOfferings;

    private String createErrorMessage;

    private String invalidCatalogCourseCodeError;
    private String invalidTargetTermError;

    private boolean excludeCancelledActivityOfferings;
    private boolean excludeSchedulingInformation;
    private boolean excludeInstructorInformation;

    public CourseOfferingCreateWrapper(){
        showTermOfferingLink = true;
        formatOfferingList = new ArrayList<FormatOfferingInfo>();
        existingCourseOfferings = new ArrayList<ExistingCourseOffering>();
        existingTermOfferings = new ArrayList<ExistingCourseOffering>();
    }

    public String getTargetTermCode() {
        return targetTermCode;
    }

    public void setTargetTermCode(String targetTermCode) {
        this.targetTermCode = targetTermCode;
    }

    public String getCatalogCourseCode() {
        return catalogCourseCode;
    }

    public void setCatalogCourseCode(String catalogCourseCode) {
        this.catalogCourseCode = catalogCourseCode;
    }

    public boolean isCreateFromCatalog() {
        return createFromCatalog;
    }

    public void setCreateFromCatalog(boolean createFromCatalog) {
        this.createFromCatalog = createFromCatalog;
    }

    public CourseInfo getCourse() {
        return course;
    }

    public void setCourse(CourseInfo course) {
        this.course = course;
    }

    public CourseOfferingInfo getCoInfo() {
        return coInfo;
    }

    public void setCoInfo(CourseOfferingInfo coInfo) {
        this.coInfo = coInfo;
    }

    public String getCreditCount() {
        return creditCount;
    }

    public void setCreditCount(String creditCount) {
        this.creditCount = creditCount;
    }

    public boolean isShowTermOfferingLink() {
        return showTermOfferingLink;
    }

    public void setShowTermOfferingLink(boolean showTermOfferingLink) {
        this.showTermOfferingLink = showTermOfferingLink;
    }

    public boolean isShowCatalogLink() {
        return showCatalogLink;
    }

    public void setShowCatalogLink(boolean showCatalogLink) {
        this.showCatalogLink = showCatalogLink;
    }

    public String getCourseOfferingSuffix() {
        return courseOfferingSuffix;
    }

    public void setCourseOfferingSuffix(String courseOfferingSuffix) {
        this.courseOfferingSuffix = courseOfferingSuffix;
    }

    public boolean isShowAllSections() {
        return showAllSections;
    }

    public void setShowAllSections(boolean showAllSections) {
        this.showAllSections = showAllSections;
    }

    public List<ExistingCourseOffering> getExistingCourseOfferings() {
        return existingCourseOfferings;
    }

    public void setExistingCourseOfferings(List<ExistingCourseOffering> existingCourseOfferings) {
        this.existingCourseOfferings = existingCourseOfferings;
    }

    public TermInfo getTerm() {
        return term;
    }

    public void setTerm(TermInfo term) {
        this.term = term;
    }

    public List<ExistingCourseOffering> getExistingTermOfferings() {
        return existingTermOfferings;
    }

    public void setExistingTermOfferings(List<ExistingCourseOffering> existingTermOfferings) {
        this.existingTermOfferings = existingTermOfferings;
    }

    public int getNoOfTermOfferings() {
        return noOfTermOfferings;
    }

    public void setNoOfTermOfferings(int noOfTermOfferings) {
        this.noOfTermOfferings = noOfTermOfferings;
    }

    public boolean isEnableCreateButton() {
        return enableCreateButton;
    }

    public void setEnableCreateButton(boolean enableCreateButton) {
        this.enableCreateButton = enableCreateButton;
    }

    public List<FormatOfferingInfo> getFormatOfferingList() {
        return formatOfferingList;
    }

    public void setFormatOfferingList(List<FormatOfferingInfo> formatOfferingList) {
        this.formatOfferingList = formatOfferingList;
    }

    public String getCreateErrorMessage() {
        return createErrorMessage;
    }

    public void setCreateErrorMessage(String createErrorMessage) {
        this.createErrorMessage = createErrorMessage;
    }
    public String getInvalidCatalogCourseCodeError() {
        return invalidCatalogCourseCodeError;
    }

    public void setInvalidCatalogCourseCodeError(String invalidCatalogCourseCodeError) {
        this.invalidCatalogCourseCodeError = invalidCatalogCourseCodeError;
    }

    public String getInvalidTargetTermError() {
        return invalidTargetTermError;
    }

    public void setInvalidTargetTermError(String invalidTargetTermError) {
        this.invalidTargetTermError = invalidTargetTermError;
    }

    public boolean isExcludeCancelledActivityOfferings() {
        return excludeCancelledActivityOfferings;
    }

    public void setExcludeCancelledActivityOfferings(boolean excludeCancelledActivityOfferings) {
        this.excludeCancelledActivityOfferings = excludeCancelledActivityOfferings;
    }

    public boolean isExcludeSchedulingInformation() {
        return excludeSchedulingInformation;
    }

    public void setExcludeSchedulingInformation(boolean excludeSchedulingInformation) {
        this.excludeSchedulingInformation = excludeSchedulingInformation;
    }

    public boolean isExcludeInstructorInformation() {
        return excludeInstructorInformation;
    }

    public void setExcludeInstructorInformation(boolean excludeInstructorInformation) {
        this.excludeInstructorInformation = excludeInstructorInformation;
    }

    public void clear(){
        setCourse(null);
        setShowAllSections(false);
        setCreditCount("");
        getExistingTermOfferings().clear();
        getExistingCourseOfferings().clear();
        setNoOfTermOfferings(0);
        setEnableCreateButton(false);
        setExcludeCancelledActivityOfferings(false);
        setExcludeSchedulingInformation(false);
        setExcludeInstructorInformation(false);
    }
}
