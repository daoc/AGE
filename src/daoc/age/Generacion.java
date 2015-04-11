
package daoc.age;

import java.util.List;

/**
 *
 * @author dordonez
 */
public interface Generacion {
    
    /**
     * 
     * @param poblacion la población actual
     * @return la población inicial con la que se va a empezar
     */
    public List<Individuo> generarPoblacionInicial(Poblacion poblacion);
    
    /**
     * 
     * @param poblacion la población actual
     * @param individuo el individuo para quien se desea cambiar un atributo
     * @param indiceAtributo el atributo que se desea cambiar
     * @return el nuevo valor para dicho atributo
     */
    public int generarNuevoValorIndividuo(Poblacion poblacion, Individuo individuo, int indiceAtributo);
    
}
