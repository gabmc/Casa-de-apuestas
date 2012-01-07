#!/usr/bin/env python
# coding: utf8
from gluon.tools import Service
service = Service()

class ServicioWeb:

    def __init__(self,db):
        self.__db = db
    
    def obtenerServicio(self):
        return service()        
    
    @service.soap('enviarApuesta', returns={'result':str},args={'listaApuestas':[{'apuesta':{'idEvento':int, 'idParticipante':int, 'montoApuesta':int, 'fechaApuesta':str, 'maquinaid':id}}]})
    def enviarApuesta(listaApuestas):
        return 'hola'
