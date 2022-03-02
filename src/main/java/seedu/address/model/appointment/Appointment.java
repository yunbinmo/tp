package seedu.address.model.appointment;

import java.time.LocalDateTime;

/**
 * Represents an appointment in Mr. Agent.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Appointment {
    // Data fields
    private final int clientId;
    private final String description;
    private final LocalDateTime dateTime;
    /**
     * Every field must be present and not null.
     */
    public Appointment(int clientId, String description, LocalDateTime dateTime) {
        this.clientId = clientId;
        this.description = description;
        this.dateTime = dateTime;
    }
}
