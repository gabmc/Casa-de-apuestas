/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author Usuario
 */
public class Evento {
    private int id;
    private int idCategoria;
    private String nombre;
    private String fecha; //Cambiar
    private String descripcion;
    private String horaInicio;
    private boolean admiteTabla;
    private boolean permiteEmpate;

    public Evento(int id, int idCategoria, String nombre, String fecha, String descripcion, String horaInicio, boolean admiteTabla, boolean permiteEmpate) {
        this.id = id;
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.horaInicio = horaInicio;
        this.admiteTabla = admiteTabla;
        this.permiteEmpate = permiteEmpate;
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

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
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

}
