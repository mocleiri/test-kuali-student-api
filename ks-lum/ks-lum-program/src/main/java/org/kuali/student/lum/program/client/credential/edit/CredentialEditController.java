package org.kuali.student.lum.program.client.credential.edit;

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
import org.kuali.student.common.ui.shared.IdAttributes.IdType;
import org.kuali.student.core.assembly.data.Data;
import org.kuali.student.core.assembly.data.QueryPath;
import org.kuali.student.core.validation.dto.ValidationResultInfo;
import org.kuali.student.lum.program.client.ProgramConstants;
import org.kuali.student.lum.program.client.ProgramRegistry;
import org.kuali.student.lum.program.client.ProgramSections;
import org.kuali.student.lum.program.client.credential.CredentialController;
import org.kuali.student.lum.program.client.events.AfterSaveEvent;
import org.kuali.student.lum.program.client.events.ChangeViewEvent;
import org.kuali.student.lum.program.client.events.MetadataLoadedEvent;
import org.kuali.student.lum.program.client.events.ModelLoadedEvent;
import org.kuali.student.lum.program.client.events.StoreRequirementIDsEvent;
import org.kuali.student.lum.program.client.events.UpdateEvent;
import org.kuali.student.lum.program.client.properties.ProgramProperties;
import org.kuali.student.lum.program.client.rpc.AbstractCallback;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;

/**
 * @author Igor
 */
public class CredentialEditController extends CredentialController {

    private final KSButton saveButton = new KSButton(ProgramProperties.get().common_save());
    private final KSButton cancelButton = new KSButton(ProgramProperties.get().common_cancel(), KSButtonAbstract.ButtonStyle.ANCHOR_LARGE_CENTERED);

    /**
     * Constructor.
     *
     * @param programModel
     */
    public CredentialEditController(DataModel programModel, ViewContext viewContext, HandlerManager eventBus) {
        super(programModel, viewContext, eventBus);
        configurer = GWT.create(CredentialEditConfigurer.class);
        bind();
    }

    @Override
    protected void configureView() {
        super.configureView();
        if (!initialized) {
            eventBus.fireEvent(new MetadataLoadedEvent(programModel.getDefinition(), this));
            List<Enum<?>> excludedViews = new ArrayList<Enum<?>>();
            excludedViews.add(ProgramSections.PROGRAM_REQUIREMENTS_EDIT);
            excludedViews.add(ProgramSections.SUPPORTING_DOCUMENTS_EDIT);
            excludedViews.add(ProgramSections.SUMMARY);
            addCommonButton(ProgramProperties.get().program_menu_sections(), saveButton, excludedViews);
            addCommonButton(ProgramProperties.get().program_menu_sections(), cancelButton, excludedViews);
            initialized = true;
        }
    }

    private void bind() {
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
        eventBus.addHandler(StoreRequirementIDsEvent.TYPE, new StoreRequirementIDsEvent.Handler() {
            @Override
            public void onEvent(StoreRequirementIDsEvent event) {
                List<String> ids = event.getProgramRequirementIds();

                programModel.set(QueryPath.parse(ProgramConstants.PROGRAM_REQUIREMENTS), new Data());
                Data programRequirements = programModel.get(ProgramConstants.PROGRAM_REQUIREMENTS);

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
        eventBus.addHandler(ChangeViewEvent.TYPE, new ChangeViewEvent.Handler() {
            @Override
            public void onEvent(ChangeViewEvent event) {
                showView(event.getViewToken());
            }
        });
        eventBus.addHandler(UpdateEvent.TYPE, new UpdateEvent.Handler() {
            @Override
            public void onEvent(UpdateEvent event) {
                doSave(event.getOkCallback());
            }
        });
        eventBus.addHandler(ModelLoadedEvent.TYPE, new ModelLoadedEvent.Handler() {
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
    }

    private void doCancel() {
        showView(ProgramSections.SUMMARY);
    }

    @Override
    protected void doSave() {
        doSave(NO_OP_CALLBACK);
    }

    private void doSave(final Callback<Boolean> okCallback) {
        requestModel(new ModelRequestCallback<DataModel>() {
            @Override
            public void onModelReady(DataModel model) {
                CredentialEditController.this.updateModelFromCurrentView();
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
                    okCallback.exec(false);
                } else {
                    programModel.setRoot(result.getValue());
                    setHeaderTitle();
                    setStatus();
                    resetFieldInteractionFlag();
                    throwAfterSaveEvent();
                    if (ProgramSections.getViewForUpdate().contains(getCurrentViewEnum().name())) {
                        showView(getCurrentViewEnum());
                    }
                    HistoryManager.logHistoryChange();
                    KSNotifier.show(ProgramProperties.get().common_successfulSave());
                    okCallback.exec(true);
                }
            }
        });
    }

    @Override
    protected void loadModel(ModelRequestCallback<DataModel> callback) {
        ViewContext viewContext = getViewContext();
        if (viewContext.getIdType() == IdType.COPY_OF_OBJECT_ID) {
            createNewVersionAndLoadModel(callback, viewContext);
        } else {
            super.loadModel(callback);
        }
    }

    protected void createNewVersionAndLoadModel(final ModelRequestCallback<DataModel> callback, final ViewContext viewContext) {
        Data data = new Data();
        Data versionData = new Data();
        versionData.set(new Data.StringKey("versionIndId"), getViewContext().getId());
        versionData.set(new Data.StringKey("versionComment"), "Credential Program Version");
        data.set(new Data.StringKey("versionInfo"), versionData);

        programRemoteService.saveData(data, new AbstractCallback<DataSaveResult>(ProgramProperties.get().common_retrievingData()) {
            public void onSuccess(DataSaveResult result) {
                super.onSuccess(result);
                viewContext.setIdType(IdType.OBJECT_ID);
                programModel.setRoot(result.getValue());
                setHeaderTitle();
                setStatus();
                callback.onModelReady(programModel);
                eventBus.fireEvent(new ModelLoadedEvent(programModel));
            }

            public void onFailure(Throwable caught) {
                super.onFailure(caught);
                callback.onRequestFail(caught);
            }
        });

    }
    
    private void throwAfterSaveEvent() {
        eventBus.fireEvent(new AfterSaveEvent(programModel, this));
    }
}
