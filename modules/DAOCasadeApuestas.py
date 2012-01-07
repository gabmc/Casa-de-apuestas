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
        f.write(eventos + "\n")
        f.write(categorias + "\n")
        f.write(participantes + "\n")
        f.write(eventosparticipantes + "\n")
        f.write(usuarios + "\n")
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
        f.write(eventos + "\n")
        f.write(categorias + "\n")
        f.write(participantes + "\n")
        f.write(eventosparticipantes + "\n")
        f.write(usuarios + "\n")
        f.close()
        from log import *
        l = log()
        l.logear("Archivo de actualización creado")
        return ("C:/Python32/web2py/applications/Casa_de_Apuestas/static/archivoActualizacion.xml")
