package pe.edu.upc.medibridge.profiles.interfaces.rest.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.medibridge.iam.domain.model.queries.GetUserByIdQuery;
import pe.edu.upc.medibridge.iam.domain.services.UserQueryService;
import pe.edu.upc.medibridge.profiles.application.internal.outboundservices.acl.ExternalIamContextService;

@Service
public class IamContextFacade implements ExternalIamContextService {

    private final UserQueryService userQueryService;

    public IamContextFacade(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    @Override
    public boolean userExists(Long userId) {
        return userId != null && userQueryService.handle(new GetUserByIdQuery(userId)).isPresent();
    }
}
