/**
 * BookMyStayApp
 *
 * UC12: Data Persistence & System Recovery
 * Demonstrates saving and restoring system state using serialization.
 *
 * @author Mahathi
 * @version 1.0
 */

import java.io.*;
import java.util.*;

// Reservation (Serializable)
class Reservation implements Serializable {
    private String id;
    private String guestName;

    public Reservation(String id, String guestName) {
        this.id = id;
        this.guestName = guestName;
    }

    public String getId() {
        return id;
    }

    public String getGuestName() {
        return guestName;
    }
}

// Inventory (Serializable)
class RoomInventory implements Serializable {
    private HashMap<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();
        availability.put("Single Room", 2);
        availability.put("Double Room", 1);
    }

    public HashMap<String, Integer> getAvailability() {
        return availability;
    }

    public void display() {
        System.out.println("Inventory: " + availability);
    }
}

// Wrapper for saving full state
class SystemState implements Serializable {
    RoomInventory inventory;
    List<Reservation> bookings;

    public SystemState(RoomInventory inventory, List<Reservation> bookings) {
        this.inventory = inventory;
        this.bookings = bookings;
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "system_state.ser";

    // Save state
    public void save(SystemState state) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(state);
            System.out.println("State saved successfully.");
        } catch (Exception e) {
            System.out.println("Error saving state: " + e.getMessage());
        }
    }

    // Load state
    public SystemState load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            SystemState state = (SystemState) in.readObject();
            System.out.println("State loaded successfully.");
            return state;
        } catch (Exception e) {
            System.out.println("No previous state found. Starting fresh.");
            return null;
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        PersistenceService persistence = new PersistenceService();

        // Try loading previous state
        SystemState state = persistence.load();

        RoomInventory inventory;
        List<Reservation> bookings;

        if (state != null) {
            inventory = state.inventory;
            bookings = state.bookings;
        } else {
            // Fresh start
            inventory = new RoomInventory();
            bookings = new ArrayList<>();

            bookings.add(new Reservation("RES101", "Mahathi"));
            bookings.add(new Reservation("RES102", "Aarav"));
        }

        // Display current state
        inventory.display();
        System.out.println("Bookings:");
        for (Reservation r : bookings) {
            System.out.println(r.getId() + " → " + r.getGuestName());
        }

        // Save state before exit
        persistence.save(new SystemState(inventory, bookings));
    }
}