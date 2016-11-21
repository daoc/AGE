
package daoc.age.ejemplos;

import daoc.age.GeneracionRestringida;
import daoc.age.Poblacion;

/**
 * Resuelve un Sudoku
 * Evaluación: {@link FuncionAptitudSudoku} <br>
 * Generación: {@link GeneracionRestringida} <br>
 * Restricción: {@link RestriccionSudoku} <br>
 * Reporteador: {@link ReporteSudoku} <br>
 * @author dordonez@ute.edu.ec
 */
public class Sudoku {
    /**
     * Número de casilas en el tablero de Sudoku: 9 * 9 = 81
     */
    public static final int N = 81;
    /**
     * @param args Si existe args[0] se toma este valor para {@link #N}
     */
    public static void main(String[] args) {       
        Poblacion poblacion = new Poblacion()
            .setAtributos(N, 1, 9)
            .setMetaAptitud(0)  
            .setNumIndividuos(100000)
            //.setFiltro(new FiltroSudoku())
            .setMetodoGeneracion(new GeneracionRestringida(new RestriccionSudoku()))
            .setFuncionAptitud(new FuncionAptitudSudoku())
            .setReporteador(new ReporteSudoku());
        
        poblacion.evolucionar();
    }

}
