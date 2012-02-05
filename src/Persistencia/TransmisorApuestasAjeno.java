/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import Logica.Logica;
import org.apache.log4j.Logger;
import org.tempuri.*;
/**
 *
 * @author HECTOR
 */
public class TransmisorApuestasAjeno {
    private Service1 servicio;
    private Service1Soap porto;
    static Logger logger = Logger.getLogger(TransmisorApuestasAjeno.class);


    public String obtenerToken(int idMaquina){
        servicio = new Service1();
        porto = servicio.getService1Soap();
        String token = porto.obtenerToken(Logica.dameLogica().getID());
        return token;
    }

}
