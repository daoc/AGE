
package daoc.age.ejemplos;

import daoc.age.CrucePareja;
import daoc.age.GeneracionLibre;
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
            .setNumAtributos(N)
            .setMaxTiempoCalculo(0, 1, 0)
            .setProbabilidadMutacion(0.02)
            .setTasaElitismo(0.05)   
            .setMetaAptitud(0)                
            .setFuncionAptitud(new FuncionAptitudNReinas(N))
            .setMetodoGeneracion(new GeneracionLibre(N))
            .setMetodoSeleccion(new SeleccionRuleta())
            .setMetodoCruce(new CrucePareja())
            .setReporteador(new ReporteNReinas());
        
        poblacion.evolucionar();
    }

}
