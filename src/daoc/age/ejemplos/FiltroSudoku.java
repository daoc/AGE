/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoc.age.ejemplos;

import daoc.age.Filtro;
import daoc.age.Individuo;
import daoc.age.Poblacion;
import daoc.age.Restriccion;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author diego
 */
public class FiltroSudoku implements Filtro {

    private int getValor(int fila, int col, int[] unaD, int lado) {
        return unaD[fila * lado + col];
    }    
    
    @Override
    /*Filtra filas para que queden valores únicos*/
    public List<Individuo> filtrarAtributosPoblacion(Poblacion poblacion) {
        //Solo para facilitar la manipulación de los individuos
        List<Individuo> individuos = poblacion.getPoblacion();
        Set<Integer> indicesBase = IntStream.range(0, 81).boxed().collect(Collectors.toSet());
        //Crea arreglo con los valores únicos entre [min - max[
        Set<Integer> valoresBase = IntStream.range(1, 10).boxed().collect(Collectors.toSet());
        Set<Integer> valoresRestantes = new HashSet<>();
        Set<Integer> valoresEncontrados = new HashSet<>();
        Set<Integer> indicesRestringidos = new HashSet<>();
        Set<Integer> indicesLibres = new HashSet<>();
        Queue<Integer> indicesDuplicados = new LinkedList<>();
        for(Individuo ind : individuos) {
            for (int f = 0; f < 9; f++) {
                valoresEncontrados.clear();
                valoresRestantes.addAll(valoresBase);
                indicesDuplicados.clear();
                indicesLibres.clear();
                //añadir índices y valores en la restricción en primer lugar
                //dado que estos NO pueden cambiar
                addRestricciones(f, indicesRestringidos, indicesLibres, valoresRestantes, valoresEncontrados);
                Iterator<Integer> idx_libres = indicesLibres.iterator();
                Iterator<Integer> vals = valoresRestantes.iterator();
                while(idx_libres.hasNext()) {
                    Integer idx = idx_libres.next();
                    Integer val = ind.getAtributos()[idx];
                    if (valoresEncontrados.contains(val)) {
                        ind.getAtributos()[idx] = vals.next();
                    }
                    valoresEncontrados.add(val);
                    //valoresRestantes.remove(val);
                } 
//                for (int c = 0; c < 9; c++) {
//                    Integer val = getValor(f, c, ind.getAtributos(), 9);
//                    if (valoresEncontrados.contains(val)) {
//                        indicesDuplicados.add(f*9+c);
//                    } else {
//                        valoresEncontrados.add(val);
//                        valoresRestantes.remove(val);
//                    }
//                }
//                
//                Iterator<Integer> idxs = indicesDuplicados.iterator();
//                while(idxs.hasNext()) {
//                    ind.getAtributos()[idxs.next()] = vals.next();
//                }                
            }
        }  
        return individuos;          
    }
    
    private void addRestricciones(int fila, Set<Integer> indicesRestringidos, Set<Integer> indicesLibres
                                , Set<Integer> valoresRestantes, Set<Integer> valoresEncontrados) {
        //esto hay que cambiar; se debe pasar como argumento!!!
        Restriccion restriccion = new RestriccionSudoku();
        
        for(int i = fila*9; i < fila*9+9; i++) {
            if(restriccion.hasRestriccion(i)) {
                indicesRestringidos.add(i);
                valoresEncontrados.add(restriccion.getNuevoValorRestringido(i));
                valoresRestantes.remove(restriccion.getNuevoValorRestringido(i));
            } else {
                indicesLibres.add(i);              
            }
        }
    }    
    
}
