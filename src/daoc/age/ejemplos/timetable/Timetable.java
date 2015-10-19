
package daoc.age.ejemplos.timetable;

import daoc.age.CruceInterno;
import daoc.age.GeneracionUnicos;
import daoc.age.Individuo;
import daoc.age.Poblacion;
import daoc.age.SeleccionRuleta;
import java.util.ArrayList;
import java.util.List;

/**
 * Encuentra una solución para el problema de los horarios.
 * @author dordonez@ute.edu.ec
 */
public class Timetable {
    public final static int PROFS = 15;
    public final static int MATS = 45;
    public final static int AULAS = 5;
    public final static int DIAS = 5;
    public final static int HORAS = 4;
    public final static int CLASEHUECA = -1;
    /**
     * Número de periodos posibles: 5 aulas * 5 días * 4 horas = 100 periodos
     */
    public final static int N = AULAS * DIAS * HORAS;
    
    //la lista considera los slots deacuerdo a su posición:
    // {aula {dia {hora}}}
    private static final List<Clase> listaClases = new ArrayList<>(N);
    
    /**
     * @param args No se usa
     */
    public static void main(String[] args) {
        
        creaClases();
        
        Poblacion poblacion = new Poblacion()
            .setNumIndividuos(100)
            .setNumAtributos(N)
            .setMaxTiempoCalculo(0, 1, 0)
            .setProbabilidadMutacion(0.02)
            .setTasaElitismo(0.05)   
            .setMetaAptitud(0)                
            .setFuncionAptitud(new FuncionAptitudTimetable())
            .setMetodoGeneracion(new GeneracionUnicos())
            .setMetodoSeleccion(new SeleccionRuleta())
            .setMetodoCruce(new CruceInterno(0.3))
            .setReporteador(new ReporteTimetable());
        
        poblacion.evolucionar();
    }

    /**
     * Creación de todas las clases que se van a dar considerando que toda materia se da
     * 2 veces (45 * 2 = 90)
     * Estamos escogiendo un poco arbitrariamente qué profesor da qué materia
     */
    private static void creaClases() {
        int materia, profesor;
        for(int i = 0; i < 90; i++) {
            materia = i % MATS;
            profesor = i % PROFS;
            Clase c = new Clase(i, materia, profesor);
            listaClases.add(c);
        }
        for(int i = 90; i < 100; i++) {
            Clase c = new Clase(i, CLASEHUECA, CLASEHUECA);
            listaClases.add(c);
        }        
    }
    
    public static Clase getClase(Individuo horario, int aula, int dia, int hora) {
        int despAula = Timetable.DIAS * Timetable.HORAS;
        int despDia = Timetable.HORAS;
        if(aula >= Timetable.AULAS || dia >= Timetable.DIAS || hora >= Timetable.HORAS) return null;
        if(aula < 0 || dia < 0 || hora < 0) return null;
        int[] atributos = horario.getAtributos();
        int indice = (aula * despAula) + (dia * despDia) + hora;
        Clase c = listaClases.get(atributos[indice]);
        return c; 
    }    
    
}
