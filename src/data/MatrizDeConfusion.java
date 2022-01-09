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
public class MatrizDeConfusion {
    //ATRIBUTOS
    private double[][] matriz;
    private ArrayList<Patron> instancias;
    private ArrayList<String> clases;
    //CONSTRUCTORES
    public MatrizDeConfusion(ArrayList<Patron> instancias){
        this.instancias = instancias;
        this.clases = new ArrayList();
        matriz = null;
        inicializarMatriz();
    }
    //MÉTODOS
    public void inicializarMatriz(){
        for(int i=0; i<instancias.size();i++){
            if(!this.clases.contains(instancias.get(i).getClase())){
                this.clases.add(instancias.get(i).getClase());
            }
        }
        int tam = this.clases.size();
        this.matriz = new double[tam][tam];
        for(int i=0; i<getInstancias().size();i++){
            int f = this.clases.indexOf(instancias.get(i).getClase());
            int c = this.clases.indexOf(instancias.get(i).getClaseResultante());
            this.matriz[f][c]++;
        }
    }
    @Override
    public String toString(){
        double porcentajeFinal=0;
        String aux = "";
        for(int i=0;i<this.clases.size();i++){
            aux += (i+1)+") "+this.clases.get(i)+"\n";
        }
        aux += "\n";
        for(int i=0;i<this.clases.size();i++){
            double suma_de_instancias_por_clase=0;
            for(int j=0;j<this.clases.size();j++){
                aux += " ";
                aux += this.matriz[i][j];
                if(this.matriz[i][j] <= 9) aux += "  ";
                if(this.matriz[i][j] <= 99 && this.matriz[i][j] > 9) aux += " ";
                suma_de_instancias_por_clase += this.matriz[i][j];
            }
            aux += " | "+"Porcentaje correcto "+((this.matriz[i][i]/suma_de_instancias_por_clase*100)+"%");
            aux += "\n";
            porcentajeFinal += matriz[i][i];
        }
        aux += "\n";
        porcentajeFinal = (porcentajeFinal/instancias.size())*100;
        aux += "Porcentaje final: "+porcentajeFinal+"%";
        return aux;
    }
    public String eficaciaGeneral(){
        double porcentajeFinal=0;
        for(int i=0;i<this.clases.size();i++){
            porcentajeFinal += matriz[i][i];
        }
        porcentajeFinal = (porcentajeFinal/instancias.size())*100;
        return "Porcentaje final: "+porcentajeFinal+"%";
    }
    /**
     * @return the matriz
     */
    public double[][] getMatriz() {
        return matriz;
    }

    /**
     * @param matriz the matriz to set
     */
    public void setMatriz(double[][] matriz) {
        this.matriz = matriz;
    }

    /**
     * @return the instancias
     */
    public ArrayList<Patron> getInstancias() {
        return instancias;
    }

    /**
     * @param instancias the instancias to set
     */
    public void setInstancias(ArrayList<Patron> instancias) {
        this.instancias = instancias;
    }

    /**
     * @return the clases
     */
    public ArrayList<String> getClases() {
        return clases;
    }

    /**
     * @param clases the clases to set
     */
    public void setClases(ArrayList<String> clases) {
        this.clases = clases;
    }
}
