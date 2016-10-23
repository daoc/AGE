
package daoc.age;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Modifica una población tal que, por cada individuo, sus atributos no tienen valores duplicados:
 * Por cada x, y en individuo.atributos, x != y 
 * Los individuos, sin embargo, tienen los mismos valores de base: [minValorAttr - maxValorAttr] 
 * @author dordonez
 */
public class FiltroAtributosUnicos implements Filtro {

    /**
     * se generarán valores entre [minValorAtributo - maxValorAtributo]
     * ningún valor de los atributos se puede repetir en un individuo
     * Es imprescindible que el tamaño del rango sea mayor o igual que el número de atributos
     */     
    @Override
    public List<Individuo> filtrarAtributosPoblacion(Poblacion poblacion) {
        //Solo para facilitar la manipulación de los individuos
        List<Individuo> individuos = poblacion.getPoblacion();
        //Crea arreglo con los valores únicos entre [min - max]
        Set<Integer> valoresBase = new HashSet<>();
        for(int i = poblacion.getMinValorAtributo(); i <= poblacion.getMaxValorAtributo(); i++) {
            valoresBase.add(i);
        }
        Set<Integer> valoresRestantes = new HashSet<>();
        Set<Integer> valoresEncontrados = new HashSet<>();
        Queue<Integer> indicesDuplicados = new LinkedList<>();
        for(Individuo ind : individuos) {
            valoresEncontrados.clear();
            valoresRestantes.addAll(valoresBase);
            indicesDuplicados.clear();
            for(int i = 0; i < ind.getNumAtributos(); i++) {
                Integer val = ind.getAtributos()[i];
                if(valoresEncontrados.contains(val)) {
                    indicesDuplicados.add(i);
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
        return individuos;        
    }
    
}
