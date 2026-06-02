package pe.edu.upc.medibridge.payments.application.internal.outboundservices.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.medibridge.iam.interfaces.rest.acl.IamContextFacade;

@Service
public class IamExternalSubscriptionService implements ExternalIamSubscriptionService {
    private final IamContextFacade iamContextFacade;

    public IamExternalSubscriptionService(IamContextFacade iamContextFacade) {
        this.iamContextFacade = iamContextFacade;
    }

    public boolean userExists(Long userId) {
        return iamContextFacade.userExists(userId);
    }

    @Override
    public void notifySubscriptionActivated(Long userId, Integer subscriptionId) {
        iamContextFacade.markSubscriptionActivated(userId, subscriptionId);
    }
}
