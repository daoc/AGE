
package daoc.age.network;

import daoc.age.Reporte;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class Responder extends Thread {
    private volatile boolean outputTasksResults = true;    
    private Tasker tasker;
    private Reporte reporte;
    
    public Responder(Tasker tasker) {
        this.tasker = tasker;
        reporte = new Reporte();
    }
    
    @Override
    public void run() {
        while(outputTasksResults) {
            try {               
                reporte.setPoblacion(tasker.getECS().take().get());
                tasker.sendResponse(reporte.reportarFinal());
            } catch (Exception ex) {
                Logger.getLogger(Responder.class.getName()).log(Level.SEVERE, null, ex);
                outputTasksResults = false;
            }
        }
        
    }
    
}
