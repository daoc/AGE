
package daoc.age.ejemplos;

import daoc.age.CruceInterno;
import daoc.age.CrucePareja;
import daoc.age.Poblacion;
import daoc.age.SeleccionRango;
/**
 * Encuentra una lista de N enteros ordenada de menor a mayor
 * @author dordonez@ute.edu.ec
 */
public class Ordenar {
    public static int N = 10;//cantidad de enteros a ordenar
    /**
     * @param args cantidad de números a ordenar en args[0]
     */
    public static void main(String[] args) {
        if(args.length > 0) {
            N = Integer.parseInt(args[0]);
        }
        
        Poblacion poblacion = new Poblacion()
            .setNumIndividuos(100)
            .setFuncionAptitud(new FuncionAptitudOrdenar(N))
            .setMetodoSeleccion(new SeleccionRango())
            .setMetodoCruce(new CruceInterno(0.5))
            .setMetaAptitud(N)
            .setMaxTiempoCalculo(0, 1, 0)
            .setParametrosIndividuos(N, 1, N)//enteros entre 1 y N
            .setProbabilidadMutacion(0.02)
            .setTasaElitismo(0.05);
        
        poblacion.evolucionar();
    }

}
