package org.kuali.student.lum.program.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import org.kuali.student.common.ui.client.configurable.mvc.layouts.MenuEditableSectionController;
import org.kuali.student.common.ui.client.mvc.*;
import org.kuali.student.common.ui.client.widgets.KSButton;
import org.kuali.student.core.assembly.data.Data;
import org.kuali.student.core.assembly.data.Metadata;
import org.kuali.student.lum.program.client.properties.ProgramProperties;
import org.kuali.student.lum.program.client.rpc.ProgramRpcService;
import org.kuali.student.lum.program.client.rpc.ProgramRpcServiceAsync;

public class ProgramController extends MenuEditableSectionController {

    public static final String PROGRAM_MODEL_ID = "programModelId";

    private AbstractProgramConfigurer configurer = GWT.create(ProgramConfigurer.class);

    private ProgramRpcServiceAsync programRemoteService = GWT.create(ProgramRpcService.class);

    private KSButton saveButton = new KSButton(ProgramProperties.get().common_save());
    private KSButton cancelButton = new KSButton(ProgramProperties.get().common_cancel());

    private DataModel programModel;

    public ProgramController() {
        super(ProgramController.class.getName());
        initHandlers();
    }

    private void initHandlers() {
        saveButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                setEditMode(false);
            }
        });
        cancelButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                setEditMode(false);
            }
        });
    }

    private void initialize(final Callback<Boolean> callback) {
        programRemoteService.getMetadata(null, null, new AbstractCallback<Metadata>() {
            @Override
            public void onSuccess(Metadata result) {
                super.onSuccess(result);
                afterMetadataIsLoaded(result);
                callback.exec(true);
            }
        });
    }

    private void afterMetadataIsLoaded(Metadata metadata) {
        registerModel(PROGRAM_MODEL_ID, new ModelProvider<Model>() {
            @Override
            public void requestModel(ModelRequestCallback<Model> modelModelRequestCallback) {
                modelModelRequestCallback.onModelReady(programModel);
            }
        });
        DataModelDefinition dataModelDefinition = new DataModelDefinition(metadata);
        programModel = new DataModel(dataModelDefinition, getData());
        configurer.setModelDefinition(new DataModelDefinition(metadata));
        configurer.configure(this);
        super.showDefaultView(NO_OP_CALLBACK);
        addButtonForView(ProgramSections.PROGRAM_DETAILS_EDIT, saveButton);
        addButtonForView(ProgramSections.PROGRAM_DETAILS_EDIT, cancelButton);
    }

    @Override
    public void showDefaultView(Callback<Boolean> callback) {
        initialize(callback);
    }

    public static Data getData() {
        Data data = new Data();
        data.set(ProgramConstants.SHORT_TITLE, "Biology");
        data.set(ProgramConstants.LONG_TITLE, "Long Biology title here");
        return data;
    }

    public DataModel getProgramModel() {
        return programModel;
    }
}
