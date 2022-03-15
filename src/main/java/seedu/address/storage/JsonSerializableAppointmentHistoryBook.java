package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyAppointmentHistoryBook;
import seedu.address.model.AppointmentBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.history.AppointmentHistory;

/**
 * An Immutable HistoryBook that is serializable to JSON format.
 */
@JsonRootName(value = "appointmenthistorybook")
public class JsonSerializableAppointmentHistoryBook {

    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "History list contains duplicate history(s).";
    private final List<JsonAdaptedAppointmentHistory> history = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAppointmentBook} with the given appointments.
     */
    @JsonCreator
    public JsonSerializableAppointmentHistoryBook(@JsonProperty("history") List<JsonAdaptedAppointmentHistory> history) {
        this.history.addAll(history);
    }
//
//    /**
//     * Converts a given {@code ReadOnlyAppointmentBook} into this class for Jackson use.
//     *
//     * @param source future changes to this will not affect the created {@code JsonSerializableAppointmentBook}.
//     */
//    public JsonSerializableAppointmentHistoryBook(ReadOnlyAppointmentHistoryBook source) {
//        history.addAll(source.getAppointmentHistoryList().stream().map(JsonAdaptedAppointmentHistory::new)
//                .collect(Collectors.toList()));
//    }

    /**
     * Converts this appointment book into the model's {@code AppointmentBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AppointmentBook toModelType() throws IllegalValueException {
        AppointmentBook appointmentBook = new AppointmentBook();
        for (JsonAdaptedAppointmentHistory jsonAdaptedAppointment : history) {
            Appointment appointment = jsonAdaptedAppointment.toModelType();
            if (appointmentBook.hasAppointment(appointment)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPOINTMENT);
            }
            appointmentBook.addAppointment(appointment);
        }
        return appointmentBook;
    }

}
