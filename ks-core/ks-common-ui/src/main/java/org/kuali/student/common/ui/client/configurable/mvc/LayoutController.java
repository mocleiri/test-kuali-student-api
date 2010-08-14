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

package org.kuali.student.common.ui.client.configurable.mvc;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.kuali.student.common.ui.client.configurable.mvc.layouts.ViewLayoutController;
import org.kuali.student.common.ui.client.configurable.mvc.sections.Section;
import org.kuali.student.common.ui.client.configurable.mvc.views.SectionView;
import org.kuali.student.common.ui.client.event.*;
import org.kuali.student.common.ui.client.mvc.*;
import org.kuali.student.common.ui.client.widgets.KSButton;
import org.kuali.student.common.ui.client.widgets.KSLightBox;
import org.kuali.student.core.validation.dto.ValidationResultInfo;
import org.kuali.student.core.validation.dto.ValidationResultInfo.ErrorLevel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class LayoutController extends Controller implements ViewLayoutController, View {

	protected Map<Enum<?>, View> viewMap = new LinkedHashMap<Enum<?>, View>();
	protected Map<String, Enum<?>> viewEnumMap = new HashMap<String, Enum<?>>();
	protected Enum<?> defaultView;

	protected String name;
	protected Enum<?> viewType;

    protected View startPopupView;
    protected KSLightBox startViewWindow;

    public LayoutController(String controllerId){
        super(controllerId);
        //Global section update Event handling
		addApplicationEventHandler(SectionUpdateEvent.TYPE, new SectionUpdateHandler(){

			@Override
			public void onSectionUpdate(final SectionUpdateEvent event) {
				LayoutController.this.requestModel(new ModelRequestCallback<DataModel>(){

					@Override
					public void onRequestFail(Throwable cause) {
						GWT.log("Unable to retrieve model for section update", cause);
					}

					@Override
					public void onModelReady(DataModel model) {
						event.getSection().updateModel(model);
						event.getSection().updateWidgetData(model);

					}
				});

			}
		});
		//Global validation Event handling
        addApplicationEventHandler(ValidateRequestEvent.TYPE, new ValidateRequestHandler() {

            @Override
            public void onValidateRequest(final ValidateRequestEvent event) {
            	FieldDescriptor originatingField = event.getFieldDescriptor();
            	String modelId = null;
            	if (originatingField != null) {
            		modelId = originatingField.getModelId();
            	}
            	if (modelId == null) {
            		requestModel(new ModelRequestCallback<DataModel>() {
            			@Override
            			public void onModelReady(DataModel model) {
            				validate(model, event);
            			}

            			@Override
            			public void onRequestFail(Throwable cause) {
            				GWT.log("Unable to retrieve model for validation", cause);
            			}

            		});
            	} else {
            		requestModel(modelId, new ModelRequestCallback<DataModel>() {
            			@Override
            			public void onModelReady(DataModel model) {
            				validate(model, event);
            			}

            			@Override
            			public void onRequestFail(Throwable cause) {
            				GWT.log("Unable to retrieve model for validation", cause);
            			}

            		});
            	}
            }

        });
    }

    private void validate(DataModel model, final ValidateRequestEvent event) {
    	if(event.validateSingleField()){
    		model.validateField(event.getFieldDescriptor(), new Callback<List<ValidationResultInfo>>() {
                @Override
                public void exec(List<ValidationResultInfo> result) {
                	if(event.getFieldDescriptor() != null){
                		event.getFieldDescriptor().getFieldElement().clearValidationPanel();
                	}
                	isValid(result, true, false);
                }
    		});
    	}
    	else{
            model.validate(new Callback<List<ValidationResultInfo>>() {
                @Override
                public void exec(List<ValidationResultInfo> result) {
                    isValid(result, false, true);
                }
            });
    	}
    }

    public ErrorLevel checkForErrors(List<ValidationResultInfo> list){
		ErrorLevel errorLevel = ErrorLevel.OK;

		for(ValidationResultInfo vr: list){
			if(vr.getErrorLevel().getLevel() > errorLevel.getLevel()){
				errorLevel = vr.getErrorLevel();
			}
			if(errorLevel.equals(ErrorLevel.ERROR)){
				break;
			}
		}

    	return errorLevel;

    }

    public static LayoutController findParentLayout(Widget w){
        LayoutController result = null;
        while (true) {
            if (w == null) {
                break;
            } else if (w instanceof HasLayoutController) {
            	result = ((HasLayoutController)w).getLayoutController();
            	if (result != null) {
            		break;
            	}
            } else if (w instanceof LayoutController) {
                result = (LayoutController) w;
                break;
            }
            w = w.getParent();

        }
        return result;
    }

	public void addStartViewPopup(final View view){
	    startPopupView = view;
	    if(startViewWindow == null){
	    	startViewWindow = new KSLightBox();
	    }

	    HorizontalPanel buttonPanel = new HorizontalPanel();

	    FlowPanel panel = new FlowPanel();
	    panel.add(view.asWidget());
	    buttonPanel.add(new KSButton("Save",new ClickHandler(){
            public void onClick(ClickEvent event) {
                view.updateModel();
                SaveActionEvent saveActionEvent = new SaveActionEvent();

                saveActionEvent.setActionCompleteCallback(new ActionCompleteCallback(){
                    public void onActionComplete(ActionEvent action) {
                        startViewWindow.hide();
                    }
                });

                fireApplicationEvent(saveActionEvent);
            }
	    }));
	    buttonPanel.add(new KSButton("Cancel", new ClickHandler(){
            public void onClick(ClickEvent event) {
                startViewWindow.hide();
            }
	    }));

	    panel.add(buttonPanel);
	    //TODO setController should be a method on all Views
	    if(view instanceof SectionView){
	    	((SectionView) view).setController(this);
	    }
	    startViewWindow.setWidget(panel);
	}

    public boolean isStartViewShowing(){
        if(startViewWindow == null){
            return false;
        }
    	return startViewWindow.isShowing();
    }

    public View getStartPopupView(){
        return startPopupView;
    }

    public void showStartPopup(final Callback<Boolean> onReadyCallback){
        startPopupView.beforeShow(new Callback<Boolean>() {
			@Override
			public void exec(Boolean result) {
				if (result) {
					startViewWindow.show();
				}
				onReadyCallback.exec(result);
			}
        });
    }


    /*New methods*/

	public void addView(View view){
		viewMap.put(view.getViewEnum(), view);
		viewEnumMap.put(view.getViewEnum().toString(), view.getViewEnum());
		if(view instanceof SectionView){
			((SectionView) view).setController(this);
		}
		else if(view instanceof ToolView){
			((ToolView) view).setController(this);
		}
	}

	public <V extends Enum<?>> void setDefaultView(V viewType){
		this.defaultView = viewType;
	}

	public abstract void updateModel();

	public void updateModelFromView(Enum<?> viewType){
		View v = viewMap.get(viewType);
		if(v != null){
			v.updateModel();
		}
	}

	public void updateModelFromCurrentView(){
		this.getCurrentView().updateModel();
	}


	@Override
	public <V extends Enum<?>> View getView(V viewType) {
		return viewMap.get(viewType);
	}

	@Override
	public Enum<?> getViewEnumValue(String enumValue) {
		return viewEnumMap.get(enumValue);
	}

	//TODO remove this method from controller hierarchy?  its not used
	@Override
	public Class<? extends Enum<?>> getViewsEnum() {
		return null;
	}

	@Override
	public void showDefaultView(Callback<Boolean> onReadyCallback) {
		if(!viewMap.isEmpty()){
			if(defaultView == null){
				showView(viewMap.entrySet().iterator().next().getKey(), onReadyCallback);
			}
			else{
				showView(defaultView, onReadyCallback);
			}
		}

	}

	/**
 	 * Check to see if current/all section(s) is valid (ie. does not contain any errors)
 	 *
	 * @param validationResults List of validation results for the layouts model.
	 * @param checkCurrentSectionOnly true if errors should be checked on current section only, false if all sections should be checked
	 * @return true if the specified sections (all or current) has any validation errors
	 */
	public boolean isValid(List<ValidationResultInfo> validationResults, boolean checkCurrentSectionOnly){
		return isValid(validationResults, checkCurrentSectionOnly, true);
	}

	public boolean isValid(List<ValidationResultInfo> validationResults, boolean checkCurrentSectionOnly, boolean allFields){
		boolean isValid = true;

		if (checkCurrentSectionOnly){
			//Check for validation errors on the currently displayed section only
	    	View v = getCurrentView();
	        if(v instanceof Section){
	        	isValid = isValid(validationResults, (Section)v, allFields);
	    	}
	     	if(this.isStartViewShowing()){
	     		if(startPopupView instanceof Section){
	     			isValid = isValid(validationResults, ((Section) startPopupView), allFields) && isValid;
	     		}
	     	}
		} else {
			//Check for validation errors on all sections
			String errorSections = "";
			StringBuilder errorSectionsbuffer = new StringBuilder();
			errorSectionsbuffer.append(errorSections);
			for (Entry<Enum<?>, View> entry:viewMap.entrySet()) {
				View v = entry.getValue();
				if (v instanceof Section){
					if (!isValid(validationResults, (Section)v, allFields)){
						isValid = false;
						errorSectionsbuffer.append(((SectionView)v).getName() + ", ");
					}
				}
			}
	     	if(this.isStartViewShowing()){
	     		if(startPopupView instanceof Section){
	     			isValid = isValid(validationResults, ((Section) startPopupView), allFields) && isValid;
	     		}
	     	}
			errorSections = errorSectionsbuffer.toString();
			if (!errorSections.isEmpty()){
				errorSections = errorSections.substring(0, errorSections.length()-2);
				//container.addMessage("Following section(s) has errors & must be corrected: " + errorSections);
			}
		}

		return isValid;
	}

	private boolean isValid(List<ValidationResultInfo> validationResults, Section section, boolean allFields){
		ErrorLevel status;
		if(allFields){
			section.setFieldHasHadFocusFlags(true);
			status = section.processValidationResults(validationResults);
		}
		else{
			status = section.processValidationResults(validationResults, false);
		}

		return (status != ErrorLevel.ERROR);
	}

	@Override
	public void beforeViewChange(Callback<Boolean> okToChange) {
		if(this.getCurrentView() instanceof Controller){
			((Controller)this.getCurrentView()).beforeViewChange(okToChange);
		}
		else{
			okToChange.exec(true);
		}
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public boolean beforeHide() {
		return true;
	}

	@Override
	public void beforeShow(Callback<Boolean> onReadyCallback) {
		onReadyCallback.exec(true);
	}

	@Override
	public Controller getController() {
		return parentController;
	}

	@Override
	public String getName() {
		if(name == null && viewType != null){
			return viewType.toString();
		}
		else{
			return name;
		}
	}

	@Override
	public Enum<?> getViewEnum() {
		return viewType;
	}

	public void setViewEnum(Enum<?> viewType){
		this.viewType= viewType;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setController(Controller controller){
		parentController = controller;
	}

	@Override
	public void collectBreadcrumbNames(List<String> names) {
		names.add(this.getName());
		if(this.getCurrentView() != null){
			this.getCurrentView().collectBreadcrumbNames(names);
		}
	}

	@Override
	public void clear() {
		
	}
	
}
