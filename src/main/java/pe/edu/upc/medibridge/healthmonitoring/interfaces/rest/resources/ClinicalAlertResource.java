package pe.edu.upc.medibridge.healthmonitoring.interfaces.rest.resources;

import pe.edu.upc.medibridge.healthmonitoring.domain.model.valueobjects.AlertSeverity;
import pe.edu.upc.medibridge.healthmonitoring.domain.model.valueobjects.AlertStatus;

import java.time.LocalDateTime;

public record ClinicalAlertResource(
        int id,
        Long patientId,
        Integer observationId,
        AlertSeverity severity,
        AlertStatus status,
        String message,
        LocalDateTime triggeredAt) {
}
