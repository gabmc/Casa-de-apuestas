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

    public HiloUSB(String archivo, javax.swing.JFrame ventana) {
        dti = new DriveTypeInfo(archivo, ventana);
    }

    @Override //empiezo el hilo xD
    public void run() {
        while (true){

            dti.comenzar();
            if (dti.terminar()) break;
        }
    }





}
