package pe.edu.upc.medibridge.profiles.domain.model.events;

import java.time.Instant;

public record FamilyMemberProfileCreatedEvent(Integer familyMemberProfileId, Long userId, Instant occurredAt, int version) {
    public FamilyMemberProfileCreatedEvent(Integer familyMemberProfileId, Long userId) {
        this(familyMemberProfileId, userId, Instant.now(), 1);
    }
}
