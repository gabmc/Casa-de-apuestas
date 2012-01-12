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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.jdom.JDOMException;


public class DriveTypeInfo{

    private int totalDispositivos;
    private File[] files;
    private boolean kill=false;
    private String archivo;
    JFrame ventana;

   //Obtengo el total de los dispositivos que hay en la computadora, ej: 2 disco duros, 1 cd rom
    public DriveTypeInfo(String archivo, JFrame ventana) {
        files = File.listRoots();
        setTotalDispositivos(files.length);
        System.out.println("TOTAL DISPOSITIVOS: "+getTotalDispositivos()+" FILE LENGTH: "+files.length);
        this.archivo=archivo;
        this.ventana = ventana;
        ventana.setVisible(true);
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
            } catch (InterruptedException ex) {
                Logger.getLogger(DriveTypeInfo.class.getName()).log(Level.SEVERE, null, ex);
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
            System.out.println("TOTAL DISPOSITIVOS: "+getTotalDispositivos()+" FILE LENGTH: "+files.length);

                File[] archivos = files[files.length-1].listFiles();
                
                for (File file : archivos) {

                    if (file.getName().contentEquals(archivo)){
                        String directorio = files[files.length-1].getPath();
                        String path = directorio + file.getName();
                        GestionPorArchivo cargar = new GestionPorArchivo();
                    try {
                        cargar.cargarActualizacion(path);
                        cargar.copiarArchivoActualizacion(path);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(DriveTypeInfo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JDOMException ex) {
                        Logger.getLogger(DriveTypeInfo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(DriveTypeInfo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        //System.out.println("ACA ESTA LO QUE HAY QUE LEER CON LA LIBRERIA JDOM!");
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
            System.out.println("TOTAL DISPOSITIVOS: "+getTotalDispositivos()+" FILE LENGTH: "+files.length);

               File archivoEscribir = new File(files[files.length-1],"apuestas.xml");
               GestionPorArchivo gestion = new GestionPorArchivo();
            try {
                gestion.copiarArchivoApuestas(archivoEscribir);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DriveTypeInfo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JDOMException ex) {
                Logger.getLogger(DriveTypeInfo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DriveTypeInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                archivoEscribir.createNewFile();
            } catch (IOException ex) {
                System.out.println(" EL LOG ACA");
            }
               kill = true;
               System.out.println(" LOG AGAIN");
        }


    }
}