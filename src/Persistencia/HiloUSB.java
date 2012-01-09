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

    private DriveTypeInfo dti;

    public HiloUSB(String archivo) {
        dti = new DriveTypeInfo(archivo);
    }

    @Override //empiezo el hilo xD
    public void run() {
        while (true){

            dti.comenzar();
            if (dti.terminar()) break;
        }
    }





}
