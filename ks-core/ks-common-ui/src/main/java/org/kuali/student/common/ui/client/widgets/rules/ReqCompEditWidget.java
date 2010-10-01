package org.kuali.student.common.ui.client.widgets.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.student.common.ui.client.configurable.mvc.FieldDescriptor;
import org.kuali.student.common.ui.client.configurable.mvc.SectionTitle;
import org.kuali.student.common.ui.client.configurable.mvc.layouts.BasicLayout;
import org.kuali.student.common.ui.client.configurable.mvc.views.VerticalSectionView;
import org.kuali.student.common.ui.client.mvc.*;
import org.kuali.student.common.ui.client.widgets.KSDropDown;
import org.kuali.student.common.ui.client.widgets.KSLabel;
import org.kuali.student.common.ui.client.widgets.buttongroups.ButtonEnumerations;
import org.kuali.student.common.ui.client.widgets.field.layout.button.ActionCancelGroup;
import org.kuali.student.common.ui.client.widgets.field.layout.element.MessageKeyInfo;
import org.kuali.student.common.ui.client.widgets.list.KSSelectItemWidgetAbstract;
import org.kuali.student.common.ui.client.widgets.list.ListItems;
import org.kuali.student.common.ui.client.widgets.list.SelectionChangeEvent;
import org.kuali.student.common.ui.client.widgets.list.SelectionChangeHandler;
import org.kuali.student.core.assembly.data.Data;
import org.kuali.student.core.assembly.data.Metadata;
import org.kuali.student.core.assembly.data.QueryPath;
import org.kuali.student.core.dto.RichTextInfo;
import org.kuali.student.core.statement.dto.ReqCompFieldInfo;
import org.kuali.student.core.statement.dto.ReqCompFieldTypeInfo;
import org.kuali.student.core.statement.dto.ReqComponentInfo;
import org.kuali.student.core.statement.dto.ReqComponentTypeInfo;
import org.kuali.student.core.validation.dto.ValidationResultInfo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;

public class ReqCompEditWidget extends FlowPanel {

    //widgets
    private FlowPanel reqCompTypePanel = new FlowPanel();
    private FlowPanel holdFieldsPanel = new FlowPanel();
    private KSDropDown reqCompTypesList = new KSDropDown();
    private VerticalSectionView reqCompFieldsPanel;
    private ActionCancelGroup actionCancelButtons = new ActionCancelGroup(ButtonEnumerations.AddCancelEnum.ADD, ButtonEnumerations.AddCancelEnum.CANCEL);
    private final String NO_SELECTION_TEXT = "Select rule type";

    //data
    private List<ReqComponentTypeInfo> reqCompTypeInfoList;     //list of all Requirement Component Types based on selected Statement Type
    private ListItems listItemReqCompTypes;                 	//list of all Requirement Component Types wrapped in a list for drop down
    private Map<String, String> compositionTemplates = new HashMap<String, String>();
    private ReqComponentInfo editedReqComp;						//Req. Component that user is editing or adding
    private ReqComponentTypeInfo selectedReqCompType;           //the type of Req. Component that the user is editing or adding
    private List<String> selectedReqCompFieldTypes;             //types of all fields for given selected req. component type
    private boolean addingNewReqComp;                           //adding (true) or editing (false) req. component
    private DataModel ruleFieldsData;
    private BasicLayout reqCompController;

    //other
    private Callback reqCompConfirmCallback;
    private Callback newReqCompSelectedCallback;
    private Callback fieldsMetadataTemplateCallback;
    private Callback compositionTemplateCallback;	
    private static int tempCounterID = 99999;
    private static final String REQ_COMP_MODEL_ID = "reqCompModelId";    
    private enum ReqCompEditView {VIEW}

    //TODO use app context for text

    public ReqCompEditWidget() {
        super();

        ruleFieldsData = new DataModel();
        ruleFieldsData.setRoot(new Data());

        //wait until req. comp. types are loaded and user actually selects a type from drop down
        reqCompTypesList.setEnabled(false);
        setEnableAddRuleButtons(false);

        setupReqCompTypesList();
        setupHandlers();
        
        displayReqCompListPanel();
        add(holdFieldsPanel);
        displayConfirmButton();       
    }

