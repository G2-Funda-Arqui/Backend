package pe.edu.upc.medibridge.appointments.application.internal.commandservices;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pe.edu.upc.medibridge.appointments.application.internal.outboundservices.acl.ExternalPatientContextService;
import pe.edu.upc.medibridge.appointments.domain.model.aggregates.Appointment;
import pe.edu.upc.medibridge.appointments.domain.model.commands.ScheduleAppointmentCommand;
import pe.edu.upc.medibridge.appointments.domain.model.events.AppointmentScheduledEvent;
import pe.edu.upc.medibridge.appointments.domain.model.exceptions.InvalidAppointmentRequestException;
import pe.edu.upc.medibridge.appointments.domain.model.exceptions.InvalidAppointmentTimeSlotException;
import pe.edu.upc.medibridge.appointments.domain.model.exceptions.InvalidPatientReferenceException;
import pe.edu.upc.medibridge.appointments.domain.model.exceptions.TimeSlotNotAvailableException;
import pe.edu.upc.medibridge.appointments.domain.model.valueobjects.AppointmentStatus;
import pe.edu.upc.medibridge.appointments.domain.model.valueobjects.TimeSlot;
import pe.edu.upc.medibridge.appointments.domain.services.AppointmentCommandService;
import pe.edu.upc.medibridge.appointments.infrastructure.persistence.jpa.repositories.AppointmentRepository;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentCommandServiceImpl implements AppointmentCommandService {

    private static final LocalTime OPENING_TIME = LocalTime.of(9, 0);
    private static final LocalTime CLOSING_TIME = LocalTime.of(18, 0);
    private static final int SLOT_DURATION_MINUTES = 60;
    private static final List<AppointmentStatus> ACTIVE_STATUSES =
            List.of(AppointmentStatus.SCHEDULED, AppointmentStatus.CONFIRMED);

    private final AppointmentRepository appointmentRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final ExternalPatientContextService externalPatientContextService;

    public AppointmentCommandServiceImpl(
            AppointmentRepository appointmentRepository,
            ApplicationEventPublisher eventPublisher,
            ObjectProvider<ExternalPatientContextService> externalPatientContextServiceProvider) {
        this.appointmentRepository = appointmentRepository;
        this.eventPublisher = eventPublisher;
        this.externalPatientContextService = externalPatientContextServiceProvider.getIfAvailable();
    }

    @Override
    public Optional<Appointment> handle(ScheduleAppointmentCommand command) {
        validateRequiredFields(command);

        var timeSlot = buildAndValidateTimeSlot(command);
        validatePatientReferenceIfAvailable(command.patientId());
        validateAvailability(command.patientId(), timeSlot);

        var appointment = new Appointment(command, timeSlot);
        var savedAppointment = appointmentRepository.save(appointment);

        eventPublisher.publishEvent(new AppointmentScheduledEvent(
                savedAppointment.getId(),
                savedAppointment.getPatientId(),
                savedAppointment.getScheduledByUserId(),
                savedAppointment.getAppointmentType().name(),
                savedAppointment.getTimeSlot().getStartsAt(),
                savedAppointment.getTimeSlot().getEndsAt()));

        return Optional.of(savedAppointment);
    }

    private void validateRequiredFields(ScheduleAppointmentCommand command) {
        if (command.patientId() == null || command.patientId() <= 0) {
            throw new InvalidAppointmentRequestException("Patient id is required");
        }
        if (command.scheduledByUserId() == null || command.scheduledByUserId() <= 0) {
            throw new InvalidAppointmentRequestException("Scheduled by user id is required");
        }
        if (command.appointmentType() == null) {
            throw new InvalidAppointmentRequestException("Appointment type is required");
        }
        if (command.startsAt() == null) {
            throw new InvalidAppointmentRequestException("Appointment start date is required");
        }
        if (command.durationInMinutes() == null || command.durationInMinutes() <= 0) {
            throw new InvalidAppointmentRequestException("Appointment duration is required");
        }
    }

    private TimeSlot buildAndValidateTimeSlot(ScheduleAppointmentCommand command) {
        if (command.durationInMinutes() != SLOT_DURATION_MINUTES) {
            throw new InvalidAppointmentTimeSlotException("Appointments must use 60-minute slots for now");
        }
        if (!command.startsAt().isAfter(LocalDateTime.now())) {
            throw new InvalidAppointmentTimeSlotException("Appointments must be scheduled in the future");
        }

        var endsAt = command.startsAt().plusMinutes(command.durationInMinutes());
        var timeSlot = new TimeSlot(command.startsAt(), endsAt);

        if (!timeSlot.getStartsAt().toLocalDate().equals(timeSlot.getEndsAt().toLocalDate())) {
            throw new InvalidAppointmentTimeSlotException("Appointments must start and end on the same day");
        }
        if (timeSlot.getStartsAt().getDayOfWeek() == DayOfWeek.SUNDAY) {
            throw new InvalidAppointmentTimeSlotException("Appointments are not available on Sundays");
        }
        if (timeSlot.getStartsAt().toLocalTime().isBefore(OPENING_TIME)
                || timeSlot.getEndsAt().toLocalTime().isAfter(CLOSING_TIME)) {
            throw new InvalidAppointmentTimeSlotException("Appointments are available from 09:00 to 18:00");
        }

        return timeSlot;
    }

    private void validatePatientReferenceIfAvailable(Long patientId) {
        if (externalPatientContextService == null) {
            return;
        }
        if (!externalPatientContextService.existsByPatientId(patientId)) {
            throw new InvalidPatientReferenceException(patientId);
        }
    }

    private void validateAvailability(Long patientId, TimeSlot timeSlot) {
        var overlaps = appointmentRepository.existsOverlappingAppointment(
                patientId,
                ACTIVE_STATUSES,
                timeSlot.getStartsAt(),
                timeSlot.getEndsAt());

        if (overlaps) {
            throw new TimeSlotNotAvailableException(patientId);
        }
    }
}
