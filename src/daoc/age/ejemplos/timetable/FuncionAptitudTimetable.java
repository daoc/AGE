
package daoc.age.ejemplos.timetable;

import daoc.age.FuncionAptitud;
import daoc.age.Individuo;

/**
 * función de aptitud para el problema del horario
 * @author dordonez@ute.edu.ec
 */
public class FuncionAptitudTimetable implements FuncionAptitud {

    @Override
    public int aptitudIndividuo(Individuo individuo) {
        int resultado = 0;
        resultado += profUnaClaseHoraDia(individuo, resultado);
        resultado += materiaUnaDia(individuo, resultado);
        return resultado;
    }

    @Override
    public boolean isOrdenNatural() {
        return false;
    }
    
    /**
     * Un profesor solo puede dar una clase por hora y día
     */
    private int profUnaClaseHoraDia(Individuo horario, int resultado) {
        int[] profesor = new int[Timetable.PROFS];
        for (int d = 0; d < Timetable.DIAS; d++) {
            for (int h = 0; h < Timetable.HORAS; h++) {
                for (int a = 0; a < Timetable.AULAS; a++) {
                    Clase c = Timetable.getClase(horario, a, d, h);
                    //las clases con prof=-1 están huecas
                    if (c.getProfesor() >= 0) {
                        //System.out.println("c.getProfesor() " + c.getProfesor());
                        profesor[c.getProfesor()]++;
                    }
                }
                for (int k = 0; k < Timetable.PROFS; k++) {
                    if (profesor[k] > 1) {
                        resultado += (profesor[k] - 1);
                    }
                    profesor[k] = 0;
                }
            }
        }
        return resultado;
    }
    
    /**
     * Solo se puede dar una clase por materia al día
     */
    private int materiaUnaDia(Individuo horario, int resultado) {
        int[] materia = new int[Timetable.MATS];
        for (int d = 0; d < Timetable.DIAS; d++) {
            for (int h = 0; h < Timetable.HORAS; h++) {
                for (int a = 0; a < Timetable.AULAS; a++) {
                    Clase c = Timetable.getClase(horario, a, d, h);
                    //las clases con mat=-1 están huecas
                    if (c.getMateria() >= 0) {
                        //System.out.println("c.getMateria() " + c.getMateria());
                        materia[c.getMateria()]++;
                    }
                }
            }
            for (int m = 0; m < Timetable.MATS; m++) {
                if (materia[m] > 1) {
                    resultado += (materia[m] - 1);
                }
                materia[m] = 0;
            }
        }
        return resultado;
    }    
    
}
