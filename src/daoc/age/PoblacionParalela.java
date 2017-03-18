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
public class PoblacionParalela extends Poblacion {

    @Override
    public Poblacion setMaxIterSinMejoraAntesReset(int maxIterSinMejoraAntesReset) {
        return super.setMaxIterSinMejoraAntesReset(maxIterSinMejoraAntesReset); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Poblacion setMaxVecesReset(int maxVecesReset) {
        return super.setMaxVecesReset(maxVecesReset); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Poblacion setParamReset(int maxVecesReset, int maxIterSinMejoraAntesReset) {
        return super.setParamReset(maxVecesReset, maxIterSinMejoraAntesReset); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Poblacion setMaxTiempoCalculo(int horas, int minutos, int segundos) {
        return super.setMaxTiempoCalculo(horas, minutos, segundos); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Poblacion setMaxNumGeneraciones(int maxNumIteraciones) {
        return super.setMaxNumGeneraciones(maxNumIteraciones); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Poblacion setMetaAptitud(int metaAptitud) {
        return super.setMetaAptitud(metaAptitud); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Poblacion setFiltro(Filtro filtro) {
        return super.setFiltro(filtro); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Poblacion setMetodoCruce(Cruce cruce) {
        return super.setMetodoCruce(cruce); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Poblacion setMetodoSeleccion(Seleccion seleccion) {
        return super.setMetodoSeleccion(seleccion); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Poblacion setMetodoGeneracion(Generacion generacion) {
        return super.setMetodoGeneracion(generacion); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Poblacion setFuncionAptitud(FuncionAptitud f) {
        return super.setFuncionAptitud(f); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Poblacion setTasaElitismo(double tasaElitismo) {
        return super.setTasaElitismo(tasaElitismo); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Poblacion setProbabilidadMutacion(double probMutacion) {
        return super.setProbabilidadMutacion(probMutacion); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Poblacion setMaxValorAtributo(int maxValorAtributo) {
        return super.setMaxValorAtributo(maxValorAtributo); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Poblacion setMinValorAtributo(int minValorAtributo) {
        return super.setMinValorAtributo(minValorAtributo); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Poblacion setNumAtributos(int numAtributos) {
        return super.setNumAtributos(numAtributos); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Poblacion setAtributos(int numAtributos, int valorMinimo, int valorMaximo) {
        return super.setAtributos(numAtributos, valorMinimo, valorMaximo); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Poblacion setNumIndividuos(int numIndividuos) {
        return super.setNumIndividuos(numIndividuos); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Poblacion evolucionar() {
        return super.evolucionar(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
