---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagramUpdated.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `AppointmentListPanel`, `InsuranceListPanel`, `RecordListPanel`, `ExpiredRecordPanel`, `ObejctDEtailedPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`
* does not replace the right panel - `AppointmentListPanel` at any time  
* based on the user commands, left panel changes accordingly. Eg. when list -r is entered, `RecordListPanel` will replace the existing panel
* listens for user click on `PersonListPanel` and will update the `ObjectDetailedPanel` to show respective client's information


### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2122S2-CS2103-F09-3/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="700" />


The `Model` component,

* stores different kinds of  address book data i.e., all `Person`, `Appointment`, `Record`, and `Insurance` objects (which are contained in a `UniquePersonList`, `UniqueAppointmentList`, `UniqueRecordList` and `UniqueInsuranceList` object respectively).
* for example, stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change. The same mechanism applies to `Appointment`, `Record`, and `Insurance` objects as well.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model (containing `Person` object only for simplicity) is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2122S2-CS2103-F09-3/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.jpg" width="550" />

The `Storage` component,

* can save address book data, insurance book data, record book data, appointment book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage`, `InsuranceBookStorage`, `RecordBookStorage`, `AppointmentBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)
* note that for history related features will only read from existing storage and will not create new json file. For example, list expired reocrd command will search in RecordBookStorage to find expired records.

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

## Sort Data Entries Feature

###Sort Records

#### Implementation

The Sort Records Feature is facilitated by `SortReocrdCommand`. It extends `Command` with it own execution logic. It supports the following commands:

* `sort -r sa` — Sort the records by start date in ascending order.
* `sort -r sd` — Sort the records by start date in descending order.
* `sort -r ea` — Sort the records by end date in ascending order.
* `sort -r ed` — Sort the records by end date in descending order.

These commands will be parsed by the `AddressBookParser` and `SortReocrdCommandPasrser`. `ModelManger` will override `sortRecordBook()` from `Model` which take in an `Comparator<Record>` object as input. Then `ReocrdBook` will sort the `UniqueRecordList` base on the Comparator given.

Given below is an example usage scenario and how the Sort Records Command behaves at each step.

Step 1. The user launches the application and executes `sort -r sa` command to sort the records by start date in ascending order.

Step 2. The `LogicManager` receives the input from `UI#MainWindow` and calls `AddressBookParser#parseCommand()`, causing the user input being parsed and an object of `SortRecordCommand` returns.

Step 3. `LogicManager` executes object of `SortRecordCommand`. `SortRecordCommand` will invoke `ModelManager#sortRecordBook()` with different Comparator base on user input.

Step 4. `ModelManger` will invoke `RecordBook#sortRecord()` with the comparator as input to sort the `UniqueRecordList`.

Step 5. `MainWindow` will update the `RecordListPanel` with sorted `ObservableList` of records.

#### Design considerations

**Aspect: How sort records executes:**

* **Alternative 1 (current choice):** Saves the sorted records to RecordBook.
  * Pros: Easy to implement.
  * Cons: The original order of record List is not preserved.

* **Alternative 2:** Creates a temporary sorted records List.
  * Pros: The original order of record List is preserved.
  * Cons:  May have performance issues in terms of memory usage.

### Sort Appointments Feature

#### Implementation

The Sort Appointments Feature is facilitated by `SortAppointmentCommand`. It extends `Command` with it own execution logic. It supports the following commands:

* `sort -a a` — Sort the appointments by appointment time in ascending order.
* `sort -a d` — Sort the records by appointment time in descending order.

These commands will be parsed by the `AddressBookParser` and `SortAppointmentCommandPasrser`. `ModelManger` will override `sortAppointmentBook()` from `Model` which take in an `Comparator<Appointment>` object as input. Then `AppointmentBook` will sort the `UniqueAppointmentList` base on the Comparator given.

Given below is an example usage scenario and how the Sort Appointments Command behaves at each step.

Step 1. The user launches the application and executes `sort -a a` command to sort the appointments by time in ascending order.

Step 2. The `LogicManager` receives the input from `UI#MainWindow` and calls `AddressBookParser#parseCommand()`, causing the user input being parsed and an object of `SortAppointmentCommand` returns.

