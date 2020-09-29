import java.util.HashMap;

/**
 * The implementation class of one of the system's computational nodes
 */
public class Node1 implements NodeInterface {

    // HashMap which holds the tasks and resource requirements of each task.
    private HashMap<String, Integer> tasks;
    // holds the current load of the node
    private int currentLoad;

    /**
     * Class constructor. It assigns the appropriate attributes.
     */
    public Node1() {
        tasks = new HashMap<>();
        currentLoad = 0;
    }


    /**
     *
     * Invokes the task described in the parameters.
     * @param taskId the unique id of the task.
     * @param resourceCount the number of resources needed for the specific instance of the task
     * @return a string containing the response of the invoked node.
     */
    public String invokeTask(String taskId, int resourceCount) {
        tasks.put(taskId, resourceCount);
        currentLoad+= resourceCount;
        String info = "N1: Task " + taskId + " performed using " + resourceCount + " units.";
        System.out.println(info);
        return info;
    }


    /**
     * Method which returns the current load of the node.
     * @return an integer value of the current load of the node
     */
    public int queryResources() {
        return currentLoad;
    }
}
