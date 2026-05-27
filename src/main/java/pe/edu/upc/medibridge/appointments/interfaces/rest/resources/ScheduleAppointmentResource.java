package pe.edu.upc.medibridge.appointments.interfaces.rest.resources;

import java.time.LocalDateTime;

public record ScheduleAppointmentResource(
        Long patientId,
        Long scheduledByUserId,
        String appointmentType,
        LocalDateTime startsAt,
        Integer durationInMinutes,
        String reason) {
}
