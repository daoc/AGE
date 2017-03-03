
package age;

import daoc.age.GeneracionRestringida;
import daoc.age.Poblacion;
import daoc.age.Reporte;
import daoc.age.ejemplos.sudoku.FiltroSudoku;
import daoc.age.ejemplos.sudoku.FuncionAptitudSudoku;
import daoc.age.ejemplos.sudoku.RestriccionSudoku;
import daoc.age.ejemplos.sudoku.Sudoku;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Lee sudokus por resolver desde archivos de texto donde:
 * Un sudoku por línea
 * Cada línea tiene 81 números
 * el 0 significa casilla libre (no hay restricción)
 * los números del 1 al 9 significan casilla ocupada (restricción: no se puede cambiar)
 * @author dordonez@ute.edu.ec
 */
public class LeeSudokus {

    /**
     * @param args Si existe args[0] se toma este valor para {@link #N}
     */
    public static void main(String[] args) {
        final String fileName = "3.txt";
        final int tallaMuestra = 100;
        final List<Map<Integer, Integer>> listaSudokus = Sudoku.leeArchivoSudokus(fileName);
        final int numSudokus = listaSudokus.size();
        final Random r = new Random();
        
        for(int i = 0; i < tallaMuestra; i++) {
            final int idx = r.nextInt(numSudokus);
            System.out.print("Indice:" + idx + "; ");
            Poblacion poblacion = new Poblacion()
                .setAtributos(Sudoku.N, 1, 9)
                .setMetaAptitud(0)
                .setMaxTiempoCalculo(0, 2, 0)
                //.setParamReset(5, 20)
                //.setFiltro(new FiltroSudoku())
                .setNumIndividuos(100000)
                .setMetodoGeneracion(
                    new GeneracionRestringida(
                        new RestriccionSudoku(listaSudokus.get(idx))))
                .setFuncionAptitud(new FuncionAptitudSudoku())
                .setReporteador(new ReporteSudokuBatch());

            poblacion.evolucionar();               
        }
    }
    
    
    public static class ReporteSudokuBatch extends Reporte {
        
        @Override
        public void reportarFinal() {
            System.out.println(
                String.format(
                    "Generacion:%d; Tiempo:%s; Aptitud:%d;",
                    getPoblacion().getNumGeneraciones(),
                    getPoblacion().getTiempoCalculo() / 1000,
                    getPoblacion().getMasApto().getAptitud()));         
        }        
        
        @Override
        public void reportarIteracion() {}
   
    }
}
