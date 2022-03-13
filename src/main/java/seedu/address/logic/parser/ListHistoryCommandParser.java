//package seedu.address.logic.parser;
//
//import seedu.address.logic.commands.ListAppointmentHistoryCommand;
//import seedu.address.logic.parser.exceptions.ParseException;
//import seedu.address.model.history.HistoryTypePredicate;
//
//import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
//
//
///**
// * Parses input arguments and creates a new FindCommand object
// */
//public class ListHistoryCommandParser implements Parser<ListAppointmentHistoryCommand> {
//
//    /**
//     * Parses the given {@code String} of arguments in the context of the ListHistoryCommand
//     * and returns a ListHistoryCommand object for execution.
//     * @throws ParseException if the user input does not conform the expected format
//     */
//    public ListAppointmentHistoryCommand parse(String args) throws ParseException {
//        if (trimmedArgs.isEmpty()) {
//            throw new ParseException(
//                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListAppointmentHistoryCommand.MESSAGE_USAGE));
//        }
//        return new ListAppointmentHistoryCommand(new HistoryTypePredicate());
//    }
//
//}