package org.kuali.student.lum.program.client.configuration;

import com.google.gwt.user.client.ui.Label;
import org.kuali.student.common.ui.client.configurable.mvc.sections.HorizontalSection;
import org.kuali.student.common.ui.client.configurable.mvc.views.VerticalSectionView;
import org.kuali.student.common.ui.client.mvc.View;
import org.kuali.student.common.ui.client.widgets.field.layout.element.MessageKeyInfo;
import org.kuali.student.lum.program.client.ProgramConfigurer;
import org.kuali.student.lum.program.client.ProgramConstants;
import org.kuali.student.lum.program.client.ProgramController;
import org.kuali.student.lum.program.client.ProgramSections;
import org.kuali.student.lum.program.client.configuration.base.AbstractConfiguration;
import org.kuali.student.lum.program.client.configuration.base.EditableConfiguration;
import org.kuali.student.lum.program.client.properties.ProgramProperties;

/**
 * @author Igor
 */
public class ProgramInformationConfiguration extends AbstractConfiguration<ProgramConfigurer> implements EditableConfiguration<ProgramConfigurer> {

    private VerticalSectionView showView;

    private VerticalSectionView editView;

    public ProgramInformationConfiguration() {
    }

    @Override
    public View getEditView() {
        if (editView == null) {
            createEditView();
        }
        return editView;
    }

    @Override
    public View getView() {
        if (showView == null) {
            createShowView();
        }
        return showView;
    }

    private void createEditView() {
        editView = new VerticalSectionView(ProgramSections.PROGRAM_DETAILS_EDIT, ProgramProperties.get().program_menu_sections_programDetails(), ProgramController.PROGRAM_MODEL_ID);
        HorizontalSection section = new HorizontalSection();
        configurer.addField(section, ProgramConstants.SHORT_TITLE, new MessageKeyInfo(ProgramProperties.get().program_menu_sections_programDetails_programTitle()));
        configurer.addField(section, ProgramConstants.LONG_TITLE, new MessageKeyInfo(ProgramProperties.get().program_menu_sections_programDetails_longTitle()));
        editView.addSection(section);
    }

    private void createShowView() {
        showView = new VerticalSectionView(ProgramSections.PROGRAM_DETAILS_VIEW, ProgramProperties.get().program_menu_sections_programDetails(), ProgramController.PROGRAM_MODEL_ID);
        HorizontalSection section = new HorizontalSection();
        configurer.addField(section, ProgramConstants.SHORT_TITLE, new MessageKeyInfo(ProgramProperties.get().program_menu_sections_programDetails_programTitle()), new Label(configurer.getData(ProgramConstants.SHORT_TITLE)));
        configurer.addField(section, ProgramConstants.LONG_TITLE, new MessageKeyInfo(ProgramProperties.get().program_menu_sections_programDetails_longTitle()), new Label(configurer.getData(ProgramConstants.LONG_TITLE)));
        showView.addSection(section);
    }
}
