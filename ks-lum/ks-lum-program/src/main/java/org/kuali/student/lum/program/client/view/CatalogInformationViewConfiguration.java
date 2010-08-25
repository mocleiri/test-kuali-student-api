package org.kuali.student.lum.program.client.view;

import org.kuali.student.common.ui.client.configurable.mvc.SectionTitle;
import org.kuali.student.common.ui.client.configurable.mvc.sections.TableSection;
import org.kuali.student.common.ui.client.configurable.mvc.views.VerticalSectionView;
import org.kuali.student.common.ui.client.widgets.field.layout.element.MessageKeyInfo;
import org.kuali.student.lum.program.client.ProgramConstants;
import org.kuali.student.lum.program.client.ProgramSections;
import org.kuali.student.lum.program.client.framework.AbstractSectionConfiguration;
import org.kuali.student.lum.program.client.properties.ProgramProperties;

/**
 * @author Igor
 */
public class CatalogInformationViewConfiguration extends AbstractSectionConfiguration<ProgramViewConfigurer> {

    public CatalogInformationViewConfiguration() {
        rootSection = new VerticalSectionView(ProgramSections.CATALOG_INFO_VIEW, ProgramProperties.get().program_menu_sections_catalogInfo(), ProgramConstants.PROGRAM_MODEL_ID);
    }

    @Override
    protected void buildLayout() {
        TableSection section = new TableSection(SectionTitle.generateEmptyTitle());
        configurer.addReadOnlyField(section, ProgramConstants.DESCRIPTION + "/plain", new MessageKeyInfo(ProgramProperties.get().catalogInformation_descr()));
        configurer.addReadOnlyField(section, ProgramConstants.CATALOG_DESCRIPTION, new MessageKeyInfo(ProgramProperties.get().catalogInformation_catalogDescr()));
        configurer.addReadOnlyField(section, ProgramConstants.CORE_FACULTY_MEMBERS, new MessageKeyInfo(ProgramProperties.get().catalogInformation_publishedInstructors()));
        configurer.addReadOnlyField(section, ProgramConstants.PUBLICATION_TARGETS, new MessageKeyInfo(ProgramProperties.get().catalogInformation_catalogPublicationTargets()));
        configurer.addReadOnlyField(section, ProgramConstants.FULL_PART_TIME, new MessageKeyInfo(ProgramProperties.get().catalogInformation_intensity()));
        configurer.addReadOnlyField(section, ProgramConstants.DURATION, new MessageKeyInfo(ProgramProperties.get().catalogInformation_stdDuration()));
        configurer.addReadOnlyField(section, ProgramConstants.MORE_INFORMATION, new MessageKeyInfo(ProgramProperties.get().catalogInformation_referenceUrl()));
        rootSection.addSection(section);
    }
}
