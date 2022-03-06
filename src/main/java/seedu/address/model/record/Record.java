package seedu.address.model.record;

public class Record {
    //TODO
    public final int id;

    public Record(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + String.valueOf(id) + ']';
    }
}
