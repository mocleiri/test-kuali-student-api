package org.kuali.student.lum.program.client.core.view;

import org.kuali.student.common.ui.client.configurable.mvc.views.SectionView;
import org.kuali.student.common.ui.client.configurable.mvc.views.VerticalSectionView;
import org.kuali.student.common.ui.client.widgets.field.layout.element.MessageKeyInfo;
import org.kuali.student.common.ui.client.widgets.menus.KSListPanel;
import org.kuali.student.lum.common.client.configuration.AbstractSectionConfiguration;
import org.kuali.student.lum.common.client.lo.TreeStringBinding;
import org.kuali.student.lum.program.client.ProgramConstants;
import org.kuali.student.lum.program.client.ProgramSections;
import org.kuali.student.lum.program.client.core.CoreEditableHeader;
import org.kuali.student.lum.program.client.properties.ProgramProperties;
import org.kuali.student.lum.program.client.widgets.EditableHeader;

/**
 * @author Igor
 */
public class CoreLearningObjectivesViewConfiguration extends AbstractSectionConfiguration {

     public static CoreLearningObjectivesViewConfiguration create() {
        return new CoreLearningObjectivesViewConfiguration(new VerticalSectionView(ProgramSections.LEARNING_OBJECTIVES_VIEW, ProgramProperties.get().program_menu_sections_learningObjectives(), ProgramConstants.PROGRAM_MODEL_ID));
    }

    public static CoreLearningObjectivesViewConfiguration createSpecial() {
        String title = ProgramProperties.get().program_menu_sections_learningObjectives();
        return new CoreLearningObjectivesViewConfiguration(new VerticalSectionView(ProgramSections.LEARNING_OBJECTIVES_VIEW, title, ProgramConstants.PROGRAM_MODEL_ID, new CoreEditableHeader(title, ProgramSections.LEARNING_OBJECTIVES_EDIT)));
    }

    private CoreLearningObjectivesViewConfiguration(SectionView sectionView) {
        rootSection = sectionView;
    }
    protected void buildLayout() {
        configurer.addReadOnlyField(rootSection, ProgramConstants.LEARNING_OBJECTIVES, new MessageKeyInfo(""), new KSListPanel()).setWidgetBinding(new TreeStringBinding());
    }
}
