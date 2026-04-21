/**
 * BookMyStayApp
 *
 * UC4: Room Search & Availability Check
 * Demonstrates read-only access to inventory with filtering.
 *
 * @author Mahathi
 * @version 1.0
 */

import java.util.HashMap;

// Room domain class
class Room {
    private String type;
    private double price;

    public Room(String type, double price) {
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }
}

// Inventory class (read-only usage here)
class RoomInventory {

    private HashMap<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();
        availability.put("Single Room", 5);
        availability.put("Double Room", 0); // unavailable
        availability.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return availability.getOrDefault(roomType, 0);
    }

    public HashMap<String, Integer> getAllAvailability() {
        return availability;
    }
}

// Search Service (read-only)
class RoomSearchService {

    public void searchAvailableRooms(RoomInventory inventory, Room[] rooms) {

        System.out.println("===== Available Rooms =====");

        for (Room room : rooms) {
            int count = inventory.getAvailability(room.getType());

            // filter unavailable rooms
            if (count > 0) {
                System.out.println("Room Type: " + room.getType());
                System.out.println("Price: ₹" + room.getPrice());
                System.out.println("Available: " + count);
                System.out.println("---------------------------");
            }
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        // Inventory (state holder)
        RoomInventory inventory = new RoomInventory();

        // Room domain objects
        Room[] rooms = {
                new Room("Single Room", 1500),
                new Room("Double Room", 2500),
                new Room("Suite Room", 5000)
        };

        // Search service (read-only)
        RoomSearchService search = new RoomSearchService();

        // Perform search
        search.searchAvailableRooms(inventory, rooms);
    }
}