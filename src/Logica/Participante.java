/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author Usuario
 */
public class Participante {
    int id;
    String nombre;
    String descripcion;
    int idCategoria;

    public Participante(int id, String nombre, String descripcion, int idCategoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idCategoria = idCategoria;
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


}
