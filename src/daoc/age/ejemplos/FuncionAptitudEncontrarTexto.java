
package daoc.age.ejemplos;

import daoc.age.FuncionAptitud;
import daoc.age.Individuo;

/**
 * función de aptitud para el problema de ordenar N enteros
 * @author dordonez@ute.edu.ec
 */
public class FuncionAptitudEncontrarTexto implements FuncionAptitud {
    private final String TXT;

    public FuncionAptitudEncontrarTexto(String texto) {
        TXT = texto;
    }

    @Override
    public int aptitudIndividuo(Individuo individuo) {
        int ubicados = 0;
        int[] atributos = individuo.getAtributos();
        for (int i = 0; i < atributos.length; i++) {
            if(atributos[i] == (int)TXT.charAt(i)) {
                ubicados++;
            }
        }
        return ubicados;
    }

    @Override
    public boolean isOrdenNatural() {
        return true;
    }
    
}
