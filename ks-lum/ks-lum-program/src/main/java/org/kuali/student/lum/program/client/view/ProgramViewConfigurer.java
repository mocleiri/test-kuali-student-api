package org.kuali.student.lum.program.client.view;

import org.kuali.student.lum.common.client.configuration.Configuration;
import org.kuali.student.lum.common.client.configuration.ConfigurationManager;
import org.kuali.student.lum.program.client.AbstractProgramConfigurer;

import com.google.gwt.core.client.GWT;

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
        programSectionConfigManager.registerConfiguration(GWT.<Configuration>create(ProgramRequirementsViewConfiguration.class));
        programSectionConfigManager.registerConfiguration(GWT.<Configuration>create(LearningObjectivesViewConfiguration.class));
        programSectionConfigManager.registerConfiguration(GWT.<Configuration>create(SupportingDocsViewConfiguration.class));
        programSectionConfigManager.registerConfiguration(GWT.<Configuration>create(ViewAllSectionConfiguration.class));
    }
}
