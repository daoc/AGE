
package daoc.age.ejemplos;

import daoc.age.Reporte;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class ReporteNReinas extends Reporte {
    public static final char PUNTO_MEDIO = 183;
    
    @Override
    public void reportarFinal() {
        super.reportarFinal();
        int[] data = getPoblacion().getMasApto().getAtributos();
        for(int i = 0; i <= getPoblacion().getNumAtributos(); i++) {
            for(int j = 0; j < data.length; j++) {
                if(data[j] == i) {
                    System.out.print("X ");
                } else {
                    System.out.print(PUNTO_MEDIO + " ");
                }
            }
            System.out.println("");
        }
    }
    
}
