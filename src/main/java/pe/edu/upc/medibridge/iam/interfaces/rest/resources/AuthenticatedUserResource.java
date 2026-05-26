package pe.edu.upc.medibridge.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(int id, String username, String token) {
}
