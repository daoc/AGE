
package daoc.age;

import java.util.ArrayList;
import java.util.List;

/**
 * Genera una población donde los valores de los atributos son un aleatorio
 * entre [minValorAtributo - maxValorAtributo]
 * @author dordonez@ute.edu.ec
 */
public class GeneracionLibre implements Generacion {
    
    /**
     * Genera una población donde los valores de los atributos son un aleatorio
     * entre [[minValorAtributo - maxValorAtributo]
     * @param poblacion permite obtener los parámetros generales de la población
     * @return una nueva lista de individuos
     */
    @Override
    public List<Individuo> generarPoblacionInicial(Poblacion poblacion) {
        List<Individuo> poblacionInicial = new ArrayList<>(poblacion.getNumIndividuos());
        for(int i = 0; i < poblacion.getNumIndividuos(); i++) {
            int[] atributos = new int[poblacion.getNumAtributos()];
            for(int j = 0; j < atributos.length; j++) {
//                int num = poblacion.random.nextInt(1 + poblacion.getMaxValorAtributo() - poblacion.getMinValorAtributo());
//                num += poblacion.getMinValorAtributo();
                atributos[j] = generarNuevoValorIndividuo(poblacion, null, j);
            }           
            poblacionInicial.add(new Individuo(atributos));
        }   
        return poblacionInicial;
    }

    /**
     * Calcula un nuevo valor para el atributo e individuo de la población
     * @param poblacion
     * @param individuo no se utiliza en esta implementación
     * @param indiceAtributo no se utiliza en esta implementación
     * @return entero aleatorio entre [minValorAtributo - maxValorAtributo]
     */
    @Override
    public int generarNuevoValorIndividuo(Poblacion poblacion, Individuo individuo, int indiceAtributo) {
        int num = poblacion.random.nextInt(1 + poblacion.getMaxValorAtributo() - poblacion.getMinValorAtributo());
        num += poblacion.getMinValorAtributo();
        //individuo.getAtributos()[indiceAtributo] = num;
        return num;
    }
    
}
