/**
 * BookMyStayApp
 *
 * UC8: Booking History & Reporting
 * Stores confirmed reservations and generates reports without modifying data.
 *
 * @author Mahathi
 * @version 1.0
 */

import java.util.*;

// Reservation (confirmed booking)
class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

// Booking History (stores data)
class BookingHistory {
    private List<Reservation> history;

    public BookingHistory() {
        history = new ArrayList<>();
    }

    // Add confirmed reservation
    public void addReservation(Reservation r) {
        history.add(r);
    }

    // Get all reservations (read-only usage)
    public List<Reservation> getAllReservations() {
        return history;
    }
}

// Reporting Service (read-only)
class BookingReportService {

    // Display all bookings
    public void displayAllBookings(List<Reservation> reservations) {
        System.out.println("===== Booking History =====");

        for (Reservation r : reservations) {
            System.out.println("ID: " + r.getReservationId());
            System.out.println("Guest: " + r.getGuestName());
            System.out.println("Room: " + r.getRoomType());
            System.out.println("---------------------------");
        }
    }

    // Generate summary report
    public void generateSummary(List<Reservation> reservations) {

        HashMap<String, Integer> countMap = new HashMap<>();

        for (Reservation r : reservations) {
            countMap.put(r.getRoomType(),
                    countMap.getOrDefault(r.getRoomType(), 0) + 1);
        }

        System.out.println("\n===== Booking Summary =====");

        for (String room : countMap.keySet()) {
            System.out.println(room + " → " + countMap.get(room) + " bookings");
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        // Booking history
        BookingHistory history = new BookingHistory();

        // Simulated confirmed bookings
        history.addReservation(new Reservation("RES101", "Mahathi", "Single Room"));
        history.addReservation(new Reservation("RES102", "Aarav", "Suite Room"));
        history.addReservation(new Reservation("RES103", "Dhruv", "Single Room"));

        // Reporting service
        BookingReportService report = new BookingReportService();

        // Display history
        report.displayAllBookings(history.getAllReservations());

        // Generate summary
        report.generateSummary(history.getAllReservations());
    }
}