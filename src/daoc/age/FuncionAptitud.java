
package daoc.age;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public interface FuncionAptitud {
    
    /**
     * 
     * @param individuo el individuo para el cual se va calcular la aptitud
     * @return aptitud del individuo. Para su interpretación ver isOrdenNatural()
     */
    public int aptitudIndividuo(Individuo individuo);

    /**
     *
     * @return  true: un valor mayor es más apto que uno menor
     *          false: un valor menor es más apto que uno mayor
     */
    public boolean isOrdenNatural();
}
