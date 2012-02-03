#!/usr/bin/env python
# coding: utf8
from gluon import *
import hashlib

class DAOCasadeApuestas:

    """
    Constructor de la Clase DAO
    """
    def __init__(self):
        self.__codificador = hashlib.md5()
        
    """
    Genera un xml del resultado del query pasado por parametros
    
    @param rows: Las columnas generadas por un query
    """
    def generarFormatoXml(self, rows, tabla):
        idx=range(len(rows.colnames))
        colnames=[item.replace('.','_') for item in rows.colnames]
        records=[]
        for row in rows.response:
            records.append(TAG['record'](*[TAG[colnames[i]](row[i]) for i in idx]))
            records.append("\n")
        return str(TAG[tabla](*records))
        
    """
    Genera un hash MD5 del mensaje, resultado del query pasado por parametros
    
    @param rows: Las columnas generadas por un query
    """
       
    def generarMensajeMD5(self, rows):
  #      self.__codificador.update(self.generarFormatoXml(rows))
        return (self.__codificador.hexdigest())

    """
    Genera un hash MD5 del mensaje, resultado del query pasado por parametros
    
    @param eventos: hash MD5, correspondiente a la estructura XML de 
                    los eventos
           categorias: hash MD5, correspondiente a la estructura XML
                       de las categorias
           participantes: hash MD5, correspondiente a la estructura 
                          XML de las participantes
           eventosparticipantes: hash MD5, correspondiente a la 
                                 estructura XML de los eventosparticipantes
           usuarios: hash MD5, correspondiente a la estructura XML de las 
                     usuarios
    """
        
    def generarArchivoActualizacionMD5(self, eventos, categorias, participantes, eventosparticipantes, usuarios):
        f = open ("C:/Python32/web2py/applications/Casa_de_Apuestas/static/archivoActualizacion.md5", "w")
        f.write('<archivo>'+"\n")
        f.write(categorias + "\n")
        f.write(eventos + "\n")
        f.write(participantes + "\n")
        f.write(eventosparticipantes + "\n")
        f.write(usuarios + "\n")
        f.write('</archivo>'+"\n")
        f.close()
        from log import *
        l = log()
        l.logear("Archivo de actualización creado")
        return ("C:/Python32/web2py/applications/Casa_de_Apuestas/static/archivoActualizacion.md5")  
    
    """
    Genera un hash MD5 del mensaje, resultado del query pasado por parametros
    
    @param eventos: estructura XML correspondiente a los eventos
           categorias: estructura XML correspondiente a las categorias
           participantes: estructura XML correspondiente a los participantes
           eventosparticipantes: estructura XML correspondiente a los 
                                 eventosparticipantes
           usuarios: lestructura XML correspondiente a los usuarios
    """
        
    def generarArchivoActualizacionXML(self, eventos, categorias, participantes, eventosparticipantes, usuarios):
        f = open ("C:/Python32/web2py/applications/Casa_de_Apuestas/static/archivoActualizacion.xml", "w")
        f.write('<archivo>'+"\n")
        f.write(categorias + "\n")
        f.write(eventos + "\n")
        f.write(participantes + "\n")
        f.write(eventosparticipantes + "\n")
        f.write(usuarios + "\n")
        f.write('</archivo>'+"\n")
        f.close()
        from log import *
        l = log()
        l.logear("Archivo de actualización creado")
        return ("C:/Python32/web2py/applications/Casa_de_Apuestas/static/archivoActualizacion.xml")
        
        
        self.db = db
        self.rutaArchivo = rutaArchivo
        
    """
    Carga Apuestas desde el Archivo Xml Propio
    
    @param db: base de datos para hacer el insert de la apuesta
           rutaArchivo: ruta del Archivo que se utilizara para cargar las Apuestas
    """
        
    def cargarXmlApuestas(self,db,rutaArchivo):
        from xml.dom import minidom
        xml_documento = minidom.parse(rutaArchivo)
        nodos = xml_documento.childNodes
        lista = nodos[0].getElementsByTagName("apuesta")
        for nodo in lista: 
 #          print nodo.childNodes[1].firstChild.data
 #          print nodo.childNodes[3].firstChild.data
 #          print nodo.childNodes[5].firstChild.data
           monto = nodo.childNodes[7].firstChild.data
           fecha = nodo.childNodes[9].firstChild.data
           idEvento = nodo.childNodes[11].firstChild.data
           idMaquina = nodo.childNodes[15].firstChild.data
           idApuesta = db.apuestas.insert(montoApuesta = monto,fechaApuesta = fecha,maquina_id = idMaquina)
           lista_aux = nodo.getElementsByTagName("aposto_por")
           for apostados in lista_aux:
            lista_aux2 = apostados.getElementsByTagName("participante")
            for participante in lista_aux2:
#                   print participante.childNodes[1].firstChild.data
                   idParticipante = participante.childNodes[3].firstChild.data
                   db.e_a_p.insert(apuestasId = idApuesta,idParticipantes  = idParticipante, eventosId = idEvento)

    """
    Carga Apuestas desde el Archivo Xml Provisto por el otro equipo
    
    @param db: base de datos para hacer el insert de la apuesta
           rutaArchivo: ruta del Archivo que se utilizara para cargar las Apuestas
    """
    
    def cargarXmlApuestasAjeno(self,db,rutaArchivo):
        from xml.dom import minidom
        xml_documento = minidom.parse(rutaArchivo)
        nodos = xml_documento.childNodes
        apuestas = nodos[0].getElementsByTagName("Apuestas")    
        apuesta = apuestas[0].getElementsByTagName("Apuesta")    
        for nodo in apuesta:
            idApuesta = nodo.childNodes[1].firstChild.data
            monto = nodo.childNodes[3].firstChild.data
            fecha = nodo.childNodes[5].firstChild.data
            idMaquina = nodo.childNodes[7].firstChild.data
            db.apuestas.insert(montoApuesta = monto,fechaApuesta = fecha,maquina_id = idMaquina)
        posiciones = nodos[0].getElementsByTagName("Posiciones_tabla")
        posicion = posiciones[0].getElementsByTagName("Posicion_tabla")
        for nodo in posicion:
            pos = nodo.childNodes[1].firstChild.data
            idApuesta0 = nodo.childNodes[3].firstChild.data
            det = nodo.childNodes[5].firstChild.data
 #          idApuesta = db.apuestas.insert(montoApuesta = monto,fechaApuesta = fecha,maquina_id = idMaquina)
 #           for participante in lista_aux2:
 #                   print participante.childNodes[1].firstChild.data
 #                  idParticipante = participante.childNodes[3].firstChild.data
 #                  db.e_a_p.insert(apuestasId = idApuesta,idParticipantes  = idParticipante, eventosId = idEvento)
