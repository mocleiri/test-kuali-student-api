/*
 * Copyright 2007 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.student.lum.program.service.assembler;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.kuali.student.common.util.UUIDHelper;
import org.kuali.student.core.assembly.BOAssembler;
import org.kuali.student.core.assembly.BaseDTOAssemblyNode;
import org.kuali.student.core.assembly.BaseDTOAssemblyNode.NodeOperation;
import org.kuali.student.core.assembly.data.AssemblyException;
import org.kuali.student.core.dto.AmountInfo;
import org.kuali.student.core.dto.RichTextInfo;
import org.kuali.student.core.exceptions.DataValidationErrorException;
import org.kuali.student.lum.course.service.assembler.CourseAssembler;
import org.kuali.student.lum.lo.service.LearningObjectiveService;
import org.kuali.student.lum.lu.dto.AdminOrgInfo;
import org.kuali.student.lum.lu.dto.CluIdentifierInfo;
import org.kuali.student.lum.lu.dto.CluInfo;
import org.kuali.student.lum.lu.dto.CluResultInfo;
import org.kuali.student.lum.lu.dto.LuCodeInfo;
import org.kuali.student.lum.lu.dto.ResultOptionInfo;
import org.kuali.student.lum.lu.service.LuService;
import org.kuali.student.lum.program.dto.CoreProgramInfo;
import org.kuali.student.lum.program.dto.MajorDisciplineInfo;
import org.kuali.student.lum.program.dto.ProgramVariationInfo;
import org.kuali.student.lum.service.assembler.CluAssemblerUtils;

/**
 * @author KS TODO - Much of this should be shared with ProgramVariationAssembler (and probably other Program Assemblers to
 *         come). AssemblerUtils?
 */
public class MajorDisciplineAssembler implements BOAssembler<MajorDisciplineInfo, CluInfo> {
    final static Logger LOG = Logger.getLogger(CourseAssembler.class);

    private LuService luService;
    private LearningObjectiveService loService;

    private ProgramVariationAssembler programVariationAssembler;
    private CoreProgramAssembler coreProgramAssembler;
    private CluAssemblerUtils cluAssemblerUtils;

    @Override
    public MajorDisciplineInfo assemble(CluInfo clu, MajorDisciplineInfo majorDiscipline, boolean shallowBuild) throws AssemblyException {

        MajorDisciplineInfo mdInfo = (null != majorDiscipline) ? majorDiscipline : new MajorDisciplineInfo();

        // Copy all the data from the clu to the majordiscipline
        mdInfo.setIntensity((null != clu.getIntensity()) ? clu.getIntensity().getUnitType() : null);
        mdInfo.setReferenceURL((null != clu.getReferenceURL()) ? clu.getReferenceURL() : null);
        mdInfo.setPublishedInstructors(clu.getInstructors());
        mdInfo.setCredentialProgramId(getCredentialProgramID(clu.getId()));
        mdInfo.setVariations(getVariations(clu.getId(), shallowBuild));
        setIds(mdInfo, clu);
        setCodes(mdInfo, clu);
        mdInfo.setResultOptions(getResultOptions(clu));
        mdInfo.setStdDuration(clu.getStdDuration());
        mdInfo.setStartTerm(clu.getExpectedFirstAtp());
        mdInfo.setEndTerm(clu.getLastAtp());
        mdInfo.setEndProgramEntryTerm(clu.getLastAdmitAtp());
        mdInfo.setNextReviewPeriod(clu.getNextReviewPeriod());
        mdInfo.setEffectiveDate(clu.getEffectiveDate());
        setTitles(mdInfo, clu);
        mdInfo.setDescr(clu.getDescr());
        mdInfo.setCatalogDescr(getCatalogDescr(clu.getId()));
        mdInfo.setLearningObjectives(cluAssemblerUtils.getLearningObjectives(clu.getId(), shallowBuild));
        mdInfo.setCampusLocations(clu.getCampusLocations());
        mdInfo.setOrgCoreProgram(getCoreProgram(clu.getId(), shallowBuild));
        /* TODO
        mdInfo.setProgramRequirements(???);
        */
        // TODO - https://test.kuali.org/confluence/display/KULSTU/majorDisciplineInfo+Structure says ....orgId
        mdInfo.setAccreditingAgencies(clu.getAccreditations());
        // TODO - wait for Neerav's check in to do orgs
        setOrgs(mdInfo, clu.getAdminOrgs());

        mdInfo.setId(clu.getId());
        mdInfo.setMetaInfo(clu.getMetaInfo());
        mdInfo.setType(clu.getType());
        mdInfo.setState(clu.getState());
        return mdInfo;
    }

    private void setIds(MajorDisciplineInfo mdInfo, CluInfo clu) {
        mdInfo.setCode(clu.getOfficialIdentifier().getCode());
        mdInfo.setShortTitle(clu.getOfficialIdentifier().getShortName());
        mdInfo.setLongTitle(clu.getOfficialIdentifier().getLongName());
    }

