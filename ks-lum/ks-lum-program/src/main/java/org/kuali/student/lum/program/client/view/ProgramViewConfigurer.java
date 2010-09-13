package org.kuali.student.lum.program.client.view;

import com.google.gwt.core.client.GWT;
import org.kuali.student.lum.program.client.AbstractProgramConfigurer;
import org.kuali.student.lum.program.client.edit.ProgramRequirementsEditConfiguration;
import org.kuali.student.lum.program.client.framework.Configuration;
import org.kuali.student.lum.program.client.framework.ConfigurationManager;
import org.kuali.student.lum.program.client.framework.ConfigurationRegistry;
import org.kuali.student.lum.program.client.framework.EditableConfiguration;

/**
 * This class represents configuration for  {@link org.kuali.student.lum.program.client.view.ProgramViewController}.
 *
 * @author Igor
 */
public class ProgramViewConfigurer extends AbstractProgramConfigurer {

    public ProgramViewConfigurer() {
        programSectionConfigManager = new ConfigurationManager(this);
        programSectionConfigManager.registerConfiguration(GWT.<Configuration>create(ProgramInformationViewConfiguration.class));
        programSectionConfigManager.registerConfiguration(GWT.<Configuration>create(ManagingBodiesViewConfiguration.class));
        programSectionConfigManager.registerConfiguration(GWT.<Configuration>create(SpecializationsViewConfiguration.class));
        programSectionConfigManager.registerConfiguration(GWT.<Configuration>create(CatalogInformationViewConfiguration.class));
        programSectionConfigManager.registerConfiguration(GWT.<Configuration>create(ProgramRequirementsEditConfiguration.class));  //TODO change to View
        programSectionConfigManager.registerConfiguration(GWT.<Configuration>create(LearningObjectivesViewConfiguration.class));
        programSectionConfigManager.registerConfiguration(GWT.<Configuration>create(ViewAllSectionConfiguration.class));
    }
}
