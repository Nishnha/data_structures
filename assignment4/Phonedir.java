/* Nishant Sinha
 * Data Structures MW
 * Bhola
 * Target: Java SE 7
 */

/* This program recreates a phone directory.
 * To run the program run java Phonedir.
 * You will be presented with a menu of options
 * and prompted to pick one.
 *
 * The phone directory uses a linked list to hold
 * records. The list is manipulated in place and
 * all records are added in their alphabetical position.
 */

import java.util.Scanner;
import java.util.LinkedList;

public class Phonedir {

    public static void main(String[] args) {

        System.out.println("Nishant Sinha");

        Directory phonebook = new Directory();
        Record record = null; // current record selected in the phonebook.
        Scanner keyboard = new Scanner(System.in);
        char selection;

        do {
            System.out.print(getMenu());
            selection = keyboard.next().charAt(0);

            try {
                switch(selection) {
                    case 'a':
                        phonebook.showAllRecords();
                        break;
                    case 'd':
                        phonebook.deleteRecord(record);
                        record = null;
                        break;
                    case 'f':
                        record.setFname(keyboard);
                        break;
                    case 'l':
                        phonebook.deleteRecord(record);
                        record = record.setLname(keyboard); // Can still be modified since record is in memory
                        phonebook.addRecord(record);
                        break;
                    case 'n':
                        record = phonebook.createRecord(keyboard);
                        break;
                    case 'p':
                        record.setPnumber(keyboard);
                        break;
                    case 'q':
                        break;
                    case 's':
                        Record temp = phonebook.selectRecord(keyboard);
                        if (temp != null)
                            record = temp;
                        else
                            System.out.println("No matching record found");
                        break;
                    default:
                        System.out.println("Illegal command");
                        break;
                };
            } catch (NullPointerException e) {
                System.out.println("No current record");
            }
        } while (selection != 'q');

        keyboard.close();
    }

    /* returns a formatted String containing the options
     * for the phonedir program
     */
    private static String getMenu() {
        return "\n\nA program to keep a phone directory\n\n" +
            "\ta\tShow all records\n\n" +
            "\td\tDelete the current record\n\n" +
            "\tf\tChange the first name in the current record\n\n" +
            "\tl\tChange the last name in the current record\n\n" +
            "\tn\tAdd a new record\n\n" +
            "\tp\tChange the phone number in the current record\n\n" +
            "\tq\tQuit\n\n" +
            "\ts\tSelect a record from the record list ot become the current record\n\n" +
            "Enter a command from the list above (q to quit): ";
    }
}


/* A record is the object a directory is made of.
 * A record contains a first name, last name, and phone number and
 * represents an entree in a directory.
 */
class Record {
    private String fname;
    private String lname;
    private int pnumber;

    public Record(String fname, String lname, int pnumber) {
        this.fname = fname;
        this.lname = lname;
        this.pnumber = pnumber;
    }

    // Prompts the user to enter a new first name
    public void setFname(Scanner keyboard) {
        System.out.print("Enter a new first name: ");
        this.fname = keyboard.next();

        this.displayCurrentRecord();
    }

    // Returns the first name of the record
    public String getFname() {
        return this.fname;
    }

    /* Prompts the user to enter a new last name.
     * Since the directory is sorted by last name, changing lname will
     * resort the record.
     */
    public Record setLname(Scanner keyboard) {
        System.out.print("Enter a new last name: ");
        this.lname = keyboard.next();

        this.displayCurrentRecord();

        return this;
    }

    // Returns the last name of the record
    public String getLname() {
        return this.lname;
    }

    // Prompts the user to enter a new phone number
    public void setPnumber(Scanner keyboard) {
        System.out.print("Enter a new phone number: "); // Can still be modified since record is in memory
        this.pnumber = keyboard.nextInt();

        this.displayCurrentRecord();
    }

    @Override
    public String toString() {
        return this.fname + " " + this.lname + " " + this.pnumber;
    }

    public void displayCurrentRecord() {
        System.out.println("\n<> Current record: " + this.toString());
    }

    public String formattedToString() {
        return this.fname + "\t\t" + this.lname + "\t\t" + this.pnumber;
    }
}

/* The directory holding records.
 * Acts like a phonebook.
 * Will add records in alphabetical order by last name.
 */
class Directory {

    LinkedList<Record> records;

    public Directory() {
        records = new LinkedList<Record>();
    }

    /* Creates a record prompting the user for input.
     * The record is then added to the Directory
     */
    public Record createRecord(Scanner keyboard) {
        System.out.print("Enter first name: ");
        String fname = keyboard.next();

        System.out.print("Enter last name: ");
        String lname = keyboard.next();

        System.out.print("Enter phone number: ");
        int pnumber = keyboard.nextInt();

        Record record = new Record(fname, lname, pnumber);
        this.addRecord(record);
        record.displayCurrentRecord();

        return record;
    }

    /* Adds a new record to the directory
     * in alphabetical order by last name
     */
    protected void addRecord(Record r) {
        int insert = 0; // If no records exist addRecord will default to adding the record at index 0

        for (Record s : records) {
            if(s.getLname().compareTo(r.getLname()) < 0) { // When r.lname comes before s.lname alphabetically
                insert = records.indexOf(s) + 1;
            }
        }

        records.add(insert, r);
    }

    public void deleteRecord(Record r) {
        records.remove(r);
    }

    public Record selectRecord(Scanner keyboard) {
        System.out.print("Enter first name: ");
        String fname = keyboard.next();

        System.out.print("Enter last name: ");
        String lname = keyboard.next();

        for (Record r : records) {
            if (r.getFname().equals(fname) && r.getLname().equals(lname))
                return r;
        }

        return null;
    }

    /* Shows all records in a table format
     */
    public void showAllRecords() {
        System.out.println();
        System.out.println("First Name\tLast Name\tPhone Number");
        System.out.println("----------\t---------\t------------");
        for (Record r : records) {
            System.out.println(r.formattedToString());
        }
    }
}
