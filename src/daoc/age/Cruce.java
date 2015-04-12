
package daoc.age;

import java.util.List;

/**
 * Las clases que implementen esta interfaz proporcionarán un método para
 * cruza los individuos de la población original, generando una nueva población
 * de igual talla que la original
 * @author dordonez@ute.edu.ec
 */
public interface Cruce {
       
    /**
     * @param poblacion poblacion actual u original
     * @return lista de individuos en la nueva población
     */
    public List<Individuo> cruzarPoblacion(Poblacion poblacion);
    
}
