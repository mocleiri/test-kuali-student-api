package org.kuali.student.core.assembly.transform;

import java.io.StringWriter;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.rice.kew.dto.DocumentDetailDTO;
import org.kuali.rice.kew.service.WorkflowUtility;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kew.webservice.DocumentResponse;
import org.kuali.rice.kew.webservice.SimpleDocumentActionsWebService;
import org.kuali.rice.kew.webservice.StandardResponse;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * The WorkflowFilter can be added to dto/data TransformationManager to create and update a KEW
 * workflow document. The workflow filter can be configured as follows:
 * 
 * 1) defaultDocType: The workflow document type is normally determined by WORKFLOW_DOC_TYPE property.
 * However, when this property is not found it will use the default doc type.
 * 
 * 2) docTitlePath: The workflow document title is normally determined by the ProposalFilter.PROPOSAL_NAME property.
 * However, when this property is not found it will use the docTitlePath to use a field from the dto object as the title.
 * 
 * 3) docTypeFieldPaths: Use this to provide the field paths to use when creating the workflow document content for
 * each document type. Below is an example spring configuration for this property:
 * 
 *   <map>
 *     <entry key="kuali.proposal.type.docType1">
 *       <map>
 *	       <entry key="field1" value="field1Path"/>
 *         <entry key="field2" value="field2Array[0]"/>
 *       </map>
 *	   </entry>
 *     <entry key="kuali.proposal.type.docType2">
 *        <map>
 *	       	<entry key="field1" value="field1Path"/>
 *	        <entry key="field2" value="field2Path"/>
 *        </map>
 *     </entry>
 *   </map>
 *
 *   Note: At this time this does not allow us to directly obtain values from the proposalInfo structure. To easily allow
 *   this in the future the WorkflowFilter and ProposalFilter should really be combined.
 *    
 * @author Will
 *
 */
public class WorkflowFilter extends AbstractDTOFilter {
    
	public static final String WORKFLOW_ACTION		= "WorkflowFilter.Action";
	public static final String WORKFLOW_DOC_ID		= "WorkflowFilter.DocumentId";
    public static final String WORKFLOW_DOC_TYPE	= "WorkflowFilter.DocumentType";
    public static final String WORKFLOW_USER		= "WorkflowFilter.WorkflowUser";
    
    public static final String WORKFLOW_SUBMIT		= "Submit";
    public static final String WORKFLOW_APPROVE		= "Approve";
    
	// below string MUST match org.kuali.student.lum.workflow.qualifierresolver.AbstractCocOrgQualifierResolver.DOCUMENT_CONTENT_XML_ROOT_ELEMENT_NAME constant
    public static final String DOCUMENT_CONTENT_XML_ROOT_ELEMENT_NAME	= "info";

    final Logger LOG = Logger.getLogger(WorkflowFilter.class);
    
    private WorkflowUtility workflowUtilityService;
	private SimpleDocumentActionsWebService simpleDocService;
	
	private Map<String, Map<String, String>> docTypeFieldMap;
	private String docTitlePath;
	private String defaultDocType;
	private Class<?> dtoClass;
    
	/**
	 * This intercepts a newly created/updated object and initiates a workflow, or submits/approves 
	 * the workflow document for the object. 
	 */
	@Override
	public void applyOutboundDtoFilter(Object data, Map<String, String> properties) throws Exception {
        if(simpleDocService==null){
        	throw new Exception("Workflow Service is unavailable");
        }
		
		//get a user name
        String username = properties.get(WORKFLOW_USER);
        	        
        //Setting the app id to proposal id or the id of data object        
        String appId = properties.get(MetadataFilter.METADATA_ID_VALUE); 
        
        //Get the workflow id
        String workflowId = properties.get(WORKFLOW_DOC_ID);
        
        String docType = properties.get(WORKFLOW_DOC_TYPE);
        if (docType == null){
        	docType = getDocumentType();
        }
       
        //Get the workflow document or create one if workflow document doesn't exist
        DocumentDetailDTO docDetail;
        if (workflowId != null){
        	docDetail = workflowUtilityService.getDocumentDetail(Long.parseLong(workflowId));
        } else  {
            LOG.info("Creating Workflow Document.");
            
        	String docTitle = "Unnamed"; 
			if (properties.get(ProposalFilter.PROPOSAL_NAME) != null){
				docTitle = properties.get(ProposalFilter.PROPOSAL_NAME);
			} else if (getDocumentTitle(data) != null){
				docTitle = getDocumentTitle(data);
			}
            
            DocumentResponse docResponse = simpleDocService.create(username, appId, docType, docTitle);
            if (StringUtils.isNotBlank(docResponse.getErrorMessage())) {
            	throw new RuntimeException("Error found creating document: " + docResponse.getErrorMessage());
            }
            
            //Lookup the workflow document detail to see if create was successful
			try {
				docDetail = workflowUtilityService.getDocumentDetailFromAppId(docType, appId);
			} catch (Exception e) {
            	throw new RuntimeException("Error found gettting document for newly created object with id " + appId);
			}
			
			properties.put(WORKFLOW_DOC_ID, String.valueOf(docDetail.getRouteHeaderId()));
		}

        //Generate the document content xml
        String docContent = getDocumentContent(data, docType);
        
        //Save
        StandardResponse stdResp;
        if ( (KEWConstants.ROUTE_HEADER_INITIATED_CD.equals(docDetail.getDocRouteStatus())) ||
        	 (KEWConstants.ROUTE_HEADER_SAVED_CD.equals(docDetail.getDocRouteStatus())) ) {
        	//if the route status is initial, then save initial
        	stdResp = simpleDocService.save(docDetail.getRouteHeaderId().toString(), username, docDetail.getDocTitle(), docContent, "");
        } else {
        	//Otherwise just update the doc content
        	stdResp = simpleDocService.saveDocumentContent(docDetail.getRouteHeaderId().toString(), username, docDetail.getDocTitle(), docContent);
        }

        //Check if there were errors saving
        if(stdResp==null||StringUtils.isNotBlank(stdResp.getErrorMessage())){
        	if(stdResp==null){
        		throw new RuntimeException("Error found updating document");
        	}else{
        		throw new RuntimeException("Error found updating document: " + stdResp.getErrorMessage());
        	}
    	}            						
	}

	
	/**
	 * Used to set the workflow utility service required by this filter
	 * 
	 * @param workflowUtilityService
	 */
	public void setWorkflowUtilityService(WorkflowUtility workflowUtilityService) {
		this.workflowUtilityService = workflowUtilityService;
	}
	
