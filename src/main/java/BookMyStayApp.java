/**
 * BookMyStayApp
 *
 * UC9: Error Handling & Validation
 * Demonstrates input validation, custom exceptions, and fail-fast design.
 *
 * @author Mahathi
 * @version 1.0
 */

import java.util.*;

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Inventory
class RoomInventory {
    private HashMap<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();
        availability.put("Single Room", 2);
        availability.put("Double Room", 1);
        availability.put("Suite Room", 0);
    }

    public boolean isValidRoomType(String type) {
        return availability.containsKey(type);
    }

    public int getAvailability(String type) {
        return availability.getOrDefault(type, 0);
    }

    public void reduceAvailability(String type) {
        availability.put(type, availability.get(type) - 1);
    }
}

// Validator
class BookingValidator {

    public static void validate(String roomType, RoomInventory inventory)
            throws InvalidBookingException {

        // Validate room type
        if (!inventory.isValidRoomType(roomType)) {
            throw new InvalidBookingException("Invalid Room Type: " + roomType);
        }

        // Validate availability
        if (inventory.getAvailability(roomType) <= 0) {
            throw new InvalidBookingException("No rooms available for: " + roomType);
        }
    }
}

// Booking Service
class BookingService {

    public void bookRoom(String guestName, String roomType, RoomInventory inventory) {

        try {
            // Validate first (fail-fast)
            BookingValidator.validate(roomType, inventory);

            // Proceed with booking
            inventory.reduceAvailability(roomType);

            System.out.println("Booking Successful for " + guestName + " → " + roomType);

        } catch (InvalidBookingException e) {
            // Graceful failure
            System.out.println("Booking Failed: " + e.getMessage());
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingService service = new BookingService();

        // Valid booking
        service.bookRoom("Mahathi", "Single Room", inventory);

        // Invalid room type
        service.bookRoom("Aarav", "Luxury Room", inventory);

        // No availability
        service.bookRoom("Dhruv", "Suite Room", inventory);

        // Valid booking
        service.bookRoom("Gyanashri", "Double Room", inventory);
    }
}