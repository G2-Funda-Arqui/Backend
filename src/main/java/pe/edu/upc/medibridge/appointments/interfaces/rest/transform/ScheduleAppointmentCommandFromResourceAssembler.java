package pe.edu.upc.medibridge.appointments.interfaces.rest.transform;

import pe.edu.upc.medibridge.appointments.domain.model.commands.ScheduleAppointmentCommand;
import pe.edu.upc.medibridge.appointments.domain.model.exceptions.InvalidAppointmentRequestException;
import pe.edu.upc.medibridge.appointments.domain.model.valueobjects.AppointmentType;
import pe.edu.upc.medibridge.appointments.interfaces.rest.resources.ScheduleAppointmentResource;

import java.util.Locale;

public class ScheduleAppointmentCommandFromResourceAssembler {

    public static ScheduleAppointmentCommand toCommandFromResource(ScheduleAppointmentResource resource) {
        return new ScheduleAppointmentCommand(
                resource.patientId(),
                resource.scheduledByUserId(),
                toAppointmentType(resource.appointmentType()),
                resource.startsAt(),
                resource.durationInMinutes(),
                resource.reason());
    }

    private static AppointmentType toAppointmentType(String appointmentType) {
        if (appointmentType == null || appointmentType.isBlank()) {
            throw new InvalidAppointmentRequestException("Appointment type is required");
        }
        try {
            return AppointmentType.valueOf(appointmentType.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException exception) {
            throw new InvalidAppointmentRequestException("Invalid appointment type: " + appointmentType);
        }
    }
}
