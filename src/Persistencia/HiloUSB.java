/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;



/**
 *
 * @author hector
 */
public class HiloUSB extends Thread {

    public static DriveTypeInfo dti;
    private  String accion;

    public HiloUSB(String archivo, javax.swing.JFrame ventana,String accionArealizar) {
        dti = new DriveTypeInfo(archivo,ventana);
        accion = accionArealizar;
    }



    @Override //empiezo el hilo xD
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
