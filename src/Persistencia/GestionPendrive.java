/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

/**
 *
 * @author hector
 */
import GUI.AdvertenciaReinicio;
import java.io.*;


import javax.swing.JFrame;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jdom.JDOMException;


public class GestionPendrive{

    private int totalDispositivos;
    private File[] files;
    private boolean kill=false;
    private String archivo;
    JFrame ventana;
    static Logger logger = Logger.getLogger(GestionPendrive.class);

   //Obtengo el total de los dispositivos que hay en la computadora, ej: 2 disco duros, 1 cd rom
    public GestionPendrive(String archivo, JFrame ventana) {
        files = File.listRoots();
        setTotalDispositivos(files.length);
        System.out.println("TOTAL DISPOSITIVOS: "+getTotalDispositivos()+" FILE LENGTH: "+files.length);
        this.archivo=archivo;
        this.ventana = ventana;
        ventana.setVisible(true);
        PropertyConfigurator.configure("log4j.properties");
    }


    public int getTotalDispositivos() {
        return totalDispositivos;
    }

    public void setTotalDispositivos(int totalDispositivos) {
        this.totalDispositivos = totalDispositivos;
    }

    public void setVentana(javax.swing.JFrame nuevaVentana){
        ventana = nuevaVentana;
    }

    public boolean terminar(){

        if (kill){
            try {
                ventana.setEnabled(false);
                Thread.sleep(5000);
                ventana.setVisible(false);
                ventana.dispose();
                AdvertenciaReinicio ventanaReinicio = new AdvertenciaReinicio();
                ventanaReinicio.setVisible(true);
                Thread.sleep(5000); //Hilo de JLABEL
                ventanaReinicio.setVisible(false);
                ventanaReinicio.dispose();
                logger.info("Terminando el hilo actual");
            } catch (InterruptedException ex) {
                logger.error("Error al momento de terminal el hilo actual");
            }
            return true;
        }
        else
            return false;
    }

    public void comenzar(){
//ahora obtengo nuevamente los dispositivos en la computadora una vez iniciado el hilo
       files = File.listRoots();
       ventana.setVisible(true);
       
       if (files.length>getTotalDispositivos()){
//se compara el total de dispositivos actuales con el total de dispositivos al inicio del programa
            logger.info("TOTAL DISPOSITIVOS: "+getTotalDispositivos()+" FILE LENGTH: "+files.length);

                File[] archivos = files[files.length-2].listFiles();
                
                for (File file : archivos) {

                    if (file.getName().contentEquals(archivo)){
                        String directorio = files[files.length-2].getPath();
                        String path = directorio + file.getName();
                        GestionPorArchivo cargar = new GestionPorArchivo();
                    try {
                        cargar.cargarActualizacion(path);
                        cargar.copiarArchivoActualizacion(path);
                        logger.info("Datos cargados de actualizacion");
                    } catch (FileNotFoundException ex) {
                        logger.error("Archivo de actualizacion no encontrado");
                    } catch (JDOMException ex) {
                        logger.error("Excepcion de JDOM");
                    } catch (IOException ex) {
                        logger.error("Excepcion de I/O");
                    }
                        kill = true;
                    }
                }
            }


   }


//METODO PARA ESCRIBIR EN EL PENDRIVE CUANDO VAYAMOS A GUARDAR LAS APUESTAS QUE NO FUERON TRANSMITIDAS!
    public void escribirArchivo(){
        files = File.listRoots();
         if (files.length>getTotalDispositivos()){
//se compara el total de dispositivos actuales con el total de dispositivos al inicio del programa
            logger.info("TOTAL DISPOSITIVOS: "+getTotalDispositivos()+" FILE LENGTH: "+files.length);

               File archivoEscribir = new File(files[files.length-1],"apuestas.xml");
               GestionPorArchivo gestion = new GestionPorArchivo();
            try {
                gestion.copiarArchivoApuestas(archivoEscribir);
                logger.info("Archivos de apuestas copiados");
            } catch (FileNotFoundException ex) {
                logger.error("Archivo de apuestas no encontrado "+ex.getMessage());
            } catch (JDOMException ex) {
                logger.error("Excepcion de JDOM"+ex.getMessage());
            } catch (IOException ex) {
                logger.error("Excepcion de I/O "+ex.getMessage());
            }
            try {
                archivoEscribir.createNewFile();
                logger.info("Archivo de apuestas creados");
            } catch (IOException ex) {
                logger.error("Excepcion de I/O "+ex.getMessage());
            }
               kill = true;
               
        }


    }
}