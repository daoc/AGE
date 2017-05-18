
package daoc.age.network;

import daoc.age.Poblacion;
import daoc.age.Reporte;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import jdk.nashorn.internal.parser.JSONParser;
import distributed.Task;

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
                Poblacion poblacion = tasker.getECS().take().get();
                Task task = poblacion.getTask();
                reporte.setPoblacion(poblacion);
                task.request = false;
                task.response = reporte.reportarFinal();
                //tasker.sendResponse(task);
                
            } catch (Exception ex) {
                Logger.getLogger(Responder.class.getName()).log(Level.SEVERE, null, ex);
                outputTasksResults = false;
            }
        }
        
    }
    
}
