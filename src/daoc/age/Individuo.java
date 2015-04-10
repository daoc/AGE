
package daoc.age;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class Individuo implements Comparable<Individuo> {
    private final int[] atributos;
    private Integer aptitud;
    
    public Individuo(int[] atributos) {
        this.atributos = atributos;
    }
    
    public int[] getAtributos() {
        return atributos;
    }
    
    public int getNumAtributos() {
        return atributos.length;
    }
    
    public Integer getAptitud() {
        return aptitud;
    }
    
    public void setAptitud(Integer aptitud) {
        this.aptitud = aptitud;
    }

    @Override
    public int compareTo(Individuo o) {
        return getAptitud() - o.getAptitud();
    }
}
