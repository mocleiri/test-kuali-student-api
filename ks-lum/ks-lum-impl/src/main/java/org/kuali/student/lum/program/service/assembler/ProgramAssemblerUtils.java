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

package org.kuali.student.lum.program.service.assembler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.kuali.student.common.util.UUIDHelper;
import org.kuali.student.core.assembly.BaseDTOAssemblyNode;
import org.kuali.student.core.assembly.BaseDTOAssemblyNode.NodeOperation;
import org.kuali.student.core.assembly.data.AssemblyException;
import org.kuali.student.core.dto.MetaInfo;
import org.kuali.student.core.dto.RichTextInfo;
import org.kuali.student.core.exceptions.DoesNotExistException;
import org.kuali.student.core.exceptions.InvalidParameterException;
import org.kuali.student.core.exceptions.MissingParameterException;
import org.kuali.student.core.exceptions.OperationFailedException;
import org.kuali.student.lum.lu.dto.AdminOrgInfo;
import org.kuali.student.lum.lu.dto.CluCluRelationInfo;
import org.kuali.student.lum.lu.dto.CluIdentifierInfo;
import org.kuali.student.lum.lu.dto.CluInfo;
import org.kuali.student.lum.lu.dto.CluPublicationInfo;
import org.kuali.student.lum.lu.dto.CluResultInfo;
import org.kuali.student.lum.lu.dto.LuCodeInfo;
import org.kuali.student.lum.lu.service.LuService;
import org.kuali.student.lum.program.dto.MajorDisciplineInfo;
import org.kuali.student.lum.service.assembler.CluAssemblerUtils;

public class ProgramAssemblerUtils {

    private LuService luService;
    private CluAssemblerUtils cluAssemblerUtils;

     /**
     * Copy basic values from clu to program
     *
     * @param clu
     * @param o
     * @return
     * @throws AssemblyException
     */
    public Object assembleBasics(CluInfo clu, Object o) throws AssemblyException {

        Method method;
        Class[] parms;
        Object[] value;

		try 	{
            if (clu.getType() != null) {
                parms = new Class[]{String.class};
                try {
                    method = o.getClass().getMethod("setType", parms);
                    if (null != method) {
                        value = new Object[]{clu.getType()};
                        method.invoke(o, value);
                    }
                } catch (NoSuchMethodException nsme) {
                    // CredentialProgramInfo has "credentialprogramType" rather than "type"
                }
            }

            if (clu.getState() != null) {
                parms = new Class[]{String.class};
                method  = o.getClass().getMethod("setState", parms);
                value = new Object[]{clu.getState()};
                method.invoke(o, value);
            }

            if (clu.getMetaInfo() != null) {
                parms = new Class[]{MetaInfo.class};
                method  = o.getClass().getMethod("setMetaInfo", parms);
                value = new Object[]{clu.getMetaInfo()};
                method.invoke(o, value);
            }

            if (clu.getAttributes() != null) {
                parms = new Class[]{Map.class};
                method  = o.getClass().getMethod("setAttributes", parms);
                value = new Object[]{clu.getAttributes()};
                method.invoke(o, value);
            }

            if (clu.getId() != null) {
                parms = new Class[]{String.class};
                method  = o.getClass().getMethod("setId", parms);
                value = new Object[]{clu.getId()};
                method.invoke(o, value);
            }
		}
		catch (IllegalAccessException   e){
            throw new AssemblyException("Error assembling program basics", e);
		}
        catch (InvocationTargetException e){
            throw new AssemblyException("Error assembling program basics", e);
		}
		catch (NoSuchMethodException e)
		{
            throw new AssemblyException("Error assembling program basics", e);
		}

        return o;

    }

    /**
     * Copy basic values from program to clu
     *
     * @param clu
     * @param o
     * @param operation
     * @return
     * @throws AssemblyException
     */
    public CluInfo disassembleBasics(CluInfo clu, Object o, NodeOperation operation) throws AssemblyException {

         Method method;
         Class[] parms;
         Object[] value;

         try 	{

             try {
                 method = o.getClass().getMethod("getType", null);
                 String type = (String)method.invoke(o, null);
                 clu.setType(type);

             } catch (NoSuchMethodException nsme) {
                 // CredentialProgramInfo has "credentialprogramType" rather than "type"
             }

             method = o.getClass().getMethod("getId", null);
             String id = (String)method.invoke(o, null);
             clu.setId(UUIDHelper.genStringUUID(id));

             method = o.getClass().getMethod("getState", null);
             String state = (String)method.invoke(o, null);
             clu.setState(state);

             method = o.getClass().getMethod("getMetaInfo", null);
             MetaInfo meta = (MetaInfo)method.invoke(o, null);
             clu.setMetaInfo(meta);

             method = o.getClass().getMethod("getAttributes", null);
             Map attr = (Map)method.invoke(o, null);
             clu.setAttributes(attr);

         }
         catch (IllegalAccessException   e){               }
         catch (InvocationTargetException e){             }
         catch (NoSuchMethodException e)            {
             throw new AssemblyException("Error disassembling program basics", e);
         }

         return clu;

     }


    //TODO assembleRequirements .  Or maybe this should be in CluAssemblerUtils??
    public Object assembleRequirements(CluInfo clu, Object o) throws AssemblyException {
   	
		try {
			List<String> requirements = luService.getRelatedCluIdsByCluId(clu.getId(), ProgramAssemblerConstants.HAS_PROGRAM_REQUIREMENT);
			if (requirements != null && requirements.size() > 0) {
			    Class[] parms = new Class[]{List.class};
			    Method method = o.getClass().getMethod("setProgramRequirements", parms);
			    method.invoke(o, requirements);
			}
	    }
	    catch (IllegalAccessException   e){
	        throw new AssemblyException("Error assembling program requirements", e);
	    }
	    catch (InvocationTargetException e){
	        throw new AssemblyException("Error assembling program requirements", e);
	    }
	    catch (NoSuchMethodException e)
	    {
	        throw new AssemblyException("Error assembling program requirements", e);
	    }
	    catch (Exception e)
	    {
	        throw new AssemblyException("Error assembling program requirements", e);
	    }

        return o;

    }

