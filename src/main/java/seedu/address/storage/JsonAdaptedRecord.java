package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.appointment.Description;
import seedu.address.model.record.Record;
import seedu.address.model.record.ClientID;
import seedu.address.model.record.InsuranceID;
import seedu.address.model.record.StartDate;
import seedu.address.model.record.EndDate;


/**
 * Jackson-friendly version of {@link Record}.
 */
class JsonAdaptedRecord {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Record's %s field is missing!";

    private final String client_id;
    private final String insurance_id;
    private final String startDate;
    private final String endDate;

    /**
     * Constructs a {@code JsonAdaptedRecord} with the given insurance details.
     */
    @JsonCreator
    public JsonAdaptedRecord(@JsonProperty("client_id") String client_id,
                                  @JsonProperty("insurance_id") String insurance_id,
                                  @JsonProperty("startDate") String startDate,
                                  @JsonProperty("endDate") String endDate) {
        this.client_id = client_id;
        this.insurance_id = insurance_id;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    /**
     * Converts a given {@code Record} into this class for Jackson use.
     */
    public JsonAdaptedRecord(Record source) {
        client_id = source.getClientID().toString();
        insurance_id = source.getInsuranceID().toString();
        startDate = source.getStartDate().toString();
        endDate = source.getEndDate().toString();
    }

    /*@JsonValue
    public int getRecordName() {
        return id;
    }*/



    /**
     * Converts this Jackson-friendly adapted record object into the model's {@code Record} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted record.
     */
    public Record toModelType() throws IllegalValueException {
        //TODO: validate Record object
        if(client_id==null){
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ClientID.class.getSimpleName()));
        }
        final ClientID model_clientID = new ClientID(client_id);

        if(insurance_id==null){
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    InsuranceID.class.getSimpleName()));
        }
        final InsuranceID model_insuranceID = new InsuranceID(insurance_id);

        if(startDate==null){
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StartDate.class.getSimpleName()));
        }
        if (StartDate.validateDateTime(startDate) == null) {
            throw new IllegalValueException(StartDate.MESSAGE_CONSTRAINTS);
        }
        final StartDate modelStartDate = new StartDate(startDate);

        if(endDate==null){
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EndDate.class.getSimpleName()));
        }

        if (EndDate.validateDateTime(endDate) == null) {
            throw new IllegalValueException(EndDate.MESSAGE_CONSTRAINTS);
        }
        final EndDate modelEndDate = new EndDate(endDate);


        return new Record(model_clientID,model_insuranceID,modelStartDate,modelEndDate);
    }

}
