from gluon import *

class Categoria:

    def __init__(self, nombre, descripcion):
        self.__nombre = nombre
        self.__descripcion = descripcion
        
    def setNombre(nombre):
        self.__nombre = nombre
        
    def getNombre():
        return self.__nombre
    
    def setDescripcion(descripcion):
        self.__descripcion = descripcion
    
    def getDescripcion():
        return self.__descripcion
    
    def getScore():
        pass
