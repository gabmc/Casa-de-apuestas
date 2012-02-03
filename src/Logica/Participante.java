/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *Clase que posee toda la informacion referente a un participante
 *
 * @author Usuario
 */
public class Participante implements Cloneable {
    int id;
    String nombre;
    String descripcion;
    int idCategoria;
    String relacionPago;
    float limiteApuesta;

  

    /**
     * Constructor de la clase Participante, posee todos los atributos de
     * participante y el id de la categoria
     * @param id del participante
     * @param nombre del participante
     * @param descripcion del participante
     * @param idCategoria del participante
     */
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
   
  public double getLimiteApuesta() {
        return limiteApuesta;
    }

    public void setLimiteApuesta(float limiteApuesta) {
        this.limiteApuesta = limiteApuesta;
    }

    public String getRelacionPago() {
        return relacionPago;
    }

    public void setRelacionPago(String relacionPago) {
        this.relacionPago = relacionPago;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
