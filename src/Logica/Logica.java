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
    
    ArrayList<Administrador> listaAdministradores;
    ArrayList<Categoria> listaCategorias;
    ArrayList<Apuesta> listaApuestas;
    
    private Logica(){
        
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
    
    
}
