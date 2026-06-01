package pe.edu.upc.medibridge.payments.domain.model.events;

import java.time.Instant;

public record SubscriptionRenewedEvent(Integer subscriptionId, Long userId, Instant occurredAt, int version) {
    public SubscriptionRenewedEvent(Integer subscriptionId, Long userId) {
        this(subscriptionId, userId, Instant.now(), 1);
    }
}
