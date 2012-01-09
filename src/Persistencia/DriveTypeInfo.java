/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

/**
 *
 * @author hector
 */
import java.io.*;


public class DriveTypeInfo{

    private int totalDispositivos;
    private File[] files;
    private boolean kill=false;
    private String archivoAbuscar;

   //Obtengo el total de los dispositivos que hay en la computadora, ej: 2 disco duros, 1 cd rom
    public DriveTypeInfo(String archivoAbuscar) {
        files = File.listRoots();
        setTotalDispositivos(files.length);
        System.out.println("TOTAL DISPOSITIVOS: "+getTotalDispositivos()+" FILE LENGTH: "+files.length);
        this.archivoAbuscar=archivoAbuscar;
    }

    public int getTotalDispositivos() {
        return totalDispositivos;
    }

    public void setTotalDispositivos(int totalDispositivos) {
        this.totalDispositivos = totalDispositivos;
    }

    public boolean terminar(){

        if (kill)
            return true;
        else
            return false;
    }

    public void comenzar(){
//ahora obtengo nuevamente los dispositivos en la computadora una vez iniciado el hilo
       files = File.listRoots();
       
       if (files.length>getTotalDispositivos()){
//se compara el total de dispositivos actuales con el total de dispositivos al inicio del programa
            System.out.println("TOTAL DISPOSITIVOS: "+getTotalDispositivos()+" FILE LENGTH: "+files.length);


                File[] archivos = files[files.length-1].listFiles();
                
                for (File file : archivos) {

                    if (file.getName().contentEquals(archivoAbuscar)){

                        System.out.println(file.getName());
                        System.out.println("ACA ESTA LO QUE HAY QUE LEER CON LA LIBRERIA JDOM!");
                        kill=true;
                    }
                }
            }



   }



 
      
}
