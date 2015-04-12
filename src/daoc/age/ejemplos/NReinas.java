
package daoc.age.ejemplos;

import daoc.age.CrucePareja;
import daoc.age.GeneracionLibre;
import daoc.age.Poblacion;
import daoc.age.SeleccionRuleta;

/**
 * Encuentra una solución para el problema de las N reinas.
 * Evaluación: {@link FuncionAptitudNReinas} <br>
 * Generación: {@link GeneracionLibre} <br>
 * Selección: {@link SeleccionRuleta} <br>
 * Cruce: {@link CrucePareja} <br>
 * Reporteador: {@link ReporteNReinas} <br>
 * @author dordonez@ute.edu.ec
 */
public class NReinas {
    /**
     * Número de reinas. El valor por defecto es 8
     */
    public static int N = 8;
    /**
     * @param args Si existe args[0] se toma este valor para {@link #N}
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
