
package daoc.age;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class SeleccionRango extends Seleccion {

    @Override
    public List<Individuo> seleccionarPoblacion(int numIndividuos, Poblacion poblacion) {
        List<Individuo> restoPoblacion = new ArrayList<>(numIndividuos);        
        //probabilidad proporcional a la ubicación en la población (rango)
        //Considera TODA la población
        double[] aptitudAcumulada = new double[poblacion.getNumIndividuos()];
        int posicion = numIndividuos;
        aptitudAcumulada[0] = posicion--;
        for (int i = 1; i < numIndividuos; i++) {
            aptitudAcumulada[i] = aptitudAcumulada[i - 1] + posicion--;
        }
        //Rueda de ruleta
        for (int i = 0; i < (numIndividuos); i++) {
            double seleccionado = poblacion.random.nextDouble() * aptitudAcumulada[poblacion.getNumIndividuos() - 1];
            int indice = Arrays.binarySearch(aptitudAcumulada, seleccionado);
            if(indice < 0) {
                indice = Math.abs(indice + 1);
            }
            restoPoblacion.add(poblacion.getIndividuo(indice));
        }
        return restoPoblacion;
    }
    
}
