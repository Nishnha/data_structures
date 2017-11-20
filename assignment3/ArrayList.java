/* Nishant Sinha
 * Bhola Data Structures MW 5:30-6:30
 * 
 * This class attempts to recreate the ArrayList class implemented in the java.utils library.
 * This class is run with the TestArray main method. See TestArray.java for details.
 * The ArrayList class has 3 private variables: a capacity (defaults to 10), occupied (defaults to 0), and an array of objects (defaults to empty).
 * The capacity will grow by 1.5 times each time the ArratList reaches the max capacity.
 * Occupied grows and shrinks with the number of objects that are in the ArrayList.
 * The array of objects acts simply as a container for the objects passed into the class.
 * The toString() method of this class will print out the current state of ArrayList.array
 */

public class ArrayList {
    private int capacity;
    private int occupied = 0;
    private Object[] array;

    /* The default constructor for ArrayList
     * Initializes an ArrayList with a capacity of 10
     */
    public ArrayList() {
        this.capacity = 10;
        array = new Object[10];
    }

    /* Overloaded constructor for an ArrayList
     * Initializes an ArrayList with a passed in capacity
     * @param capacity must be greater than 0
     */
    public ArrayList(int capacity) {
        this.capacity = capacity;
        array = new Object[capacity];
    }

    /* Resizes ArrayList.array to hold 1.5 times the amount of objects.
     * Creates a new array of size (1.5 * ArrayList.capacity) and copies the objects
     * from the previous ArrayList over. Sets ArrayList.array to point at the new array
     * and changes ArrayList.capacity to reflect the new capacity.
     */
    private void resize() {
        int size = (int) (this.array.length * 1.5);
        Object[] output = new Object[size];
        int prevCapcity = this.capacity;

        for (int i = 0; i < this.capacity; i++) {
            output[i] = this.array[i];
        }

        this.capacity = size;
        this.array = output;

        System.out.println("ArrayList was resized from " + prevCapcity + " to " + this.capacity + ".");
    }

    /* Shift the values in ArrayList.array to the bucket on the right from the given index.
     * Used as a helper function for add(int, Object).
     * @param index must be a valid index
     */
    private void shiftR(int index) {
        for (int i = this.size() + 1; i > index; i--) {
            this.array[i] = this.array[i - 1];
        }
    }

    /* Appends the passed in Object to the end of the ArrayList
     * Resizes the ArrayList if ArrayList.array is too small
     */
    public void add(Object o) {
        if (occupied < capacity) {
            this.occupied += 1;
            this.array[this.occupied - 1] = o;
        } else {
            this.resize();
            this.occupied += 1;
            this.array[this.occupied - 1] = o;
        }
    }

    /* Adds the passed in Object at the passed in index.
     * Shifts ArrayList.array so that no objects are overwritten.
     * Resizes the ArrayList if ArrayList.array is too small.
     */
    public void add(int index, Object o) {
        if (occupied < capacity) {
            this.occupied += 1;
            this.shiftR(index);
            this.array[index] = o;
        } else {
            this.resize();
            this.occupied += 1;
            this.shiftR(index);
            this.array[index] = o;
        }
    }

    /* Returns the object at the passed in index.
     * @param index must be greater than 0
     */
    public Object get(int index) {
        return this.array[index];
    }

    /* Returns the number of occupied slots in the ArrayList */
    public int size() {
        return this.occupied;
    }

    /* Returns whether the passed in object is in the ArrayList */
    public boolean isIn(Object o) {
        for (int i = 0; i < this.array.length; i++)
            if (this.array[i] == o)
                return true;

        return false;
    }

    /* Returns the index of the first occurance of the passed in object in the ArrayList.
     * Returns -1 if the object is not in the ArrayList.
      */
    public int find(Object o) {
        for (int i = 0; i < this.array.length; i++)
            if (this.array[i] == o)
                return i;
        return -1;
    }

    /* Returns true if the ArrayList is empty */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /* Shift the values in ArrayList.array to the bucket on the left from the given index.
     * Used as a helper function for remove(Object).
     * @param index must be a valid index
     */
    private void shiftL(int index) {
        for (int i = index; i < this.occupied; i++) {
            this.array[i] = this.array[i + 1];
        }
    }

    /* Removes the passed in object from the ArrayList.
     * shifts Objects in the ArrayList one bucket left
     * from the first occurance that matches the passed in Object,
     * sets the last object as 0, and decreases the size of the ArrayList by 1.
    */
    public void remove(Object o) {
        for (int i = 0; i < this.array.length; i++) {
            if (this.array[i] == o) {
                this.shiftL(i);
                this.array[this.occupied] = 0;
                this.occupied -= 1;
            }
        }
    }

    /* Returns a String with the contents of ArrayList.array */
    @Override
    public String toString() {
        String output = "";
        
        for (int i = 0; i < this.size(); i++)
            output = output + this.get(i) + ", ";

        // Chop off the extra comma
        if (output.length() > 2)
            output = output.substring(0, output.length() - 2);

        return output;
    }
}
