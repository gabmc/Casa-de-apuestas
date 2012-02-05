/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Persistencia.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author Usuario
 */
public class Fachada {
    
    private GestionPorArchivo propio;
    private GestionPorArchivoAjeno ajeno;
    
    public Fachada(){
        propio = new GestionPorArchivo();
        ajeno = new GestionPorArchivoAjeno();
    }
    
    public String buscarArchivo(){
        File[] files;
        files = File.listRoots();
        String directorio = files[files.length-2].getPath();
        try {
            String path = directorio + "archivoActualizacion.xml";
            FileReader fr = new FileReader(path);
            return "archivoActualizacion.xml";
            
        } catch (FileNotFoundException ex) {
            try{
                String path = directorio + "ArchivoDeExportacion.xml";
                FileReader fr = new FileReader(path);
                return "ArchivoDeExportacion.xml";
            }catch(Exception e){
                
            }
        }
        return "aaa";
    }
    
    public Element abrirArchivo(String path){
        SAXBuilder constructor = new SAXBuilder();
        try {
            Document documento = (Document) constructor.build(new FileInputStream(path));
            Element elemento = documento.getRootElement();
            return elemento;
        } catch (JDOMException ex) {
            System.out.println("error");
        } catch (IOException ex) {
            System.out.println("error");
        }
        return null;

    }
    
    public void cargarLocal(String path){
            if (path.equals("archivos/persistencia.xml")){
                Element archivo = abrirArchivo(path);
                if (archivo.getName().equals("archivo")){
                    GestionPorArchivo gestion = new GestionPorArchivo();
                    gestion.cargarActualizacion(path);
                }
                else if (archivo.getName().equals("DatosDeCarga")){
                    GestionPorArchivoAjeno gestion = new GestionPorArchivoAjeno();
                    gestion.cargarActualizacion(path);
                }
            }
    }
    
    public void cargarActualizacion(String path){
            if (buscarArchivo().equals("archivoActualizacion.xml")){
                GestionPorArchivo gestion = new GestionPorArchivo();
                gestion.cargarActualizacion(path);
                gestion.copiarArchivoActualizacion(path);
            }
            if (buscarArchivo().equals("ArchivoDeExportacion.xml")){
                GestionPorArchivoAjeno gestion = new GestionPorArchivoAjeno();
                gestion.cargarActualizacion(path);
                gestion.copiarArchivoActualizacion(path);
            }

    }
    
    public void cargarApuestasMemoria(){
        
    }
    

    
}
