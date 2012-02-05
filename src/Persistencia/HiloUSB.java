/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;



/**
 *Clase que ejecuta un thread en el sistema para esperar la inserccion de un
 * usb (pendrive) en el cual se leera o escribir un archivo
 *
 * @author hector
 */
public class HiloUSB extends Thread {

    public static GestionPendrive dti;
    private  String accion;

    /**
     * Constructor de la clase, se indica el archivo a leer en el pendrive,
     * una ventana de inicio y una accion a realizar que puede ser "LEER"
     * o "ESCRIBIR"
     *
     * @param archivo es el nombre del archivo que se desea  leer o escribir
     * @param ventana es la ventana actual que se esta ejecutando en la aplicacion
     * @param accionArealizar puede tener dos valores: "LEER" o "ESCRIBIR"
     */
    public HiloUSB(javax.swing.JFrame ventana,String accionArealizar) {
        dti = new GestionPendrive(ventana);
        accion = accionArealizar;
    }
    
    public HiloUSB(String accionArealizar){
        dti = new GestionPendrive();
        accion = accionArealizar;
    }


/**
 * Metodo encargado de correr el hilo, en el cual se evalua que accion a evaluar
 * indico el usuario y el metodo de dti al que se llama
 */

    @Override 
    public void run() {
        while (true){

          if(accion.contentEquals("LEER")){
                dti.comenzar();

          }
          else if(accion.contentEquals("ESCRIBIR")){

                    dti.escribirArchivo();

          }

          if (dti.terminar()) break;
        }
    }
}
