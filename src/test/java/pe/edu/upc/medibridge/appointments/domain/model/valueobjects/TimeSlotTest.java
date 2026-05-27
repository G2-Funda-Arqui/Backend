package pe.edu.upc.medibridge.appointments.domain.model.valueobjects;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TimeSlotTest {

    @Test
    void shouldRejectInvalidTimeSlot() {
        var start = LocalDateTime.of(2026, 6, 1, 10, 0);
        var end = LocalDateTime.of(2026, 6, 1, 9, 0);

        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(start, end));
    }

    @Test
    void shouldDetectOverlappingTimeSlots() {
        var first = new TimeSlot(
                LocalDateTime.of(2026, 6, 1, 10, 0),
                LocalDateTime.of(2026, 6, 1, 11, 0));
        var second = new TimeSlot(
                LocalDateTime.of(2026, 6, 1, 10, 30),
                LocalDateTime.of(2026, 6, 1, 11, 30));

        assertTrue(first.overlaps(second));
    }

    @Test
    void shouldAllowAdjacentTimeSlots() {
        var first = new TimeSlot(
                LocalDateTime.of(2026, 6, 1, 10, 0),
                LocalDateTime.of(2026, 6, 1, 11, 0));
        var second = new TimeSlot(
                LocalDateTime.of(2026, 6, 1, 11, 0),
                LocalDateTime.of(2026, 6, 1, 12, 0));

        assertFalse(first.overlaps(second));
        assertEquals(60, first.durationInMinutes());
    }
}
