/**
 * BookMyStayApp
 *
 * UC2: Basic Room Types & Static Availability
 * Demonstrates abstraction, inheritance, and polymorphism.
 *
 * @author Mahathi
 * @version 1.0
 */

// Abstract class
abstract class Room {
    private String type;
    private int beds;
    private double price;

    public Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    public void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Beds: " + beds);
        System.out.println("Price: ₹" + price);
    }
}

// Single Room
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 1500);
    }
}

// Double Room
class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 2500);
    }
}

// Suite Room
class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 5000);
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("===== Room Availability =====\n");

        // Polymorphism
        Room r1 = new SingleRoom();
        Room r2 = new DoubleRoom();
        Room r3 = new SuiteRoom();

        // Static availability
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        r1.displayDetails();
        System.out.println("Available: " + singleAvailable);
        System.out.println("---------------------------");

        r2.displayDetails();
        System.out.println("Available: " + doubleAvailable);
        System.out.println("---------------------------");

        r3.displayDetails();
        System.out.println("Available: " + suiteAvailable);
    }
}