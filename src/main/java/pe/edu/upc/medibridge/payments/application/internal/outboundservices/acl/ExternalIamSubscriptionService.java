package pe.edu.upc.medibridge.payments.application.internal.outboundservices.acl;

public interface ExternalIamSubscriptionService {
    void notifySubscriptionActivated(Long userId, Integer subscriptionId);
}
