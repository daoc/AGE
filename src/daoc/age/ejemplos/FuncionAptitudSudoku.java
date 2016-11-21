
package daoc.age.ejemplos;

import daoc.age.FuncionAptitud;
import daoc.age.Individuo;
import java.util.HashSet;
import java.util.Set;

/**
 * función de aptitud para un Sudoku
 * @author dordonez@ute.edu.ec
 */
public class FuncionAptitudSudoku implements FuncionAptitud {

    @Override
    public int aptitudIndividuo(Individuo individuo) {
        int colisiones = 0;
        int[] atributos = individuo.getAtributos();
        colisiones += filas(atributos);
        colisiones += columnas(atributos);
        colisiones += bloques(atributos);
        return colisiones;
    }
    
    @Override
    public boolean isOrdenNatural() {
        return false;
    }
    
    private int getValor(int fila, int col, int[] unaD, int lado) {
        return unaD[fila * lado + col];
    }
    
    private int filas(int[] atributos) {
        Set val = new HashSet(9);
        int crash = 0;
        for(int f = 0; f < 9; f++) {
            val.clear();
            for(int c = 0 ; c < 9; c++) {
                if(val.contains(getValor(f, c, atributos, 9))) {
                    crash++;
                } else {
                    val.add(getValor(f, c, atributos, 9));
                }
            }
        }
        return crash;
    }
    
    private int columnas(int[] atributos) {
        Set val = new HashSet(9);
        int crash = 0;
        for(int c = 0; c < 9; c++) {
            val.clear();
            for(int f = 0 ; f < 9; f++) {
                if(val.contains(getValor(f, c, atributos, 9))) {
                    crash++;
                } else {
                    val.add(getValor(f, c, atributos, 9));
                }
            }
        }
        return crash;
    }

    private int bloques(int[] atributos) {
        Set val = new HashSet(9);
        int crash = 0;
        
        for(int bf = 0; bf < 3; bf++) {
            for(int bc = 0 ; bc < 3; bc++) {
                val.clear();
                for(int f = bf*3; f < bf*3+3; f++) {
                    for(int c = bc*3 ; c < bc*3+3; c++) {
                        if(val.contains(getValor(f, c, atributos, 9))) {
                            crash++;
                        } else {
                            val.add(getValor(f, c, atributos, 9));
                        }
                    }
                }
            }
        }
        return crash;
    }    
    

    
}
