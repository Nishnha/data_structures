/* Nishant Sinha
 * Assignment 5
 * Calc.java
 * 11/01/17 TBH didn't know this assignment was a thing until today.
 * Bhola MW 5:30-6:30
 */

/**
 * Calc.java evaluates an infix expression entered by the user.
 * An expression may contain:
 * (1) Integer constants
 * (2) x, a value to be supplied later
 * (3) binary operators (+, -, *, /, and %)
 * (4) Parentheses
 *
 * Calc.java converts the infix expression to postfix and display the result.
 * If x is entered in the program, the program will run again prompting the user for a
 * new value of x each time.
 * The program will run until q in input.
 */

import java.util.Scanner;
import java.util.Stack;

public class Calc {

    /* Prompts the user for an infix expression
     * and then converts it to a postfix expression.
     *
     * Also prompts the user for a value of x (if one is entered in the infix expression)
     * and will keep doing so until the user exits with q
     */
    public static void main(String[] args) {
        System.out.println("Nishant Sinha\n");

        Scanner keyboard = new Scanner(System.in);
        Character x = 1; // Represents the value x that the postfix expression is evaluated at.
        boolean containsVariable = false; // Whether the infix expression contains a variable (x) or not.

        System.out.print("Enter an infix expression: ");
        String infix = keyboard.nextLine().replaceAll(" ", "");

        // Verify the infix expression is valid
        try {
            containsVariable = verifyInfixExpression(infix);
        } catch (IllegalArgumentException e) {
            System.out.println("Error in expression: " + e);
            System.exit(1);
        }

        // Print out the converted postfix expression
        String postfix = infixToPostfix(infix);
        System.out.println("Postfix expression: " + postfix);

        if (containsVariable) {
            while (true) {
                System.out.print("Enter a value for x: ");
                x = keyboard.next().charAt(0);
                if (x == 'q')
                    break;
                System.out.println(evaluatePostfixExpression(postfix, x));
            }
        } else {
            System.out.println(evaluatePostfixExpression(postfix));
        }


        keyboard.close();
    }

    /* Parses and verifies an infix expression.
     *
     * @param infix is a String containing an infix expression.
     * @return true if the infix expression contains a variable x.
     * Throws an IllegalArgumentException if the expression has an error.
     */
    private static boolean verifyInfixExpression(String infix) throws IllegalArgumentException {
        boolean containsVariable = false;
        int leftParenthesis = 0;
        int rightParenthesis = 0;
        char prev = '+'; // operator character for initializing prev

        for (char c : infix.toCharArray()) {

            if (c == '.')
                throw new IllegalArgumentException("Cannot accept floating point numbers.");

            if (isOperator(c) && isOperator(prev))
                throw new IllegalArgumentException("The " + c + " operator cannot preceed the " + prev + " operator.");

            if (!isOperator(c) && !isParenthesis(c) && !isOperator(prev) && !isParenthesis(prev))
                throw new IllegalArgumentException("No operator between operands.");

            if (c == '(')
                rightParenthesis++;
            if (c == ')')
                leftParenthesis++;

            if (c == 'x')
                containsVariable = true;

            prev = c;
        }

        if (rightParenthesis != leftParenthesis)
            throw new IllegalArgumentException("Mismatching parenthesis.");

        return containsVariable;
    }

    /* Converts an infix expression to a postfix expression.
     * Creates a stack and for each character c in the String infix:
     *  if c is an operand append it to the end of infix
     *  if c is ), pop it from the stack until ( and append it to the output.
     *      ** do not append ) to the output
     *  if c is an operator or (:
     *      -- if c has a higher precednece than the top of the stack, push c onto the stack
     *      -- if c does not have a higher precedence than the top of the stack, pop
     *      from the stack (and appending to infix) until it has higher precendence or the stack is empty
     *
     * Repeat until all of infix is pushed to the stack.
     *
     * @param infix is guarenteed to be a correct infix expression.
     * @return a correctly formatted postfix String expression.
     */
    private static String infixToPostfix(String infix) {
        Stack<Character> stack = new Stack<>();
        StringBuffer postfix = new StringBuffer(infix.length());

        for (char c : infix.toCharArray()) {

            if (!isOperator(c) && !isParenthesis(c)) {
                postfix.append(c);
            }

            else if (c == '(') {
                stack.push(c);
            }

            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }

                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }

            else if (isOperator(c) || isParenthesis(c)) {
                if (!stack.isEmpty() && getPrecendence(c) <= getPrecendence(stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }

        return postfix.toString();
    }

    // Helper function for infixToPostfix
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static boolean isParenthesis(char c) {
        return c == '(' || c == ')';
    }

    // Helper function for infixToPostfix
    // Returns precdence of operators
    private static int getPrecendence(char c) {
        switch(c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    /* Evaluate a postfix expression at x.
     * Reads in a postfix String and pushes each character to a stack.
     *
     * @param postfixString is guarenteed to be a correctly formatted postfix expression.
     * @param x is a value that will be used to evaluate x in the postfix expresion.
     * @return the postfix expression evaluated at x.
     */
    private static int evaluatePostfixExpression(String postfixString, Character x) {
        Stack<Integer> stack = new Stack<>();

        for (Character c : postfixString.toCharArray()) {
            if (c == 'x') {
                stack.push(Character.getNumericValue(x));
            } else if (Character.getNumericValue(c) != -1) { // Non numeric characters are -1
                stack.push(Character.getNumericValue(c));
            } else {
                int num2 = stack.pop();
                int num1 = stack.pop();

                switch (c) {
                    case '+':
                        stack.push(num1 + num2);
                        break;
                    case '-':
                        stack.push(num1 - num2);
                        break;
                    case '*':
                        stack.push(num1 * num2);
                        break;
                    case '/':
                        stack.push(num1 / num2);
                        break;
                }
            }
        }

        return stack.pop();
    }

    /* Evaluate a postfix expression when no variable x exists in the expression.
     * Reads in a postfix String and pushes each character to a stack.
     *
     * @param postfixString is guarenteed to be a correctly formatted postfix expression.
     * @return the evaluated postfix expression.
     */
    private static int evaluatePostfixExpression(String postfixString) {
        Stack<Integer> stack = new Stack<>();

        for (Character c : postfixString.toCharArray()) {
            if (Character.getNumericValue(c) != -1) { // Non numeric characters are -1
                stack.push(Character.getNumericValue(c));
            } else {
                int num2 = stack.pop();
                int num1 = stack.pop();

                switch (c) {
                    case '+':
                        stack.push(num1 + num2);
                        break;
                    case '-':
                        stack.push(num1 - num2);
                        break;
                    case '*':
                        stack.push(num1 * num2);
                        break;
                    case '/':
                        stack.push(num1 / num2);
                        break;
                }
            }
        }

        return stack.pop();
    }
}
