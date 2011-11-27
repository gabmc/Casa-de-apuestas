# -*- coding: utf-8 -*-
### required - do no delete
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
    form =crud.create(db.maquinas)
    maquinas=db().select(db.maquinas.ALL)
    return dict(form=form, maquinas=maquinas)


def crearCategoria():
    categorias = db().select(db.categorias.ALL)
    form = crud.create(db.categorias)
    return dict(form=form, categorias=categorias)

def crearEvento():
    eventos= db().select(db.eventos.ALL)
    form = crud.create(db.eventos)
    return dict(form=form,eventos=eventos)

def crearParticipante():
    participantes= db().select(db.participantes.ALL)
    form = crud.create(db.participantes)
    return dict(form=form,participantes=participantes)

def crearEventoParticipante():
    eventosparticipantes= db().select(db.eventos_participantes.ALL)
    form = crud.create(db.eventos_participantes)
    return dict(form=form,eventosparticipantes=eventosparticipantes)

def crearUsuario():
    usuarios= db().select(db.auth_user.ALL)
    form = crud.create(db.auth_user)
    return dict(form=form,usuarios=usuarios)

def crearMaquina():
    maquinas= db().select(db.maquinas.ALL)
    form = crud.create(db.maquinas)
    return dict(form=form,maquinas=maquinas)

def prueba_xml():
    from DAOCasadeApuestas import *
    #response.headers['Content-Type']='application/xml'
    archivo = DAOCasadeApuestas()
    var = archivo.generarMensajeMD5(db().select(db.participantes.ALL))
    #a = generar_xml(db().select(db.participantes.ALL));
    #codificador.update(generar_xml(db().select(db.participantes.ALL)))
    #return generar_xml(db().select(db.participantes.ALL))
    return dict(var=var)

def mostrarCategoria():
    categorias= db().select(db.categorias.ALL, orderby=db.categorias.id)
    return dict(categorias=categorias)

def showCategoria():
    form = crud.update(db.categorias,request.args(0),next=URL('mostrarCategoria'))
    return dict(form=form)


def mostrarEvento():
    eventos= db().select(db.eventos.ALL, orderby=db.eventos.id)
    return dict(eventos=eventos)

def showEvento():
    form = crud.update(db.eventos,request.args(0),next=URL('mostrarEvento'))
    return dict(form=form)


def mostrarParticipante():
    participantes= db().select(db.participantes.ALL, orderby=db.participantes.id)
    return dict(participantes=participantes)

def showParticipante():
    form = crud.update(db.participantes,request.args(0),next=URL('mostrarParticipante'))
    return dict(form=form)
    
def mostrarMaquina():
    maquinas= db().select(db.maquinas.ALL, orderby=db.maquinas.id)
    return dict(maquinas=maquinas)

def showMaquinas():
    form = crud.update(db.maquinas,request.args(0),next=URL('mostrarMaquina'))
    return dict(form=form)
    
def mostrarUsuario():
    usuarios= db().select(db.auth_user.ALL, orderby=db.auth_user.id)
    return dict(usuarios=usuarios)

def showUsuario():
    form = crud.update(db.auth_user,request.args(0),next=URL('mostrarUsuario'))
    return dict(form=form)

def verResultado1():
    eventos= db().select(db.eventos.ALL, orderby=db.eventos.id)
    return dict(eventos=eventos)
        
def verResultado2():
    resultados= db((db.resultados.eventos_id==request.args(0))& (db.resultados.participantes_id==db.participantes.id)).select()
    return dict(resultados=resultados)
