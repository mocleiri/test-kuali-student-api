package org.kuali.student.common.ui.server.gwt;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.student.common.assembly.data.Data;
import org.kuali.student.common.assembly.data.Metadata;
import org.kuali.student.common.assembly.transform.AuthorizationFilter;
import org.kuali.student.common.assembly.transform.MetadataFilter;
import org.kuali.student.common.assembly.transform.TransformFilter;
import org.kuali.student.common.assembly.transform.TransformFilter.TransformFilterAction;
import org.kuali.student.common.assembly.transform.TransformationManager;
import org.kuali.student.common.dto.DtoConstants;
import org.kuali.student.common.exceptions.DataValidationErrorException;
import org.kuali.student.common.exceptions.DoesNotExistException;
import org.kuali.student.common.exceptions.OperationFailedException;
import org.kuali.student.common.rice.StudentIdentityConstants;
import org.kuali.student.common.rice.authorization.PermissionType;
import org.kuali.student.common.ui.client.service.DataSaveResult;
import org.kuali.student.common.ui.shared.IdAttributes;
import org.kuali.student.common.util.security.SecurityUtils;
import org.kuali.student.core.assembly.transform.ProposalWorkflowFilter;
import org.kuali.student.core.proposal.dto.ProposalInfo;
import org.kuali.student.core.proposal.service.ProposalService;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true,noRollbackFor={DoesNotExistException.class},rollbackFor={Throwable.class})
public abstract class AbstractDataService implements DataService{

	private static final long serialVersionUID = 1L;

	final Logger LOG = Logger.getLogger(AbstractDataService.class);

	private TransformationManager transformationManager;
	
	private PermissionService permissionService;

    //TODO: why do we have this reference in the base class????
	private ProposalService proposalService;

	@Override
	public Data getData(String id) throws OperationFailedException {
		Map<String, Object> filterProperties = getDefaultFilterProperties();
		filterProperties.put(TransformFilter.FILTER_ACTION, TransformFilterAction.GET);
		filterProperties.put(MetadataFilter.METADATA_ID_VALUE, id);
		
		String dtoId = id;
		//First check if this is a proposal id
        //TODO: Igor : Why do we check for this when getting the data for programs?
		try{
			if (proposalService != null){
				ProposalInfo proposalInfo = proposalService.getProposal(dtoId);
				filterProperties.put(ProposalWorkflowFilter.PROPOSAL_INFO, proposalInfo);
				dtoId = proposalInfo.getProposalReference().get(0);
			}			

			Object dto = get(dtoId);
			if (dto != null){
				return transformationManager.transform(dto, filterProperties);
			}
		} catch(DoesNotExistException e){
			return null;
		} catch (Exception e) {
			throw new OperationFailedException("Error getting data",e);
		}
		return null;
	}

	@Override
	public Metadata getMetadata(String id, Map<String, String> attributes) {
		Map<String, Object> filterProperties = getDefaultFilterProperties();
		filterProperties.put(MetadataFilter.METADATA_ID_VALUE, id);
		
		//Place id attributes into filter properties
		String idType = (attributes != null? attributes.get(IdAttributes.ID_TYPE):null);
		String docType = (attributes != null ? attributes.get(StudentIdentityConstants.DOCUMENT_TYPE_NAME):null);
		String dtoState = (attributes != null ? attributes.get(DtoConstants.DTO_STATE):null);
		String dtoNextState = (attributes != null ? attributes.get(DtoConstants.DTO_NEXT_STATE):null);
				
		if (idType == null){
			filterProperties.remove(MetadataFilter.METADATA_ID_TYPE);
		} else {
			filterProperties.put(MetadataFilter.METADATA_ID_TYPE, idType);
		}
	
		if (docType == null){
			filterProperties.put(ProposalWorkflowFilter.WORKFLOW_DOC_TYPE, getDefaultWorkflowDocumentType());
		} else {
			filterProperties.put(ProposalWorkflowFilter.WORKFLOW_DOC_TYPE, docType);
		}

		if (dtoState != null){
			filterProperties.put(DtoConstants.DTO_STATE, dtoState);			
		}
		
		if (dtoNextState != null){
			filterProperties.put(DtoConstants.DTO_NEXT_STATE, dtoNextState);			
		}

		if (checkDocumentLevelPermissions()){
			filterProperties.put(AuthorizationFilter.DOC_LEVEL_PERM_CHECK, Boolean.TRUE.toString());
		}
		
		Metadata metadata = transformationManager.getMetadata(getDtoClass().getName(), filterProperties); 
		return metadata;
	}

	@Override
	@Transactional(readOnly=false)
	public DataSaveResult saveData(Data data) throws OperationFailedException, DataValidationErrorException {
		Map<String, Object> filterProperties = getDefaultFilterProperties();
		filterProperties.put(TransformFilter.FILTER_ACTION, TransformFilterAction.SAVE);
		try {
			Object dto = transformationManager.transform(data, getDtoClass(), filterProperties);
			dto = save(dto, filterProperties);
				
			Data persistedData = transformationManager.transform(dto, filterProperties);
			return new DataSaveResult(null, persistedData);
		}catch (DataValidationErrorException e){
			throw e;
		}catch (Exception e) {
			throw new OperationFailedException("Unable to save",e);
		}
	}

