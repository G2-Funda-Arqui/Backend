package pe.edu.upc.medibridge.appointments.domain.model.commands;

import pe.edu.upc.medibridge.appointments.domain.model.valueobjects.AppointmentType;

import java.time.LocalDateTime;

public record ScheduleAppointmentCommand(
        Long patientId,
        Long scheduledByUserId,
        AppointmentType appointmentType,
        LocalDateTime startsAt,
        Integer durationInMinutes,
        String reason) {
}
