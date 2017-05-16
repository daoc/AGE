
package daoc.age.network;

import daoc.age.Poblacion;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


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
    
    ScriptEngineManager engineManager = new ScriptEngineManager();
    ScriptEngine engine = engineManager.getEngineByName("nashorn");
    
    public void initECS() {
        pool = Executors.newFixedThreadPool(numHilos);
        ecs = new ExecutorCompletionService<>(pool);
    }
    
    public void openTaskReception() {
        conn = new Connection(this);
        conn.openTaskReceiver();
        
    }
    
    public void addTask(Task task) {
        try {
            String script = "";
            script += "load('nashorn:mozilla_compat.js'); importPackage(Packages.daoc.age); importPackage(Packages.daoc.age.ejemplos.sudoku); ";
            script += "var fun = function(ecs, pob) { pob";
            script += task.task + ";";
            script += "ecs.submit(pob);}";
            engine.eval(script);
            Invocable invocable = (Invocable) engine;
            invocable.invokeFunction("fun", ecs, new Poblacion().setTask(task));
        } catch (Exception ex) {
            Logger.getLogger(Tasker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendResponse(Task answer) {
        conn.sendResponse(answer);
    }
    
    public ExecutorCompletionService<Poblacion> getECS() {
        return ecs;
    }
    
    public void kill() {
        pool.shutdown();
    }
    
    public void killNow() {
        pool.shutdownNow();
    }
}
