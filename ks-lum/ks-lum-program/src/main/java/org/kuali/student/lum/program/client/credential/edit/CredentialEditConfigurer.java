package org.kuali.student.lum.program.client.credential.edit;

import org.kuali.student.lum.common.client.configuration.ConfigurationManager;
import org.kuali.student.lum.program.client.AbstractProgramConfigurer;
import org.kuali.student.lum.program.client.major.edit.LearningObjectivesEditConfiguration;

/**
 * @author Igor
 */
public class CredentialEditConfigurer extends AbstractProgramConfigurer {

    public CredentialEditConfigurer() {
        programSectionConfigManager = new ConfigurationManager(this);
        programSectionConfigManager.registerConfiguration(new CredentialInformationEditConfiguration());
        programSectionConfigManager.registerConfiguration(new CredentialManagingBodiesEditConfiguration());
        programSectionConfigManager.registerConfiguration(new CredentialCatalogDetailsEditConfiguration());
        programSectionConfigManager.registerConfiguration(new CredentialRequirementsEditConfiguration());
        programSectionConfigManager.registerConfiguration(new LearningObjectivesEditConfiguration());
        programSectionConfigManager.registerConfiguration(new CredentialDocsEditConfiguration());
        programSectionConfigManager.registerConfiguration(new CredentialSummaryConfiguration());
    }
}
