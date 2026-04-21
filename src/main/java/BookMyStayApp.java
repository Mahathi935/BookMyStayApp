/**
 * BookMyStayApp
 *
 * UC10: Booking Cancellation & Inventory Rollback
 * Demonstrates safe cancellation using Stack (LIFO) and inventory restoration.
 *
 * @author Mahathi
 * @version 1.0
 */

import java.util.*;

// Reservation class
class Reservation {
    private String reservationId;
    private String roomType;

    public Reservation(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getRoomType() {
        return roomType;
    }
}

// Inventory
class RoomInventory {
    private HashMap<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();
        availability.put("Single Room", 1);
        availability.put("Double Room", 1);
    }

    public void increaseAvailability(String type) {
        availability.put(type, availability.get(type) + 1);
    }

    public void display() {
        System.out.println("Current Inventory: " + availability);
    }
}

// Booking History
class BookingHistory {
    private HashMap<String, Reservation> confirmedBookings;

    public BookingHistory() {
        confirmedBookings = new HashMap<>();
    }

    public void addBooking(Reservation r) {
        confirmedBookings.put(r.getReservationId(), r);
    }

    public Reservation getBooking(String id) {
        return confirmedBookings.get(id);
    }

    public void removeBooking(String id) {
        confirmedBookings.remove(id);
    }
}

// Cancellation Service
class CancellationService {

    private Stack<String> rollbackStack;

    public CancellationService() {
        rollbackStack = new Stack<>();
    }

    public void cancelBooking(String reservationId,
                              BookingHistory history,
                              RoomInventory inventory) {

        // Validate existence
        Reservation r = history.getBooking(reservationId);

        if (r == null) {
            System.out.println("Cancellation Failed: Invalid Reservation ID");
            return;
        }

        // Push to stack (LIFO)
        rollbackStack.push(reservationId);

        // Restore inventory
        inventory.increaseAvailability(r.getRoomType());

        // Remove booking
        history.removeBooking(reservationId);

        System.out.println("Booking Cancelled Successfully: " + reservationId);
    }

    public void displayRollbackStack() {
        System.out.println("Rollback Stack: " + rollbackStack);
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        // Setup
        RoomInventory inventory = new RoomInventory();
        BookingHistory history = new BookingHistory();
        CancellationService cancelService = new CancellationService();

        // Simulate confirmed bookings
        history.addBooking(new Reservation("RES101", "Single Room"));
        history.addBooking(new Reservation("RES102", "Double Room"));

        // Initial state
        inventory.display();

        // Cancel valid booking
        cancelService.cancelBooking("RES101", history, inventory);

        // Attempt invalid cancellation
        cancelService.cancelBooking("RES999", history, inventory);

        // Final state
        inventory.display();
        cancelService.displayRollbackStack();
    }
}