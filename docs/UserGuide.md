---
layout: page
title: User Guide
---

Mr. Agent is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Mr. Agent can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start (***todo***)

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `Mr Agent.jar` from [here](https://github.com/AY2122S2-CS2103-F09-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features (***todo***)

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) are not allowed.<br>

</div>

## Client Management
### Adding a client: `add -c`

Adds a client to Mr. Agent.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS d/DESCRIPTION [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A client can have any number of tags (including 0)
</div>

Examples:
* `add n/JunJie p/98765432 e/junjie@example.com a/John street, block 123, #01-01 d/come from China`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 d/like to coffee t/NUS`

### Listing all client : `list -c`

Shows a list of all clients in the Mr. Agent.

Format: `list -c`

### Deleting a client : `delete -c`

Deletes the specified client from the Mr. Agent.

Format: `delete -c INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list -c` followed by `delete -c 2` deletes the 2nd person in the Mr. Agent.
* `find -c Junjie` followed by `delete -c 1` deletes the 1st person in the results of the `find` command.

### Editing a client : `edit -c`

Edits an existing client in the Mr. Agent.

Format: `edit -c INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [d/DESCRIPTION] [t/TAG]…​`

* Edits the client at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the title of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating client by name: `find -c`

Finds client whose names contain any of the given keywords.

Format: `find -c KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `mike` will match `Mike`
* The order of the keywords does not matter. e.g. `Lee Junjie` will match `Junjie Lee`
* Only the name is searched.
* Only full words will be matched e.g. `Le` will not match `Lee`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Lee Junjie` will return `Wong Junjie`, `Lee Yang`
* 
Examples:
* `find -c John` returns `john` and `John Doe`
* `find -c alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)



## Insurance

### Adding an Insurance: `add -i`

Adds a type of insurance 

Format: `add -i KEYWORD`

* Adds a insurance type as specified in the `KEYWORD`
* The keyword will be case insensitve

Examples: (***todo***)
* `add -i hosipitalision`
* `add -i terminal_illness`

### Listing all Insurance: `list -i`

Shows a list of all insurances in the Mr. Agent.

Format: `list -i`

### Deleting an Insurance: `delete -i`

Deletes the specified insurance from the Mr. Agent.

Format: `delete -i INDEX`

### Locating insurance by title: `find -i`

Finds insurance which titles contain any of the given keywords.

Format: `find -i KEYWORD [MORE_KEYWORDS]`

### Editing an Insurance: `edit -i` (***coming soon***)

## Record

### Adding a record to a client: `add -r`

Adds an insurance to a client

Format: `add -r CLIENT_INDEX INSURANCE_INDEX [sd/START_DATE] [ed/END_DATE]

* START_DATE and END_DATE are in the format of dd-MM-yyyy
* Each client index can only own 1 insurance of each type
* Client index and Insurance index starts from 1

Examples: (***todo***)
* `add -r c/10 i/2  sd/23-02-2022 ed/23-02-2024` , adds an insurance 2 to index 10 with start date from 23-02-2022 and end date at 23-02-2024
* `add -r c/25 i/2  sd/21-11-2021 ed/21-11-2023`


### Deleting a record: `delete -r`

Deletes the specified record from the Mr. Agent.

Format: `delete -r RECORD_INDEX`

### Listing all records : `list -r`

Shows a list of all records in the Mr. Agent.

Format: `list -r`

### Editing a record : `edit -r` 

Edits an existing record in the Mr. Agent.

Format: `edit -r INDEX [c/CLIENT_INDEX] [i/INSURANCE_INDEX] [sd/START_DATE] [ed/END_DATE]`

* Edits the record at the specified `INDEX`. The index refers to the index number shown in the displayed record list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.


Examples:
*  `edit -r 1 c/2 i/2` Edits the client and the type of insurance of the 1st record to be client 2 and insurance 2 respectively.

### Locating record by keyword (insurance name): `find -r`

Finds records whose insurance name contains any of the given keyword

Format: `find -r KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `health` will match `Health`
* The order of the keywords does not matter. e.g. `critical illness ` will match `illnes critical`
* Only the insurance name is searched.
* Only full words will be matched e.g. `hea` will not match `health`
* Records matching at least one keyword will be returned (i.e. `OR` search).

Examples: 
* `find -r health` 

## History

### Listing appointment history: `list -h`

Shows a list of all passed appointments.

Format: `list -h`

### Listing expired records: `list -e`

Shows a list of all expired records.

Format: `list -e`

### List history by client (***coming soon***)

## Appointment

### Adding an appointment: `add -a`

Adds a new appointment with client

Format: `add -a d/DESCRIPTION dt/DATETIME`

Examples:
* `add -a d/Meet James dt/20-02-2022 18:00` 

### Listing all appointments: `list -a`

Lists all appointments with clients

Format: `list -a`

### Deleting an appointment: `delete -a`

Deletes an unneeded appointment

Format: `delete -a APPOINTMENT_INDEX`

### Editing an appointment: `edit -a`

Edits an existing appointment

Format: `edit -a APPOINTMENT_INDEX [d/DESCRIPTION] [dt/DATETIME]` 

### Locating appointments by keyword: `find -a`

Find appointment(s) using keyword(s)

Format: `find -a KEYWORD [MORE_KEYWORDS]`

### Sort appointments: `sort -a`

Sorts the appointment list ascendingly/descendingly by time

Format: `sort -a SORT_OPTION`
* To sort ascendingly, replace SORT_OPTION with `a`
* To sort descendingly, replace SORT_OPTION with `d`

## Others

### Clearing all entries : `clear`

Clears all entries from the Mr. Agent.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                                            | Format, Examples                                                                                                                                                         |
|---------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add a client**                                  | `add -c n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Delete a client**                               | `delete -c INDEX`<br> e.g., `delete 3`                                                                                                                                   |
| **Edit a client**                                 | `edit -c INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                           |
| **Find a client**                                 | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                               |
| **List all client**                               | `list -c`                                                                                                                                                                |
| **Add an appointment**                            | `add -a d/DESCRIPTION dt/DATETIME`                                                                                                                                       |
| **List all appointments**                         | `list -a`                                                                                                                                                                |
| **Delete an appointment**                         | `delete -a APPOINTMENT_INDEX`                                                                                                                                            |
| **Edit an appointment**                           | `edit -a APPOINTMENT_INDEX [d/DESCRIPTION] [dt/DATETIME]`                                                                                                                |
| **Find appointment(s)**                           | `edit -a APPOINTMENT_INDEX [d/DESCRIPTION] [dt/DATETIME]`                                                                                                                |
| **Sort appointments in ascending order by time**  | `sort -a a`                                                                                                                                                              |
| **Sort appointments in descending order by time** | `sort -a d`                                                                                                                                                              |

