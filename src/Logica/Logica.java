/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Usuario
 */
public class Logica {
    
    private int id;
    private ArrayList<Administrador> listaAdministradores;
    private ArrayList<Categoria> listaCategorias;
    private ArrayList<Apuesta> listaApuestas;
    private int idCategoriaActual;
    private int idEventoActual;
    static Logger logger = Logger.getLogger(Logica.class);
    
    private Logica(){
        listaAdministradores = new ArrayList();
        listaCategorias = new ArrayList();
        listaApuestas = new ArrayList();
        id = 9;
        PropertyConfigurator.configure("log4j.properties");
    }
    
    private static Logica instancia;
    
    public static Logica dameLogica(){
        if (instancia == null)
            instancia = new Logica();
        return instancia;     
    }

    public ArrayList<Administrador> getListaAdministradores() {
        return listaAdministradores;
    }

    public ArrayList<Apuesta> getListaApuestas() {
        return listaApuestas;
    }

    public ArrayList<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setID(int id){
        this.id = id;
    }

    public int getID(){
        return id;
    }

    public boolean insertarApuestaLocal(Apuesta apuesta){
        return listaApuestas.add(apuesta);
    }

    public Categoria obtenerCategoriaPorId(int id){
        Iterator iterator = listaCategorias.iterator();
        Categoria categoria = null;
        while(iterator.hasNext()){
            categoria = (Categoria)iterator.next();
            if (categoria.getId() == id)
                logger.info("Se obtuvo la categoria con el id:"+id);
                break;
        }
        return categoria;
    }

    public Categoria obtenerCategoriaPorString(String nombre){
        Iterator iterator = listaCategorias.iterator();
        Categoria categoria = null;
        while(iterator.hasNext()){
            categoria = (Categoria)iterator.next();
            if (categoria.getNombre().equals(nombre))
                logger.info("Se obtuvo la categoria con el nombre:"+nombre);
                break;
        }
        return categoria;
    }
    
    public Evento obtenerEventoPorString(String nombre, int idCategoria){
        Iterator iterator = listaCategorias.iterator();
        Categoria categoria = null;
        while(iterator.hasNext()){
            categoria = (Categoria)iterator.next();
            if (categoria.getId() == idCategoria)
                break;
        }
        Evento evento = null;
        Iterator iterator2 = categoria.getListaEventos().iterator();
        while (iterator2.hasNext()){
            evento = (Evento)iterator2.next();
            if (evento.getNombre().equals(nombre))
                logger.info("Se obtuvo el evento con el nombre:"+nombre);
                break;
        }
        return evento;
    }
    
    public Participante obtenerParticipantePorString(String nombre, int idEvento,
            int idCategoria){
        Iterator iterator = listaCategorias.iterator();
        Categoria categoria = null;
        while(iterator.hasNext()){
            categoria = (Categoria)iterator.next();
            if (categoria.getId() == idCategoria)
                break;
        }
        Evento evento = null;
        Iterator iterator2 = categoria.getListaEventos().iterator();
        while (iterator2.hasNext()){
            evento = (Evento)iterator2.next();
            if (evento.getId() == idEvento)
                break;
        }
        Participante participante = null;
        Iterator iterator3 = evento.getParticipantes().iterator();
        while (iterator3.hasNext()){
            participante = (Participante)iterator3.next();
            if (participante.getNombre().equals(nombre))
                logger.info("Se obtuvo el participante con el nombre:"+nombre);
                break;
        }
        return participante;
    }


    public Evento getEventoPorId(int id){
        Iterator iterator = listaCategorias.iterator();
        Categoria categoria = null;
        Evento evento = null;
        while(iterator.hasNext()){
            categoria = (Categoria) iterator.next();
            if(categoria.getEventoPorId(id) != null){
                evento = categoria.getEventoPorId(id);
                logger.info("Se obtuvo el evento con el id:"+id);
                break;
            }
            evento = null;
        }
        return evento;
    }

    public Evento getEventoPorNombre(String nombre){
        Iterator iterator = listaCategorias.iterator();
        Categoria categoria = null;
        Evento evento = null;
        while(iterator.hasNext()){
            categoria = (Categoria) iterator.next();
            if(categoria.getEventoPorNombre(nombre) != null){
                evento = categoria.getEventoPorNombre(nombre);
                logger.info("Se obtuvo el evento con el nombre:"+nombre);
                break;
            }
            evento = null;
        }
        return evento;
    }

    public void setIdCategoriaActual(int idCategoriaActual) {
        this.idCategoriaActual = idCategoriaActual;
    }

    public void setIdEventoActual(int idEventoActual) {
        this.idEventoActual = idEventoActual;
    }

    public int getIdCategoriaActual() {
        return idCategoriaActual;
    }

    public int getIdEventoActual() {
        return idEventoActual;
    }
    
    public void agregarApuesta(Apuesta apuesta){
        listaApuestas.add(apuesta);
    }
    /*
     * MAMAGUEVOS, LES FALTO EL ID DE LA APUESTA =.=!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
    public void generarPdf(Apuesta apuesta){
                Document documento = new Document();
        try {
            PdfWriter.getInstance(documento, new FileOutputStream("archivos/factura.pdf"));
            documento.open();
            documento.add(new Paragraph("FACTURA DE APUESTA"));
            documento.add(new Paragraph("Nombre del apostador: " + 
                    apuesta.getNombreApostador() + " " + apuesta.getApellidoApostador()));
            documento.add(new Paragraph("Cédula de Identidad: " + apuesta.getCedulaApostador()));
            documento.add(new Paragraph("Monto: Bs. " + apuesta.getMontoApuesta()));
            documento.add(new Paragraph("Hecho el: " + apuesta.getFechaApuesta()));
            documento.add(new Paragraph("Evento: " + 
                    Logica.dameLogica().getEventoPorId(apuesta.getIdEvento()).getNombre()));
            for (Participante participantes : apuesta.getParticipantes()){
                documento.add(new Paragraph("Aposto por:" + participantes.getNombre()));
            }
            documento.add(new Paragraph("Apuesta hecha en la máquina: " + apuesta.getIdMaquina()));
        } catch (DocumentException de) {
            logger.error("Excepcion en el documento: "+de.getMessage());
        } catch (IOException ioe) {
            logger.error("Error de Entrada / Salida: "+ioe.getMessage());
        }
        documento.close();
        try {
          File path = new File("archivos/factura.pdf");
          Desktop.getDesktop().open(path);
          logger.info("Se genero la factura para la apuesta");
} catch (IOException ex) {
     logger.error("Error de entrada/salida");
}
    }
}
