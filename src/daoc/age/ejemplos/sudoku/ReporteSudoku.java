
package daoc.age.ejemplos.sudoku;

import daoc.age.Reporte;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class ReporteSudoku extends Reporte {
    
    @Override
    public String reportarFinal() {
        super.reportarFinal();
        int counter = 0;
        int[] data = getPoblacion().getMasApto().getAtributos();
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                System.out.print(data[counter++] + " ");
            }
            System.out.println("");
        }
        return null;
    }
    
}
