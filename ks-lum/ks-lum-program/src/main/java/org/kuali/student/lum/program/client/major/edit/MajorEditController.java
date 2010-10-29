package org.kuali.student.lum.program.client.major.edit;

import java.util.ArrayList;
import java.util.List;

import org.kuali.student.common.ui.client.application.ViewContext;
import org.kuali.student.common.ui.client.mvc.Callback;
import org.kuali.student.common.ui.client.mvc.DataModel;
import org.kuali.student.common.ui.client.mvc.ModelRequestCallback;
import org.kuali.student.common.ui.client.mvc.history.HistoryManager;
import org.kuali.student.common.ui.client.service.DataSaveResult;
import org.kuali.student.common.ui.client.widgets.KSButton;
import org.kuali.student.common.ui.client.widgets.KSButtonAbstract;
import org.kuali.student.common.ui.client.widgets.notification.KSNotifier;
import org.kuali.student.common.ui.shared.IdAttributes;
import org.kuali.student.core.assembly.data.Data;
import org.kuali.student.core.assembly.data.QueryPath;
import org.kuali.student.core.validation.dto.ValidationResultInfo;
import org.kuali.student.lum.common.client.widgets.AppLocations;
import org.kuali.student.lum.program.client.*;
import org.kuali.student.lum.program.client.events.*;
import org.kuali.student.lum.program.client.major.MajorController;
import org.kuali.student.lum.program.client.properties.ProgramProperties;
import org.kuali.student.lum.program.client.rpc.AbstractCallback;
import org.kuali.student.lum.program.client.widgets.ProgramSideBar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;

/**
 * @author Igor
 */
public class MajorEditController extends MajorController {

    private KSButton saveButton = new KSButton(ProgramProperties.get().common_save());
    private KSButton cancelButton = new KSButton(ProgramProperties.get().common_cancel(), KSButtonAbstract.ButtonStyle.ANCHOR_LARGE_CENTERED);

    /**
     * Constructor.
     *
     * @param programModel
     */
    public MajorEditController(String name, DataModel programModel, ViewContext viewContext, HandlerManager eventBus) {
        super(name, programModel, viewContext, eventBus);
        configurer = GWT.create(MajorEditConfigurer.class);
        sideBar.setState(ProgramSideBar.State.EDIT);
        initHandlers();
    }

    @Override
    protected void configureView() {
        super.configureView();
        if (!initialized) {
            eventBus.fireEvent(new MetadataLoadedEvent(programModel.getDefinition(), this));
            List<Enum<?>> excludedViews = new ArrayList<Enum<?>>();
            excludedViews.add(ProgramSections.PROGRAM_REQUIREMENTS_EDIT);
            excludedViews.add(ProgramSections.SUPPORTING_DOCUMENTS_EDIT);
            addCommonButton(ProgramProperties.get().program_menu_sections(), saveButton, excludedViews);
            addCommonButton(ProgramProperties.get().program_menu_sections(), cancelButton, excludedViews);
            initialized = true;
        }
    }

