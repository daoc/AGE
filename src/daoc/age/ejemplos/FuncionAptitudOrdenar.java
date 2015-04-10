
package daoc.age.ejemplos;

import daoc.age.FuncionAptitud;
import daoc.age.Individuo;

/**
 * función de aptitud para el problema de ordenar N enteros
 * @author dordonez@ute.edu.ec
 */
public class FuncionAptitudOrdenar implements FuncionAptitud {
    private final int N;

    public FuncionAptitudOrdenar(int numEnteros) {
        N = numEnteros;
    }

    @Override
    public int aptitudIndividuo(Individuo individuo) {
        int ordenados = 1;//como el primer número no tiene con qué compararse damos un punto por defecto
        int[] atributos = individuo.getAtributos();
        for (int i = 1; i < N; i++) {
            if(atributos[i - 1] < atributos[i]) {
                ordenados++;
            }
        }
        return ordenados;
    }

    @Override
    public boolean isOrdenNatural() {
        return true;
    }
    
}
