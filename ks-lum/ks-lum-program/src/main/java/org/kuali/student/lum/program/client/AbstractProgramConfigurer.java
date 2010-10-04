package org.kuali.student.lum.program.client;

import org.kuali.student.common.ui.client.configurable.mvc.Configurer;
import org.kuali.student.lum.common.client.configuration.AbstractControllerConfiguration;
import org.kuali.student.lum.common.client.configuration.Configuration;
import org.kuali.student.lum.common.client.configuration.ConfigurationManager;
import org.kuali.student.lum.program.client.major.edit.ProgramSummaryConfiguration;
import org.kuali.student.lum.program.client.properties.ProgramProperties;

import java.util.ArrayList;

/**
 * @author Igor
 */
public abstract class AbstractProgramConfigurer extends Configurer {

    private ProgramController programController;

    protected ConfigurationManager programSectionConfigManager;

    public void configure(ProgramController viewController) {
        this.programController = viewController;
        configureProgramSections();
    }

    /**
     * Configures menu for Program Sections
     */
    private void configureProgramSections() {
        String programSectionLabel = ProgramProperties.get().program_menu_sections();
        programController.addMenu(programSectionLabel);
        ArrayList<Configuration> configurations = programSectionConfigManager.getConfigurations();
        for (Configuration configuration : configurations) {
            if (configuration instanceof AbstractControllerConfiguration) {
                ((AbstractControllerConfiguration) configuration).setController(programController);
            }
            if (configuration instanceof ProgramSummaryConfiguration) {
                programController.addSpecialMenuItem(configuration.getView(), programSectionLabel);
            } else {
                programController.addMenuItem(programSectionLabel, configuration.getView());
            }
        }
    }

    public ProgramController getProgramController() {
        return programController;
    }
}
