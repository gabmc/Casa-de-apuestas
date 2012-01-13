/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TestPackages;

import Logica.Participante;
import junit.framework.TestCase;

/**
 *
 * @author hector
 */
public class ParticipanteTest extends TestCase {

    final Participante participante = new Participante(1,"Juan Perozo","Gran codificador",1);

     public void testGetDescripcion() {
        assertEquals("Gran codificador",participante.getDescripcion());
    }

    public void testGetId() {
        assertEquals(1, participante.getId());
    }

    public void testGetIdCategoria() {
        assertEquals(1,participante.getIdCategoria());
    }


    public void testGetNombre() {
        assertEquals("Juan Perozo",participante.getNombre());
    }


}
