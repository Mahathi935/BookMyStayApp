/**
 * BookMyStayApp
 *
 * UC7: Add-On Service Selection
 * Demonstrates mapping of services to reservations without affecting core booking logic.
 *
 * @author Mahathi
 * @version 1.0
 */

import java.util.*;

// Add-On Service class
class AddOnService {
    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}

// Add-On Service Manager
class AddOnServiceManager {

    // Map<ReservationID, List of Services>
    private HashMap<String, List<AddOnService>> serviceMap;

    public AddOnServiceManager() {
        serviceMap = new HashMap<>();
    }

    // Add service to reservation
    public void addService(String reservationId, AddOnService service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    // Display services
    public void displayServices(String reservationId) {
        System.out.println("\nServices for Reservation: " + reservationId);

        List<AddOnService> services = serviceMap.get(reservationId);

        if (services == null) {
            System.out.println("No services added.");
            return;
        }

        double total = 0;

        for (AddOnService s : services) {
            System.out.println(s.getServiceName() + " → ₹" + s.getCost());
            total += s.getCost();
        }

        System.out.println("Total Add-On Cost: ₹" + total);
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        // Simulated reservation ID (from UC6)
        String reservationId = "RES123";

        // Add-on manager
        AddOnServiceManager manager = new AddOnServiceManager();

        // Guest selects services
        manager.addService(reservationId, new AddOnService("Breakfast", 500));
        manager.addService(reservationId, new AddOnService("Airport Pickup", 1000));
        manager.addService(reservationId, new AddOnService("Extra Bed", 700));

        // Display services and total cost
        manager.displayServices(reservationId);
    }
}