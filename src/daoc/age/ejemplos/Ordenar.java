
package daoc.age.ejemplos;

import daoc.age.FiltroAtributosUnicos;
import daoc.age.Poblacion;
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
     * Cantidad de enteros a ordenar y rango máximo de los enteros: [1 - N].
     * El valor por defecto es 6.
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
            .setAtributos(N, 1, N)
            .setMetaAptitud(N)
            .setFiltro(new FiltroAtributosUnicos())
            .setFuncionAptitud(new FuncionAptitudOrdenar(N));

        poblacion.evolucionar();
    }

}
