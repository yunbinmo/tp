package seedu.address.model.record;

import java.util.Objects;


public class Record {
    //TODO

    public static int globalId = 0;

    private final int record_id;
    private final ClientID client_id;
    private final InsuranceID insurance_id;
    private final StartDate startDate;
    private final EndDate endDate;



    public Record(ClientID client_id, InsuranceID insurance_id, StartDate startDate, EndDate endDate) {
        this.record_id = globalId++;
        this.client_id = client_id;
        this.insurance_id = insurance_id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getRecordId() {
        return this.record_id;
    }

    public ClientID getClientID(){
        return this.client_id;
    }

    public InsuranceID getInsuranceID(){
        return this.insurance_id;
    }

    public StartDate getStartDate(){
        return this.startDate;
    }

    public EndDate getEndDate(){
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
                && otherRecord.getClientID().equals(this.client_id)
                && otherRecord.getInsuranceID().equals(this.insurance_id);

    }

    /**
     * Returns true if both records have the same identity and data fields.
     * This defines a stronger notion of equality between two records.
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
        return otherRecord.getRecordId()==this.record_id
                && otherRecord.getClientID().equals(this.client_id)
                && otherRecord.getInsuranceID().equals(this.insurance_id)
                && otherRecord.getStartDate().equals(this.startDate)
                && otherRecord.getEndDate().equals(this.endDate);
    }


    /**
     * Format state as text for viewing.
     */
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Record ")
                .append(this.getRecordId())
                .append(" Client ")
                .append(this.getClientID())
                .append(" bought insurance ")
                .append(this.getInsuranceID())
                .append(" valid from ")
                .append(this.getStartDate())
                .append(" to ")
                .append(this.getEndDate());
        return builder.toString();
    }
}
