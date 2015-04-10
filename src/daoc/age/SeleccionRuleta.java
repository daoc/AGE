
package daoc.age;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Selecciona nueva población de igual talla que la población original
 * utiliza el método de ruleta con probabilidad proporcional al valor
 * de la aptitud
 * @author dordonez@ute.edu.ec
 */
public class SeleccionRuleta extends Seleccion {

    @Override
    public List<Individuo> seleccionarPoblacion(int numIndividuos, Poblacion poblacion) {
        FuncionAptitud f = poblacion.getFuncionAptitud();
        List<Individuo> restoPoblacion = new ArrayList<>(numIndividuos);
        //Probabilidad proporcional al valor de la aptitud.
        //Considera TODA la población
        double[] aptitudAcumulada = new double[poblacion.getNumIndividuos()];
        for(int i = 1; i < poblacion.getNumIndividuos(); i++) {
            double previo = 0;
            if(i > 0) previo = aptitudAcumulada[i - 1];
            if(f.isOrdenNatural()) {
                aptitudAcumulada[i] = previo + poblacion.getIndividuo(i).getAptitud();
            } else {
                aptitudAcumulada[i] = previo + 1.0 / poblacion.getIndividuo(i).getAptitud();
            }
        }
        //Roulette Wheel
        //Selecciona los individuos que faltan para completar la población
        //luego de haber seleccionado la élite
        for(int i = 0; i < (numIndividuos); i++) {
            double seleccionado = poblacion.random.nextDouble() * aptitudAcumulada[poblacion.getNumIndividuos() - 1];
            int index = Arrays.binarySearch(aptitudAcumulada, seleccionado);
            index = index > 0 ? index : Math.abs(index + 1);
            restoPoblacion.add(poblacion.getIndividuo(index));
        }        
        return restoPoblacion;
    }
}
