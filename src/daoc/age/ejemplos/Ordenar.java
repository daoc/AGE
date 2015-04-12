
package daoc.age.ejemplos;

import daoc.age.CruceInterno;
import daoc.age.GeneracionUnicos;
import daoc.age.Poblacion;
import daoc.age.SeleccionRango;
/**
 * Encuentra una lista de N enteros entre [0 - N[, ordenados de menor a mayor.
 * Los enteros no se repiten al interior de cada individuo.<br>
 * Ordenamiento: {@link FuncionAptitudOrdenar} <br>
 * Generación: {@link GeneracionUnicos} <br>
 * Selección: {@link SeleccionRango} <br>
 * Cruce: {@link CruceInterno} <br>
 * @author dordonez@ute.edu.ec
 */
public class Ordenar {
    /**
     * Cantidad de enteros a ordenar y rango máximo de los enteros: [0 - N[.
     * El valor por defecto es 10.
     */
    public static int N = 10;
    /**
     * @param args Si existe args[0] se usa este valor para {@link #N}
     */
    public static void main(String[] args) {
        if(args.length > 0) {
            N = Integer.parseInt(args[0]);
        }
        
        Poblacion poblacion = new Poblacion()
            .setNumIndividuos(100)
            .setNumAtributos(N)
            .setMaxTiempoCalculo(0, 1, 0)
            .setProbabilidadMutacion(0.02)
            .setTasaElitismo(0.05)
            .setMetaAptitud(N)
            .setFuncionAptitud(new FuncionAptitudOrdenar(N))
            .setMetodoGeneracion(new GeneracionUnicos())
            .setMetodoSeleccion(new SeleccionRango())
            .setMetodoCruce(new CruceInterno(0.5));
        
        poblacion.evolucionar();
    }

}
