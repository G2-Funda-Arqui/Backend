package pe.edu.upc.medibridge.reportsanalytics.application.internal.commandservices;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pe.edu.upc.medibridge.reportsanalytics.domain.model.aggregates.ClinicalReport;
import pe.edu.upc.medibridge.reportsanalytics.domain.model.commands.GenerateClinicalReportCommand;
import pe.edu.upc.medibridge.reportsanalytics.domain.model.commands.GeneratePdfReportCommand;
import pe.edu.upc.medibridge.reportsanalytics.domain.model.entities.ReportSection;
import pe.edu.upc.medibridge.reportsanalytics.domain.model.events.ClinicalReportGeneratedEvent;
import pe.edu.upc.medibridge.reportsanalytics.domain.model.exceptions.ReportNotFoundException;
import pe.edu.upc.medibridge.reportsanalytics.domain.services.ClinicalReportCommandService;
import pe.edu.upc.medibridge.reportsanalytics.infrastructure.pdf.ITextPdfReportGenerator;
import pe.edu.upc.medibridge.reportsanalytics.infrastructure.persistence.jpa.repositories.ClinicalReportRepository;

import java.util.Optional;

@Service
public class ClinicalReportCommandServiceImpl implements ClinicalReportCommandService {
    private final ClinicalReportRepository clinicalReportRepository;
    private final ITextPdfReportGenerator pdfReportGenerator;
    private final ApplicationEventPublisher eventPublisher;

    public ClinicalReportCommandServiceImpl(
            ClinicalReportRepository clinicalReportRepository,
            ITextPdfReportGenerator pdfReportGenerator,
            ApplicationEventPublisher eventPublisher) {
        this.clinicalReportRepository = clinicalReportRepository;
        this.pdfReportGenerator = pdfReportGenerator;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<ClinicalReport> handle(GenerateClinicalReportCommand command) {
        var report = new ClinicalReport(command, "Clinical report generated for patient " + command.patientId());
        report.addSection(new ReportSection("Clinical evolution", "Clinical data summary for the selected period.", 1));
        report.addSection(new ReportSection("Medication management", "Medication adherence and dose administration summary.", 2));
        report.addSection(new ReportSection("Appointments", "Appointment completion and follow-up summary.", 3));
        var savedReport = clinicalReportRepository.save(report);
        eventPublisher.publishEvent(new ClinicalReportGeneratedEvent(savedReport.getId(), savedReport.getPatientId()));
        return Optional.of(savedReport);
    }

    @Override
    public Optional<ClinicalReport> handle(GeneratePdfReportCommand command) {
        var report = clinicalReportRepository.findById(command.reportId())
                .orElseThrow(() -> new ReportNotFoundException(command.reportId()));
        pdfReportGenerator.generate(report);
        report.attachPdf("reports/clinical-report-" + report.getId() + ".pdf");
        return Optional.of(clinicalReportRepository.save(report));
    }
}
