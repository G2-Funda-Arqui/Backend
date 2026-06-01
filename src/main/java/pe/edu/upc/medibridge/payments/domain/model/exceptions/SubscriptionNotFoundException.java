package pe.edu.upc.medibridge.payments.domain.model.exceptions;

public class SubscriptionNotFoundException extends RuntimeException {
    public SubscriptionNotFoundException(Integer subscriptionId) {
        super("Subscription not found with id: " + subscriptionId);
    }
}
