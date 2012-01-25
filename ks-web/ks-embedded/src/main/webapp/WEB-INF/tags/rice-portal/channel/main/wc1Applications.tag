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

    <strong>Holiday Calendar</strong>
    <ul class="chan">
        <li><portal:portalLink displayTitle="true" title="Create Holiday Calendar" url="${ConfigProperties.application.url}/kr-krad/holidayCalendar?viewId=holidayCalendarEditView&methodToCall=start&action=C" /></li>
        <li><portal:portalLink displayTitle="true" title="View Holiday Calendar" url="${ConfigProperties.application.url}/kr-krad/holidayCalendar?viewId=holidayCalendarView&methodToCall=start" /></li>
    </ul>

</div>
<channel:portalChannelBottom />
