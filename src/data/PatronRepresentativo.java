/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

/**
 *
 * @author oziel
 */
public class PatronRepresentativo extends Patron{
    private int cantidad;
    
    public PatronRepresentativo(String clase, double[] vector) {
        super(clase, vector);
    }

    public PatronRepresentativo(Patron get) {
        super(get.getClase(),get.getVector().clone());
    }
    
    public void Acumular(Patron aux){
        //PROCESO ITERATIVO EN EL QUE ACUMULAMOS LA INFORMACIÓN
        for(int x=0; x<super.getVector().length;x++){
            super.getVector()[x]+= aux.getVector()[x];
        }
        cantidad++;
    }
    public void promediar(){
        for(int x=0; x<super.getVector().length;x++){
            super.getVector()[x]/= cantidad;
        }
    }
}