	@Override
	public Boolean isAuthorized(PermissionType type, Map<String,String> attributes) {
		String user = SecurityUtils.getCurrentUserId();
		boolean result = false;
		if (checkDocumentLevelPermissions()) {
			if (type == null) {
				return null;
			}
			String namespaceCode = type.getPermissionNamespace();
			String permissionTemplateName = type.getPermissionTemplateName();
			
			Map<String,String> roleQuals = new LinkedHashMap<String,String>();
			if (attributes != null) {				
				if (proposalService != null){
					ProposalInfo proposalInfo = null;
					try {
						if (attributes.containsKey(IdAttributes.IdType.KS_KEW_OBJECT_ID.toString())){
							proposalInfo = proposalService.getProposal(attributes.get(IdAttributes.IdType.KS_KEW_OBJECT_ID.toString()));
						} else if (attributes.containsKey(IdAttributes.IdType.DOCUMENT_ID.toString())){
							proposalInfo = proposalService.getProposalByWorkflowId(attributes.get(IdAttributes.IdType.DOCUMENT_ID.toString()));
						}
						if (proposalInfo != null){
							attributes.put(IdAttributes.IdType.KS_KEW_OBJECT_ID.toString(), proposalInfo.getId());
							attributes.put(IdAttributes.IdType.DOCUMENT_ID.toString(), proposalInfo.getWorkflowId());
							attributes.put(StudentIdentityConstants.DOCUMENT_TYPE_NAME, proposalInfo.getType());
						}
					} catch (Exception e){
						LOG.error("Could not retrieve proposal to determine permission qualifiers.");
					}
				}
				roleQuals.putAll(attributes);
			}
			if (StringUtils.isNotBlank(namespaceCode) && StringUtils.isNotBlank(permissionTemplateName)) {
				LOG.info("Checking Permission '" + namespaceCode + "/" + permissionTemplateName + "' for user '" + user + "'");
				result = getPermissionService().isAuthorizedByTemplate(user, namespaceCode, permissionTemplateName, null, roleQuals);
			}
			else {
				LOG.info("Can not check Permission with namespace '" + namespaceCode + "' and template name '" + permissionTemplateName + "' for user '" + user + "'");
				return Boolean.TRUE;
			}
		}
		else {
			LOG.info("Will not check for document level permissions. Defaulting authorization to true.");
			result = true;
		}
		LOG.info("Result of authorization check for user '" + user + "': " + result);
		return Boolean.valueOf(result);
	}
	
	public Map<String, Object> getDefaultFilterProperties(){
		Map<String, Object> filterProperties = new HashMap<String,Object>();
		filterProperties.put(MetadataFilter.METADATA_ID_TYPE, StudentIdentityConstants.QUALIFICATION_KEW_OBJECT_ID);
		filterProperties.put(ProposalWorkflowFilter.WORKFLOW_USER, SecurityUtils.getCurrentUserId());
		
		return filterProperties;
	}
	
	protected DataSaveResult _saveData(Data data, Map<String, Object> filterProperties) throws OperationFailedException{
		try {
			filterProperties.put(MetadataFilter.METADATA_ID_VALUE, (String)data.query("id"));	

			Object dto = transformationManager.transform(data, getDtoClass(),filterProperties);
			dto = save(dto, filterProperties);
				
			Data persistedData = transformationManager.transform(dto,filterProperties);
			return new DataSaveResult(null, persistedData);
		} catch (DataValidationErrorException dvee){
			return new DataSaveResult(dvee.getValidationResults(), null);
		} catch (Exception e) {
			LOG.error("Unable to save", e);
			throw new OperationFailedException("Unable to save");
		}		
	}
	
	protected boolean checkDocumentLevelPermissions() {
		return false;
	}



	public TransformationManager getTransformationManager() {
		return transformationManager;
	}

	public void setTransformationManager(TransformationManager transformationManager) {
		this.transformationManager = transformationManager;
	}

	public PermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}
	
	public ProposalService getProposalService() {
		return proposalService;
	}

	public void setProposalService(ProposalService proposalService) {
		this.proposalService = proposalService;
	}

	protected abstract String getDefaultWorkflowDocumentType();
	
	protected abstract String getDefaultMetaDataState();
	
	/**
	 * Implement this method to make to make service call to get DTO object. The method is called
	 * by the get(Data) method before it invokes transformationManager to convert DTO to a Data map 
	 * 
	 * @param id DTO id
	 * @return the dto retrieved by calling the appropriate service method
	 * @throws Exception
	 */
	protected abstract Object get(String id) throws Exception;
	
	/**
	 * Implement this method to make to make service call to get DTO object. The method is called	 
	 * by the get(Data) method before it invokes transformationManager to convert DTO to a Data map
	 * 
	 * @param id DTO id
	 * @return the dto retrieved by calling the appropriate service method
	 * @throws Exception
	 */ 
	protected abstract Object save(Object dto, Map<String, Object> properties) throws Exception;
	
	/**
	 * Implement this method to return the type of the dto object.
	 * 
	 * @return The object type returned and expected by the get & save dto methods
	 */
	protected abstract Class<?> getDtoClass();
}
