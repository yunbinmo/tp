package seedu.address.model.history;

import java.util.function.Predicate;
// todo remove this in the future
/**
 * Tests that a {@code History}'s {@code type} matches any of the keywords given.
 */
public class HistoryTypePredicate implements Predicate<AppointmentHistory> {
    private final String type;

    public HistoryTypePredicate(String type) {
        this.type = type;
    }

    @Override
    public boolean test(AppointmentHistory history) {
        return type.equals("a") || type.equals("r");
    }
}
