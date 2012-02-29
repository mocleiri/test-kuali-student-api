/*
 * Copyright 2011 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 1.0 (the
 * "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.student.enrollment.courseoffering.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.kuali.student.r2.common.dto.IdEntityInfo;

import org.w3c.dom.Element;

/**
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FormatOfferingInfo", propOrder = {
                "id", "typeKey", "stateKey", "name", "descr", 
                "meta", "attributes", "_futureElements"})

public class FormatOfferingInfo 
    extends IdEntityInfo  {

    private static final long serialVersionUID = 1L;

    @XmlAnyElement
    private List<Element> _futureElements;


    /**
     * Constructs a new FormatOfferingInfo.
     */
    public FormatOfferingInfo() {
    }
}
