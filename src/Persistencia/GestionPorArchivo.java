/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import Logica.Administrador;
import Logica.Apuesta;
import Logica.Categoria;
import Logica.Evento;
import Logica.Logica;
import Logica.Participante;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
/**
 *
 * @author hector
 */
public class GestionPorArchivo implements DaoXml {
    
    private Document documento;
    static Logger logger = Logger.getLogger(GestionPorArchivo.class);

    public GestionPorArchivo() {
        PropertyConfigurator.configure("log4j.properties");
    }

/**
 * Dada una dirección se intenta abrir el archivo.
 *
 * @param path de ubicación
 * @return El primer elemento del esquema XML
 */
    @Override
    public Element abrirArchivo(String path){
        SAXBuilder constructor = new SAXBuilder();
        try {
            documento = (Document) constructor.build(new FileInputStream(path));
        } catch (JDOMException ex) {
            logger.error("Excepcion JDOM "+ex.getMessage());
        } catch (IOException ex) {
            logger.error("Excepcion I/O "+ex.getMessage());
        }
        Element elemento = documento.getRootElement();
        logger.info("Se abrio el archivo en el path  "+path);
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
             Element categoriaNueva = (Element) atributos.next();
             if (categoriaNueva.getName().equals("categorias_id"))
                 id = Integer.parseInt(categoriaNueva.getText());
             if (categoriaNueva.getName().equals("categorias_nombre"))
                 nombre = categoriaNueva.getText();
             if (categoriaNueva.getName().equals("categorias_descripcion"))
                 descripcion = categoriaNueva.getText();
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
        float limiteApuesta=0;
        String relacionPago="";
        idEventos = idParticipantes = 0;
        while(atributos.hasNext()){
            Element partev = (Element)atributos.next();
            if(partev.getName().equals("eventosparticipantes_eventos_id"))
                idEventos = Integer.parseInt(partev.getText());
            if(partev.getName().equals("eventosparticipantes_participantes_id"))
                idParticipantes = Integer.parseInt(partev.getText());
            if(partev.getName().equals("eventosparticipantes_relacionago"))
                relacionPago = partev.getText();
            if(partev.getName().equals("eventosparticipantes_limite_apuesta"))
                limiteApuesta = Float.parseFloat(partev.getText());
        }
        Participante participante = buscarParticipanteId(idParticipantes,
                participantes);
        Participante participanteClonado = clonarParticipante(participante);
        participanteClonado.setLimiteApuesta(limiteApuesta);
        participanteClonado.setRelacionPago(relacionPago);
        Logica.dameLogica().getEventoPorId(idEventos).getParticipantes()
                .add(participanteClonado);
        return Boolean.TRUE;
    }

        public Participante clonarParticipante(Participante participante){
        Participante participanteClonado=null;
        try {
            participanteClonado= (Participante) participante.clone();
        }
        catch (CloneNotSupportedException e){
            logger.error("Error a clonar "+e.getMessage());
        }
        return participanteClonado;
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
    
    public Administrador construirAdministrador(List elemento){
        Iterator iterator = elemento.iterator();
        int id = 0;
        String nick, nombre, apellido, password, email;
        nick = nombre = apellido = password = email = "";
        while(iterator.hasNext()){
            Element admin = (Element)iterator.next();
            if(admin.getName().equals("auth_user_id"))
                id = Integer.parseInt(admin.getText());
            if(admin.getName().equals("auth_user_nick"))
                nick = admin.getText();
            if(admin.getName().equals("auth_user_nombre"))
                nombre = admin.getText();
            if(admin.getName().equals("auth_user_apellido"))
                apellido = admin.getText();
            if(admin.getName().equals("auth_user_password"))
                password = admin.getText();
            if(admin.getName().equals("auth_user_email"))
                email = admin.getText();
        }
        Administrador administrador = new Administrador(id, nick, nombre, 
                apellido, password,email);
        return administrador;
    }

    public boolean cargarAdministradores(Element elemento){
        List elementosInternos = elemento.getChildren();
        Iterator iterator = elementosInternos.iterator();
        Element administradores = null;
        while(iterator.hasNext()){
            administradores = (Element)iterator.next();
            List administrador = administradores.getChildren();
            Administrador nuevoAdministrador =
                    construirAdministrador(administrador);
            Logica.dameLogica().getListaAdministradores().add(nuevoAdministrador);
        }
        return Boolean.TRUE;
    }

    /**
     * Metodo que carga el archivo .xml de actualizacion del pendrive,
     * se reutiliza en caso de cargar el archivo de persistencia del repositorio
     * local
     *
     * @param path de dirección
     * @return True en caso de haber realizado la lectura
     */

    @Override
    public boolean cargarActualizacion(String path) {
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
            if (elementosInternos.getName().equals("eventosparticipantes"))
                cargarParticipantesEventos(elementosInternos, participantes);
            if(elementosInternos.getName().equals("auth_user"))
                cargarAdministradores(elementosInternos);
        }
        
        return Boolean.TRUE;
    }
    

