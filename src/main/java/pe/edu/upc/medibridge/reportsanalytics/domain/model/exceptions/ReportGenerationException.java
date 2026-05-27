package pe.edu.upc.medibridge.reportsanalytics.domain.model.exceptions;

public class ReportGenerationException extends RuntimeException {
    public ReportGenerationException(String message) {
        super(message);
    }
}
