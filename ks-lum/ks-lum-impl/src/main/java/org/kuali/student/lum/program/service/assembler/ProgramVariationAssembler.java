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

import org.apache.log4j.Logger;
import org.kuali.student.core.assembly.BOAssembler;
import org.kuali.student.core.assembly.BaseDTOAssemblyNode;
import org.kuali.student.core.assembly.BaseDTOAssemblyNode.NodeOperation;
import org.kuali.student.core.assembly.data.AssemblyException;
import org.kuali.student.core.dto.AmountInfo;
import org.kuali.student.core.exceptions.DoesNotExistException;
import org.kuali.student.lum.lu.dto.CluInfo;
import org.kuali.student.lum.lu.service.LuService;
import org.kuali.student.lum.program.dto.ProgramVariationInfo;
import org.kuali.student.lum.service.assembler.CluAssemblerUtils;

import java.util.List;

/**
 * @author KS
 *
 */
public class ProgramVariationAssembler implements BOAssembler<ProgramVariationInfo, CluInfo> {
    final static Logger LOG = Logger.getLogger(ProgramVariationAssembler.class);

    private LuService luService;
    private CluAssemblerUtils cluAssemblerUtils;
    private ProgramAssemblerUtils programAssemblerUtils;

    @Override
    public ProgramVariationInfo assemble(CluInfo clu, ProgramVariationInfo programVariation, boolean shallowBuild) throws AssemblyException {

        ProgramVariationInfo pvInfo = (null != programVariation) ? programVariation : new ProgramVariationInfo();

        // Copy all the data from the clu to the programvariation
        programAssemblerUtils.assembleBasics(clu, pvInfo);
        programAssemblerUtils.assembleIdentifiers(clu, pvInfo);
        programAssemblerUtils.assembleAdminOrgs(clu, pvInfo);
        programAssemblerUtils.assembleAtps(clu, pvInfo);
        programAssemblerUtils.assembleLuCodes(clu, pvInfo);
        programAssemblerUtils.assemblePublicationInfo(clu, pvInfo);
        programAssemblerUtils.assembleRequirements(clu, pvInfo);
        
        pvInfo.setResultOptions(programAssemblerUtils.assembleResultOptions(clu.getId(), ProgramAssemblerConstants.DEGREE_RESULTS));
        pvInfo.setLearningObjectives(cluAssemblerUtils.assembleLearningObjectives(clu.getId(), shallowBuild));

        pvInfo.setIntensity((null != clu.getIntensity()) ? clu.getIntensity().getUnitType() : null);
        pvInfo.setCampusLocations(clu.getCampusLocations());  
        pvInfo.setEffectiveDate(clu.getEffectiveDate());
        pvInfo.setDescr(clu.getDescr());

        return pvInfo;
    }

    @Override
    public BaseDTOAssemblyNode<ProgramVariationInfo, CluInfo> disassemble(ProgramVariationInfo variation, NodeOperation operation) throws AssemblyException {
    	BaseDTOAssemblyNode<ProgramVariationInfo, CluInfo> result = new BaseDTOAssemblyNode<ProgramVariationInfo, CluInfo>(this);
    	
    	if (variation == null) {
			// FIXME Unsure now if this is an exception or just return null or
			// empty assemblyNode
		    LOG.error("Variation to disassemble is null!");
			throw new AssemblyException("Variation can not be null");
		}

		CluInfo clu;
		try {
			if(NodeOperation.UPDATE == operation){
				if(variation.getId()!= null && !"".equals(variation.getId()))
					clu = luService.getClu(variation.getId());
				else{
					clu = new CluInfo();
					operation = NodeOperation.CREATE;
				}
			}
			else
				clu = new CluInfo();
			//clu = (NodeOperation.UPDATE == operation) ? luService.getClu(variation.getId()) : new CluInfo();
        } catch (Exception e) {
			throw new AssemblyException("Error getting existing learning unit during variation update", e);
        } 
        
        programAssemblerUtils.disassembleBasics(clu, variation, operation);
        if (variation.getId() == null)
        	variation.setId(clu.getId());
        programAssemblerUtils.disassembleIdentifiers(clu, variation, operation);
        programAssemblerUtils.disassembleAdminOrgs(clu, variation, operation);
        programAssemblerUtils.disassembleAtps(clu, variation, operation);
        programAssemblerUtils.disassembleLuCodes(clu, variation, operation);        
        programAssemblerUtils.disassemblePublicationInfo(clu, variation, operation);
        programAssemblerUtils.disassembleRequirements(clu, variation, operation);
        
        if (variation.getResultOptions() != null) {
            disassembleResultOptions(variation, operation, result);           
        }

        if (variation.getLearningObjectives() != null) {
            disassembleLearningObjectives(variation, operation, result);
        }
 
		AmountInfo intensity = new AmountInfo();
		intensity.setUnitType(variation.getIntensity());
		clu.setIntensity(intensity);
		
        clu.setCampusLocations(variation.getCampusLocations());
        clu.setEffectiveDate(variation.getEffectiveDate());
        clu.setDescr(variation.getDescr());
        
		// Add the Clu to the result
		result.setNodeData(clu);
		result.setOperation(operation);
		result.setBusinessDTORef(variation);
		return result;
    	
    }

    private void disassembleLearningObjectives(ProgramVariationInfo variation, NodeOperation operation, BaseDTOAssemblyNode<ProgramVariationInfo, CluInfo> result) throws AssemblyException {
        try {
            List<BaseDTOAssemblyNode<?, ?>> loResults = cluAssemblerUtils.disassembleLos(variation.getId(), variation.getState(),  variation.getLearningObjectives(), operation);
            if (loResults != null) {
                result.getChildNodes().addAll(loResults);
            }
        } catch (DoesNotExistException e) {
        } catch (Exception e) {
            throw new AssemblyException("Error while disassembling los", e);
        }
    }

    private void disassembleResultOptions(ProgramVariationInfo variation, NodeOperation operation, BaseDTOAssemblyNode<ProgramVariationInfo, CluInfo> result) throws AssemblyException {
        BaseDTOAssemblyNode<?, ?> resultOptions = cluAssemblerUtils.disassembleCluResults(
        		variation.getId(), variation.getState(), variation.getResultOptions(), operation, ProgramAssemblerConstants.DEGREE_RESULTS, "Result options", "Result option");
        if (resultOptions != null) {
            result.getChildNodes().add(resultOptions);           
        }
    }
    
    // Spring setter
    public void setLuService(LuService luService) {
        this.luService = luService;
    }
    
    public void setCluAssemblerUtils(CluAssemblerUtils cluAssemblerUtils) {
        this.cluAssemblerUtils = cluAssemblerUtils;
    }

    public void setProgramAssemblerUtils(ProgramAssemblerUtils programAssemblerUtils) {
        this.programAssemblerUtils = programAssemblerUtils;
    }
}
