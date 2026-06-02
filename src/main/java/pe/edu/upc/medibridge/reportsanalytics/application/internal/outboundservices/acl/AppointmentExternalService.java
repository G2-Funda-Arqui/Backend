package pe.edu.upc.medibridge.reportsanalytics.application.internal.outboundservices.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.medibridge.appointments.interfaces.rest.acl.AppointmentContextFacade;

@Service
public class AppointmentExternalService implements ExternalAppointmentService {
    private final AppointmentContextFacade appointmentContextFacade;

    public AppointmentExternalService(AppointmentContextFacade appointmentContextFacade) {
        this.appointmentContextFacade = appointmentContextFacade;
    }

    @Override
    public String getAppointmentSummary(Long patientId) {
        return appointmentContextFacade.fetchAppointmentSummaryByPatientId(patientId);
    }
}
