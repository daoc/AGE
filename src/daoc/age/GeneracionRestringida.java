/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoc.age;

/**
 *
 * @author diego
 */
public class GeneracionRestringida extends GeneracionLibre {
    final Restriccion restriccion;
    
    public GeneracionRestringida(Restriccion restriccion) {
        this.restriccion = restriccion;
    }

    @Override
    public int generarNuevoValorIndividuo(Poblacion poblacion, Individuo individuo, int indiceAtributo) {
        int num;
        if(restriccion.hasRestriccion(indiceAtributo)) {
            num = restriccion.getNuevoValorRestringido(indiceAtributo);
        } else {
            num = poblacion.random.nextInt(1 + poblacion.getMaxValorAtributo() - poblacion.getMinValorAtributo());
            num += poblacion.getMinValorAtributo();
        }
        return num;
    }
    
    
    
}
