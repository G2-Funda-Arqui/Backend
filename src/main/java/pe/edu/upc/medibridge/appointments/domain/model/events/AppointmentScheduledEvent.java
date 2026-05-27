package pe.edu.upc.medibridge.appointments.domain.model.events;

import java.time.Instant;
import java.time.LocalDateTime;

public record AppointmentScheduledEvent(
        Integer appointmentId,
        Long patientId,
        Long scheduledByUserId,
        String appointmentType,
        LocalDateTime startsAt,
        LocalDateTime endsAt,
        Instant occurredAt,
        int version) {

    public AppointmentScheduledEvent(
            Integer appointmentId,
            Long patientId,
            Long scheduledByUserId,
            String appointmentType,
            LocalDateTime startsAt,
            LocalDateTime endsAt) {
        this(appointmentId, patientId, scheduledByUserId, appointmentType, startsAt, endsAt, Instant.now(), 1);
    }
}
