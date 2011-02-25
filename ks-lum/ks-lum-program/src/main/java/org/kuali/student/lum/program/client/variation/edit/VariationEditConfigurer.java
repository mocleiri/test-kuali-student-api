package org.kuali.student.lum.program.client.variation.edit;

import org.kuali.student.lum.common.client.configuration.ConfigurationManager;
import org.kuali.student.lum.program.client.AbstractProgramConfigurer;
import org.kuali.student.lum.program.client.major.edit.CatalogInformationEditConfiguration;
import org.kuali.student.lum.program.client.major.edit.LearningObjectivesEditConfiguration;
import org.kuali.student.lum.program.client.major.edit.ManagingBodiesEditConfiguration;
import org.kuali.student.lum.program.client.major.edit.ProgramRequirementsEditConfiguration;
import org.kuali.student.lum.program.client.major.edit.SupportingDocsEditConfiguration;

/**
 * @author Igor
 */
public class VariationEditConfigurer extends AbstractProgramConfigurer {

    public VariationEditConfigurer() {
        programSectionConfigManager = new ConfigurationManager(this);
        programSectionConfigManager.registerConfiguration(new VariationInformationEditConfiguration());
        programSectionConfigManager.registerConfiguration(new ManagingBodiesEditConfiguration());
        programSectionConfigManager.registerConfiguration(new CatalogInformationEditConfiguration());
        programSectionConfigManager.registerConfiguration(new ProgramRequirementsEditConfiguration());
        programSectionConfigManager.registerConfiguration(new LearningObjectivesEditConfiguration());
        programSectionConfigManager.registerConfiguration(new SupportingDocsEditConfiguration());
        programSectionConfigManager.registerConfiguration(new VariationSummaryConfiguration());
    }
}