/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package reconocimientodepatrones2021;

import data.MatrizDeConfusion;
import data.Patron;
import data.PatronRepresentativo;
import herramientas.GeneradorDeInstancias;
import herramientas.Herramientas;
import java.io.IOException;
import java.util.ArrayList;
import nosupervisada.CMeans;
import supervisada.KNN;
import supervisada.MinimaDistancia;
import supervisada.NaiveBayes;

/**
 *
 * @author oziel
 */
public class ReconocimientoDePatrones2021 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Herramientas h = new Herramientas();
        ArrayList<Patron> instancias = h.abrirFile();
        CMeans cm = new CMeans();
        cm.clasificar(instancias, 3);
        cm.generarClusters(3);
        
        
        /*
        Herramientas h = new Herramientas();
        ArrayList<Patron> instancias = h.abrirFile();
        ArrayList<Patron> instanciasb = h.abrirFile();
        NaiveBayes nb = new NaiveBayes();
        nb.entrenar(instancias);
        nb.clasificar(instanciasb);
        System.out.println(h.toStringArray(instanciasb));
        */
        
        
        
        /*
        Herramientas h = new Herramientas();
        ArrayList<Patron> instancias = h.abrirFile();
        for(int i=2;i<6;i++){
            GeneradorDeInstancias gi = new GeneradorDeInstancias(instancias);
            GeneradorDeInstancias gi2 = new GeneradorDeInstancias(instancias);
            int[] vector = new int[i];
            for(int j=0;j<i;j++){
                vector[j] = j;
            }
            gi.instrucciones(vector,30,"PRIMEROS");
            ArrayList<Patron> instanciasEntrenar = gi.getInstanciasUltimo();
            gi2.instrucciones(vector,50,"PRIMEROS");
            ArrayList<Patron> instanciasClasificar = gi2.getInstanciasUltimo();
            ArrayList<Patron> instanciasEntrenar2 = (ArrayList<Patron>) instanciasEntrenar.clone();
            ArrayList<Patron> instanciasClasificar2 = (ArrayList<Patron>) instanciasClasificar.clone();
            
            
        //KNN///////////////////////////////////////////////////////////////
            KNN kn = new KNN(2);
            kn.entrenar(instanciasEntrenar);
            kn.clasificar(instanciasClasificar);
            //IMPRIMIR MATRIZ DE CONFUSI??N
            //System.out.println(".::: KNN :::.");
            MatrizDeConfusion mc = new MatrizDeConfusion(instanciasClasificar);
            //System.out.println(mc.toString());
            System.out.println("KNN: "+mc.eficaciaGeneral());
        ////////////////////////////////////////////////////////////////////////
        
        
         ///////////////////////////MINIMA DISTANCIA/////////////////////////////
       MinimaDistancia md = new MinimaDistancia();
       md.entrenar(instanciasEntrenar2);
       md.clasificar(instanciasClasificar2);
       //IMPRIMIR MATRIZ DE CONFUSI??N
        //System.out.println(".::: M??NIMA DISTANCIA :::.");
       MatrizDeConfusion mc2 = new MatrizDeConfusion(instanciasClasificar2);
       //System.out.println(mc2.toString());
       System.out.println("MD: "+mc2.eficaciaGeneral());
       /////////////////////////////////////////////////////////////////////////
       
       
        }
        
        */
        
        /*       
    //OBJETOS 
       Herramientas h = new Herramientas();
       KNN kn = new KNN(2);
       //INSTANCIAS DE ENTRENAMIENTO Y CLASIFICACI??N
       ArrayList<Patron> instancias = h.abrirFile();
       ArrayList<Patron> instanciasb = h.abrirFile();
       //ENTRENAMIENTO Y CLASIFICACI??N
       kn.entrenar(instancias);
       kn.clasificar(instanciasb);
       //IMPRIMIR MATRIZ DE CONFUSI??N
       MatrizDeConfusion mc = new MatrizDeConfusion(instanciasb);
       System.out.println(mc.toString());
        */
        
        /*          PROBANDO EL M??NIMA DISTANCIA
        Herramientas h = new Herramientas();
        MinimaDistancia md = new MinimaDistancia();
        ArrayList<Patron> instancias = h.abrirFile();
        md.entrenar(instancias);
        ArrayList<Patron> instanciasb = h.abrirFile();
        md.clasificar(instanciasb);
        //System.out.println(h.toStringArray(instancias));
        MatrizDeConfusion mc = new MatrizDeConfusion(instanciasb);
        System.out.println(mc.toString());
        */
    }
    
}
