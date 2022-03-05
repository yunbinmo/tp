package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.record.Record;

/**
 * Jackson-friendly version of {@link Record}.
 */
class JsonAdaptedRecord {

    private final int id;

    /**
     * Constructs a {@code JsonAdaptedRecord} with the given {@code id}.
     */
    @JsonCreator
    public JsonAdaptedRecord(int id) {
        this.id = id;
    }

    /**
     * Converts a given {@code Record} into this class for Jackson use.
     */
    public JsonAdaptedRecord(Record source) {
        id = source.id;
    }

    @JsonValue
    public int getRecordName() {
        return id;
    }

    /**
     * Converts this Jackson-friendly adapted record object into the model's {@code Record} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted record.
     */
    public Record toModelType() throws IllegalValueException {
        //TODO: validate Record object
        //if (!Record.isValidRecord(id)) {
        //      throw new IllegalValueException(Record.MESSAGE_CONSTRAINTS);
        //}
        return new Record(id);
    }

}
