
package daoc.age.ejemplos;

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
public class EncontrarTexto {
    /**
     * Cantidad de enteros a ordenar y rango máximo de los enteros: [1 - N].
     * El valor por defecto es 6.
     */
    public static String TXT = "Este es el texto que quiero encontrar";
    /**
     * @param args Si existe args[0] se usa este valor para {@link #N}
     */
    public static void main(String[] args) {
        if(args.length > 0) {
            TXT = args[0];
        }
        
        Poblacion poblacion = new Poblacion()
            .setAtributos(TXT.length(), 32, 126)
            .setMetaAptitud(TXT.length())
            .setFuncionAptitud(new FuncionAptitudEncontrarTexto(TXT))
            .setReporteador(new ReporteEncontrarTexto());

        poblacion.evolucionar();
    }

}
