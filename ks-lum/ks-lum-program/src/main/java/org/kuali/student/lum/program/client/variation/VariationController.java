package org.kuali.student.lum.program.client.variation;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Widget;
import org.kuali.student.common.ui.client.application.ViewContext;
import org.kuali.student.common.ui.client.mvc.DataModel;
import org.kuali.student.common.ui.client.mvc.ModelRequestCallback;
import org.kuali.student.common.ui.client.mvc.history.HistoryManager;
import org.kuali.student.core.assembly.data.Data;
import org.kuali.student.lum.program.client.ProgramController;
import org.kuali.student.lum.program.client.events.ModelLoadedEvent;
import org.kuali.student.lum.program.client.properties.ProgramProperties;
import org.kuali.student.lum.program.client.rpc.AbstractCallback;

import java.util.List;

/**
 * @author Igor
 */
public abstract class VariationController extends ProgramController {

    private String name;

    /**
     * Constructor.
     *
     * @param programModel
     * @param eventBus
     */
    public VariationController(String name, DataModel programModel, ViewContext viewContext, HandlerManager eventBus) {
        super("", programModel, viewContext, eventBus);
        this.name = name;
        setName(getProgramName());
    }

    @Override
    protected void configureView() {
        setStatus();
        super.configureView();
        setContentTitle("Specialization of " + getProgramName());
        addContentWidget(createParentAnchor());
        addContentWidget(createCommentPanel());
    }

    private Widget createParentAnchor() {
        Anchor anchor = new Anchor("Parent Program: " + name);
        anchor.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                HistoryManager.navigate("/HOME/CURRICULUM_HOME/PROGRAM_VIEW", getViewContext());
            }
        });
        return anchor;
    }

    @Override
    public void collectBreadcrumbNames(List<String> names) {
        names.add(name + "@" + HistoryManager.appendContext("/HOME/CURRICULUM_HOME/PROGRAM_VIEW", getViewContext()));
        super.collectBreadcrumbNames(names);
    }

    /**
     * Loads data model from the server.
     *
     * @param callback we have to invoke this callback when model is loaded or failed.
     */
    @Override
    protected void loadModel(final ModelRequestCallback<DataModel> callback) {
        programRemoteService.getData(getViewContext().getId(), new AbstractCallback<Data>(ProgramProperties.get().common_retrievingData()) {

            @Override
            public void onFailure(Throwable caught) {
                super.onFailure(caught);
                callback.onRequestFail(caught);
            }

            @Override
            public void onSuccess(Data result) {
                super.onSuccess(result);
                programModel.setRoot(result);
                callback.onModelReady(programModel);
                eventBus.fireEvent(new ModelLoadedEvent(programModel));
            }
        });
    }
}
