/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nosupervisada;

import data.Patron;
import java.util.ArrayList;

/**
 *
 * @author oziel
 *  RECIBE INSTANCIAS
 *  GENERA CLUSTERS (ELIGIENDO CENTROIDES)
 *  CLASIFICA CONJUNTO DE INSTANCIAS A CLUSTERS
 * -PROCESO ITERATIVO
 *  SE RECALCULAN CENTROIDES 
 *  CLASIFICA INSTANCIAS
 *  SE REPITE
 * -(HASTA QUE EL CENTROIDE QUEDA ESTÁTICO)
 */
public class CMeans {
    ArrayList<Patron> instancias;
    ArrayList<Patron> centroides;
    public CMeans() {
        this.instancias = new ArrayList();
        this.centroides = new ArrayList();
    }
    public void clasificar(ArrayList<Patron> instancias, int c){
        this.instancias = instancias;
    }
    public void generarClusters(int c){
        ArrayList<Integer> control = new ArrayList();
        for(int i=0; i<c; i++){
            int random = (int) (Math.random()*this.instancias.size());
            if(!control.contains(random)){
                control.add(random);
                this.instancias.get(random).setClaseResultante("centroide"+i);
                this.centroides.add(this.instancias.get(random));
            }else{
                i--;
            }
        }
    }
    public void sacarDistancias(){
        for(int i=0; i<this.instancias.size();i++){
            
        }
    }
}
