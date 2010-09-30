package org.kuali.student.lum.program.client.major.view;

import org.kuali.student.common.ui.client.mvc.Controller;
import org.kuali.student.lum.common.client.configuration.AbstractControllerConfiguration;
import org.kuali.student.lum.program.client.ProgramSections;
import org.kuali.student.lum.program.client.properties.ProgramProperties;
import org.kuali.student.lum.program.client.requirements.ProgramRequirementsViewController;

public class ProgramRequirementsViewConfiguration extends AbstractControllerConfiguration {

    private ProgramRequirementsViewController progReqcontroller;

    public ProgramRequirementsViewConfiguration() {
        progReqcontroller = new ProgramRequirementsViewController(controller, ProgramProperties.get().program_menu_sections_requirements(),
                ProgramSections.PROGRAM_REQUIREMENTS_VIEW, true);
        rootSection = progReqcontroller.getProgramRequirementsView();
    }

    @Override
    protected void buildLayout() {
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
        if (progReqcontroller != null) {
            progReqcontroller.setParentController(controller);
        }
    }
}
