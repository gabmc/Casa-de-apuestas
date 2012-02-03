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

public class GestionPorArchivoAjeno implements DaoXml {




    public  class DatosEventos{
        private boolean permiteEmpate;
        private boolean admiteTabla;
        private Integer idCategoria;

        public DatosEventos(boolean permiteEmpate, boolean admiteTabla, Integer idCategoria) {
            this.permiteEmpate = permiteEmpate;
            this.admiteTabla = admiteTabla;
            this.idCategoria = idCategoria;
        }

        public boolean isAdmiteTabla() {
            return admiteTabla;
        }

        public void setAdmiteTabla(boolean admiteTabla) {
            this.admiteTabla = admiteTabla;
        }

        public Integer getIdCategoria() {
            return idCategoria;
        }

        public void setIdCategoria(Integer idCategoria) {
            this.idCategoria = idCategoria;
        }

        public boolean isPermiteEmpate() {
            return permiteEmpate;
        }

        public void setPermiteEmpate(boolean permiteEmpate) {
            this.permiteEmpate = permiteEmpate;
        }




    }

    public  class RelacionPago{

        private int id;
        private float valor;

        public RelacionPago(int id, float  valor) {
            this.id = id;
            this.valor = valor;
        }



        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public float  getValor() {
            return valor;
        }

        public void setValor(float  valor) {
            this.valor = valor;
        }


    }




    private Document documento;
    static Logger logger = Logger.getLogger(GestionPorArchivoAjeno.class);
    private ArrayList<DatosEventos> datosEventos;
    private ArrayList<RelacionPago> relacionPago;

    public GestionPorArchivoAjeno() {
        PropertyConfigurator.configure("log4j.properties");
        datosEventos = new ArrayList<DatosEventos>();
        relacionPago = new ArrayList<RelacionPago>();
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
            if (eventoNuevo.getName().equals("Id"))
                id = Integer.parseInt(eventoNuevo.getText());
            if (eventoNuevo.getName().equals("Categoria"))
                idCategoria = Integer.parseInt(eventoNuevo.getText());
            if (eventoNuevo.getName().equals("Nombre"))
                nombre = eventoNuevo.getText();
            if (eventoNuevo.getName().equals("Fecha")){
                
                String fechaCompleta = eventoNuevo.getText();
                fecha = fechaCompleta.substring(0,9); //probar esto!
                horaInicio = fechaCompleta.substring(11,18);//igual
            }
            if (eventoNuevo.getName().equals("Descripcion"))
                descripcion = eventoNuevo.getText();

            //********************************************************************
            //
            if (eventoNuevo.getName().equals("Max_tabla")){
                if (eventoNuevo.getText().equals(""))
                    admiteTabla = Boolean.FALSE;
                else
                    admiteTabla = Boolean.TRUE;
            }
//ACA FALTA LO DE PERMITE EMPATE
//            if (eventoNuevo.getName().equals("eventos_permiteEmpate")){
//                if (eventoNuevo.getText().equals("F"))
//                    permiteEmpate = Boolean.FALSE;
//                else
//                    permiteEmpate = Boolean.TRUE;
//            }
        }
        for (DatosEventos datos : datosEventos) {
            if (datos.getIdCategoria()==idCategoria){
                permiteEmpate = datos.isPermiteEmpate();
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
         boolean permiteEmpate = Boolean.FALSE,admiteTabla = Boolean.FALSE;
         String nombre, descripcion,texto="";
         nombre = descripcion = "";
         while(atributos.hasNext()){
             Element categoriaNueva = (Element) atributos.next();
             if (categoriaNueva.getName().equals("Id"))
                 id = Integer.parseInt(categoriaNueva.getText());
             if (categoriaNueva.getName().equals("Nombre"))
                 nombre = categoriaNueva.getText();
             if (categoriaNueva.getName().equals("Descripcion"))
                 descripcion = categoriaNueva.getText();
             if (categoriaNueva.getName().equals("Dos_jugadores")){
                 texto = categoriaNueva.getText();
                 if (texto.contentEquals("No")) admiteTabla = Boolean.TRUE;
                 else admiteTabla = Boolean.FALSE;
             }
             if (categoriaNueva.getName().equals("Empate")){
                 texto = categoriaNueva.getText();
                 if (texto.contentEquals("No"))
                     permiteEmpate = Boolean.FALSE;
                 else permiteEmpate = Boolean.TRUE;
             }

         }
         //datosEventos.add(new DatosEventos(permiteEmpate, admiteTabla, id));
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
             if (participante.getName().equals("Id"))
                 id = Integer.parseInt(participante.getText());
             if (participante.getName().equals("Nombre"))
                 nombre = participante.getText();
             if (participante.getName().equals("Descripcion"))
                 descripcion = participante.getText();
             if (participante.getName().equals("Categoria"))
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
        int idEventos, idParticipantes,idPago,entero=0,decimal=0;
        float limiteApuesta=0;
        idEventos = idParticipantes = 0;
        String relacion_pago ="",numeroLeido;
        while(atributos.hasNext()){
            Element partev = (Element)atributos.next();
            if(partev.getName().equals("Evento"))
                idEventos = Integer.parseInt(partev.getText());
            if(partev.getName().equals("Monto_tope")){
                 numeroLeido = partev.getText();
                 if (numeroLeido.contentEquals("")==false){
                    if (numeroLeido.contains(",")){
                        entero = Integer.parseInt(numeroLeido.split(",")[0]);
                        decimal = Integer.parseInt(numeroLeido.split(",")[1]);
                        limiteApuesta = Float.parseFloat(entero+"."+decimal);
                    }
                    else limiteApuesta = Float.parseFloat(numeroLeido);

                }
            }
            if(partev.getName().equals("Participante"))
                idParticipantes = Integer.parseInt(partev.getText());
            if(partev.getName().equals("Pago")){
                idPago = Integer.parseInt(partev.getText());
                for (RelacionPago rp : relacionPago) {
                        if (rp.getId()==idPago){
                            relacion_pago=rp.getValor()+" a 1";
                            break;
                        }
                }
            }
        }
        //FALTA COLOCARLE LOS ATRIBUTOS DEL MONTO MAXIMO DE LA APUESTA!
        //EL MALDITO NOS DIO MAL EL ARCHIVO, NO EXISTEN NI EL EVENTO 1,2,3,10 =.=
        Participante participante = buscarParticipanteId(idParticipantes,
                participantes);
        Participante participanteClonado = clonarParticipante(participante);
        participanteClonado.setLimiteApuesta(limiteApuesta);
        participanteClonado.setRelacionPago(relacion_pago);
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
        String nick, nombre, apellido, password;
        nick = nombre = apellido = password = "";
        while(iterator.hasNext()){
            Element admin = (Element)iterator.next();
            if(admin.getName().equals("Id"))
                id = Integer.parseInt(admin.getText());
            if(admin.getName().equals("Nombre")){
                nick = admin.getText();
                nombre = admin.getText();
            }
            if(admin.getName().equals("auth_user_apellido"))
                apellido = admin.getText();
            if(admin.getName().equals("Contraseña"))
                password = admin.getText();
        }
        Administrador administrador = new Administrador(id, nick, nombre,
                apellido, password, nombre);
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
            if (elementosInternos.getName().equals("Categorias"))
                cargarCategorias(elementosInternos);
            if (elementosInternos.getName().equals("Eventos"))
                cargarEventos(elementosInternos);
            if (elementosInternos.getName().equals("Relacion_pagos"))
                cargarRelacionPagos(elementosInternos);
            if (elementosInternos.getName().equals("Participantes"))
                participantes = cargarParticipantes(elementosInternos);
            if (elementosInternos.getName().equals("Detalles_pe"))
                cargarParticipantesEventos(elementosInternos, participantes);
            if(elementosInternos.getName().equals("Usuarios"))
                cargarAdministradores(elementosInternos);
        }

        return Boolean.TRUE;
    }

