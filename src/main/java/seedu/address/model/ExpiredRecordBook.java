package seedu.address.model;

import java.time.LocalDate;

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

        LocalDate now = LocalDate.now();
        for (Record r : records) {
            LocalDate endDate = r.getEndDate().getDate();

//            assert false;
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
