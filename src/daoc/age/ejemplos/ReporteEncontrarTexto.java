
package daoc.age.ejemplos;

import daoc.age.Reporte;
import java.util.Arrays;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class ReporteEncontrarTexto extends Reporte {
    public static final char PUNTO_MEDIO = 183;
    
    @Override
    public String reportarFinal() {
        super.reportarFinal();
        int[] data = getPoblacion().getMasApto().getAtributos();
        for(int i = 0; i < data.length; i++) {
            System.out.print((char)data[i]);
        }
        System.out.println();
        return null;
    }

    @Override
    public void reportarIteracion() {
        System.out.print(
            String.format(
                "g:%d; f:%d :: ", 
                getPoblacion().getNumGeneraciones(), 
                getPoblacion().getMasApto().getAptitud())); 
        int[] data = getPoblacion().getMasApto().getAtributos();
        for(int i = 0; i < data.length; i++) {
            System.out.print((char)data[i]);
        }
        System.out.println();        
    }
    
    
    
}
