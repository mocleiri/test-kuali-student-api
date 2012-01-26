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

package org.kuali.student.r2.lum.lu.dto;

import org.kuali.student.r2.common.dto.AmountInfo;
import org.kuali.student.r2.common.dto.IdNamelessEntityInfo;
import org.kuali.student.r2.common.dto.TimeAmountInfo;
import org.kuali.student.r2.core.versionmanagement.dto.VersionInfo;
import org.kuali.student.r2.lum.lu.infc.Accreditation;
import org.kuali.student.r2.lum.lu.infc.AdminOrg;
import org.kuali.student.r2.lum.lu.infc.Clu;
import org.kuali.student.r2.lum.lu.infc.CluIdentifier;
import org.kuali.student.r2.lum.lu.infc.CluInstructor;
import org.kuali.student.r2.lum.lu.infc.LuCode;

import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CluInfo", propOrder = {"id", "typeKey", "stateKey", "officialIdentifier", "alternateIdentifiers", "studySubjectArea", "campusLocations", "accreditations", "adminOrgs", "primaryInstructor", "instructors",
        "expectedFirstAtp", "lastAtp", "lastAdmitAtp", "intensity", "stdDuration", "canCreateLui", "referenceURL", "luCodes", "nextReviewPeriod", "isEnrollable", "offeredAtpTypes", "hasEarlyDropDeadline", "defaultEnrollmentEstimate",
        "defaultMaximumEnrollment", "isHazardousForDisabledStudents", "feeInfo", "accountingInfo", "versionInfo", "effectiveDate", "expirationDate", "meta", "attributes", "_futureElements"})
public class CluInfo extends IdNamelessEntityInfo implements Serializable, Clu {

    private static final long serialVersionUID = 1L;

    @XmlElement
    private CluIdentifierInfo officialIdentifier;

    @XmlElement
    private List<CluIdentifierInfo> alternateIdentifiers;

    @XmlElement
    private String studySubjectArea;

    @XmlElement
    private List<String> campusLocations;

    @XmlElement
    private List<AccreditationInfo> accreditations;

    @XmlElement
    private List<AdminOrgInfo> adminOrgs;

    @XmlElement
    private CluInstructorInfo primaryInstructor;

    @XmlElement
    private List<CluInstructorInfo> instructors;

    @XmlElement
    private String expectedFirstAtp;

    @XmlElement
    private String lastAtp;

    @XmlElement
    private String lastAdmitAtp;

    @XmlElement
    private AmountInfo intensity;

    @XmlElement
    private TimeAmountInfo stdDuration;

    @XmlElement
    private boolean canCreateLui;

    @XmlElement
    private String referenceURL;

    @XmlElement
    private List<LuCodeInfo> luCodes;

    @XmlElement
    private String nextReviewPeriod;

    @XmlElement
    private boolean isEnrollable;

    @XmlElement
    private List<String> offeredAtpTypes;

    @XmlElement
    private boolean hasEarlyDropDeadline;

    @XmlElement
    private int defaultEnrollmentEstimate;

    @XmlElement
    private int defaultMaximumEnrollment;

    @XmlElement
    private boolean isHazardousForDisabledStudents;

    @XmlElement
    private CluFeeInfo feeInfo;

    @XmlElement
    private CluAccountingInfo accountingInfo;

    @XmlElement
    private VersionInfo versionInfo;

    @XmlElement
    private Date effectiveDate;

    @XmlElement
    private Date expirationDate;

    @XmlAnyElement
    private List<Element> _futureElements;

    public CluInfo() {

    }

