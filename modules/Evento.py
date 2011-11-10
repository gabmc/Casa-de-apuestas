#!/usr/bin/env python
# coding: utf8
from gluon import *

class Evento:

    def __init__(self, nombre, fecha, descripcion):
        self.__nombre = nombre
        self.__fecha = fecha
        self.__descripcion = descripcion
    
    def setNombre(self, nombre):
        self.__nombre = nombre
    
    def setFecha(self, fecha):
        self.__fecha = fecha
        
    def setDescripcion(self, descripcion):
        self.__descrupcion = descripcion
        
    def getNombre(self):
        return self.__nombre
    
    def getFecha(self):
        return self.__fecha
        
    def getDescripcion(self):
        return self.__descripcion
