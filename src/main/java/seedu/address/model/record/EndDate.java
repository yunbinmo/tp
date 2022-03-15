package seedu.address.model.record;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Record's associated date and time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #validateDateTime(String)}
 */
public class EndDate {

    public static final String MESSAGE_CONSTRAINTS =
            "End Date should be in this format: dd-MM-yyyy e.g. 02-03-2022 18:00";

    private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm";

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public final LocalDateTime date;

    /**
     * Constructs a {@code DateTime}.
     *
     * @param input A valid date time string.
     */
    public EndDate(String input) {
        requireNonNull(input);
        LocalDateTime result = validateDateTime(input);
        if (result == null) {
            checkArgument(false, MESSAGE_CONSTRAINTS);
        }
        this.date = result;
    }

    /**
     * Alternative constructor using LocalDateTime object as input.
     *
     * @param date A valid LocalDateTime object.
     */
    public EndDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Returns a valid LocalDateTime object if a given string is valid date and time.
     */
    public static LocalDateTime validateDateTime(String test) {
        try {
            LocalDateTime date = LocalDateTime.parse(test, DATE_FORMATTER);
            return date;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return date.format(DATE_FORMATTER);
    }


    @Override
    public int hashCode() {
        return date.hashCode();
    }

    public LocalDateTime getDate() {
        return this.date;
    }
}
