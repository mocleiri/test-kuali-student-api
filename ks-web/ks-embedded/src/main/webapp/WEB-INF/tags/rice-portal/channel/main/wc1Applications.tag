<%--
 Copyright 2007-2009 The Kuali Foundation

 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.opensource.org/licenses/ecl2.php

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/rice-portal/jsp/sys/riceTldHeader.jsp"%>

<channel:portalChannelTop channelTitle="West Coast 1 Applications" />
<div class="body">
    <strong>Calendar Search</strong>
    <ul class="chan">
        <li><portal:portalLink displayTitle="true" title="Enrollment Home" url="${ConfigProperties.application.url}/kr-krad/launch?viewId=enrollmentHomeView&methodToCall=start" /></li>
    </ul>
    <strong>Holiday Calendar</strong>
    <ul class="chan">
        <li><portal:portalLink displayTitle="true" title="Create Holiday Calendar" url="${ConfigProperties.application.url}/kr-krad/holidayCalendar?viewId=holidayCalendarFlowView&pageId=holidayCalendarEditPage&methodToCall=start" /></li>
        <li><portal:portalLink displayTitle="true" title="Edit Holiday Calendar" url="${ConfigProperties.application.url}/kr-krad/holidayCalendar?viewId=holidayCalendarFlowView&pageId=holidayCalendarEditPage&methodToCall=start&hcId=testAtpId2" /></li>
        <li><portal:portalLink displayTitle="true" title="View Holiday Calendar" url="${ConfigProperties.application.url}/kr-krad/holidayCalendar?viewId=holidayCalendarFlowView&pageId=holidayCalendarViewPage&methodToCall=start&hcId=testAtpId2" /></li>
        <li><portal:portalLink displayTitle="true" title="Copy Holiday Calendar" url="${ConfigProperties.application.url}/kr-krad/holidayCalendar?viewId=holidayCalendarFlowView&pageId=holidayCalendarCopyPage&methodToCall=startNew" /></li>
    </ul>
    <strong>Academic Calendar</strong>
    <ul class="chan">
        <li><portal:portalLink displayTitle="true" title="Create Academic Calendar" url="${ConfigProperties.application.url}/kr-krad/academicCalendar?viewId=academicCalendarEditView&methodToCall=start" /></li>
        <li><portal:portalLink displayTitle="true" title="Edit Academic Calendar" url="${ConfigProperties.application.url}/kr-krad/academicCalendar?viewId=academicCalendarEditView&methodToCall=start&acalId=testAtpId1" /></li>
    </ul>

    <strong>Academic Term</strong>
    <ul class="chan">
        <li><portal:portalLink displayTitle="true" title="Edit Academic Term" url="${ConfigProperties.application.url}/kr-krad/academicTerm?viewId=academicTermEditView&methodToCall=editTerm&termId=testTermId1" /></li>
    </ul>

</div>
<channel:portalChannelBottom />
