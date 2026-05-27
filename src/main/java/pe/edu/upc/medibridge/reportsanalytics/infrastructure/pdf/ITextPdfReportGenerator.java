package pe.edu.upc.medibridge.reportsanalytics.infrastructure.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import pe.edu.upc.medibridge.reportsanalytics.domain.model.aggregates.ClinicalReport;
import pe.edu.upc.medibridge.reportsanalytics.domain.model.entities.ReportSection;
import pe.edu.upc.medibridge.reportsanalytics.domain.model.exceptions.ReportGenerationException;

import java.awt.Color;
import java.io.ByteArrayOutputStream;

@Component
public class ITextPdfReportGenerator {
    public byte[] generate(ClinicalReport report) {
        try {
            var outputStream = new ByteArrayOutputStream();
            var document = new Document(PageSize.A4, 48, 48, 48, 48);
            PdfWriter.getInstance(document, outputStream);

            document.open();
            addTitle(document);
            addReportMetadata(document, report);
            addSummary(document, report);
            addSections(document, report);
            document.close();

            return outputStream.toByteArray();
        } catch (DocumentException exception) {
            throw new ReportGenerationException("Unable to generate clinical report PDF");
        }
    }

    private void addTitle(Document document) throws DocumentException {
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Color.BLACK);
        var title = new Paragraph("MediBridge Clinical Report", titleFont);
        title.setSpacingAfter(16);
        document.add(title);
    }

    private void addReportMetadata(Document document, ClinicalReport report) throws DocumentException {
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, Color.WHITE);
        Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.BLACK);

        var table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingAfter(16);

        addMetadataRow(table, "Report ID", String.valueOf(report.getId()), headerFont, bodyFont);
        addMetadataRow(table, "Patient ID", String.valueOf(report.getPatientId()), headerFont, bodyFont);
        addMetadataRow(table, "Report Type", report.getReportType().name(), headerFont, bodyFont);
        addMetadataRow(table, "Period", report.getPeriodStartDate() + " to " + report.getPeriodEndDate(), headerFont, bodyFont);
        addMetadataRow(table, "Generated At", String.valueOf(report.getGeneratedAt()), headerFont, bodyFont);

        document.add(table);
    }

    private void addMetadataRow(PdfPTable table, String label, String value, Font headerFont, Font bodyFont) {
        var labelCell = new PdfPCell(new Phrase(label, headerFont));
        labelCell.setBackgroundColor(new Color(42, 73, 102));
        labelCell.setPadding(8);
        table.addCell(labelCell);

        var valueCell = new PdfPCell(new Phrase(value, bodyFont));
        valueCell.setPadding(8);
        table.addCell(valueCell);
    }

    private void addSummary(Document document, ClinicalReport report) throws DocumentException {
        Font sectionTitleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13, Color.BLACK);
        Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 11, Color.BLACK);

        var title = new Paragraph("Summary", sectionTitleFont);
        title.setSpacingAfter(6);
        document.add(title);

        var summary = new Paragraph(report.getSummary(), bodyFont);
        summary.setSpacingAfter(14);
        document.add(summary);
    }

    private void addSections(Document document, ClinicalReport report) throws DocumentException {
        Font sectionTitleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13, Color.BLACK);
        Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 11, Color.BLACK);

        for (ReportSection section : report.getSections()) {
            var title = new Paragraph(section.getTitle(), sectionTitleFont);
            title.setSpacingBefore(8);
            title.setSpacingAfter(6);
            document.add(title);

            var content = new Paragraph(section.getContent(), bodyFont);
            content.setSpacingAfter(10);
            document.add(content);
        }
    }
}
