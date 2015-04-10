
package daoc.age.ejemplos;

import daoc.age.CrucePareja;
import daoc.age.Poblacion;
import daoc.age.SeleccionRuleta;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class NReinas {
    public static int N = 8;//número de reinas por defecto
    /**
     * @param args número de reinas en args[0]
     */
    public static void main(String[] args) {
        if(args.length > 0) {
            N = Integer.parseInt(args[0]);
        }
        
        Poblacion poblacion = new Poblacion()
            .setNumIndividuos(100)
            .setFuncionAptitud(new FuncionAptitudNReinas(N))
            .setMetodoSeleccion(new SeleccionRuleta())
            .setMetodoCruce(new CrucePareja())
            .setMetaAptitud(0)
            .setMaxTiempoCalculo(0, 1, 0)
            .setParametrosIndividuos(N, 1, N)
            .setProbabilidadMutacion(0.02)
            .setTasaElitismo(0.05)
            .setReporteador(new ReporteNReinas());
        
        poblacion.evolucionar();
    }

}
