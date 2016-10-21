
package daoc.age;

import java.util.ArrayList;
import java.util.List;

/**
 * Las clases que extiendan esta clase proporcionarán el método mediante el cual
 * se seleccionarán los integrantes de la nueva población, a partir de los miembros
 * de la población actual.
 * Esta clase abstracta asegura que la élite se incluya siempre en la nueva población, delegando
 * a la clase hijo la selección del resto de individuos.
 * Selecciona nueva población de igual talla que la población original
 * @author dordonez@ute.edu.ec
 */
public abstract class Seleccion {
    
    /**
     * Selecciona la nueva población a partir de la original
     * @param poblacion poblacion actual u original
     * @return nueva población
     */
    public final List<Individuo> nuevaPoblacion(Poblacion poblacion) {               
        List<Individuo> nuevaPoblacion = new ArrayList<>(poblacion.getNumIndividuos());
        
        //selecciona élite
        nuevaPoblacion.addAll(seleccionarElite(poblacion));

        //selecciona resto de la población
        nuevaPoblacion.addAll(
            seleccionarPoblacion(
                poblacion.getNumIndividuos() - nuevaPoblacion.size(),
                poblacion));
        
        if(nuevaPoblacion.size() > poblacion.getNumIndividuos()) {
            nuevaPoblacion = nuevaPoblacion.subList(0, poblacion.getNumIndividuos());
        }
              
        return nuevaPoblacion;        
    }
    
    /**
     * Selecciona la élite de la población original
     * @param poblacion poblacion actual u original
     * @return lista de población con aptitud más alta,
     *         de talla tallaPoblacion * tasaElitismo
     */
    private List<Individuo> seleccionarElite(Poblacion poblacion) {
        int tallaElite = (int) (poblacion.getNumIndividuos() * poblacion.getTasaElitismo());
        List<Individuo> elitePoblacion = new ArrayList<>(tallaElite);
        for(int indice = 0 ; indice < tallaElite; indice++) {
            elitePoblacion.add(poblacion.getIndividuo(indice));
            //Guarda la élite para que no se pierdan los mejores individuos
            poblacion.getElite().add(poblacion.getIndividuo(indice).clonar());
        }
        return elitePoblacion;
    }
    
    /**
     * Selecciona el resto de la nueva población. La élite ya fue incluida previamente
     * en la nueva población
     * @param poblacion poblacion actual u original
     * @param numIndividuos tallaPoblación - tallaElite
     * @return lista de individuos de talla numIndividuos, a ser incluida en la nueva población
     */
    public abstract List<Individuo> seleccionarPoblacion(int numIndividuos, Poblacion poblacion);
    
}
