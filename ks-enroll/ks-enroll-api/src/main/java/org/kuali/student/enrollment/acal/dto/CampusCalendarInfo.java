/*
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
package org.kuali.student.enrollment.acal.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.kuali.student.enrollment.acal.infc.CampusCalendar;
import org.kuali.student.r2.common.dto.KeyEntityInfo;
import org.kuali.student.r2.common.infc.Attribute;
import org.kuali.student.r2.common.infc.Meta;
import org.kuali.student.r2.common.infc.RichText;
import org.w3c.dom.Element;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CampusCalendarInfo", propOrder = {"key", "typeKey", "stateKey", "name", "descr", "startDate", "endDate", "location", "metaInfo", "attributes", "_futureElements"})
public class CampusCalendarInfo extends TermInfo implements CampusCalendar, Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement
    private String location;
    @XmlAnyElement
    private List<Element> _futureElements;
 
    public static CampusCalendarInfo newInstance() {
        return new CampusCalendarInfo();
    }
    
    public static CampusCalendarInfo getInstance(CampusCalendarInfo campusCalendarInfo) {
        return new CampusCalendarInfo(campusCalendarInfo);
    }

    public static CampusCalendarInfo getInstance(String key, String name, RichText descr,
                                                    String typeKey, String stateKey,
                                                    Date startDate, Date endDate, String location,
                                                    List<? extends Attribute> attributes, Meta metaInfo) {
        return new CampusCalendarInfo(key, name, descr, typeKey, stateKey, startDate, endDate, location, attributes, metaInfo);
    }
    
    private CampusCalendarInfo() {
        location = null;
        _futureElements = null;
    }

    /**
     * Constructs a new CampusCalendarInfo from another
     * CampusCalendar.
     *
     * @param campusCalendar the Campus Calendar to copy
     */
    private CampusCalendarInfo(CampusCalendar campusCalendar) {
        super(campusCalendar);
        _futureElements = null;
    }

    private CampusCalendarInfo(String key, String name, RichText descr,
                                String typeKey, String stateKey,
                                Date startDate, Date endDate, String location,
                                List<? extends Attribute> attributes, Meta metaInfo) {
        super(key, name, descr, endDate, endDate, typeKey, stateKey, attributes, metaInfo);
        this.location = location;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }
}
