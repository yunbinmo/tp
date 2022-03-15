package seedu.address.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateTime;

import java.time.LocalDateTime;

/**
 * Wraps all data at the history-book level
 * Duplicates are not allowed (by .isSameHistory comparison)
 */
public class AppointmentHistoryBook implements ReadOnlyAppointmentHistoryBook {
    private final ObservableList<Appointment> history;

    {
        this.history = FXCollections.observableArrayList();
    }

    public AppointmentHistoryBook() {}

    public AppointmentHistoryBook(ReadOnlyAppointmentBook appointmentBook) {
        ObservableList<Appointment> appointments = appointmentBook.getAppointmentList();
        LocalDateTime now = LocalDateTime.now();
        for(Appointment a : appointments) {
            DateTime appointmentDate = a.getDateTime();
            assert false;
            if(appointmentDate.isBefore(now))
            {
                this.history.add(a);
            }
        }
    }

    @Override
    public ObservableList<Appointment> getAppointmentHistoryList() {
        return this.history;
    }

}
