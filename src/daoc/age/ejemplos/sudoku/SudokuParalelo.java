
package daoc.age.ejemplos.sudoku;

import daoc.age.GeneracionRestringida;
import daoc.age.PoblacionParalela;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Resuelve un Sudoku
 * Evaluación: {@link FuncionAptitudSudoku} <br>
 * Generación: {@link GeneracionRestringida} <br>
 * Restricción: {@link RestriccionSudoku} <br>
 * Reporteador: {@link ReporteSudoku} <br>
 * @author dordonez@ute.edu.ec
 */
public class SudokuParalelo {
    /**
     * Número de casillas en el tablero de Sudoku: 9 * 9 = 81
     */
    public static final int N = 81;
    /**
     * @param args Si existe args[0] se toma este valor para {@link #N}
     */
    public static void main(String[] args) {
        final String fileName = "p096_sudoku.txt";
        final int idx = 0;
        
        PoblacionParalela poblacion = new PoblacionParalela(5)
            .setAtributos(N, 1, 9)
            .setMetaAptitud(0)
            .setMaxTiempoCalculo(0, 4, 0)
            .setNumIndividuos(100000)
            .setParamReset(10, 25)
            //.setFiltro(new FiltroSudoku())
            .setMetodoGeneracion(
                new GeneracionRestringida(
                    new RestriccionSudoku(leeArchivoSudokus(fileName).get(idx))))
            .setFuncionAptitud(new FuncionAptitudSudoku())
            .setReporteador(new ReporteSudoku());
        
        poblacion.evolucionar();
    }
    
    /**
     * Lee sudokus por resolver desde archivos de texto donde:
     * Un sudoku por línea
     * Cada línea tiene 81 números
     * el 0 significa casilla libre (no hay restricción)
     * los números del 1 al 9 significan casilla ocupada (restricción: no se puede cambiar)
     * @author dordonez@ute.edu.ec
     */
    public static List<Map<Integer, Integer>> leeArchivoSudokus(String fileName) {
        List<Map<Integer, Integer>> todosLosSudokus = new LinkedList();
        Map<Integer, Integer> sparseSudoku;
        try {
            List<int[]> fullSudokus = 
                Files.lines(Paths.get(fileName))
                    .map(s -> s.chars().map(i -> Character.getNumericValue((char) i)).toArray())
                    .collect(Collectors.toList());  
            for(int[] item : fullSudokus) {
                sparseSudoku = new HashMap<>(); 
                for (int i = 0; i < item.length; i++) {
                    if(item[i] != 0) {
                        sparseSudoku.put(i, item[i]);
                    }
                }
                todosLosSudokus.add(sparseSudoku);
            }
            return todosLosSudokus;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }    
    
}
