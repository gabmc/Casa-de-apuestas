#!/usr/bin/env python
# coding: utf8
from gluon import *
from log import *
from gluon.globals import *

class Servicio:

    """
    @param baseDeDatos: baseDeDatos recibida
                        para realizacion de querys
           crud:        variable global del framework
                        que genera formularios
                                         
    @attrib: baseDeDatos, crud, log
    
    Constructor de la Clase Servicio.
    """
    def __init__(self, baseDeDatos, crud):
        self.__baseDeDatos = baseDeDatos
        self.__crud = crud
        self.__log = log()
              
    """                                   
    Obtiene de la Base de Datos las Categorias.
    Asimismo, genera el formulario asociado
    """
    def crearCategoria(self):
        categorias = self.__baseDeDatos().select(self.__baseDeDatos.categorias.ALL)
        form = self.__crud.create(self.__baseDeDatos.categorias)
        if form.process().accepted:
            self.__log.logear("Categoría creada: "+form.vars.nombre)
        elif form.errors:
            self.__log.logear("Error creando categoría")
        return dict(form=form, categorias=categorias)
        
    """
    Obtiene de la Base de Datos los Eventos.
    Asimismo, genera el formulario asociado
    """        
    def crearEvento(self):
        eventos = self.__baseDeDatos().select(self.__baseDeDatos.eventos.ALL)
        form = self.__crud.create(self.__baseDeDatos.eventos)
        if form.process().accepted:
            log.logear("Evento creado")
        elif form.errors:
            log.logear("Error creando evento")
        return dict(form=form,eventos=eventos)
    
    """
    Obtiene de la Base de Datos los Participantes.
    Asimismo, genera el formulario asociado
    """            
    def crearParticipante(self):
        participantes = self.__baseDeDatos().select(self.__baseDeDatos.participantes.ALL)
        form = self.__crud.create(self.__baseDeDatos.participantes)
        if form.process().accepted:
            self.__log.logear("Participante creado")
        elif form.errors:
            self.__log.logear("Error creando participante")
        return dict(form=form,participantes=participantes)
        
    """
    Obtiene de la Base de Datos los Eventos y 
    Participantes, correspondientes a la Categoria.
    Asimismo, genera el formulario asociado
    """            
    def crearEventoParticipante(self):
        eventosparticipantes = self.__baseDeDatos().select(self.__baseDeDatos.eventosparticipantes.ALL)
        form = self.__crud.create(self.__baseDeDatos.eventosparticipantes)
        if form.process().accepted:
            self.__log.logear("EventoParticipante creado")
        elif form.errors: 
            self.__log.logear("Error creando eventoparticipante")
        return dict(form=form,eventosparticipantes=eventosparticipantes)
        
    """
    Obtiene de la Base de Datos los Usuarios registrados.
    Asimismo, genera el formulario asociado.
    """
    def crearUsuario(self):
        usuarios = self.__baseDeDatos().select(self.__baseDeDatos.auth_user.ALL)
        form = self.__crud.create(self.__baseDeDatos.auth_user)
        if form.process().accepted:
            self.__log.logear("Usuario creado")
        elif form.errors:
            self.__log.logear("Error creando usuario")
        return dict(form=form,usuarios=usuarios)
        
    def mostrarParticipante(self):
        participantes = self.__baseDeDatos().select(self.__baseDeDatos.participantes.ALL, orderby = self.__baseDeDatos.participantes.id)
        return dict(participantes = participantes)
    
    def mostrarEvento(self):
        eventos = self.__baseDeDatos().select(self.__baseDeDatos.eventos.ALL, orderby = self.__baseDeDatos.eventos.id)
        return dict(eventos = eventos)
        
    def mostrarCategoria(self):
        categorias = self.__baseDeDatos().select(self.__baseDeDatos.categorias.ALL, orderby = self.__baseDeDatos.categorias.id)
        return dict(categorias = categorias)
        
    def mostrarEventosParticipantes(self):
        eventosparticipantes = self.__baseDeDatos().select(self.__baseDeDatos.eventosparticipantes.ALL,orderby = self.__baseDeDatos.eventosparticipantes.id)
        return dict(eventosparticipantes= eventosparticipantes)
    
    def mostrarUsuario(self):
        usuarios = self.__baseDeDatos().select(self.__baseDeDatos.auth_user.ALL, orderby = self.__baseDeDatos.auth_user.id)
        return dict(usuarios = usuarios)
        
    def showCategoria(self, request):
        form = self.__crud.update(self.__baseDeDatos.categorias, request.args(0), next = URL('mostrarCategoria'))
        return dict(form = form)
        
    def showEvento(self, request):
        form = self.__crud.update(self.__baseDeDatos.eventos, request.args(0),next = URL('mostrarEvento'))
        return dict(form = form)
        
    def showParticipante(self, request):
        form = self.__crud.update(self.__baseDeDatos.participantes, request.args(0),next = URL('mostrarParticipante'))
        return dict(form = form)
        
    def showUsuario(self, request):
        form = self.__crud.update(self.__baseDeDatos.auth_user, request.args(0), next = URL('mostrarUsuario'))
        return dict(form = form)
