package pe.edu.upc.medibridge.profiles.interfaces.rest.resources;

public record FamilyPatientLinkResource(
        int id,
        Long familyMemberProfileId,
        Long patientId,
        boolean active) {
}
