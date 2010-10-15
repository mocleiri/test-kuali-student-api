package org.kuali.student.lum.program.client.major.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import org.kuali.student.common.ui.client.application.ViewContext;
import org.kuali.student.common.ui.client.mvc.DataModel;
import org.kuali.student.common.ui.client.mvc.history.HistoryManager;
import org.kuali.student.lum.common.client.widgets.AppLocations;
import org.kuali.student.lum.common.client.widgets.DropdownList;
import org.kuali.student.lum.program.client.ProgramSections;
import org.kuali.student.lum.program.client.events.MajorViewEvent;
import org.kuali.student.lum.program.client.events.MajorViewEventHandler;
import org.kuali.student.lum.program.client.events.ModelLoadedEvent;
import org.kuali.student.lum.program.client.events.ModelLoadedEventHandler;
import org.kuali.student.lum.program.client.major.ActionType;
import org.kuali.student.lum.program.client.major.MajorController;


public class MajorViewController extends MajorController {

    private DropdownList actionBox = new DropdownList(ActionType.getValues());

    /**
     * Constructor.
     *
     * @param programModel
     */
    public MajorViewController(String name, DataModel programModel, ViewContext viewContext, HandlerManager eventBus) {
        super(name, programModel, viewContext, eventBus);
        configurer = GWT.create(MajorViewConfigurer.class);
        initHandlers();
    }

    private void initHandlers() {
        actionBox.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                ActionType actionType = ActionType.of(actionBox.getSelectedValue());
                if (actionType == ActionType.MODIFY) {
                    HistoryManager.navigate(AppLocations.Locations.EDIT_PROGRAM.getLocation(), getViewContext());
                }
            }
        });
        eventBus.addHandler(MajorViewEvent.TYPE, new MajorViewEventHandler() {
            @Override
            public void onEvent(MajorViewEvent event) {
                actionBox.setSelectedIndex(0);
            }
        });
        eventBus.addHandler(ModelLoadedEvent.TYPE, new ModelLoadedEventHandler() {
            @Override
            public void onEvent(ModelLoadedEvent event) {
               showView(ProgramSections.VIEW_ALL);
            }
        });
    }

    @Override
    protected void configureView() {
        super.configureView();
        addContentWidget(actionBox);
        initialized = true;
    }
}
