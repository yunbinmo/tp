package seedu.address.model;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.history.UniqueAppointmentHistoryList;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Wraps all data at the history-book level
 * Duplicates are not allowed (by .isSameHistory comparison)
 */
public class AppointmentHistoryBook implements ReadOnlyAppointmentHistoryBook {
    private final UniqueAppointmentHistoryList history;

    {
        this.history = new UniqueAppointmentHistoryList();
    }

    public AppointmentHistoryBook() {}

    public AppointmentHistoryBook(ReadOnlyAppointmentBook appointmentBook) {
        ObservableList<Appointment> appointments = appointmentBook.getAppointmentList();
        // todo add filter here before printing
        LocalDateTime now = LocalDateTime.now();
        for(Appointment a : appointments) {
            DateTime appointmentDate = a.getDateTime();
            assert false;
            if(appointmentDate.isBefore(now))
            {
                this.history.add(a);
            }
            System.out.println("done");
        }

    }

    @Override
    public ObservableList<Appointment> getAppointmentHistoryList() {
        return this.history.asUnmodifiableObservableList();
    }

}
