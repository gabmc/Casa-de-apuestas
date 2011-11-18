#!/usr/bin/env python
# coding: utf8
from gluon import *

class Maquina:

    def __init__(self, idMaquina , estado, descripcion, ubicacion, lista):
        self.__idMaquina = idMaquina
        self.__estadoMaquina = estado
        self.__descripcionMaquina = descripcion
        self.__ubicacionMaquina = ubicacion
        self.__listaApuesta = lista
        
    def setIdMaquina(self, idMaquina):
        self.__idMaquina = idMaquina
        
    def getIdMaquina(self):
        return self.__idMaquina
        
    def setEstadoMaquina(self, estado):
        self.__estadoMaquina = estado
        
    def getEstadoMaquina(self):
        return self.__estadoMaquina
        
    def setDescripcionMaquina(self, descripcion):
        self.__descripcionMaquina = descripcion
        
    def getDescripcionMaquina(self):
        return self.__descripcionMaquina
        
    def setUbicacionMaquina(self, ubicacion):
        self.__ubicacionMaquina = ubicacion
        
    def getUbicacionMaquina(self):
        return self.__ubicacionMaquina
        
    def setListaApuesta(self, lista):
        self.__listaApuesta = lista
        
    def getListaApuesta(self):
        return self.__listaApuesta
