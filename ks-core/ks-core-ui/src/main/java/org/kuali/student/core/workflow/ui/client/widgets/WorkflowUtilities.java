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
package org.kuali.student.core.workflow.ui.client.widgets;

import java.util.ArrayList;
import java.util.List;

import org.kuali.student.common.ui.client.application.Application;
import org.kuali.student.common.ui.client.application.KSAsyncCallback;
import org.kuali.student.common.ui.client.configurable.mvc.LayoutController;
import org.kuali.student.common.ui.client.configurable.mvc.SectionTitle;
import org.kuali.student.common.ui.client.configurable.mvc.sections.InfoMessage;
import org.kuali.student.common.ui.client.event.SaveActionEvent;
import org.kuali.student.common.ui.client.event.SubmitProposalEvent;
import org.kuali.student.common.ui.client.mvc.Callback;
import org.kuali.student.common.ui.client.mvc.DataModel;
import org.kuali.student.common.ui.client.mvc.ModelRequestCallback;
import org.kuali.student.common.ui.client.service.CommentRpcService;
import org.kuali.student.common.ui.client.service.CommentRpcServiceAsync;
import org.kuali.student.common.ui.client.widgets.KSLabel;
import org.kuali.student.common.ui.client.widgets.KSLightBox;
import org.kuali.student.common.ui.client.widgets.KSRichEditor;
import org.kuali.student.common.ui.client.widgets.StylishDropDown;
import org.kuali.student.common.ui.client.widgets.buttongroups.AcknowledgeCancelGroup;
import org.kuali.student.common.ui.client.widgets.buttongroups.ConfirmApprovalCancelGroup;
import org.kuali.student.common.ui.client.widgets.buttongroups.OkGroup;
import org.kuali.student.common.ui.client.widgets.buttongroups.RejectCancelGroup;
import org.kuali.student.common.ui.client.widgets.buttongroups.ButtonEnumerations.AcknowledgeCancelEnum;
import org.kuali.student.common.ui.client.widgets.buttongroups.ButtonEnumerations.ConfirmApprovalCancelEnum;
import org.kuali.student.common.ui.client.widgets.buttongroups.ButtonEnumerations.RejectCancelEnum;
import org.kuali.student.common.ui.client.widgets.buttongroups.ButtonEnumerations.OkEnum;
import org.kuali.student.common.ui.client.widgets.dialog.ConfirmationDialog;
import org.kuali.student.common.ui.client.widgets.field.layout.element.AbbrPanel;
import org.kuali.student.common.ui.client.widgets.menus.KSMenuItemData;
import org.kuali.student.common.ui.client.widgets.notification.KSNotification;
import org.kuali.student.common.ui.client.widgets.notification.KSNotifier;
import org.kuali.student.core.assembly.data.QueryPath;
import org.kuali.student.core.comment.dto.CommentInfo;
import org.kuali.student.core.dto.RichTextInfo;
import org.kuali.student.core.validation.dto.ValidationResultInfo;
import org.kuali.student.core.workflow.ui.client.WorkflowConstants;
import org.kuali.student.core.workflow.ui.client.service.WorkflowRpcService;
import org.kuali.student.core.workflow.ui.client.service.WorkflowRpcServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class WorkflowUtilities{
	DataModel dataModel=null;
	
	boolean loaded=false;
    
	private boolean workflowWidgetsEnabled = true;
	
	private KSMenuItemData wfApproveItem;
	private KSMenuItemData wfDisApproveItem;
	private KSMenuItemData wfAcknowledgeItem;
	private KSMenuItemData wfStartWorkflowItem;
	private KSMenuItemData wfFYIWorkflowItem;
	private KSMenuItemData wfWithdrawItem;
	
	private static String APPROVE_DECISION = "kuali.comment.type.workflowDecisionRationale.approve";
	private static String REJECT_DECISION = "kuali.comment.type.workflowDecisionRationale.reject";
	private static String RETURN_DECISION = "kuali.comment.type.workflowDecisionRationale.return";
	private static String ACK_DECISION = "kuali.comment.type.workflowDecisionRationale.acknowledge";
	private static String FYI_DECISION = "kuali.comment.type.workflowDecisionRationale.fyi";
	
	private List<KSMenuItemData> items = new ArrayList<KSMenuItemData>();
	    
    SaveActionEvent approveSaveActionEvent;
    SaveActionEvent startWorkflowSaveActionEvent;
    
    WorkflowRpcServiceAsync workflowRpcServiceAsync = GWT.create(WorkflowRpcService.class);
    private CommentRpcServiceAsync commentServiceAsync = GWT.create(CommentRpcService.class);
    
    private String modelName;
    private String proposalPath;
    private String proposalId = "";
    private String workflowId;
    private String proposalName="";
        
	private List<StylishDropDown> workflowWidgets = new ArrayList<StylishDropDown>();
	private Callback<Boolean> submitCallback;
	private ConfirmationDialog dialog = new ConfirmationDialog("Submit Proposal", "Are you sure you want to submit the proposal to workflow?", "Submit");
	private AbbrPanel required; 
	private KSLightBox submitSuccessDialog;
	private VerticalPanel dialogPanel;
    
    private KSLabel workflowStatusLabel = new KSLabel("");
    
    private LayoutController parentController;
	
	public WorkflowUtilities(LayoutController parentController, String proposalPath) {
		this.parentController = parentController;
		this.proposalPath = proposalPath;
		setupWFButtons();
		setupDialog();
	}
	
	public void requestAndSetupModel() {
		
		if(null==dataModel){
			//Get the Model from the controller and register a model change handler when the workflow model is updated
			parentController.requestModel(modelName, new ModelRequestCallback<DataModel>() {
			
				@Override
				public void onRequestFail(Throwable cause) {
					Window.alert("Model Request Failed. "+cause.getMessage());
				}

				@Override
				public void onModelReady(DataModel model) {
					//After we get the model update immediately
					dataModel = model;
					updateWorkflow(dataModel);
				}
			});
		}else{
			//If the model has been set don't waste time finding it again and don't register 
			//another change listener, just update
			updateWorkflow(dataModel);
		}
	}
	
	private void setupWFButtons() {
		wfApproveItem = getApproveItem();
		wfDisApproveItem = getDisApproveItem();
		wfAcknowledgeItem = getAcknowledgeItem();
		wfStartWorkflowItem = getStartItem();
		wfFYIWorkflowItem = getFYIWorkflowItem();
		wfWithdrawItem = getWithdrawItem();
	}
	
	private void setupSubmitSuccessDialog(){
		if(submitSuccessDialog==null){
			submitSuccessDialog= new KSLightBox();
			submitSuccessDialog.setSize(580, 400);
			dialogPanel = new VerticalPanel();
			submitSuccessDialog.setWidget(dialogPanel);
			
		}

	}
	
	private void setupDialog(){

		dialog.getConfirmButton().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				dialog.getConfirmButton().setEnabled(false);
				workflowRpcServiceAsync.submitDocumentWithId(workflowId, new KSAsyncCallback<Boolean>(){
					public void handleFailure(Throwable caught) {
						Window.alert("Error starting Proposal workflow");
						dialog.getConfirmButton().setEnabled(true);
					}
					public void onSuccess(Boolean result) {
						if (result){
							updateWorkflow(dataModel);						
							dialog.hide();
							dialog.getConfirmButton().setEnabled(true);
							if(submitCallback != null){
								submitCallback.exec(true);
							}
							//Notify the user that the document was submitted
							KSNotifier.add(new KSNotification("Proposal has been routed to workflow", false));
						} else {
							Window.alert("Error starting Proposal workflow");
						}
					}
				});
				
			}
		});
	}
	
	public Widget getWorkflowActionsWidget(){
		//InfoMessage infoContainer = new InfoMessage();
		StylishDropDown workflowActionsDropDown = new StylishDropDown("Workflow Actions");
		workflowActionsDropDown.makeAButtonWhenOneItem(true);
		workflowActionsDropDown.addStyleName("KS-Workflow-DropDown");
		workflowWidgets.add(workflowActionsDropDown);
		workflowActionsDropDown.setVisible(false);
		refresh();
/*		infoContainer.add(workflowActionsDropDown);
		infoContainer.showWarnStyling(false);
		infoContainer.setVisible(true);*/
		//workflowActionsDropDown
		return workflowActionsDropDown;
	}
	
	public void enableWorkflowActionsWidgets(boolean enable){
		workflowWidgetsEnabled = enable;
		for(StylishDropDown widget: workflowWidgets){	
			widget.setEnabled(enable);
		}
	}
	
	public void doValidationCheck(Callback<List<ValidationResultInfo>> callback){
		dataModel.validateNextState(callback);
	}
	
	public KSLabel getWorkflowStatusLabel(){
		return workflowStatusLabel;
	}
	
	private void updateWorkflowIdFromModel(final DataModel model){
		if(model!=null){
			String modelProposalId = model.get(QueryPath.parse(proposalPath + "/id"));
			
			//If proposalId in model has been set or changed, get new workflowId and update workfow widget
			if (modelProposalId != null && !modelProposalId.isEmpty() && !modelProposalId.equals(proposalId)){
				proposalId = modelProposalId;
				workflowId = model.get(QueryPath.parse(proposalPath + "/workflowId"));
				proposalName = model.get(QueryPath.parse(proposalPath + "/name"));
				updateWorkflow(model);
			}
		}
	}

	private void updateWorkflow(DataModel model){
		updateWorkflowIdFromModel(model);
		
		if (workflowId != null && !workflowId.isEmpty()){
			//Determine which workflow actions are displayed in the drop down
			workflowRpcServiceAsync.getActionsRequested(workflowId, new KSAsyncCallback<String>(){
		
				public void onSuccess(String result) {
					items.clear();
					if(result.contains("S")){
						items.add(wfStartWorkflowItem);
					}
					if(result.contains("W")){
						items.add(wfWithdrawItem);
					}
					if(result.contains("A")){
	
						items.add(wfApproveItem);
						items.add(wfDisApproveItem);
	
					}
					if(result.contains("K")){
						items.add(wfAcknowledgeItem);
					}
					
					if(result.contains("F")){
						items.add(wfFYIWorkflowItem);
					}
					for(StylishDropDown widget: workflowWidgets){
						
						widget.setItems(items);
						widget.setEnabled(workflowWidgetsEnabled);
						if(items.isEmpty()){
							widget.setVisible(false);
						}
						else{
							widget.setVisible(true);
						}
					}
				}
			});
		
			workflowRpcServiceAsync.getDocumentStatus(workflowId, new KSAsyncCallback<String>(){
				@Override
				public void handleFailure(Throwable caught) {
					workflowStatusLabel.setText("Status: Unknown");
				}

				@Override
				public void onSuccess(String result) {
					setWorkflowStatus(result);
				}						
			});
		} else {
			workflowStatusLabel.setText("Status: Draft");
		}			
	}
	
	private KSMenuItemData getFYIWorkflowItem() {
		KSMenuItemData wfFYIWorkflowItem;
		final KSRichEditor rationaleEditor = new KSRichEditor();
		wfFYIWorkflowItem = new KSMenuItemData("FYI Proposal", new ClickHandler(){
	        public void onClick(ClickEvent event) {	   
	        	addRationale(rationaleEditor,FYI_DECISION);
				workflowRpcServiceAsync.fyiDocumentWithId(workflowId, new KSAsyncCallback<Boolean>(){
					public void handleFailure(Throwable caught) {
						Window.alert("Error FYIing Proposal");
					}
					public void onSuccess(
							Boolean result) {
						if(result){
							updateWorkflow(dataModel);
							if(submitCallback != null){
								submitCallback.exec(true);
							}
							//Notify the user that the document was FYIed
							KSNotifier.add(new KSNotification("Proposal was FYIed", false));
						}else{
							Window.alert("Error FYIing Proposal");
						}
					}
					
				});
	        }        
	    });
		return wfFYIWorkflowItem;
	}

	private KSMenuItemData getAcknowledgeItem() {
		KSMenuItemData wfAcknowledgeItem;
		wfAcknowledgeItem = new KSMenuItemData("Acknowledge Proposal", new ClickHandler(){
	        public void onClick(ClickEvent event) {
	        	setupSubmitSuccessDialog();
				final KSRichEditor rationaleEditor = new KSRichEditor();
				AcknowledgeCancelGroup approvalButton = new AcknowledgeCancelGroup(new Callback<AcknowledgeCancelEnum>(){

					@Override
					public void exec(AcknowledgeCancelEnum result) {
						if(!result.name().equals("CANCEL")){
							addRationale(rationaleEditor,ACK_DECISION);
							workflowRpcServiceAsync.acknowledgeDocumentWithId(workflowId, new KSAsyncCallback<Boolean>(){
								public void handleFailure(Throwable caught) {
									submitSuccessDialog.hide();
									Window.alert("Error acknowledging Proposal");
								}
								public void onSuccess(Boolean result) {
									submitSuccessDialog.hide();
									if(result){
										updateWorkflow(dataModel);
										if(submitCallback != null){
											submitCallback.exec(true);
										}
										//Notify the user that the document was acknowledged
										KSNotifier.add(new KSNotification("Proposal was acknowledged", false));
									}else{
										Window.alert("Error acknowledging Proposal");
									}
								}
							});
						}
						else{
							submitSuccessDialog.hide();
						}
					}
				});
				
				SectionTitle headerTitle = SectionTitle.generateH3Title("Acknowledge Proposal");
				SectionTitle dialogLabel = SectionTitle.generateH4Title("You are acknowledging the " + proposalName +" proposal");
				SectionTitle fieldLabel = SectionTitle.generateH4Title("Decision Rationale");
				rationaleEditor.addStyleName("KS-Comment-Create-Editor");
				dialogPanel.clear();
				dialogPanel.add(headerTitle);	
				dialogPanel.add(dialogLabel);
				dialogPanel.add(fieldLabel);
				dialogPanel.add(rationaleEditor);
				dialogPanel.add(approvalButton);
				submitSuccessDialog.show();
	        }        
	    });
		return wfAcknowledgeItem;
	}

	private KSMenuItemData getDisApproveItem() {
		KSMenuItemData wfDisApproveItem;
		wfDisApproveItem = new KSMenuItemData("Reject Proposal", new ClickHandler(){
	        public void onClick(ClickEvent event) {   
	        	setupSubmitSuccessDialog();
				final KSRichEditor rationaleEditor = new KSRichEditor();
				RejectCancelGroup approvalButton = new RejectCancelGroup(new Callback<RejectCancelEnum>(){

					@Override
					public void exec(RejectCancelEnum result) {
						if(!result.name().equals("CANCEL")){
							if(rationaleEditor.getText().trim().equals("")){
								required.setText("Please enter the decision rationale");
							}
							else{
								addRationale(rationaleEditor,REJECT_DECISION);
								workflowRpcServiceAsync.disapproveDocumentWithId(workflowId, new KSAsyncCallback<Boolean>(){
									public void handleFailure(Throwable caught) {
										submitSuccessDialog.hide();
										Window.alert("Error rejecting Proposal");
									}
									public void onSuccess(Boolean result) {
										submitSuccessDialog.hide();
										if(submitCallback != null){
											submitCallback.exec(result);
										}
										if(result){
											KSNotifier.add(new KSNotification("Proposal was rejected", false));
											updateWorkflow(dataModel);
										}else{
											Window.alert("Error rejecting Proposal");
										}
									}
									
								});
							}

					}
					else{
						submitSuccessDialog.hide();
					}
					}
				});
				SectionTitle headerTitle = SectionTitle.generateH3Title("Reject Proposal");
				SectionTitle dialogLabel = SectionTitle.generateH4Title("You are rejecting the " + proposalName +" proposal");
				SectionTitle fieldLabel = SectionTitle.generateH4Title("Decision Rationale");
				required = new AbbrPanel("Required", "ks-form-module-elements-required", " * ");
				required.setVisible(true);
//				final KSRichEditor rationaleEditor = new KSRichEditor();
//				rationaleEditor.addStyleName("ks-textarea-width");
//				rationaleEditor.addStyleName("ks-textarea-large-height");
				rationaleEditor.addStyleName("KS-Comment-Create-Editor");
				dialogPanel.clear();
				dialogPanel.add(headerTitle);	
				dialogPanel.add(dialogLabel);
				dialogPanel.add(fieldLabel);
				dialogPanel.add(required);
				dialogPanel.add(rationaleEditor);
				dialogPanel.add(approvalButton);
				submitSuccessDialog.setWidget(dialogPanel);
				submitSuccessDialog.show();
	        }        
	    });
		return wfDisApproveItem;
	}

	private KSMenuItemData getApproveItem() {
		KSMenuItemData wfApproveItem;

		wfApproveItem= new KSMenuItemData("Approve Proposal", new ClickHandler(){
			public void onClick(ClickEvent event) {
				setupSubmitSuccessDialog();
				final KSRichEditor rationaleEditor = new KSRichEditor();
				ConfirmApprovalCancelGroup approvalButton = new ConfirmApprovalCancelGroup(new Callback<ConfirmApprovalCancelEnum>(){

					@Override
					public void exec(ConfirmApprovalCancelEnum result) {
						if(!result.name().equals("CANCEL")){
							String temp = rationaleEditor.getText().trim();
							String temp2 = temp.trim();
							if(rationaleEditor.getText().trim().equals("")){
								required.setText("Please enter the decision rationale");
							}
							else{
								addRationale(rationaleEditor,APPROVE_DECISION);
								
								workflowRpcServiceAsync.approveDocumentWithId(workflowId, new KSAsyncCallback<Boolean>(){
								public void handleFailure(Throwable caught) {
									submitSuccessDialog.hide();
									Window.alert("Error approving Proposal");
								}
								public void onSuccess(Boolean result) {
									submitSuccessDialog.hide();
									if (result){
										updateWorkflow(dataModel);
										if(submitCallback != null){
											submitCallback.exec(result);
										}
										//Notify the user that the document was approved
										KSNotifier.add(new KSNotification("Proposal was approved", false));
									} else {
										Window.alert("Error approving Proposal");
									}
								}
							});
							}

					}
					else{
						submitSuccessDialog.hide();
					}
					}
				});
				
				SectionTitle headerTitle = SectionTitle.generateH3Title("Approve Proposal");
				SectionTitle dialogLabel = SectionTitle.generateH4Title("You are approving the " + proposalName +" proposal");
				SectionTitle fieldLabel = SectionTitle.generateH4Title("Decision Rationale");
				required = new AbbrPanel("Required", "ks-form-module-elements-required", " * ");
				required.setVisible(true);
				rationaleEditor.addStyleName("KS-Comment-Create-Editor");
				dialogPanel.clear();
				dialogPanel.add(headerTitle);	
				dialogPanel.add(dialogLabel);
				dialogPanel.add(fieldLabel);
				dialogPanel.add(required);
				dialogPanel.add(rationaleEditor);
				dialogPanel.add(approvalButton);
				dialogPanel.setSize("580px", "400px");
//				submitSuccessDialog.setWidget(dialogPanel);
				submitSuccessDialog.show();
			}        
		});
		return wfApproveItem;
	}

	private KSMenuItemData getWithdrawItem() {
		KSMenuItemData wfWithdrawItem;
    	wfWithdrawItem = new KSMenuItemData("Withdraw Proposal", new ClickHandler(){
	        public void onClick(ClickEvent event) {
	        	
				workflowRpcServiceAsync.withdrawDocumentWithId(workflowId, new KSAsyncCallback<Boolean>(){
					public void handleFailure(Throwable caught) {
						GWT.log("Error Withdrawing Proposal", caught);
						Window.alert("Error Withdrawing Proposal");
					}
					public void onSuccess(Boolean result) {
						if(result){
							updateWorkflow(dataModel);
							for(StylishDropDown widget: workflowWidgets){
								List<KSMenuItemData> items = new ArrayList<KSMenuItemData>();
								widget.setItems(items);
							}
							if(submitCallback != null){
								submitCallback.exec(result);
							}
							//Notify the user that the document was Withdrawn
							KSNotifier.add(new KSNotification("Proposal was Withdrawn", false));
						}else{
							Window.alert("Error Withdrawing Proposal");
						}
					}
				});
	        }        
    	});
		return wfWithdrawItem;
	}

	private void addRationale(KSRichEditor rationaleEditor, String rationaleType){
		CommentInfo newDecisionRationale = new CommentInfo();
		RichTextInfo text = new RichTextInfo();
        text.setFormatted(rationaleEditor.getHTML());
        text.setPlain(rationaleEditor.getText());
		newDecisionRationale.setCommentText(text);
		newDecisionRationale.setType(rationaleType);
		
		try{
		commentServiceAsync.addComment(proposalId, "referenceType.clu.proposal", newDecisionRationale,new KSAsyncCallback<CommentInfo>(){

		@Override
		public void handleFailure(Throwable caught) {
			GWT.log("Add Comment Failed", caught);
		}

		@Override
		public void onSuccess(CommentInfo result) {
			System.out.println("Rationale Added successfully");
		}
		});
		} catch (Exception e) {
			GWT.log("Add Comment Failed", e);
		}
	}
	private KSMenuItemData getStartItem() {
		KSMenuItemData wfStartWorkflowItem;
    	wfStartWorkflowItem = new KSMenuItemData("Submit Proposal", new ClickHandler(){
    		public void onClick(ClickEvent event) {
                //Make sure the entire data model is valid before submit
				dataModel.validateNextState(new Callback<List<ValidationResultInfo>>() {
                    @Override
                    public void exec(List<ValidationResultInfo> result) {
                    	
                    	boolean isValid = ((LayoutController)parentController).isValid(result, false);
                    	if(isValid){
            				dialog.show();
                    	}
                    	else{
                    		Window.alert("Unable to submit to workflow.  Please check sections for missing fields.");
                    	}                            
                    }
                });
    		}

    	});
		return wfStartWorkflowItem;
	}
		
	private void setWorkflowStatus(String statusCd){
		String statusTranslation = "";
		if (WorkflowConstants.ROUTE_HEADER_SAVED_CD.equals(statusCd)){
			statusTranslation = getLabel(WorkflowConstants.ROUTE_HEADER_SAVED_LABEL_KEY);
		} else  if (WorkflowConstants.ROUTE_HEADER_INITIATED_CD.equals(statusCd)){
			statusTranslation = getLabel(WorkflowConstants.ROUTE_HEADER_INITIATED_LABEL_KEY);
		} else if (WorkflowConstants.ROUTE_HEADER_ENROUTE_CD.equals(statusCd)){
			statusTranslation = getLabel(WorkflowConstants.ROUTE_HEADER_ENROUTE_LABEL_KEY);
		} else if (WorkflowConstants.ROUTE_HEADER_APPROVED_CD.equals(statusCd)){
			statusTranslation = getLabel(WorkflowConstants.ROUTE_HEADER_APPROVED_LABEL_KEY);
		} else if (WorkflowConstants.ROUTE_HEADER_CANCEL_CD.equals(statusCd)){
			statusTranslation = getLabel(WorkflowConstants.ROUTE_HEADER_CANCEL_LABEL_KEY);
		} else if (WorkflowConstants.ROUTE_HEADER_EXCEPTION_CD.equals(statusCd)){
			statusTranslation = getLabel(WorkflowConstants.ROUTE_HEADER_EXCEPTION_LABEL_KEY);
		} else if (WorkflowConstants.ROUTE_HEADER_DISAPPROVED_CD.equals(statusCd)){
			statusTranslation = getLabel(WorkflowConstants.ROUTE_HEADER_DISAPPROVED_LABEL_KEY);
		} else if (WorkflowConstants.ROUTE_HEADER_FINAL_CD.equals(statusCd)){
			statusTranslation = getLabel(WorkflowConstants.ROUTE_HEADER_FINAL_LABEL_KEY);
		} else if (WorkflowConstants.ROUTE_HEADER_DISAPPROVE_CANCEL_CD.equals(statusCd)){
			statusTranslation = getLabel(WorkflowConstants.ROUTE_HEADER_DISAPPROVE_CANCEL_LABEL_KEY);
		} else if (WorkflowConstants.ROUTE_HEADER_PROCESSED_CD.equals(statusCd)){
			statusTranslation = getLabel(WorkflowConstants.ROUTE_HEADER_PROCESSED_LABEL_KEY);
		} else {
			statusTranslation = statusCd;
		}
		
		workflowStatusLabel.setText("Status: " + statusTranslation);	
	}
	
	/**
	 * Use to set the modelName to use when this widget requests the data model.
	 * 
	 * @param modelName
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	/**
	 * Use to set the data model path to retrieve the propsal data to use for this workflow. 
	 * @param idPath
	 */
	public void setProposalPath(String proposalPath) {
		this.proposalPath = proposalPath;
	}
		
	public void setWorkflowRpcService(WorkflowRpcServiceAsync workflowRpcServiceAsync){
		this.workflowRpcServiceAsync = workflowRpcServiceAsync;
	}

	public void refresh(){
		updateWorkflow(dataModel);
	}
	
    private String getLabel(String labelKey) {
        return Application.getApplicationContext().getUILabel("common", null, null, labelKey);
    }
    
    public void getDataIdFromWorkflowId(String workflowId, AsyncCallback<String> callback){
    	workflowRpcServiceAsync.getDataIdFromWorkflowId(workflowId, callback);
    }

	public void addSubmitCallback(Callback<Boolean> callback) {
		this.submitCallback = callback;
		
	}
}
