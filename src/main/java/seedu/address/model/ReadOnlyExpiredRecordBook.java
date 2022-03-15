package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.record.Record;

/**
 * Unmodifiable view of expired record book
 */
public interface ReadOnlyExpiredRecordBook {
    /**
     * Returns an unmodifiable view of the expired record list.
     * This list will not contain any duplicate record.
     * @return
     */
    ObservableList<Record> getExpiredRecordList();
}
