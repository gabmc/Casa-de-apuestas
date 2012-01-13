/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Persistencia.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.JDOMException;

/**
 *
 * @author Usuario
 */
public class main {
public static void main (String[] args){    
    GestionPorArchivo gestion = new GestionPorArchivo();

            gestion.cargarActualizacion("archivos/persistencia.xml");
            gestion.cargarApuestasMemoria("archivos/apuestas.xml");

    while (true){
    HiloUSB hilo = new HiloUSB("archivoActualizacion.xml", new GUI.InicioCategoria(),"LEER");
    hilo.run();
   // GestionPorArchivo cargar = new GestionPorArchivo();
    }
}
}
