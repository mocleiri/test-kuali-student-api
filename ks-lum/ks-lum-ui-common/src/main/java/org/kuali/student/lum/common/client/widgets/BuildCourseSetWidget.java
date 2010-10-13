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
package org.kuali.student.lum.common.client.widgets;

import java.util.List;

import org.kuali.student.common.ui.client.configurable.mvc.layouts.BasicLayout;
import org.kuali.student.common.ui.client.configurable.mvc.sections.Section;
import org.kuali.student.common.ui.client.mvc.Callback;
import org.kuali.student.common.ui.client.mvc.DataModel;
import org.kuali.student.common.ui.client.mvc.DataModelDefinition;
import org.kuali.student.common.ui.client.mvc.ModelProvider;
import org.kuali.student.common.ui.client.mvc.ModelRequestCallback;
import org.kuali.student.common.ui.client.mvc.View;
import org.kuali.student.common.ui.client.service.DataSaveResult;
import org.kuali.student.common.ui.client.widgets.progress.BlockingTask;
import org.kuali.student.common.ui.client.widgets.progress.KSBlockingProgressIndicator;
import org.kuali.student.common.ui.client.widgets.rules.AccessWidgetValue;
import org.kuali.student.core.assembly.data.Data;
import org.kuali.student.core.assembly.data.Metadata;
import org.kuali.student.core.validation.dto.ValidationResultInfo;
import org.kuali.student.core.validation.dto.ValidationResultInfo.ErrorLevel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;

public class BuildCourseSetWidget extends FlowPanel implements AccessWidgetValue {

    private CluSetRetriever cluSetRetriever;
    private CluSetEditorWidget cluSetEditorWidgetView;
    private static final String CLUSET_MODEL_ID = "clussetModelId";
    private DataModel ruleFieldsData;
    private BasicLayout reqCompController;
    public enum BuildCourseView {VIEW}
    private BlockingTask retrievingTask = new BlockingTask("Retrieving ...");
    private String cluSetType;

    public BuildCourseSetWidget(final CluSetRetriever cluSetRetriever, String cluSetType) {
        super();

        cluSetEditorWidgetView = new CluSetEditorWidget(
                new CluSetRetrieverImpl(),
                BuildCourseView.VIEW,
                "", CLUSET_MODEL_ID, false,
                null);

        ruleFieldsData = new DataModel();
        ruleFieldsData.setRoot(new Data());
        this.cluSetType = cluSetType;

        //setup controller
        final CluSetRetriever theRetriever = cluSetRetriever;
        this.cluSetRetriever = cluSetRetriever;
        reqCompController = new BasicLayout(null);
        reqCompController.addView(cluSetEditorWidgetView);
        reqCompController.setDefaultModelId(CLUSET_MODEL_ID);
        reqCompController.registerModel(CLUSET_MODEL_ID, new ModelProvider<DataModel>() {
            @Override
            public void requestModel(final ModelRequestCallback<DataModel> callback) {
                if (ruleFieldsData.getDefinition() == null) {
                    theRetriever.getMetadata("cluset", new Callback<Metadata>(){
                        @Override
                        public void exec(Metadata result) {
                            DataModelDefinition def = new DataModelDefinition(result);
                            ruleFieldsData.setDefinition(def);
                            callback.onModelReady(ruleFieldsData);
                        }
                    });
                } else {
                    callback.onModelReady(ruleFieldsData);
                }
            }
        });

        reqCompController.showView(BuildCourseView.VIEW);
        add(reqCompController);
    }

    @Override
    public void setValue(final String cluSetId) {
        if (cluSetId != null) {
            KSBlockingProgressIndicator.addTask(retrievingTask);
            cluSetRetriever.getData(cluSetId,  new Callback<Data>() {
                @Override
                public void exec(Data result) {
                    try {
                        ruleFieldsData.setRoot(result);
                        cluSetEditorWidgetView.updateView(ruleFieldsData);
                        reqCompController.showView(BuildCourseView.VIEW);
                    } finally {
                        KSBlockingProgressIndicator.removeTask(retrievingTask);
                    }
                }
            });
        }
    }

    @Override
    public void getValue(final Callback<String> doneSaveCallback) {
        GWT.log("CluSetManagementController received save action request.", null);
        reqCompController.getCurrentView().updateModel();
        if(ruleFieldsData!=null){
            ruleFieldsData.validate(new Callback<List<ValidationResultInfo>>() {
                @Override
                public void exec(List<ValidationResultInfo> result) {
    
                    boolean save = true;
                    View v = reqCompController.getCurrentView();
                    if(v instanceof Section){
                        ((Section) v).setFieldHasHadFocusFlags(true);
                        ErrorLevel status = ((Section) v).processValidationResults(result);
                        if(status == ErrorLevel.ERROR){
                            save = false;
                        }
                    }
    
                    if(save){
                        reqCompController.getCurrentView().updateModel();
                        reqCompController.updateModel();
                        // set reusable flag here for CluSetManagement.
                        Calendar startCal = new GregorianCalendar();
                        startCal.set(Calendar.YEAR, 1990);
                        startCal.set(Calendar.MONTH, Calendar.JANUARY);
                        startCal.set(Calendar.DATE, 1);
                        startCal.set(Calendar.HOUR_OF_DAY, 1);
                        startCal.set(Calendar.MINUTE, 0);
                        startCal.set(Calendar.SECOND, 0);
                        Calendar endCal = new GregorianCalendar();
                        endCal.set(Calendar.YEAR, 2200);
                        endCal.set(Calendar.MONTH, Calendar.JANUARY);
                        endCal.set(Calendar.DATE, 1);
                        endCal.set(Calendar.HOUR_OF_DAY, 1);
                        endCal.set(Calendar.MINUTE, 0);
                        endCal.set(Calendar.SECOND, 0);
                        CluSetHelper cluSetHelper = CluSetHelper.wrap(ruleFieldsData.getRoot());
                        cluSetHelper.setType(cluSetType);
                        cluSetHelper.setName("AdHock");
                        cluSetHelper.setReusable(new Boolean(false));
                        cluSetHelper.setReferenceable(new Boolean(false));
                        cluSetHelper.setEffectiveDate(startCal.getTime());
                        cluSetHelper.setEffectiveDate(endCal.getTime());
                        cluSetRetriever.saveData(ruleFieldsData.getRoot(), new Callback<DataSaveResult>() {
                            @Override
                            public void exec(DataSaveResult result) {
                                // FIXME needs to check validation results and display messages if validation failed
                                ruleFieldsData.setRoot(result.getValue());
                                String cluSetId = CluSetHelper.wrap((Data)ruleFieldsData.getRoot()).getId();
                                doneSaveCallback.exec(cluSetId);
                            }
                        });
                    }
                    else {
                        Window.alert("Save failed.  Please check fields for errors.");
                    }

                }
            });
        }
    }
    
}
