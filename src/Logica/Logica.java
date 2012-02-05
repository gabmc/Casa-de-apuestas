/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *Clase controladora del paquete Logica, bajo el Patron de Diseño Singleton
 *
 * @author Usuario
 */
public class Logica {
    
    private int id;
    private String token;
    private ArrayList<Administrador> listaAdministradores;
    private ArrayList<Categoria> listaCategorias;
    private ArrayList<Apuesta> listaApuestas;
    private int idCategoriaActual;
    private int idEventoActual;
    static Logger logger = Logger.getLogger(Logica.class);
    
    /**
     * Constructor de la clase Logica, instancia las listas del repositorio 
     * local de datos
     * 
     */

    private Logica(){
        listaAdministradores = new ArrayList();
        listaCategorias = new ArrayList();
        listaApuestas = new ArrayList();
        id = 9;
        token = "";
        PropertyConfigurator.configure("log4j.properties");
    }
    
    private static Logica instancia;
    
    /**
     * Metodo para obtener la instancia del objeto único creado en ejecución
     * @return logica
     */

    public static Logica dameLogica(){
        if (instancia == null)
            instancia = new Logica();
        return instancia;     
    }

    public ArrayList<Administrador> getListaAdministradores() {
        return listaAdministradores;
    }

    public ArrayList<Apuesta> getListaApuestas() {
        return listaApuestas;
    }

    public ArrayList<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setID(int id){
        this.id = id;
    }

    public int getID(){
        return id;
    }

    public boolean insertarApuestaLocal(Apuesta apuesta){
        return listaApuestas.add(apuesta);
    }

    /**
     * Obtiene una categoria por el id por el que fue asignado en la Base de
     * Datos del Servidor
     *
     * @param id de la categoria deseada
     * @return categoria o null en caso de no existir
     */

    public Categoria obtenerCategoriaPorId(int id){
        Iterator iterator = listaCategorias.iterator();
        Categoria categoria = null;
        while(iterator.hasNext()){
            categoria = (Categoria)iterator.next();
            if (categoria.getId() == id){
                    logger.info("Se obtuvo la categoria con el id:"+id);
                    break;
            }
        }
        return categoria;
    }

    /**
     * Obtiene una categoria por su nombre
     *
     * @param nombre de la categoria
     * @return categoria o null en caso de no existir
     */

    public Categoria obtenerCategoriaPorString(String nombre){
        Iterator iterator = listaCategorias.iterator();
        Categoria categoria = null;
        while(iterator.hasNext()){
            categoria = (Categoria)iterator.next();
            if (categoria.getNombre().equals(nombre)){
                logger.info("Se obtuvo la categoria con el nombre:"+nombre);
                break;
            }
        }
        return categoria;
    }

    /**
     * Obtiene un evento por su nombre, perteneciente a las categorias
     * almacenadas
     *
     * @param nombre del evento
     * @return evento o null en caso de no existir
     */
    
    public Evento obtenerEventoPorString(String nombre, int idCategoria){
        Iterator iterator = listaCategorias.iterator();
        Categoria categoria = null;
        while(iterator.hasNext()){
            categoria = (Categoria)iterator.next();
            if (categoria.getId() == idCategoria)
                break;
        }
        Evento evento = null;
        Iterator iterator2 = categoria.getListaEventos().iterator();
        while (iterator2.hasNext()){
            evento = (Evento)iterator2.next();
            if (evento.getNombre().equals(nombre)){
                logger.info("Se obtuvo el evento con el nombre:"+nombre);
                break;
            }
        }
        return evento;
    }

    /**
     * Obtiene un participante por su nombre, perteneciente a una categoria y
     * evento registrado
     *
     * @param nombre del participante
     * @param id del evento
     * @param id de la categoria
     * @return participante o null en caso de no existir
     */
    
