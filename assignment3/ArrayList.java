public class ArrayList {
    private int capacity;
    private int occupied = 0;
    private Object[] array;

    public ArrayList() {
        this.capacity = 10;
        array = new Object[10];
    }

    public ArrayList(int capacity) {
        this.capacity = capacity;
        array = new Object[capacity];
    }

    private void resize() {
        int size = (int) (this.array.length * 1.5);
        Object[] output = new Object[size];

        for (int i = 0; i < this.capacity; i++) {
            output[i] = this.array[i];
        }

        this.capacity = size;
        this.array = output;

        System.out.println("An array was resized to hold " + this.capacity);
    }

    // Shift the values in the array to the next bucket by 1 from the given index
    private void shift(int index) {
        for (int i = this.size() + 1; i > index; i--) {
            this.array[i] = this.array[i - 1];
        }
    }


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

    public void add(int index, Object o) {
            this.array[index] = o;
    }

    public void insert(int index, Object o) {
        if (occupied < capacity) {
            this.occupied += 1;
            this.shift(index);
            this.array[index] = o;
        } else {
            this.resize();
            this.occupied += 1;
            this.shift(index);
            this.array[index] = o;
        }
    }

    public Object get(int index) {
        return this.array[index];
    }

    public int size() {
        return this.occupied;
    }

    public boolean isIn(Object o) {
        for (int i = 0; i < this.array.length; i++)
            if (this.array[i] == o)
                return true;

        return false;
    }

    public int find(Object o) {
        for (int i = 0; i < this.array.length; i++)
            if (this.array[i] == o)
                return i;
        return -1;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public void remove(Object o) {
        for (int i = 0; i < this.array.length; i++) {
            if (this.array[i] == o) {
                this.array[i] = null;
                this.occupied -= 1;
            }
        }
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < this.size(); i++) {
            output = output + this.get(i) + ", ";
        }
        // Chop off the extra comma
        output = output.substring(0, output.length() - 2);

        return output;
    }
}
