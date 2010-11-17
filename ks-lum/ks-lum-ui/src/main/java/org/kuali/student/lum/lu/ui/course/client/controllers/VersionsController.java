package org.kuali.student.lum.lu.ui.course.client.controllers;

import java.util.ArrayList;
import java.util.List;

import org.kuali.student.common.ui.client.application.Application;
import org.kuali.student.common.ui.client.application.KSAsyncCallback;
import org.kuali.student.common.ui.client.application.ViewContext;
import org.kuali.student.common.ui.client.configurable.mvc.layouts.BasicLayoutWithContentHeader;
import org.kuali.student.common.ui.client.configurable.mvc.views.VerticalSectionView;
import org.kuali.student.common.ui.client.mvc.*;
import org.kuali.student.common.ui.client.widgets.KSButton;
import org.kuali.student.common.ui.client.widgets.KSButtonAbstract.ButtonStyle;
import org.kuali.student.common.ui.client.widgets.progress.BlockingTask;
import org.kuali.student.common.ui.client.widgets.progress.KSBlockingProgressIndicator;
import org.kuali.student.common.ui.shared.IdAttributes.IdType;
import org.kuali.student.core.assembly.data.Data;
import org.kuali.student.core.assembly.data.Metadata;
import org.kuali.student.core.assembly.data.QueryPath;
import org.kuali.student.core.rice.StudentIdentityConstants;
import org.kuali.student.core.statement.dto.StatementTypeInfo;
import org.kuali.student.lum.common.client.lu.LUUIConstants;
import org.kuali.student.lum.lu.assembly.data.client.refactorme.orch.CreditCourseConstants;
import org.kuali.student.lum.lu.ui.course.client.configuration.CourseSummaryConfigurer;
import org.kuali.student.lum.lu.ui.course.client.requirements.CourseRequirementsDataModel;
import org.kuali.student.lum.lu.ui.course.client.service.CourseRpcService;
import org.kuali.student.lum.lu.ui.course.client.service.CourseRpcServiceAsync;
import org.kuali.student.lum.lu.ui.course.client.views.SelectVersionsView;
import org.kuali.student.lum.lu.ui.course.client.views.ShowVersionView;
import org.kuali.student.lum.lu.ui.course.client.widgets.CourseWorkflowActionList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

public class VersionsController extends BasicLayoutWithContentHeader{
	
	public static enum Views{VERSION_SELECT, VERSION_VIEW, VERSION_COMPARE}
	
	private SelectVersionsView select = new SelectVersionsView(this, "", Views.VERSION_SELECT);
	private ShowVersionView view;
	private VerticalSectionView compare;
    private static final String MSG_GROUP = "course";
    private String type = "course";
    private String state = "draft";
    private String groupName = LUUIConstants.COURSE_GROUP_NAME;
	CourseSummaryConfigurer summaryConfigurer;
	CourseRpcServiceAsync rpcServiceAsync = GWT.create(CourseRpcService.class);
	DataModelDefinition definition;
	
	private DataModel cluModel1;
	private DataModel cluModel2;
	
