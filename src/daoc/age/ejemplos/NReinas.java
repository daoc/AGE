
package daoc.age.ejemplos;

import daoc.age.Poblacion;

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
            .setAtributos(N, 1, N)
            .setMetaAptitud(0)                
            .setFuncionAptitud(new FuncionAptitudNReinas())
            .setReporteador(new ReporteNReinas());
        
        poblacion.evolucionar();
    }

}
