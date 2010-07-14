package org.kuali.student.lum.program.client;

import com.google.gwt.core.client.GWT;
import org.kuali.student.common.ui.client.configurable.mvc.layouts.MenuEditableSectionController;
import org.kuali.student.common.ui.client.mvc.*;
import org.kuali.student.core.assembly.data.Metadata;
import org.kuali.student.lum.program.client.rpc.ProgramRpcService;
import org.kuali.student.lum.program.client.rpc.ProgramRpcServiceAsync;

/**
 * TODO: why do I need to implement WorkflowEnhancedController???
 *
 * @author Igor
 */
public class ProgramController extends MenuEditableSectionController {

    public static final String PROGRAM_MODEL_ID = "programModelId";

    private AbstractProgramConfigurer configurer = GWT.create(ProgramConfigurer.class);

    private ProgramRpcServiceAsync programRemoteService = GWT.create(ProgramRpcService.class);

    public ProgramController() {
        super(ProgramController.class.getName());
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
                modelModelRequestCallback.onModelReady(new DataModel());
            }
        });
        configurer.setModelDefinition(new DataModelDefinition(metadata));
        configurer.configure(this);
        super.showDefaultView(NO_OP_CALLBACK);
    }

    @Override
    public void showDefaultView(Callback<Boolean> callback) {
        initialize(callback);
    }
}
