/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TestPackages;

import Logica.Administrador;
import Logica.Apuesta;
import Logica.Categoria;
import Logica.Evento;
import Logica.Logica;
import Logica.Participante;
import java.util.ArrayList;
import junit.framework.TestCase;

/**
 *
 * @author hector
 */
public class LogicaTest extends TestCase {

    final Logica logica = Logica.dameLogica();
    static  ArrayList<Administrador> listaAdmin = new ArrayList<Administrador>();
    static  ArrayList<Apuesta> listaApuestas = new ArrayList<Apuesta>();
    static  ArrayList<Categoria> listaCategoria = new ArrayList<Categoria>();
    static  ArrayList<Evento> listaEventos = new ArrayList<Evento>();
    static  ArrayList<Participante> listaParticipantes = new ArrayList<Participante>();
    
    public LogicaTest() {
        logica.setListaAdministradores(listaAdmin);
        logica.setListaApuestas(listaApuestas);
        logica.setListaCategorias(listaCategoria);
        listaCategoria.add(new Categoria(1, "Hola Mundo", null));
        listaAdmin.add(new Administrador(1, null, null, null, null,null));
        listaApuestas.add(new Apuesta(null, null, 123456, 1, 1, null));
        listaEventos.add(new Evento(1,"Tennis Cup", null, null, null, true, true));
        listaParticipantes.add(new Participante(1,"Gary Bustillos", null, 1));
        listaEventos.get(0).setParticipantes(listaParticipantes);
        listaCategoria.get(0).setListaEventos(listaEventos);
        logica.setIdEventoActual(1);
        logica.setIdCategoriaActual(1);

    }



     public void  testGetListaAdministradores() {
        assertEquals(listaAdmin,logica.getListaAdministradores());

    }

    public void testGetListaApuestas() {
        assertEquals(listaApuestas,logica.getListaApuestas());
    }

    public void testGetListaCategorias() {
        assertEquals(listaCategoria,logica.getListaCategorias());
    }


    public void testGetID(){
        assertEquals(9,logica.getID());
    }

    public void testInsertarApuestaLocal(){

        assertEquals(listaApuestas.add(new Apuesta(null, null, 2, 2, 2, null)),listaApuestas.add(new Apuesta(null, null, 2, 2, 2, null)));
    }

    public void testObtenerCategoriaPorId(){
        assertEquals(listaCategoria.get(0), logica.obtenerCategoriaPorId(1));
    }

    public void testObtenerCategoriaPorString(){
        assertEquals(listaCategoria.get(0), logica.obtenerCategoriaPorString("Hola Mundo"));
    }

    public void testObtenerEventoPorString(String nombre, int idCategoria){
        assertEquals(listaEventos.get(0), logica.obtenerEventoPorString("Tennis Cup", 1));
    }

    public void  testObtenerParticipantePorString(String nombre, int idEvento){
        assertEquals(listaParticipantes.get(0),logica.obtenerParticipantePorString("Gary Bustillos",1,1));

    }


    public void testGetEventoPorId(int id){
        assertEquals(listaEventos.get(0),logica.getEventoPorId(1));
    }

    public void testGetEventoPorNombre(String nombre){
        assertEquals(listaEventos.get(0), logica.getEventoPorNombre("Tennis Cup"));
    }


    public void testGetIdCategoriaActual() {
        assertEquals(1,logica.getIdCategoriaActual());
    }

    public void testGetIdEventoActual() {
        assertEquals(1,logica.getIdEventoActual());
    }


    

}
