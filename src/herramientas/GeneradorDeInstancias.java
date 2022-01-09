/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package herramientas;

import data.Patron;
import java.util.ArrayList;

/**
 *
 * @author oziel
 *  1. Elegir características
 *  2. Restringir cantidad de instancias por clase
 *  3. Elegir la forma en que se seleccionan las instancias
 */
public class GeneradorDeInstancias {
    //ATRIBUTOS
    Herramientas h = new Herramientas();
    private ArrayList<Patron> instanciasOriginales;
    private ArrayList<Patron> instanciasNuevas;
    private ArrayList<Patron> instanciasUltimo;
    //CONSTRUCTORES
    public GeneradorDeInstancias(ArrayList<Patron> instancias) {
       this.instanciasOriginales = instancias;
       this.instanciasNuevas = new ArrayList();
       this.instanciasUltimo = new ArrayList();
    }
    //MÉTODOS
    
    public void instrucciones(int[] caracteristicas, int porcentaje, String tipo){
        seleccionDeCaracteristicas(caracteristicas);
        extraerInstancias(porcentaje,tipo);
    }
    
    ////////////////// SELECCIONAR CARACTERÍSTICAS /////////////////////////////
    public void seleccionDeCaracteristicas(int[] caracteristicas){
        for(int i=0;i<this.getInstanciasOriginales().size();i++){
            double[] vector = new double[caracteristicas.length];
            for(int j=0;j<caracteristicas.length;j++){
                vector[j] = this.getInstanciasOriginales().get(i).getVector()[caracteristicas[j]];
            }
            
            Patron aux = new Patron();
            aux.setClase(getInstanciasOriginales().get(i).getClase());
            aux.setVector(vector);
            this.getInstanciasNuevas().add(aux);
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    
    
    public void extraerInstancias(int porcentaje, String tipo){
        switch(tipo){
            case "PRIMEROS":
                extraerInstanciasPrimero(porcentaje);
                break;
            case "ULTIMOS":
                extraerInstanciasUltimo(porcentaje);
                break;
            case "RANDOM":
                extraerInstanciasRandom(porcentaje);
                break;
            default: 
                System.out.println("¿QUÉ?");
                break;
        }
    }
    
    public void extraerInstanciasPrimero(int porcentaje){
        ArrayList<String> clases = obtenerClases(instanciasNuevas);
        int[] cantidad_a_extraer = calcularInstanciasAExtraer(porcentaje,clases);
        int[] contador = new int[cantidad_a_extraer.length];
        int i=0;
        do{
            int j;
            for(j=0;j<clases.size();j++){
               if(clases.get(j).equals(this.instanciasNuevas.get(i).getClase())){
                break;
                }
            }
            if(contador[j] != cantidad_a_extraer[j]){
                this.getInstanciasUltimo().add(this.instanciasNuevas.get(i));
                contador[j]++;
            }
            i++;
        }while(!contador.equals(cantidad_a_extraer)  && i<this.instanciasNuevas.size());
    }
    
    public void extraerInstanciasRandom(int porcentaje){
        ArrayList<String> clases = obtenerClases(instanciasNuevas);
        int[] cantidad_a_extraer = calcularInstanciasAExtraer(porcentaje,clases);
        int[] contador = new int[cantidad_a_extraer.length];
        
        int control=0;
        
        do{
        ArrayList<Patron> instanciasAux = new ArrayList();
        for(int i=0;i<this.instanciasNuevas.size();i++){
            if(clases.get(control).equals(this.instanciasNuevas.get(i).getClase())){
                Patron aux = new Patron();
                aux.setClase(this.instanciasNuevas.get(i).getClase());
                aux.setVector(this.instanciasNuevas.get(i).getVector());
                instanciasAux.add(aux);
            }
        }
        ArrayList<Integer> controlDeNumerosRandom = new ArrayList();
        int random;
        do{
            random = (int) (Math.random()*instanciasAux.size());
            if (random == instanciasAux.size()) random = random-1;
            if(!controlDeNumerosRandom.contains(random)){
                this.instanciasUltimo.add(instanciasAux.get(random));
                controlDeNumerosRandom.add(random);
                contador[control]++;
            }
        }while(contador[control] != cantidad_a_extraer[control]);
        control++;
        }while(control < 3);
        
        
    }
    
    public void extraerInstanciasUltimo(int porcentaje){
        ArrayList<String> clases = obtenerClases(instanciasNuevas);
        int[] cantidad_a_extraer = calcularInstanciasAExtraer(porcentaje,clases);
        int[] contador = new int[cantidad_a_extraer.length];
        int i = this.instanciasNuevas.size()-1;
        do{
            int j;
            for(j=0;j<clases.size();j++){
               if(clases.get(j).equals(this.instanciasNuevas.get(i).getClase())){
                break;
                }
            }
            if(contador[j] != cantidad_a_extraer[j]){
                this.getInstanciasUltimo().add(this.instanciasNuevas.get(i));
                contador[j]++;
            }
            i--;
        }while(!contador.equals(cantidad_a_extraer)  && i>0);
        
    }
    
    public int[] calcularInstanciasAExtraer(int porcentaje, ArrayList<String> clases){
        int[] cantidad_por_clase = cantidadDeInstanciasPorClase(instanciasNuevas, clases);
        int[] cantidad_a_extraer_por_clase = new int[clases.size()];
        int aux;
        for(int i=0;i<clases.size();i++){
            aux = cantidad_por_clase[i]*porcentaje/100;
            if(aux==0) aux = 1;
            cantidad_a_extraer_por_clase[i] = aux;
        }
        return cantidad_a_extraer_por_clase;
    }
    
    public int[] cantidadDeInstanciasPorClase(ArrayList<Patron> instancias, ArrayList<String> clases){
        int[] cantidad = new int[clases.size()];
        for(int i=0;i<instancias.size();i++){
            for(int j=0;j<clases.size();j++){
                if(clases.get(j).equals(instancias.get(i).getClase())){
                    cantidad[j]++;
                }
            }
        }
        return cantidad;
    }
    ////////////////////////////////////////////////////////////////////////////
    public ArrayList<String> obtenerClases(ArrayList<Patron> instancias){
        ArrayList<String> clases = new ArrayList();
        clases.add(instancias.get(0).getClase());
        for(int i=1;i<instancias.size();i++){
            if(!clases.contains(instancias.get(i).getClase())){
                clases.add(instancias.get(i).getClase());
            }
        }
        return clases;
    }
    ////////////////////////////////////////////////////////////////////////////
    /**
     * @return the instanciasOriginales
     */
    public ArrayList<Patron> getInstanciasOriginales() {
        return instanciasOriginales;
    }

    /**
     * @param instanciasOriginales the instanciasOriginales to set
     */
    public void setInstanciasOriginales(ArrayList<Patron> instanciasOriginales) {
        this.instanciasOriginales = instanciasOriginales;
    }

    /**
     * @return the instanciasNuevas
     */
    public ArrayList<Patron> getInstanciasNuevas() {
        return instanciasNuevas;
    }

    /**
     * @param instanciasNuevas the instanciasNuevas to set
     */
    public void setInstanciasNuevas(ArrayList<Patron> instanciasNuevas) {
        this.instanciasNuevas = instanciasNuevas;
    }

    /**
     * @return the instanciasUltimo
     */
    public ArrayList<Patron> getInstanciasUltimo() {
        return instanciasUltimo;
    }

    /**
     * @param instanciasUltimo the instanciasUltimo to set
     */
    public void setInstanciasUltimo(ArrayList<Patron> instanciasUltimo) {
        this.instanciasUltimo = instanciasUltimo;
    }
}
