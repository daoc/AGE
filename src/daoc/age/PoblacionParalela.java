
package daoc.age;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author diego
 */
public class PoblacionParalela {
    private final int numHilos;
    private final List<Poblacion> lista;
    
    public PoblacionParalela(int numHilos) {
        this.numHilos = numHilos;
        lista = new ArrayList<>();
        for(int i = 0; i < numHilos; i++) {
            lista.add(new Poblacion());
        }
    }
    
    public PoblacionParalela setMaxIterSinMejoraAntesReset(int maxIterSinMejoraAntesReset) {
        lista.stream().forEach(p -> p.setMaxIterSinMejoraAntesReset(maxIterSinMejoraAntesReset));
        return this;
    }

    public PoblacionParalela setMaxVecesReset(int maxVecesReset) {
        lista.stream().forEach(p -> p.setMaxVecesReset(maxVecesReset));
        return this;
    }

    public PoblacionParalela setParamReset(int maxVecesReset, int maxIterSinMejoraAntesReset) {
        lista.stream().forEach(p -> p.setParamReset(maxVecesReset, maxIterSinMejoraAntesReset));
        return this;
    }

    public PoblacionParalela setMaxTiempoCalculo(int horas, int minutos, int segundos) {
        lista.stream().forEach(p -> p.setMaxTiempoCalculo(horas, minutos, segundos));
        return this;
    }

    public PoblacionParalela setMaxNumGeneraciones(int maxNumIteraciones) {
        lista.stream().forEach(p -> p.setMaxNumGeneraciones(maxNumIteraciones));
        return this;
    }

    public PoblacionParalela setMetaAptitud(int metaAptitud) {
        lista.stream().forEach(p -> p.setMetaAptitud(metaAptitud));
        return this;
    }

    public PoblacionParalela setFiltro(Filtro filtro) {
        lista.stream().forEach(p -> p.setFiltro(filtro));
        return this;
    }

    public PoblacionParalela setMetodoCruce(Cruce cruce) {
        lista.stream().forEach(p -> p.setMetodoCruce(cruce));
        return this;
    }

    public PoblacionParalela setMetodoSeleccion(Seleccion seleccion) {
        lista.stream().forEach(p -> p.setMetodoSeleccion(seleccion));
        return this;
    }

    public PoblacionParalela setMetodoGeneracion(Generacion generacion) {
        lista.stream().forEach(p -> p.setMetodoGeneracion(generacion));
        return this;
    }

    public PoblacionParalela setFuncionAptitud(FuncionAptitud f) {
        lista.stream().forEach(p -> p.setFuncionAptitud(f));
        return this;
    }

    public PoblacionParalela setTasaElitismo(double tasaElitismo) {
        lista.stream().forEach(p -> p.setTasaElitismo(tasaElitismo));
        return this;
    }

    public PoblacionParalela setProbabilidadMutacion(double probMutacion) {
        lista.stream().forEach(p -> p.setProbabilidadMutacion(probMutacion));
        return this;
    }

    public PoblacionParalela setMaxValorAtributo(int maxValorAtributo) {
        lista.stream().forEach(p -> p.setMaxValorAtributo(maxValorAtributo));
        return this;
    }

    public PoblacionParalela setMinValorAtributo(int minValorAtributo) {
        lista.stream().forEach(p -> p.setMinValorAtributo(minValorAtributo));
        return this;
    }

    public PoblacionParalela setNumAtributos(int numAtributos) {
        lista.stream().forEach(p -> p.setNumAtributos(numAtributos));
        return this;
    }

    public PoblacionParalela setAtributos(int numAtributos, int valorMinimo, int valorMaximo) {
        lista.stream().forEach(p -> p.setAtributos(numAtributos, valorMinimo, valorMaximo));
        return this;
    }

    public PoblacionParalela setNumIndividuos(int numIndividuos) {
        lista.stream().forEach(p -> p.setNumIndividuos(numIndividuos));
        return this;
    }

    public final PoblacionParalela setReporteador(Reporte reporte) {
        lista.stream().forEach(p -> p.setReporteador(reporte.copiar()));
        return this;
    }     
    
    public PoblacionParalela evolucionar() {
        ExecutorService pool = Executors.newFixedThreadPool(numHilos);
        
        for(int i = 0; i < lista.size(); i++) {
            pool.execute(lista.get(i)::evolucionar);
        }
           
        return this;        
    }
    
}