    private void initHandlers() {
        saveButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                doSave();
            }
        });
        cancelButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                doCancel();
            }
        });
        eventBus.addHandler(UpdateEvent.TYPE, new UpdateEventHandler() {
            @Override
            public void onEvent(UpdateEvent event) {
                doSave(event.getOkCallback());
            }
        });

        eventBus.addHandler(SpecializationSaveEvent.TYPE, new SpecializationSaveEventHandler() {
            @Override
            public void onEvent(SpecializationSaveEvent event) {
                ((Data) programModel.get(ProgramConstants.VARIATIONS)).add(event.getData());
                doSave();
            }
        });
        eventBus.addHandler(AddSpecializationEvent.TYPE, new AddSpecializationEventHandler() {
            @Override
            public void onEvent(AddSpecializationEvent event) {
                String id = (String) programModel.get(ProgramConstants.ID);
                ProgramRegistry.setRow(programModel.<Data>get(ProgramConstants.VARIATIONS).size());
                ProgramRegistry.setData(ProgramUtils.createNewSpecializationBasedOnMajor(programModel));
                ViewContext viewContext = new ViewContext();
                viewContext.setId(id);
                viewContext.setIdType(IdAttributes.IdType.OBJECT_ID);
                HistoryManager.navigate(AppLocations.Locations.EDIT_VARIATION.getLocation(), viewContext);

            }
        });
        eventBus.addHandler(SpecializationUpdateEvent.TYPE, new SpecializationUpdateEventHandler() {
            @Override
            public void onEvent(SpecializationUpdateEvent event) {
                doSave();
            }
        });
        eventBus.addHandler(ModelLoadedEvent.TYPE, new ModelLoadedEventHandler() {
            @Override
            public void onEvent(ModelLoadedEvent event) {
                Enum<?> changeSection = ProgramRegistry.getSection();
                if (changeSection != null) {
                    showView(changeSection);
                    ProgramRegistry.setSection(null);
                } else {
                    String id = (String) programModel.get(ProgramConstants.ID);
                    if (id == null) {
                        showView(ProgramSections.PROGRAM_DETAILS_EDIT);
                    } else {
                        showView(ProgramSections.SUMMARY);
                    }
                }
            }
        });
        eventBus.addHandler(StoreRequirementIDsEvent.TYPE, new StoreRequirementIdsEventHandler() {
            @Override
            public void onEvent(StoreRequirementIDsEvent event) {
                String programId = event.getProgramId();       //this can be either Major or Specialization ID
                String programType = event.getProgramType();
                List<String> ids = event.getProgramRequirementIds();
                Data programRequirements = null;

                //specializations will be handled differently from Major
                if (programType.equals("kuali.lu.type.Variation")) {

                    Data variationMap = programModel.get(ProgramConstants.VARIATIONS);

                    //TODO we don't need this code since we prevent user from saving rules before
                    //saving key program information...
                    //if we are saving rules and this specialization program was not yet saved
                /*    if (programId == null) {
                        //define data model for the new specialization
                        DataModel variationModel = new DataModel();
                        variationModel.setDefinition(programModel.getDefinition());
                        variationModel.setRoot(ProgramRegistry.getData());

                        //if this is the first specialization, create a container map
                        if (variationMap == null) {
                            variationMap = new Data();
                            programModel.set(QueryPath.parse(ProgramConstants.VARIATIONS), variationMap);
                        }
                        variationMap.add(variationModel.getRoot());
                        programRequirements = variationModel.getRoot().get(ProgramConstants.PROGRAM_REQUIREMENTS);
                        
                    } else { //at least one specialization is saved */

                        //find the specialization that we need to update
                        for (Data.Property property : variationMap) {
                            final Data variationData = property.getValue();
                            if (variationData.get(ProgramConstants.ID).equals(programId)) {
                                variationData.set(ProgramConstants.PROGRAM_REQUIREMENTS, new Data());
                                programRequirements = variationData.get(ProgramConstants.PROGRAM_REQUIREMENTS);
                                break;
                            }
                        }
                   // }
                    
                } else {                                       
                    programModel.set(QueryPath.parse(ProgramConstants.PROGRAM_REQUIREMENTS), new Data());
                    programRequirements = programModel.get(ProgramConstants.PROGRAM_REQUIREMENTS);
                }

                if (programRequirements == null) {
                    Window.alert("Cannot find program requirements in data model.");
                    GWT.log("Cannot find program requirements in data model", null);
                    return;
                }

                for (String id : ids) {
                    programRequirements.add(id);
                }
                doSave();
            }
        });
        eventBus.addHandler(ChangeViewEvent.TYPE, new ChangeViewEventHandler() {
            @Override
            public void onEvent(ChangeViewEvent event) {
                showView(event.getViewToken());
            }
        });
    }

    private void doSave(final Callback<Boolean> okCallback) {
        requestModel(new ModelRequestCallback<DataModel>() {
            @Override
            public void onModelReady(DataModel model) {
                MajorEditController.this.updateModelFromCurrentView();
                model.validate(new Callback<List<ValidationResultInfo>>() {
                    @Override
                    public void exec(List<ValidationResultInfo> result) {
                        boolean isSectionValid = isValid(result, true);
                        if (isSectionValid) {
                            saveData(okCallback);
                        } else {
                            okCallback.exec(false);
                            Window.alert("Save failed.  Please check fields for errors.");
                        }
                    }
                });

            }

            @Override
            public void onRequestFail(Throwable cause) {
                GWT.log("Unable to retrieve model for validation and save", cause);
            }
        });
    }

    private void doCancel() {
        showView(ProgramSections.SUMMARY);
    }

    protected void doSave() {
        doSave(NO_OP_CALLBACK);
    }

    private void saveData(final Callback<Boolean> okCallback) {
        programRemoteService.saveData(programModel.getRoot(), new AbstractCallback<DataSaveResult>(ProgramProperties.get().common_savingData()) {
            @Override
            public void onSuccess(DataSaveResult result) {
                super.onSuccess(result);
                if (result.getValidationResults() != null && !result.getValidationResults().isEmpty()) {
                    isValid(result.getValidationResults(), false, true);
                    StringBuilder msg = new StringBuilder();
                    for (ValidationResultInfo vri : result.getValidationResults()) {
                        msg.append(vri.getMessage());
                    }
                    KSNotifier.show(ProgramProperties.get().common_failedSave(msg.toString()));
                    okCallback.exec(false);
                } else {
                    programModel.setRoot(result.getValue());
                    setHeaderTitle();
                    setStatus();
                    resetFieldInteractionFlag();
                    throwAfterSaveEvent();
                    HistoryManager.logHistoryChange();
                    if (getCurrentViewEnum().equals(ProgramSections.SPECIALIZATIONS_EDIT.name())) {
                        showView(getCurrentViewEnum());
                    }
                    KSNotifier.show(ProgramProperties.get().common_successfulSave());
                    okCallback.exec(true);
                }
            }
        });
    }

    private void throwAfterSaveEvent() {
        eventBus.fireEvent(new AfterSaveEvent(programModel, this));
    }
}