    public CluInfo(Clu clu) {
        super(clu);
        if (null != clu) {
            this.officialIdentifier = (null != clu.getOfficialIdentifier()) ? new CluIdentifierInfo(clu.getOfficialIdentifier()) : null;
            this.alternateIdentifiers = new ArrayList<CluIdentifierInfo>();
            for (CluIdentifier cluIdentifier : clu.getAlternateIdentifiers()) {
                this.alternateIdentifiers.add(new CluIdentifierInfo(cluIdentifier));
            }
            this.studySubjectArea = clu.getStudySubjectArea();
            this.campusLocations = (null != clu.getCampusLocations()) ? new ArrayList<String>(clu.getCampusLocations()) : null;
            this.accreditations = new ArrayList<AccreditationInfo>();
            for (Accreditation accreditation : clu.getAccreditations()) {
                this.accreditations.add(new AccreditationInfo(accreditation));
            }
            this.adminOrgs = new ArrayList<AdminOrgInfo>();
            for (AdminOrg adminOrg : clu.getAdminOrgs()) {
                this.adminOrgs.add(new AdminOrgInfo(adminOrg));
            }
            this.primaryInstructor = (null != clu.getPrimaryInstructor()) ? new CluInstructorInfo(clu.getPrimaryInstructor()) : null;
            this.instructors = new ArrayList<CluInstructorInfo>();
            for (CluInstructor cluInstructor : clu.getInstructors()) {
                this.instructors.add(new CluInstructorInfo(cluInstructor));
            }
            this.expectedFirstAtp = clu.getExpectedFirstAtp();
            this.lastAtp = clu.getLastAtp();
            this.lastAdmitAtp = clu.getLastAdmitAtp();
            this.intensity = (null != clu.getIntensity()) ? new AmountInfo(clu.getIntensity()) : null;
            this.stdDuration = (null != clu.getStdDuration()) ? new TimeAmountInfo(clu.getStdDuration()) : null;
            this.canCreateLui = clu.isCanCreateLui();
            this.referenceURL = clu.getReferenceURL();
            this.luCodes = new ArrayList<LuCodeInfo>();
            for (LuCode luCode : clu.getLuCodes()) {
                this.luCodes.add(new LuCodeInfo(luCode));
            }
            this.nextReviewPeriod = clu.getNextReviewPeriod();
            this.isEnrollable = clu.isEnrollable();
            this.offeredAtpTypes = (null != clu.getOfferedAtpTypes()) ? new ArrayList<String>(clu.getOfferedAtpTypes()) : null;
            this.hasEarlyDropDeadline = clu.isHasEarlyDropDeadline();
            this.defaultEnrollmentEstimate = clu.getDefaultEnrollmentEstimate();
            this.defaultMaximumEnrollment = clu.getDefaultMaximumEnrollment();
            this.isHazardousForDisabledStudents = clu.isHazardousForDisabledStudents();
            this.feeInfo = (null != clu.getFeeInfo()) ? new CluFeeInfo(clu.getFeeInfo()) : null;
            this.accountingInfo = (null != clu.getAccountingInfo()) ? new CluAccountingInfo(clu.getAccountingInfo()) : null;
            this.versionInfo = (null != clu.getVersionInfo()) ? new VersionInfo(clu.getVersionInfo()) : null;
            this.effectiveDate = (null != clu.getEffectiveDate()) ? new Date(clu.getEffectiveDate().getTime()) : null;
            this.expirationDate = (null != clu.getExpirationDate()) ? new Date(clu.getExpirationDate().getTime()) : null;
        }
    }

    @Override
    public CluIdentifierInfo getOfficialIdentifier() {
        return officialIdentifier;
    }

    public void setOfficialIdentifier(CluIdentifierInfo officialIdentifier) {
        this.officialIdentifier = officialIdentifier;
    }

    @Override
    public List<CluIdentifierInfo> getAlternateIdentifiers() {
        if (alternateIdentifiers == null) {
            alternateIdentifiers = new ArrayList<CluIdentifierInfo>();
        }
        return alternateIdentifiers;
    }

    public void setAlternateIdentifiers(List<CluIdentifierInfo> alternateIdentifiers) {
        this.alternateIdentifiers = alternateIdentifiers;
    }


    @Override
    public String getStudySubjectArea() {
        return studySubjectArea;
    }

    public void setStudySubjectArea(String studySubjectArea) {
        this.studySubjectArea = studySubjectArea;
    }

    @Override
    public List<AccreditationInfo> getAccreditations() {
        if (accreditations == null) {
            accreditations = new ArrayList<AccreditationInfo>();
        }
        return accreditations;
    }

    public void setAccreditations(List<AccreditationInfo> accreditations) {
        this.accreditations = accreditations;
    }

    @Override
    public List<String> getCampusLocations() {
        if (campusLocations == null) {
            campusLocations = new ArrayList<String>();
        }
        return campusLocations;
    }

    public void setCampusLocations(List<String> campusLocations) {
        this.campusLocations = campusLocations;
    }

    @Override
    public List<AdminOrgInfo> getAdminOrgs() {
        if (adminOrgs == null) {
            adminOrgs = new ArrayList<AdminOrgInfo>();
        }
        return adminOrgs;
    }

    public void setAdminOrgs(List<AdminOrgInfo> adminOrgs) {
        this.adminOrgs = adminOrgs;
    }

    @Override
    public CluInstructorInfo getPrimaryInstructor() {
        return primaryInstructor;
    }

    public void setPrimaryInstructor(CluInstructorInfo primaryInstructor) {
        this.primaryInstructor = primaryInstructor;
    }

