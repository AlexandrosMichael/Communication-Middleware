import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * This is the RMI Server implementation. It's job is to create instances of the remote object implementation,
 * export the remote object and then bind that instance to a name in a Java RMI registry.
 */
public class Server {

    public static void main(String[] args) {

        try {
            // get the registry
            Registry registry = LocateRegistry.getRegistry("localhost");

            // create instance of the remote object
            Node1 node1 = new Node1();
            // export the remote object making it available to receive incoming calls on the supplier port
            NodeInterface node1Stub = (NodeInterface) UnicastRemoteObject.exportObject(node1, 0);
            // bind a remote reference to the specified name in this registry
            registry.rebind("Node1", node1Stub);

            Node2 node2 = new Node2();
            NodeInterface node2Stub = (NodeInterface) UnicastRemoteObject.exportObject(node2, 0);
            registry.rebind("Node2", node2Stub);


            SchedulerImplementation scheduler = new SchedulerImplementation();
            SchedulerInterface schedulerStub = (SchedulerInterface) UnicastRemoteObject.exportObject(scheduler, 0);
            registry.rebind("Scheduler", schedulerStub);


            System.out.println("Server ready...");

        } catch (Exception e) {
            System.err.println("Node exception:");
            e.printStackTrace();
        }
    }
}
