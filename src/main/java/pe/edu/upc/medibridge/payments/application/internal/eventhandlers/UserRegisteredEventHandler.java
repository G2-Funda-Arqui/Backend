package pe.edu.upc.medibridge.payments.application.internal.eventhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegisteredEventHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRegisteredEventHandler.class);

    @EventListener(condition = "#event.class.simpleName == 'UserRegisteredEvent'")
    public void on(Object event) {
        LOGGER.info("User registration event received by payments: {}", event);
    }
}