    private void setupHandlers() {

        actionCancelButtons.addCallback(new Callback<ButtonEnumerations.ButtonEnum>(){
                @Override
               public void exec(ButtonEnumerations.ButtonEnum result) {

                    setEnableAddRuleButtons(false);
                    
                    if (result == ButtonEnumerations.AddCancelEnum.ADD) {

                        //if req. comp. has no data then do not retrieve field values
                        if (ruleFieldsData.getRoot().size() == 0) {
                            finalizeRuleUpdate();
                            return;
                        }

                        //1. check that all fields have values
                        ruleFieldsData.validate(new Callback<List<ValidationResultInfo>>() {
                            @Override
                            public void exec(List<ValidationResultInfo> validationResults) {

                                //do not proceed if the user input is not valid
                                if (!reqCompController.isValid(validationResults, true, true)) {
                                    setEnableAddRuleButtons(true);
                                    return;
                                }

                                //2. retrieve entered values and set the rule info
                                List<ReqCompFieldInfo> editedFields = new ArrayList<ReqCompFieldInfo>();
                                for (ReqCompFieldTypeInfo fieldTypeInfo : selectedReqCompType.getReqCompFieldTypeInfos()) {
                                    ReqCompFieldInfo fieldInfo = new ReqCompFieldInfo();
                                    fieldInfo.setId(fieldTypeInfo.getId());
                                    fieldInfo.setType(fieldTypeInfo.getId());
                                    String fieldValue = ruleFieldsData.getRoot().get(fieldTypeInfo.getId()).toString();

                                    if ((fieldValue == null) || (fieldValue.isEmpty())) {
                                        
                                    }

                                    fieldInfo.setValue((fieldValue == null ? "" : fieldValue.toString()));
                                    editedFields.add(fieldInfo);
                                }

                                //3. update req. component being edited
                                editedReqComp.setReqCompFields(editedFields);
                                finalizeRuleUpdate();
                            }
                        });
                    } else {
                        setupNewReqComp();
                        reqCompConfirmCallback.exec(null);                        
                    }
               }                                             
        });

        reqCompTypesList.addSelectionChangeHandler(new SelectionChangeHandler() {
			@Override
            public void onSelectionChange(SelectionChangeEvent event) {

			    if (!event.isUserInitiated()) {
			        return;
			    }

                //disable Step 2 section and enable 'Add Rule' and 'cancel' buttons
                 newReqCompSelectedCallback.exec(null);                
                 setEnableAddRuleButtons(true);

                 List<String> ids = ((KSSelectItemWidgetAbstract)event.getWidget()).getSelectedItems();
                 selectedReqCompType = reqCompTypeInfoList.get(Integer.valueOf(ids.get(0)));

                 if (addingNewReqComp) {
                     createReqComp(selectedReqCompType);
                 } else {
                	 //editedReqComp.setRequiredComponentType(selectedReqCompType);
                     editedReqComp.setType(selectedReqCompType.getId());
                 }

                 displayFieldsSection();
         }});

    }

    private void finalizeRuleUpdate() {
        editedReqComp.setType(selectedReqCompType.getId());
        /*
        setEnableAddRuleButtons(false);
        holdFieldsPanel.clear();
        if (reqCompTypesList.getSelectedItem() != null) {
            reqCompTypesList.deSelectItem(reqCompTypesList.getSelectedItem());
        } */

        //callback needs to update NL for given req. component and the rule
        reqCompConfirmCallback.exec(editedReqComp);
    }

    public void setupNewReqComp() {
        addingNewReqComp = true;
        selectedReqCompType = null;
        createReqComp(null);
        holdFieldsPanel.clear();
        redraw();        
    }

    /* create a new Req. Component Type based on user selection or empty at first */
    private void createReqComp(ReqComponentTypeInfo reqCompTypeInfo) {
        RichTextInfo desc = new RichTextInfo();
        desc.setPlain("");
        desc.setFormatted("");
        editedReqComp = new ReqComponentInfo();
        editedReqComp.setDesc(desc);
        editedReqComp.setId(Integer.toString(tempCounterID++));  
        editedReqComp.setReqCompFields(null);
        //editedReqComp.setRequiredComponentType(reqCompTypeInfo);
        if (reqCompTypeInfo != null) {
            editedReqComp.setType(reqCompTypeInfo.getId());
        }
    }

    //call when user wants to edit an existing req. component
    public void setupExistingReqComp(ReqComponentInfo existingReqComp) {
        if (!actionCancelButtons.getButton(ButtonEnumerations.AddCancelEnum.ADD).isEnabled()) {
            return;
        }

        addingNewReqComp = false;
        editedReqComp = existingReqComp;
        //originalReqType = editedReqComp.getRequiredComponentType();

        selectedReqCompType = null;
        for (ReqComponentTypeInfo aReqCompTypeInfoList : reqCompTypeInfoList) {
            if (editedReqComp.getType().equals(aReqCompTypeInfoList.getId())) {
                selectedReqCompType = aReqCompTypeInfoList;
                break;
            }
        }
        if (selectedReqCompType == null) {
            GWT.log("Unknown Requirement Component Type found: " + existingReqComp.getType(), null);
            Window.alert("Unknown Requirement Component Type found: " + existingReqComp.getType());
        }

        redraw();        
    }

