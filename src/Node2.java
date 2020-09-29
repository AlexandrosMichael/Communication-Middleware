import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;


/**
 * The implementation class of one of the system's computational nodes
 */
public class Node2 implements NodeInterface {

    // HashMap which holds the tasks and resource requirements of each task.
    private HashMap<String, Integer> tasks;
    // holds the current load of the node
    private int currentLoad;

    /**
     * Class constructor. It assigns the appropriate attributes.
     */
    public Node2() {
        tasks = new HashMap<>();
        currentLoad = 0;
    }

    /**
     * Check if a number passed as a parameter is a prime.
     * Taken from https://mkyong.com/java/how-to-determine-a-prime-number-in-java/
     * @param n for which is to be determined if it's a prime or not.
     * @return true if n is prime and false if not.
     */
    boolean isPrime(int n) {
        if (n == 1) {
            return false;
        }
        //check if n is a multiple of 2
        if (n%2==0) return false;
        //if not, then just check the odds
        for(int i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }


    /**
     * If the number of resources are indeed a prime number, the task will be delegated to the other node.
     * @param taskId
     * @param resourceCount
     * @return
     */
    public String delegateTask(String taskId, int resourceCount) {
        try {
            // Getting the registry
            Registry registry = LocateRegistry.getRegistry("localhost");
            // Get the remote object
            NodeInterface node1 = (NodeInterface) registry.lookup("Node1");
            System.out.println("Task resources required ("+Integer.toString(resourceCount) +") is prime: delegating task to Node1.");
            // The other node's invokeTask method is called
            return node1.invokeTask(taskId, resourceCount);
        }
        catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return "Could not delegate task";
    }


    /**
     * Invokes the task described in the parameters.
     * @param taskId the unique id of the task. If this is prime, then the task will be delegated to the other node.
     * @param resourceCount the number of resources needed for the specific instance of the task
     * @return a string containing the response of the invoked node.
     */
    @SuppressWarnings("Duplicates")
    public String invokeTask(String taskId, int resourceCount) {
        // check if the number of resources needed is prime
        if (!isPrime(resourceCount)) {
            tasks.put(taskId, resourceCount);
            currentLoad += resourceCount;
            String info = "N2: Task " + taskId + " performed using " + resourceCount + " units.";
            System.out.println(info);
            return info;
        }
        else {
            return  delegateTask(taskId, resourceCount);
        }
    }

    /**
     * Method which returns the current load of the node.
     * @return an integer value of the current load of the node
     */
    public int queryResources() {
        return currentLoad;
    }
}
