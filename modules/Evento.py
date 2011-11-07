#!/usr/bin/env python
# coding: utf8
from gluon import *

class Evento:

    def __init__(self, nombre, fecha, descripcion):
        self.__nombre = nombre
        self.__fecha = fecha
        self.__descripcion = descripcion
    
    def setNombre(nombre):
        self.__nombre = nombre
    
    def setFecha(fecha):
        self.__fecha = fecha
        
    def setDescripcion(descripcion):
        self.__descrupcion = descripcion
        
    def getNombre():
        return self.__nombre
    
    def getFecha():
        return self.__fecha
        
    def getDescripcion():
        return self.__descripcion
