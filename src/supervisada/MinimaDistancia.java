/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supervisada;

import data.Patron;
import data.PatronRepresentativo;
import herramientas.Herramientas;
import java.util.ArrayList;

/**
 *
 * @author Jorge Oziel Rubio Correa
 * 
 *                        Clasificación supervisada
 * Existen dos pasos principales:
 *  Fase de entrenamiento:
 *      - Obtener los datos (patrones) representativos de las clases
 *      - Generar un conjunto de patrones representativos de cada una de las clases
 *      - (En este caso utilizaremos la media)
 *  Fase de clasificación
 * - Recibir el o el conjunto de patrones "desconocidos" a clasificar
 * - Utilizando el patrón P se calcula la distancia con respecto a losp atrones
 * representativos de cada una de las clases, la distancia más corta define la clase
 * a la que pertenece el patrón P "desconocido"
 */     
public class MinimaDistancia {
    private ArrayList<PatronRepresentativo> representativos;
    private Herramientas h = new Herramientas();
    //CONSTRUCTORES
    public MinimaDistancia() {
        this.representativos = new ArrayList<>();
    }
    
    public void entrenar(ArrayList<Patron> instancias){
        //GENERAR LOS REPRESENTATIVOS
        //AGREGAMOS EL PRIMER REPRESENTATIVO
        getRepresentativos().add(new PatronRepresentativo(instancias.get(0)));
        //RECORRER TODAS LAS INSTANCIAS
        for(int x=1;x<instancias.size();x++){
            Patron aux = instancias.get(x);
            //VERIFICAMOS LA EXISTENCIA O NO DEL REPRESENTATIVO
            int pos = buscar(aux);
            if(pos != -1){
                //ACUMULAR AL REPRESENTATIVO QUE LE CORRESPONDA
                getRepresentativos().get(pos).Acumular(aux);
            }
            else{
                getRepresentativos().add(new PatronRepresentativo(aux));
            }
        }
        for(int i=0; i<getRepresentativos().size();i++){
            getRepresentativos().get(i).promediar();
        }
    }
    
    public void clasificar(Patron patron){
        int iMenor=0;
        double dMenor = h.calcularDistanciaEuclidiana(patron, this.representativos.get(0));
        //EN PROCESO ITERATIVO CALCULAR LAS DISTANCIAS CON RESPECTO A LOS REPRESENTATIVOS
        for(int i= 1; i < this.representativos.size();i++){
            double dN = h.calcularDistanciaEuclidiana(patron, this.representativos.get(i));
            if(dN <= dMenor){
                dMenor = dN;
                iMenor = i;
            }
        }
        patron.setClaseResultante(this.representativos.get(iMenor).getClase());
    }
    
    public void clasificar(ArrayList<Patron> patrones){
        for(int i=0; i<patrones.size();i++){
            clasificar(patrones.get(i));
        }
    }
    private int buscar(Patron p){
        String clase = "";
        for (int i=0; i<getRepresentativos().size(); i++){
            if(getRepresentativos().get(i).getClase().equals(p.getClase())){
                return i;
            }
        }
        return -1;
    }

    /**
     * @return the representativos
     */
    public ArrayList<PatronRepresentativo> getRepresentativos() {
        return representativos;
    }
    
}
