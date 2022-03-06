package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.insurance.Insurance;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final InsuranceBook insuranceBook;
    private final AppointmentBook appointmentBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Insurance> filteredInsurances;
    private final FilteredList<Appointment> filteredAppointments;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, InsuranceBook insuranceBook,
                        ReadOnlyAppointmentBook appointmentBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.insuranceBook = new InsuranceBook(insuranceBook);
        this.appointmentBook = new AppointmentBook(appointmentBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        this.filteredInsurances = new FilteredList<>(this.insuranceBook.getInsuranceList());
        this.filteredAppointments = new FilteredList<>(this.appointmentBook.getAppointmentList());
    }

    public ModelManager() {
        this(new AddressBook(), new InsuranceBook(), new AppointmentBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return this.userPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public GuiSettings getGuiSettings() {
        return this.userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return this.userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return this.addressBook;
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return this.addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        this.addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        this.addressBook.addPerson(person);
        this.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        this.addressBook.setPerson(target, editedPerson);
    }

    //=========== InsuranceBook ================================================================================

    @Override
    public ReadOnlyInsuranceBook getInsuranceBook() {
        return this.insuranceBook;
    }

    @Override
    public void setInsuranceBook(ReadOnlyInsuranceBook insuranceBook) {
        this.insuranceBook.resetData(insuranceBook);
    }

    @Override
    public boolean hasInsurance(Insurance insurance) {
        requireNonNull(insurance);
        return this.insuranceBook.hasInsurance(insurance);
    }

    @Override
    public void deleteInsurance(Insurance target) {
        this.insuranceBook.removeInsurance(target);
    }

    @Override
    public void addInsurance(Insurance insurance) {
        this.insuranceBook.addInsurance(insurance);
        this.updateFilteredInsuranceList(PREDICATE_SHOW_ALL_INSURANCES);
    }

    @Override
    public void setInsurance(Insurance target, Insurance editedInsurance) {
        requireAllNonNull(target, editedInsurance);

        this.insuranceBook.setInsurance(target, editedInsurance);
    }

    //=========== AppointmentBook ============================================================================
    @Override
    public ReadOnlyAppointmentBook getAppointmentBook() {
        return this.appointmentBook;
    }

    @Override
    public void setAppointmentBook(ReadOnlyAppointmentBook appointmentBook) {
        this.appointmentBook.resetData(appointmentBook);
    }

    @Override
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return this.appointmentBook.hasAppointment(appointment);
    }

    @Override
    public void addAppointment(Appointment appointment) {
        this.appointmentBook.addAppointment(appointment);
        this.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
    }

    @Override
    public void deleteAppointment(Appointment appointment) {
        this.appointmentBook.removeAppointment(appointment);
    }

    @Override
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);
        this.appointmentBook.setAppointment(target, editedAppointment);
    }

    //=========== Filtered Appointment List Accessors ========================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return this.filteredAppointments;
    }

    @Override
    public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
        requireNonNull(predicate);
        this.filteredAppointments.setPredicate(predicate);
    }


    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return this.filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        this.filteredPersons.setPredicate(predicate);
    }

    //=========== Filtered Insurance List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Insurance> getFilteredInsuranceList() {
        return this.filteredInsurances;
    }

    @Override
    public void updateFilteredInsuranceList(Predicate<Insurance> predicate) {
        requireNonNull(predicate);
        this.filteredInsurances.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return this.addressBook.equals(other.addressBook)
                && this.userPrefs.equals(other.userPrefs)
                && this.filteredPersons.equals(other.filteredPersons);
    }

}
