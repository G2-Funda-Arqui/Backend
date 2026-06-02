package pe.edu.upc.medibridge.reportsanalytics.application.internal.outboundservices.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.medibridge.medicationmanagement.interfaces.rest.acl.MedicationContextFacade;

@Service
public class MedicationExternalService implements ExternalMedicationService {
    private final MedicationContextFacade medicationContextFacade;

    public MedicationExternalService(MedicationContextFacade medicationContextFacade) {
        this.medicationContextFacade = medicationContextFacade;
    }

    @Override
    public String getMedicationSummary(Long patientId) {
        return medicationContextFacade.fetchMedicationSummaryByPatientId(patientId);
    }
}