    //TODO  maybe this should be in CluAssemblerUtils??
    public CluInfo disassembleRequirements(CluInfo clu, Object o, NodeOperation operation, BaseDTOAssemblyNode<?, ?> result) throws AssemblyException {
        try {
            Method method = o.getClass().getMethod("getProgramRequirements", null);
            List<String> requirements = (List<String>)method.invoke(o, null);

            if (requirements != null && !requirements.isEmpty()) {
               	Map<String, String> currentRelations = null;

                if (!NodeOperation.CREATE.equals(operation)) {
                	currentRelations = getCluCluRelations(clu.getId(), ProgramAssemblerConstants.HAS_PROGRAM_REQUIREMENT);
                }
                
    	    	for (String requirementId : requirements){
    	    		List<BaseDTOAssemblyNode<?, ?>> reqResults = addAllRelationNodes(clu.getId(), requirementId, ProgramAssemblerConstants.HAS_PROGRAM_REQUIREMENT, operation, currentRelations);
    	            if (reqResults != null && reqResults.size()> 0) {
    	                result.getChildNodes().addAll(reqResults);
    	            }
    	    	}
            }
        } catch (NoSuchMethodException e) {
        	throw new AssemblyException("Error while disassembling program requirements", e); 
        } catch (InvocationTargetException e) {
        	throw new AssemblyException("Error while disassembling program requirements", e); 
        } catch (IllegalAccessException e) {
            throw new AssemblyException("Error while disassembling program requirements", e); 
	    } catch (Exception e) {
	        throw new AssemblyException("Error while disassembling program requirements", e);
	    }
	    
        return clu;

    }


    /**
     * Copy identifier values from clu to program
     *
     * @param clu
     * @param o
     * @return
     * @throws AssemblyException
     */
    public Object assembleIdentifiers(CluInfo clu, Object o) throws AssemblyException {

        try    {
            Method method;
            Class[] parms;
            Object[] value;
            if (clu.getOfficialIdentifier() != null) {
                if (clu.getOfficialIdentifier().getShortName() != null) {
                    parms = new Class[]{String.class};
                    method = o.getClass().getMethod("setShortTitle", parms);
                    value = new Object[]{clu.getOfficialIdentifier().getShortName()};
                    method.invoke(o, value);
                }
                if (clu.getOfficialIdentifier().getLongName() != null) {
                    parms = new Class[]{String.class};
                    method = o.getClass().getMethod("setLongTitle", parms);
                    value = new Object[]{clu.getOfficialIdentifier().getLongName()};
                    method.invoke(o, value);
                }
                if (clu.getOfficialIdentifier().getCode() != null) {
                    parms = new Class[]{String.class};
                    method = o.getClass().getMethod("setCode", parms);
                    value = new Object[]{clu.getOfficialIdentifier().getCode()};
                    method.invoke(o, value);
                }
            }
            if (clu.getAlternateIdentifiers() != null) {
                for (CluIdentifierInfo cluIdInfo : clu.getAlternateIdentifiers()) {
                    String idInfoType = cluIdInfo.getType();
                    if (ProgramAssemblerConstants.TRANSCRIPT.equals(idInfoType)) {
                        parms = new Class[]{String.class};
                        method = o.getClass().getMethod("setTranscriptTitle", parms);
                        value = new Object[]{cluIdInfo.getShortName()};
                        method.invoke(o, value);
                    } else if (ProgramAssemblerConstants.DIPLOMA.equals(idInfoType)) {
                        parms = new Class[]{String.class};
                        method = o.getClass().getMethod("setDiplomaTitle", parms);
                        value = new Object[]{cluIdInfo.getShortName()};
                        method.invoke(o, value);
                    }
                }
            }
        }
        catch (IllegalAccessException   e){
            throw new AssemblyException("Error assembling program titles", e);
        }
        catch (InvocationTargetException e){
            throw new AssemblyException("Error assembling program titles", e);
        }
        catch (NoSuchMethodException e)
        {
            throw new AssemblyException("Error assembling program titles", e);
        }

        return o;
    }


