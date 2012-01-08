/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Apuesta {
    private String nombreApostador;
    private String apellidoApostador;
    private int cedulaApostador;
    private int montoApuesta;  //Volver a Float
    private Calendar fechaApuesta;
    private int idEvento;
    private int idParticipante;
    private int idMaquina;

    public Apuesta(String nombreApostador, String apellidoApostador, int cedulaApostador, int montoApuesta, int idEvento, int idParticipante) {
        this.nombreApostador = nombreApostador;
        this.apellidoApostador = apellidoApostador;
        this.cedulaApostador = cedulaApostador;
        this.montoApuesta = montoApuesta;
        this.fechaApuesta = Calendar.getInstance();
        this.idEvento = idEvento;
        this.idParticipante = idParticipante;
        this.idMaquina = Logica.dameLogica().getID();
    }

    public String getApellidoApostador() {
        return apellidoApostador;
    }

    public void setApellidoApostador(String apellidoApostador) {
        this.apellidoApostador = apellidoApostador;
    }

    public int getCedulaApostador() {
        return cedulaApostador;
    }

    public void setCedulaApostador(int cedulaApostador) {
        this.cedulaApostador = cedulaApostador;
    }

    public Calendar getFechaApuesta() {
        return fechaApuesta;
    }

    public String getFechaApuestaString(){
        String fecha = String.valueOf(fechaApuesta.get(Calendar.YEAR)) + "-";
        if (fechaApuesta.get(Calendar.MONTH) < 9)
            fecha += "0" + String.valueOf(fechaApuesta.get(Calendar.MONTH)+1) + "-";
        else
            fecha += String.valueOf(fechaApuesta.get(Calendar.MONTH)+1) + "-";
        if (fechaApuesta.get(Calendar.DATE) < 10)
            fecha += "0" + String.valueOf(fechaApuesta.get(Calendar.DATE));
        else
            fecha += String.valueOf(fechaApuesta.get(Calendar.DATE));
        return fecha;
    }
    
    public void setFechaApuesta() {
        this.fechaApuesta = Calendar.getInstance();
    }

    public float getMontoApuesta() {
        return montoApuesta;
    }

    public void setMontoApuesta(int montoApuesta) {
        this.montoApuesta = montoApuesta;
    }

    public String getNombreApostador() {
        return nombreApostador;
    }

    public void setNombreApostador(String nombreApostador) {
        this.nombreApostador = nombreApostador;
    }
    
     public int getIdEvento() {
        return idEvento;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public int getIdParticipante() {
        return idParticipante;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }

    public void setFechaApuesta(Calendar fechaApuesta) {
        this.fechaApuesta = fechaApuesta;
    }

    
}