    @Override
    public void copiarArchivoActualizacion(String path){
        try {
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
        catch (FileNotFoundException e){
            logger.error("Excepcion FileNotFoundException "+e.getMessage());
        }
        catch (IOException ex){
            logger.error("Excepcion I/O "+ex.getMessage());
        }

        }

    @Override
    public void copiarArchivoApuestas(File archivoDestino){
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
        catch (FileNotFoundException e){
            logger.error("Excepcion FileNotFoundException "+e.getMessage());
        }
        catch(IOException ex){
            logger.error("Excepcion I/O "+ex.getMessage());
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
       
        Apuesta a;
        while(iterator.hasNext()){
            Element elemInternos = (Element)iterator.next();
            if (elemInternos.getName().equals("apuesta")){
                a = construirApuesta(elemInternos.getChildren());
                Logica.dameLogica().getListaApuestas().add(a);

            }
        }
        }

     public boolean cargarRelacionPagos(Element elemento){
        List elementosInternos = elemento.getChildren();
        Iterator iterator = elementosInternos.iterator();
        Element relacionPagos = null;
        while(iterator.hasNext()){
            relacionPagos = (Element) iterator.next();
            List relacionPago1 = relacionPagos.getChildren();
            RelacionPago r = construirRelacionPago(relacionPago1);
            relacionPago.add(r);
        }
        return Boolean.TRUE;
    }
     
     public RelacionPago construirRelacionPago(List elemento){
         Iterator atributos = elemento.iterator();
         int id = 0,entero=0,decimal=0;
         float valor=0;
         String numeroLeido="";

         while(atributos.hasNext()){
             Element relacionPago1 = (Element) atributos.next();
             if (relacionPago1.getName().equals("Id"))
                 id = Integer.parseInt(relacionPago1.getText());
             if (relacionPago1.getName().equals("Valor")){
                 numeroLeido = relacionPago1.getText();
                 if (numeroLeido.contentEquals("")==false){
                 if (numeroLeido.contains(",")){
                 entero = Integer.parseInt(numeroLeido.split(",")[0]);
                 decimal = Integer.parseInt(numeroLeido.split(",")[1]);
                 valor = Float.parseFloat(entero+"."+decimal);
                 }
                 else valor = Float.parseFloat(numeroLeido);
             }
             }

         }
         //datosEventos.add(new DatosEventos(permiteEmpate, admiteTabla, id));
         RelacionPago relacionPago = new RelacionPago(id, valor);
        
        return relacionPago;
    }

}



