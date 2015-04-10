
package daoc.age;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class CruceInterno implements Cruce {
    private double probabilidadCruceInterno;
    
    public CruceInterno(double probabilidadCruceInterno) {
        this.probabilidadCruceInterno = probabilidadCruceInterno;
    }
    
    @Override
    public List<Individuo> cruzarPoblacion(Poblacion poblacion) {
        List<Individuo> nuevaPoblacion = new ArrayList<>(poblacion.getNumIndividuos());

        for(int i = 0; i < poblacion.getNumIndividuos(); i++) {
            int[] ind = poblacion.getIndividuo(i).getAtributos().clone();
            for(int j = 0; j < poblacion.getNumAtributos(); j++) {
                if(poblacion.random.nextDouble() <= probabilidadCruceInterno) {
                    int indice2 = poblacion.random.nextInt(poblacion.getNumAtributos());
                    int tmp = ind[j];
                    ind[j] = ind[indice2];
                    ind[indice2] = tmp;
                }
            }
            nuevaPoblacion.add(new Individuo(ind));
        }
        return nuevaPoblacion;
    }
    
}
