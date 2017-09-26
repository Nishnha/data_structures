import java.util.Random;

public class TestArray {

    private static final int ARRAYLIST_SIZE = 15;

    public static void main(String[] args) {

        // Initialize two ArrayLists.
        ArrayList a = new ArrayList();
        ArrayList b = new ArrayList(ARRAYLIST_SIZE);

        // Populate the ArrayLists with 15 random integers
        for (int i = 0; i < ARRAYLIST_SIZE; i++) {
            a.add(randomInt());
            b.add(randomInt());
        }

        // Print out the two ArrayLists to see the initial values.
        System.out.println("a: " + a.toString());
        System.out.println("b: " + b.toString());
        System.out.println();

        // Testing the add method
        System.out.println("Adding a new random value to each array: ");
        a.add(randomInt());
        b.add(randomInt());
        System.out.println("a: " + a.toString());
        System.out.println("b: " + b.toString());
        System.out.println();

        // Testing the overloaded add method
        System.out.println("Adding a new random value to each array: ");
        a.add(0, randomInt());
        b.add(1, randomInt());
        System.out.println("a: " + a.toString());
        System.out.println("b: " + b.toString());
        System.out.println();

        // Testing the insert method
        System.out.println("Inserting a new random value to each array: ");
        a.insert(0, randomInt());
        b.insert(1, randomInt());
        System.out.println("a: " + a.toString());
        System.out.println("b: " + b.toString());
        System.out.println();

        // Testing the isIn and find methods
        int testIsIn = randomInt();

        String isInA = a.isIn(testIsIn) ? " is in a" : " is not in a";
        System.out.println(testIsIn + isInA + " at index " + a.find(testIsIn));

        String isInB = b.isIn(testIsIn) ? " is in b" : " is not in b";
        System.out.println(testIsIn + isInB +  " at index " + a.find(testIsIn));
        System.out.println();

        // Testing the get methods
        Random rand = new Random();
        int randIndex = (int) (rand.nextFloat() * ARRAYLIST_SIZE);
        System.out.println("The object in a at index " + randIndex + " is " + a.get(randIndex));
        System.out.println("The object in b at index " + randIndex + " is " + b.get(randIndex));
        System.out.println();

        // Testing the size method
        System.out.println("ArrayList a has " + a.size() + " objects.");
        System.out.println("ArrayList b has " + b.size() + " objects.");
        System.out.println();

        // Testing the remove method
        System.out.println("Removing all objects from a");
        for (int i = 0; i < a.size(); i++)
            a.remove(i);
        System.out.println("a: " + a.toString());
        System.out.println();

        // Testing the isEmpty method
        System.out.println("ArrayList a is empty: " + a.isEmpty());
        System.out.println();

    }

    // Returns a new random integer between 1 and 25.
    private static int randomInt() {
        Random rand = new Random();
        return (int) (rand.nextFloat() * 25 + 1);
    }
}
