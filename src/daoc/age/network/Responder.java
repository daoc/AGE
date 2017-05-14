
package daoc.age.network;

import daoc.age.Poblacion;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class Responder extends Thread {
    private volatile boolean outputTasksResults = true;    
    private Tasker tasker;
    
    public Responder(Tasker tasker) {
        this.tasker = tasker;
    }
    
    @Override
    public void run() {
        while(outputTasksResults) {
            try {
                Poblacion p = tasker.getECS().take().get();
                tasker.sendResponse(p.getMasApto().toString());//esto hay que probar y mejorar
            } catch (Exception ex) {
                Logger.getLogger(Responder.class.getName()).log(Level.SEVERE, null, ex);
                outputTasksResults = false;
            }
        }
        
    }
    
}
