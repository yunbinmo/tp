package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.insurance.TitleContainsKeywordsPredicate;

/**
 * Finds and lists all insurances in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindInsuranceCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all insurances whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD + " -i car";

    private final TitleContainsKeywordsPredicate predicate;

    public FindInsuranceCommand(TitleContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInsuranceList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_INSURANCES_LISTED_OVERVIEW, model.getFilteredInsuranceList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindInsuranceCommand // instanceof handles nulls
                && predicate.equals(((FindInsuranceCommand) other).predicate)); // state check
    }
}
