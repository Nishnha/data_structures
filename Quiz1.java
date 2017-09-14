import java.util.ArrayList;
import java.util.Random;

public class Quiz1 {

    public static void main(String[] args) {
        Random rand = new Random();

        ArrayList<Integer> array = new ArrayList<>(15);
        for (int i = 0; i < 15; i++) {
            array.add(rand.nextInt(50) + 1);
        }

        sorting(array);
    }


    // sorts in ascending order
    public static void sorting(ArrayList<Integer> unsorted) {
        boolean isSorted = false;

        // convert the array list to an array manually since
        // cannot use ArrayList.toArray in a static context
        int[] sorted = new int[15];

        for (int i = 0; i < unsorted.size(); i++) {
            sorted[i] = unsorted.get(i);
        }

        int temp;

        do {
            isSorted = true;
            for (int i = 0; i < sorted.length; i++) {
                for (int j = i; j < sorted.length; j++) {
                    if (sorted[i] > sorted[j]) {
                        temp = sorted[i];
                        sorted[i] = sorted[j];
                        sorted[j] = temp;
                        isSorted = false;
                    }
                }
            }
        } while (!isSorted);

        // print out the array
        for (int i = 0; i < sorted.length; i++) {
            System.out.print(sorted[i] + " ");
        }
        System.out.println();
    }
}
