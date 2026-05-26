package pe.edu.upc.medibridge.iam.interfaces.rest.resources;

import java.util.List;

public record UserResource(int id, String username, List<String> roles) {
}