	/**
	 * Used to set the simple doc service required by this filter
	 * @param simpleDocService
	 */
	public void setSimpleDocService(SimpleDocumentActionsWebService simpleDocService) {
		this.simpleDocService = simpleDocService;
	}

	
	/**
	 * @return The doctype of the workflow document to be associated with this workflow process.
	 */
	public String getDocumentType(){
		return defaultDocType;
	}
		
	/**
	 * The title to associate with the workflow process.
	 * 
	 * @param data
	 * @return
	 */
	public String getDocumentTitle(Object dto) throws Exception{
		return getString(dto, docTitlePath);
	}
	
	/**
	 * This method should be implemented to provide the document content required to properly
	 * handle the workflow associated with the object being processed.
	 * 
	 * @param data
	 * @return the document content required by the workflow process
	 */
	public String getDocumentContent(Object dto, String docType) throws FilterException {
		String docContentString = "";
		
		Map<String, String> docFieldMap = docTypeFieldMap.get(docType);
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation impl = builder.getDOMImplementation();
			
			//Create the document content
			Document docContent = null;
			docContent = impl.createDocument(null, null, null);
			Element root = docContent.createElement(DOCUMENT_CONTENT_XML_ROOT_ELEMENT_NAME);
			docContent.appendChild(root);
			for (Entry<String,String> entry:docFieldMap.entrySet()){
				Element element = docContent.createElement(entry.getKey());
				Text node = docContent.createTextNode(getString(dto,entry.getValue()));
				element.appendChild(node);
				root.appendChild(element);
			}
			
			//Convert document content to string
			DOMSource domSource = new DOMSource(docContent);
	        TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer transformer = tf.newTransformer();
	        StringWriter sw = new StringWriter();
	        StreamResult sr = new StreamResult(sw);
	        transformer.transform(domSource, sr);

	        docContentString = sw.toString();
	        
	        LOG.debug("Generated workflow doc content: " + docContentString);
		} catch (Exception e) {
			LOG.error(e);
			throw new FilterException("Error creating document content",e);
		}
		
		return docContentString;
	}

	/**
	 * This method inspects the dto for the fieldName and gets it's value.
	 * 
	 * @param dto
	 * @param fieldName
	 * @return The value of dto.fieldName property
	 * @throws Exception
	 */
	private String getString(Object dto, String fieldName) throws Exception{
		String value = "";
		try {
			if (fieldName.contains("[")){
				try {
					value = BeanUtils.getIndexedProperty(dto, fieldName);	
				} catch (IndexOutOfBoundsException e){
					//Just return empty string
				}				
			} else {
				try {
					value = BeanUtils.getProperty(dto, fieldName);
				} catch (NoSuchMethodException nsme){
					//This could be result of Unknown Property error
					//The property could be a dynamic attribute
					String mappedProperty = "attributes(" + fieldName + ")";
					if (BeanUtils.getMappedProperty(dto, mappedProperty) != null){
						value = BeanUtils.getMappedProperty(dto, mappedProperty);
					} else {
						LOG.warn("Property " + fieldName + " has a null dynamic attribute value. Make sure this field is a dynamic attribute.");
					}
				}
			}
		} catch (NullPointerException npe){
			//Just return empty string
		}
		
		return value;
	}

	@Override
	public Class<?> getType() {
		return dtoClass;
	}
	

	/**
	 * Set the field paths to be used to generate the workflow document content from the dto object
	 * for each document type.
	 * 
	 * @param docFieldMap
	 */
	public void setDocTypeFieldPaths(Map<String, Map<String,String>> docFieldMap) {
		this.docTypeFieldMap = docFieldMap;
	}


	/**
	 * The path in the dto object from which to set the default workflow document title. By default
	 * this is set to the proposal title.
	 * 
	 * @param docTitlePath
	 */
	public void setDocTitlePath(String docTitlePath) {
		this.docTitlePath = docTitlePath;
	}


	/**
	 * The default workflow document type to create for workflow. The default is used when workflow
	 * document type could not be obtained from the WORKFLOW_DOC_TYPE filter property.
	 * 
	 * @param docType
	 */
	public void setDefaultDocType(String docType) {
		this.defaultDocType = docType;
	}


	public void setDtoClass(Class<?> dtoClass) {
		this.dtoClass = dtoClass;
	}

}
