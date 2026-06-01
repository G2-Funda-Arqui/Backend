package pe.edu.upc.medibridge.payments.domain.model.events;

import java.time.Instant;

public record SubscriptionCancelledEvent(Integer subscriptionId, Long userId, Instant occurredAt, int version) {
    public SubscriptionCancelledEvent(Integer subscriptionId, Long userId) {
        this(subscriptionId, userId, Instant.now(), 1);
    }
}