    private List<ProgramVariationInfo> getVariations(String cluId, boolean shallowBuild) throws AssemblyException {
        List<String> variationIds;
        List<ProgramVariationInfo> variations = new ArrayList<ProgramVariationInfo>();
        try {
            variationIds = luService.getRelatedCluIdsByCluId(cluId, ProgramAssemblerConstants.HAS_PROGRAM_VARIATION);

            for (String variationId : variationIds) {
                CluInfo variationClu = luService.getClu(variationId);
                variations.add(programVariationAssembler.assemble(variationClu, null, false));
            }
        } catch (Exception e) {
            throw new AssemblyException(e);
        }
        return variations;
    }

    private String getCredentialProgramID(String cluId) throws AssemblyException {
        List<String> credentialProgramIDs = null;
        try {
            credentialProgramIDs = luService.getCluIdsByRelation(cluId, ProgramAssemblerConstants.HAS_MAJOR_PROGRAM);
        } catch (Exception e) {
            throw new AssemblyException(e);
        }
        // Can a MajorDiscipline have more than one Credential Program?
        // TODO - do we need to validate that?
        if (null == credentialProgramIDs || credentialProgramIDs.size() == 0) {
            throw new AssemblyException("MajorDiscipline with ID == " + cluId + " has no Credential Program associated with it.");
        } else if (credentialProgramIDs.size() > 1) {
            throw new AssemblyException("MajorDiscipline with ID == " + cluId + " has more than one Credential Program associated with it.");
        }

        return credentialProgramIDs.get(0);
    }

    private void setCodes(MajorDisciplineInfo majorDiscipline, CluInfo clu) {
        List<LuCodeInfo> luCodes = clu.getLuCodes();
        for (LuCodeInfo codeInfo : luCodes) {
            if (ProgramAssemblerConstants.CIP_2000.equals(codeInfo.getId())) {
                majorDiscipline.setCip2000Code(codeInfo.getValue());
            } else if (ProgramAssemblerConstants.CIP_2010.equals(codeInfo.getId())) {
                majorDiscipline.setCip2010Code(codeInfo.getValue());
            } else if (ProgramAssemblerConstants.HEGIS.equals(codeInfo.getId())) {
                majorDiscipline.setHegisCode(codeInfo.getValue());
            } else if (ProgramAssemblerConstants.UNIVERSITY_CLASSIFICATION.equals(codeInfo.getId())) {
                majorDiscipline.setUniversityClassification(codeInfo.getValue());
            } else if (ProgramAssemblerConstants.SELECTIVE_ENROLLMENT.equals(codeInfo.getId())) {
                majorDiscipline.setSelectiveEnrollmentCode(codeInfo.getValue());
            }
        }
    }
    
