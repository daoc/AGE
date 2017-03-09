
package age;

import daoc.age.GeneracionRestringida;
import daoc.age.Poblacion;
import daoc.age.Reporte;
import daoc.age.Seleccion;
import daoc.age.SeleccionRango;
import daoc.age.SeleccionRuleta;
import daoc.age.SeleccionTorneo;
import daoc.age.ejemplos.sudoku.FuncionAptitudSudoku;
import daoc.age.ejemplos.sudoku.RestriccionSudoku;
import daoc.age.ejemplos.sudoku.Sudoku;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Lee sudokus por resolver desde archivos de texto donde:
 * Un sudoku por línea
 * Cada línea tiene 81 números
 * el 0 significa casilla libre (no hay restricción)
 * los números del 1 al 9 significan casilla ocupada (restricción: no se puede cambiar)
 * @author dordonez@ute.edu.ec
 */
public class TestSeleccionSudokus {

    /**
     * @param args Si existe args[0] se toma este valor para {@link #N}
     */
    public static void main(String[] args) {
        final String[] FILENAME = {"0.txt", "1.txt", "2.txt", "3.txt", "5.txt"};//, "p096_sudoku.txt"};
        final int REPETICIONES = 10;
        final int TALLA_MUESTRA = 100;
        
        ExecutorService pool = Executors.newFixedThreadPool(10);
 
        for(String f : FILENAME) {
            final List<Map<Integer, Integer>> listaSudokus = Sudoku.leeArchivoSudokus(f);
            for(int r = 0; r < REPETICIONES; r++) {
                final int[] muestra = new Random().ints(TALLA_MUESTRA, 0, listaSudokus.size()).toArray();
                
                pool.execute(new ThreadTestSudoku(muestra, listaSudokus, new SeleccionRango(), "rep_" + r + "_log_" + f + "_rango.txt"));
                pool.execute(new ThreadTestSudoku(muestra, listaSudokus, new SeleccionRuleta(), "rep_" + r + "_log_" + f + "_ruleta.txt"));
                pool.execute(new ThreadTestSudoku(muestra, listaSudokus, new SeleccionTorneo(0.6), "rep_" + r + "_log_" + f + "_torneo0.6.txt"));

            }
        }
        
        pool.shutdown();
        System.out.println("Enviados trabajos !!!!!");
    }
    
    private static class ThreadTestSudoku extends Thread {
        private final int[] indices;
        private final List<Map<Integer, Integer>> listaSudokus;
        private final Seleccion metodo;
        private final String logFileName;
        
        public ThreadTestSudoku(int[] indices, List<Map<Integer, Integer>> listaSudokus, Seleccion metodo, String logFileName) {
            this.indices = indices;
            this.listaSudokus = listaSudokus;
            this.metodo = metodo;
            this.logFileName = logFileName;
        }

        @Override
        public void run() {
            System.out.println("Inicia Tarea Metodo:" + logFileName);
            try {
                FileWriter log = new FileWriter(logFileName, true);
                for(int i : indices) {
                    log.write("Metodo:" + metodo.getClass().getName() + ";Indice:" + i + ";");
                    getPoblacionBase(i, listaSudokus, metodo, log).evolucionar();
                }
                log.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            } 
            System.out.println("Finaliza Tarea Metodo:" + logFileName);
        }
    }
    
    private static Poblacion getPoblacionBase(int indice, List<Map<Integer, Integer>> listaSudokus, Seleccion metodo, FileWriter log) {
        Poblacion poblacion = new Poblacion()
            .setAtributos(Sudoku.N, 1, 9)
            .setMetaAptitud(0)
            .setMaxTiempoCalculo(0, 1, 0)
            .setNumIndividuos(100000)
            .setMetodoGeneracion(
                new GeneracionRestringida(
                    new RestriccionSudoku(listaSudokus.get(indice))))
            .setFuncionAptitud(new FuncionAptitudSudoku())
            .setMetodoSeleccion(metodo)
            .setReporteador(new ReporteSudokuBatch(log));  
        return poblacion;
    }
    
    public static class ReporteSudokuBatch extends Reporte {
        private final FileWriter log;
        
        public ReporteSudokuBatch(FileWriter log) {
            this.log = log;
        }
        
        @Override
        public void reportarFinal() {
            try {
                log.write(
                    String.format(
                        "Generacion:%d;Tiempo:%s;Aptitud:%d;\n",
                        getPoblacion().getNumGeneraciones(),
                        getPoblacion().getTiempoCalculo() / 1000,         
                        getPoblacion().getMasApto().getAptitud()));
                log.flush();
            } catch (IOException ex) {
                Logger.getLogger(TestSeleccionSudokus.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        
        @Override
        public void reportarIteracion() {}
   
    }
}
