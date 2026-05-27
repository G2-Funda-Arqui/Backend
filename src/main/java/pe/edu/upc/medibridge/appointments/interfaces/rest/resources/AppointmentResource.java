package pe.edu.upc.medibridge.appointments.interfaces.rest.resources;

import java.time.LocalDateTime;

public record AppointmentResource(
        int id,
        Long patientId,
        Long scheduledByUserId,
        String appointmentType,
        String status,
        LocalDateTime startsAt,
        LocalDateTime endsAt,
        String reason) {
}
