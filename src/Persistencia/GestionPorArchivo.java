/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import Logica.Evento;
import Logica.Logica;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
/**
 *
 * @author hector
 */
public class GestionPorArchivo {
    
    private Document documento;

    public Element abrirArchivo(String path) throws FileNotFoundException,
            JDOMException, IOException{
        SAXBuilder constructor = new SAXBuilder();
        documento = (Document) constructor.build(new FileInputStream(path));
        Element elemento = documento.getRootElement();
        return elemento;
    }

    public Evento construirEvento(List elemento){
        Iterator atributos = elemento.iterator();
        String nombre, fecha, descripcion,horaInicio;
        nombre = fecha = descripcion = horaInicio = "";
        int id, idCategoria;
        id = idCategoria = 0;
        boolean admiteTabla, permiteEmpate;
        admiteTabla = permiteEmpate = false;
        while(atributos.hasNext()){
            Element eventoNuevo = (Element) atributos.next();
            if (eventoNuevo.getName().equals("eventos_id"))
                id = Integer.parseInt(eventoNuevo.getText());
            if (eventoNuevo.getName().equals("eventos_categoria_id"))
                idCategoria = Integer.parseInt(eventoNuevo.getText());
            if (eventoNuevo.getName().equals("eventos_nombre"))
                nombre = eventoNuevo.getText();
            if (eventoNuevo.getName().equals("eventos_fecha"))
                fecha = eventoNuevo.getText();
            if (eventoNuevo.getName().equals("eventos_descripcion"))
                descripcion = eventoNuevo.getText();
            if (eventoNuevo.getName().equals("eventos_horaInicio"))
                horaInicio = eventoNuevo.getText();
            if (eventoNuevo.getName().equals("eventos_admiteTablaResultado")){
                if (eventoNuevo.getText().equals("F"))
                    admiteTabla = Boolean.FALSE;
                else
                    admiteTabla = Boolean.TRUE;
            }
            if (eventoNuevo.getName().equals("eventos_permiteEmpate")){
                if (eventoNuevo.getText().equals("F"))
                    permiteEmpate = Boolean.FALSE;
                else
                    permiteEmpate = Boolean.TRUE;
            }
        }
        Evento evento = new Evento(id, idCategoria, nombre, fecha, descripcion,
                horaInicio, admiteTabla, permiteEmpate);
        return evento;
    }

    public boolean cargarEventos(Element elemento){
        List elementosInternos = elemento.getChildren();
        Iterator iterator = elementosInternos.iterator();
        Element eventos = null;
        String prueba = "";
        String prueba1 = "";
        int cont = 0;
        while(iterator.hasNext()){
            eventos = (Element) iterator.next();
            List evento = eventos.getChildren();
            Evento nuevoEvento = construirEvento(evento);
            Logica.dameLogica().getListaEventos().add(nuevoEvento);
        }
        return Boolean.TRUE;
    }

    public boolean cargarActualizacion(String path) throws FileNotFoundException,
            JDOMException, IOException{
        Element archivo = abrirArchivo(path);
        List elementos = archivo.getChildren();
        Iterator iterator = elementos.iterator();
        while(iterator.hasNext()){
            Element elementosInternos = (Element)iterator.next();
            if (elementosInternos.getName().equals("eventos"))
                cargarEventos(elementosInternos);
            //Hacer los demas que faltan, mismo algoritmo, puro trabajo de secretaria
        }
        return Boolean.TRUE;
    }
}
