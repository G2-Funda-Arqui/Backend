package pe.edu.upc.medibridge.appointments.interfaces.rest.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.medibridge.appointments.application.internal.outboundservices.acl.ExternalProfilesContextService;
import pe.edu.upc.medibridge.profiles.domain.model.queries.CanDoctorAttendPatientQuery;
import pe.edu.upc.medibridge.profiles.domain.model.queries.CanFamilyMemberAccessPatientQuery;
import pe.edu.upc.medibridge.profiles.domain.model.queries.GetPatientProfileByIdQuery;
import pe.edu.upc.medibridge.profiles.domain.services.CareRelationshipQueryService;
import pe.edu.upc.medibridge.profiles.domain.services.PatientProfileQueryService;

@Service
public class ProfilesContextFacade implements ExternalProfilesContextService {

    private final PatientProfileQueryService patientProfileQueryService;
    private final CareRelationshipQueryService careRelationshipQueryService;

    public ProfilesContextFacade(
            PatientProfileQueryService patientProfileQueryService,
            CareRelationshipQueryService careRelationshipQueryService) {
        this.patientProfileQueryService = patientProfileQueryService;
        this.careRelationshipQueryService = careRelationshipQueryService;
    }

    @Override
    public boolean patientExists(Long patientId) {
        return patientId != null && patientProfileQueryService
                .handle(new GetPatientProfileByIdQuery(patientId))
                .isPresent();
    }

    @Override
    public boolean familyMemberCanAccessPatient(Long familyMemberProfileId, Long patientId) {
        return familyMemberProfileId != null
                && patientId != null
                && careRelationshipQueryService.handle(
                new CanFamilyMemberAccessPatientQuery(familyMemberProfileId, patientId));
    }

    @Override
    public boolean doctorCanAttendPatient(Long doctorProfileId, Long patientId) {
        return doctorProfileId != null
                && patientId != null
                && careRelationshipQueryService.handle(
                new CanDoctorAttendPatientQuery(doctorProfileId, patientId));
    }
}
