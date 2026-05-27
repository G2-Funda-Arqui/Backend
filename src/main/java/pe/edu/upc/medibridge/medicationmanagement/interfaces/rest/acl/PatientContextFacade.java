package pe.edu.upc.medibridge.medicationmanagement.interfaces.rest.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.medibridge.medicationmanagement.application.outboundservices.acl.ExternalPatientContextService;

@Service
public class PatientContextFacade implements ExternalPatientContextService {
    @Override
    public boolean patientExists(Long patientId) {
        return patientId != null && patientId > 0;
    }
}