Step 3. `LogicManager` executes object of `SortAppointmentCommand`. `SortAppointmentCommand` will invoke `ModelManager#sortAppointmentBook()` with the stated Comparator base on user input.

Step 4. `ModelManger` will invoke `AppointmentBook#sortAppointment()` with the comparator as input to sort the `UniqueAppointmentList`.

Step 5. `MainWindow` will update the `AppointmentListPanel` with sorted `ObservableList` of records.

#### Design considerations

Considerations for sort record command also apply here.

## Click event on objectListPlaceholder feature

![UIStructure](images/UIstructure.jpg)
#### Implementation

The `objectListPanel` will be updated according to user command. It support follwoing commands:

* `list -c` — List all clients.
* `list -i` — List all insurances.
* `list -r` — List all records.
* `list -e` — List all expired records.
* `list -h` — List histroy of appointments.
  
The content in `detailPane` will be updated with details of the object base on the click event.

![MainWindow](images/MainWindow.jpg)

Step 1. The user launches the application and executes `list -c` command to list all clients.

Step 2.  Then `personListPanel` (filled by `ObservableList` of client) will be added into `objectListPanel` as children component.

Step 3. User click on `Davia Li`, then `PersonDetailCard` (filled by the detail information of `Davia Li`) will be added into `detailPanel` as children component.

#### Design considerations

**Aspect: How to arrage all components:**

* **Alternative 1 (current choice):** Update the panel base on command and click event.
  * Pros:
      1. The UI looks more clean and clear.
      2. The cells of `objectListPanel` only contians important information like client name and tags.
  * Cons: User need to click on certain cell to look into deatils.

* **Alternative 2:** Display everything with details in the `objectListPanel`.
  * Pros: User no need to click to look into details.
  * Cons: All information are squeeze together and the list can only contains maximum 3-5 cells.
  User still to scroll down to check other items in the list.

## Add Records Feature

#### Implementation

The Add Records Feature will be facilitated by `AddRecordCommand` which implements `Parser` and `AddCommand` which extends `Command`. 

The command format is `add -r c/ClientIndex i/InsuranceIndex sd/StartDate ed/EndDate` - add a record to the client at `ClientIndex` with insurance of `InsuranceIndex` valid from `StartDate` to `EndDate`

The relevant methods are:

1. `AddCommandParser#parse(List<Person> personList, ObservableList<Insurance> insuranceList,
   String args)` --> Parse the relevant detailed information 
   
2. `AddCommand#execute(Model model)` --> Checks for duplication , validate each information and store to library

Given below is an example usage scenerio and how the AddCommand behaves at each step.

Step1. The user launches the application and execute `add -r c/1 i/1 sd/22-03-2022 ed/ 22-03-2032`

Step2. The `LogicManager` receives the input from `UI#MainWindow` and calls `AddressBookParser#parseCommand()`, 
and determine that it is an Add Command.

Step3. AddCommandParser would check if the `ClientIndex` and `InsuranceIndex` exist in the library. Then it will continue to check if the `StartDate` is before the `EndDate`

Step4. Execution of Add would take place and the result will be updated in the filtered record list Model.

Important Features to take note:

1. The `add record` command takes in the `ClientIndex` and `InsuranceIndex` but stores the the Client's `Name` and Insurance's `Title` in the RecordBook.
2. The `sd/STARTDATE` entered must strictly be before the `sd/ENDDATE`, else `ParseException` will be thrown

#### Design considerations

**Aspect: How to add record to recordBook:**

* **Alternative 1 (current choice):** User input all required information at once
    * Pros:
        1. Faster response rate as system does not need to prompt and wait for the user to key in the information needed
        2. User which are fast-typing can enter information at once, increasing efficiency
    * Cons: User may make mistakes when keying in information more frequently


* **Alternative 2:** System prompts and user input information one at a time
    * Pros: Easier for user to view their input, reducing typing errors
    * Cons: Less responsive as user needs to wait for the system to validate the information entered one at a time before prompting the user to key in the next information.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:


