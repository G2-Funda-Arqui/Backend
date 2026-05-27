package pe.edu.upc.medibridge.medicationmanagement.application.eventhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PatientRegisteredEventHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientRegisteredEventHandler.class);

    @EventListener(condition = "#event.class.simpleName == 'PatientRegisteredEvent'")
    public void on(Object event) {
        LOGGER.info("Patient registration event received by medication management: {}", event);
    }
}
