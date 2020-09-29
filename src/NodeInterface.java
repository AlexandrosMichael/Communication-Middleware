import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Declaring the set of remote methods for the distributed system's computation nodes.
 */
public interface NodeInterface extends Remote {

    /**
     *
     * Invokes the task described in the parameters.
     * @param taskId the unique id of the task.
     * @param resourceCount the number of resources needed for the specific instance of the task
     * @return a string containing the response of the invoked node.
     * @throws RemoteException covering exceptions that may occur during the execution of a remote method call.
     */
    String invokeTask(String taskId, int resourceCount) throws RemoteException;

    /**
     * Method which returns the current load of the node.
     * @return an integer value of the current load of the node
     * @throws RemoteException covering exceptions that may occur during the execution of a remote method call.
     */
    int queryResources() throws RemoteException;
}
