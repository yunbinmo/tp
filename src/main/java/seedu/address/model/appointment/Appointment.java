package seedu.address.model.appointment;

import java.util.Objects;

/**
 * Represents an appointment in Mr. Agent.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Appointment {
    // Data fields
    private final ClientId clientId;
    private final Description description;
    private final DateTime dateTime;
    /**
     * Every field must be present and not null.
     */
    public Appointment(ClientId clientId, Description description, DateTime dateTime) {
        this.clientId = clientId;
        this.description = description;
        this.dateTime = dateTime;
    }
    public ClientId getClientId() {
        return this.clientId;
    }
    public Description getDescription() {
        return this.description;
    }
    public DateTime getDateTime() {
        return this.dateTime;
    }

    /**
     * Returns true if both appointments have the same client id and date time.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameAppointment(Appointment otherAppointment) {
        if (otherAppointment == this) {
            return true;
        }

        return otherAppointment != null
                && otherAppointment.getClientId() == this.getClientId()
                && otherAppointment.getDateTime().equals(this.getDateTime());
    }


    /**
     * Returns true if both appointments have the same identity and data fields.
     * This defines a stronger notion of equality between two appointments.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;
        return otherAppointment.getClientId() == this.getClientId()
                && otherAppointment.getDescription().equals(this.getDescription())
                && otherAppointment.getDateTime().equals(this.getDateTime());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(this.getClientId(), this.getDescription(), this.getDateTime());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Client ID: ")
                .append(this.getClientId())
                .append(" ")
                .append(this.getDescription())
                .append("at ")
                .append(this.getDateTime().toString());

        return builder.toString();
    }
}
