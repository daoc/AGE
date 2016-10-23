
package daoc.age;

import java.util.List;

/**
 * Las clases que implementan esta interfaz proporcionarán mecanismos de
 * filtrado de los valores de los atributos de cada individuo, antes de
 * su evaluación
 * @author dordonez
 */
public interface Filtro {
    
    /**
     * Filtra los valores de los atributos de cada inidividuo en la población
     * antes de su evaluación para encontrar su aptitud
     * @param poblacion la población actual
     * @return la población filtrada
     */
    public List<Individuo> filtrarAtributosPoblacion(Poblacion poblacion);
}
