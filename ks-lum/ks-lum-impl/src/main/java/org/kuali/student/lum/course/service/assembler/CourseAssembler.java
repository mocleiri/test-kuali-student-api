/*
 * Copyright 2008 The Kuali Foundation
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
package org.kuali.student.lum.course.service.assembler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.kuali.student.common.util.UUIDHelper;
import org.kuali.student.core.assembly.BOAssembler;
import org.kuali.student.core.assembly.BaseDTOAssemblyNode;
import org.kuali.student.core.assembly.BaseDTOAssemblyNode.NodeOperation;
import org.kuali.student.core.assembly.data.AssemblyException;
import org.kuali.student.core.dto.RichTextInfo;
import org.kuali.student.core.exceptions.DoesNotExistException;
import org.kuali.student.core.exceptions.InvalidParameterException;
import org.kuali.student.core.exceptions.MissingParameterException;
import org.kuali.student.core.exceptions.OperationFailedException;
import org.kuali.student.lum.course.dto.CourseInfo;
import org.kuali.student.lum.course.dto.CourseJointInfo;
import org.kuali.student.lum.course.dto.FormatInfo;
import org.kuali.student.lum.lu.dto.AcademicSubjectOrgInfo;
import org.kuali.student.lum.lu.dto.AdminOrgInfo;
import org.kuali.student.lum.lu.dto.CluCluRelationInfo;
import org.kuali.student.lum.lu.dto.CluIdentifierInfo;
import org.kuali.student.lum.lu.dto.CluInfo;
import org.kuali.student.lum.lu.dto.CluResultInfo;
import org.kuali.student.lum.lu.dto.LuCodeInfo;
import org.kuali.student.lum.lu.dto.ResultOptionInfo;
import org.kuali.student.lum.lu.service.LuService;
/**
 * Assembler for CourseInfo. Provides assemble and disassemble operation on
 * CourseInfo from/to CluInfo and other base DTOs
 * 
 * @author Kuali Student Team
 * 
 */
public class CourseAssembler implements BOAssembler<CourseInfo, CluInfo> {

    final static Logger LOG = Logger.getLogger(CourseAssembler.class);
    
	private LuService luService;
	private FormatAssembler formatAssembler;
	private CourseJointAssembler courseJointAssembler;

