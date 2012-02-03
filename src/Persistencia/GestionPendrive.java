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

   /**
    * Constructor de la clase GestionPendrive.Es la encargada de ejecutar
    * las acciones del pendrive y la asignacion de ventanas del paquete GUI
    *
    * @param path del archivo de actualizción o de asentamiento de apuesta
    * @param ventana
    */
    public GestionPendrive(String archivo, JFrame ventana) {
        files = File.listRoots();
        setTotalDispositivos(files.length);
        this.archivo=archivo;
        this.ventana = ventana;
        ventana.setVisible(true);
        PropertyConfigurator.configure("log4j.properties");
    }
    
    public GestionPendrive(String archivo){
        files = File.listRoots();
        setTotalDispositivos(files.length);
        this.archivo = archivo;
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

    /**
     * Termina la ejecucion del hilo y procede al reinicio de la aplicación
     * @return True en caso de haber finalizado el hilo
     */

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

    /**
     * Arranca el Hilo y gestiona la busqueda del archivo dentro del
     * dispositivo
     */

    public void comenzar(){
//ahora obtengo nuevamente los dispositivos en la computadora una vez iniciado el hilo
       files = File.listRoots();
       ventana.setVisible(true);
       
       if (files.length>getTotalDispositivos()){
//se compara el total de dispositivos actuales con el total de dispositivos al inicio del programa
            logger.info("TOTAL DISPOSITIVOS: "+getTotalDispositivos()+" FILE LENGTH: "+files.length);

                File[] archivos = files[files.length-1].listFiles();
                
                for (File file : archivos) {

                    if (file.getName().contentEquals(archivo)){
                        String directorio = files[files.length-1].getPath();
                        String path = directorio + file.getName();
                        Logica.Logica.dameLogica().getListaAdministradores().clear();
                        Logica.Logica.dameLogica().getListaCategorias().clear();
                        GestionPorArchivo cargar = new GestionPorArchivo();
                  
                        cargar.cargarActualizacion(path);
                        cargar.copiarArchivoActualizacion(path);
                        logger.info("Datos cargados de actualizacion");
                   
                        kill = true;
                    }
                }
            }


   }

/**
 * Escribe el archivo de apuestas no transmitidas en el pendrive
 */
    public void escribirArchivo(){
        files = File.listRoots();
//         if (files.length>getTotalDispositivos()){
//se compara el total de dispositivos actuales con el total de dispositivos al inicio del programa
            logger.info("TOTAL DISPOSITIVOS: "+getTotalDispositivos()+" FILE LENGTH: "+files.length);

               File archivoEscribir = new File(files[files.length-1],"apuestas.xml");
               GestionPorArchivo gestion = new GestionPorArchivo();
          
                gestion.copiarArchivoApuestas(archivoEscribir);
                logger.info("Archivos de apuestas copiados");
          
               kill = true;
               
//        }


    }
}