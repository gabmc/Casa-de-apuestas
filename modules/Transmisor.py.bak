#!/usr/bin/env python
# coding: utf8
from datetime import datetime

class Token:
    def __init__(self,id):
        self.__id = id
        self.__creacion = datetime.now().minute
    
    def getID(self):
        return self.__id

    def creacion(self):
        return self.__creacion
    
    def setCreacion(self,creacion):
        self.__creacion = creacion

    def actualizarCreacion(self):
        return (self.__creacion + 60)

class ListaTokens:
    def __init__(self,db):
        self.__lista = []
        tokens = db().select(db.tokens.ALL)
        for tok in tokens:
            nuevoToken = Token(tok.idT)
            nuevoToken.setCreacion(int(tok.Creacion))
            self.insertarToken(nuevoToken)

    def insertarToken(self, token):
        self.__lista += [token]

    def eliminarToken(self, pos, db):
        #self.__lista.remove(self.__lista[pos])
        db(db.tokens.idT==self.__lista[pos].getID()).delete()

    def recorrerToken(self):
        for i in range(0,len(self.__lista)):
            print(self.__lista[i].getID())
            
    def obtenerDimension(self,db):
        #llenarArreglo(db)
        return len(self.__lista)
        
    def llenarArreglo(self,db):
        tokens = db().select(db.tokens.ALL)
        for tok in tokens:
            nuevoToken = Token(tok.idT)
            nuevoToken.setCreacion(int(tok.Creacion))
            self.insertarToken(nuevoToken)

    def verificarToken(self, token, db):
        retorno = 1
        if(len(self.__lista)):
            for i in range(0,len(self.__lista)):
                if(self.__lista[i].getID() == token.getID()):
                    retorno = 0
                    break
            limite = token.creacion() - self.__lista[i].creacion()
            if ((limite > 5)or(limite < 0)):
                self.eliminarToken(i,db)
                return 1
            return retorno
