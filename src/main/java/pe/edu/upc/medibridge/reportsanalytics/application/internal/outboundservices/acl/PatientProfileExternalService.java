package pe.edu.upc.medibridge.reportsanalytics.application.internal.outboundservices.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.medibridge.profiles.interfaces.rest.acl.PatientProfileContextFacade;

import java.util.Optional;

@Service
public class PatientProfileExternalService implements ExternalPatientProfileService {
    private final PatientProfileContextFacade patientProfileContextFacade;

    public PatientProfileExternalService(PatientProfileContextFacade patientProfileContextFacade) {
        this.patientProfileContextFacade = patientProfileContextFacade;
    }

    @Override
    public boolean patientExists(Long patientId) {
        return patientProfileContextFacade.patientExists(patientId);
    }

    @Override
    public Optional<String> getPatientFullName(Long patientId) {
        return patientProfileContextFacade.fetchPatientFullNameById(patientId);
    }
}
