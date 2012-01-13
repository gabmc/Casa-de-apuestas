/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TestPackages;

import Logica.Apuesta;
import Logica.Participante;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author hector
 */
public class ApuestaTest extends TestCase {

    final ArrayList<Participante> participanteTest = new ArrayList<Participante>();
    final Apuesta apuesta = new Apuesta("Gary", "Bustillos",
            123456789, 300, 1
            , participanteTest);

    
    public ApuestaTest(){
        
    Participante participante1 = new Participante(1, "Caracas Futbol Club", "Gran Equipo", 1);
    participanteTest.add(participante1);
}

  public void  testGetApellidoApostador() {
      assertEquals("Bustillos",apuesta.getApellidoApostador());
  }
        

    public void testGetCedulaApostador() {
       assertEquals(123456789,apuesta.getCedulaApostador());
    }


    public void testGetFechaApuesta() {
         assertEquals(Calendar.getInstance().getTime(),apuesta.getFechaApuesta());
    }


    public void testGetMontoApuesta() {
        assertEquals(300.0,apuesta.getMontoApuesta());
    }

  

    public void testGetNombreApostador() {
        assertEquals("Gary",apuesta.getNombreApostador());
    }

     public void testGetIdEvento() {
        assertEquals(1,apuesta.getIdEvento());
    }

    public void testGetIdMaquina() {
        assertEquals(9,apuesta.getIdMaquina());
    }

    public void testGetParticipantes() {
        assertEquals(participanteTest, apuesta.getParticipantes());
    }

  

 }