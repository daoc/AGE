
package daoc.age;

import java.util.List;

/**
 * Cruza los individuos de la población oiginal para generar
 * una nueva población de igual talla que la original
 * @author dordonez@ute.edu.ec
 */
public interface Cruce {
       
    /**
     * @param poblacion poblacion actual u original
     * @return lista de individuos en la nueva población
     */
    public List<Individuo> cruzarPoblacion(Poblacion poblacion);
    
}