    /**
     * Copy identifier values from program to clu
     *
     * @param clu
     * @param o
     * @param operation
     * @return
     * @throws AssemblyException
     */
    public CluInfo disassembleIdentifiers(CluInfo clu, Object o, NodeOperation operation) throws AssemblyException {

        Method method;
        String value;

        try {
            CluIdentifierInfo official = null != clu.getOfficialIdentifier() ? clu.getOfficialIdentifier() : new CluIdentifierInfo();

            method = o.getClass().getMethod("getCode", null);
            String code = (String)method.invoke(o, null);
            official.setCode(code);
            method = o.getClass().getMethod("getLongTitle", null);
            String longTitle = (String)method.invoke(o, null);
            official.setLongName(longTitle);
            method = o.getClass().getMethod("getShortTitle", null);
            String shortTitle = (String)method.invoke(o, null);
            official.setShortName(shortTitle);
            method = o.getClass().getMethod("getState", null);
            String existingState = (String) method.invoke(o, null);
            official.setState((null != existingState && existingState.length() > 0) ? existingState : ProgramAssemblerConstants.ACTIVE);
            // gotta be this type
            official.setType(ProgramAssemblerConstants.OFFICIAL);

            try {
                method = o.getClass().getMethod("getProgramLevel", null);
                String level = (String)method.invoke(o, null);
                official.setLevel(level);
            }
            catch (NoSuchMethodException e)        {
                //ignore - only CredentialProgram has programLevel
            }

            clu.setOfficialIdentifier(official);

            //Remove any existing diploma or transcript alt identifiers
            CluIdentifierInfo diplomaInfo = null;
            CluIdentifierInfo transcriptInfo = null;
            for(Iterator<CluIdentifierInfo> iter = clu.getAlternateIdentifiers().iterator();iter.hasNext();){
                CluIdentifierInfo cluIdentifier = iter.next();
                if (ProgramAssemblerConstants.DIPLOMA.equals(cluIdentifier.getType())) {
                   diplomaInfo = cluIdentifier;
                } else if (ProgramAssemblerConstants.TRANSCRIPT.equals(cluIdentifier.getType())) {
                    transcriptInfo = cluIdentifier;
                }
            }

            try {
                method = o.getClass().getMethod("getDiplomaTitle", null);
                value = (String)method.invoke(o, null);
                if (value != null) {
                    if (diplomaInfo == null) {
                        diplomaInfo = new CluIdentifierInfo();
                        diplomaInfo.setState(ProgramAssemblerConstants.ACTIVE);
                        clu.getAlternateIdentifiers().add(diplomaInfo);
	                }
                    diplomaInfo.setCode(official.getCode());
                    diplomaInfo.setShortName(value);
                    diplomaInfo.setType(ProgramAssemblerConstants.DIPLOMA);
                }
            }
            catch (NoSuchMethodException e)        {
                //ignore - only Major and Variation have diploma title and transcript title
            }

            try{
                method = o.getClass().getMethod("getTranscriptTitle", null);
                value = (String)method.invoke(o, null);
                if (value != null) {
                    if (transcriptInfo == null) {
                        transcriptInfo = new CluIdentifierInfo();
                        transcriptInfo.setState(ProgramAssemblerConstants.ACTIVE);
                        clu.getAlternateIdentifiers().add(transcriptInfo);
                    }
                    transcriptInfo.setCode(official.getCode());
                    transcriptInfo.setShortName(value);
                    transcriptInfo.setType(ProgramAssemblerConstants.TRANSCRIPT);
                }

            }
            catch (NoSuchMethodException e)        {
                //ignore - only Major and Variation have diploma title and transcript title
            }

        }
        catch (IllegalAccessException   e){
            throw new AssemblyException("Error disassembling program basics", e);
        }
        catch (InvocationTargetException e){
            throw new AssemblyException("Error disassembling program basics", e);
        }
        catch (NoSuchMethodException e)  {
            throw new AssemblyException("Error disassembling program basics", e);
        }

        return clu;
    }

    /**
     * Copy Lu Codes from clu to program
     *
     * @param clu
     * @param o
     * @return
     * @throws AssemblyException
     */
    public Object assembleLuCodes(CluInfo clu, Object o) throws AssemblyException {
        try {
            if (clu.getLuCodes() != null) {
                for (LuCodeInfo codeInfo : clu.getLuCodes()) {
                    if (ProgramAssemblerConstants.CIP_2000.equals(codeInfo.getType())) {
                        buildLuCodeFromClu(o, codeInfo.getValue(), "setCip2000Code");
                    } else if (ProgramAssemblerConstants.CIP_2010.equals(codeInfo.getType())) {
                        buildLuCodeFromClu(o, codeInfo.getValue(), "setCip2010Code");
                    } else if (ProgramAssemblerConstants.HEGIS.equals(codeInfo.getType())) {
                        buildLuCodeFromClu(o, codeInfo.getValue(), "setHegisCode");
                    } else if (ProgramAssemblerConstants.UNIVERSITY_CLASSIFICATION.equals(codeInfo.getType())) {
                        buildLuCodeFromClu(o, codeInfo.getValue(), "setUniversityClassification");
                    } else if (ProgramAssemblerConstants.SELECTIVE_ENROLLMENT.equals(codeInfo.getType())) {
                        buildLuCodeFromClu(o, codeInfo.getValue(), "setSelectiveEnrollmentCode");
                    }
                 }
            }
        }
        catch (IllegalAccessException   e){
            throw new AssemblyException("Error assembling program titles", e);
        }
        catch (InvocationTargetException e){
            throw new AssemblyException("Error assembling program titles", e);
        }
        catch (NoSuchMethodException e)
        {
            throw new AssemblyException("Error assembling program titles", e);
        }
        return o;
    }


    /**
     * Copy Lu Codes from program to clu
     *
     * @param clu
     * @param o
     * @param operation
     * @throws AssemblyException
     */
    public CluInfo disassembleLuCodes(CluInfo clu, Object o, NodeOperation operation) throws AssemblyException {

        clu.setLuCodes(new ArrayList<LuCodeInfo>());

        addLuCode(clu, o, "getCip2000Code", ProgramAssemblerConstants.CIP_2000);
        addLuCode(clu, o, "getCip2010Code", ProgramAssemblerConstants.CIP_2010);
        addLuCode(clu, o, "getHegisCode", ProgramAssemblerConstants.HEGIS);
        addLuCode(clu, o, "getUniversityClassification", ProgramAssemblerConstants.UNIVERSITY_CLASSIFICATION);
        addLuCode(clu, o, "getSelectiveEnrollmentCode", ProgramAssemblerConstants.SELECTIVE_ENROLLMENT);

        return clu;

    }

