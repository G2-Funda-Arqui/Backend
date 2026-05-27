package pe.edu.upc.medibridge.appointments.domain.services;

import pe.edu.upc.medibridge.appointments.domain.model.aggregates.Appointment;
import pe.edu.upc.medibridge.appointments.domain.model.commands.ScheduleAppointmentCommand;

import java.util.Optional;

public interface AppointmentCommandService {
    Optional<Appointment> handle(ScheduleAppointmentCommand command);
}
