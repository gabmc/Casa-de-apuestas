/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Apuesta {
    private String nombreApostador;
    private String apellidoApostador;
    private int cedulaApostador;
    private float montoApuesta;
    private Date fechaApuesta;
    private int idApuesta;
    private int idMaquina;

    public Apuesta(String nombreApostador, String apellidoApostador, int cedulaApostador, float montoApuesta, Date fechaApuesta, int idApuesta) {
        this.nombreApostador = nombreApostador;
        this.apellidoApostador = apellidoApostador;
        this.cedulaApostador = cedulaApostador;
        this.montoApuesta = montoApuesta;
        this.fechaApuesta = fechaApuesta;
        this.idApuesta = idApuesta;
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

    public Date getFechaApuesta() {
        return fechaApuesta;
    }
    
    public void setFechaApuesta(Date fechaApuesta) {
        this.fechaApuesta = fechaApuesta;
    }

    public int getIdApuesta() {
        return idApuesta;
    }

    public void setIdApuesta(int idApuesta) {
        this.idApuesta = idApuesta;
    }

    public float getMontoApuesta() {
        return montoApuesta;
    }

    public void setMontoApuesta(float montoApuesta) {
        this.montoApuesta = montoApuesta;
    }

    public String getNombreApostador() {
        return nombreApostador;
    }

    public void setNombreApostador(String nombreApostador) {
        this.nombreApostador = nombreApostador;
    }
    
    
}
