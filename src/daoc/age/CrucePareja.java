
package daoc.age;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Cruza los individuos por parejas. Se comparte una porción de los atributos
 * de tamaño aleatorio. Si el individuos es impar, uno de ellos se copiará sin
 * cruzarse.
 * @author dordonez@ute.edu.ec
 */
public class CrucePareja implements Cruce {

    @Override
    public List<Individuo> cruzarPoblacion(Poblacion poblacion) {
        List<Individuo> nuevaPoblacion = new ArrayList<>(poblacion.getNumIndividuos());

        //Baraja la población para asegurar que las parejas se escojan aleatoriamente
        //NOTA: Es responsabilidad del método de selección asegurar que los individuos
        //más aptos aparezcan más veces en la población y tengan mayor probabilidad de
        //emparejarse
        Collections.shuffle(poblacion.getPoblacion());
        
        //Si la población tiene un número impar de individuos se toma uno tal cual
        //(sin cruzarlo)
        int i = 0;
        if(poblacion.getNumIndividuos() % 2 == 1) {
            nuevaPoblacion.add(new Individuo(poblacion.getIndividuo(0).getAtributos().clone()));
            i = 1;
        }
        
        //Cruza las parejas tomando un punto aleatorio entre [0 - numAtributos[
        //e intercambiando las partes: Ej. si puntoCruce = 4
        //aaaabb => ccccbb
        //ccccdd => aaaadd
        for(; i < poblacion.getNumIndividuos(); i+=2) {
            int[] x = poblacion.getIndividuo(i).getAtributos().clone();
            int[] y = poblacion.getIndividuo(i+1).getAtributos().clone();
            int puntoCruce = poblacion.random.nextInt(poblacion.getNumAtributos());
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