    private void redraw() {

        selectReqCompTypeInList();        

        displayFieldsSection();

        if (addingNewReqComp) {
            actionCancelButtons.getButton(ButtonEnumerations.AddCancelEnum.ADD).setText("Add Rule");
            setEnableAddRuleButtons(false);
        } else {
            actionCancelButtons.getButton(ButtonEnumerations.AddCancelEnum.ADD).setText("Update Rule"); 
            setEnableAddRuleButtons(false);
            if (selectedReqCompType != null) {
                setEnableAddRuleButtons(true);
            }
        }
    }

    public void displayFieldsSection() {

        //if no req. comp. type is selected then don't display anything
        if (selectedReqCompType == null) {
            return;
        }

        //no display if the req. comp. type has no fields
        if ((selectedReqCompType.getReqCompFieldTypeInfos() == null) || selectedReqCompType.getReqCompFieldTypeInfos().isEmpty()) {
            return;
        }

        //get composition template either from local cache or from service through user of this widget
        String compositionTemplate = compositionTemplates.get(selectedReqCompType.getId());
        if (compositionTemplate == null) {
            compositionTemplateCallback.exec(editedReqComp);
            return;
        }

        displayFieldsStart(compositionTemplate);
    }

    public void displayFieldsStart(String compositionTemplate) {
        
        compositionTemplates.put(selectedReqCompType.getId(), compositionTemplate);
        setEnableAddRuleButtons(true);

        selectedReqCompFieldTypes = new ArrayList<String>();
        for (ReqCompFieldTypeInfo fieldTypeInfo : selectedReqCompType.getReqCompFieldTypeInfos()) {
            selectedReqCompFieldTypes.add(fieldTypeInfo.getId());
        }

        fieldsMetadataTemplateCallback.exec(selectedReqCompFieldTypes);
    }

    public void displayFieldsEnd(List<Metadata> fieldsMetadataList) {

        List<ReqCompFieldInfo> reqCompFields = (editedReqComp == null ? null : editedReqComp.getReqCompFields());
        reqCompFieldsPanel = new VerticalSectionView(ReqCompEditView.VIEW, "", REQ_COMP_MODEL_ID, false);
        reqCompFieldsPanel.addStyleName("KS-Rule-FieldsList");
        
        int ix = 0;
        Map<String, Metadata> fieldDefinitionMetadata = new HashMap<String,Metadata>();
        for (Metadata oneFieldMetadata : fieldsMetadataList) {
            
            Metadata fieldMetadata = oneFieldMetadata.getProperties().get("value");
            String fieldType = selectedReqCompFieldTypes.get(ix++);
            String fieldLabel = getFieldLabel(fieldType);

            FieldDescriptor fd = new FieldDescriptor(fieldType, new MessageKeyInfo(fieldLabel), fieldMetadata);
            reqCompFieldsPanel.addField(fd);

            //add field to the data model metadata
            fieldDefinitionMetadata.put(fieldType, fieldMetadata);
        }

        //setup data model
        Metadata modelDefinitionMetadata = new Metadata();
        modelDefinitionMetadata.setCanView(true);
        modelDefinitionMetadata.setDataType(Data.DataType.DATA);        
        modelDefinitionMetadata.setProperties(fieldDefinitionMetadata);
        ruleFieldsData = new DataModel();
        ruleFieldsData.setRoot(new Data());
        ruleFieldsData.setDefinition(new DataModelDefinition(modelDefinitionMetadata));            

        //initialize fields with values if user is editing an existing rule
        if (!addingNewReqComp) {
            for (String fieldType : selectedReqCompFieldTypes) {
                String fieldValue = getFieldValue(reqCompFields, fieldType);
                if (fieldValue != null) {
                    ruleFieldsData.set(QueryPath.parse(fieldType), fieldValue);
                }
            }
        }

        //setup controller
        reqCompController = new BasicLayout(null);
        reqCompController.addView(reqCompFieldsPanel);
        reqCompController.setDefaultModelId(REQ_COMP_MODEL_ID);        
        reqCompController.registerModel(REQ_COMP_MODEL_ID, new ModelProvider<DataModel>() {
            @Override
            public void requestModel(final ModelRequestCallback<DataModel> callback) {
                callback.onModelReady(ruleFieldsData);
            }
        });

        //show fields
        holdFieldsPanel.clear();
        holdFieldsPanel.add(reqCompController);        
        reqCompController.showView(ReqCompEditView.VIEW);

        //TODO save history        
    }

    private String getFieldLabel(String fieldType) {
        String compositionTemplate = compositionTemplates.get(selectedReqCompType.getId());
        String label = compositionTemplate.substring(compositionTemplate.indexOf(fieldType) + fieldType.length());
        int ix = label.indexOf("reqCompFieldLabel") + "reqCompFieldLabel".length();
        int ix2 = label.indexOf(">", ix);
        return label.substring(ix, ix2).replace("=", "").trim();
    }

