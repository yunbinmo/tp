package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.AppointmentHistoryBook;
import seedu.address.model.ReadOnlyAppointmentHistoryBook;

/**
 * Represents a storage for {@link AppointmentHistoryBook}.
 */
public interface AppointmentHistoryBookStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getAppointmentHistoryBookFilePath();

    /**
     * Returns HistoryBook data as a {@link ReadOnlyAppointmentHistoryBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyAppointmentHistoryBook> readAppointmentHistoryBook() throws DataConversionException, IOException;

    /**
     * @see #getHistoryBookFilePath()
     */
    Optional<ReadOnlyAppointmentHistoryBook> readAppointmentHistoryBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyAppointmentHistoryBook} to the storage.
     * @param historyBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAppointmentHistoryBook(ReadOnlyAppointmentHistoryBook historyBook) throws IOException;

    /**
     * @see #saveAppointmentHistoryBook(ReadOnlyAppointmentHistoryBook)
     */
    void saveAppointmentHistoryBook(ReadOnlyAppointmentHistoryBook historyBook, Path filePath) throws IOException;

}
