package pe.edu.upc.medibridge.appointments.interfaces.rest.transform;

import pe.edu.upc.medibridge.appointments.domain.model.aggregates.Appointment;
import pe.edu.upc.medibridge.appointments.interfaces.rest.resources.AppointmentResource;

public class AppointmentResourceFromEntityAssembler {

    public static AppointmentResource toResourceFromEntity(Appointment appointment) {
        return new AppointmentResource(
                appointment.getId(),
                appointment.getPatientId(),
                appointment.getScheduledByUserId(),
                appointment.getAppointmentType().name(),
                appointment.getStatus().name(),
                appointment.getTimeSlot().getStartsAt(),
                appointment.getTimeSlot().getEndsAt(),
                appointment.getReason());
    }
}
