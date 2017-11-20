/* Nishant Sinha
 * Bhola Data Structures MW 5:30-6:30
 * 
 * This program contains the main method used to test the ArrayList class.
 * This program has two methods: the main method used to test each method in the ArrayList class for each type of constructor
 * and the randomInt() method that generates a random integer between 1 and 25.
 * The only data structure used in this program is the ArrayList class.
 * Run the program through the console `java TestArray`
 */

import java.util.Random;

public class TestArray {

    private static final int TEST_SIZE = 15;

    public static void main(String[] args) {

        // Initialize two ArrayLists using both constructors
        System.out.println("Creating ArrayList a and ArrayList b");
        ArrayList a = new ArrayList();
        ArrayList b = new ArrayList(TEST_SIZE);

        // Populate the ArrayLists with TEST_SIZE random integers
        System.out.println("Populating ArrayList a and ArrayList b");
        for (int i = 0; i < TEST_SIZE; i++) {
            a.add(randomInt());
            b.add(randomInt());
        }

        // Print out the two ArrayLists to see the initial values.
        System.out.println("a: " + a.toString());
        System.out.println("b: " + b.toString());
        System.out.println();

        // Testing the add method
        System.out.println("Adding a new random value to each array using the add(Object) method: ");
        a.add(randomInt());
        System.out.println("a: " + a.toString());
        b.add(randomInt());
        System.out.println("b: " + b.toString());
        System.out.println();

        // Testing the overloaded add method
        System.out.println("Adding a new random value to each array using the add(int, Object) method: ");
        a.add(0, randomInt());
        System.out.println("a: " + a.toString());
        b.add(0, randomInt());
        System.out.println("b: " + b.toString());
        System.out.println();

        // Testing the isIn and find methods
        int testIsIn = randomInt();
        System.out.println("Testing the iSIn(int) method:");
        System.out.print("Checking if " + testIsIn + " is in a: ");
        System.out.println(a.isIn(testIsIn));
        System.out.print("Checking if " + testIsIn + " is in b: ");
        System.out.println(b.isIn(testIsIn));
        System.out.println();

        // Testing the find method
        System.out.println("Testing the find(Object) method:");
        System.out.println(testIsIn + " is in a at index: " + a.find(testIsIn));
        System.out.println(testIsIn + " is in b at index: " + b.find(testIsIn));
        System.out.println();
        
        // Testing the get methods
        System.out.println("Testing the get(int) method:");
        System.out.println("The object in a at index " + TEST_SIZE + " is " + a.get(TEST_SIZE));
        System.out.println("The object in b at index " + TEST_SIZE + " is " + b.get(TEST_SIZE));
        System.out.println();

        // Testing the size method
        System.out.println("Testing the size() method:");
        System.out.println("ArrayList a has " + a.size() + " objects.");
        System.out.println("ArrayList b has " + b.size() + " objects.");
        System.out.println();

        // Testing the remove method
        System.out.println("Testing the remove(int) method:");
        System.out.println("Removing all objects from a:");
        // For each bucket in a.array, try removing an integers 1-25
        for (int i = 0; i < a.size(); i++) {
            for (int j = 1; j < 26; j++) {
                a.remove(j);
            }
        }
        System.out.println("a: " + a.toString());
        
        System.out.println("Removing the first Object in b:");
        int firstInB = (int) b.get(0);
        b.remove(firstInB);
        System.out.println("b: " + b.toString());
        
        System.out.println();

        // Testing the isEmpty method
        System.out.println("Testing the isEmpty() method:");
        System.out.println("ArrayList a is empty: " + a.isEmpty());
        System.out.println("ArrayList b is empty: " + b.isEmpty());
        System.out.println();

    }

    // Returns a new random integer between 1 and 25.
    private static int randomInt() {
        Random rand = new Random();
        return (int) (rand.nextFloat() * 25 + 1);
    }
}
