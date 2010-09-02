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

package org.kuali.student.lum.common.client.lo;

import java.util.ArrayList;
import java.util.List;

import org.kuali.student.common.ui.client.application.KSAsyncCallback;
import org.kuali.student.common.ui.client.widgets.KSButton;
import org.kuali.student.common.ui.client.widgets.KSCheckBox;
import org.kuali.student.common.ui.client.widgets.KSDropDown;
import org.kuali.student.common.ui.client.widgets.KSLabel;
import org.kuali.student.common.ui.client.widgets.KSLightBox;
import org.kuali.student.common.ui.client.widgets.KSTextBox;
import org.kuali.student.common.ui.client.widgets.list.impl.SimpleListItems;
import org.kuali.student.common.ui.client.widgets.searchtable.ResultRow;
import org.kuali.student.lum.common.client.lo.rpc.LoRpcService;
import org.kuali.student.lum.common.client.lo.rpc.LoRpcServiceAsync;
import org.kuali.student.lum.lo.dto.LoCategoryInfo;
import org.kuali.student.lum.lo.dto.LoCategoryTypeInfo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.gen2.table.client.SelectionGrid.SelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CategoryManagement extends Composite {
    private KSButton addButton = new KSButton("Create");
    private KSButton deleteButton = new KSButton("Delete");
    private KSButton updateButton = new KSButton("Update");

    KSCheckBox accreditationCheckBox = new KSCheckBox("Accreditation");
    KSCheckBox skillCheckBox = new KSCheckBox("Skill");
    KSCheckBox subjectCheckBox = new KSCheckBox("Subject");
    KSTextBox wordsInCategoryTextBox = new KSTextBox();
	
    static LoRpcServiceAsync loRpcServiceAsync = GWT.create(LoRpcService.class);
    
    CategoryManagementTable categoryManagementTable = null;

    VerticalPanel mainPanel = new VerticalPanel();
    KSLabel messageLabel = new KSLabel();
    
    List<LoCategoryTypeInfo> categoryTypeList;
    private void initCategoryManagement() {
        super.initWidget(mainPanel);
        mainPanel.addStyleName("KSLOCategoryManagementMainPanel");
        accreditationCheckBox.setValue(true);
        skillCheckBox.setValue(true);
        subjectCheckBox.setValue(true);
        VerticalPanel filterPanel = new VerticalPanel();
        filterPanel.addStyleName("KSLOCategoryManagementFilterPanel");
        KSLabel filterLabel = new KSLabel("Filter");
        filterLabel.addStyleName("KSLOCategoryManagementFilterLabel");
        filterPanel.add(filterLabel);

        Hyperlink selectAllLink = new Hyperlink("Select All","Select All");
        selectAllLink.addStyleName("Home-Small-Hyperlink");
        filterPanel.add(selectAllLink);
        selectAllLink.addClickHandler(new ClickHandler(){
            @Override
            public void onClick(ClickEvent event) {
                accreditationCheckBox.setValue(true);
                skillCheckBox.setValue(true);
                subjectCheckBox.setValue(true);
                filterCategoryByType();
            }
        });

        Hyperlink clearLink = new Hyperlink("Clear","Clear");
        clearLink.addStyleName("Home-Small-Hyperlink");
        filterPanel.add(clearLink);
        clearLink.addClickHandler(new ClickHandler(){
            @Override
            public void onClick(ClickEvent event) {
                accreditationCheckBox.setValue(false);
                skillCheckBox.setValue(false);
                subjectCheckBox.setValue(false);
                wordsInCategoryTextBox.setText(null);
                filterCategoryByType();
            }
        });
        filterPanel.add(accreditationCheckBox);
        filterPanel.add(skillCheckBox);
        filterPanel.add(subjectCheckBox);
        filterPanel.add(new KSLabel("By words in category"));
        filterPanel.add(wordsInCategoryTextBox);

        HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.addStyleName("KSLOCategoryManagementButtonPanel");
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);

        mainPanel.add(buttonPanel);
        HorizontalPanel filterTablePanel = new HorizontalPanel();
        filterTablePanel.add(filterPanel);
        if(this.categoryManagementTable == null) {
            categoryManagementTable = new CategoryManagementTable();
        }
        filterTablePanel.add(categoryManagementTable);
        mainPanel.add(filterTablePanel);
        
        mainPanel.add(messageLabel);

        loRpcServiceAsync.getLoCategoryTypes(new KSAsyncCallback<List<LoCategoryTypeInfo>>() {
            @Override
            public void handleFailure(Throwable caught) {
                GWT.log("getLoCategoryTypes failed", caught);
                Window.alert("Get LoCategory Types failed");
            }
            @Override
            public void onSuccess(List<LoCategoryTypeInfo> results) {
                categoryTypeList = results;
                categoryManagementTable.loadTable();
            }
        });

        wordsInCategoryTextBox.addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                filterCategoryByWords();
            }

        });
        subjectCheckBox.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                filterCategoryByType();
            }
        });
        skillCheckBox.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                filterCategoryByType();
            }
        });
        accreditationCheckBox.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                filterCategoryByType();
            }
        });

        addButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                CreateCategoryDialog dialog = new CreateCategoryDialog();
                dialog.setCategoryType(categoryTypeList);
                dialog.show();
            }
        });
        deleteButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                
                String id = categoryManagementTable.getSelectedLoCategoryInfoId();
                loRpcServiceAsync.getLoCategory(id, new KSAsyncCallback<LoCategoryInfo>() {
                    @Override
                    public void handleFailure(Throwable caught) {
                        GWT.log("getSelectedLoCategoryInfo failed", caught);
                        Window.alert("Get Selected Lo Category failed");
                    }

                    @Override
                    public void onSuccess(LoCategoryInfo result) {
                        DeleteConfirmationDialog dialog = new DeleteConfirmationDialog();
                        dialog.setCategory(result);
                        dialog.show();
                    }
                });

            }
        });
        updateButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                String id = categoryManagementTable.getSelectedLoCategoryInfoId();
                loRpcServiceAsync.getLoCategory(id, new KSAsyncCallback<LoCategoryInfo>() {
                    @Override
                    public void handleFailure(Throwable caught) {
                        GWT.log("getSelectedLoCategoryInfo failed", caught);
                        Window.alert("Get Selected Lo Category failed");
                    }

                    @Override
                    public void onSuccess(LoCategoryInfo result) {
                        UpdateCategoryDialog dialog = new UpdateCategoryDialog();
                        dialog.setCategory(result);
                        dialog.setCategoryType(categoryTypeList);
                        dialog.show();
                    }
                });
            }
        });
    }
    public CategoryManagement() {
        initCategoryManagement();
    }
    
    public CategoryManagement(boolean hideInactiveCategories,SelectionPolicy selectionPolicy) {
        this.categoryManagementTable = new CategoryManagementTable(hideInactiveCategories, selectionPolicy);
        initCategoryManagement();
    }
    public List<LoCategoryInfo> getSelectedCategoryList(){
        return categoryManagementTable.getSelectedLoCategoryInfos();
    }
       	
    private void filterCategoryByType() {

        List<ResultRow> bufferList = new ArrayList<ResultRow>();
        if(subjectCheckBox.getValue() == true){
            bufferList.addAll(categoryManagementTable.getRowsByType("subject"));
        }
        if(skillCheckBox.getValue() == true){
            bufferList.addAll(categoryManagementTable.getRowsByType("skill"));
        }
        if(accreditationCheckBox.getValue() == true){
            bufferList.addAll(categoryManagementTable.getRowsByType("accreditation"));
        }
        categoryManagementTable.redraw(bufferList);

    }
    
    private void filterCategoryByWords() {

        List<ResultRow> bufferList = new ArrayList<ResultRow>();
        String input = wordsInCategoryTextBox.getText();
        if(input != null && (!input.isEmpty())){
            List<ResultRow> tmpList = categoryManagementTable.getRowsLikeName(input);
            bufferList.addAll(tmpList);
            categoryManagementTable.redraw(bufferList);
        } else {
            filterCategoryByType();
        }       
    }

    public void setDeleteButtonEnabled(boolean b) {
        deleteButton.setVisible(b);
    }

    public void setUpdateButtonEnabled(boolean b) {
        updateButton.setVisible(b);
    }

    public void setInsertButtonEnabled(boolean b) {
        addButton.setVisible(b);
    }
    class DeleteConfirmationDialog extends KSLightBox {
        KSLabel categoryNameLabel = new KSLabel();
        KSLabel categoryTypeLabel = new KSLabel();
        LoCategoryInfo categoryInfo;
        public DeleteConfirmationDialog() {
            VerticalPanel mainPanel = new VerticalPanel();
            FlexTable layoutTable = new FlexTable();
            mainPanel.add(new KSLabel("You are about to delete the following:"));
            mainPanel.add(layoutTable);
            layoutTable.setWidget(0, 0, new KSLabel("Category"));
            layoutTable.setWidget(0, 1, categoryNameLabel);
            layoutTable.setWidget(1, 0, new KSLabel("Type"));
            layoutTable.setWidget(1, 1, categoryTypeLabel);

            KSButton deleteButton = new KSButton("Delete");
            Hyperlink cancelButton = new Hyperlink();
            cancelButton.setText("Cancel");
            HorizontalPanel buttonPanel = new HorizontalPanel();
            buttonPanel.add(deleteButton);

            deleteButton.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    // not really deleting; rather 'retiring' LoCategory, but switching state to "inactive"
                	categoryInfo.setState("inactive");
                    CategoryManagement.loRpcServiceAsync.updateLoCategory(categoryInfo.getId(), categoryInfo, new KSAsyncCallback<LoCategoryInfo>() {
                        @Override
                        public void handleFailure(Throwable caught) {
                            GWT.log("updateLoCategory failed ", caught);
                            Window.alert("Switch LoCategory state to inactive failed ");
                        }

                        @Override
                        public void onSuccess(LoCategoryInfo updatedLo) {
                            categoryManagementTable.loadTable();
                            filterCategoryByType();
                        }
                    });

                    DeleteConfirmationDialog.this.hide();
                }
            });

            buttonPanel.add(cancelButton);
            cancelButton.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    DeleteConfirmationDialog.this.hide();
                }

            });
            mainPanel.add(buttonPanel);
            super.setWidget(mainPanel);
        }

        public void setCategory(LoCategoryInfo cate) {
            categoryInfo = cate;
            categoryNameLabel.setText(categoryInfo.getName());
            categoryTypeLabel.setText(categoryInfo.getType());
        }
    }
    class UpdateCategoryDialog extends KSLightBox {
        FlexTable layoutTable = new FlexTable();
        KSTextBox nameTextBox = new KSTextBox();
        KSDropDown typeListBox = new KSDropDown();
        KSButton okButton = new KSButton("OK");
        KSButton cancelButton = new KSButton("Cancel");
        LoCategoryInfo categoryInfo;

        public UpdateCategoryDialog() {
            layoutTable.setWidget(0, 0, new KSLabel("Category"));
            layoutTable.setWidget(0, 1, new KSLabel("Type"));
            layoutTable.setWidget(1, 0, nameTextBox);
            layoutTable.setWidget(1, 1, typeListBox);

            HorizontalPanel buttonPanel = new HorizontalPanel();
            buttonPanel.add(okButton);
            buttonPanel.add(cancelButton);

            VerticalPanel mainPanel = new VerticalPanel();
            mainPanel.add(new KSLabel("Update Category"));
            mainPanel.add(layoutTable);
            mainPanel.add(buttonPanel);

            super.setWidget(mainPanel);
            okButton.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    LoCategoryInfo cate = getCategory();
                    CategoryManagement.loRpcServiceAsync.updateLoCategory(cate.getId(), cate, new KSAsyncCallback<LoCategoryInfo>() {
                        @Override
                        public void handleFailure(Throwable caught) {
                            GWT.log("updateLoCategory failed ", caught);
                            Window.alert("Update LoCategory failed ");
                        }
                        @Override
                        public void onSuccess(LoCategoryInfo result) {
                            categoryManagementTable.loadTable();
                            filterCategoryByType();
                        }
                    });
                    UpdateCategoryDialog.this.hide();
                }

            });

            cancelButton.addClickHandler(new ClickHandler() {

                @Override
                public void onClick(ClickEvent event) {
                    UpdateCategoryDialog.this.hide();
                }

            });
            super.setWidget(mainPanel);
        }

        public void setCategoryType(List<LoCategoryTypeInfo> categoryTypeList) {
            typeListBox.clear();
            SimpleListItems categoryTypes = new SimpleListItems();
            for (LoCategoryTypeInfo type : categoryTypeList) {
                categoryTypes.addItem(type.getId(), type.getDesc());
            }
            typeListBox.setListItems(categoryTypes);
        }

        public void setCategory(LoCategoryInfo cate) {
            categoryInfo = cate;
            nameTextBox.setText(categoryInfo.getName());
            typeListBox.selectItem(categoryInfo.getType());
        }

        public LoCategoryInfo getCategory() {
            categoryInfo.setName(nameTextBox.getText());
            //categoryInfo.setType(typeListBox.getItemText(typeListBox.getSelectedIndex()));
            categoryInfo.setType(typeListBox.getSelectedItem());
            return categoryInfo;
        }
    }

    class CreateCategoryDialog extends KSLightBox {
        FlexTable layoutTable = new FlexTable();

        KSButton okButton = new KSButton("OK");
        KSButton cancelButton = new KSButton("Cancel");

        KSTextBox nameTextBox = new KSTextBox();

         KSDropDown typeListBox = new KSDropDown();
        public CreateCategoryDialog() {

            layoutTable.setWidget(0, 0, new KSLabel("Category"));
            layoutTable.setWidget(0, 1, new KSLabel("Type"));
            layoutTable.setWidget(1, 0, nameTextBox);
            layoutTable.setWidget(1, 1, typeListBox);

            HorizontalPanel buttonPanel = new HorizontalPanel();
            buttonPanel.add(okButton);
            buttonPanel.add(cancelButton);

            VerticalPanel mainPanel = new VerticalPanel();
            mainPanel.add(new KSLabel("Create New Category"));
            mainPanel.add(layoutTable);
            mainPanel.add(buttonPanel);
            mainPanel.setPixelSize(300, 300);
            super.setWidget(mainPanel);
            okButton.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    LoCategoryInfo cate = getCategory();
                    CategoryManagement.loRpcServiceAsync.createLoCategory(cate.getLoRepository(), cate.getType(), cate, new KSAsyncCallback<LoCategoryInfo>() {
                        @Override
                        public void handleFailure(Throwable caught) {
                            GWT.log("createLoCategory failed ", caught);
                            Window.alert("Create LoCategory failed ");
                        }
                        @Override
                        public void onSuccess(LoCategoryInfo result) {
                            categoryManagementTable.loadTable();
                            filterCategoryByType();
                        }
                    });
                    CreateCategoryDialog.this.hide();
                }
            });
            cancelButton.addClickHandler(new ClickHandler() {

                @Override
                public void onClick(ClickEvent event) {
                    CreateCategoryDialog.this.hide();
                }

            });
        }

        public void setCategoryType(List<LoCategoryTypeInfo> categoryTypeList) {
            typeListBox.clear();
            SimpleListItems categoryTypes = new SimpleListItems();
            for (LoCategoryTypeInfo type : categoryTypeList) {
                categoryTypes.addItem(type.getId(), type.getDesc());
            }
            typeListBox.setListItems(categoryTypes);
        }

        public LoCategoryInfo getCategory() {
            LoCategoryInfo info = new LoCategoryInfo();
            info.setName(nameTextBox.getText());
            info.setType(typeListBox.getSelectedItem());
            info.setState("active");
            info.setLoRepository("kuali.loRepository.key.singleUse");
            // FIXME [KSCOR-225] user needs to specify what LoRepository they want category to tagged with
            return info;
        }
    }

}


