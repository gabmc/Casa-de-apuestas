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
        try {
            gestion.cargarActualizacion("archivos/persistencia.xml");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JDOMException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    while (true){
    HiloUSB hilo = new HiloUSB("archivoActualizacion.xml", new GUI.InicioCategoria(),"LEER");
    hilo.run();
    GestionPorArchivo cargar = new GestionPorArchivo();
}
}
}