* has a need to manage a significant number of clients
* has a need to manage and schedule numerous appointments
* prefer desktop apps over other types
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage clients and appointments faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                        | I want to …​                  | So that I can…​                                                        |
|----------|------------------------------------------------|-------------------------------|------------------------------------------------------------------------|
| `* * *`  | new user                                       | see usage instructions        | refer to instructions when I forget how to use the App                 |
| `* * *`  | insurance agent                                | add a new client              | manage clients in the application                                      |
| `* * *`  | insurance agent                                | delete a client               | remove entries that I no longer need                                   |
| `* * *`  | insurance agent                                | find a client by name         | locate details of clients without having to go through the entire list |
| `* * *`  | insurance agent                                | list all clients              | view clients that are in my contact                                    |
| `* * *`  | insurance agent                                | add an insurance              | manage all insurances in the apllication                               |
| `* * *`  | insurance agent                                | edit an insurance             | make changes to insurance saved in the application                     |
| `* * *`  | insurance agent                                | list all insurance            | view all that I have stored in the application                         |
| `* * *`  | insurance agent                                    | add a record to client        | keep a record of the insurances that the client have                   |
| `* * *`  | insurance agent                                | delete a record               | remove entries that I no longer need                                   |
| `* * *`  | insurance agent                                | edit a record                 | make changes to record saved in the application                        |
| `*`      | insurance agent want to check records     | sort record by start/end date | locate almost expired/newest records easily                            |
| `***`    | insurance agent frequently having appointments | view all appointments         | be reminded of all the meetings I have with my clients                 |
| `***`    | insurance agent frequently having appointments | add new appointments          | note down any future meetings I have with my client                    |
| `***`    | insurance agent frequently having appointments | delete an appointment         | get rid of any canceled/completed appointments to avoid confusion      |
| `***`    | insurance agent frequently having appointments | edit an appointment           | amend appointment details to suit clients' needs                       |
| `***`    | insurance agent frequently having appointments | find an appointment           | quickly locate a related appointment                                   |
| `***`    | insurance agent frequently having appointments | sort appointments             | prioritize more urgent appointments                                    |
| `* * *`  | insurance agent                                | view passed appointments      | know account my meetups with my clients                                |
| `* * *`  | insurance agent                                | view expired records          | refer to passed records in case clients want to know/renew insurance   |


*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `Mr Agent` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Add a client**

**MSS**
1. User enters command and arguments to add new client.
2. Mr Agent shows success message for adding client.

**Extensions**
* 2a. The argument(s) are incomplete.

    * 2a1. Mr Agent shows an error message.

      Use case resumes at step 1.

**Use case: Edit a client**

**MSS**

1.  User requests to list clients.
2.  Mr Agent shows a list of clients.
3.  User requests to edit a specific client by index in the list and enters requested details.
4.  Mr Agent updates the client.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. Mr Agent shows an error message.

      Use case resumes at step 2.
      
* 3b. Mr Agent detects an error in the entered data.

    * 3a1. Mr Agent shows an error message.

      Use case resumes at step 3.
    
**Use case: List a client**

**MSS**

1.  User requests to list clients
2.  Mr Agent shows a list of clients

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.


**Use case: Delete a client**

**MSS**

1.  User requests to list clients
2.  Mr Agent shows a list of clients
3.  User requests to delete a specific client by index in the list
4.  Mr Agent deletes the client

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. Mr Agent shows an error message.

      Use case resumes at step 2.

**Use case: Sort appointments**

**MSS**
1. User enters command and arguments to sort appointments ascendingly.
2. Mr Agent shows success message for sorting appointments.

**Extensions**
* 1a. The argument(s) are incomplete.

    * 1a1. Mr Agent shows an error message and correct command usage.

      Use case resumes at step 1.

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 clients without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  No network connection needed.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Client**: Contacts that needs to be managed by user
* **Insurance category:** Types of insurance, e.g. health, financial, life
* **Insurance code:** Unique IDs attached to each insurance
* **Record:** Insurances that are bought by the client. The insurances are saved as records in the database



--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a client

1. Deleting a person while all clients are being shown

   1. Prerequisites: List all clients using the `list` command. Multiple clients in the list.

   1. Test case: `delete -c 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete -c 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete -c`, `delete -c x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
