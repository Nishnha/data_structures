/* Nishant Sinha
 * Bank.java
 * Bhola MW 5:30 - 6:30
 * 11/04/17
 */


/* Bank.java simulates a bank.
 * The bank is a simulation lasting for 2 minutes.
 * In the simulation there are 5 tellers who can take customers
 * and customers will show up and join the queue every 2-6 seconds.
 * Each customer has a predetermined time that they will take with the teller,
 * which is 2-5 seconds.
 *
 * At the start of the simulation each teller will be occupied by a customer.
 * When a teller is available they can take the next customer in the queue.
 *
 * When the simulation ends, the program will print out:
 * 1. The total amount of customers that visit the bank (including the customers that were in the queue at the end of the simulation),
 * 2. The total amount of customers each teller helped.
 * 3. The total amount of time each teller was occupied.
 * 4. The amount of customers that were not helped by a teller.
 */

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/* A bank has 5 tellers.
 * A bank has a queue of customers. A new customer is generated every 2-6 seconds.
 * Each customer has a set amount of time they are assisted by a teller.
 * The queue of customers is implemented via LinkedList.
 */
public class Bank {

    Random rand;
    Teller[] tellers;
    LinkedList<Customer> customerQueue;

    public Bank() {
        rand = new Random();
        tellers = new Teller[] {
            new Teller(new Customer(rand)),
            new Teller(new Customer(rand)),
            new Teller(new Customer(rand)),
            new Teller(new Customer(rand)),
            new Teller(new Customer(rand))
        };
        customerQueue = new LinkedList<Customer>();
    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        boolean runAgain = true;

        do {
            runBankSimulation();

            System.out.println("Would you like to run the simulation again? (y/Y)");
            char response = keyboard.next().charAt(0);
            if (response != 'y' || response != 'Y')
                runAgain = false;

        } while (runAgain);

    }

    public int getTotalCustomers() {
        int totalCustomers = 0;

        for (int i = 0; i < tellers.length; i++) {
            totalCustomers += tellers[i].getTotalCustomers();
        }

        return totalCustomers;
    }

    private static void runBankSimulation() {
        Bank bank = new Bank();
        long startTime = System.currentTimeMillis();
        long createNewCustomerTime = System.currentTimeMillis() + (bank.rand.nextInt(5) + 2) * 1000; // Random time between 2-6 seconds.

        while (System.currentTimeMillis() - startTime < 120000) { // The while loop runs for 2 minutes (120 seconds, 120000 milliseconds).

            // Create a new customer if createNewCustomerTime has passed.
            if (System.currentTimeMillis() > createNewCustomerTime) { // Add a new customer to the queue every 2-6 seconds.
                bank.customerQueue.add(new Customer(bank.rand));
                createNewCustomerTime = System.currentTimeMillis() + (bank.rand.nextInt(5) + 2) * 1000;
            }

            for (int i = 0; i < bank.tellers.length; i++) {
                /* After the customer's durationWithTeller has passed:
                 * Add to the teller's totalDuration
                 * Increment the teller's customersAssisted
                 * Set the currentCustomer to null
                 */
                if (bank.tellers[i].getCurrentCustomer() != null &&
                        bank.tellers[i].getCurrentCustomer().getDurationWithTeller() * 1000 < System.currentTimeMillis() - bank.tellers[i].getPrevTimeCustomerHelped()) {
                    bank.tellers[i].addTotalDuration(bank.tellers[i].getCurrentCustomer());
                    bank.tellers[i].incrementTotalCustomers();
                    bank.tellers[i].setCurrentCustomer(null);
                }

                if (bank.tellers[i].getCurrentCustomer() == null && !bank.customerQueue.isEmpty()) {
                    bank.tellers[i].setCurrentCustomer(bank.customerQueue.pop());
                    bank.tellers[i].setPrevTimeCustomerHelped(System.currentTimeMillis());
                }
            }

            // Thread.sleep to keep the processor thread from maxing out while executing the while loop.
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Print out bank simulation stats
        System.out.println("\nTotal customers who visited the bank: " + (bank.getTotalCustomers() + bank.customerQueue.size()));
        for (int i = 0; i < bank.tellers.length; i++) {
            System.out.print("Teller " + (i + 1) + " assisted " + bank.tellers[i].getTotalCustomers() + " customers ");
            System.out.println("and spent a total time of " + bank.tellers[i].getTotalDuration() + " seconds with customers");
        }
        System.out.println("There were " + bank.customerQueue.size() + " customers who were not assisted by a teller");
    }

}

/* A teller keeps track of the number of customers they assisted, and the total time they assisted customers.
 * In order for a teller to keep track of the total time they've assisted customers,
 * a teller also keeps track of the previous time (UNIX time) that they finished assisting a customer.
 *
 * When a teller gets a new customer to assist, that customer is set as currentCustomer.
 * The customer's totalDuration is then added to the teller's totalDuration;
 */
class Teller {
    private long prevTimeCustomerHelped;
    private int totalDuration;
    private int totalCustomers;
    private Customer currentCustomer;

    public Teller(Customer c) {
        prevTimeCustomerHelped = System.currentTimeMillis();
        totalDuration = 0;
        totalCustomers = 0;
        currentCustomer = c;
    }

     /* Get the previous time (UNIX time) since a customer was assisted by a teller. */
    public long getPrevTimeCustomerHelped() {
        return prevTimeCustomerHelped;
    }

    /* Sets the previous time a customer was assisted by the teller. */
    public void setPrevTimeCustomerHelped(long time) {
        prevTimeCustomerHelped = time;
    }

    /* Adds to the teller the total amount of time it has spent with customers. */
    public void addTotalDuration(Customer c) {
        totalDuration += c.getDurationWithTeller();
    }

    /* Returns the total duration a teller was assiting customers */
    public int getTotalDuration() {
        return totalDuration;
    }

    /* Increments the number of customers the teller has assisted. */
    public void incrementTotalCustomers() {
        totalCustomers++;
    }

    /* Returns the total number of customers a teller assisted */
    public int getTotalCustomers() {
        return totalCustomers;
    }

    /* Get the customer the teller is assisting. */
    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    /* Set the customer the teller is assisting. */
    public void setCurrentCustomer(Customer c) {
        currentCustomer = c;
    }

}


/* A customer can be 'assited' by a teller.
 * While a teller is assisting a customer, the teller cannot take another customer.
 * When the teller is done assisting the customer, the total time spend with that
 * customer is added to the teller's totalDuration.
 */
class Customer {
    private int durationWithTeller;

    public Customer(Random r) {
        durationWithTeller = r.nextInt(4) + 2;
    }

    public int getDurationWithTeller() {
        return durationWithTeller;
    }
}