	private String lastId1 = "";
	private String lastId2 = "";
	private KSButton versionHistoryButton = new KSButton("Version History", ButtonStyle.ANCHOR_LARGE_CENTERED, new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
			VersionsController.this.showDefaultView(Controller.NO_OP_CALLBACK);
		}
	});
	
	private boolean initialized = false;
	private String versionIndId = "";
	private String currentVersionId = "";
	private BlockingTask loadDataTask = new BlockingTask("Retrieving Data....");
	
	private List<CourseWorkflowActionList> actionDropDownWidgets = new ArrayList<CourseWorkflowActionList>();
	
	public VersionsController(Enum<?> viewType) {
		super(VersionsController.class.toString());
		this.addView(select);
        this.setDefaultView(Views.VERSION_SELECT);
        this.setName("Versions");
        this.setViewEnum(viewType);
        this.getHeader().addWidget(versionHistoryButton);
        this.viewContainer.addStyleName("standard-content-padding");
        initialize();
    }	

	public void setVersionIndId(String versionIndId) {
		this.versionIndId = versionIndId;
	}
	
	private void initialize() {
        super.setDefaultModelId("Model");
        super.registerModel("Model", new ModelProvider<DataModel>() {

            @Override
            public void requestModel(final ModelRequestCallback<DataModel> callback) {
                if(getViewContext().getId() != null && !getViewContext().getId().isEmpty()){
            		getCourseFromCluId(getViewContext().getId(), 1, callback, true);
                }
                else{
                	callback.onModelReady(null);
                }
            }
        });
        
        super.registerModel("ComparisonModel", new ModelProvider<DataModel>() {
            @Override
            public void requestModel(final ModelRequestCallback<DataModel> callback) {
            	if(getViewContext().getAttribute("docId2") != null && !getViewContext().getAttribute("docId2").isEmpty()){
            		getCourseFromCluId(getViewContext().getAttribute("docId2"), 2, callback, false);	
            	}
            	else{
            		callback.onModelReady(null);
            	}
            }
        });
	}
	 
	@SuppressWarnings("unchecked")    
	private void getCourseFromCluId(final String courseId, final int modelNum, final ModelRequestCallback callback, final boolean id1Model){
		KSBlockingProgressIndicator.addTask(loadDataTask);
	
	    rpcServiceAsync.getData(courseId, new KSAsyncCallback<Data>(){
	
	        @Override
	        public void handleFailure(Throwable caught) {
	            Window.alert("Error loading Course: "+caught.getMessage());
	            callback.onRequestFail(caught);
	            KSBlockingProgressIndicator.removeTask(loadDataTask);
	        }
	
	        @Override
	        public void onSuccess(Data result) {
	        	if(modelNum == 1){
	        		cluModel1 = new DataModel();
	        		cluModel1.setDefinition(definition);
	        		cluModel1.setRoot(result);
	        		if(courseId.equals(currentVersionId)){
	        			String name = "Version " + cluModel1.get("versionInfo/sequenceNumber") + " (current version)";
	        			cluModel1.setModelName(name);
	        			view.setName(name);
	        			view.showWarningMessage(false);
	        		}
	        		else{
	        			String name = "Version " + cluModel1.get("versionInfo/sequenceNumber");
	        			cluModel1.setModelName(name);
	        			view.setName(name);
	        			view.showWarningMessage(true);
	        		}
	        		updateCourseActionItems(cluModel1);
	 	            callback.onModelReady(cluModel1);
	 	            lastId1 = courseId;
	        	}
	        	else{
	        		cluModel2 = new DataModel();
	        		cluModel2.setDefinition(definition);
	        		cluModel2.setRoot(result);
	        		if(courseId.equals(currentVersionId)){
	        			cluModel2.setModelName("Version " + cluModel2.get("versionInfo/sequenceNumber") + " (current version)");
	        		}
	        		else{
	        			cluModel2.setModelName("Version " + cluModel2.get("versionInfo/sequenceNumber"));
	        		}
	 	            callback.onModelReady(cluModel2);
	 	            lastId2 = courseId;
	        	}
	            
	            KSBlockingProgressIndicator.removeTask(loadDataTask);
	        }
	
	    });
	}
	
    @Override
    public void showDefaultView(final Callback<Boolean> onReadyCallback) {
        init(new Callback<Boolean>() {

            @Override
            public void exec(Boolean result) {
                if (result) {
                	VersionsController.super.showDefaultView(onReadyCallback);
                } else {
                    onReadyCallback.exec(false);
                }
            }
        });
    }
    
    @Override
    public void beforeShow(Callback<Boolean> onReadyCallback) {
    	versionHistoryButton.setVisible(false);
    	this.getHeader().showPrint(false);
    	showDefaultView(onReadyCallback);
    }
    
    private void init(final Callback<Boolean> onReadyCallback) {

        if (initialized) {
            onReadyCallback.exec(true);
        } else {
        	KSBlockingProgressIndicator.addTask(loadDataTask);
    		
        	rpcServiceAsync.getMetadata("", null, new KSAsyncCallback<Metadata>(){

	        	public void handleFailure(Throwable caught) {
	        		initialized = false;
                	onReadyCallback.exec(false);
                	KSBlockingProgressIndicator.removeTask(loadDataTask);
                    throw new RuntimeException("Failed to get model definition.", caught);
                }

                public void onSuccess(Metadata result) {
                	definition = new DataModelDefinition(result);
                    KSBlockingProgressIndicator.removeTask(loadDataTask);
                    configureScreens(onReadyCallback);
                }
	          });
            
        }
    }

    private void configureScreens(final Callback<Boolean> onReadyCallback) {
        CourseRequirementsDataModel.getStatementTypes(new Callback<List<StatementTypeInfo>>() {

            @Override
            public void exec(List<StatementTypeInfo> stmtTypes) {
                List<StatementTypeInfo> stmtTypesOut = new ArrayList<StatementTypeInfo>();
                if (stmtTypes != null) {
                    for (StatementTypeInfo stmtType : stmtTypes) {
                        if (stmtType.getId().contains("kuali.statement.type.course.enrollmentEligibility") ||
                            stmtType.getId().contains("kuali.statement.type.course.creditConstraints")) {
                            continue;
                        }
                        stmtTypesOut.add(stmtType);
                    }
                }

                summaryConfigurer = new CourseSummaryConfigurer(type, state, groupName, definition, stmtTypesOut, VersionsController.this, "Model");
                view = new ShowVersionView(Views.VERSION_VIEW, "Version", "Model", VersionsController.this, stmtTypesOut);
                compare = summaryConfigurer.generateCourseSummarySection();
                compare.setLayoutController(VersionsController.this);
                compare.setSectionTitle("Compare Versions");
                compare.setName("Compare Versions");
                compare.setViewEnum(Views.VERSION_COMPARE);
                VersionsController.this.addView(view);
                VersionsController.this.addView(compare);
                initialized = true;
                onReadyCallback.exec(true);
            }
        });
    }
    
    public Widget generateActionDropDown(){
    	CourseWorkflowActionList actionList = new CourseWorkflowActionList(this.getMessage("cluActionsLabel"));

    	actionDropDownWidgets.add(actionList);
        
    	return actionList;
    }
    
    private void updateCourseActionItems(DataModel cluModel) {
    	ViewContext viewContext = new ViewContext();
		
    	if(getViewContext() != null && getViewContext().getId() != null && !getViewContext().getId().isEmpty()){
			viewContext.setId((String)cluModel.get(CreditCourseConstants.VERSION_INFO + QueryPath.getPathSeparator() + CreditCourseConstants.VERSION_IND_ID));
            viewContext.setIdType(IdType.COPY_OF_OBJECT_ID);
            viewContext.setAttribute(StudentIdentityConstants.DOCUMENT_TYPE_NAME, "kuali.proposal.type.course.modify");
        }
    	
    	String cluState = cluModel.get("state").toString();    	
    	
		for(CourseWorkflowActionList widget: actionDropDownWidgets){
			widget.init(viewContext, "/HOME/CURRICULUM_HOME/COURSE_PROPOSAL", CourseWorkflowActionList.isCurrentVersion(cluModel));    	
			widget.updateCourseActionItems(cluState);
			widget.setEnabled(true);
			if(widget.isEmpty()) {
				setVisible(false);
			}
			else{
				setVisible(true);
			}
		}
    }
    
    public DataModelDefinition getDefinition(){
    	return definition;
    }

	public void setCurrentVersionId(String id) {
		this.currentVersionId = id;
	}

	public String getCurrentVersionId() {
		return currentVersionId;
	}
	
	public String getVersionIndId() {
		return versionIndId;
	}
	
    public String getMessage(String courseMessageKey) {
    	String msg = Application.getApplicationContext().getMessage(MSG_GROUP, courseMessageKey);
    	if (msg == null) {
    		msg = courseMessageKey;
    	}
    	return msg;
    }
	
	
	@Override
	public <V extends Enum<?>> void showView(V viewType, Callback<Boolean> onReadyCallback) {
		if(viewType != Views.VERSION_SELECT){
			versionHistoryButton.setVisible(true);
			this.getHeader().showPrint(true);
		}
		else{
			versionHistoryButton.setVisible(false);
			this.getHeader().showPrint(false);
		}
		super.showView(viewType, onReadyCallback);
	}



	public void setCurrentTitle(String currentTitle) {
    	this.getHeader().setTitle(currentTitle);
	}
}
