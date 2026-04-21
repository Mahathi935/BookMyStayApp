/**
 * BookMyStayApp
 *
 * UC11: Concurrent Booking Simulation (Thread Safety)
 * Demonstrates thread-safe booking using synchronized methods.
 *
 * @author Mahathi
 * @version 1.0
 */

import java.util.*;

// Reservation
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

// Shared Inventory (critical resource)
class RoomInventory {

    private HashMap<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();
        availability.put("Single Room", 2);
    }

    // synchronized = critical section
    public synchronized boolean bookRoom(String roomType, String guestName) {

        int available = availability.getOrDefault(roomType, 0);

        if (available > 0) {
            System.out.println(Thread.currentThread().getName() +
                    " booking for " + guestName);

            // simulate delay (to expose race condition if not synchronized)
            try { Thread.sleep(100); } catch (Exception ignored) {}

            availability.put(roomType, available - 1);

            System.out.println("Booking SUCCESS for " + guestName +
                    " | Remaining: " + (available - 1));
            return true;

        } else {
            System.out.println("Booking FAILED for " + guestName + " (No rooms)");
            return false;
        }
    }
}

// Booking Thread
class BookingThread extends Thread {

    private RoomInventory inventory;
    private Reservation reservation;

    public BookingThread(RoomInventory inventory, Reservation reservation) {
        this.inventory = inventory;
        this.reservation = reservation;
    }

    public void run() {
        inventory.bookRoom(reservation.getRoomType(), reservation.getGuestName());
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        // Simulate concurrent users
        Thread t1 = new BookingThread(inventory, new Reservation("Mahathi", "Single Room"));
        Thread t2 = new BookingThread(inventory, new Reservation("Aarav", "Single Room"));
        Thread t3 = new BookingThread(inventory, new Reservation("Dhruv", "Single Room"));

        // Start threads (simultaneous execution)
        t1.start();
        t2.start();
        t3.start();
    }
}