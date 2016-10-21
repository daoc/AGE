
package daoc.age;

import java.util.ArrayList;
import java.util.List;

/**
 * Genera una población donde, por cada individuo, sus atributos no tienen valores duplicados:
 * Por cada x, y en individuo.atributos, x != y 
 * Los individuos, sin embargo, son creados con los mismos valores de base: [0 - N[ 
 * @author dordonez@ute.edu.ec
 */
public class GeneracionUnicos implements Generacion {
    
    /**
     * 
     * se generarán valores entre [minValorAtributo - maxValorAtributo]
     * ningún valor de los atributos se puede repetir en un individuo
     * Es imprescindible que el tamaño del rango sea mayor o igual que el número de atributos
     */    
    @Override
    public List<Individuo> generarPoblacionInicial(Poblacion poblacion) {
        List<Individuo> poblacionInicial = new ArrayList<>(poblacion.getNumIndividuos());
        
        //Crea arreglo con los valores únicos entre [min - max]
        int[] valoresBase = new int[poblacion.getNumAtributos()];
        for(int i = poblacion.getMinValorAtributo(); i <= poblacion.getMaxValorAtributo(); i++) {
            valoresBase[i] = i;
        }  
        
        //Genera los individuos. Cada uno ha sido barajado aleatoriamente
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
     * En esta implementación este método debe devolver el mismo valor,
     * caso contrario los valores ya no serían únicos. Equivale a una 
     * probabilidad de mutación de 0.0
     */
    @Override
    public int generarNuevoValorIndividuo(Poblacion poblacion, Individuo individuo, int indiceAtributo) {
        return individuo.getAtributos()[indiceAtributo];
    }
    
}
