/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Usuario
 */
public class Apuesta {
    private String nombreApostador;
    private String apellidoApostador;
    private int cedulaApostador;
    private int montoApuesta;  //Volver a Float
    private Date fechaApuesta;
    private int idEvento;
    private ArrayList<Participante> Participantes;
    private int idMaquina;
    static Logger logger = Logger.getLogger(Apuesta.class);

    public Apuesta(String nombreApostador, String apellidoApostador, 
            int cedulaApostador, int montoApuesta, int idEvento
            , ArrayList<Participante> idsParticipantes) {
        this.nombreApostador = nombreApostador;
        this.apellidoApostador = apellidoApostador;
        this.cedulaApostador = cedulaApostador;
        this.montoApuesta = montoApuesta;
        this.fechaApuesta = Calendar.getInstance().getTime();
        this.idEvento = idEvento;
        this.Participantes = idsParticipantes;
        this.idMaquina = Logica.dameLogica().getID();
         PropertyConfigurator.configure("log4j.properties");
    }

    public String getApellidoApostador() {
        return apellidoApostador;
    }

    public void setApellidoApostador(String apellidoApostador) {
        this.apellidoApostador = apellidoApostador;
    }

    public int getCedulaApostador() {
        return cedulaApostador;
    }

    public void setCedulaApostador(int cedulaApostador) {
        this.cedulaApostador = cedulaApostador;
    }

    public Date getFechaApuesta() {
        return fechaApuesta;
    }

//    public String getFechaApuestaString(){
//        String fecha = String.valueOf(fechaApuesta.get(Calendar.YEAR)) + "-";
//        if (fechaApuesta.get(Calendar.MONTH) < 9)
//            fecha += "0" + String.valueOf(fechaApuesta.get(Calendar.MONTH)+1) + "-";
//        else
//            fecha += String.valueOf(fechaApuesta.get(Calendar.MONTH)+1) + "-";
//        if (fechaApuesta.get(Calendar.DATE) < 10)
//            fecha += "0" + String.valueOf(fechaApuesta.get(Calendar.DATE));
//        else
//            fecha += String.valueOf(fechaApuesta.get(Calendar.DATE));
//        return fecha;
//    }
    
    public void setFechaApuesta() {
        this.fechaApuesta = Calendar.getInstance().getTime();
    }

    public float getMontoApuesta() {
        return montoApuesta;
    }

    public void setMontoApuesta(int montoApuesta) {
        this.montoApuesta = montoApuesta;
    }

    public String getNombreApostador() {
        return nombreApostador;
    }

    public void setNombreApostador(String nombreApostador) {
        this.nombreApostador = nombreApostador;
    }
    
     public int getIdEvento() {
        return idEvento;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public ArrayList<Participante> getParticipantes() {
        return Participantes;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public void setIdsParticipantes(ArrayList<Participante> idParticipante) {
        this.Participantes = idParticipante;
    }

    public void setFechaApuesta(Date fechaApuesta) {
        this.fechaApuesta = fechaApuesta;
    }
    
    public void guardarApuesta(ArrayList<Apuesta> apuestas){
        
            Element root = new Element("apuestas");
            java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
            for (Apuesta apuestafor : apuestas){
                Element apuesta = new Element("apuesta");
                apuesta.addContent(new Element ("nombre_apostador").addContent(apuestafor.getNombreApostador()));
                apuesta.addContent(new Element("apellido_apostador").addContent(apuestafor.getApellidoApostador()));
                apuesta.addContent(new Element("cedula_apostador").addContent(String.valueOf(apuestafor.getCedulaApostador())));
                apuesta.addContent(new Element("monto").addContent(String.valueOf(apuestafor.getMontoApuesta())));
                apuesta.addContent(new Element("fecha").addContent(sdf.format(apuestafor.getFechaApuesta())));
                apuesta.addContent(new Element("id_evento").addContent(String.valueOf(apuestafor.getIdEvento())));
                for (Participante participantefor : apuestafor.getParticipantes()){
                    Element participantes = new Element("aposto_por");
                    Element participante = new Element("participante");
                    Element nombre = new Element("nombre").addContent(participantefor.getNombre());
                    Element id = new Element("id").addContent(String.valueOf(participantefor.getId()));
                    participante.addContent(nombre);
                    participante.addContent(id);
                    participantes.addContent(participante);
                    
                    apuesta.addContent(participantes);
                }
                apuesta.addContent(new Element("id_maquina").addContent(String.valueOf(Logica.dameLogica().getID())));
                
                root.addContent(apuesta);              
            }
             
        Document docSalida = new Document(root);
        try {
                XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
                FileWriter writer = new FileWriter("archivos/apuestas.xml");
                outputter.output(docSalida, writer);
                writer.close();
                logger.info("Se genero el archivo apuestas.xml en el directorio archivos");

            } catch (java.io.IOException e) {
                e.printStackTrace();
                logger.error("Problema con I/O al escribir el xml");
            } catch (Exception e) {
                logger.error("Problema al escribir el xml");
            }
        
    }

    
}
