/**
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

package org.kuali.student.r2.core.organization.service;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.dto.StatusInfo;
import org.kuali.student.r2.common.dto.TypeInfo;
import org.kuali.student.r2.common.dto.ValidationResultInfo;

import org.kuali.student.r2.common.exceptions.AlreadyExistsException;
import org.kuali.student.r2.common.exceptions.DataValidationErrorException;
import org.kuali.student.r2.common.exceptions.DoesNotExistException;
import org.kuali.student.r2.common.exceptions.InvalidParameterException;
import org.kuali.student.r2.common.exceptions.MissingParameterException;
import org.kuali.student.r2.common.exceptions.OperationFailedException;
import org.kuali.student.r2.common.exceptions.PermissionDeniedException;
import org.kuali.student.r2.common.exceptions.ReadOnlyException;
import org.kuali.student.r2.common.exceptions.VersionMismatchException;

import org.kuali.student.r2.core.organization.dto.OrgHierarchyInfo;
import org.kuali.student.r2.core.organization.dto.OrgInfo;
import org.kuali.student.r2.core.organization.dto.OrgOrgRelationInfo;
import org.kuali.student.r2.core.organization.dto.OrgPersonRelationInfo;
import org.kuali.student.r2.core.organization.dto.OrgPositionRestrictionInfo;
import org.kuali.student.r2.core.organization.dto.OrgTreeInfo;

/**
 * <h3><a name="KSDOC-ServiceDescriptions-Description"></a>Description</h3>
 * 
 * <p>The Organization service manages organizational units that have
 * some relationship to the institution and manages the relationships
 * between people and those organization. Internal organizations
 * include officially recognized organizations (such as schools and
 * departments) and unofficial organizations (such as clubs or student
 * groups). Organizations may also be external to the institution,
 * such as companies, other institutions, government,
 * associations.</p>
 * 
 * <h3><a name="KSDOC-ServiceDescriptions-Assumptions"></a>Assumptions</h3>
 * 
 * <p>The design of this service considers the following assumptions:</p>
 * <ul>
 * 	<li>Most organizations have "parent" organization(s) within a
 * 	given context. For example, the School of Arts and Sciences
 * 	exists within the institution, so the School or Arts and
 * 	Sciences is a child of the institution.</li>
 * 	<li>There may be multiple relationships that need to be
 * 	captured for a given organization since the parent
 * 	organization may be different depending on the context. For
 * 	example, a department may report to a particular organization
 * 	for administrative purposes, but report to another
 * 	organization for financial purposes.</li>
 * 	<li>Organization-to-organization relationships can be grouped
 * 	into hierarchies based upon the type of relationship.</li>
 * 	<li>Organizations may place additional constraints on the
 * 	types of relationships a person may have with the
 * 	organization.</li>
 * </ul>
 * 
 * 
 * <h3><a name="KSDOC-ServiceDescriptions-KeyConcepts"></a>Key Concepts</h3>
 * 
 * <ul>
 * 	<li>Organizations are different from authorization groups in
 * 	that organizations deal directly with people while
 * 	authorization groups deal directly with
 * 	principals. Organizations may contain individuals who have no
 * 	way to authenticate themselves (and thus have no unique
 * 	permissions) and authorization groups may have principals
 * 	which are linked to non-human entities (such as batch jobs,
 * 	other services, and so forth).</li>
 * 	<li>Organizations and authorization groups may be related in
 * 	that a member of an organization may have a principal
 * 	associated with an authorization group, but this is not
 * 	required.
 * </ul>
 *
 * @author Kuali Student Team
 */

