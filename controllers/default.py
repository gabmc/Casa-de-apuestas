# -*- coding: utf-8 -*-
### required - do no delete
"""
Este Modulo corresponde al de controladores segun MVC, los metodos aqui contenidos
son los encargados de invocar a las vistas, eso esta configurado en el framework
"""
from Servicio import *
service = Servicio(db, crud)
from log import *
log = log()

def user(): return dict(form=auth())
def download(): return response.download(request,db)
def call(): return service()

### end requires

@auth.requires_login()
def index():
    return dict()

def error():
    return dict()

def maquinas():
    form = crud.update(db.maquinas, request.args(0),next=URL('maquinas'))
    maquinas = db().select(db.maquinas.ALL)
    coordenadas = False
    if (request.args(0)):
        for i, maquina in enumerate(maquinas):
            if (i == request.args(0)):
                break
        coordenadas = dict(lon=maquina.longitud,lat=maquina.latitud)                    
    return dict(form=form, maquinas=maquinas, coordenadas=coordenadas)

def crearCategoria():
        categorias = db().select(db.categorias.ALL)
        form = crud.create(db.categorias)
        #if form.accepts(request.vars, formname=None):
        if form.process().accepted:
            db.commit()        
            from log import *
            log = log()
            log.logear("Categoria creada: "+form.vars.nombre)
        return response.render(dict(form=form, categorias=categorias))

def crearEvento():
        eventos = db().select(db.eventos.ALL)
        form = crud.create(db.eventos)
        #if form.accepts(request.vars, formname=None):
        if form.process().accepted:
            db.commit()        
            from log import *
            log = log()
            log.logear("Evento creado: "+form.vars.nombre)
        return response.render(dict(form=form,eventos=eventos))

def crearParticipante():
        participantes = db().select(db.participantes.ALL)
        form = crud.create(db.participantes)
        #if form.accepts(request.vars, formname=None):
        if form.process().accepted:
            db.commit()        
            from log import *
            log = log()
            log.logear("Participante creado: "+form.vars.nombre)
        return response.render(dict(form=form,participantes=participantes))

def crearEventoParticipante():
        eventosparticipantes = db().select(db.eventosparticipantes.ALL)
        form = crud.create(db.eventosparticipantes)
        #if form.accepts(request.vars, formname=None):
        if form.process().accepted:
            db.commit()        
            from log import *
            log = log()
            log.logear("EventoParticipante creado: "+form.vars.nombre)
        return response.render(dict(form=form,eventosparticipantes=eventosparticipantes))

def crearUsuario():
        usuarios = db().select(db.auth_user.ALL)
        form = crud.create(db.auth_user)
        #if form.accepts(request.vars, formname=None):
        if form.process().accepted:
            db.commit()        
            from log import *
            log = log()
            log.logear("Usuario creado: "+form.vars.nombre)
        return response.render(dict(form=form,usuarios=usuarios))
    

def mostrarCategoria():
        categorias = db().select(db.categorias.ALL, orderby = db.categorias.id)
        return response.render(dict(categorias = categorias))

def mostrarEvento():
        eventos = db().select(db.eventos.ALL, orderby = db.eventos.id)
        return response.render(dict(eventos = eventos))

def mostrarParticipante():
        participantes = db().select(db.participantes.ALL, orderby = db.participantes.id)
        return response.render(dict(participantes = participantes))

def mostrarEventosParticipantes():
        eventosparticipantes = db().select(db.eventosparticipantes.ALL,orderby = db.eventosparticipantes.id)
        return response.render(dict(eventosparticipantes= eventosparticipantes))
    
def mostrarUsuario():
        usuarios = db().select(db.auth_user.ALL, orderby = db.auth_user.id)
        return response.render(dict(usuarios = usuarios))
    
    
    
def showEvento():
        form = crud.update(db.eventos, request.args(0),next = URL('mostrarEvento'))
        #if form.accepts(request.vars, formname=None):
        if form.process().accepted:
            db.commit()        
            from log import *
            log = log()
            log.logear("Evento modificado: "+form.vars.nombre)           
        return response.render(dict(form = form))

def showCategoria():
        form =crud.update(db.categorias, request.args(0), next = URL('mostrarCategoria'))
        #if form.accepts(request.vars, formname=None):
        if form.process().accepted:
            db.commit()        
            from log import *
            log = log()
            log.logear("Categoria modificada: "+form.vars.nombre)          
        return response.render(dict(form = form))

def showParticipante():
        form = crud.update(db.participantes, request.args(0),next = URL('mostrarParticipante'))
        #if form.accepts(request.vars, formname=None):
        if form.process().accepted:
            db.commit()        
            from log import *
            log = log()
            log.logear("Participante modificado: "+form.vars.nombre)            
        return response.render(dict(form = form)) 

def showEventosParticipantes():
    return service.showEventoParticipante(request)
    
def showUsuario():
        form = crud.update(db.auth_user, request.args(0), next = URL('mostrarUsuario'))
        #if form.accepts(request.vars, formname=None):
        if form.process().accepted:
            db.commit()        
            from log import *
            log = log()
            log.logear("Usuario modificado: "+form.vars.nombre)           
        return response.render(dict(form = form))


def verResultado1():
    import datetime
    fechaActual=datetime.datetime.now()
    eventos =db(db.eventos.fecha <fechaActual).select()
    return dict(eventos=eventos)
        
def verResultado2():
    resultados= db((db.resultados.eventos_id==request.args(0))& (db.resultados.participantes_id==db.participantes.id)).select()
    evento=db(db.eventos.id==request.args(0)).select()
    return dict(resultados=resultados,evento=evento)
    
def mostrar():
    from DAOCasadeApuestas import *
    dao = DAOCasadeApuestas()
    dao2 = DAOCasadeApuestas()
    dao3 = DAOCasadeApuestas()
    dao4 = DAOCasadeApuestas()
    dao5 = DAOCasadeApuestas()
    q = dao.generarArchivoActualizacionMD5(dao.generarMensajeMD5(db().select(db.eventos.ALL)), dao2.generarMensajeMD5(db().select(db.categorias.ALL)), dao3.generarMensajeMD5(db().select(db.participantes.ALL)), dao4.generarMensajeMD5(db().select(db.eventosparticipantes.ALL)), dao5.generarMensajeMD5(db().select(db.auth_user.ALL)))
    s = dao.generarArchivoActualizacionXML(dao.generarFormatoXml(db().select(db.eventos.ALL)), dao2.generarFormatoXml(db().select(db.categorias.ALL)), dao3.generarFormatoXml(db().select(db.participantes.ALL)), dao4.generarFormatoXml(db().select(db.eventosparticipantes.ALL)), dao5.generarFormatoXml(db().select(db.auth_user.ALL)))
    return response.render(dict(q = q, s = s))   
    
def verProximosEventos1():
    import datetime
    fechaActual=datetime.datetime.now()
    eventos =db(db.eventos.fecha>fechaActual).select()
    return dict(eventos=eventos)
    
def verProximosEventos2():
    evento_participante= db((db.eventosparticipantes.eventos_id==request.args(0))& (db.participantes.id==db.eventosparticipantes.participantes_id)).select()
    evento = db(db.eventos.id==request.args(0)).select()
    categoria = db((db.categorias.id==db.eventos.categoria_id)& (db.eventos.id==request.args(0))).select()
    return dict(evento_participante=evento_participante,evento=evento,categoria=categoria)
    
def indexPublico():
    return dict()
