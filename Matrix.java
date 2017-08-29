/* Nishant Sinha
 * 2720 Data Structures, 86473
 * Bhola
 *
 *
 * This program creates two matricies of dimensions determined by user input
 * and then attempts to multiply and add them together
 *
 * The addition algorithm first checks to make sure the matricies can be added
 * (ie. they have the same dimensions) and then adds the values of each matrix
 * row by row into a new one of the same dimensions
 *
 * The multiplication algorithm checks first to see if the matricies can be
 * multiplied (ie. inner dimensions match) and then multiplies and adds the
 * corresponding rows/columns for each spot in a new matrix.
 *
 * The data structure used for representing the matrix is the MatrixStruct class
 * it contains 4 components: a name for the array (String), an int for the number
 * of rows, an int for the number of columns, and a double int array to represent
 * the matrix itself/
 *
 * To use the program: when prompted enter two values for each matrix: number
 * of rows, and number of columns. The program will print out each matrix after
 * filling each slot with a random int from 1-30. It will then multiply them
 * together and print the result (if possible) and add them together and print
 * the resulting matrix (if possible). The time for each calculation is also
 * displayed. The time does not include the time taken to print out each matrix
 * to the console.
 *
 * The Matrix class is the runner class for this assignment. It contains the main
 * method as well as the method to time the execution for multiplying and adding
 * the matricies.
 *
 * The MatrixStruct class, as mentioned above, is how the matricies are stored.
 */

import java.util.Scanner;
import java.util.Random;

public class Matrix {

    private static MatrixStruct matrix_a;
    private static MatrixStruct matrix_b;

    public static void main (String[] args) {

        boolean repeat = true;

        Scanner scan = new Scanner(System.in);

        while (repeat) {
            repeat = false;

            matrix_a = new MatrixStruct("Matrix A");
            matrix_b = new MatrixStruct("Matrix B");

            matrix_a.fill();
            matrix_b.fill();

            System.out.println(matrix_a);
            System.out.println(matrix_b);

    	// multiply
            try {
                long startTime = System.currentTimeMillis();
                MatrixStruct multiplied = matrix_a.multiply(matrix_b);
                long endTime = System.currentTimeMillis();
                System.out.println(multiplied);
                printTime(startTime, endTime);
            } catch (RuntimeException e) {
                System.out.println(e);
            }

    	// add
            try {
                long startTime = System.currentTimeMillis();
                MatrixStruct added = matrix_a.add(matrix_b);
                long endTime = System.currentTimeMillis();
                System.out.println(added);
                printTime(startTime, endTime);
            } catch (Exception e) {
                System.out.println(e);
            }

            System.out.println("Would you like to rerun the program? (y/n)");
            char answer = scan.next().charAt(0);
            if (answer == 'y' || answer == 'Y') repeat = true;
        }
    }

    public static void printTime(long start, long end) {
        long time = end-start;
        System.out.println("Calculation took " + time + " milliseconds");
    }

}


class MatrixStruct {

    private String name;
    private int m;
    private int n;
    private int[][] matrix;

    public MatrixStruct(String name) {
        this.name = name;
        int[] dimensions = setDimensions(name);
        this.m = dimensions[0];
        this.n = dimensions[1];
        this.matrix = new int[m][n];
    }

    public MatrixStruct(String name, int m, int n) {
        this.name = name;
        this.m = m;
        this.n = n;
        matrix = new int[m][n];
    }

    public String getName() {
        return this.name;
    }

    public int getRows() {
        return this.m;
    }

    public int getColumns() {
        return this.n;
    }

    public int[][] getMatrix() {
        return this.matrix;
    }

    public int[] setDimensions(String name) {
        final int MIN_DIMENSION = 25;

        Scanner scan = new Scanner(System.in);

        System.out.println(name);
        System.out.println("Matrix dimensions must be " + MIN_DIMENSION + " or greater: ");
    	System.out.print("Enter number of rows and columns (separated by a space): ");
        int m = scan.nextInt();
	int n = scan.nextInt();

        while (m < MIN_DIMENSION || n < MIN_DIMENSION) {
            System.err.println("Matrix dimensions must be " + MIN_DIMENSION + " or greater!");
    	    System.out.print("Enter number of rows and columns (separated by a space): ");
            m = scan.nextInt();
	    n = scan.nextInt();
        }

        int dimensions[] = {m,n};
        return dimensions;
    }

    // fill in the matrix with random integers ranging from 1 to 30
    public void fill() {
        Random random = new Random();

        for (int row = 0; row < this.m; row++)
            for (int col = 0; col < this.n; col++)
                this.matrix[row][col] = random.nextInt(30) + 1;
    }

    public MatrixStruct multiply (MatrixStruct matrix_b) throws RuntimeException {
        MatrixStruct result = new MatrixStruct(this.getName() + " * " + matrix_b.getName(), this.getRows(), matrix_b.getColumns());

        if (this.canBeMultipliedBy(matrix_b)) {

            for (int i = 0; i < this.getRows(); i++)
		for (int j = 0; j < matrix_b.getColumns(); j++)
		    for (int k = 0; k < matrix_b.getRows(); k++)
			result.matrix[i][j] += this.matrix[i][k] * matrix_b.matrix[k][j];

	}

        return result;
    }

    public boolean canBeMultipliedBy (MatrixStruct matrix) {
        if (this.getColumns() == matrix.getRows()) {
            return true;
        } else {
            throw new IllegalArgumentException("Matrix dimensions mismatch. Cannot be multiplied");
        }
    }

    public MatrixStruct add (MatrixStruct matrix_b) throws RuntimeException {
        MatrixStruct added = new MatrixStruct(this.getName() + " + " + matrix_b.getName(), this.getRows(), this.getColumns());

        if (this.canBeAddedTo(matrix_b)) {
            for (int row = 0; row < this.m; row++)
                for (int col = 0; col < this.n; col++)
                    added.matrix[row][col] = this.matrix[row][col] + matrix_b.matrix[row][col];
        }

        return added;
    }

    public boolean canBeAddedTo (MatrixStruct matrix) {
        if (this.getColumns() == matrix.getColumns() &&
            this.getRows() == matrix.getRows()) {
            return true;
        } else {
            throw new IllegalArgumentException("Matrix dimensions mismatch. Cannot be added.");
        }
    }

    @Override
    public String toString() {
        String output = "\n" + this.getName() + "\n";

        for (int row = 0; row < this.m; row++) {
            for (int col = 0; col < this.n; col++) {
                output = output + matrix[row][col] + "\t";
            }

            output = output + "\n";
        }

        return output;
    }

}

