package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.insurance.Insurance;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyInsuranceBook {
    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Insurance> getInsuranceList();
}
