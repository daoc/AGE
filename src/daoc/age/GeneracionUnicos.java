
package daoc.age;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dordonez
 */
public class GeneracionUnicos implements Generacion {
    
    /**
     * 
     * se generarán valores entre [0 - poblacion.getNumAtributos()[
     * ningún valor de los atributos se puede repetir
     */    
    @Override
    public List<Individuo> generarPoblacionInicial(Poblacion poblacion) {
        List<Individuo> poblacionInicial = new ArrayList<>(poblacion.getNumIndividuos());
        
        int[] valoresBase = new int[poblacion.getNumAtributos()];
        for(int i = 0; i < valoresBase.length; i++) {
            valoresBase[i] = i;
        }  
        
        for(int ind = 0; ind < poblacion.getNumIndividuos(); ind++) {
            //mezcla el arreglo
            int index, temp;
            for (int i = valoresBase.length - 1; i > 0; i--) {
                index = poblacion.random.nextInt(i + 1);
                temp = valoresBase[index];
                valoresBase[index] = valoresBase[i];
                valoresBase[i] = temp;
            }          
            poblacionInicial.add(new Individuo(valoresBase.clone()));
        }   
        return poblacionInicial;
    }

    /**
     * En esta implementación este método debe devolver el mismo número.
     * caso contrario los valores ya no serían únicos
     */
    @Override
    public int generarNuevoValorIndividuo(Poblacion poblacion, Individuo individuo, int indiceAtributo) {
        return individuo.getAtributos()[indiceAtributo];
    }
    
}
