package pe.edu.upc.medibridge.reportsanalytics.application.internal.outboundservices.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.medibridge.healthmonitoring.interfaces.rest.acl.HealthMonitoringContextFacade;

@Service
public class HealthMonitoringExternalService implements ExternalHealthMonitoringService {

    private final HealthMonitoringContextFacade healthMonitoringContextFacade;

    public HealthMonitoringExternalService(HealthMonitoringContextFacade healthMonitoringContextFacade) {
        this.healthMonitoringContextFacade = healthMonitoringContextFacade;
    }

    @Override
    public String getPatientClinicalSummary(Long patientId) {
        return healthMonitoringContextFacade.fetchPatientClinicalSummaryByPatientId(patientId);
    }
}
