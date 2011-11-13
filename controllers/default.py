# -*- coding: utf-8 -*-
### required - do no delete
def user(): return dict(form=auth())
def download(): return response.download(request,db)
def call(): return service()
### end requires

@auth.requires_login()
def index():
    return dict()
    
@auth.requires_login()
def error():
    return dict()

@auth.requires_login()
def maquinas():
    return dict()

@auth.requires_login()
def crearCategoria():
    categorias = db().select(db.categorias.ALL)
    form = crud.create(db.categorias)
    return dict(form=form, categorias=categorias)

@auth.requires_login()
def crearEvento():
    form = crud.create(db.eventos)
    return dict(form=form)

@auth.requires_login()
def crearParticipantes():
    form = crud.create(db.participantes)
    return dict(form=form)

@auth.requires_login()
def crearEventosParticipantes():
    form = crud.create(db.evetos_participantes)
    return dict(form=form)

@auth.requires_login()
def crearUsuarios():
    form = crud.create(db.usuarios)
    return dict(form=form)

@auth.requires_login()
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

@auth.requires_login()
def eliminarCategoria():
    form = crud.delete(db.categorias)
    return dict(form=form)

@auth.requires_login()
def actualizarCategoria():
    form = crud.update(db.categorias,7)
    return dict(form=form)

@auth.requires_login()
def mostrarCategoria():
    categorias= db().select(db.categorias.ALL, orderby=db.categorias.id)
    return dict(categorias=categorias)

@auth.requires_login()
def showCategoria():
    form = crud.update(db.categorias,request.args(0),next=URL('mostrarCategoria'))
    return dict(form=form)
