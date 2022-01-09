/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package herramientas;

import data.ObjetoKNN;
import data.Patron;
import data.PatronRepresentativo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author oziel
 */
public class Herramientas {
    
    
    public ArrayList<Patron> abrirFile() throws IOException {
        int contador=0;
     String texto, aux;
     LinkedList<String> lista = new LinkedList();
     ArrayList<Patron> patrones = new ArrayList<>();
        try {
            //llamamos el metodo que permite cargar la ventana
            JFileChooser file = new JFileChooser();
            file.showOpenDialog(file);
            //abrimos el archivo seleccionado
            File abre = file.getSelectedFile();

            //recorremos el archivo y lo leemos
            if (abre != null) {
                FileReader archivos = new FileReader(abre);
                BufferedReader lee = new BufferedReader(archivos);

                while ((aux = lee.readLine()) != null) {
                    texto = aux;
                    lista.add(texto);
                }
                lee.close();

                ArrayList<String> lista2 = new ArrayList<>();
                String clase = "";
                for (int i = 0; i < (lista.size()); i++) {
                    StringTokenizer st = new StringTokenizer(lista.get(i), ",");

                    while (st.hasMoreTokens()) {
                        lista2.add(st.nextToken());
                    }
                    double[] vector = new double[lista2.size()-1];
                    for (int x = 0; x < lista2.size()-1; x++) {
                        vector[x] = Double.parseDouble(lista2.get(x));
                    }
                    clase = lista2.get(lista2.size()-1);
                    patrones.add(new Patron(clase, vector));
                    lista2.clear();
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex + ""
                    + "\nNo se ha encontrado el archivo",
                    "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        return patrones;
    }
    public double calcularDistanciaEuclidiana(Patron a, Patron b){
        double aux = 0;
        for(int x=0;x<a.getVector().length;x++){
            aux+=Math.pow(a.getVector()[x]-b.getVector()[x],2);
        }
        return Math.sqrt(aux);
    }
    public void ordenarObjetosKNN(ArrayList<ObjetoKNN> objetosKNN){
        ObjetoKNN auxObjetoKNN;
        for(int i=0;i<objetosKNN.size();i++){
            for(int j=0; j<objetosKNN.size()-1;j++){
                if(objetosKNN.get(j).getDistancia() > objetosKNN.get(j+1).getDistancia()){
                    auxObjetoKNN = objetosKNN.get(j);
                    objetosKNN.set(j, objetosKNN.get(j+1));
                    objetosKNN.set(j+1, auxObjetoKNN);
                }
            }
        }
    }
    public String toStringArray(ArrayList<Patron> aux){
        String auxS="";
        for(int i=0;i<aux.size();i++){
            auxS+=aux.get(i).toString()+"\n";
        }
        return auxS;
    }
    public String toStringArrayRepresentativos(ArrayList<PatronRepresentativo> aux){
        String auxS="";
        for(int i=0;i<aux.size();i++){
            auxS+=aux.get(i).toString()+"\n";
        }
        return auxS;
    }
    public String toStringArrayObjetosKNN(ArrayList<ObjetoKNN> aux){
       String auxO = "";
        for(int i=0; i<aux.size();i++){
            auxO += aux.get(i).toString()+"\n";
        }
        return auxO;
    }
}
