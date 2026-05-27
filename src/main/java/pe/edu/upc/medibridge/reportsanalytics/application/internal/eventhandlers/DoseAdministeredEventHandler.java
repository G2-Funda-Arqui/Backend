package pe.edu.upc.medibridge.reportsanalytics.application.internal.eventhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DoseAdministeredEventHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(DoseAdministeredEventHandler.class);

    @EventListener(condition = "#event.class.simpleName == 'DoseAdministeredEvent'")
    public void on(Object event) {
        LOGGER.info("Dose administered event received by reports analytics: {}", event);
    }
}
