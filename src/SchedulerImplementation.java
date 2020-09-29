import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SchedulerImplementation implements SchedulerInterface {

    private Registry registry;
    private NodeInterface node1;
    private NodeInterface node2;

    /**
     * Scheduler constructor. Initialises the necessary class attributes.
     */
    public SchedulerImplementation() {

        try {
            // Getting the registry
            registry = LocateRegistry.getRegistry(null);
            // Looking up the registry for the remote objects
            node1 = (NodeInterface) registry.lookup("Node1");
            node2 = (NodeInterface) registry.lookup("Node2");
        }
        catch(Exception e) {
            System.out.println("Scheduler exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Method which prints the load values for each node. It is used purely for information purposes.
     */
    private void printInfo() {
        try {
            System.out.println("Load status: N1: " + Integer.toString(node1.queryResources()) + ", N2: " + Integer.toString(node2.queryResources()));
        }
        catch (RemoteException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    /**
     * Forwards the task to the appropriate node. It chooses the node with the lead amount of load.
     * @param taskId the unique id of the task.
     * @param resourceCount the number of resources needed for the specific instance of the task
     * @return a string containing the response of the invoked node.
     * @throws RemoteException covering exceptions that may occur during the execution of a remote method call.
     */
    public String forwardTask(String taskId, int resourceCount) {
        String response = "No node could execute the task.";
        try {
            if (node1.queryResources() <= node2.queryResources()) {
                response = node1.invokeTask(taskId, resourceCount);
            }
            else {
                response = node2.invokeTask(taskId, resourceCount);
            }
            printInfo();
            return response;

        } catch (Exception e) {
            System.out.println("Scheduler exception: " + e.getMessage());
        }
        return response;
    }
}
