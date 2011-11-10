from gluon import *

class Categoria:

    def __init__(self, nombre, descripcion):
        self.__nombre = nombre
        self.__descripcion = descripcion
        
    def setNombre(self, nombre):
        self.__nombre = nombre
        
    def getNombre(self):
        return self.__nombre
    
    def setDescripcion(self, descripcion):
        self.__descripcion = descripcion
    
    def getDescripcion(self):
        return self.__descripcion
    
    def getScore(self, participantes):
        pass
