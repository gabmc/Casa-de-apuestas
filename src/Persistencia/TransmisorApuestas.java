/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import Logica.Apuesta;
import Logica.Logica;
import Logica.Participante;
import WebClient.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author HECTOR
 */
public class TransmisorApuestas {
    Logica logica = Logica.dameLogica();
    private Casa_0020De_0020ApuestasService conexion;
    private Casa_0020De_0020ApuestasPortType tipoPuerto;
    private static TransmisorApuestas instancia;

    private TransmisorApuestas(){
        this.conexion = new Casa_0020De_0020ApuestasService();
        this.tipoPuerto = conexion.getCasa_0020De_0020Apuestas();
    }

    public static TransmisorApuestas getInstance(){
        if (instancia == null)
            instancia = new TransmisorApuestas();
        return instancia;
    }

    public String obtenerToken(int idMaquina){
        AsignacionToken parametro = new AsignacionToken();
        parametro.setId(idMaquina);
        String token = tipoPuerto.asignacionToken(parametro).getResult();
        return token;
    }

    private ArrayOfenviarApuestalistaApuestasapuesta construirApuestaParametro
            (Apuesta anteriorApuesta){
        ArrayOfenviarApuestalistaApuestasapuesta nuevaApuesta =
                new ArrayOfenviarApuestalistaApuestasapuesta();
        ArrayOfenviarApuestalistaApuestasapuestaParticipantes par =
                new ArrayOfenviarApuestalistaApuestasapuestaParticipantes();
        ArrayOfArrayOfenviarApuestalistaApuestasapuestaParticipanteslistaParticipantes
                lista = new ArrayOfArrayOfenviarApuestalistaApuestasapuestaParticipanteslistaParticipantes();
        lista.getIdParticipante().addAll(Logica.dameLogica()
                .getListaIdsParticipantesApuesta(
                anteriorApuesta.getParticipantes()));
        par.setListaParticipantes(lista);
        nuevaApuesta.setFechaApuesta(anteriorApuesta.getFechaApuestaString());
        nuevaApuesta.setIdEvento(anteriorApuesta.getIdEvento());
        nuevaApuesta.setParticipantes(par);
        nuevaApuesta.setMaquinaid(anteriorApuesta.getIdMaquina());
        nuevaApuesta.setMontoApuesta((int) anteriorApuesta.getMontoApuesta());
        return nuevaApuesta;
    }

    private ArrayOfenviarApuestalistaApuestas construirListaParametro(){
        ArrayOfenviarApuestalistaApuestas listaApuestaEnviar =
                new ArrayOfenviarApuestalistaApuestas();
        ArrayList<Apuesta>listaApuesta = logica.getListaApuestas();
        Iterator iterator = listaApuesta.iterator();
        while(iterator.hasNext()){
            listaApuestaEnviar.getApuesta().
                    add(construirApuestaParametro((Apuesta)iterator.next()));
        }
        return listaApuestaEnviar;
    }

    public boolean enviarApuesta(){
        if (!logica.getListaApuestas().isEmpty()){
            EnviarApuesta peticion = new EnviarApuesta();
            peticion.setListaApuestas(construirListaParametro());
            peticion.setTokenMaquina(Logica.dameLogica().getToken());
            tipoPuerto.enviarApuesta(peticion);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
