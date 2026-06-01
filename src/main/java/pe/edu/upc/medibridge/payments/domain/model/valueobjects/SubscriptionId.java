package pe.edu.upc.medibridge.payments.domain.model.valueobjects;

public record SubscriptionId(Integer value) {
    public SubscriptionId {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("Subscription id must be a positive number");
        }
    }
}