	@Override
	public CourseInfo assemble(CluInfo clu, CourseInfo courseInfo,
			boolean shallowBuild) throws AssemblyException {

		CourseInfo course = (null != courseInfo) ? courseInfo
				: new CourseInfo();

		// Copy all the data from the clu to the course
		
		List<String> academicSubjectOrgs = new ArrayList<String>();
		for(AcademicSubjectOrgInfo orgInfo:clu.getAcademicSubjectOrgs()){
			academicSubjectOrgs.add(orgInfo.getOrgId());
		}
		course.setAcademicSubjectOrgs(academicSubjectOrgs);
		
		course.setAttributes(clu.getAttributes());
		course.setCampusLocations(clu.getCampusLocations());
		course.setCode(clu.getOfficialIdentifier().getCode());
		course.setCourseNumberSuffix(clu.getOfficialIdentifier()
				.getSuffixCode());
		course.setOutOfClassHours(clu.getIntensity());
		course.setInstructors(clu.getInstructors());
		course.setStartTerm(clu.getExpectedFirstAtp());
		course.setEndTerm(clu.getLastAtp());
		
		// TODO: LO
		// course.setCourseSpecificLOs();

		course.setCourseTitle(clu.getOfficialIdentifier().getLongName());

		// TODO: CrossListings
		// course.setCrossListings();

		course.setDepartment(clu.getPrimaryAdminOrg().getOrgId());
		course.setDescr(clu.getDescr());
		course.setDuration(clu.getStdDuration());
		course.setEffectiveDate(clu.getEffectiveDate());
		course.setExpirationDate(clu.getExpirationDate());

		// TODO: Fee
		// course.setFeeInfo(feeInfo)

		course.setId(clu.getId());
		course.setType(clu.getType());
		course.setTermsOffered(clu.getOfferedAtpTypes());
		course.setPrimaryInstructor(clu.getPrimaryInstructor());
		course.setInstructors(clu.getInstructors());
		course.setState(clu.getState());
		course.setSubjectArea(clu.getOfficialIdentifier().getDivision());
		course.setTranscriptTitle(clu.getOfficialIdentifier().getShortName());
		course.setMetaInfo(clu.getMetaInfo());

		// Don't make any changes to nested datastructures if this is
		if (!shallowBuild) {
			try {
				// Use the luService to find Joints, then convert and add to the
				// course
				List<CluCluRelationInfo> cluClus = luService
						.getCluCluRelationsByClu(clu.getId());
				
				for (CluCluRelationInfo cluRel : cluClus) {
					if (cluRel.getType().equals(
							CourseAssemblerConstants.JOINT_RELATION_TYPE)) {
						CourseJointInfo jointInfo = courseJointAssembler
								.assemble(cluRel, null, false);
						course.getJoints().add(jointInfo);
					}
				}
			} catch (DoesNotExistException e) {
			} catch (Exception e) {
				throw new AssemblyException("Error getting course joints", e);
			}

			try {
				// Use the luService to find formats, then convert and add to
				// the course
				List<CluInfo> formats = luService.getRelatedClusByCluId(course
						.getId(),
						CourseAssemblerConstants.COURSE_FORMAT_RELATION_TYPE);
				
				for (CluInfo format : formats) {
					FormatInfo formatInfo = formatAssembler.assemble(format,
							null, false);
					course.getFormats().add(formatInfo);
				}

			} catch (DoesNotExistException e) {
			} catch (Exception e) {
				throw new AssemblyException("Error getting related formats", e);
			}

			try{
				//Set Credit and Grading options
				List<CluResultInfo> cluResults = luService.getCluResultByClu(course.getId());
				
				List<String> creditOptions = assembleCluResults(CourseAssemblerConstants.COURSE_RESULT_TYPE_CREDITS, cluResults);
				course.setCreditOptions(creditOptions);
				
				List<String> gradingOptions = assembleCluResults(CourseAssemblerConstants.COURSE_RESULT_TYPE_GRADE, cluResults);
				course.setGradingOptions(gradingOptions);
			} catch (DoesNotExistException e){
			} catch (Exception e) {
				throw new AssemblyException("Error getting course results", e);
			}
		}

		//Special topics code
		course.setSpecialTopicsCourse(false);
		for(LuCodeInfo luCode : clu.getLuCodes()){
			if(CourseAssemblerConstants.COURSE_CODE_SPECIAL_TOPICS.equals(luCode.getValue())){
				course.setSpecialTopicsCourse(true);
				break;
			}
		}
		//Pilot Course code
		course.setPilotCourse(false);
		for(LuCodeInfo luCode : clu.getLuCodes()){
			if(CourseAssemblerConstants.COURSE_CODE_PILOT_COURSE.equals(luCode.getValue())){
				course.setPilotCourse(true);
				break;
			}
		}
		
		// TODO: Variations
		// course.setVariations(variations)

		return course;
	}

