# -*- coding: utf-8 -*-
### required - do no delete
def user(): return dict(form=auth())
def download(): return response.download(request,db)
def call(): return service()
### end requires
def index():
    return dict()

def error():
    return dict()
   
   
def maquinas():
    return dict()


def crearCategoria():
    categorias = db().select(db.categorias.ALL)
    form = crud.create(db.categorias)
<<<<<<< HEAD
    return dict(form=form, categorias=categorias)
=======
    return dict(form=form)

def crearEvento():
    form = crud.create(db.eventos)
    return dict(form=form)
    
def crearParticipantes():
    form = crud.create(db.participantes)
    return dict(form=form)

def crearEventosParticipantes():
    form = crud.create(db.evetos_participantes)
    return dict(form=form)

def crearUsuarios():
    form = crud.create(db.usuarios)
    return dict(form=form)
    
def crearMaquina():
    form = crud.create(db.maquinas)
    return dict(form=form)
>>>>>>> origin/origin
