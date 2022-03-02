package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPT_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPT_DESCRIPTION;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

/**
 * Adds an appointment to the address book.
 */
public class AddAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String DATE_TIME_PATTERN = "dd-MM-yyyy HH:mm";
    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an appointment to the address book. "
            + "Parameters: "
            + PREFIX_APPT_DESCRIPTION + "TITLE "
            + PREFIX_APPT_DATETIME + "PRICE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_APPT_DESCRIPTION + "Meet James at UTown "
            + PREFIX_APPT_DATETIME + "20-03-2022 18:00 ";

    private final Appointment toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Appointment}
     */
    public AddAppointmentCommand(Appointment appoinment) {
        requireNonNull(appoinment);
        toAdd = appoinment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.addAppointment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAppointmentCommand // instanceof handles nulls
                && toAdd.equals(((AddAppointmentCommand) other).toAdd));
    }
}