    private String getFieldValue(List<ReqCompFieldInfo> fields, String key) {

        if (fields == null) {
            return null;
        }

        for (ReqCompFieldInfo fieldInfo : fields) {
            if (fieldInfo.getId().equals(key)) {
                return (fieldInfo.getValue() == null ? "" : fieldInfo.getValue());
            }
        }
        
        return "";
    }

    private void displayConfirmButton() {
        actionCancelButtons.addStyleName("KS-Rule-ReqComp-btn");
        actionCancelButtons.getButton(ButtonEnumerations.AddCancelEnum.ADD).setText("Add Rule");        
        add(actionCancelButtons);
    }

    private void displayReqCompListPanel() {

        reqCompTypePanel.setStyleName("KS-Rule-ReqCompList-box");

        SectionTitle subHeader = SectionTitle.generateH5Title("Select rule type");
        subHeader.setStyleName("KS-Rule-ReqComp-header");
        reqCompTypePanel.add(subHeader);

        KSLabel instructions = new KSLabel("Use the list below to select the type of rule you would like to add to this requirement");
        reqCompTypePanel.add(instructions);
        
        reqCompTypesList.addStyleName("KS-Rule-ReqCompList");
        reqCompTypePanel.add(reqCompTypesList);
        add(reqCompTypePanel);
    }

    private void selectReqCompTypeInList() {
        if (selectedReqCompType == null) {
            if (reqCompTypesList.getSelectedItem() != null) {
                reqCompTypesList.deSelectItem(reqCompTypesList.getSelectedItem());
            }
            return;
        }

        int i = 0;
        for (ReqComponentTypeInfo comp : reqCompTypeInfoList) {
            if (comp.getId().equals(selectedReqCompType.getId())) {
                reqCompTypesList.selectItem(Integer.toString(i));
                break;
            }
            i++;
        }
    }

    private void setupReqCompTypesList() {
	    listItemReqCompTypes = new ListItems() {
	        @Override
	        public List<String> getAttrKeys() {
	            List<String> attributes = new ArrayList<String>();
	            attributes.add("Key");
	            return attributes;
	        }

	        @Override
	        public String getItemAttribute(String id, String attrkey) {
	            String value = null;
	            Integer index;
	            try{
	                index = Integer.valueOf(id);
	                value = reqCompTypeInfoList.get(index).getDescr();
	            } catch (Exception e) {
	            }

	            return value;
	        }

	        @Override
	        public int getItemCount() {
	            return reqCompTypeInfoList.size();
	        }

	        @Override
	        public List<String> getItemIds() {
	            List<String> ids = new ArrayList<String>();
	            for(int i = 0; i < reqCompTypeInfoList.size(); i++){
	                ids.add(String.valueOf(i));
	            }
	            return ids;
	        }

	        @Override
	        public String getItemText(String id) {
	            return getItemAttribute(id, "?");
	        }
	    };
    }

    private void setEnableAddRuleButtons(boolean enable) {
        actionCancelButtons.getButton(ButtonEnumerations.AddCancelEnum.ADD).setEnabled(enable);
        actionCancelButtons.getButton(ButtonEnumerations.AddCancelEnum.CANCEL).setEnabled(enable);

        if (!enable) {
            reqCompFieldsPanel = null;
        }
    }

    //called by view that manages this widget, passing list of req. component types
    public void setReqCompList(List<ReqComponentTypeInfo> reqComponentTypeInfoList) {

        if (reqComponentTypeInfoList == null || reqComponentTypeInfoList.size() == 0) {
            GWT.log("Missing Requirement Component Types", null);
            Window.alert("Missing Requirement Component Types");
            return;
        }

        //store all requirement components locally
        reqCompTypeInfoList = reqComponentTypeInfoList;

        reqCompTypesList.setListItems(listItemReqCompTypes);
        if (reqCompTypesList.getSelectedItem() != null) {
            reqCompTypesList.deSelectItem(reqCompTypesList.getSelectedItem());
        }

        reqCompTypesList.setEnabled(true);
        setEnableAddRuleButtons(false);
    }

    public void setReqCompConfirmButtonClickCallback(Callback<ReqComponentInfo> callback) {
        reqCompConfirmCallback = callback;
    }
	
    public void setRetrieveCompositionTemplateCallback(Callback<ReqComponentInfo> callback) {
        compositionTemplateCallback = callback;
    }	

    public void setRetrieveFieldsMetadataCallback(Callback<List<String>> callback) {
        fieldsMetadataTemplateCallback = callback;
    }

    public void setNewReqCompSelectedCallbackCallback(Callback<ReqComponentInfo> callback) {
        newReqCompSelectedCallback = callback;
    }
}