    /**
     * Copy AdminOrg id's from clu's AdminOrgInfo's to program
     *
     * @param clu
     * @param o
     * @return
     * @throws AssemblyException
     */
    public Object assembleAdminOrgIds(CluInfo clu, Object o) throws AssemblyException {


        try {

            if (clu.getAdminOrgs() != null) {
                clearProgramAdminOrgs(o);
                for (AdminOrgInfo cluOrg : clu.getAdminOrgs()) {
                    if (cluOrg.getType().equals(ProgramAssemblerConstants.CONTENT_OWNER_DIVISION)) {
                        addOrgIdToProgram(o, cluOrg, "getDivisionsContentOwner", "setDivisionsContentOwner");
                    }
                    else if (cluOrg.getType().equals(ProgramAssemblerConstants.STUDENT_OVERSIGHT_DIVISION)) {
                        addOrgIdToProgram(o, cluOrg, "getDivisionsStudentOversight", "setDivisionsStudentOversight");
                    }
                    else if (cluOrg.getType().equals(ProgramAssemblerConstants.DEPLOYMENT_DIVISION)) {
                        addOrgIdToProgram(o, cluOrg, "getDivisionsDeployment", "setDivisionsDeployment");
                    }
                    else if (cluOrg.getType().equals(ProgramAssemblerConstants.FINANCIAL_RESOURCES_DIVISION)) {
                        addOrgIdToProgram(o, cluOrg, "getDivisionsFinancialResources", "setDivisionsFinancialResources");
                    }
                    else if (cluOrg.getType().equals(ProgramAssemblerConstants.FINANCIAL_CONTROL_DIVISION)) {
                        addOrgIdToProgram(o, cluOrg, "getDivisionsFinancialControl", "setDivisionsFinancialControl");
                    }
                    else if (cluOrg.getType().equals(ProgramAssemblerConstants.CONTENT_OWNER_UNIT)) {
                        addOrgIdToProgram(o, cluOrg, "getUnitsContentOwner", "setUnitsContentOwner");
                    }
                    else if (cluOrg.getType().equals(ProgramAssemblerConstants.STUDENT_OVERSIGHT_UNIT)) {
                        addOrgIdToProgram(o, cluOrg, "getUnitsStudentOversight", "setUnitsStudentOversight");
                    }
                    else if (cluOrg.getType().equals(ProgramAssemblerConstants.DEPLOYMENT_UNIT)) {
                        addOrgIdToProgram(o, cluOrg, "getUnitsDeployment", "setUnitsDeployment");
                    }
                    else if (cluOrg.getType().equals(ProgramAssemblerConstants.FINANCIAL_RESOURCES_UNIT)) {
                        addOrgIdToProgram(o, cluOrg, "getUnitsFinancialResources", "setUnitsFinancialResources");
                    }
                    else if (cluOrg.getType().equals(ProgramAssemblerConstants.FINANCIAL_CONTROL_UNIT)) {
                        addOrgIdToProgram(o, cluOrg, "getUnitsFinancialControl", "setUnitsFinancialControl");
                    }
                }
            }
        }
        catch (IllegalAccessException   e){
            throw new AssemblyException("Error assembling program orgs", e);
        }
        catch (InvocationTargetException e){
            throw new AssemblyException("Error assembling program orgs", e);
        }
        catch (NoSuchMethodException e)
        {
            throw new AssemblyException("Error assembling program orgs", e);
        }

        return o;
    }

