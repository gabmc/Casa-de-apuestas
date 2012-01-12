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
public class Evento {
    private int id;
    private String nombre;
    private String fecha; //Cambiar
    private String descripcion;
    private String horaInicio;
    private boolean admiteTabla;
    private boolean permiteEmpate;
    private ArrayList<Participante> participantes;
    static Logger logger = Logger.getLogger(Evento.class);

    public Evento(int id, String nombre, String fecha, String descripcion, String horaInicio, boolean admiteTabla, boolean permiteEmpate) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.horaInicio = horaInicio;
        this.admiteTabla = admiteTabla;
        this.permiteEmpate = permiteEmpate;
        participantes = new ArrayList();
         PropertyConfigurator.configure("log4j.properties");
    }

    public boolean isAdmiteTabla() {
        return admiteTabla;
    }

    public void setAdmiteTabla(boolean admiteTabla) {
        this.admiteTabla = admiteTabla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
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

    public boolean isPermiteEmpate() {
        return permiteEmpate;
    }

    public void setPermiteEmpate(boolean permiteEmpate) {
        this.permiteEmpate = permiteEmpate;
    }

    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(ArrayList<Participante> participantes) {
        this.participantes = participantes;
    }

    public Participante buscarParticipanteNombre(String nombre){
        Iterator iterator = participantes.iterator();
        Participante participante = null;
        while(iterator.hasNext()){
            participante = (Participante)iterator.next();
            if(participante.getNombre().equals(nombre)){
                logger.info("Se obtuvo  el participante con el nombre: "+nombre);
                break;
            }
            participante = null;
        }
        return participante;
    }

    
}
