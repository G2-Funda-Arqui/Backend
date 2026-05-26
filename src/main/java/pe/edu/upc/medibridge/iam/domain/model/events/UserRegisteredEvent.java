package pe.edu.upc.medibridge.iam.domain.model.events;

import java.time.Instant;

public record UserRegisteredEvent(Integer userId, String username, Instant occurredAt, int version) {
    public UserRegisteredEvent(Integer userId, String username) {
        this(userId, username, Instant.now(), 1);
    }
}
