package pe.edu.upc.medibridge.healthmonitoring.domain.model.events;

import java.time.Instant;

public record ClinicalAlertTriggeredEvent(
        Integer alertId,
        Long patientId,
        Integer observationId,
        String severity,
        String message,
        Instant occurredAt,
        int version) {

    public ClinicalAlertTriggeredEvent(
            Integer alertId,
            Long patientId,
            Integer observationId,
            String severity,
            String message) {
        this(alertId, patientId, observationId, severity, message, Instant.now(), 1);
    }
}
