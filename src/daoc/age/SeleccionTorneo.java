
package daoc.age;

import java.util.ArrayList;
import java.util.List;

/**
 * Para seleccionar a cada nuevo individuo se escoge al azar dos individuos de
 * la población original y se escoge al más apto con una probabilidad de
 * probSeleccionMasApto. Caso contrario se escoge al menos apto.
 * @author dordonez@ute.edu.ec
 */
public class SeleccionTorneo extends Seleccion {
    private double probSeleccionMasApto;
    
    public SeleccionTorneo() {
        this(0.7);
    }
    
    /**
     * 
     * @param probSeleccionMasApto probabilidad de seleccionar al individuo
     * más apto en el torneo. Esta probabilidad debería ser mayor a 0.5 para
     * que tenga sentido
     */
    public SeleccionTorneo(double probSeleccionMasApto) {
        this.probSeleccionMasApto = probSeleccionMasApto;
        if(probSeleccionMasApto <= 0.5) {
            System.err.println("La probabilidad debería ser mayor a 0.5!!!!");
        }
    }
    
    @Override
    public List<Individuo> seleccionarPoblacion(int numIndividuos, Poblacion poblacion) {
        List<Individuo> restoPoblacion = new ArrayList<>(numIndividuos);        
        //torneo
        for (int i = 0; i < (numIndividuos); i++) {
            int ind1 = poblacion.random.nextInt(poblacion.getNumIndividuos());
            int ind2 = poblacion.random.nextInt(poblacion.getNumIndividuos());
            if(poblacion.random.nextDouble() <= probSeleccionMasApto) {//selecciona al más apto
                if(ind1 < ind2) {//el índice 0 es el más apto
                    restoPoblacion.add(poblacion.getIndividuo(ind1));
                } else {
                    restoPoblacion.add(poblacion.getIndividuo(ind2));
                }
            } else {//selecciona al menos apto
                if(ind1 < ind2) {//el índice 0 es el más apto
                    restoPoblacion.add(poblacion.getIndividuo(ind2));
                } else {
                    restoPoblacion.add(poblacion.getIndividuo(ind1));
                }                
            }          
        }
        return restoPoblacion;
    }
    
}
