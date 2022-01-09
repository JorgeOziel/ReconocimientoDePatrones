/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supervisada;

import data.ObjetoKNN;
import data.Patron;
import herramientas.Herramientas;
import java.util.ArrayList;

/**
 *
 * @author oziel
 */
public class KNN {
    //ATRIBUTOS
    Herramientas h;
    private ArrayList<Patron> instanciasEntreno;
    private ArrayList<Patron> instanciasClasificacion;
    private ArrayList<String> clases;
    private int k;
    
    //CONSTRUCTORES
    public KNN(){
        
    }
    public KNN(int k) {
        this.h = new Herramientas();
        this.instanciasEntreno = new ArrayList();
        this.instanciasClasificacion = new ArrayList();
        this.clases = new ArrayList();
        this.k = k;
    }
    
    //FUNCIONES
    //ENTRENAR - CONOCER LAS CLASES
    public void entrenar(ArrayList<Patron> instancias){
        this.instanciasEntreno = instancias;
        for(int i=0; i<this.instanciasEntreno.size();i++){
            if(buscar(this.instanciasEntreno.get(i))==-1){
                this.clases.add(this.instanciasEntreno.get(i).getClase());
            }
        }
    }
    
    //SACAR DISTANCIA CON TODOS LOS PUNTOS Y LLENAR EL ARRAYLIST
    //ORDENAR LAS DISTANCIAS DE MENOR A MAYOR
    //IR SELECCIONANDO LAS DISTANCIAS MENORES HASTA UN NÚMERO K DE VECES
    
    public void clasificar(Patron aux){
        ArrayList<ObjetoKNN> objetosKNN = new ArrayList();
        for(int i=0;i<this.instanciasEntreno.size();i++){
            objetosKNN.add(new ObjetoKNN(h.calcularDistanciaEuclidiana(aux, this.instanciasEntreno.get(i)), this.instanciasEntreno.get(i).getClase()));
        }
        h.ordenarObjetosKNN(objetosKNN);
        //CONTEO HASTA LLEGAR A K VECES
        int[] contador = new int[this.clases.size()];   
        int contadorCiclo=0; //PARA IR RECORRIENDO LOS OBJETOS KNN
        boolean bandera = false;
        do{
            contador[this.clases.indexOf(objetosKNN.get(contadorCiclo).getClase())]++;
            for(int i=0;i<contador.length;i++){
                if(contador[i]==k){
                    bandera=true;
                    aux.setClaseResultante(this.clases.get(i));
                }
            }
            contadorCiclo++;
        }while(bandera != true);
    }
    
    public void clasificar(ArrayList<Patron> aux){
        this.instanciasClasificacion = aux;
        for(int i=0;i<this.instanciasClasificacion.size();i++){
            clasificar(this.instanciasClasificacion.get(i));
        }
    }
    
    public int buscar(Patron aux){
        for(int i=0;i<this.clases.size();i++){
            if(aux.getClase().equals(this.clases.get(i))){
                return i;
            }
        }
        return -1;
    }
}
