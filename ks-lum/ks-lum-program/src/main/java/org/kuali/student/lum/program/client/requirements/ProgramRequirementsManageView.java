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

package org.kuali.student.lum.program.client.requirements;

import java.util.List;

import org.kuali.student.common.ui.client.configurable.mvc.SectionTitle;
import org.kuali.student.common.ui.client.configurable.mvc.views.SectionView;
import org.kuali.student.common.ui.client.configurable.mvc.views.VerticalSectionView;
import org.kuali.student.common.ui.client.mvc.Callback;
import org.kuali.student.common.ui.client.widgets.KSProgressIndicator;
import org.kuali.student.common.ui.client.widgets.buttongroups.ButtonEnumerations;
import org.kuali.student.common.ui.client.widgets.field.layout.button.ActionCancelGroup;
import org.kuali.student.common.ui.client.widgets.rules.ObjectClonerUtil;
import org.kuali.student.common.ui.client.widgets.rules.ReqCompEditWidget;
import org.kuali.student.common.ui.client.widgets.rules.RuleManageWidget;
import org.kuali.student.core.statement.dto.ReqComponentInfo;
import org.kuali.student.core.statement.dto.ReqComponentTypeInfo;
import org.kuali.student.core.statement.dto.StatementTreeViewInfo;
import org.kuali.student.lum.program.client.properties.ProgramProperties;
import org.kuali.student.lum.program.client.rpc.StatementRpcService;
import org.kuali.student.lum.program.client.rpc.StatementRpcServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ProgramRequirementsManageView extends VerticalSectionView {

    private StatementRpcServiceAsync statementRpcServiceAsync = GWT.create(StatementRpcService.class);

    private ProgramRequirementsViewController parentController;

    //view's widgets
    private VerticalPanel layout = new VerticalPanel();
    private ReqCompEditWidget editReqCompWidget;
    private RuleManageWidget ruleManageWidget;
    private SimplePanel twiddlerPanel = new SimplePanel();

    //view's data
    private StatementTreeViewInfo rule = null;
    private boolean isInitialized = false;
    private boolean newRule = false;
    private ReqComponentInfo editedReqCompInfo = null;

    public ProgramRequirementsManageView(ProgramRequirementsViewController parentController, Enum<?> viewEnum, String name, String modelId) {
        super(viewEnum, name, modelId);
        this.parentController = parentController;
    }
    
    @Override
    public void beforeShow(final Callback<Boolean> onReadyCallback) {

        if (isInitialized == false) {
            retrieveAndSetupReqCompTypes();
            setupHandlers();
            draw();
            isInitialized = true;
        }

        onReadyCallback.exec(true);
    }

    private void setupHandlers() {                        
        editReqCompWidget.setReqCompConfirmButtonClickCallback(confirmReqCompCallback);
        ruleManageWidget.setReqCompEditButtonClickCallback(editReqCompCallback);
    }
      
    private void draw() {

        remove(layout);
        layout.clear();

        //STEP 1
        SectionTitle title = SectionTitle.generateH3Title(ProgramProperties.get().programRequirements_manageViewPageStep1Title());
        title.setStyleName("KS-Program-Requirements-Manage-Step-header1");  //make the header orange
        layout.add(title);

        layout.add(editReqCompWidget);

        //STEP 2
        title = SectionTitle.generateH3Title(ProgramProperties.get().programRequirements_manageViewPageStep2Title());
        title.setStyleName("KS-Program-Requirements-Manage-Step-header2");  //make the header orange
        layout.add(title);

        layout.add(ruleManageWidget);

        //add progressive indicator when rules are being simplified
        KSProgressIndicator twiddler = new KSProgressIndicator();
        twiddler.setVisible(false);
        twiddlerPanel.setWidget(twiddler);
        layout.add(twiddlerPanel);

        addWidget(layout);

        displaySaveButton();
    }

    private void displaySaveButton() {
        ActionCancelGroup actionCancelButtons = new ActionCancelGroup(ButtonEnumerations.UpdateCancelEnum.UPDATE, ButtonEnumerations.UpdateCancelEnum.CANCEL);
        actionCancelButtons.addStyleName("KS-Program-Requirements-Save-Button");
        actionCancelButtons.addCallback(new Callback<ButtonEnumerations.ButtonEnum>(){
             @Override
            public void exec(ButtonEnumerations.ButtonEnum result) {
                if (result == ButtonEnumerations.UpdateCancelEnum.UPDATE) {
                    ((SectionView)parentController.getCurrentView()).setIsDirty(true);
                }
                parentController.showView(ProgramRequirementsViewController.ProgramRequirementsViews.PREVIEW);
            }
        });
        addWidget(actionCancelButtons);
    }

    // called by requirement display widget when user wants to edit or add a sub-rule
    public void setRuleTree(StatementTreeViewInfo stmtTreeInfo, String treeId, String ruleType) {

        if (isInitialized == false) {
            editReqCompWidget = new ReqCompEditWidget();
            ruleManageWidget = new RuleManageWidget();            
        }

        editedReqCompInfo = null;
        
        //true if user is adding a new rule
        if (stmtTreeInfo == null) {
            rule = new StatementTreeViewInfo();
            rule.setId(treeId);
            rule.setType(ruleType);
            newRule = true;
        } else {
            rule = ObjectClonerUtil.clone(stmtTreeInfo);
            newRule = false;
        }

        //update screen elements
        editReqCompWidget.setupNewReqComp();
        ruleManageWidget.redraw(rule);
    }

    public StatementTreeViewInfo getRuleTree() {
        return rule;
    }

    public boolean isNewRule() {
        return newRule;
    }

    protected Callback<ReqComponentInfo> editReqCompCallback = new Callback<ReqComponentInfo>(){
        public void exec(ReqComponentInfo reqComp) {
            editReqCompWidget.setupExistingReqComp(reqComp);
            editedReqCompInfo = reqComp;
        }
    };

    //called when user clicks 'Add' or 'Update' a req. component of a rule
    protected Callback<ReqComponentInfo> confirmReqCompCallback = new Callback<ReqComponentInfo>(){
        public void exec(final ReqComponentInfo reqComp) {

            //1. update NL for the req. component
            statementRpcServiceAsync.translateReqComponentToNL(reqComp, "KUALI.RULEEDIT", "en", new AsyncCallback<String>() {
                public void onFailure(Throwable caught) {
                    Window.alert(caught.getMessage());
                    GWT.log("translateReqComponentToNL failed", caught);
               }

                public void onSuccess(final String statementNaturalLanguage) {
                    reqComp.setNaturalLanguageTranslation(statementNaturalLanguage);

                    //2. add / update req. component
                    if (editedReqCompInfo == null) {
                        if (rule.getStatements() != null && !rule.getStatements().isEmpty()) {
                            StatementTreeViewInfo newStatementTreeViewInfo = new StatementTreeViewInfo();
                            newStatementTreeViewInfo.setOperator(rule.getStatements().get(0).getOperator());
                            newStatementTreeViewInfo.getReqComponents().add(reqComp);
                            rule.getStatements().add(newStatementTreeViewInfo);
                        } else {
                            rule.getReqComponents().add(reqComp);
                        }
                    } else {
                        editedReqCompInfo.setNaturalLanguageTranslation(reqComp.getNaturalLanguageTranslation());
                        editedReqCompInfo.setReqCompFields(reqComp.getReqCompFields());
                        editedReqCompInfo.setRequiredComponentType(reqComp.getRequiredComponentType());
                    }
                    editedReqCompInfo = null;

                    //3. update NL for the rule
                    statementRpcServiceAsync.translateStatementTreeViewToNL(rule, "KUALI.RULEEDIT", "en", new AsyncCallback<String>() {
                        public void onFailure(Throwable caught) {
                            Window.alert(caught.getMessage());
                            GWT.log("translateStatementTreeViewToNL failed", caught);
                       }

                        public void onSuccess(final String statementNaturalLanguage) {
                            rule.setNaturalLanguageTranslation(statementNaturalLanguage);
                            ruleManageWidget.redraw(rule);
                        }
                    });
                }
            });
        }
    };

    private void retrieveAndSetupReqCompTypes() {

        statementRpcServiceAsync.getReqComponentTypesForStatementType(rule.getType(), new AsyncCallback<List<ReqComponentTypeInfo>>() {
            public void onFailure(Throwable cause) {
            	GWT.log("Failed to get req. component types for statement of type:" + rule.getType(), cause);
            	Window.alert("Failed to get req. component types for statement of type:" + rule.getType());
            }

            public void onSuccess(final List<ReqComponentTypeInfo> reqComponentTypeInfoList) {
                if (reqComponentTypeInfoList == null || reqComponentTypeInfoList.size() == 0) {
                    GWT.log("Missing Requirement Component Types", null);
                    Window.alert("Missing Requirement Component Types");
                    return;
                }
                editReqCompWidget.setReqCompList(reqComponentTypeInfoList);
            }
        });
    }    
}