    @Override
    public List<CluInstructorInfo> getInstructors() {
        if (instructors == null) {
            instructors = new ArrayList<CluInstructorInfo>();
        }
        return instructors;
    }

    public void setInstructors(List<CluInstructorInfo> instructors) {
        this.instructors = instructors;
    }

    @Override
    public AmountInfo getIntensity() {
        return intensity;
    }

    public void setIntensity(AmountInfo intensity) {
        this.intensity = intensity;
    }

    @Override
    public TimeAmountInfo getStdDuration() {
        return stdDuration;
    }

    public void setStdDuration(TimeAmountInfo stdDuration) {
        this.stdDuration = stdDuration;
    }

    @Override
    public boolean isCanCreateLui() {
        return canCreateLui;
    }

    public void setCanCreateLui(boolean canCreateLui) {
        this.canCreateLui = canCreateLui;
    }

    @Override
    public String getReferenceURL() {
        return referenceURL;
    }

    public void setReferenceURL(String referenceURL) {
        this.referenceURL = referenceURL;
    }

    @Override
    public List<LuCodeInfo> getLuCodes() {
        if (luCodes == null) {
            luCodes = new ArrayList<LuCodeInfo>();
        }
        return luCodes;
    }

    public void setLuCodes(List<LuCodeInfo> luCodes) {
        this.luCodes = luCodes;
    }

    @Override
    public String getNextReviewPeriod() {
        return nextReviewPeriod;
    }

    public void setNextReviewPeriod(String nextReviewPeriod) {
        this.nextReviewPeriod = nextReviewPeriod;
    }

    @Override
    public boolean isEnrollable() {
        return isEnrollable;
    }

    public void setEnrollable(boolean isEnrollable) {
        this.isEnrollable = isEnrollable;
    }

    @Override
    public List<String> getOfferedAtpTypes() {
        if (offeredAtpTypes == null) {
            offeredAtpTypes = new ArrayList<String>();
        }
        return offeredAtpTypes;
    }

    public void setOfferedAtpTypes(List<String> offeredAtpTypes) {
        this.offeredAtpTypes = offeredAtpTypes;
    }

    @Override
    public boolean isHasEarlyDropDeadline() {
        return hasEarlyDropDeadline;
    }

    public void setHasEarlyDropDeadline(boolean hasEarlyDropDeadline) {
        this.hasEarlyDropDeadline = hasEarlyDropDeadline;
    }

    @Override
    public int getDefaultEnrollmentEstimate() {
        return defaultEnrollmentEstimate;
    }

    public void setDefaultEnrollmentEstimate(int defaultEnrollmentEstimate) {
        this.defaultEnrollmentEstimate = defaultEnrollmentEstimate;
    }

    @Override
    public int getDefaultMaximumEnrollment() {
        return defaultMaximumEnrollment;
    }

    public void setDefaultMaximumEnrollment(int defaultMaximumEnrollment) {
        this.defaultMaximumEnrollment = defaultMaximumEnrollment;
    }

    @Override
    public boolean isHazardousForDisabledStudents() {
        return isHazardousForDisabledStudents;
    }

    public void setHazardousForDisabledStudents(boolean isHazardousForDisabledStudents) {
        this.isHazardousForDisabledStudents = isHazardousForDisabledStudents;
    }

    @Override
    public CluFeeInfo getFeeInfo() {
        return feeInfo;
    }

    public void setFeeInfo(CluFeeInfo feeInfo) {
        this.feeInfo = feeInfo;
    }

    @Override
    public CluAccountingInfo getAccountingInfo() {
        return accountingInfo;
    }

    public void setAccountingInfo(CluAccountingInfo accountingInfo) {
        this.accountingInfo = accountingInfo;
    }


    @Override
    public VersionInfo getVersionInfo() {
        return versionInfo;
    }

    public void setVersionInfo(VersionInfo versionInfo) {
        this.versionInfo = versionInfo;
    }

    @Override
    public String getExpectedFirstAtp() {
        return expectedFirstAtp;
    }

    public void setExpectedFirstAtp(String expectedFirstAtp) {
        this.expectedFirstAtp = expectedFirstAtp;
    }

    @Override
    public String getLastAtp() {
        return lastAtp;
    }

    public void setLastAtp(String lastAtp) {
        this.lastAtp = lastAtp;
    }

    @Override
    public String getLastAdmitAtp() {
        return lastAdmitAtp;
    }

    public void setLastAdmitAtp(String lastAdmitAtp) {
        this.lastAdmitAtp = lastAdmitAtp;
    }

    @Override
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Override
    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "CluInfo[id=" + this.getId() + ", type=" + this.getTypeKey() + "]";
    }
}