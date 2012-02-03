/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import org.jdom.Element;

/**
 *
 * @author hector
 */
public interface DaoXml {
    
    public Element abrirArchivo(String path);

    public boolean cargarActualizacion(String path);

    public void copiarArchivoActualizacion(String path);

    public void copiarArchivoApuestas(File archivoDestino);

   
    public void cargarApuestasMemoria(String path);




}
