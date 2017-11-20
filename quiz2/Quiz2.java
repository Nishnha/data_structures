/* Nishant Sinha
 * Quiz2
 * 10/26/2017
 */

import java.util.Scanner;

public class Quiz2 {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        boolean again = false;

        do {
            System.out.println("Enter a string of As and Bs to test if it is in the form of A^(2n)B^(n)");
            String inputString = keyboard.next();

            boolean isCorrectForm = correctForm(0, 0, inputString);

            if (isCorrectForm)
                System.out.println("The string " + inputString + " is in the correct form.");
            else
                System.out.println("The string " + inputString + " is not in the correct form.");

            System.out.println("Do you want to test again? (y/n)");

            if (keyboard.next().charAt(0) == 'y')
                again = true;
            else
                again = false;

        } while (again);
    }

    private static boolean correctForm(int a, int b, String s) {
        char c = s.charAt(0);

        if (c == 'a' || c == 'A')
            a++;

        if (c == 'b' || c == 'B')
            b++;

        if (s.length() == 1)
            return (a == 2*b);

        return correctForm(a, b, s.substring(1, s.length()));
    }

}
