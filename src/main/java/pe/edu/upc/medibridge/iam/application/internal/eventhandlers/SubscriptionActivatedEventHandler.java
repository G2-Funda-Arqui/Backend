package pe.edu.upc.medibridge.iam.application.internal.eventhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionActivatedEventHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionActivatedEventHandler.class);

    @EventListener(condition = "#event.class.simpleName == 'SubscriptionActivatedEvent'")
    public void on(Object event) {
        LOGGER.info("Subscription activation event received by IAM: {}", event);
    }
}
