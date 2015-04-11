
package daoc.age.ejemplos;

import daoc.age.CruceInterno;
import daoc.age.CrucePareja;
import daoc.age.GeneracionLibre;
import daoc.age.GeneracionUnicos;
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
