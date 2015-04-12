
package daoc.age;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Selecciona los miembros de la nueva población con una probabilidad relativa
 * a su posición en la población, ordenada por aptitud.
 * Ej: Para una población de 10 individuos, el más apto tendrá una probabilidad de 10/55,
 * el siguiente de 9/55, ... , el menos apto de 1/55 (55 = (10^2 + 10) / 2)
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
        for (int i = 0; i < numIndividuos; i++) {
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
