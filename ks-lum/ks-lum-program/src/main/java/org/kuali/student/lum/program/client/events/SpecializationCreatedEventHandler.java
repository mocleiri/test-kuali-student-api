package org.kuali.student.lum.program.client.events;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Igor
 */
public interface SpecializationCreatedEventHandler extends EventHandler {
    void onEvent(SpecializationCreatedEvent event);
}
