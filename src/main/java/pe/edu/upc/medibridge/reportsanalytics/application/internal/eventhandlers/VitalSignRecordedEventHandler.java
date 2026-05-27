package pe.edu.upc.medibridge.reportsanalytics.application.internal.eventhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class VitalSignRecordedEventHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(VitalSignRecordedEventHandler.class);

    @EventListener(condition = "#event.class.simpleName == 'VitalSignRecordedEvent'")
    public void on(Object event) {
        LOGGER.info("Vital sign event received by reports analytics: {}", event);
    }
}
