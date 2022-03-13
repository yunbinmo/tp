package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddRecordCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.record.Record;
import seedu.address.model.record.ClientID;
import seedu.address.model.record.InsuranceID;
import seedu.address.model.record.StartDate;
import seedu.address.model.record.EndDate;

/**
 * Parses input arguments and creates a new AddRecordCommand object
 */
public class AddRecordCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AddRecordCommand
     * and returns an AddRecordCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddRecordCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,PREFIX_REC_CLIENTID, PREFIX_REC_INSURANCEID, PREFIX_REC_STARTDATE, PREFIX_REC_ENDDATE);

        // Just check if any prefixes are missing
        if (!arePrefixesPresent(argMultimap, PREFIX_REC_CLIENTID, PREFIX_REC_INSURANCEID, PREFIX_REC_STARTDATE, PREFIX_REC_ENDDATE )) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddRecordCommand.MESSAGE_USAGE));
        }

        ClientID clientID =
                RecordParserUtil.parseClientID(argMultimap.getValue(PREFIX_REC_CLIENTID).get());
        InsuranceID insuranceID =
                RecordParserUtil.parseInsuranceID(argMultimap.getValue(PREFIX_REC_INSURANCEID).get());
        StartDate startDate = RecordParserUtil.parseStartDate(argMultimap.getValue(PREFIX_REC_STARTDATE).get());
        EndDate endDate = RecordParserUtil.parseEndDate(argMultimap.getValue(PREFIX_REC_ENDDATE).get());

        Record record = new Record(clientID, insuranceID, startDate, endDate);
        return new AddRecordCommand(record);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}