/**
 * BookMyStayApp
 *
 * UC6: Reservation Confirmation & Room Allocation
 * Ensures no double booking using Set and synchronized inventory updates.
 *
 * @author Mahathi
 * @version 1.0
 */

import java.util.*;

// Reservation request
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

// Inventory Service
class RoomInventory {
    private HashMap<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();
        availability.put("Single Room", 2);
        availability.put("Double Room", 1);
        availability.put("Suite Room", 1);
    }

    public int getAvailability(String type) {
        return availability.getOrDefault(type, 0);
    }

    public void reduceAvailability(String type) {
        availability.put(type, availability.get(type) - 1);
    }
}

// Booking Service
class BookingService {

    private Queue<Reservation> queue;
    private HashMap<String, Set<String>> allocatedRooms;

    public BookingService() {
        queue = new LinkedList<>();
        allocatedRooms = new HashMap<>();
    }

    // Add request
    public void addRequest(Reservation r) {
        queue.add(r);
    }

    // Process bookings
    public void processBookings(RoomInventory inventory) {

        System.out.println("===== Processing Bookings =====");

        while (!queue.isEmpty()) {

            Reservation r = queue.poll();
            String type = r.getRoomType();

            if (inventory.getAvailability(type) > 0) {

                // Generate unique room ID
                String roomId = type.replace(" ", "") + "_" + UUID.randomUUID().toString().substring(0, 5);

                // Store in set
                allocatedRooms.putIfAbsent(type, new HashSet<>());
                allocatedRooms.get(type).add(roomId);

                // Update inventory
                inventory.reduceAvailability(type);

                System.out.println("Booking Confirmed: " + r.getGuestName());
                System.out.println("Room Type: " + type);
                System.out.println("Room ID: " + roomId);
                System.out.println("---------------------------");

            } else {
                System.out.println("Booking Failed (No Availability): " + r.getGuestName() + " → " + type);
            }
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        // Inventory
        RoomInventory inventory = new RoomInventory();

        // Booking service
        BookingService service = new BookingService();

        // Add requests (FIFO)
        service.addRequest(new Reservation("Mahathi", "Single Room"));
        service.addRequest(new Reservation("Aarav", "Single Room"));
        service.addRequest(new Reservation("Dhruv", "Single Room")); // should fail

        // Process bookings
        service.processBookings(inventory);
    }
}