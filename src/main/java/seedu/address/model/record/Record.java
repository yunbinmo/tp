package seedu.address.model.record;

import java.util.Objects;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.insurance.Insurance;

public class Record {
    //TODO

    public static int globalId = 0;

    private final int record_id;
    private final int client_id;
    private final int insurance_id;
    private final DateTime startDate;
    private final DateTime endDate;



    public Record(int client_id, int insurance_id, DateTime startDate, DateTime endDate) {
        this.record_id = globalId++;
        this.client_id = client_id;
        this.insurance_id = insurance_id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getRecordId() {
        return this.record_id;
    }

    public int getClientID(){
        return this.client_id;
    }

    public int getInsuranceID(){
        return this.insurance_id;
    }

    public DateTime getStartDate(){
        return this.startDate;
    }

    public DateTime getEndDate(){
        return this.endDate;
    }

    /**
     * Returns true if both record have the same client_id
     * This defines a weaker notion of equality between two records.
     */
    public boolean isSameRecord(Record otherRecord) {
        if (otherRecord == this) {
            return true;
        }

        return otherRecord != null
                && otherRecord.getClientID() == this.getClientID();

    }

    /**
     * Returns true if both appointments have the same identity and data fields.
     * This defines a stronger notion of equality between two appointments.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Record)) {
            return false;
        }

        Record otherRecord = (Record) other;
        return otherRecord.getClientID()==this.client_id
                && otherRecord.getRecordId()==this.record_id;
    }


    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + String.valueOf(record_id) + ']';
    }
}
