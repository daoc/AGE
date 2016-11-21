/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoc.age;

import java.util.Collection;
import java.util.Set;

/**
 *
 * @author dordonez
 */
public interface Restriccion {
    public boolean hasRestriccion(int indiceAtributo);
    public int getNuevoValorRestringido(int indiceAtributo);
    public Set<Integer> getIndicesRestringidos();
    public Collection<Integer> getValoresRestringidos();
}
