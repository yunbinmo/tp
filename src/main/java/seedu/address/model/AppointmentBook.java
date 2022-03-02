package seedu.address.model;

import seedu.address.model.insurance.UniqueInsuranceList;

/**
 * Wraps all data at the appointment-book level
 * Duplicates are not allowed (by .isSameAppointment comparison)
 */
public class AppointmentBook extends ReadOnlyAppointmentBook {
    private final UniqueInsuranceList appointments;

    {
        this.appointments = new UniqueInsuranceList();
    }

    public AppointmentBook() {
    }

    /**
     * Creates an AddressBook using the Appointments in the {@code toBeCopied}
     */
    public AppointmentBook(ReadOnlyAppointmentBook toBeCopied) {
        this();
        this.resetData(toBeCopied);
    }
}
