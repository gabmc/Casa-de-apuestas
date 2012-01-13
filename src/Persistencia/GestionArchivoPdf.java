/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.Apuesta;
import Logica.Logica;
import Logica.Participante;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.log4j.Logger;

/**
 *
 * @author Usuario
 */
public class GestionArchivoPdf {
    
        static Logger logger = Logger.getLogger(GestionArchivoPdf.class);

        /**
         * Genera un archivo Pdf externo.
         * @param La apuesta que se desea escribir
         */

        public void generarPdf(Apuesta apuesta){
                Document documento = new Document();
                documento.setPageSize(new Rectangle(400, 400));
                
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
            documento.add(new Paragraph("Aposto por:"));
            int pos = 1;
            for (Participante participantes : apuesta.getParticipantes()){
                documento.add(new Paragraph(pos + "- " + participantes.getNombre()));
                pos++;
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
