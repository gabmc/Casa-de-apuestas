/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TestPackages;

import Logica.Categoria;
import Logica.Evento;
import java.util.ArrayList;
import junit.framework.TestCase;

/**
 *
 * @author hector
 */
public class CategoriaTest extends TestCase {

    final Categoria categoria = new Categoria(1,"Tenis", "Deporte rapido");
    final ArrayList<Evento> eventos = new ArrayList<Evento>();

    public CategoriaTest() {
        eventos.add(new Evento(1,"eventoPrueba", null, null, null, true, true));
        categoria.setListaEventos(eventos);
    }

    public void testGetDescripcion() {
        assertEquals("Deporte rapido",categoria.getDescripcion());
    }

    public void testGetId() {
        assertEquals(1,categoria.getId());
    }


    public void testGetNombre() {
        assertEquals("Tenis", categoria.getNombre());
    }

 
    public void testGetListaEventos() {
        assertEquals(eventos,categoria.getListaEventos());
    }

    public void testGetEventoPorId(){
       assertEquals(eventos.get(0), categoria.getEventoPorId(1));
    }

    public void testGetEventoPorNombre(){
        assertEquals(eventos.get(0),categoria.getEventoPorNombre("eventoPrueba"));
    }



}
