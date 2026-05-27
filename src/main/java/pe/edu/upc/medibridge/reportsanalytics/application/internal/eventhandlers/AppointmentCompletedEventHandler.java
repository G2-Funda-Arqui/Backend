package pe.edu.upc.medibridge.reportsanalytics.application.internal.eventhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AppointmentCompletedEventHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentCompletedEventHandler.class);

    @EventListener(condition = "#event.class.simpleName == 'AppointmentCompletedEvent'")
    public void on(Object event) {
        LOGGER.info("Appointment completed event received by reports analytics: {}", event);
    }
}
