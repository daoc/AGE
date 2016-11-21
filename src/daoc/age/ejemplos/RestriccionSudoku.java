/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoc.age.ejemplos;

import daoc.age.Individuo;
import daoc.age.Poblacion;
import daoc.age.Restriccion;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author dordonez
 */
public class RestriccionSudoku implements Restriccion {
    private Map<Integer, Integer> data = new HashMap<>();
    
    public RestriccionSudoku() {
        data.put(1, 5);data.put(2, 2);data.put(5, 6);
        data.put(9, 1);data.put(10, 6);data.put(12, 9);data.put(17, 4);
        data.put(19, 4);data.put(20, 9);data.put(21, 8);data.put(23, 3);data.put(24, 6);data.put(25, 2);
        data.put(27, 4);data.put(33, 8);
        data.put(37, 8);data.put(38, 3);data.put(39, 2);data.put(41, 1);data.put(42, 5);data.put(43, 9);
        data.put(47, 1);data.put(53, 2);
        data.put(55, 9);data.put(56, 7);data.put(57, 3);data.put(59, 5);data.put(60, 2);data.put(61, 4);
        data.put(63, 2);data.put(68, 9);data.put(70, 5);data.put(71, 6);
        data.put(75, 1);data.put(78, 9);data.put(79, 7);
    }
    
    @Override
    public boolean hasRestriccion(int indiceAtributo) {
        return data.containsKey(indiceAtributo);
    }

    @Override
    public int getNuevoValorRestringido(int indiceAtributo) {      
        return data.get(indiceAtributo);
    }
    
    public Set<Integer> getIndicesRestringidos() {
        return data.keySet();
    }
    
    public Collection<Integer> getValoresRestringidos() {
        return data.values();
    }
}
