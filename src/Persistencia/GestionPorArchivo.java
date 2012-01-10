/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import Logica.Categoria;
import Logica.Evento;
import Logica.Logica;
import Logica.Participante;
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

    public void construirEvento(List elemento){
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
        Evento evento = new Evento(id, nombre, fecha, descripcion,
                horaInicio, admiteTabla, permiteEmpate);
        Logica.dameLogica().obtenerCategoriaPorId(idCategoria).getListaEventos()
                .add(evento);
    }

    public boolean cargarEventos(Element elemento){
        List elementosInternos = elemento.getChildren();
        Iterator iterator = elementosInternos.iterator();
        Element eventos = null;
        while(iterator.hasNext()){
            eventos = (Element) iterator.next();
            List evento = eventos.getChildren();
            construirEvento(evento);
        }
        return Boolean.TRUE;
    }

    public Categoria construirCategoria(List elemento){
         Iterator atributos = elemento.iterator();
         int id = 0;
         String nombre, descripcion;
         nombre = descripcion = "";
         while(atributos.hasNext()){
             Element categoria = (Element) atributos.next();
             if (categoria.getName().equals("categorias_id"))
                 id = Integer.parseInt(categoria.getText());
             if (categoria.getName().equals("categorias_nombre"))
                 nombre = categoria.getText();
             if (categoria.getName().equals("categorias_descripcion"))
                 descripcion = categoria.getText();
         }
        Categoria categoria = new Categoria(id, nombre, descripcion);
        return categoria;
    }

    public boolean cargarCategorias(Element elemento){
        List elementosInternos = elemento.getChildren();
        Iterator iterator = elementosInternos.iterator();
        Element categorias = null;
        while(iterator.hasNext()){
            categorias = (Element) iterator.next();
            List categoria = categorias.getChildren();
            Categoria nuevaCategoria = construirCategoria(categoria);
            Logica.dameLogica().getListaCategorias().add(nuevaCategoria);
        }
        return Boolean.TRUE;
    }

    public Participante construirParticipante(List elemento){
         Iterator atributos = elemento.iterator();
         int id, idCategoria;
         id = idCategoria = 0;
         String nombre, descripcion;
         nombre = descripcion = "";
         while(atributos.hasNext()){
             Element participante = (Element) atributos.next();
             if (participante.getName().equals("participantes_id"))
                 id = Integer.parseInt(participante.getText());
             if (participante.getName().equals("participantes_nombre"))
                 nombre = participante.getText();
             if (participante.getName().equals("participantes_descripcion"))
                 descripcion = participante.getText();
             if (participante.getName().equals("participantes_categoria_id"))
                 idCategoria = Integer.parseInt(participante.getText());
         }
        Participante part = new Participante(id, nombre, descripcion, idCategoria);
        return part;
    }

    public ArrayList<Participante> cargarParticipantes(Element elemento){
        List elementosInternos = elemento.getChildren();
        Iterator iterator = elementosInternos.iterator();
        Element participantes = null;
        ArrayList<Participante> part = new ArrayList<Participante>();
        while(iterator.hasNext()){
            participantes = (Element) iterator.next();
            List participante = participantes.getChildren();
            Participante nuevoParticipante = construirParticipante(participante);
            part.add((Participante) nuevoParticipante);
        }
        return part;
    }

    public Participante buscarParticipanteId(int id,
            ArrayList<Participante> participantes){
        Iterator iterator = participantes.iterator();
        Participante participante = null;
        while(iterator.hasNext()){
            participante = (Participante) iterator.next();
            if (participante.getId() == id)
                break;
            participante = null;
        }
        return participante;
    }

    public boolean construirParticipantesEventos(List partevs,
            ArrayList<Participante> participantes){
        Iterator atributos = partevs.iterator();
        int idEventos, idParticipantes;
        idEventos = idParticipantes = 0;
        while(atributos.hasNext()){
            Element partev = (Element)atributos.next();
            if(partev.getName().equals("eventosparticipantes_eventos_id"))
                idEventos = Integer.parseInt(partev.getText());
            if(partev.getName().equals("eventosparticipantes_participantes_id"))
                idParticipantes = 0;
        }
        Participante participante = buscarParticipanteId(idParticipantes,
                participantes);
        Logica.dameLogica().getEventoPorId(idEventos).getParticipantes()
                .add(participante);
        return Boolean.TRUE;
    }

    public boolean cargarParticipantesEventos(Element elemento,
            ArrayList<Participante> participantes){
        List elementosInternos = elemento.getChildren();
        Iterator iterator = elementosInternos.iterator();
        Element partev = null;
        while(iterator.hasNext()){
            partev = (Element)iterator.next();
            List partevs = partev.getChildren();
            construirParticipantesEventos(partevs, participantes);
        }
        return Boolean.TRUE;
    }

    public boolean cargarActualizacion(String path) throws FileNotFoundException,
            JDOMException, IOException{
        Element archivo = abrirArchivo(path);
        List elementos = archivo.getChildren();
        Iterator iterator = elementos.iterator();
        ArrayList<Participante> participantes = null;
        while(iterator.hasNext()){
            Element elementosInternos = (Element)iterator.next();
            if (elementosInternos.getName().equals("categorias"))
                cargarCategorias(elementosInternos);
            if (elementosInternos.getName().equals("eventos"))
                cargarEventos(elementosInternos);
            if (elementosInternos.getName().equals("participantes"))
                participantes = cargarParticipantes(elementosInternos);
            if (elementosInternos.getName().equals("eventos_participantes"))
                cargarParticipantesEventos(elementosInternos, participantes);
            //Hacer los demas que faltan, mismo algoritmo, puro trabajo de secretaria
        }
        return Boolean.TRUE;
    }
}
