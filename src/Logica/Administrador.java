/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author Usuario
 */
public class Administrador {
    int id;
    String nick;
    String nombre;
    String apellido;
    String password;

    public Administrador(int id, String nick, String nombre, String apellido, String password) {
        this.id = id;
        this.nick = nick;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