    @Override
    public void copiarArchivoActualizacion(String path) {
        try{
            File origen = new File(path);
            File destino = new File("archivos/persistencia.xml");
            InputStream in = new FileInputStream(origen);
            OutputStream out = new FileOutputStream(destino);
        
            byte[] buf = new byte[2048];
            int len;
            while ((len = in.read(buf)) > 0){
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }
        catch (FileNotFoundException ex){
            logger.error("Excepcion FileNotFoundException "+ex.getMessage());
        }
        catch(IOException io){
            logger.error("Excepcion I/O "+io.getMessage());
        }
        }
    
    @Override
    public void copiarArchivoApuestas(File archivoDestino) {
        try{
            File origen = new File("archivos/apuestas.xml");
            InputStream in = new FileInputStream(origen);
            OutputStream out = new FileOutputStream(archivoDestino);
        
            byte[] buf = new byte[2048];
            int len;
            while ((len = in.read(buf)) > 0){
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }
        catch (FileNotFoundException f){
            logger.error("Excepcion FileNotFoundException "+f.getMessage());
        }
        catch(IOException e){
            logger.error("Excepcion I/O "+e.getMessage());
        }
       
    }
    
    public ArrayList<Participante> recibirParticipantes(List elemento){
        Iterator participantes = elemento.iterator();
        String nombre = "";
        int id = 0;
        ArrayList<Participante> listaParticipantes = new ArrayList();
        while(participantes.hasNext()){
            Element participante = (Element)participantes.next();
            List atributos = participante.getChildren();
            Iterator listAtributo = atributos.iterator();
            while(listAtributo.hasNext()){
                Element atributo = (Element)listAtributo.next();
                if(atributo.getName().equals("nombre"))
                    nombre = atributo.getText();
                if(atributo.getName().equals("id"))
                    id = Integer.parseInt(atributo.getText());
            }
            Participante par = new Participante(id, nombre, nombre, id);   //No guardas en ningun lado descripcion
            listaParticipantes.add(par);
        }
        return listaParticipantes;
    }
    
    public Apuesta construirApuesta(List elemento){
        Iterator atributos = elemento.iterator();
        int cedula, monto, idEvento, id;
        id = cedula = monto = idEvento = 0;
        String nombre, apellido;
        float montoFloat;
        Date fecha = new Date();
        nombre = apellido = "";
        ArrayList<Participante> listaParticipantes = new ArrayList();
        while(atributos.hasNext()){
            Element apuesta = (Element)atributos.next();
            if(apuesta.getName().equals("nombre_apostador"))
                nombre = apuesta.getText();
            if (apuesta.getName().equals("apellido_apostador"))
                apellido = apuesta.getText();
            if(apuesta.getName().equals("cedula_apostador"))
                cedula = Integer.parseInt(apuesta.getText());
            if(apuesta.getName().equals("monto")){
                montoFloat = Float.parseFloat(apuesta.getText());
                monto = (int) montoFloat;
            }
            if(apuesta.getName().equals("id_evento"))
                idEvento = Integer.parseInt(apuesta.getText());
            if(apuesta.getName().equals("aposto_por")){
                List part = apuesta.getChildren();
                listaParticipantes = recibirParticipantes(part);
            }
            if(apuesta.getName().equals("id_maquina"))
                id = Integer.parseInt(apuesta.getText());
        }
        Apuesta apuestaNueva = new Apuesta(nombre, apellido, cedula, monto, idEvento,
                listaParticipantes);
        return apuestaNueva;
    }
    
    public boolean cargarApuestas(Element elemento){
        List elemInternos = elemento.getChildren();
        Iterator iterator = elemInternos.iterator();
        Element apuestas = null;
        while(iterator.hasNext()){
            apuestas = (Element) iterator.next();
            List apuesta = apuestas.getChildren();
            Apuesta nuevaApuesta = construirApuesta(apuesta);
            
            Logica.dameLogica().getListaApuestas().add(nuevaApuesta);
        }
        return Boolean.TRUE;
    }
    
    /**
     * Carga las apuestas de un archivo .xml que se encuentra en el repositorio
     * interno de la aplicación
     * 
     * @param path de dirección
     */

    @Override
    public void cargarApuestasMemoria(String path){
        Element archivo = abrirArchivo(path);
        List elementos = archivo.getChildren();
        Iterator iterator = elementos.iterator();
        ArrayList<Participante> participantes = new ArrayList<Participante>();
        Apuesta a;
        while(iterator.hasNext()){
            Element elemInternos = (Element)iterator.next();
            if (elemInternos.getName().equals("apuesta")){
                a = construirApuesta(elemInternos.getChildren());
                Logica.dameLogica().getListaApuestas().add(a);
               
            }
        }
        }
}


