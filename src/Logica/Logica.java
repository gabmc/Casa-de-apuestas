/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Logica {
    
    private int id;
    private ArrayList<Administrador> listaAdministradores;
    private ArrayList<Categoria> listaCategorias;
    private ArrayList<Apuesta> listaApuestas;
    
    private Logica(){
        listaAdministradores = new ArrayList();
        listaCategorias = new ArrayList();
        listaApuestas = new ArrayList();
        id = 9;
    }
    
    private static Logica instancia;
    
    public static Logica dameLogica(){
        if (instancia == null)
            instancia = new Logica();
        return instancia;     
    }

    public ArrayList<Administrador> getListaAdministradores() {
        return listaAdministradores;
    }

    public ArrayList<Apuesta> getListaApuestas() {
        return listaApuestas;
    }

    public ArrayList<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setID(int id){
        this.id = id;
    }

    public int getID(){
        return id;
    }

    public boolean insertarApuestaLocal(Apuesta apuesta){
        return listaApuestas.add(apuesta);
    }
}
