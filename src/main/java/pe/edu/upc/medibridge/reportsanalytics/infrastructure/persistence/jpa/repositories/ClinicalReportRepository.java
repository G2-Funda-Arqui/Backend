package pe.edu.upc.medibridge.reportsanalytics.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.medibridge.reportsanalytics.domain.model.aggregates.ClinicalReport;

import java.util.List;

@Repository
public interface ClinicalReportRepository extends JpaRepository<ClinicalReport, Integer> {
    List<ClinicalReport> findByPatientIdOrderByGeneratedAtDesc(Long patientId);
}
