
package daoc.age.ejemplos;

import daoc.age.Reporte;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class ReporteEncontrarTexto extends Reporte {
    public static final char PUNTO_MEDIO = 183;
    
    @Override
    public void reportarFinal() {
        super.reportarFinal();
        int[] data = getPoblacion().getMasApto().getAtributos();
        for(int i = 0; i < data.length; i++) {
            System.out.print((char)data[i]);
        }
        System.out.println();
    }

    @Override
    public void reportarIteracion() {
        super.reportarIteracion();
        int[] data = getPoblacion().getMasApto().getAtributos();
        for(int i = 0; i < data.length; i++) {
            System.out.print((char)data[i]);
        }
        System.out.println();        
    }
    
    
    
}