    private List<String> getResultOptions(CluInfo clu) {
        List<String> resultStrs = new ArrayList<String>();
        try {
            List<CluResultInfo> rInfos = luService.getCluResultByClu(clu.getId());
            for (CluResultInfo rInfo : rInfos) {
                for (ResultOptionInfo optionInfo : rInfo.getResultOptions()) {
                    resultStrs.add(optionInfo.getDesc().getPlain());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultStrs;
    }

    private void setTitles(MajorDisciplineInfo mdInfo, CluInfo clu) {
        for (CluIdentifierInfo cluIdInfo : clu.getAlternateIdentifiers()) {
            String idInfoType = cluIdInfo.getType();
            if (ProgramAssemblerConstants.TRANSCRIPT.equals(idInfoType)) {
                mdInfo.setTranscriptTitle(cluIdInfo.getShortName());
            } else if (ProgramAssemblerConstants.DIPLOMA.equals(idInfoType)) {
                mdInfo.setDiplomaTitle(cluIdInfo.getShortName());
            }
        }
    }

    private RichTextInfo getCatalogDescr(String cluId) throws AssemblyException {
        RichTextInfo returnInfo = new RichTextInfo();
        /* TODO - LuService's Publication methods aren't implemented yet
        try {
            List<CluPublicationInfo> pubs = luService.getCluPublicationsByCluId(cluId);
            for (CluPublicationInfo pubInfo : pubs) {
                for (FieldInfo fieldInfo : pubInfo.getVariants()) {
                    if (fieldInfo.getId().equals(CluInfoConstants.CLU_INFO + "." + CluInfoConstants.DESCR)) {
                        returnInfo.setPlain(fieldInfo.getValue());
                        return returnInfo; // or break to a label to avoid multiple return points
                    }
                }
            }
        } catch (Exception e) {
            throw new AssemblyException(e);
        }
        */
        return returnInfo;
    }

    private CoreProgramInfo getCoreProgram(String cluId, boolean shallowBuild) throws AssemblyException {
        CoreProgramInfo coreProgramInfo = null;
        try {
            List<CluInfo> corePrograms = luService.getRelatedClusByCluId(cluId, ProgramAssemblerConstants.HAS_CORE_PROGRAM);
            // TODO - is it an error if there's more than one core program?
            if (corePrograms.size() == 1) {
                coreProgramInfo = coreProgramAssembler.assemble(corePrograms.get(0), null, shallowBuild);
            } else if (corePrograms.size() > 1) {
                throw new AssemblyException(new DataValidationErrorException("MajorDiscipline has more than one associated Core Program"));
            }
        } catch (Exception e) {
            throw new AssemblyException(e);
        }
        return coreProgramInfo;
    }

    // TODO - set institution, accreditingAgencies,
    //            divisionsContentOwner, divisionsStudentOversight, divisionsDeployment, divisionsFinancialResources, divisionsFinancialControl,
    //            unitsContentOwner, unitsStudentOversight, unitsDeployment, unitsFinancialResources, unitsFinancialControl
    private void setOrgs(MajorDisciplineInfo mdInfo, List<AdminOrgInfo> orgInfos) {
        /*
        for (AdminOrgInfo orgInfo : orgInfos) {

        }
        */
    }

    @Override
    public BaseDTOAssemblyNode<MajorDisciplineInfo, CluInfo> disassemble(MajorDisciplineInfo major, NodeOperation operation) throws AssemblyException {
		if (major == null) {
		    LOG.error("Major for  disassemble is null!");
			throw new AssemblyException("Major cannot be null");
		}
    	
		BaseDTOAssemblyNode<MajorDisciplineInfo, CluInfo> result = new BaseDTOAssemblyNode<MajorDisciplineInfo, CluInfo>(
				this);
		
		CluInfo clu;
		try {
			clu = (NodeOperation.UPDATE == operation) ? luService.getClu(major.getId()) : new CluInfo();
        } catch (Exception e) {
			throw new AssemblyException("Error getting existing learning unit during major update", e);
        } 
        
		clu.setId(UUIDHelper.genStringUUID(major.getId()));
		if (null == major.getId()) {
			major.setId(clu.getId());
		}
		clu.setType(major.getType());
		clu.setState(major.getState());

        disassembleIdentifiers(clu, major);
//
//		List<BaseDTOAssemblyNode<?, ?>> variationResults;
//        try {
//            variationResults = disassembleVariations(clu, major, operation);
//            result.getChildNodes().addAll(variationResults);
//        } catch (DoesNotExistException e) {
//        } catch (Exception e) {
//            throw new AssemblyException("Error while disassembling Variation", e);
//        }

        AmountInfo intensity = new AmountInfo();
        intensity.setUnitType(major.getIntensity());
		clu.setIntensity(intensity);

		clu.setReferenceURL(major.getReferenceURL());
		clu.setInstructors(major.getPublishedInstructors());
		
//		disassembleCodes(major, clu);
		
		clu.setStdDuration(major.getStdDuration());
		
		clu.setDescr(major.getDescr());
		
		clu.setAttributes(major.getAttributes());
		clu.setCampusLocations(major.getCampusLocations());
		
		clu.setMetaInfo(major.getMetaInfo());
		
		clu.setEffectiveDate(major.getEffectiveDate());

		clu.setExpectedFirstAtp(major.getStartTerm());
		clu.setLastAtp(major.getEndTerm());
		clu.setLastAdmitAtp(major.getEndProgramEntryTerm());
		
		clu.setNextReviewPeriod(major.getNextReviewPeriod());
		
//        try {
//    		List<BaseDTOAssemblyNode<?, ?>> loResults;
//    		loResults = disassembleLearningObjectives(clu.getId(), major, operation);
//            result.getChildNodes().addAll(loResults);
//        } catch (DoesNotExistException e) {
//        } catch (Exception e) {
//            throw new AssemblyException("Error while disassembling los", e);
//        }

//TODO	    private String credentialProgramId;           Is this the cluId?

//        disassembleOrgs(major, clu);
//        disassembleCluResults(major, operation, null, "resultOptions", "resultOptions");

//        clu.setAccreditations(major.getAccreditingAgencies());

//        disassembleCatalogDescr(major, clu);


//	    private List<String> programRequirements;


		

		// Add the Clu to the result
		result.setNodeData(clu);
		result.setOperation(operation);
		result.setBusinessDTORef(major);

	    
    	return result;
    }

    private void disassembleIdentifiers(CluInfo clu, MajorDisciplineInfo major) {
        CluIdentifierInfo identifier = new CluIdentifierInfo();
        identifier.setCode(major.getCode());
        identifier.setLongName(major.getLongTitle());
        identifier.setShortName(major.getShortTitle());
        clu.setOfficialIdentifier(identifier);
    }

    // Setters for Spring
    public void setLuService(LuService luService) {
        this.luService = luService;
    }

    public void setLoService(LearningObjectiveService loService) {
        this.loService = loService;
    }

    public void setProgramVariationAssembler(ProgramVariationAssembler programVariationAssembler) {
        this.programVariationAssembler = programVariationAssembler;
    }

    public ProgramVariationAssembler getProgramVariationAssembler() {
        return programVariationAssembler;
    }

    public void setCoreProgramAssembler(CoreProgramAssembler coreProgramAssembler) {
        this.coreProgramAssembler = coreProgramAssembler;
    }

    public void setCluAssemblerUtils(CluAssemblerUtils cluAssemblerUtils) {
        this.cluAssemblerUtils = cluAssemblerUtils;
    }
}
