package pe.edu.upc.medibridge.reportsanalytics.domain.model.exceptions;

public class ReportNotFoundException extends RuntimeException {
    public ReportNotFoundException(Integer reportId) {
        super("Clinical report not found with id: " + reportId);
    }
}