    public Participante obtenerParticipantePorString(String nombre, int idEvento,
            int idCategoria){
        Iterator iterator = listaCategorias.iterator();
        Categoria categoria = null;
        while(iterator.hasNext()){
            categoria = (Categoria)iterator.next();
            if (categoria.getId() == idCategoria)
                break;
        }
        Evento evento = null;
        Iterator iterator2 = categoria.getListaEventos().iterator();
        while (iterator2.hasNext()){
            evento = (Evento)iterator2.next();
            if (evento.getId() == idEvento)
                break;
        }
        Participante participante = null;
        Iterator iterator3 = evento.getParticipantes().iterator();
        while (iterator3.hasNext()){
            participante = (Participante)iterator3.next();
            if (participante.getNombre().equals(nombre)){
                logger.info("Se obtuvo el participante con el nombre:"+nombre);
                break;
            }
        }
        return participante;
    }

    /**
     * Obtiene un evento por su id
     *
     * @param id del evento buscado
     * @return evento o null en caso de no existir
     */

    public Evento getEventoPorId(int id){
        Iterator iterator = listaCategorias.iterator();
        Categoria categoria = null;
        Evento evento = null;
        while(iterator.hasNext()){
            categoria = (Categoria) iterator.next();
            if(categoria.getEventoPorId(id) != null){
                evento = categoria.getEventoPorId(id);
                logger.info("Se obtuvo el evento con el id:"+id);
                break;
            }
            evento = null;
        }
        return evento;
    }

    /**
     * Obtiene un evento por su nombre
     *
     * @param nombre del evento buscado
     * @return evento o null en caso de no existir
     */

    public Evento getEventoPorNombre(String nombre){
        Iterator iterator = listaCategorias.iterator();
        Categoria categoria = null;
        Evento evento = null;
        while(iterator.hasNext()){
            categoria = (Categoria) iterator.next();
            if(categoria.getEventoPorNombre(nombre) != null){
                evento = categoria.getEventoPorNombre(nombre);
                logger.info("Se obtuvo el evento con el nombre:"+nombre);
                break;
            }
            evento = null;
        }
        return evento;
    }

    public void setIdCategoriaActual(int idCategoriaActual) {
        this.idCategoriaActual = idCategoriaActual;
    }

    public void setIdEventoActual(int idEventoActual) {
        this.idEventoActual = idEventoActual;
    }

    public int getIdCategoriaActual() {
        return idCategoriaActual;
    }

    public int getIdEventoActual() {
        return idEventoActual;
    }
    
    public void agregarApuesta(Apuesta apuesta){
        listaApuestas.add(apuesta);
    }

    public void setListaAdministradores(ArrayList<Administrador> listaAdministradores) {
        this.listaAdministradores = listaAdministradores;
    }

    public void setListaApuestas(ArrayList<Apuesta> listaApuestas) {
        this.listaApuestas = listaApuestas;
    }

    public void setListaCategorias(ArrayList<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

     public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



    /**
     * Valida que el nick y la contraseña, sean correctas para un administrador
     * en particular
     *
     * @param nick del administrador
     * @param password del administrador
     * @return true de ser correcto
     */
    
    public boolean validarAdmin(String nick, String password){
        for (Administrador admin : Logica.dameLogica().getListaAdministradores()){
            if ((admin.getEmail().equals(nick)) && (admin.getPassword().equals(password))){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
    
    public boolean hayConexion(){
             try {
       InetAddress address = InetAddress.getByName("192.168.137.1");
                 System.out.println("a: " + address.getHostName());
       if (address.getHostName().equals("192.168.137.1")){
           return true;
       }
       else
           return false;
     }
     catch (UnknownHostException e) {
       System.err.println("No se encuentra el host");
     }
     catch (IOException e) {
       System.err.println("El host es inalcanzable");
     }
             return false;
    }
    
    public ArrayList<Integer> getListaIdsParticipantesApuesta(ArrayList<Participante> listaParticipantes){
        ArrayList<Integer> listaIds = new ArrayList();
        Iterator iterador = listaParticipantes.iterator();
        while (iterador.hasNext()){
            Participante participante = (Participante)iterador.next();
            listaIds.add(participante.getId());
        }
        return listaIds;
    }


}
