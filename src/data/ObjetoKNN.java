/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

/**
 *
 * @author oziel
 */
public class ObjetoKNN {
    //ATRIBUTOS
    private double distancia;
    private String clase;
    //CONSTRUCTORES
    public ObjetoKNN() {
    }
    public ObjetoKNN(double distancia, String clase) {
        this.distancia = distancia;
        this.clase = clase;
    }
    //MÉTODOS
    public String toString(){
        return "{"+distancia+","+clase+"}";
    }
    /**
     * @return the distancia
     */
    public double getDistancia() {
        return distancia;
    }

    /**
     * @param distancia the distancia to set
     */
    public void setDistancia(double distancia) {
        this.distancia = distancia;
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

    

    
}