    private void clearProgramAdminOrgs(Object o) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        clearAdminOrgs(o, "setDivisionsContentOwner");
        clearAdminOrgs(o, "setDivisionsStudentOversight");
        clearAdminOrgs(o, "setDivisionsDeployment");
        clearAdminOrgs(o, "setDivisionsFinancialResources");
        clearAdminOrgs(o, "setDivisionsFinancialControl");
        clearAdminOrgs(o, "setUnitsContentOwner");
        clearAdminOrgs(o, "setUnitsStudentOversight");
        clearAdminOrgs(o, "setUnitsDeployment");
        clearAdminOrgs(o, "setUnitsFinancialResources");
        clearAdminOrgs(o, "setUnitsFinancialControl");
    }

    private void clearAdminOrgs(Object o, String setMethodName) throws InvocationTargetException, IllegalAccessException {
        Class[] parms =  new Class[]{List.class};
        Method  method = null;
        try {
            method = o.getClass().getMethod(setMethodName, parms);
            List<String> objOrgs = new ArrayList<String>();

            Object[] value = new Object[]{objOrgs};
            method.invoke(o, value);
        } catch (NoSuchMethodException ignore) {
            // Not all programs have all org types.
            //  This will be fixed by JIRA KSLUM-414 later
        }

    }


    /**
     * Copy AdminOrg values from program to clu
     *
     * @param clu
     * @param o
     * @param operation
     */
    public CluInfo disassembleAdminOrgs(CluInfo clu, Object o, NodeOperation operation){

        // clear out all old admin orgs
        clu.setAdminOrgs(new ArrayList<AdminOrgInfo>());

        addAdminOrgs(clu, o, "getDivisionsContentOwner", ProgramAssemblerConstants.CONTENT_OWNER_DIVISION);
        addAdminOrgs(clu, o, "getDivisionsStudentOversight", ProgramAssemblerConstants.STUDENT_OVERSIGHT_DIVISION);
        addAdminOrgs(clu, o, "getDivisionsDeployment", ProgramAssemblerConstants.DEPLOYMENT_DIVISION);
        addAdminOrgs(clu, o, "getDivisionsFinancialResources", ProgramAssemblerConstants.FINANCIAL_RESOURCES_DIVISION);
        addAdminOrgs(clu, o, "getDivisionsFinancialControl", ProgramAssemblerConstants.FINANCIAL_CONTROL_DIVISION);
        addAdminOrgs(clu, o, "getUnitsContentOwner", ProgramAssemblerConstants.CONTENT_OWNER_UNIT);
        addAdminOrgs(clu, o, "getUnitsStudentOversight", ProgramAssemblerConstants.STUDENT_OVERSIGHT_UNIT);
        addAdminOrgs(clu, o, "getUnitsDeployment", ProgramAssemblerConstants.DEPLOYMENT_UNIT);
        addAdminOrgs(clu, o, "getUnitsFinancialResources", ProgramAssemblerConstants.FINANCIAL_RESOURCES_UNIT);
        addAdminOrgs(clu, o, "getUnitsFinancialControl", ProgramAssemblerConstants.FINANCIAL_CONTROL_UNIT);
        addAdminOrg(clu, o, "getInstitution", ProgramAssemblerConstants.INSTITUTION);

        return clu;

    }

    private void addAdminOrgs(CluInfo clu, Object o, String methodName, String adminOrgType) {
        List<AdminOrgInfo> orgs = getAdminOrgsFromProgram(o, methodName, adminOrgType);
        if(orgs != null)
            clu.getAdminOrgs().addAll(orgs);
    }

    private void addAdminOrg(CluInfo clu, Object o, String methodName, String adminOrgType){
        AdminOrgInfo org = null;
		try	{
			Method method = o.getClass().getMethod(methodName, null);
			org = (AdminOrgInfo) method.invoke(o, null);
            if (org != null) {
        		org.setType(adminOrgType);
            	clu.getAdminOrgs().add(org);
            }
        }
		catch (NoSuchMethodException ex) {}
        catch (IllegalArgumentException e) {}
        catch (IllegalAccessException e) {}
        catch (InvocationTargetException e) {}
    }
    /**
     * Copy result option values from clu to program
     *
     * @param cluId
     * @param resultType
     * @return
     * @throws AssemblyException
     */
    public List<String> assembleResultOptions(String cluId) throws AssemblyException {
        List<String> resultOptions = null;
        try{
            List<CluResultInfo> cluResults = luService.getCluResultByClu(cluId);

            List<String> resultTypes = new ArrayList<String>();
            resultTypes.add(ProgramAssemblerConstants.DEGREE_RESULTS);
            resultTypes.add(ProgramAssemblerConstants.CERTIFICATE_RESULTS);

            resultOptions = cluAssemblerUtils.assembleCluResults(resultTypes, cluResults);

        } catch (DoesNotExistException e){
        } catch (Exception e) {
            throw new AssemblyException("Error getting major results", e);
        }
        return resultOptions;
    }

    /**
     * Copy atp values  from clu to program
     *
     * @param clu
     * @param o
     * @return
     * @throws AssemblyException
     */
    public Object assembleAtps(CluInfo clu, Object o) throws AssemblyException {

        Method method;
        Class[] parms;
        Object[] value;

        try {
            if (clu.getExpectedFirstAtp() != null) {
                parms = new Class[]{String.class};
                method = o.getClass().getMethod("setStartTerm", parms);
                value = new Object[]{clu.getExpectedFirstAtp()};
                method.invoke(o, value);
            }
            if (clu.getLastAtp() != null) {
                parms = new Class[]{String.class};
                method = o.getClass().getMethod("setEndTerm", parms);
                value = new Object[]{clu.getLastAtp()};
                method.invoke(o, value);
            }
            if (clu.getLastAdmitAtp() != null) {
                parms = new Class[]{String.class};
                method = o.getClass().getMethod("setEndProgramEntryTerm", parms);
                value = new Object[]{clu.getLastAdmitAtp()};
                method.invoke(o, value);
            }
            if (clu.getNextReviewPeriod() != null) {
                parms = new Class[]{String.class};
                method = o.getClass().getMethod("setNextReviewPeriod", parms);
                value = new Object[]{clu.getNextReviewPeriod()};
                method.invoke(o, value);
            }
        }
        catch (IllegalAccessException   e){
            throw new AssemblyException("Error assembling program dates", e);
        }
        catch (InvocationTargetException e){
            throw new AssemblyException("Error assembling program dates", e);
        }
        catch (NoSuchMethodException e)
        {
            throw new AssemblyException("Error assembling program dates", e);
        }
        return o;
    }


    /**
     * Copy atp values from Program to clu
     *
     * @param clu
     * @param o
     * @return
     * @throws AssemblyException
     */
    public CluInfo disassembleAtps(CluInfo clu, Object o, NodeOperation operation) throws AssemblyException {

        Method method;
        String value;

        try 	{

            method = o.getClass().getMethod("getStartTerm", null);
            value = (String)method.invoke(o, null);
            clu.setExpectedFirstAtp(value);

            method = o.getClass().getMethod("getEndTerm", null);
            value = (String)method.invoke(o, null);
            clu.setLastAtp(value);

            method = o.getClass().getMethod("getEndProgramEntryTerm", null);
            value = (String)method.invoke(o, null);
            clu.setLastAdmitAtp(value);

        }
        catch (IllegalAccessException   e){          }
        catch (InvocationTargetException e){         }
        catch (NoSuchMethodException e)             {
            throw new AssemblyException("Error disassembling program basics", e);
        }


        return clu;

    }

    /**
     * Copy publication values from clu to program
     *
     * @param clu
     * @param o
     * @return
     * @throws AssemblyException
     */
    public Object assemblePublicationInfo(CluInfo clu, Object o) throws AssemblyException {

        Method method;
        Class[] parms;
        Object[] value;

//
        try {
            if (clu.getReferenceURL() != null) {
                parms = new Class[]{String.class};
                method = o.getClass().getMethod("setReferenceURL", parms);
                value = new Object[]{clu.getReferenceURL()};
                method.invoke(o, value);
            }

            //TODO assemble catalogDescr

            RichTextInfo description = assembleCatalogDescr(clu.getId());
//            if (description != null) {
//                parms = new Class[]{RichTextInfo.class};
//                method = o.getClass().getMethod("setCatalogDescr", parms);
//                value = new Object[]{description};
//                method.invoke(o, value);
//            }

            try {
                List<CluPublicationInfo> cluPublications = luService.getCluPublicationsByCluId(clu.getId());

                List<String> targets = new ArrayList<String>();

                for (CluPublicationInfo cluPublication : cluPublications) {
                    targets.add(cluPublication.getType());
                }

                parms =  new Class[]{List.class};
                method = o.getClass().getMethod("setCatalogPublicationTargets", parms);
                value = new Object[]{targets};
                method.invoke(o, value);

            } catch (DoesNotExistException e) {
            } catch (InvalidParameterException e) {
            } catch (MissingParameterException e) {
            } catch (OperationFailedException e) {
                throw new AssemblyException("Error getting publication targets", e);
            }

        }
        catch (IllegalAccessException   e){
            throw new AssemblyException("Error assembling program dates", e);
        }
        catch (InvocationTargetException e){
            throw new AssemblyException("Error assembling program dates", e);
        }
        catch (NoSuchMethodException e)
        {
            throw new AssemblyException("Error assembling program dates", e);
        }


        return o;
    }

    /**
     * Copy publication values from program to clu
     *
     * @param clu
     * @param o
     * @param operation
     * @return
     * @throws AssemblyException
     */
    public CluInfo disassemblePublicationInfo(CluInfo clu, Object o, NodeOperation operation) throws AssemblyException {

        Method method;
        String value;

        try 	{
            method = o.getClass().getMethod("getReferenceURL", null);
            value = (String)method.invoke(o, null);
            clu.setReferenceURL(value);

            //TODO diasassembleCatalogDescr
//            method = o.getClass().getMethod("getCatalogDescr", null);
//            RichTextInfo descr = (RichTextInfo)method.invoke(o, null);
//            clu.setDescr(descr);

//TODO        clu.setPublicationInfo(major.getCatalogPublicationTargets());

        }

        catch (IllegalAccessException   e){
            throw new AssemblyException("Error disassembling program publication info", e);
        }
        catch (InvocationTargetException e){
            throw new AssemblyException("Error disassembling program  publication info", e);
        }
        catch (NoSuchMethodException e)  {
            throw new AssemblyException("Error disassembling program  publication info", e);
        }
        return clu;

    }

    /**
     * Copy credential program id value from program to clu
     *
     * @param clu
     * @param o
     * @param operation
     * @return
     * @throws AssemblyException
     */
    public List<BaseDTOAssemblyNode<?,?>>  disassembleCredentialProgram(Object o, NodeOperation operation, String relationType) throws AssemblyException {

        List<BaseDTOAssemblyNode<?, ?>> results = new ArrayList<BaseDTOAssemblyNode<?, ?>>();

        Method  method = null;
        String programId = null;
        String credentialId = null;
        try {
            method = o.getClass().getMethod("getCredentialProgramId", null);
            credentialId = (String)method.invoke(o, null);

            method = o.getClass().getMethod("getId", null);
            programId = (String)method.invoke(o, null);

        } catch (NoSuchMethodException e) {
        } catch (InvocationTargetException e) {
        } catch (IllegalAccessException e) {
            throw new AssemblyException("Error disassembling program credential", e);
        }

        try {
            CluInfo credentialClu = luService.getClu(credentialId);
        } catch (DoesNotExistException e) {
        } catch (Exception e) {
            throw new AssemblyException("Credential Clu does not exist for " + credentialId);
        }

        Map<String, String> currentRelations = new HashMap<String, String>();

        if (!NodeOperation.CREATE.equals(operation)) {
            try {
                List<CluCluRelationInfo> cluRelations = luService.getCluCluRelationsByClu(programId);
                for (CluCluRelationInfo cluRelation : cluRelations) {
                    if (relationType.equals(cluRelation.getType()) ) {
                        currentRelations.put(cluRelation.getRelatedCluId(), cluRelation.getId());
                    }
                }
            } catch (DoesNotExistException e) {
            } catch (InvalidParameterException e) {
            } catch (MissingParameterException e) {
            } catch (OperationFailedException e) {
                throw new AssemblyException("Error getting related clus", e);
            }
        }


        //  If this is a create then vreate new relation
        if (NodeOperation.CREATE == operation
                || (NodeOperation.UPDATE == operation && !currentRelations.containsKey(credentialId) )) {
            // the relation does not exist, so create
            CluCluRelationInfo relation = new CluCluRelationInfo();
            relation.setCluId(credentialId);
            relation.setRelatedCluId(programId);
            relation.setType(relationType);
            relation.setState(ProgramAssemblerConstants.ACTIVE);

            BaseDTOAssemblyNode<Object, CluCluRelationInfo> relationNode = new BaseDTOAssemblyNode<Object, CluCluRelationInfo>(
                    null);
            relationNode.setNodeData(relation);
            relationNode.setOperation(NodeOperation.CREATE);

            results.add(relationNode);
        } else if (NodeOperation.UPDATE == operation
                && currentRelations.containsKey(credentialId)) {
            // If the relationship already exists update it

            // remove this entry from the map so we can tell what needs to
            // be deleted at the end
            currentRelations.remove(credentialId);
        } else if (NodeOperation.DELETE == operation
                && currentRelations.containsKey(programId))  {
            // Delete the Format and its relation
            CluCluRelationInfo relationToDelete = new CluCluRelationInfo();
            relationToDelete.setId( currentRelations.get(programId) );
            BaseDTOAssemblyNode<Object, CluCluRelationInfo> relationToDeleteNode = new BaseDTOAssemblyNode<Object, CluCluRelationInfo>(
                    null);
            relationToDeleteNode.setNodeData(relationToDelete);
            relationToDeleteNode.setOperation(NodeOperation.DELETE);
            results.add(relationToDeleteNode);

            // remove this entry from the map so we can tell what needs to
            // be deleted at the end
            currentRelations.remove(programId);
        }

        if(currentRelations != null && currentRelations.size() > 0){
	        for (Map.Entry<String, String> entry : currentRelations.entrySet()) {
	            // Create a new relation with the id of the relation we want to
	            // delete
	            CluCluRelationInfo relationToDelete = new CluCluRelationInfo();
	            relationToDelete.setId( entry.getValue() );
	            BaseDTOAssemblyNode<Object, CluCluRelationInfo> relationToDeleteNode = new BaseDTOAssemblyNode<Object, CluCluRelationInfo>(
	                    null);
	            relationToDeleteNode.setNodeData(relationToDelete);
	            relationToDeleteNode.setOperation(NodeOperation.DELETE);
	            results.add(relationToDeleteNode);
	        }
        }
        return results;
    }

    public List<BaseDTOAssemblyNode<?, ?>> addRelationNodes(String cluId, String relatedCluId, String relationType, NodeOperation operation)throws AssemblyException{
    	Map<String, String> currentRelations = null;
    	List<BaseDTOAssemblyNode<?, ?>> results = new ArrayList<BaseDTOAssemblyNode<?, ?>>();

        if (!NodeOperation.CREATE.equals(operation)) {
        	currentRelations = getCluCluRelations(cluId, relationType);
        }

        //  If this is a create then vreate new relation
       /* if (NodeOperation.CREATE == operation
                || (NodeOperation.UPDATE == operation && !currentRelations.containsKey(relatedCluId) )) {
            // the relation does not exist, so create
        	addCreateRelationNode(cluId, relatedCluId, relationType, results);
        } else if (NodeOperation.UPDATE == operation
                && currentRelations.containsKey(relatedCluId)) {
            // If the relationship already exists update it

            // remove this entry from the map so we can tell what needs to
            // be deleted at the end
            currentRelations.remove(relatedCluId);
        } else if (NodeOperation.DELETE == operation
                && currentRelations.containsKey(relatedCluId))  {
            // Delete the Format and its relation
        	addDeleteRelationNodes(currentRelations, results);

            // remove this entry from the map so we can tell what needs to
            // be deleted at the end
            currentRelations.remove(relatedCluId);
        }*/
        if (NodeOperation.UPDATE.equals(operation) && (currentRelations != null && currentRelations.containsKey(relatedCluId))) {
            // remove this entry from the map so we can tell what needs to be deleted at the end
        	currentRelations.remove(relatedCluId);  
        } else if (!NodeOperation.DELETE.equals(operation)) {
        	addCreateRelationNode(cluId, relatedCluId, relationType, results);
		}
        
        if(currentRelations != null && currentRelations.size() > 0){
	        for (Map.Entry<String, String> entry : currentRelations.entrySet()) {
	            // Create a new relation with the id of the relation we want to
	            // delete
	            CluCluRelationInfo relationToDelete = new CluCluRelationInfo();
	            relationToDelete.setId( entry.getValue() );
	            BaseDTOAssemblyNode<Object, CluCluRelationInfo> relationToDeleteNode = new BaseDTOAssemblyNode<Object, CluCluRelationInfo>(
	                    null);
	            relationToDeleteNode.setNodeData(relationToDelete);
	            relationToDeleteNode.setOperation(NodeOperation.DELETE);
	            results.add(relationToDeleteNode);
	        }
        }
        return results;
    }
    public List<BaseDTOAssemblyNode<?, ?>> addAllRelationNodes(String cluId, String relatedCluId, String relationType, NodeOperation operation, Map<String, String> currentRelations)throws AssemblyException{
    	List<BaseDTOAssemblyNode<?, ?>> results = new ArrayList<BaseDTOAssemblyNode<?, ?>>();

        if (NodeOperation.UPDATE.equals(operation) && (currentRelations != null && currentRelations.containsKey(relatedCluId))) {
            // remove this entry from the map so we can tell what needs to be deleted at the end
        	currentRelations.remove(relatedCluId);  
        } else if (!NodeOperation.DELETE.equals(operation)) {
        	addCreateRelationNode(cluId, relatedCluId, relationType, results);
		}
        
        if(currentRelations != null && currentRelations.size() > 0){
	        for (Map.Entry<String, String> entry : currentRelations.entrySet()) {
	            // Create a new relation with the id of the relation we want to
	            // delete
	            CluCluRelationInfo relationToDelete = new CluCluRelationInfo();
	            relationToDelete.setId( entry.getValue() );
	            BaseDTOAssemblyNode<Object, CluCluRelationInfo> relationToDeleteNode = new BaseDTOAssemblyNode<Object, CluCluRelationInfo>(
	                    null);
	            relationToDeleteNode.setNodeData(relationToDelete);
	            relationToDeleteNode.setOperation(NodeOperation.DELETE);
	            results.add(relationToDeleteNode);
	        }
        }
        return results;
    }
    public Map<String, String> getCluCluRelations(String cluId, String relationType) throws AssemblyException{
        Map<String, String> currentRelations = new HashMap<String, String>();

            try {
                List<CluCluRelationInfo> cluRelations = luService.getCluCluRelationsByClu(cluId);

                for (CluCluRelationInfo cluRelation : cluRelations) {
                    if (relationType.equals(cluRelation.getType())) {
                        currentRelations.put(cluRelation.getRelatedCluId(), cluRelation.getId());
                    }
                }
            } catch (DoesNotExistException e) {
            } catch (InvalidParameterException e) {
            } catch (MissingParameterException e) {
            } catch (OperationFailedException e) {
                throw new AssemblyException("Error getting related clus", e);
            }

            return currentRelations;
    }

    public void addCreateRelationNode(String cluId, String relatedCluId, String relationType, List<BaseDTOAssemblyNode<?, ?>> results){
        CluCluRelationInfo relation = new CluCluRelationInfo();
        relation.setCluId(cluId);
        relation.setRelatedCluId(relatedCluId);
        relation.setType(relationType);
        relation.setState(ProgramAssemblerConstants.ACTIVE);

        BaseDTOAssemblyNode<Object, CluCluRelationInfo> relationNode = new BaseDTOAssemblyNode<Object, CluCluRelationInfo>(
                null);
        relationNode.setNodeData(relation);
        relationNode.setOperation(NodeOperation.CREATE);

        results.add(relationNode);

    }

    public void addDeleteRelationNodes(Map<String, String> currentRelations, List<BaseDTOAssemblyNode<?, ?>> results){
        for (Map.Entry<String, String> entry : currentRelations.entrySet()) {
            // Create a new relation with the id of the relation we want to
            // delete
            CluCluRelationInfo relationToDelete = new CluCluRelationInfo();
            relationToDelete.setId( entry.getValue() );
            BaseDTOAssemblyNode<Object, CluCluRelationInfo> relationToDeleteNode = new BaseDTOAssemblyNode<Object, CluCluRelationInfo>(
                    null);
            relationToDeleteNode.setNodeData(relationToDelete);
            relationToDeleteNode.setOperation(NodeOperation.DELETE);
            results.add(relationToDeleteNode);
        }
    }

    private LuCodeInfo buildLuCodeFromProgram(Object o, String methodName, String codeType) throws AssemblyException {

        LuCodeInfo code = null;
        try {
            Method method = o.getClass().getMethod(methodName, null);
            String value = (String)method.invoke(o, null);

            if (value != null && !value.isEmpty()) {
                code = new LuCodeInfo();
                code.setType(codeType);
                code.setValue(value);
                code.setAttributes(new HashMap<String, String>());
            }

        } catch (NoSuchMethodException e) {
            //ignore - this program type doesn't have this method
        } catch (InvocationTargetException e) {
        } catch (IllegalAccessException e) {
            throw new AssemblyException("Error while disassembling program LU codes", e);
        }
        return code;
    }

    private void buildLuCodeFromClu(Object o, String codeValue, String methodName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class[] parms = new Class[]{String.class};
        Method method = o.getClass().getMethod(methodName, parms);
        Object[] value= new Object[]{codeValue};
        method.invoke(o, value);
    }

    private void addOrgIdToProgram(Object o, AdminOrgInfo cluOrg, String getMethod, String setMethod) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        Method method = o.getClass().getMethod(getMethod, null);
        List<String> objOrgs = (List<String>) method.invoke(o, null);

        if (objOrgs == null)     {
            objOrgs = new ArrayList<String>();
        }
        objOrgs.add(cluOrg.getOrgId());
        Class[] parms =  new Class[]{List.class};
        method = o.getClass().getMethod(setMethod, parms);
        Object[] value = new Object[]{objOrgs};
        method.invoke(o, value);
    }

    //TODO assembleCatalogDescr
    private RichTextInfo assembleCatalogDescr(String cluId) throws AssemblyException {
//        RichTextInfo returnInfo = new RichTextInfo();
//        try {
//            List<CluPublicationInfo> pubs = luService.getCluPublicationsByCluId(cluId);
//            for (CluPublicationInfo pubInfo : pubs) {
//                for (FieldInfo fieldInfo : pubInfo.getVariants()) {
//                    if (fieldInfo.getId().equals(ProgramAssemblerConstants.CLU_INFO + "." + ProgramAssemblerConstants.DESCR)) {
//                        returnInfo.setPlain(fieldInfo.getValue());
//                        return returnInfo; // or break to a label to avoid multiple return points
//                    }
//                }
//            }
//        } catch (Exception e) {
//            throw new AssemblyException(e);
//        }
//        return returnInfo;
        return null;
    }

    //TODO disassembleCatalogDescr
     private CluInfo disassembleCatalogDescr(String cluId) throws AssemblyException {
//        RichTextInfo returnInfo = new RichTextInfo();
//        try {
//            List<CluPublicationInfo> pubs = luService.getCluPublicationsByCluId(cluId);
//            for (CluPublicationInfo pubInfo : pubs) {
//                for (FieldInfo fieldInfo : pubInfo.getVariants()) {
//                    if (fieldInfo.getId().equals(ProgramAssemblerConstants.CLU_INFO + "." + ProgramAssemblerConstants.DESCR)) {
//                        returnInfo.setPlain(fieldInfo.getValue());
//                        return returnInfo; // or break to a label to avoid multiple return points
//                    }
//                }
//            }
//        } catch (Exception e) {
//            throw new AssemblyException(e);
//        }
//        return returnInfo;
        return null;
    }

    private List<AdminOrgInfo> getAdminOrgsFromProgram(Object t, String methodName, String adminOrgType) {
        List<AdminOrgInfo> result = new ArrayList<AdminOrgInfo>();
		try	{
			Class<?> clazz = t.getClass();
			Method method = clazz.getMethod(methodName, null);
            List<String> orgIds = (List<String>) method.invoke(t, null);
            if (null != orgIds) {
                for (String orgId : orgIds) {
                    AdminOrgInfo subjectOrg = new AdminOrgInfo();
                    subjectOrg.setType(adminOrgType);
                    subjectOrg.setOrgId(orgId);
                    result.add(subjectOrg);
                }
            }
        }
		catch (IllegalAccessException   ex){
			return null;
		}
		catch (InvocationTargetException  ex){
			return null;
		}
		catch (NoSuchMethodException ex) {
			 return null;
		}

        return result;
    }

     private void addLuCode(CluInfo clu, Object o, String methodName, String codeType ) throws AssemblyException {

        LuCodeInfo code = buildLuCodeFromProgram(o, methodName, codeType );
        if (code != null) {
//            if (currentCodes.containsKey(code.getType())) {
//                clu.getLuCodes().remove(currentCodes.get(code.getType()));
//            }
            clu.getLuCodes().add(code);
        }
    }

    // Spring setters
    public void setLuService(LuService luService) {
        this.luService = luService;
    }

    public void setCluAssemblerUtils(CluAssemblerUtils cluAssemblerUtils) {
        this.cluAssemblerUtils = cluAssemblerUtils;
    }

    public String getCredentialProgramID(String cluId) throws AssemblyException {

        List<String> credentialProgramIDs = null;
        try {
            credentialProgramIDs = luService.getCluIdsByRelation(cluId, ProgramAssemblerConstants.HAS_MAJOR_PROGRAM);
        } catch (Exception e) {
            throw new AssemblyException(e);
        }
        // Can a Program have more than one Credential Program?
        // TODO - do we need to validate that?
        if (null == credentialProgramIDs || credentialProgramIDs.isEmpty()) {
            throw new AssemblyException("Program with ID == " + cluId + " has no Credential Program associated with it.");
        } else if (credentialProgramIDs.size() > 1) {
            throw new AssemblyException("Program with ID == " + cluId + " has more than one Credential Program associated with it.");
        }
        return credentialProgramIDs.get(0);
    }
}