	@Override
	public BaseDTOAssemblyNode<CourseInfo, CluInfo> disassemble(
			CourseInfo course, NodeOperation operation)
			throws AssemblyException {

		if (course == null) {
			// FIXME Unsure now if this is an exception or just return null or
			// empty assemblyNode
		    LOG.error("Course to disassemble is null!");
			throw new AssemblyException("Course can not be null");
		}

		BaseDTOAssemblyNode<CourseInfo, CluInfo> result = new BaseDTOAssemblyNode<CourseInfo, CluInfo>(
				this);

		CluInfo clu;
		try {
			clu = (NodeOperation.UPDATE == operation) ? luService.getClu(course.getId()) : new CluInfo();
        } catch (Exception e) {
			throw new AssemblyException("Error getting existing learning unit during course update", e);
        } 

		// Create the id if it's not there already(important for creating
		// relations)
		clu.setId(UUIDHelper.genStringUUID(course.getId()));
		if (null == course.getId()) {
			course.setId(clu.getId());
		}
		clu.setType(course.getType());
		clu.setState(course.getState());

		CluIdentifierInfo identifier = new CluIdentifierInfo();
		identifier.setType(CourseAssemblerConstants.COURSE_OFFICIAL_IDENT_TYPE);
		identifier.setState(CourseAssemblerConstants.COURSE_OFFICIAL_IDENT_STATE);
		identifier.setCode(course.getCode());
		identifier.setSuffixCode(course.getCourseNumberSuffix());
		identifier.setLongName(course.getCourseTitle());
		
		identifier.setDivision(course.getSubjectArea());
		identifier.setShortName(course.getTranscriptTitle());
		clu.setOfficialIdentifier(identifier);

		AdminOrgInfo orgInfo = (null != clu.getPrimaryAdminOrg()) ? clu.getPrimaryAdminOrg() : new AdminOrgInfo();
		orgInfo.setOrgId(course.getDepartment());
		clu.setPrimaryAdminOrg(orgInfo);

		List<AcademicSubjectOrgInfo> subjectOrgs = new ArrayList<AcademicSubjectOrgInfo>();
		for (String orgId : course.getAcademicSubjectOrgs()) {
			AcademicSubjectOrgInfo info = new AcademicSubjectOrgInfo();
			info.setOrgId(orgId);
			subjectOrgs.add(info);
		}
		clu.setAcademicSubjectOrgs(subjectOrgs);

		
		clu.setAttributes(course.getAttributes());
		clu.setCampusLocations(course.getCampusLocations());
		clu.setDescr(course.getDescr());
		clu.setStdDuration(course.getDuration());
		clu.setEffectiveDate(course.getEffectiveDate());
		clu.setExpirationDate(course.getExpirationDate());

		clu.setOfferedAtpTypes(course.getTermsOffered());
		clu.setPrimaryInstructor(course.getPrimaryInstructor());
		
		clu.setIntensity(course.getOutOfClassHours());
		clu.setInstructors(course.getInstructors());
		
		clu.setExpectedFirstAtp(course.getStartTerm());
		clu.setLastAtp(course.getEndTerm());
		
		clu.setMetaInfo(course.getMetaInfo());

		// Add the Clu to the result
		result.setNodeData(clu);
		result.setOperation(operation);
		result.setBusinessDTORef(course);

		// Use the Format assembler to disassemble the formats and relations
		List<BaseDTOAssemblyNode<?, ?>> formatResults;
        try {
            formatResults = disassembleFormats(clu
            		.getId(), course, operation);
            result.getChildNodes().addAll(formatResults);
            
        } catch (DoesNotExistException e) {
        } catch (Exception e) {
            throw new AssemblyException("Error while disassembling format", e);
        }

		// Use the CourseJoint assembler to disassemble the CourseJoints and
		// relations
		List<BaseDTOAssemblyNode<?, ?>> courseJointResults = disassembleJoints(
				clu.getId(), course, operation);
		result.getChildNodes().addAll(courseJointResults);

		//Disassemble the CluResults (grading and credit options)
		BaseDTOAssemblyNode<?, ?> creditOptions = disassembleCluResults(
				clu.getId(), course.getState(), course.getCreditOptions(), operation, CourseAssemblerConstants.COURSE_RESULT_TYPE_CREDITS, "Credit outcome options", "Credit outcome option");
		result.getChildNodes().add(creditOptions);

		BaseDTOAssemblyNode<?, ?> gradingOptions = disassembleCluResults(
				clu.getId(), course.getState(), course.getGradingOptions(), operation, CourseAssemblerConstants.COURSE_RESULT_TYPE_GRADE, "Grading options", "Grading option");
		result.getChildNodes().add(gradingOptions);
		
		//add the special topics code if it did not exist, or remove if it was not wanted
		boolean alreadyHadSpecialTopicsCode = false;
		for(Iterator<LuCodeInfo> luCodeIterator = clu.getLuCodes().iterator();luCodeIterator.hasNext();){
			LuCodeInfo luCode = luCodeIterator.next();
			if(CourseAssemblerConstants.COURSE_CODE_SPECIAL_TOPICS.equals(luCode.getValue())){
				alreadyHadSpecialTopicsCode = true;
				if(!course.isSpecialTopicsCourse()){
					luCodeIterator.remove();
				}
				break;
			}
		}
		if(!alreadyHadSpecialTopicsCode && course.isSpecialTopicsCourse()){
			LuCodeInfo luCode = new LuCodeInfo();
			luCode.setValue(CourseAssemblerConstants.COURSE_CODE_SPECIAL_TOPICS);
			clu.getLuCodes().add(luCode);
		}
		
		//add the special topics code if it did not exist, or remove if it was not wanted
		boolean alreadyHadPilotCourseCode = false;
		for(Iterator<LuCodeInfo> luCodeIterator = clu.getLuCodes().iterator();luCodeIterator.hasNext();){
			LuCodeInfo luCode = luCodeIterator.next();
			if(CourseAssemblerConstants.COURSE_CODE_PILOT_COURSE.equals(luCode.getValue())){
				alreadyHadPilotCourseCode = true;
				if(!course.isPilotCourse()){
					luCodeIterator.remove();
				}
				break;
			}
		}
		if(!alreadyHadPilotCourseCode && course.isPilotCourse()){
			LuCodeInfo luCode = new LuCodeInfo();
			luCode.setValue(CourseAssemblerConstants.COURSE_CODE_PILOT_COURSE);
			clu.getLuCodes().add(luCode);
		}
		
		return result;
	}


