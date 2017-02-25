
package daoc.age.ejemplos.sudoku;

import daoc.age.GeneracionRestringida;
import daoc.age.Poblacion;
import static daoc.age.ejemplos.sudoku.Sudoku.N;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        String fileName = "0.txt";
        List<int[]> listaSudokus = leeArchivo(fileName);
        Map<Integer, Integer> sudoku = leeSudoku(listaSudokus, 1);
        
        Poblacion poblacion = new Poblacion()
            .setAtributos(N, 1, 9)
            .setMetaAptitud(0)
            .setMaxTiempoCalculo(0, 2, 0)
            .setNumIndividuos(100000)
            //.setFiltro(new FiltroSudoku())
            .setMetodoGeneracion(new GeneracionRestringida(new RestriccionSudoku(sudoku)))
            .setFuncionAptitud(new FuncionAptitudSudoku())
            .setReporteador(new ReporteSudoku());
        
        poblacion.evolucionar();        
        
        System.out.println(sudoku);
    }

    private static Map<Integer, Integer> leeSudoku(List<int[]> listaSudokus, int idx) {
        Map<Integer, Integer> sudoku = new HashMap<>();
        int[] nums = listaSudokus.get(idx);
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                sudoku.put(i, nums[i] );
            }
        }       
        return sudoku;
    }
    
    private static List<int[]> leeArchivo(String fileName) {
        List<int[]> sudokus = new LinkedList<>();
        try {
            List<String> strings = Files.lines(Paths.get(fileName)).collect(Collectors.toList());            
            for(String str : strings) {
                char[] ch = str.toCharArray();
                int[] num = new int[ch.length];
                for(int i = 0; i < num.length; i++) {
                    num[i] = Character.getNumericValue(ch[i]);
                }
                sudokus.add(num);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sudokus;
    }
    
}
