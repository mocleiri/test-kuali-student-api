package org.kuali.student.lum.program.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.kuali.student.common.ui.client.configurable.mvc.Configurer;
import org.kuali.student.common.ui.client.configurable.mvc.layouts.MenuEditableSectionController;
import org.kuali.student.common.ui.client.configurable.mvc.layouts.TabbedSectionLayout;
import org.kuali.student.common.ui.client.configurable.mvc.views.SectionView;
import org.kuali.student.common.ui.client.configurable.mvc.views.VerticalSectionView;
import org.kuali.student.common.ui.client.mvc.*;
import org.kuali.student.common.ui.client.service.MetadataRpcService;
import org.kuali.student.common.ui.client.service.MetadataRpcServiceAsync;
import org.kuali.student.common.ui.client.widgets.containers.KSTitleContainerImpl;
import org.kuali.student.core.assembly.data.Metadata;
import org.kuali.student.core.workflow.ui.client.widgets.WorkflowEnhancedController;
import org.kuali.student.core.workflow.ui.client.widgets.WorkflowUtilities;
import org.kuali.student.lum.program.client.properties.ProgramProperties;

/**
 * TODO: why do I need to implement WorkflowEnhancedController???
 *
 * @author Igor
 */
public class MajorDisciplineController extends MenuEditableSectionController implements WorkflowEnhancedController {

    public static final String PROGRAM_MODEL_ID = "programModelId";

    private AbstractProgramConfigurer configurer = GWT.create(ProgramConfigurer.class);

    private MetadataRpcServiceAsync metadataService = GWT.create(MetadataRpcService.class);

    public MajorDisciplineController() {
        super(MajorDisciplineController.class.getName());
        initialize();
    }

    private void initialize() {
        registerModel(PROGRAM_MODEL_ID, new ModelProvider<Model>() {
            @Override
            public void requestModel(ModelRequestCallback<Model> modelModelRequestCallback) {

            }
        });
        /* metadataService.getMetadata(null, null, null, new AbstractCallback<Metadata>() {
            @Override
            public void onSuccess(Metadata result) {
                afterMetadataIsLoaded(result);
            }
        });*/
        afterMetadataIsLoaded(null);
    }

    private void afterMetadataIsLoaded(Metadata metadata) {
        configurer.setModelDefinition(new DataModelDefinition(metadata));
        configurer.configure(this);
    }

    @Override
    public void showDefaultView(Callback<Boolean> onReadyCallback) {
        onReadyCallback.exec(true);
    }

    @Override
    public WorkflowUtilities getWfUtilities() {
        return null;
    }
}
