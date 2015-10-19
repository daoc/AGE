
package daoc.age.ejemplos;

import daoc.age.FuncionAptitud;
import daoc.age.Individuo;

/**
 * función de aptitud para el problema de las N reinas
 * @author dordonez@ute.edu.ec
 */
public class FuncionAptitudNReinas implements FuncionAptitud {
    private final int N;

    public FuncionAptitudNReinas(int numReinas) {
        N = numReinas;
    }

    @Override
    public int aptitudIndividuo(Individuo individuo) {
        int colisiones = 0;
        int[] atributos = individuo.getAtributos();
        for (int i = 0; i < N - 1; i++) {
            int reina = atributos[i];
            for (int j = i + 1; j < N; j++) {
                int vecina = atributos[j];
                // horizontal derecha
                if (reina == vecina) {
                    ++colisiones;
                }
                // diagonal derecha arriba
                if (reina == (vecina - (j - i))) {
                    ++colisiones;
                }
                // diagonal derecha abajo
                if (reina == (vecina + (j - i))) {
                    ++colisiones;
                }
            }
        }
        return colisiones;
    }

    @Override
    public boolean isOrdenNatural() {
        return false;
    }
    
}
