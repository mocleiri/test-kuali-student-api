/*
 * Copyright 2009 The Kuali Foundation Licensed under the Educational Community
 * License, Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.opensource.org/licenses/ecl1.php Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.kuali.student.r2.lum.program.dto;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.kuali.student.r2.common.dto.IdNamelessEntityInfo;
import org.kuali.student.r2.lum.program.infc.HonorsProgram;
import org.kuali.student.r2.lum.program.infc.MinorDiscipline;
import org.w3c.dom.Element;


@XmlType(name = "MinorDisciplineInfo", propOrder = {"id",
    "typeKey",
    "stateKey",
    "credentialProgramId",
    "programRequirementIds",
    "meta",
    "attributes",
    "_futureElements"})
@XmlAccessorType(XmlAccessType.FIELD)
public class MinorDisciplineInfo extends IdNamelessEntityInfo implements MinorDiscipline, Serializable {
    
    private static final long serialVersionUID = 1L;
    @XmlElement
    private String credentialProgramId;
    @XmlElement
    private List<String> programRequirementIds;
    @XmlAnyElement
    private List<Element> _futureElements;
    
    public MinorDisciplineInfo() {
        this.programRequirementIds = new ArrayList<String>();
    }
    
    public MinorDisciplineInfo(MinorDiscipline minorDiscipline) {
        super (minorDiscipline);
        if (minorDiscipline != null) {
            this.credentialProgramId = minorDiscipline.getCredentialProgramId();
            this.programRequirementIds = minorDiscipline.getProgramRequirementIds() != null 
                    ? new ArrayList<String>(minorDiscipline.getProgramRequirementIds()) 
                    : new ArrayList<String>();
        }
    }

    /**
     * Identifier of the credential program under which the honors belongs
     */
    @Override
    public String getCredentialProgramId() {
        return credentialProgramId;
    }
    
    public void setCredentialProgramId(String credentialProgramId) {
        this.credentialProgramId = credentialProgramId;
    }
    
    @Override
    public List<String> getProgramRequirementIds() {
        if (programRequirementIds == null) {
            programRequirementIds = new ArrayList<String>(0);
        }
        return programRequirementIds;
    }
    
    public void setProgramRequirementIds(List<String> programRequirements) {
        this.programRequirementIds = programRequirements;
    }

    /**
     * Compatibility method for R1.
     * Same as getProgramRequirementIds
     * @deprecated
     */
    @Deprecated
    public List<String> getProgramRequirements() {
        return this.getProgramRequirementIds();
    }

    /**
     * Compatibility method for R1.
     * Same as setProgramRequirementIds
     * @deprecated
     */
    @Deprecated
    public void setProgramRequirements(List<String> programRequirements) {
        this.setProgramRequirementIds(programRequirements);
    }
}
