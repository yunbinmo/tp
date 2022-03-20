package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AppointmentBook;
import seedu.address.model.appointment.Appointment;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointments {
    public static final Appointment APPOINTMENT1 = new AppointmentBuilder()
            .withDescription("Meet James for health insurance")
            .withDateTime("02-03-2022 15:00").build();

    public static final Appointment APPOINTMENT2 = new AppointmentBuilder()
            .withDescription("Meet Alice for car insurance")
            .withDateTime("02-04-2022 19:00").build();

    public static final Appointment APPOINTMENT3 = new AppointmentBuilder()
            .withDescription("Meet Tom for insurance renewal")
            .withDateTime("04-03-2022 18:00").build();

    private TypicalAppointments() {} // prevents instantiation

    /**
     * Returns an {@code AppointmentBook} with all the typical appointments.
     */
    public static AppointmentBook getTypicalAppointmentBook() {
        AppointmentBook ab = new AppointmentBook();
        for (Appointment appointment : getTypicalAppointments()) {
            ab.addAppointment(appointment);
        }
        return ab;
    }

    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(APPOINTMENT1, APPOINTMENT2, APPOINTMENT3));
    }
}
