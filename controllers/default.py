# -*- coding: utf-8 -*-
### required - do no delete
from Servicio import *
service = Servicio(db, crud)

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
    return service.crearCategoria()

def crearEvento():
    return service.crearEvento()

def crearParticipante():
    return service.crearParticipante()

def crearEventoParticipante():
    return service.crearEventoParticipante()

def crearUsuario():
    return service.crearUsuario()
    

def mostrarCategoria():
    return service.mostrarCategoria()

def mostrarEvento():
    return service.mostrarEvento()

def mostrarParticipante():
    return service.mostrarParticipante()

def mostrarEventosParticipantes():
    return service.mostrarEventosParticipantes()
    
def mostrarUsuario():
    return service.mostrarUsuario()
    
    
    
def showEvento():
    return service.showEvento(request)

def showCategoria():
    return service.showCategoria(request)

def showParticipante():
    return service.showParticipante(request)

def showEventoParticipante():
    return service.showEventoParticipante(request)
    
def showUsuario():
    return service.showUsuario(request)


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
    return dict(q = q, s = s)   
    
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
