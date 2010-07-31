package org.kuali.student.lum.program.client.configuration.base;

import org.kuali.student.common.ui.client.configurable.mvc.Configurer;

/**
 * Skeletal implementation of {@link org.kuali.student.lum.program.client.configuration.base.Configuration}
 *
 * @author Igor
 */
public abstract class AbstractConfiguration<T extends Configurer> implements Configuration<T> {

    protected T configurer;

    public void setConfigurer(T configurer) {
        this.configurer = configurer;
    }
}
