
package daoc.age;

import java.util.List;

/**
 * Las clases que implementen esta interfaz proporcionarán el método de generación
 * de individuos y de los valores de sus atributos, para la población
 * @author dordonez
 */
public interface Generacion {
    
    /**
     * Genera la lista de individuos inicial para empezar el trabajo
     * @param poblacion la población actual (vacía en este punto)
     * @return la población inicial
     */
    public List<Individuo> generarPoblacionInicial(Poblacion poblacion);
    
    /**
     * Proporciona un nuevo valor que puede ser usado para sustituir el valor actual
     * de algún atributo del individuo
     * @param poblacion la población actual
     * @param individuo el individuo para quien se desea cambiar un atributo
     * @param indiceAtributo el atributo que se desea cambiar
     * @return el nuevo valor para dicho atributo
     */
    public int generarNuevoValorIndividuo(Poblacion poblacion, Individuo individuo, int indiceAtributo);
    
}
