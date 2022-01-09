/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.util.ArrayList;

/**
 *
 * @author oziel
 */
public class Patron {
    private String clase;
    private String claseResultante;
    private double vector[];
    //CONSTRUCTORES
    public Patron(){
        
    }
    public Patron(String clase, double[] vector) {
        this.clase = clase;
        this.claseResultante = "";
        this.vector = vector.clone();
    }
    //MÉTODOS
    
    @Override
    public String toString(){
        String aux = "{";
        for(int i=0;i<getVector().length;i++){
            aux+= getVector()[i]+",";
        }
        aux+= getClase()+"-"+getClaseResultante()+"}";
        return aux;
    }
    
    /**
     * @return the clase
     */
    public String getClase() {
        return clase;
    }

    /**
     * @param clase the clase to set
     */
    public void setClase(String clase) {
        this.clase = clase;
    }

    /**
     * @return the claseResultante
     */
    public String getClaseResultante() {
        return claseResultante;
    }

    /**
     * @param claseResultante the claseResultante to set
     */
    public void setClaseResultante(String claseResultante) {
        this.claseResultante = claseResultante;
    }

    /**
     * @return the vector
     */
    public double[] getVector() {
        return vector;
    }

    /**
     * @param vector the vector to set
     */
    public void setVector(double[] vector) {
        this.vector = vector;
    }
    
}
