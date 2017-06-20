
package daoc.age;

import distributed.Task;
import distributed.Distributable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class Poblacion extends Distributable implements Cloneable {
    // General
    public final Random random;
    private List<Individuo> poblacion;
    private List<Individuo> elite;
    private final Long tiempoInicio;
    private int numGeneraciones;
    // Parámetros
    private Integer numIndividuos;
    private Integer numAtributos;
    private Integer minValorAtributo;
    private Integer maxValorAtributo;
    private double probMutacion;
    private double tasaElitismo;
    private FuncionAptitud f;    
    private Generacion generacion;
    private Filtro filtro;
    private Seleccion seleccion;
    private Cruce cruce;
    private Reporte reporte;
    // Finalización
    private Integer metaAptitud;
    private Integer maxNumGeneraciones;
    private Long maxTiempoCalculo;
    // Resetear cálculo y población para salir de mínimo local
    private boolean calcReset;
    private Integer maxVecesReset;
    private Integer cuentaVecesReset;
    private Integer maxIterSinMejoraAntesReset;
    private Integer cuentaIterSinMejora;
    private Integer ultimaAptitud;
    // Información de tarea remota
    //private Task task;
    
    public Poblacion() {
        random = new Random();
        tiempoInicio = System.currentTimeMillis();
        setDefaults();
    }
    
    public Poblacion(Task task) {
        super(task);
        random = new Random();
        tiempoInicio = System.currentTimeMillis();
        setDefaults();        
    }    
    
    //Valores por defecto
    private void setDefaults() {
        setMetodoCruce(new CrucePareja());
        setMetodoGeneracion(new GeneracionLibre());
        setMetodoSeleccion(new SeleccionTorneo());
        setFiltro(null);
        setNumIndividuos(1000);
        setProbabilidadMutacion(0.002);
        setReporteador(new Reporte());
        setTasaElitismo(0.25);
        setMaxTiempoCalculo(0, 1, 0);
        setParamReset(0, 0);
        calcReset = false;
        cuentaVecesReset = 0;
        ultimaAptitud = -1;
        cuentaIterSinMejora = 0;
    }
    
    //Procesamiento
    
    public Poblacion evolucionarParalelo(int numHilos) {
        ExecutorService pool = Executors.newFixedThreadPool(numHilos);
        
        try {
            for(int i = 0; i < numHilos; i++) {
                final Poblacion dos = (Poblacion) this.clone();
                pool.execute(() -> {
                    dos.getReporteador().setPoblacion(dos);
                    dos.evolucionar();
                });
            };
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        return this;
    }
    
    public Poblacion evolucionar() {
        
        poblacion = new ArrayList<>(numIndividuos);
        elite = new ArrayList<>();
        do { 
            generarPoblacion();
            filtrarPoblacion();
            evaluarPoblacion();
            while(!isCondicionFinAlcanzada()) {
                seleccionarPoblacion();
                cruzarIndividuos();
                mutarIndividuos();
                filtrarPoblacion();
                evaluarPoblacion();
            }
        } while(isReset());
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
        //condiciones de reseteo
        calcReset = false;
        if( (maxVecesReset > 0) && (maxVecesReset >= cuentaVecesReset) ) {
            if(getMasApto().getAptitud() == ultimaAptitud) {
                if(maxIterSinMejoraAntesReset <= ++cuentaIterSinMejora) {
                    ++cuentaVecesReset;
                    ultimaAptitud = -1;
                    cuentaIterSinMejora = 0;
                    calcReset = true;
                    System.out.println("RESETEANDO");
                    return true;
                }
            } else {
                ultimaAptitud = getMasApto().getAptitud();
                cuentaIterSinMejora = 0;
            }
        } else if(maxVecesReset < cuentaVecesReset) {
            return true;
        }
        
        // por defecto sigue trabajando
        return false;
    }
    
    private void generarPoblacion() {
        poblacion = generacion.generarPoblacionInicial(this);
    } 
    
    private void evaluarPoblacion() {
        for(Individuo i : poblacion) {
            i.setAptitud(f.aptitudIndividuo(i));
        }
        //Añadimos la élite guardada en la nueva población
        //y vaciamos dicha élite para actualizarla
        poblacion.addAll(elite);
        elite.clear();
        //Ordenamos del más apto(mejor) al menos apto(peor)
        Collections.sort(poblacion);
        if(f.isOrdenNatural()) {
            Collections.reverse(poblacion);
        }
    }    
    
    private void seleccionarPoblacion() {    
        poblacion = seleccion.nuevaPoblacion(this);
    }
    
    private void cruzarIndividuos() {
        poblacion = cruce.cruzarPoblacion(this);
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

    // Parametrización del sistema
    
    public Poblacion setNumIndividuos(int numIndividuos) {
        this.numIndividuos = numIndividuos;
        return this;
    }        
    
    public Poblacion setAtributos(int numAtributos, int valorMinimo, int valorMaximo) {
        this.numAtributos = numAtributos;
        this.minValorAtributo = valorMinimo;
        this.maxValorAtributo = valorMaximo;
        return this;
    }     
    
    public Poblacion setNumAtributos(int numAtributos) {
        this.numAtributos = numAtributos;
        return this;
    }    
        
    public Poblacion setMinValorAtributo(int minValorAtributo) {
        this.minValorAtributo = minValorAtributo;
        return this;
    }      
    
    public Poblacion setMaxValorAtributo(int maxValorAtributo) {
        this.maxValorAtributo = maxValorAtributo;
        return this;
    }     
    
    public Poblacion setProbabilidadMutacion(double probMutacion) {
        this.probMutacion = probMutacion;
        return this;
    }    
    
    public Poblacion setTasaElitismo(double tasaElitismo) {
        this.tasaElitismo = tasaElitismo;
        return this;
    }    
            
    public Poblacion setFuncionAptitud(FuncionAptitud f) {
        this.f = f;
        return this;
    }
             
    public Poblacion setMetodoGeneracion(Generacion generacion) {
        this.generacion = generacion;
        return this;
    }    
        
    public Poblacion setMetodoSeleccion(Seleccion seleccion) {
        this.seleccion = seleccion;
        return this;
    }    
        
    public Poblacion setMetodoCruce(Cruce cruce) {
        this.cruce = cruce;
        return this;
    }     
        

    public Poblacion setFiltro(Filtro filtro) {
        this.filtro = filtro;
        return this;
    }    
    
    public final Poblacion setReporteador(Reporte reporte) {
        reporte.setPoblacion(this);
        this.reporte = reporte;
        return this;
    }    
    
    // Condiciones de finalización
    
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
    
    public Poblacion setParamReset(int maxVecesReset, int maxIterSinMejoraAntesReset) {
        this.maxVecesReset = maxVecesReset;
        this.maxIterSinMejoraAntesReset = maxIterSinMejoraAntesReset;
        return this;
    }
    
    public Poblacion setMaxVecesReset(int maxVecesReset) {
        this.maxVecesReset = maxVecesReset;
        return this;
    }
    
    public Poblacion setMaxIterSinMejoraAntesReset(int maxIterSinMejoraAntesReset) {
        this.maxIterSinMejoraAntesReset = maxIterSinMejoraAntesReset;
        return this;
    }    
    
    // Obtener información de los parámetros
    
    public Integer getNumIndividuos() {
        return numIndividuos;
    }

    public Integer getNumAtributos() {
        return numAtributos;
    }
    
    public Integer getMinValorAtributo() {
        return minValorAtributo;
    }      
    
    public Integer getMaxValorAtributo() {
        return maxValorAtributo;
    }      
         
    public double getProbMutacion() {
        return probMutacion;
    }

    public double getTasaElitismo() {
        return tasaElitismo;
    }

    public FuncionAptitud getFuncionAptitud() {
        return f;
    }    

    public Generacion getMetodoGeneracion() {
        return generacion;
    }

    public Seleccion getMetodoSeleccion() {
        return seleccion;
    }    

    public Cruce getMetodoCruce() {
        return cruce;
    }           
    
    public Filtro getFiltro() {
        return filtro;
    }
    
    public Reporte getReporteador() {
        return reporte;
    }    
    
    public Integer getMaxVecesReset() {
        return maxVecesReset;
    }
    
    public Integer getMaxIterSinMejoraAntesReset() {
        return maxIterSinMejoraAntesReset;
    }    
    
    private boolean isReset() {
        return calcReset;
    }
    
    //Obtener información del entorno o del estado del trabajo
        
    public List<Individuo> getPoblacion() {
        return poblacion;
    }
    
    public List<Individuo> getElite() {
        return elite;
    }    
    
    public Individuo getIndividuo(int indice) {
        return poblacion.get(indice);
    }
       
    public Individuo getMasApto() {
        return getIndividuo(0);
    }
    
    public int getNumGeneraciones() {
        return numGeneraciones;
    }    
    
    public long getTiempoCalculo() {
        return System.currentTimeMillis() - tiempoInicio;
    }

    private void filtrarPoblacion() {
        if(filtro == null) {
            return;
        }
        poblacion = filtro.filtrarAtributosPoblacion(this);
    }

    /*
    public Task getTask() {
        return task;
    }

    public Poblacion setTask(Task task) {
        this.task = task;
        return this;
    }
    */
    
    @Override
    public void run() {
        this.evolucionar();
    }

    @Override
    public Poblacion call() throws Exception {
        return this.evolucionar();
    }

    @Override
    public void configureForCall() {
        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            Invocable invocable = (Invocable) engine;
            String script = "";
            script += "load('nashorn:mozilla_compat.js'); importPackage(Packages.daoc.age); importPackage(Packages.daoc.age.ejemplos.sudoku); ";
            script += "var config = function(pob) { ";
            script += "pob" + getTask().task + ";}";
            engine.eval(script);
            invocable.invokeFunction("config", this);
        } catch (Exception ex) {
            Logger.getLogger(Poblacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
