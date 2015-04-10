
package daoc.age;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class CrucePareja implements Cruce {

    @Override
    public List<Individuo> cruzarPoblacion(Poblacion poblacion) {
        List<Individuo> nuevaPoblacion = new ArrayList<>(poblacion.getNumIndividuos());

        int i = 0;
        if(poblacion.getNumIndividuos() % 2 == 1) {
            nuevaPoblacion.add(new Individuo(poblacion.getIndividuo(0).getAtributos().clone()));
            i = 1;
        }
        for(; i < poblacion.getNumIndividuos(); i+=2) {
            int[] x = poblacion.getIndividuo(i).getAtributos().clone();
            int[] y = poblacion.getIndividuo(i+1).getAtributos().clone();
            int puntoCruce = (int) (poblacion.getNumAtributos() * poblacion.random.nextDouble());
            int[] tmp = x.clone();
            for (int j = 0; j < puntoCruce; j++) {
                x[j] = y[j];
                y[j] = tmp[j];
            }
            nuevaPoblacion.add(new Individuo(x));
            nuevaPoblacion.add(new Individuo(y));
        }
        return nuevaPoblacion;
    }
    
}
