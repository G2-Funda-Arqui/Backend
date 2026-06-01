package pe.edu.upc.medibridge.profiles.interfaces.rest.resources;

public record DoctorPatientAssignmentResource(
        int id,
        Long doctorProfileId,
        Long patientId,
        boolean active) {
}
