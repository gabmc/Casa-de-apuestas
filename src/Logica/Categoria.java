/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;
import java.util.Iterator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Usuario
 */
public class Categoria {
    int id;
    String nombre;
    String descripcion;
    private ArrayList<Evento> listaEventos;
    static Logger logger = Logger.getLogger(Categoria.class);

    public Categoria(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        listaEventos = new ArrayList<Evento>();
        PropertyConfigurator.configure("log4j.properties");
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
            if(evento.getId() == id){
                logger.info("Se obtuvo el evento con el id:"+id);
                break;
            }
            evento = null;
        }
        return evento;
    }

    public Evento getEventoPorNombre(String nombre){
        Iterator iterator = listaEventos.iterator();
        Evento evento = null;
        while(iterator.hasNext()){
            evento = (Evento)iterator.next();
            if(evento.getNombre().equals(nombre)){
                logger.info("Se obtuvo el evento con el nombre:"+nombre);
                break;
            }
            evento = null;
        }
        return evento;
    }

}
