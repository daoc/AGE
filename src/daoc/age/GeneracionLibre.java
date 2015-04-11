
package daoc.age;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dordonez
 */
public class GeneracionLibre implements Generacion {
    private final int maxValorAtributo;
    
    /**
     * 
     * @param maxValorAtributo se generarán valores entre [0 - maxValorAtributo[
     */
    public GeneracionLibre(int maxValorAtributo) {
        this.maxValorAtributo = maxValorAtributo;
    }
    
    @Override
    public List<Individuo> generarPoblacionInicial(Poblacion poblacion) {
        List<Individuo> poblacionInicial = new ArrayList<>(poblacion.getNumIndividuos());
        for(int i = 0; i < poblacion.getNumIndividuos(); i++) {
            int[] atributos = new int[poblacion.getNumAtributos()];
            for(int j = 0; j < atributos.length; j++) {
                int num = poblacion.random.nextInt(maxValorAtributo);
                atributos[j] = num;
            }           
            poblacionInicial.add(new Individuo(atributos));
        }   
        return poblacionInicial;
    }

    /**
     * En esta implementación no es relevante ninguno de los parámetros
     * @param poblacion
     * @param individuo
     * @param indiceAtributo
     * @return entero aleatorio entre [0 - maxValorAtributo[
     */
    @Override
    public int generarNuevoValorIndividuo(Poblacion poblacion, Individuo individuo, int indiceAtributo) {
        return poblacion.random.nextInt(maxValorAtributo);
    }
    
}
