
package daoc.age;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public class Reporte implements Cloneable {
    private Poblacion poblacion;

    public final void setPoblacion(Poblacion poblacion) {
        this.poblacion = poblacion;
    }
    
    public final Poblacion getPoblacion() {
        return poblacion;
    }
    
    public void reportarIteracion() {
        System.out.println(
            String.format(
                "g:%d; f:%d; %s", 
                poblacion.getNumGeneraciones(), 
                poblacion.getMasApto().getAptitud(), 
                Arrays.toString(poblacion.getMasApto().getAtributos())));        
    }
    
    public void reportarFinal() {
        long tiempo = poblacion.getTiempoCalculo();
        String hms = String.format("h%02d:m%02d:s%02d", 
            TimeUnit.MILLISECONDS.toHours(tiempo),
            TimeUnit.MILLISECONDS.toMinutes(tiempo) % 60,
            TimeUnit.MILLISECONDS.toSeconds(tiempo) % 60);
        System.out.println(
            String.format(
                "Última solución -> Generacion:%d; Tiempo:%s; Aptitud:%d;\n\t %s",
                poblacion.getNumGeneraciones(),
                hms,
                poblacion.getMasApto().getAptitud(), 
                Arrays.toString(poblacion.getMasApto().getAtributos())));         
    }
    
    public Reporte copiar() {
        try {
            return (Reporte) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
