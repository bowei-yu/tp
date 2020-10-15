package seedu.address.logic.commands.flashcardcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FLASHCARDSET_NAME;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.FlashcardModel;
import seedu.address.model.flashcard.FlashcardSet;

/**
 * Regulates the behaviour of a {@code Command} that adds a {@code FlashcardSet}
 */
public class AddFlashcardSetCommand extends Command<FlashcardModel> {

    public static final String COMMAND_WORD = "add flset";
    public static final String MESSAGE_SUCCESS = "New flashcard set added: %1$s";
    public static final String MESSAGE_DUPLICATE_FLASHCARD_SET =
            "This flashcard set already exists in the flashcard bank.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a flashcard set to the flashcard bank. "
            + "Parameters: "
            + PREFIX_FLASHCARDSET_NAME + "<name>\n"
            + "Example: "
            + COMMAND_WORD + " " + PREFIX_FLASHCARDSET_NAME + "Japanese";

    private final FlashcardSet toAdd;

    /**
     * Creates a {@code Command} to add the specified {@code FlashcardSet}
     */
    public AddFlashcardSetCommand(FlashcardSet toAdd) {
        requireNonNull(toAdd);
        this.toAdd = toAdd;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddFlashcardSetCommand // instanceof handles nulls
                && toAdd.equals(((AddFlashcardSetCommand) other).toAdd));
    }

    @Override
    public CommandResult execute(FlashcardModel model) throws CommandException {
        requireNonNull(model);

        if (model.hasFlashcardSet(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_FLASHCARD_SET);
        }

        model.addFlashcardSet(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
