/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supervisada;

import data.Patron;
import java.util.ArrayList;

/**
 *
 * @author oziel
 *  ENTRENAMIENTO:
 * VECTORES PROMEDIO
 * VECTORES VARIANZA
 * VECTORES DESVIACIÓN
 * 
 *  CLASIFICACIÓN
 * DISTRIBUCIÓN NORMAL
 */
public class NaiveBayes {
    private ArrayList<Patron> instancias;
    private ArrayList<String> clases;
    private ArrayList<Integer> numero_de_instancias_pc;
    private double[][] promedio;
    private double[][] varianza;
    private double[][] desviacion;
    private double[] a_priori;
    private double[] a_posteriori;
    
    public NaiveBayes() {
        this.instancias = new ArrayList();
        this.clases = new ArrayList();
        this.numero_de_instancias_pc = new ArrayList();
    }
    
    public void entrenar(ArrayList<Patron> instancias){
        this.instancias = instancias;
        obtenerClases();
        obtenerAPriori();
        obtenerPromedio();
        obtenerVarianza();
        obtenerDesviacion();
    }
    
    public void clasificar(Patron patron){
        double mayor;
        double in;
        
        this.a_posteriori = new double[this.a_priori.length];
        double[][] distribucion_normal = new double[this.clases.size()][this.instancias.get(0).getVector().length];
        for(int i=0;i<this.clases.size();i++){
            for(int j=0;j<this.instancias.get(0).getVector().length; j++){
                double varianza = this.varianza[i][j];
                double b = Math.pow(patron.getVector()[j]-this.promedio[i][j],2);
                double parte1 = (1/(Math.sqrt(2*Math.PI*varianza)));
                double parte2 = Math.exp((-1*b)/(2*varianza));
                double aux = parte1*parte2;
                distribucion_normal[i][j] = aux;
            }
        }
        for(int i=0; i<this.clases.size();i++){
            this.a_posteriori[i]=this.a_priori[i];
            for(int j=0; j<this.instancias.get(0).getVector().length;j++){
                this.a_posteriori[i] = (this.a_posteriori[i]*distribucion_normal[i][j]); 
            }
        }
        mayor = this.a_posteriori[0];
        int ctrl=0;
        for(int i=0;i<this.a_posteriori.length;i++){
            in = this.a_posteriori[i];
            if(in > mayor){
                mayor = in;
                ctrl = i;
            }
        }
        patron.setClaseResultante(this.clases.get(ctrl));
    }
    
    public void clasificar(ArrayList<Patron> instanciasb){
        for(int i=0;i<instanciasb.size();i++){
            clasificar(instanciasb.get(i));
        }
    }
    public void obtenerAPriori(){
        this.a_priori = new double[this.clases.size()];
        for(int i=0; i<this.clases.size();i++){
            this.a_priori[i] = (double)this.numero_de_instancias_pc.get(i) / (double)this.instancias.size();
        }
    }
    public void obtenerPromedio(){
        double[][] suma = new double[this.clases.size()][this.instancias.get(0).getVector().length];
        this.promedio = new double[this.clases.size()][this.instancias.get(0).getVector().length];
        for(int i=0; i<this.instancias.size();i++){
            for(int j=0;j<this.instancias.get(0).getVector().length;j++){
            suma[this.clases.indexOf(this.instancias.get(i).getClase())][j] += this.instancias.get(i).getVector()[j];
            }   
        }
        
        
        for(int i=0;i<this.clases.size();i++){
            for(int j=0;j<this.instancias.get(i).getVector().length;j++){
                promedio[i][j] = suma[i][j]/this.numero_de_instancias_pc.get(i);
            }
        }
    }
    
    public void obtenerVarianza(){
        varianza = new double[this.clases.size()][this.instancias.get(0).getVector().length];
        double[][] suma = new double[this.clases.size()][this.instancias.get(0).getVector().length];
        for(int i=0;i<this.instancias.size();i++){
            for(int j=0;j<this.instancias.get(0).getVector().length;j++){
                double valor = this.promedio[this.clases.indexOf(this.instancias.get(i).getClase())][j];
                double valor2 = this.instancias.get(i).getVector()[j];
                suma[this.clases.indexOf(this.instancias.get(i).getClase())][j]+=
                        Math.pow(valor-valor2,2);
            }
        }
        
        for(int i=0; i<this.clases.size();i++){
            for(int j=0;j<this.instancias.get(0).getVector().length;j++){
                this.varianza[i][j] = (suma[i][j]/((this.numero_de_instancias_pc.get(i))-1));    
            }
        }
    }
    
    public void obtenerDesviacion(){
        this.desviacion = new double[this.clases.size()][this.instancias.get(0).getVector().length];
        for(int i=0; i<this.clases.size();i++){
            for(int j=0; j<this.instancias.get(i).getVector().length;j++){
                this.desviacion[i][j] = Math.sqrt(this.varianza[i][j]);
            }
        }
    }
    
    public void obtenerClases(){
        for(int i=0; i< this.instancias.size();i++){
            if(!this.clases.contains(this.instancias.get(i).getClase())){
                this.clases.add(this.instancias.get(i).getClase());
                this.numero_de_instancias_pc.add(this.clases.indexOf(this.instancias.get(i).getClase()), 1);
            }else{
                acumular(this.clases.indexOf(this.instancias.get(i).getClase()));
                
            }
        }
    }
    
    public void acumular(int indexOf){
        this.numero_de_instancias_pc.set(indexOf, this.numero_de_instancias_pc.get(indexOf)+1);
    }
}
