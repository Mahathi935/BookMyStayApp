/**
 * BookMyStayApp
 *
 * UC3: Centralized Room Inventory using HashMap
 * Demonstrates inventory management with a single source of truth.
 *
 * @author Mahathi
 * @version 1.0
 */

import java.util.HashMap;

// Inventory Class
class RoomInventory {

    private HashMap<String, Integer> availability;

    // Constructor
    public RoomInventory() {
        availability = new HashMap<>();

        // Initialize room types
        availability.put("Single Room", 5);
        availability.put("Double Room", 3);
        availability.put("Suite Room", 2);
    }

    // Get availability
    public int getAvailability(String roomType) {
        return availability.getOrDefault(roomType, 0);
    }

    // Update availability
    public void updateAvailability(String roomType, int count) {
        availability.put(roomType, count);
    }

    // Display inventory
    public void displayInventory() {
        System.out.println("===== Room Inventory =====");
        for (String room : availability.keySet()) {
            System.out.println(room + " → " + availability.get(room));
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display inventory
        inventory.displayInventory();

        // Example update
        System.out.println("\nUpdating Single Room availability...\n");
        inventory.updateAvailability("Single Room", 4);

        // Display updated inventory
        inventory.displayInventory();
    }
}