	private List<String> assembleCluResults(String courseResultType, List<CluResultInfo> cluResults) throws AssemblyException{
		if(courseResultType==null){
			throw new AssemblyException("courseResultType can not be null");
		}
		List<String> results = new ArrayList<String>();
		//Loop through all the CluResults to find the one with the matching type
		for(CluResultInfo cluResult:cluResults){
			if(courseResultType.equals(cluResult.getType())){
				//Loop through all options and add to the list of Strings
				for(ResultOptionInfo resultOption: cluResult.getResultOptions()){
					results.add(resultOption.getResultComponentId());
				}
				break;
			}
		}
		return results;
	}

	private BaseDTOAssemblyNode<?, ?> disassembleCluResults(String cluId,
			String courseState, List<String> options, NodeOperation operation, String courseResultType, 
			String resultsDescription, String resultDescription) throws AssemblyException {
		BaseDTOAssemblyNode<List<String>, CluResultInfo> cluResultNode = new BaseDTOAssemblyNode<List<String>, CluResultInfo>(null);
		if(courseResultType==null){
			throw new AssemblyException("courseResultType can not be null");
		}
		
		// Get the current options and put them in a map of option type id/cluResult
		Map<String, ResultOptionInfo> currentResults = new HashMap<String, ResultOptionInfo>();

		CluResultInfo cluResult = null;
		
		//If this is not a create, lookup the results for this clu
		if (!NodeOperation.CREATE.equals(operation)) {
			try {
				List<CluResultInfo> cluResultList = luService.getCluResultByClu(cluId);
				
				for (CluResultInfo currentResult : cluResultList) {
					if (courseResultType
							.equals(currentResult.getType())) {
						cluResult = currentResult;
						if(NodeOperation.DELETE.equals(operation)){
							//if this is a delete, then we only need the CluResultInfo
							cluResultNode.setOperation(NodeOperation.DELETE);
						}else{
							//Find all the Result options and store in a map for easy access later
							cluResultNode.setOperation(NodeOperation.UPDATE);
							for(ResultOptionInfo resultOption:currentResult.getResultOptions()){
								currentResults.put(resultOption.getResultComponentId(), resultOption);
							}
						}
						break;
					}
				}
			} catch (DoesNotExistException e) {
			} catch (InvalidParameterException e) {
				throw new AssemblyException("Error getting related " + resultsDescription, e);
			} catch (MissingParameterException e) {
				throw new AssemblyException("Error getting related " + resultsDescription, e);
			} catch (OperationFailedException e) {
				throw new AssemblyException("Error getting related " + resultsDescription, e);
			}
		}

		//If this is a delete we don't need the result options, just the CluResultInfo
		if(!NodeOperation.DELETE.equals(operation)){
			if(cluResult == null){
				//Create a new resultInfo of the given type if one does not exist and set operation to Create
				cluResult = new CluResultInfo();
				cluResult.setCluId(cluId);
				cluResult.setState(courseState);
				cluResult.setType(courseResultType);
				RichTextInfo desc = new RichTextInfo();
				desc.setPlain(resultsDescription);
				cluResult.setDesc(desc);
				cluResult.setEffectiveDate(new Date());
				cluResultNode.setOperation(NodeOperation.CREATE);
			}
	
			cluResult.setResultOptions(new ArrayList<ResultOptionInfo>());
	
			// Loop through all the credit options in this course
			for (String optionType : options) {
				if(currentResults.containsKey(optionType)){
					//If the option exists already copy it to the new list of result options
					ResultOptionInfo resultOptionInfo = currentResults.get(optionType);
					cluResult.getResultOptions().add(resultOptionInfo);
				}else{
					//Otherwise create a new result option
					ResultOptionInfo resultOptionInfo = new ResultOptionInfo();
					RichTextInfo desc = new RichTextInfo();
					desc.setPlain(resultDescription);
					resultOptionInfo.setDesc(desc);
					resultOptionInfo.setResultComponentId(optionType);
					resultOptionInfo.setState(courseState);
					
					cluResult.getResultOptions().add(resultOptionInfo);
				}
			}
		}
		
		cluResultNode.setNodeData(cluResult);
		return cluResultNode;
	}

