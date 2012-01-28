/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *Clase que nos permite guardar toda la informacion referente al administrador
 * del sistema
 * 
 * @author Usuario
 */
public class Administrador {
   
    int id;
    String nick;
    String nombre;
    String apellido;
    String password;
    String email;
 /**
  * Clase que nos permite guardar toda la informacion referente al administrador
  * del sistema
  *
  * @param id del administrador
  * @param nick del administrador
  * @param nombre del administrador
  * @param apellido del administrador
  * @param password del administrador
  */
    public Administrador(int id, String nick, String nombre, String apellido,
            String password, String email) {
        this.id = id;
        this.nick = nick;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.email = email;
    }
    
    public Administrador(){
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
