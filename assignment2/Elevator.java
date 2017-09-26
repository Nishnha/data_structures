/* @Author (
 *  name = "Nishant Sinha"
 *  date = "9/7/2017"
 * )
 * 2720 Data Structures, 86473
 * Bhola
 *
 * This program emulates an elevator going up and down a 12 story building.
 * It prints out when what floor it is currently on. What floor it is going to,
 * and then what floor it stops at and then waits for 3 seconds before continuing
 * on to the next floor.
 *
 * The Elevator class generates an ElevatorStruct. The ElevatorStruct has two
 * ArrayLists: up, representing the floors the elevator goes up to, and down,
 * the floors the elevator goes down to. Up is sorted in ascending order and
 * down is sorted in descending order. The elevator also keeps track of its
 * current floor.
 *
 * The Elevator class is the runner class for this assignment. It contains the main
 * method as well as the method that emulates the elevator waiting on each floor.
 */

import java.util.Random;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

public class Elevator {

    public static void main(String[] args) throws Exception {

        boolean repeat = true;

        while (repeat) {
            repeat = false;

            ElevatorStruct elevator = new ElevatorStruct();
            Scanner scan = new Scanner(System.in);

            while (elevator.hasNextFloor()) {
                System.out.println("Starting at floor: " + elevator.getCurrentFloor());
                System.out.println("\t" + elevator.nextFloor());
                System.out.print("Stopping at floor: " + elevator.getCurrentFloor() + " for 3 seconds -> ");
                countdown();
                System.out.println();
                Thread.sleep(2000);
            }

            System.out.println("Do you want to run the elevator again? \"Y or y\"");
            char rerun = scan.next().charAt(0);
            if (rerun == 'Y' || rerun == 'y') repeat = true;
        }
    }

    private static void countdown() throws Exception {
        Thread.sleep(1000);
        System.out.print("3");
        Thread.sleep(1000);
        System.out.print(",2");
        Thread.sleep(1000);
        System.out.println(",1");
    }

}

class ElevatorStruct {

    private ArrayList<Integer> up = new ArrayList<>();
    private ArrayList<Integer> down = new ArrayList<>();
    private int currentFloor;

    public ElevatorStruct() {
        up = addFloors(8, 2, 12);
        Collections.sort(up);

        down = addFloors(5, 1, 11);
        Collections.sort(down, Collections.reverseOrder());

        currentFloor = 1;
    }

    // @Input numfloors is the total number of floors to be generated
    // @Input minFloor is the minimum floor number that can be generated
    // @Input maxFloor is the maximum floor number that can be generated
    // @Returns a new ArrayList of integers of size numFloors
    private ArrayList<Integer> addFloors(int numFloors, int minFloor, int maxFloor) {
        Random rand = new Random();
        ArrayList<Integer> floors = new ArrayList<>();

        for (int i = 0; i < numFloors; i++) {
            int nextFloor;

            do {
                nextFloor = rand.nextInt(maxFloor - minFloor + 1) + minFloor;
            } while (floors.contains(nextFloor));

            floors.add(nextFloor);
        }

        return floors;
    }

    public int getCurrentFloor() {
        return this.currentFloor;
    }

    // Only returns false if both up and down are empty.
    public boolean hasNextFloor() {
        return !(up.isEmpty() && down.isEmpty());
    }

    // Updates current floor and returns a string indicating the current floor.
    public String nextFloor() {
        if (!up.isEmpty()) {
            int nextFloor = up.remove(0);
            currentFloor = nextFloor;
            return "Going up! Now on floor " + currentFloor;
        } else {
            int nextFloor = down.remove(0);
            currentFloor = nextFloor;
            return "Going down! Now on floor " + currentFloor;
        }
    }

}