@WebService(name = "OrganizationService", targetNamespace = "http://student.kuali.org/wsdl/organization")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface OrganizationService {

    //
    // Lookup Methods for Org Hierarchy Id Entity Pattern.
    //

    /** 
     * Retrieves a single OrgHierarchy by OrgHierarchy Id.
     *
     * @param orgHierarchyId the identifier for the OrgHierarchy to be
     *        retrieved
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return the OrgHierarchy requested
     * @throws DoesNotExistException orgHierarchyId not found
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException orgHierarchyId or contextInfo
     *         is missing or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public OrgHierarchyInfo getOrgHierarchy(@WebParam(name = "orgHierarchyId") String orgHierarchyid, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a list of OrgHierarchies from a list of OrgHierarchy
     * ids. The returned list may be in any order and if duplicate ids
     * are supplied, a unique set may or may not be returned.
     * 
     * @param orgHierarchyIds a list of OrgHierarchy ids
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return a list of OrgHierarchies
     * @throws DoesNotExistException an orgHierarchyId in the list not found
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException orgHierarchyIds, a id in
     *         orgHierarchyIds, or contextInfo is missing or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<OrgHierarchyInfo> getOrgHierarchiesByIds(@WebParam(name = "orgHierarchyIds") List<String> orgHierarchyIds, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a list of OrgHierarchy ids by OrgHierarchy Type.
     * 
     * @param orgHierarchyTypeKey an identifier for the OrgHierarchy
     *        type
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return a list of OrgHierarchy ids matching orgHierarchyTypeKey
     *         or an empty list if none found
     * @throws InvalidParameterException contextInfo is invalid
     * @throws MissingParameterException orgHierarchyTypeKey or
     *         contextInfo is missing or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<String> getOrgHierarchyIdsByType(@WebParam(name = "orgHierarchyTypeKey") String orgHierarchyTypeKey, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    //
    // Existing bulk method.
    //

    /** 
     * Retrieves the list of all organization hierarchies known by
     * this service.
     *
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return a list of organization hierarchies
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException contextInfo is missing or
     *         null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<OrgHierarchyInfo> getOrgHierarchies(@WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves the list of types of organizations known by this service.
     *
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return a list of organization types
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException contextInfo is missing or
     *         null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<TypeInfo> getOrgTypes(@WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    //
    // Lookup Methods for Org Id Entity Pattern.
    //

    /** 
     * Retrieves a a single Org by Org Id.
     *
     * @param orgId the identifier for the Org to be retrieved
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return the Org requested
     * @throws DoesNotExistException orgId is not found
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException orgId or contextInfo is
     *         missing or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public OrgInfo getOrg(@WebParam(name = "orgId") String orgId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves a list of Orgs from a list of Org ids. The returned
     * list may be in any order and if duplicate ids are supplied, a
     * unique set may or may not be returned.  identifiers.
     *
     * @param orgIds a list of Org Ids
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return a list of Orgs
     * @throws DoesNotExistException an orgId in the list not found
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException orgIds, an orgId in orgIds,
     *         or contextInfo is missing or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<OrgInfo> getOrgsByIds(@WebParam(name = "orgIds") List<String> orgIds, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a list of Org ids by Org Type.
     * 
     * @param orgTypeKey an identifier for the Org type
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return a list of Org ids matching orgTypeKey or an empty list
     *         if none found
     * @throws InvalidParameterException contextInfo is invalid
     * @throws MissingParameterException orgTypeKey or contextInfo is
     *         missing or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<String> getOrgIdsByType(@WebParam(name = "orgTypeKey") String orgTypeKey, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    //
    // Search methods for Org id Entity Pattern.
    //

    /**
     * Searches for Org ids that meet the given search criteria.
     * 
     * @param criteria the search criteria
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return list of Org identifiers matching the criteria
     * @throws InvalidParameterException criteria or contextInfo is not valid
     * @throws MissingParameterException criteria or contextInfo is
     *         missing or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<String> searchForOrgIds(@WebParam(name = "criteria") QueryByCriteria criteria, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Searches for Orgs that meet the given search criteria.
     * 
     * @param criteria the search criteria
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return list of Orgs matching the criteria
     * @throws InvalidParameterException criteria or contextInfo is not valid
     * @throws MissingParameterException criteria or contextInfo is
     *         missing or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<OrgInfo> searchForOrgs(@WebParam(name = "criteria") QueryByCriteria criteria, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    //
    // CRUD methods for Org Id Entity Pattern.
    //

    /**
     * Validates an Organization. Depending on the value of
     * validationType, this validation could be limited to tests on
     * just the current object and its directly contained sub-objects
     * or expanded to perform all tests related to this Org. If an
     * identifier is present for the Org (and/or one of its contained
     * sub-objects) and a record is found for that identifier, the
     * validation checks if the Org can be shifted to the new
     * values. If a an identifier is not present or a record does not
     * exist, the validation checks if the Org with the given data can
     * be created.
     * 
     * @param validationTypeKey the identifier for the validation Type
     * @param orgTypeKey the identifier for the Org Type to be validated
     * @param orgInfo the identifier for the Org to be validated
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return a list of validation results or an empty list if validation
     *         succeeded
     * @throws DoesNotExistException validationTypeKey or orgTypeKey
     *         is not found
     * @throws InvalidParameterException orgInfo or contextInfo is not valid
     * @throws MissingParameterException validationTypeKey, orgTypeKey
     *         orgInfo, or contextInfo is missing or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<ValidationResultInfo> validateOrg(@WebParam(name = "validationTypeKey") String validationTypeKey, @WebParam(name = "orgTypeKey") String orgTypeKey, @WebParam(name = "orgInfo") OrgInfo orgInfo, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Creates a new Org. The Org Type and Meta
     * information may not be set in the supplied data object.
     * 
     * @param orgId a unique for the new Org
     * @param orgInfo the data with which to create the Org
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return the new Org
     * @throws AlreadyExistsException orgId already exists
     * @throws DataValidationErrorException supplied data is invalid
     * @throws DoesNotExistException orgTypeKey does not exist or is
     *         not supported
     * @throws InvalidParameterException orgInfo or contextInfo is not valid
     * @throws MissingParameterException orgId, orgTypeKey, orgInfo,
     *         or contextInfo is missing or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     * @throws ReadOnlyException an attempt at supplying information
     *         designated as read only
     */
    public OrgInfo createOrg(@WebParam(name = "orgId") String orgId, @WebParam(name = "orgInfo") OrgInfo orgInfo, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws AlreadyExistsException, DataValidationErrorException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Updates an existing Org. The Org id, Type, and
     * Meta information may not be changed.
     * 
     * @param orgId the identifier for the Org to be updated
     * @param orgInfo the new data for the Org
     * @param contextInfo information containing the principalId and locale
     *        information about the caller of service operation
     * @return the updated Org
     * @throws DataValidationErrorException supplied data is invalid
     * @throws DoesNotExistException orgId is not found
     * @throws InvalidParameterException orgInfo or contextInfo is not valid
     * @throws MissingParameterException orgId, orgInfo, or
     *         contextInfo is missing or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     * @throws ReadOnlyException an attempt at supplying information
     *         designated as read only
     * @throws VersionMismatchException an optimistic locking failure
     *         or the action was attempted on an out of date version
     */
    public OrgInfo updateOrg(@WebParam(name = "orgId") String orgId, @WebParam(name = "orgInfo") OrgInfo orgInfo, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DataValidationErrorException, DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException, ReadOnlyException, VersionMismatchException;

    /**
     * Deletes an existing Org.
     * 
     * @param orgId the identifier for the Org to be deleted
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return the status of the operation. This must always be true.
     * @throws DoesNotExistException orgId is not found
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException orgId or contextInfo is
     *         missing or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public StatusInfo deleteOrg(@WebParam(name = "orgId") String orgId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    //
    // Existing setup methods for OrgOrgRelations
    //                                

    /** 
     * Retrieves the list of all types of relationships between
     * organizations known to the service.
     *
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return list of organization to organization relationship types
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException contextInfo is missing or
     *         null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<TypeInfo> getOrgOrgRelationTypes(@WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves the Types of relationships between organizations that
     * are allowed for a particular type of organization.
     *
     * @param orgTypeKey an identifier for an Org Type
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return a list of relationship types between organizations for
     *         the specified organization type
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException orgTypeKey or contextInfo is
     *         missing or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<TypeInfo> getOrgOrgRelationTypesForOrgType(@WebParam(name = "orgTypeKey") String orgTypeKey, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves the Types of relationships between organizations that
     * are allowed for a particular organization hierarchy.
     *
     * @param orgHierarchyId an identifier for an OrgHierarchy
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return a list of relationship types between organizations
     * @throws DoesNotExistException orgHierarchyId is is not found
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException orgHierarchyId or contextInfo
     *         is missing or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<TypeInfo> getOrgOrgRelationTypesForOrgHierarchy(@WebParam(name = "orgHierarchyId") String orgHierarchyId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    //
    // Lookup Methods for Org Hierarchy Symmetric Relationship Pattern.
    //

    /** 
     * Retrieves a a singl OrgOrgRelation by OrgorgRelation Id.
     *
     * @param orgOrgRelationId the identifier for orgorgRelation to be
     *        retrieved
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return the OrgOrgRelation requested
     * @throws DoesNotExistException orgOrgRelationId is not found
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException orgOrgRelationId or contextInfo
     *         is missing or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public OrgOrgRelationInfo getOrgOrgRelation(@WebParam(name = "orgOrgRelationId") String orgOrgRelationId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a list of OrgOrgRelations from a list of
     * OrgOrgRelation Ids.  The returned list may be in any order and
     * if duplicate Ids are supplied, a unique set may or may not be
     * returned.
     * 
     * @param orgOrgRelationIds a list of OrgOrgRelation identifiers
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return a list of OrgOrgRelations
     * @throws DoesNotExistException an orgOrgRelationId in the list not found
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException orgOrgRelationids, an
     *         orgOrgRelationId in the orgOrgRelationIds, or
     *         contextInfo is missing or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<OrgOrgRelationInfo> getOrgOrgRelationsByIds(@WebParam(name = "orgOrgRelationIds") List<String> orgOrgRelationIds, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a list of AtpAtpRelation Ids by AtpAtpRelation Type.
     * 
     * @param atpAtpRelationTypeKey an identifier for an AtpAtpRelation Type
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return a list of AtpAtpRelation identifiers matching
     *         atpAtpRelationTypeKey or an empty list if none found
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException atpRelationTypeKey or
     *         contextInfo is missing or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<String> getAtpAtpRelationIdsByType(@WebParam(name = "atpAtpRelationTypeKey") String atpAtpRelationTypeKey, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves all OrgOrgRelations to the given Org independent of
     * which side of the relationship the Org resides.
     * 
     * @param orgId the identifier for the Org
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return a list of OrgOrgrelations to the given Org or an empty list if
     *         none found
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException orgId or contextInfo is
     *         missing or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<OrgOrgRelationInfo> getOrgOrgRelationsByOrg(@WebParam(name = "orgId") String orgId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves all OrgOrgRelations between the given Orgs.
     * 
     * @param orgId the identifier for the Org
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return a list of OrgOrgrelations between the given Orgs or an empty list
     *         if none found
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException orgId, orgPeerId, or
     *         contextInfo is missing or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<OrgOrgRelationInfo> getOrgOrgRelationsByOrgs(@WebParam(name = "orgId") String orgId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Retrieves a list of OrgOrgRelations of the specified
     * OrgOrgRelationType for an Org. (these parameters are
     * backwards).
     * 
     * @param orgId the identifier for an Org
     * @param orgRelationTypeKey the identifier for an OrgOrgRelationType
     * @param contextInfo information containing the principalId and locale
     *        information about the caller of service operation
     * @return a list of OrgOrgRelations of the specified OrgOrgRelationType for
     *         the given Org or an empty list if none found
     * @throws InvalidParameterException contextInfo is notvalid
     * @throws MissingParameterException orgId, orgOrgRelationTypeKey, or
     *         contextInfo is missing or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<OrgOrgRelationInfo> getOrgOrgRelationsByTypeAndOrg(@WebParam(name = "orgId") String orgId, @WebParam(name = "orgRelationTypeKey") String orgRelationTypeKey, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Tests if a org has a current relationship with a specified organization.
     *
     * @param orgId identifier of the organization
     * @param comparisonOrgId identifier of the organization to be compared to
     * @param orgOrgRelationTypeKey type of relationship between the organizations
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return true if a relationship exists
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException orgId, comparisonOrgId,
     *         orgOrgRelationType, or contextInfo is missing or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    @Deprecated                                                               
    public Boolean hasOrgOrgRelation(@WebParam(name = "orgId") String orgId, @WebParam(name = "comparisonOrgId") String comparisonOrgId, @WebParam(name = "orgOrgRelationTypeKey") String orgOrgRelationTypeKey, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    //
    // Search methods for OrgOrgRelation Symmetrical Relationship Pattern.
    //

    /**
     * Searches for OrgOrgRelations that meet the given search criteria.
     * 
     * @param criteria the search criteria
     * @param contextInfo information containing the principalId and locale
     *        information about the caller of service operation
     * @return a list of OrgOrgRelation identifiers matching the criteria
     * @throws InvalidParameterException criteria or contextInfo is not valid
     * @throws MissingParameterException criteria or contextInfo is missing or
     *         null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<String> searchForOrgOrgRelationIds(@WebParam(name = "criteria") QueryByCriteria criteria, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Searches for OrgOrgRelations that meet the given search
     * criteria.
     * 
     * @param criteria the search criteria
     * @param contextInfo information containing the principalId and locale
     *        information about the caller of service operation
     * @return a list of OrgOrgRelations matching the criteria
     * @throws InvalidParameterException contextInfo is invalid
     * @throws MissingParameterException criteria or contextInfo is missing or
     *         null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<OrgOrgRelationInfo> searchForOrgOrgRelations(@WebParam(name = "criteria") QueryByCriteria criteria, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    //
    // CRUD methods for OrgOrgRelation Symmetrical Relationship Pattern.
    //

    /**
     * Validates an OrgOrgRelation. Depending on the value of
     * validationType, this validation could be limited to tests on
     * just the current OrgOrgRelation and its directly contained
     * sub-objects or expanded to perform all tests related to this
     * OrgOrgRelation. If an identifier is present for the
     * OrgOrgRelation (and/or one of its contained sub-objects) and a
     * record is found for that identifier, the validation checks if
     * the OrgOrgRelation can be shifted to the new values. If an
     * identifier is not present or a record cannot be found for the
     * identifier, the validation checks if the object with the given
     * data can be created.
     * 
     * @param validationTypeKey the identifier for the validation Type
     * @param orgId the identifier for an Org
     * @param orgPeerId a the identifier for the Org peer
     * @param orgOrgRelationTypeKey the identifier for the OrgOrgRelation Type
     * @param orgOrgRelationInfo the OrgOrgRelation to be validated
     * @param contextInfo information containing the principalId and locale
     *        information about the caller of service operation
     * @return a list of validation results or an empty list if validation
     *         succeeded
     * @throws DoesNotExistException validationTypeKey, orgId,
     *         orgPeerId, or orgOrgRelationTypeKey is not found
     * @throws InvalidParameterException orgOrgRelationInfo or
     *         contextInfo is not valid
     * @throws MissingParameterException validationTypeKey, orgId,
     *         orgPeerId, orgOrgRelationTypeKey, orgOrgRelationInfo,
     *         or contextInfo is missing or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException authorization failure
     */
    public List<ValidationResultInfo> validateOrgOrgRelation(@WebParam(name = "validationTypeKey") String validationTypeKey, @WebParam(name = "orgId") String orgId, @WebParam(name = "orgPeerId") String orgPeerId, @WebParam(name = "orgOrgrelationTypeKey") String orgOrgRelationTypeKey, @WebParam(name = "orgOrgRelationInfo") OrgOrgRelationInfo orgOrgRelationInfo, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /**
     * Creates a new OrgOrgRelation. The OrgOrgRelation Id, Type, Org
     * ids, and Meta information may not be set in the supplied data.
     * 
     * @param orgId a peer of the relationship
     * @param orgPeerId a peer of the relationship
     * @param orgOrgRelationInfo the relationship to be created
     * @param contextInfo information containing the principalId and locale
     *        information about the caller of service operation
     * @return the new OrgOrgRelation
     * @throws DataValidationErrorException supplied data is invalid
     * @throws DoesNotExistException orgId, orgPeerId, or
     *         orgOrgRelationTypeKey is not found
     * @throws InvalidParameterException orgOrgRelationInfo or contextInfo is
     *         not valid
     * @throws MissingParameterException orgId, orgPeerId,
     *         orgOrgRelationTypeKey, orgOrgRelationInfo, or
     *         contextInfo is missing or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     * @throws ReadOnlyException an attempt at supplying information
     *         designated as read only
     */
    public OrgOrgRelationInfo createOrgOrgRelation(@WebParam(name = "orgId") String orgId, @WebParam(name = "orgPeerId") String orgPeerId, @WebParam(name = "orgOrgRelationInfo") OrgOrgRelationInfo orgOrgRelationInfo, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, DataValidationErrorException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException, ReadOnlyException;

    /**
     * Updates an Org Milestone Relationship. The OrgOrgRelation Id,
     * Type, Org ids, and Meta information may not be changed.
     * 
     * @param orgOrgRelationId the identifier for the OrgOrgRelation updated
     * @param orgOrgRelationInfo the new data for the OrgOrgRelation
     * @param contextInfo information containing the principalId and locale
     *        information about the caller of service operation
     * @return the updated OrgOrgRelation
     * @throws DataValidationErrorException supplied data is invalid
     * @throws DoesNotExistException orgOrgRelationId is not found
     * @throws InvalidParameterException orgOrgRelationInfo or
     *         contextInfo is not valid
     * @throws MissingParameterException orgOrgRelationId,
     *         orgOrgRelationInfo, or contextInfo is missing or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     * @throws ReadOnlyException an attempt at supplying information
     *         designated as read-only
     * @throws VersionMismatchException optimistic locking failure or the action
     *         was attempted on an out of date version
     */
    public OrgOrgRelationInfo updateOrgOrgRelation(@WebParam(name = "orgOrgRelationId") String orgOrgRelationId, @WebParam(name = "orgOrgRelationInfo") OrgOrgRelationInfo orgOrgRelationInfo, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DataValidationErrorException, DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException, ReadOnlyException, VersionMismatchException;

    /**
     * Deletes an existing OrgOrgRelation.
     * 
     * @param orgOrgRelationId the identifier for the OrgOrgRelation
     *        to be deleted
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return status of the delete operation. This must always be true.
     * @throws DoesNotExistException orgOrgrelationId is not found
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException orgOrgRelationId or
     *         contextInfo is missing or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public StatusInfo deleteOrgOrgRelation(@WebParam(name = "orgOrgRelationId") String orgOrgRelationId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;



















    /** 
     * Retrieves all Types of OrgPersonRelations between an
     * organization and a person known by this service.
     *
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return a list of all OrgPersonRelation Types
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException contextInfo is missing or
     *         null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<TypeInfo> getOrgPersonRelationTypes(@WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves the OrgPersonRelationship Types between an
     * organization and a person that are allowed for a particular
     * Org Type.
     *
     * @param orgTypeKey an identifier for an Org Type
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return a list of organization person relationship types that
     *         are valid for the supplied organization type (may have
     *         nothing)
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException orgTypeKey or contextInfo is
     *         missing or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<TypeInfo> getOrgPersonRelationTypesForOrgType(@WebParam(name = "orgTypeKey") String orgTypeKey, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;



    /** 
     * Validates a organization to person relationship. Depending on
     * the value of validationType, this validation could be limited
     * to tests on just the current object and its directly contained
     * sub-objects or expanded to perform all tests related to this
     * object. If an identifier is present for the organization person
     * relationship (and/or one of its contained sub-objects) and a
     * record is found for that identifier, the validation checks if
     * the organization person relationship can be shifted to the new
     * values. If an identifier is not present or a record cannot be
     * found for the identifier, it is assumed that the record does
     * not exist and as such, the checks performed will be much
     * shallower, typically mimicking those performed by setting the
     * validationType to the current object.
     *
     * @param validationType identifier of the extent of validation
     * @param orgPersonRelationInfo organization person relationship information to be tested.
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return results from performing the validation
     * @throws DoesNotExistException validationTypeKey not found
     * @throws InvalidParameterException orgPersonRelationInfo or
     *         contextInfo is not valid
     * @throws MissingParameterException missing validationTypeKey, orgPersonRelationInfo
     * @throws OperationFailedException unable to complete request
     */
    public List<ValidationResultInfo> validateOrgPersonRelation(@WebParam(name="validationType")String validationType, @WebParam(name="orgPersonRelationInfo")OrgPersonRelationInfo orgPersonRelationInfo, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException;

    /** 
     * Validates an organization position restriction. Depending on
     * the value of validationType, this validation could be limited
     * to tests on just the current object and its directly contained
     * sub-objects or expanded to perform all tests related to this
     * object. If an identifier is present for the position
     * restriction (and/or one of its contained sub-objects) and a
     * record is found for that identifier, the validation checks if
     * the position restriction can be shifted to the new values. If
     * an identifier is not present or a record cannot be found for
     * the identifier, it is assumed that the record does not exist
     * and as such, the checks performed will be much shallower,
     * typically mimicking those performed by setting the
     * validationType to the current object.
     *
     * @param validationType identifier of the extent of validation
     * @param orgPositionRestrictionInfo organization position restriction information to be tested.
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return results from performing the validation
     * @throws DoesNotExistException validationTypeKey not found
     * @throws InvalidParameterException orgPositionRestrictionInfo
     *         or contextInfo is not valid
     * @throws MissingParameterException missing validationTypeKey, orgPositionRestrictionInfo
     * @throws OperationFailedException unable to complete request
     */
    public List<ValidationResultInfo> validateOrgPositionRestriction(@WebParam(name="validationType")String validationType, @WebParam(name="orgPositionRestrictionInfo")OrgPositionRestrictionInfo orgPositionRestrictionInfo, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException;



    /** 
     * Tests if a specified organization is a descendant of the other
     * specified organization in the given organization hierarchy.
     *
     * @param orgId identifier of the "ancestor" organization
     * @param descendantOrgId identifier of the organization to be checked if it is a descendant
     * @param orgHierarchy identifier of the organization hierarchy to be checked against
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return True if the organization is a descendant of the other organization in that hierarchy
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException missing orgId, descendantOrgId, orgHierarchy
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public Boolean isDescendant(@WebParam(name="orgId")String orgId, @WebParam(name="descendantOrgId")String descendantOrgId, @WebParam(name="orgHierarchy")String orgHierarchy, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves the list of identifiers for all "descendant"
     * organizations of the specified organization in the given
     * organization hierarchy, regardless of the distance from the
     * specified organization.  Information about the distance from
     * the original organization is not returned by this call, so this
     * can be seen as a flattened and de-duplicated representation.
     * 
     * @param orgId identifier of the "ancestor" organization
     * @param orgHierarchy identifier of the organization hierarchy to be checked against
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return list of identifiers for the "descendant" organizations for the specified organization
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException missing orgId, orgHierarchy
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<String> getAllDescendants(@WebParam(name="orgId")String orgId, @WebParam(name="orgHierarchy")String orgHierarchy, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves the list of identifiers for all "ancestor" organizations of the
     * the specified organization in the given organization hierarchy,
     * regardless of the distance from the specified organization. 
     * Information about the distance from the original organization is not
     * returned by this call, so this can be seen as a flattened and
     * de-duplicated representation.
     * 
     * @param orgId identifier of the "descendant" organization
     * @param orgHierarchy identifier of the organization hierarchy to be checked against
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return list of identifiers for the "ancestor" organizations of the specified organization
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException missing orgId, orgHierarchy
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<String> getAllAncestors(@WebParam(name="orgId")String orgId, @WebParam(name="orgHierarchy")String orgHierarchy, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves an existing org to person relation by the relation ID
     *
     * @param orgPersonRelationId identifier for org to person relation to be retrieved
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return details of the orgPersonRelation for this id
     * @throws DoesNotExistException orgPersonRelationId not found
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException invalid orgPersonRelationId
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public OrgPersonRelationInfo getOrgPersonRelation(@WebParam(name="orgPersonRelationId")String orgPersonRelationId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves a list of existing organization to person relations
     * from a list of org person relation IDs
     *
     * @param orgPersonRelationIdList identifiers for org person relations to be retrieved
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return details of the relations for these ids
     * @throws DoesNotExistException orgPersonRelationId not found
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException invalid orgPersonRelationId
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<OrgPersonRelationInfo> getOrgPersonRelationsByIdList(@WebParam(name="orgPersonRelationIdList")List<String> orgPersonRelationIdList, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves the list of identifiers for people that have the
     * specified type of relationship to this organization The list of
     * person ids are fetched for PersonRelations that are still
     * active meaning the expiration date is greater than the current
     * date
     *
     * @param orgId identifier of the organization that members are being found for
     * @param orgPersonRelationTypeKey type of organization person relationship that is being looked for
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return list of person identifiers that match supplied criteria
     * @throws DoesNotExistException orgId, orgPersonRelationType not found
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException missing orgId, orgPersonRelationType
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<String> getPersonIdsForOrgByRelationType(@WebParam(name="orgId")String orgId, @WebParam(name="orgPersonRelationTypeKey")String orgPersonRelationTypeKey, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves the org to person relations for a particular
     * organization
     *
     * @param orgId identifier for the organization for which organization person relationships are to be found
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return list of organization person relationships found for the supplied organization
     * @throws DoesNotExistException orgId not found
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException missing orgId
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<OrgPersonRelationInfo> getOrgPersonRelationsByOrg(@WebParam(name="orgId")String orgId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves all OrgPersonRelations for a particular Person and
     * Organization
     *
     * @param personId person to use to look for relationships
     * @param orgId organization to use to look for relationships
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return list of organization person relationships that exist for the supplied person and organization
     * @throws DoesNotExistException personId, orgId not found
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException missing personId, orgId
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<OrgPersonRelationInfo> getOrgPersonRelationsByPerson(@WebParam(name="personId")String personId, @WebParam(name="orgId")String orgId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves all organization person relationships for a person.
     *
     * @param personId person to get all relationships for
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return List of organization person relationships that exist for this person
     * @throws DoesNotExistException personId not found
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException missing personId
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<OrgPersonRelationInfo> getAllOrgPersonRelationsByPerson(@WebParam(name="personId")String personId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves all organization person relationships for an
     * organization.
     *
     * @param orgId organization to get all relationships for
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return list of organization person relationships that exist for this organization
     * @throws DoesNotExistException orgId not found
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException missing orgId
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<OrgPersonRelationInfo> getAllOrgPersonRelationsByOrg(@WebParam(name="orgId")String orgId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Tests if a person has a current relationship with a specified organization
     *
     * @param orgId identifier of the organization
     * @param personId identifier of the person
     * @param orgPersonRelationTypeKey type of relationship between the person and organization
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return true if a relationship exists
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException missing orgId, personId, orgPersonRelationTypeKey
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public Boolean hasOrgPersonRelation(@WebParam(name="orgId")String orgId, @WebParam(name="personId")String personId, @WebParam(name="orgPersonRelationTypeKey")String orgPersonRelationTypeKey, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Retrieves a list of organization-specific restrictions on
     * relationships with people for a particular organization.
     *
     * @param orgId identifier of the organization
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return list of the organization-specific position restriction information
     * @throws DataValidationErrorException One or more values invalid for this operation
     * @throws DoesNotExistException the orgId not found
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException missing orgId
     * @throws PermissionDeniedException an authorization failure occurred
     * @throws OperationFailedException unable to complete request
     */
    public List<OrgPositionRestrictionInfo> getPositionRestrictionsByOrg(@WebParam(name="orgId")String orgId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DataValidationErrorException, DoesNotExistException, InvalidParameterException, MissingParameterException, PermissionDeniedException, OperationFailedException;


    /** 
     * Creates an organization to person relationship between an organization and a person with a particular type.
     * @param orgId organization
     * @param personId person
     * @param orgPersonRelationTypeKey organization person relationship type
     * @param orgPersonRelationInfo organization person relationship information
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return detail information of the new organization to person relationship
     * @throws AlreadyExistsException organization person relationship already exists
     * @throws DataValidationErrorException one or more values invalid for this operation
     * @throws DoesNotExistException personId, orgId, orgPersonRelationTypeKey not found
     * @throws InvalidParameterException orgPersonRelationInfo or
     *         contextInfo is not valid
     * @throws MissingParameterException missing personId, orgId, orgPersonRelationTypeKey, orgPersonRelationInfo
     * @throws PermissionDeniedException an authorization failure occurred
     * @throws OperationFailedException unable to complete request
     */
    public OrgPersonRelationInfo createOrgPersonRelation(@WebParam(name="orgId")String orgId, @WebParam(name="personId")String personId, @WebParam(name="orgPersonRelationTypeKey")String orgPersonRelationTypeKey, @WebParam(name="orgPersonRelationInfo")OrgPersonRelationInfo orgPersonRelationInfo, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws AlreadyExistsException, DataValidationErrorException, DoesNotExistException, InvalidParameterException, MissingParameterException, PermissionDeniedException, OperationFailedException;

    /** 
     * Updates an organization to person relationship.
     *
     * @param orgPersonRelationId organization person relationship identifier
     * @param orgPersonRelationInfo information about the organization to person relationship to be updated
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return updated organization to person relationship information
     * @throws DataValidationErrorException one or more values invalid for this operation
     * @throws DoesNotExistException orgPersonRelationId not found
     * @throws InvalidParameterException orgPersonRelationInfo or
     *         contextInfo is not valid
     * @throws MissingParameterException missing orgPersonRelationId, orgPersonRelationInfo
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     * @throws VersionMismatchException action was attempted on an out of date version.
     */
    public OrgPersonRelationInfo updateOrgPersonRelation(@WebParam(name="orgPersonRelationId")String orgPersonRelationId, @WebParam(name="orgPersonRelationInfo")OrgPersonRelationInfo orgPersonRelationInfo, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DataValidationErrorException, DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException, VersionMismatchException;

    /** 
     * Removes an organization to person relationship.
     *
     * @param orgPersonRelationId organization person relationship identifier
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return status of the operation
     * @throws DoesNotExistException orgPersonRelationId not found
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException missing orgPersonRelationId
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public StatusInfo removeOrgPersonRelation(@WebParam(name="orgPersonRelationId")String orgPersonRelationId, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Adds a description of the organization-specific usage of an
     * organization person relationship type. This information
     * typically coincides with constraints, such as how many
     * relationships of a given type may be active at a particular
     * time, etc.
     *
     * @param orgId organization
     * @param orgPersonRelationTypeKey organization person relationship type
     * @param orgPositionRestrictionInfo organization position restriction information
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return information about the newly created organization position restriction
     * @throws AlreadyExistsException org position restriction already exists
     * @throws DataValidationErrorException one or more values invalid for this operation
     * @throws DoesNotExistException the orgId, orgPersonRelationTypeKey not found
     * @throws InvalidParameterException orgPersonrestrictionInfo or
     *         contextInfo is not valid
     * @throws MissingParameterException missing orgId, orgPersonRelationTypeKey, orgPositionRestrictionInfo
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public OrgPositionRestrictionInfo addPositionRestrictionToOrg(@WebParam(name="orgId")String orgId, @WebParam(name="orgPersonRelationTypeKey")String orgPersonRelationTypeKey, @WebParam(name="orgPositionRestrictionInfo")OrgPositionRestrictionInfo orgPositionRestrictionInfo, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws AlreadyExistsException, DataValidationErrorException, DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    /** 
     * Updates a description of the organization-specific usage of an
     * organization person relationship type.
     *
     * @param orgId organization
     * @param orgPersonRelationTypeKey organization person relationship type
     * @param orgPositionRestrictionInfo organization position restriction information
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return information about the updated organization position restriction
     * @throws DataValidationErrorException one or more values invalid for this operation
     * @throws DoesNotExistException orgId, orgPersonRelationTypeKey not found
     * @throws InvalidParameterException orgPersonrestrictionInfo or
     *         contextInfo is not valid
     * @throws MissingParameterException missing orgId, orgPersonRelationTypeKey, orgPositionRestrictionInfo
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     * @throws VersionMismatchException action was attempted on an out of date version.
     */
    public OrgPositionRestrictionInfo updatePositionRestrictionForOrg(@WebParam(name="orgId")String orgId, @WebParam(name="orgPersonRelationTypeKey")String orgPersonRelationTypeKey, @WebParam(name="orgPositionRestrictionInfo")OrgPositionRestrictionInfo orgPositionRestrictionInfo, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DataValidationErrorException, DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException, VersionMismatchException;

    /** 
     * Removes a description of the organization-specific usage of an
     * organization person relationship type.
     *
     * @param orgId organization
     * @param orgPersonRelationTypeKey organization person relationship type
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return status
     * @throws DoesNotExistException the orgId, orgPersonRelationTypeKey not found
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException missing orgId, orgPersonRelationTypeKey
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public StatusInfo removePositionRestrictionFromOrg(@WebParam(name="orgId")String orgId, @WebParam(name="orgPersonRelationTypeKey")String orgPersonRelationTypeKey, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;

    
    /**
     * Finds a list of all orgs in the org hierarchy starting at the
     * root org and going down maxLevels of the tree
     *
     * @param rootOrgId 
     * @param orgHierarchyId
     * @param maxLevels the max number of levels in the tree to return. If set to 0 returns all nodes in the tree
     * @param contextInfo information containing the principalId and
     *        locale information about the caller of service operation
     * @return List of OrgTreeInfo in
     * @throws DoesNotExistException
     * @throws InvalidParameterException contextInfo is not valid
     * @throws MissingParameterException
     * @throws OperationFailedException
     * @throws PermissionDeniedException
     */
    public List<OrgTreeInfo> getOrgTree(@WebParam(name="rootOrgId")String rootOrgId,@WebParam(name="orgHierarchyId")String orgHierarchyId, @WebParam(name="maxLevels")int maxLevels, @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException;;
}
