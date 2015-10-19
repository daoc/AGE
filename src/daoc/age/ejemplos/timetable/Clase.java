
package daoc.age.ejemplos.timetable;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class Clase {
    private final int id;
    private final int materia;
    private final int profesor;

    public Clase(int id, int materia, int profesor) {
        this.id = id;
        this.materia = materia;
        this.profesor = profesor;
    }

    public int getMateria() {
        return materia;
    }

    public int getProfesor() {
        return profesor;
    }

    @Override
    public String toString() {
        String res = "";
        res += String.format("mat:%2d<br/>", getMateria());
        res += String.format("prf:%2d", getProfesor());
        return res;
    }
    
}
