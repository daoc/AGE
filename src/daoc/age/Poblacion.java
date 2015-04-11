
package daoc.age;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class Poblacion {
    public final Random random;
    private Reporte reporte;
    private Integer numIndividuos;
    private Integer numAtributos;
    private List<Individuo> poblacion;
    private Generacion generacion;
    private FuncionAptitud f;
    private Seleccion seleccion;
    private Cruce cruce;
    private Integer metaAptitud;
    private Integer maxNumGeneraciones;
    private final Long tiempoInicio;
    private Long maxTiempoCalculo;
    private int numGeneraciones;
    private double probMutacion;
    private double tasaElitismo;
    
    public Poblacion() {
        random = new Random();
        setReporteador(new Reporte());
        tiempoInicio = System.currentTimeMillis();
    }
    
    public Poblacion evolucionar() {
        poblacion = new ArrayList<>(numIndividuos);
        generarPoblacion();
        evaluarPoblacion();
        while(!isCondicionFinAlcanzada()) {
            seleccionarPoblacion();
            cruzarIndividuos();
            mutarIndividuos();
            evaluarPoblacion();
        }
        reporte.reportarFinal();
        return this;
    }
    
    private boolean isCondicionFinAlcanzada() {
        numGeneraciones++;
        reporte.reportarIteracion();
        //meta aptitud
        if(metaAptitud != null) {
            if(f.isOrdenNatural()) {
                if(getMasApto().getAptitud() >= metaAptitud) {
                    return true;
                }            
            } else {
                if(getMasApto().getAptitud() <= metaAptitud) {
                    return true;
                }            
            }            
        }
        //número iteraciones
        if(maxNumGeneraciones != null) {
            if(numGeneraciones >= maxNumGeneraciones) return true;
        }
        //tiempo de cálculo
        if(maxTiempoCalculo != null) {
            if(System.currentTimeMillis() >= maxTiempoCalculo) return true;
        }        
        return false;
    }
    
    private void mutarIndividuos() {
        for(int i = 0; i < numIndividuos; i++) {
            for(int j = 0; j < numAtributos; j++) {
                if(random.nextDouble() <= probMutacion) {
                    getIndividuo(i).getAtributos()[j] =
                        generacion.generarNuevoValorIndividuo(this, getIndividuo(i), j);
                }
            }
        }
    }
    
    private void cruzarIndividuos() {
        Collections.shuffle(poblacion);
        poblacion = cruce.cruzarPoblacion(this);
    }
    
    private void seleccionarPoblacion() {    
        poblacion = seleccion.nuevaPoblacion(this);
    }
    
    private void evaluarPoblacion() {
        for(Individuo i : poblacion) {
            i.setAptitud(f.aptitudIndividuo(i));
        }
        Collections.sort(poblacion);
        if(f.isOrdenNatural()) {
            Collections.reverse(poblacion);
        }
    }
    
    private void generarPoblacion() {
        poblacion = generacion.generarPoblacionInicial(this);
    } 
    
    public List<Individuo> getPoblacion() {
        return poblacion;
    }
    
    public Individuo getIndividuo(int indice) {
        return poblacion.get(indice);
    }

    public Integer getNumIndividuos() {
        return numIndividuos;
    }

    public Integer getNumAtributos() {
        return numAtributos;
    }
    
    public int getNumGeneraciones() {
        return numGeneraciones;
    }

    public double getProbMutacion() {
        return probMutacion;
    }

    public double getTasaElitismo() {
        return tasaElitismo;
    }
   
    public Individuo getMasApto() {
        return getIndividuo(0);
    }
    
    public long getTiempoCalculo() {
        return System.currentTimeMillis() - tiempoInicio;
    }
    
    public final Poblacion setReporteador(Reporte reporte) {
        reporte.setPoblacion(this);
        this.reporte = reporte;
        return this;
    }
    
    public Poblacion setTasaElitismo(double tasaElitismo) {
        this.tasaElitismo = tasaElitismo;
        return this;
    }
    
    public Poblacion setNumIndividuos(int numIndividuos) {
        this.numIndividuos = numIndividuos;
        return this;
    }    
    
    public Poblacion setFuncionAptitud(FuncionAptitud f) {
        this.f = f;
        return this;
    }
    
    public FuncionAptitud getFuncionAptitud() {
        return f;
    }    
    
    public Poblacion setMetaAptitud(int metaAptitud) {
        this.metaAptitud = metaAptitud;
        return this;
    }
 
    public Poblacion setMaxNumGeneraciones(int maxNumIteraciones) {
        this.maxNumGeneraciones = maxNumIteraciones;
        return this;
    }    
    
    public Poblacion setMaxTiempoCalculo(int horas, int minutos, int segundos) {
        this.maxTiempoCalculo = tiempoInicio +
            horas * 60 * 60 * 1000 +
            minutos * 60 * 1000 +
            segundos * 1000;
        return this;
    }     
    
    public Poblacion setNumAtributos(int numAtributos) {
        this.numAtributos = numAtributos;
        return this;
    }
    
    public Poblacion setProbabilidadMutacion(double probMutacion) {
        this.probMutacion = probMutacion;
        return this;
    }
    
    public Poblacion setMetodoGeneracion(Generacion generacion) {
        this.generacion = generacion;
        return this;
    }    
    
    public Generacion getMetodoGeneracion() {
        return generacion;
    }
    
    public Poblacion setMetodoSeleccion(Seleccion seleccion) {
        this.seleccion = seleccion;
        return this;
    }    
    
    public Seleccion getMetodoSeleccion() {
        return seleccion;
    }    
    
    public Poblacion setMetodoCruce(Cruce cruce) {
        this.cruce = cruce;
        return this;
    }     
    
    public Cruce getMetodoCruce() {
        return cruce;
    }       
    
}
