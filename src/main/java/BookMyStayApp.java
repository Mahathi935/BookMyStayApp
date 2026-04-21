/**
 * BookMyStayApp
 *
 * UC5: Booking Request using Queue (FIFO)
 * Demonstrates fair request handling without allocation.
 *
 * @author Mahathi
 * @version 1.0
 */

import java.util.LinkedList;
import java.util.Queue;

// Reservation class (represents a booking request)
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

// Booking Request Queue (FIFO)
class BookingQueue {

    private Queue<Reservation> queue;

    public BookingQueue() {
        queue = new LinkedList<>();
    }

    // Add booking request
    public void addRequest(Reservation reservation) {
        queue.add(reservation);
        System.out.println("Request added: " + reservation.getGuestName()
                + " → " + reservation.getRoomType());
    }

    // Display all requests (without processing)
    public void displayQueue() {
        System.out.println("\n===== Booking Queue =====");

        for (Reservation r : queue) {
            System.out.println(r.getGuestName() + " → " + r.getRoomType());
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        // Initialize queue
        BookingQueue bookingQueue = new BookingQueue();

        // Simulate incoming requests
        bookingQueue.addRequest(new Reservation("Mahathi", "Single Room"));
        bookingQueue.addRequest(new Reservation("Aarav", "Suite Room"));
        bookingQueue.addRequest(new Reservation("Dhruv", "Double Room"));

        // Display queue (FIFO order)
        bookingQueue.displayQueue();
    }
}