
package daoc.age.network;

import daoc.age.Poblacion;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 *
 * @author diego
 */
public class Tasker {
    private Connection conn;
    private ExecutorService pool;
    private ExecutorCompletionService<Poblacion> ecs;
    private Responder responder;
    
    private int numHilos = 5;//esto debe venir de otro lado
    
    public void initECS() {
        pool = Executors.newFixedThreadPool(numHilos);
        ecs = new ExecutorCompletionService<>(pool);
    }
    
    public void openTaskReception() {
        conn = new Connection(this);
        conn.openTaskReceiver();
    }
    
    public void addTask(String task) {
        //send the task to ECS
        //ecs.submit(task)
        
        //receive result from the ECS
        //send response to Caller
    }
    
    public void sendResponse(String answer) {
        conn.sendResponse(answer);
    }
    
    public ExecutorCompletionService<Poblacion> getECS() {
        return ecs;
    }
    
    public void kill() {
        
    }
}
