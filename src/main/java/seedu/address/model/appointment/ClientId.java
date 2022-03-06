package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Appointment's associated client id in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidClientId(int)}
 */
public class ClientId {

    public static final String MESSAGE_CONSTRAINTS =
            "Client ID should be an existing one in the client list, containing only numbers";

    public final Integer clientId;

    /**
     * Constructs a {@code CliendId}.
     *
     * @param clientId A valid client id.
     */
    public ClientId(int clientId) {
        requireNonNull(clientId);
        checkArgument(isValidClientId(clientId), MESSAGE_CONSTRAINTS);
        this.clientId = clientId;
    }

    /**
     * Returns true if a given string is a valid client id.
     */
    public static boolean isValidClientId(int test) {
        // TODO need calls to the client class for validity checking
        // e.g. Client.isValidIndex(test)
        return 0 < test;
    }

    @Override
    public String toString() {
        return clientId.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClientId // instanceof handles nulls
                && clientId.equals(((ClientId) other).clientId)); // state check
    }

    @Override
    public int hashCode() {
        return clientId.hashCode();
    }
}
