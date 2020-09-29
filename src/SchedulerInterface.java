import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Declaring the set of remote methods for the load balancing scheduler.
 */
public interface SchedulerInterface extends Remote{

    /**
     * Forwards the task in the appropriate node. It chooses the node with the lead amount of load.
     * @param taskId the unique id of the task.
     * @param resourceCount the number of resources needed for the specific instance of the task
     * @return a string containing the response of the invoked node.
     * @throws RemoteException covering exceptions that may occur during the execution of a remote method call.
     */
    String forwardTask(String taskId, int resourceCount) throws RemoteException;

}
