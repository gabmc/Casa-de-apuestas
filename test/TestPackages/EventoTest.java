/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TestPackages;

import Logica.Evento;
import Logica.Participante;
import java.util.ArrayList;
import junit.framework.TestCase;

/**
 *
 * @author hector
 */
public class EventoTest extends TestCase {
    final Evento evento = new Evento(1, "Grandes retos", "10-10-2012", "Grandes Emociones y mucha accion", "20:30:00", true, true);
    final ArrayList<Participante> participantes = new ArrayList<Participante>();

    public EventoTest() {
        participantes.add(new Participante(1,"Hector Sam", null,1));
        evento.setParticipantes(participantes);
    }


    public void testIsAdmiteTabla() {
        assertEquals(true,evento.isAdmiteTabla());
    }

    public void testGetDescripcion() {
        assertEquals("Grandes Emociones y mucha accion", evento.getDescripcion());
    }

   
    public void testGetFecha() {
        assertEquals("10-10-2012", evento.getFecha());
    }


    public void testGetHoraInicio() {
        assertEquals("20:30:00",evento.getHoraInicio());
    }



    public void testGetId() {
        assertEquals(1,evento.getId());
    }

   

    public void testGetNombre() {
        assertEquals("Grandes retos", evento.getNombre());
    }


    public void testIsPermiteEmpate() {
        assertEquals(true,evento.isPermiteEmpate());
    }


    public void testGetParticipantes() {
        assertEquals(participantes,evento.getParticipantes());
    }

    

    public void testBuscarParticipanteNombre(){
        assertEquals(participantes.get(0),evento.buscarParticipanteNombre("Hector Sam"));
    }

}
