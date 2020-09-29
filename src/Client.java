import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.UUID;

/**
 * The class implementing the client functionality of the distributed system
 */
public class Client {


    public static void main(String[] args) {
        try {
            // Getting the registry
            Registry registry = LocateRegistry.getRegistry("localhost");
            // Get the load balancing scheduler
            SchedulerInterface scheduler = (SchedulerInterface) registry.lookup("Scheduler");
            // Scanner is used to read user input
            Scanner scanner = new Scanner(System.in);
            while (true) {
                // Generate unique id for the task
                String uniqueID = UUID.randomUUID().toString();
                System.out.print("Resources required for " + uniqueID +" : ");
                // Get the number of resources required for that task
                int resourceValue = scanner.nextInt();
                // Forward the task to the scheduler
                String response = scheduler.forwardTask(uniqueID, resourceValue);
                // Print the response of the scheduler
                System.out.println(response);
            }
        }
        catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