	// TODO This is pretty much a copy of the FormatAssembler's
	// disassembleActivities code... maybe can be made generic
	private List<BaseDTOAssemblyNode<?, ?>> disassembleFormats(String nodeId,
			CourseInfo course, NodeOperation operation)
			throws AssemblyException, DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException {

		List<BaseDTOAssemblyNode<?, ?>> results = new ArrayList<BaseDTOAssemblyNode<?, ?>>();

		// Get the current formats and put them in a map of format id/relation
		// id
		Map<String, String> currentformatIds = new HashMap<String, String>();

		if (!NodeOperation.CREATE.equals(operation)) {
			try {
				List<CluCluRelationInfo> formatRelationships = luService
						.getCluCluRelationsByClu(course.getId());
				
				//formatRelationships = (null == formatRelationships) ? new ArrayList<CluCluRelationInfo>() : formatRelationships;
				
				for (CluCluRelationInfo formatRelation : formatRelationships) {
					if (CourseAssemblerConstants.COURSE_FORMAT_RELATION_TYPE
							.equals(formatRelation.getType())) {
						currentformatIds.put(formatRelation.getRelatedCluId(),
								formatRelation.getId());
					}
				}
			} catch (DoesNotExistException e) {
			} catch (InvalidParameterException e) {
				throw new AssemblyException("Error getting related formats", e);
			} catch (MissingParameterException e) {
				throw new AssemblyException("Error getting related formats", e);
			} catch (OperationFailedException e) {
				throw new AssemblyException("Error getting related formats", e);
			}
		}

		// Loop through all the formats in this course
		for (FormatInfo format : course.getFormats()) {

		    //  If this is a course create/new format update then all formats will be created
		    if (NodeOperation.CREATE == operation
		            || (NodeOperation.UPDATE == operation && !currentformatIds.containsKey(format.getId()) )) {
                // the format does not exist, so create
                // Assemble and add the format
                BaseDTOAssemblyNode<FormatInfo, CluInfo> formatNode = formatAssembler
                        .disassemble(format, NodeOperation.CREATE);
                results.add(formatNode);

                // Create the relationship and add it as well
                CluCluRelationInfo relation = new CluCluRelationInfo();
                relation.setCluId(nodeId);
                relation.setRelatedCluId(formatNode.getNodeData().getId());// this
                // should
                // already
                // be set even if
                // it's a create
                relation
                        .setType(CourseAssemblerConstants.COURSE_FORMAT_RELATION_TYPE);
                relation.setState(course.getState());

                BaseDTOAssemblyNode<CourseInfo, CluCluRelationInfo> relationNode = new BaseDTOAssemblyNode<CourseInfo, CluCluRelationInfo>(
                        null);
                relationNode.setNodeData(relation);
                relationNode.setOperation(NodeOperation.CREATE);

                results.add(relationNode);
            } else if (NodeOperation.UPDATE == operation
					&& currentformatIds.containsKey(format.getId())) {
				// If the course already has this format, then just update the
				// format
				BaseDTOAssemblyNode<FormatInfo, CluInfo> formatNode = formatAssembler
						.disassemble(format, NodeOperation.UPDATE);
				results.add(formatNode);

				// remove this entry from the map so we can tell what needs to
				// be deleted at the end
				currentformatIds.remove(format.getId());
			} else if (NodeOperation.DELETE == operation
                    && currentformatIds.containsKey(format.getId()))  {
			    // Delete the Format and its relation
	            CluCluRelationInfo relationToDelete = new CluCluRelationInfo();
	            relationToDelete.setId( currentformatIds.get(format.getId()) );
	            BaseDTOAssemblyNode<CourseInfo, CluCluRelationInfo> relationToDeleteNode = new BaseDTOAssemblyNode<CourseInfo, CluCluRelationInfo>(
	                    null);
	            relationToDeleteNode.setNodeData(relationToDelete);
	            relationToDeleteNode.setOperation(NodeOperation.DELETE);
	            results.add(relationToDeleteNode);
			
                BaseDTOAssemblyNode<FormatInfo, CluInfo> formatNode = formatAssembler
                .disassemble(format, NodeOperation.DELETE);
                results.add(formatNode);	            	            

                // remove this entry from the map so we can tell what needs to
                // be deleted at the end
                currentformatIds.remove(format.getId());
			}			    
		}
		
        // Now any leftover format ids are no longer needed, so delete
        // formats and relations. These formats have to be assembled first before they can be marked for deletion
        for (Entry<String, String> entry : currentformatIds.entrySet()) {
            // Create a new relation with the id of the relation we want to
            // delete
            CluCluRelationInfo relationToDelete = new CluCluRelationInfo();
            relationToDelete.setId( entry.getValue() );
            BaseDTOAssemblyNode<CourseInfo, CluCluRelationInfo> relationToDeleteNode = new BaseDTOAssemblyNode<CourseInfo, CluCluRelationInfo>(
                    null);
            relationToDeleteNode.setNodeData(relationToDelete);
            relationToDeleteNode.setOperation(NodeOperation.DELETE);
            results.add(relationToDeleteNode);
            
            CluInfo formatCluToDelete = luService.getClu(entry.getKey());
            FormatInfo formatToDelete = formatAssembler.assemble(formatCluToDelete, null, false);
            BaseDTOAssemblyNode<FormatInfo, CluInfo> formatNode = formatAssembler
            .disassemble(formatToDelete, NodeOperation.DELETE);
            results.add(formatNode);                                            
        }

		return results;
	}

