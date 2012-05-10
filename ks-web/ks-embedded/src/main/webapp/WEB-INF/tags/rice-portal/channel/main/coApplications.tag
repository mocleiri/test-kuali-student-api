<%@ include file="/rice-portal/jsp/sys/riceTldHeader.jsp"%>

<channel:portalChannelTop channelTitle="Course Offering Applications" />
<div class="body">
    <strong>Course Offering</strong>
    <ul class="chan">
        <li><portal:portalLink displayTitle="true" title="Manage Course Offering Rollover" url="${ConfigProperties.application.url}/kr-krad/courseOfferingRollover?viewId=courseOfferingRolloverManagementView&pageId=selectTermsForRollover&methodToCall=start"/></li>
    </ul>
    <strong>Basic DTO Lookup and Inquiry</strong>
    <ul class="chan">
        <li><portal:portalLink displayTitle="true" title="Activity Offering Lookup" url="${ConfigProperties.application.url}/kr-krad/lookup?methodToCall=start&dataObjectClassName=org.kuali.student.enrollment.courseoffering.dto.ActivityOfferingInfo&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
        <li><portal:portalLink displayTitle="true" title="Format Offering Info Lookup" url="${ConfigProperties.application.url}/kr-krad/lookup?methodToCall=start&dataObjectClassName=org.kuali.student.enrollment.courseoffering.dto.FormatOfferingInfo&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
        <li><portal:portalLink displayTitle="true" title="SocRolloverResultInfo Lookup" url="${ConfigProperties.application.url}/kr-krad/lookup?methodToCall=start&dataObjectClassName=org.kuali.student.enrollment.courseofferingset.dto.SocRolloverResultInfo&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
        <br>
        <text>(unused or not working stuff, will clean it up later)</text>
        <li><portal:portalLink displayTitle="true" title="Offering Instructor Info Lookup" url="${ConfigProperties.application.url}/kr-krad/lookup?viewId=KS-Person-LookupView&methodToCall=start&dataObjectClassName=org.kuali.rice.kim.api.identity.Person&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
        <li><portal:portalLink displayTitle="true" title="KS Person Lookup" url="${ConfigProperties.application.url}/kr-krad/lookup?methodToCall=start&id=2&dataObjectClassName=org.kuali.rice.kim.api.identity.Person&viewId=KS-Person-LookupView&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true"/></li>
    </ul>

    <strong>Activity Offering Maintenance</strong> <br/>
    <text>(Devs: Ingest ActivityOfferingWorkflowDocument.xml)</text>
    <ul class="chan">
        <li><portal:portalLink displayTitle="true" title="Activity Offering (New)" url="${ConfigProperties.application.url}/kr-krad/maintenance?methodToCall=start&dataObjectClassName=org.kuali.student.enrollment.class2.courseoffering.dto.ActivityOfferingFormObject&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true"/></li>
        <li><portal:portalLink displayTitle="true" title="Activity Offering (Edit)" url="${ConfigProperties.application.url}/kr-krad/maintenance?methodToCall=maintenanceEdit&dataObjectClassName=org.kuali.student.enrollment.class2.courseoffering.dto.ActivityOfferingFormObject&aoInfo.id=Lui-4&overrideKeys=aoInfo.id&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true"/></li>
    </ul>

</div>
<channel:portalChannelBottom />
