package seedu.address.model;

import java.time.LocalDateTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.record.Record;


public class ExpiredRecordBook implements ReadOnlyExpiredRecordBook {

    private final ObservableList<Record> record;

    {
        this.record = FXCollections.observableArrayList();
    }

    public ExpiredRecordBook() {
    }

    /**
     * TODO
     */
    public ExpiredRecordBook(ReadOnlyRecordBook recordBook) {
        ObservableList<Record> records = recordBook.getRecordList();
        LocalDateTime now = LocalDateTime.now();
        for (Record r : records) {
            LocalDateTime endDate = r.getEndDate().getDate();
            assert false;
            if (endDate.isBefore(now)) {
                this.record.add(r);
            }
        }
    }

    @Override
    public ObservableList<Record> getExpiredRecordList() {
        return this.record;
    }
}
