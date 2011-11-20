#!/usr/bin/env python
# coding: utf8
from gluon import *
import hashlib

class DAOCasadeApuestas:

    def __init__(self):
        self.__codificador = hashlib.md5()
        
    def generarFormatoXml(self, rows):
        idx=range(len(rows.colnames))
        colnames=[item.replace('.','_') for item in rows.colnames]
        records=[]
        for row in rows.response:
            records.append(TAG['record'](*[TAG[colnames[i]](row[i]) for i in idx]))
        return str(TAG['records'](*records))
        
    def generarMensajeMD5(self, rows):
        self.__codificador.update(self.generarFormatoXml(rows))
        return (self.__codificador.hexdigest())
    
    def insertar(self):
        pass
        
    def eliminar(self):
        pass
        
    def seleccionar(self):
        pass
        
    def modificar(self):
        pass
