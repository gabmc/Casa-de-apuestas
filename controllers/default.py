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
    return dict()


def crearCategoria():
    categorias = db().select(db.categorias.ALL)
    form = crud.create(db.categorias)
    return dict(form=form, categorias=categorias)

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

def generar_xml(rows):
    idx=range(len(rows.colnames))
    colnames=[item.replace('.','_') for item in rows.colnames]
    records=[]
    for row in rows.response:
        records.append(TAG['record'](*[TAG[colnames[i]](row[i]) for i in idx]))
    return str(TAG['records'](*records))
    #return str(estrin)

def prueba_xml():
    import hashlib
    codificador = hashlib.md5()
    response.headers['Content-Type']='application/xml'
    #a = generar_xml(db().select(db.participantes.ALL));
    codificador.update(generar_xml(db().select(db.participantes.ALL)))
    #return generar_xml(db().select(db.participantes.ALL))
    return dict(var=codificador.hexdigest(), var1=codificador1.hexdigest())

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
