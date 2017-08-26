/* Nishant Sinha
 * 2720 Data Structures, 86473
 * Bhola
 */

import java.util.Scanner;
import java.util.Random;

public class Matrix {

    private static MatrixStruct matrix_a;
    private static MatrixStruct matrix_b;

    public static void main (String[] args) {
        matrix_a = new MatrixStruct("Matrix A");
        matrix_b = new MatrixStruct("Matrix B");

        matrix_a.fill();
        matrix_b.fill();

        System.out.println(matrix_a);
        System.out.println(matrix_b);

        try {
            long startTime = System.currentTimeMillis();
            matrix_a.multiply(matrix_b);
            long endTime = System.currentTimeMillis();
            printTime(startTime, endTime);
        } catch (RuntimeException e) {
            System.out.println(e);
        }

        try {
            long startTime = System.currentTimeMillis();
            matrix_a.add(matrix_b);
            long endTime = System.currentTimeMillis();
            printTime(startTime, endTime);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void printTime(long start, long end) {
        long time = end-start;
        System.out.println("Calculation took " + time + " milliseconds");
    }


}


class MatrixStruct {

    private String name;
    private int[] dimensions;
    private int[][] matrix;

    public MatrixStruct(String name) {
        this.name = name;
        this.dimensions = setDimensions(name);
        int m = dimensions[0];
        int n = dimensions[1];
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

    public int setDimension(String name) {
        final int MIN_DIMENSION = 5;

        Scanner scan = new Scanner(System.in);

        System.out.println(name);
        System.out.print("Enter a dimension of " + MIN_DIMENSION + " or greater: ");
        int dimension = scan.nextInt();

        while (dimension < MIN_DIMENSION) {
            System.out.println("You must enter a dimension of " + MIN_DIMENSION + " or greater!");
            System.out.print("Enter a dimension of " + MIN_DIMENSION + " or greater: ");
            dimension = scan.nextInt();
        }

        return dimension;
    }

    public void fill() {
        Random random = new Random();

        for (int row = 0; row < this.m; row++) {
            for (int col = 0; col < this.n; col++) {
                this.matrix[row][col] = random.nextInt(30) + 1;
            }
        }
    }

    public void multiply (MatrixStruct matrix_b) throws RuntimeException {
        if (this.canBeMultipliedBy(matrix_b)) {
            int[][] result = new int[this.getRows()][matrix_b.getColumns()];

        }
    }

    public boolean canBeMultipliedBy (MatrixStruct matrix) {
        if (this.getColumns() == matrix.getRows()) {
            return true;
        } else {
            throw new IllegalArguementException("Matrix dimensions mismatch. Cannot be multiplied");
        }
    }

    public void add (MatrixStruct matrix_b) throws RuntimeException {
        if (this.canBeAddedTo(matrix_b)) {
            MatrixStruct added = new MatrixStruct("Matrix A + Matrix B", this.m, this.n);
            int[][] table = added.getMatrix();

            for (int row = 0; row < this.m; row++) {
                for (int col = 0; col < this.n; col++) {
                    table[row][col] = this.matrix[row][col] + matrix_b.matrix[row][col];
                }
            }

            System.out.println(added);
        }
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

