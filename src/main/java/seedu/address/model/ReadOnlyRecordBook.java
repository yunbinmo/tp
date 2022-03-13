package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.record.Record;

public interface ReadOnlyRecordBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Record> getRecordList();
}