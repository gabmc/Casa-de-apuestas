/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Usuario
 */
public class Categoria {
    int id;
    String nombre;
    String descripcion;
    private ArrayList<Evento> listaEventos;

    public Categoria(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        listaEventos = new ArrayList<Evento>();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Evento> getListaEventos() {
        return listaEventos;
    }

    public Evento getEventoPorId(int id){
        Iterator iterator = listaEventos.iterator();
        Evento evento = null;
        while (iterator.hasNext()){
            evento = (Evento)iterator.next();
            if(evento.getId() == id)
                break;
            evento = null;
        }
        return evento;
    }

    public Evento getEventoPorNombre(String nombre){
        Iterator iterator = listaEventos.iterator();
        Evento evento = null;
        while(iterator.hasNext()){
            evento = (Evento)iterator.next();
            if(evento.getNombre().equals(nombre))
                break;
            evento = null;
        }
        return evento;
    }

}
