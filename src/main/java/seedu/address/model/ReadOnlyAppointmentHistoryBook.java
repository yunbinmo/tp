package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;

/**
 * Unmodifiable view of appointment history book
 */
public interface ReadOnlyAppointmentHistoryBook {
    /**
     * Returns an unmodifiable view of the history list.
     * This list will not contain any duplicate history.
     * @return
     */
    ObservableList<Appointment> getAppointmentHistoryList();
}