	// TODO This is pretty much a copy of the disassembleJoints
	// code... maybe can be made generic
	private List<BaseDTOAssemblyNode<?, ?>> disassembleJoints(String nodeId,
			CourseInfo course, NodeOperation operation)
			throws AssemblyException {

		List<BaseDTOAssemblyNode<?, ?>> results = new ArrayList<BaseDTOAssemblyNode<?, ?>>();

		// Get the current joints and put them in a map of joint id/relation
		// id
		Map<String, String> currentJointIds = new HashMap<String, String>();

		if (!NodeOperation.CREATE.equals(operation)) {
			try {
				List<CluCluRelationInfo> jointRelationships = luService
						.getCluCluRelationsByClu(course.getId());
				for (CluCluRelationInfo jointRelation : jointRelationships) {
					if (CourseAssemblerConstants.JOINT_RELATION_TYPE
							.equals(jointRelation.getType())) {
						currentJointIds.put(jointRelation.getRelatedCluId(),
								jointRelation.getId());
					}
				}
			} catch (DoesNotExistException e) {
			} catch (InvalidParameterException e) {
				throw new AssemblyException("Error getting related formats", e);
			} catch (MissingParameterException e) {
				throw new AssemblyException("Error getting related formats", e);
			} catch (OperationFailedException e) {
				throw new AssemblyException("Error getting related formats", e);
			}
		}

		// Loop through all the joints in this course
		for (CourseJointInfo joint : course.getJoints()) {

			// If this is a course create then all joints will be created
			if (NodeOperation.UPDATE.equals(operation)
					&& currentJointIds.containsKey(joint.getRelationId())) {
				// remove this entry from the map so we can tell what needs to
				// be deleted at the end
				currentJointIds.remove(joint.getRelationId());
			} else if (!NodeOperation.DELETE.equals(operation)) {
				// the joint does not exist, so create cluclurelation
				BaseDTOAssemblyNode<CourseJointInfo, CluCluRelationInfo> jointNode = courseJointAssembler
						.disassemble(joint, NodeOperation.CREATE);
				jointNode.getNodeData().setCluId(nodeId);
				results.add(jointNode);
			}
		}

        // Now any leftover joint ids are no longer needed, so delete
        // joint relations
        for (Entry<String, String> entry : currentJointIds.entrySet()) {
            // Create a new relation with the id of the relation we want to
            // delete
            CluCluRelationInfo relationToDelete = new CluCluRelationInfo();
            relationToDelete.setId(entry.getValue());
            BaseDTOAssemblyNode<CourseJointInfo, CluCluRelationInfo> relationToDeleteNode = new BaseDTOAssemblyNode<CourseJointInfo, CluCluRelationInfo>(
                    courseJointAssembler);
            relationToDeleteNode.setNodeData(relationToDelete);
            relationToDeleteNode.setOperation(NodeOperation.DELETE);
            results.add(relationToDeleteNode);
        }
		
		return results;
	}

	public LuService getLuService() {
		return luService;
	}

	public void setLuService(LuService luService) {
		this.luService = luService;
	}

	public void setFormatAssembler(FormatAssembler formatAssembler) {
		this.formatAssembler = formatAssembler;
	}

	public FormatAssembler getFormatAssembler() {
		return formatAssembler;
	}

	public CourseJointAssembler getCourseJointAssembler() {
		return courseJointAssembler;
	}

	public void setCourseJointAssembler(
			CourseJointAssembler courseJointAssembler) {
		this.courseJointAssembler = courseJointAssembler;
	}
}
