package pe.edu.upc.medibridge.profiles.domain.model.events;

import java.time.Instant;

public record PatientProfileCreatedEvent(Integer patientId, String fullName, Instant occurredAt, int version) {
    public PatientProfileCreatedEvent(Integer patientId, String fullName) {
        this(patientId, fullName, Instant.now(), 1);
    }
}
