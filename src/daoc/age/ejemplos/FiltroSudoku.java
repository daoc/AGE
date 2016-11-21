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
    public List<Individuo> filtrarAtributosPoblacion(Poblacion poblacion) {
        //Filtra filas para que queden valores únicos
        //Solo para facilitar la manipulación de los individuos
        List<Individuo> individuos = poblacion.getPoblacion();
        Set<Integer> indicesBase = IntStream.range(0, 10).boxed().collect(Collectors.toSet());
        //Crea arreglo con los valores únicos entre [min - max]
        Set<Integer> valoresBase = new HashSet<>();
        for(int i = 1; i <= 9; i++) {
            valoresBase.add(i);
        }
        Set<Integer> valoresRestantes = new HashSet<>();
        Set<Integer> valoresEncontrados = new HashSet<>();
        Queue<Integer> indicesDuplicados = new LinkedList<>();
        for(Individuo ind : individuos) {
            for (int f = 0; f < 9; f++) {
                valoresEncontrados.clear();
                valoresRestantes.addAll(valoresBase);
                indicesDuplicados.clear();
                //añadir índices y valores en la restricción en primer lugar
                //dado que estos NO pueden cambiar
                int numRestricciones = addRestricciones(valoresRestantes, valoresEncontrados);
                for (int c = 0; c < 9; c++) {
                    Integer val = getValor(f, c, ind.getAtributos(), 9);
                    if (valoresEncontrados.contains(val)) {
                        indicesDuplicados.add(f*9+c);
                    } else {
                        valoresEncontrados.add(val);
                        valoresRestantes.remove(val);
                    }
                }
                Iterator<Integer> vals = valoresRestantes.iterator();
                Iterator<Integer> idxs = indicesDuplicados.iterator();
                while(idxs.hasNext()) {
                    ind.getAtributos()[idxs.next()] = vals.next();
                }                
            }
        }  
        return individuos;          
    }
    
    private int addRestricciones(Set<Integer> valoresRestantes, Set<Integer> valoresEncontrados) {
        //esto hay que cambiar; se debe pasar como argumento!!!
        Restriccion restriccion = new RestriccionSudoku();
        
        Set val = new HashSet(9);
        int crash = 0;
//        for(int f = 0; f < 9; f++) {
//            val.clear();
//            for(int c = 0 ; c < 9; c++) {
//                if(val.contains(getValor(f, c, atributos, 9))) {
//                    crash++;
//                } else {
//                    val.add(getValor(f, c, atributos, 9));
//                }
//            }
//        }
        return crash;
    }    
    
}
