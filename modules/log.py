#!/usr/bin/env python
# coding: utf8
from gluon import *
from datetime import *
from time import *

class log:
    """
    Constructor de la clase log.
    Se asigna un directorio de ubicacion del archivo
    """
    def __init__(self):
        self.__path = "C:/Python32/web2py/applications/Casa_de_Apuestas/static/bitacora.log"

    """
    Metodo encargado de abrir y escribir en el archivo. 
    En caso de no existir, lo crea
    
    @param info: Informacion que se va a guardar en la bitacora
    """
    def logear(self, info):
        try:
            f = open(self.__path, "a")
        except(IOError), e:
            f = open(self.__path, "w")
        f.write(strftime("%Y-%b-%d %H:%M:%S",localtime()) + " ---   " + info + "\n")
        f.close()
