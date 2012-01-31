# -*- coding: utf-8 -*-

#########################################################################
## This scaffolding model makes your app work on Google App Engine too
## File is released under public domain and you can use without limitations
#########################################################################

if not request.env.web2py_runtime_gae:
    ## if NOT running on Google App Engine use SQLite or other DB
    db = DAL('sqlite://storage.sqlite')
   ## db = DAL('mysql://root:''@localhost/test')
    db.define_table('tokens',Field('idT'),Field('Creacion'))
   
    db.define_table('categorias',
        Field('nombre',label=T('Nombre Categoria')),
        Field('descripcion', 'text',label=T('Descripcion Categoria')))

    db.define_table('eventos',
       Field('categoria_id', db.categorias,label=T('Categoria')),
       Field('nombre',label=T('Nombre Evento')),
       Field('fecha','date',label=T('Fecha Evento')),
       Field('descripcion', 'text',label=T('Descripcion Evento')),
       Field('horaInicio','time',label=T('Hora Inicio')),
       Field('admiteTablaResultado','boolean',label=T('Admite tabla de Resultados')),
       Field('permiteEmpate','boolean',label=T('Permite Empate')))
  ##  db.image.title.requires = IS_NOT_IN_DB(db, db.image.title)

    db.define_table('participantes',
        Field('nombre',label=T('Nombre Participante')),
        Field('descripcion','text',label=T('Descripcion Participante')),
        Field('categoria_id', db.categorias, label=T('Categoria'))
    )

    db.define_table('eventosparticipantes',
        Field('eventos_id',db.eventos,label=T('Evento')),
        Field('participantes_id',db.participantes,label=T('Participante')),
        Field('relacionpago',label=T('Relacion/Pago')),
        Field('limite_apuesta','double',label=T('Limite Apuesta Bs')))
 #       primarykey=['eventos_id','participantes_id'],
#        migrate=False)
##MALDITA PK !!!, HABRA QUE PONERLE ID PARA QUE FUNCIONE, MALA PRACTICA :(
#    )

    db.define_table('resultados',
        Field('eventos_id',db.eventos,label=T('Eventos')),
        Field('participantes_id',db.participantes,label=T('Participante')),
        Field('puntaje_posicion','integer',label=T('Puntaje o Posicion'))
    )


    db.categorias.nombre.requires = IS_NOT_EMPTY()
    db.categorias.nombre.requires = IS_NOT_IN_DB(db, db.categorias.nombre)
    db.categorias.nombre.requires = IS_LENGTH(maxsize=25)

    db.categorias.descripcion.requires= IS_NOT_EMPTY()
    db.categorias.descripcion.requires= IS_LENGTH(maxsize=4000)

    db.eventos.categoria_id.requires = IS_IN_DB(db, db.categorias.id, '%(nombre)s')
    db.eventos.nombre.requires = IS_NOT_EMPTY()
    db.eventos.nombre.requires = IS_LENGTH(maxsize=25)
    db.eventos.fecha.requires = IS_NOT_EMPTY()
    db.eventos.descripcion.requires = IS_NOT_EMPTY()
    db.eventos.descripcion.requires = IS_LENGTH(maxsize=4000)
    db.eventos.nombre.requires = IS_NOT_IN_DB(db, db.eventos.nombre)


    db.participantes.nombre.requires = IS_NOT_EMPTY()
    db.participantes.nombre.requires = IS_LENGTH(maxsize=25)
    db.participantes.nombre.requires = IS_NOT_IN_DB(db, db.participantes.nombre)
    db.participantes.descripcion.requires = IS_NOT_EMPTY()
    db.participantes.descripcion.requires = IS_LENGTH(maxsize=4000)
    db.participantes.categoria_id.requires = IS_IN_DB(db, db.categorias.id, '%(nombre)s')

    db.eventosparticipantes.eventos_id.requires = IS_IN_DB(db, db.eventos.id, '%(nombre)s')
    db.eventosparticipantes.participantes_id.requires = IS_IN_DB(db, db.participantes.id, '%(nombre)s')
    db.eventosparticipantes.relacionpago.requires = IS_IN_SET(['1 a 1', '2 a 1', ' 3 a 1'])
    db.eventosparticipantes.limite_apuesta.requires = IS_FLOAT_IN_RANGE(0.1,1e10)

    db.resultados.eventos_id.requires = IS_IN_DB(db, db.eventos.id, '%(nombre)s')
    db.resultados.participantes_id.requires = IS_IN_DB(db, db.participantes.id, '%(nombre)s')

else:
    ## connect to Google BigTable (optional 'google:datastore://namespace')
    db = DAL('google:datastore')
    ## store sessions and tickets there
    session.connect(request, response, db = db)
    ## or store session in Memcache, Redis, etc.
    ## from gluon.contrib.memdb import MEMDB
    ## from google.appengine.api.memcache import Client
    ## session.connect(request, response, db = MEMDB(Client()))

## by default give a view/generic.extension to all actions from localhost
## none otherwise. a pattern can be 'controller/function.extension'
response.generic_patterns = ['*'] if request.is_local else []

#########################################################################
## Here is sample code if you need for
## - email capabilities
## - authentication (registration, login, logout, ... )
## - authorization (role based authorization)
## - services (xml, csv, json, xmlrpc, jsonrpc, amf, rss)
## - old style crud actions
## (more options discussed in gluon/tools.py)
#########################################################################

from gluon.tools import Auth, Crud, Service, PluginManager, prettydate
auth = Auth(db, hmac_key=Auth.get_or_create_key())
crud, service, plugins = Crud(db), Service(), PluginManager()

crud.messages.submit_button = 'Enviar'
crud.messages.delete_label = 'Click para Eliminar'
crud.messages.record_created = 'Registro Creado'
crud.messages.record_updated = 'Registro Actualizado'
crud.messages.record_deleted = 'Registro Eliminado'

## create all tables needed by auth if not custom tables

########################################

## if you need to use OpenID, Facebook, MySpace, Twitter, Linkedin, etc.
## register with janrain.com, write your domain:api_key in private/janrain.key
from gluon.contrib.login_methods.rpx_account import use_janrain
use_janrain(auth,filename='private/janrain.key')

db.define_table('auth_user',
    Field('nick', type='string',
          label=T('Nick')),
    Field('nombre', type='string',
          label=T('Nombre')),
    Field('apellido', type='string',
          label=T('Apellido')),
    Field('email', type='string',
          label=T('Email')),
    Field('password', type='password',
          readable=False,
          label=T('Password')),
    Field('created_on','datetime',default=request.now,
          label=T('Created On'),writable=False,readable=False),
    Field('modified_on','datetime',default=request.now,
          label=T('Modified On'),writable=False,readable=False,
          update=request.now),
    Field('registration_key',default='',
          writable=False,readable=False),
    Field('reset_password_key',default='',
          writable=False,readable=False),
    Field('registration_id',default='',
          writable=False,readable=False),
    format='%(nick)s',
    migrate=settings.migrate)



db.define_table('maquinas',
    Field('usuario_id',db.auth_user,requires=IS_NOT_EMPTY()),
    Field('estado','boolean',requires=IS_NOT_EMPTY()),
    Field('procesador','string',requires=IS_NOT_EMPTY()),
    Field('latitud','string',requires=IS_NOT_EMPTY()),
    Field('longitud','string',requires=IS_NOT_EMPTY()),
    Field('capacidadMemoriaRam','string',requires=IS_NOT_EMPTY()),
    Field('capacidadDiscoDuro','string',requires=IS_NOT_EMPTY()))

db.define_table('apuestas',
   Field ('montoApuesta','integer',requires=IS_NOT_EMPTY()),
   Field ('fechaApuesta','string',requires=IS_NOT_EMPTY()),
   Field ('eventos_id',db.eventos,requires=IS_NOT_EMPTY()),
   Field ('participantes_id',db.participantes,requires=IS_NOT_EMPTY()),
   Field ('maquina_id',db.maquinas,requires=IS_NOT_EMPTY())
   )
   
   
db.define_table('e_a_p',
   Field ('apuestaId',db.apuestas,requires=IS_NOT_EMPTY()),
   Field ('eventosId',db.eventos,requires=IS_NOT_EMPTY()),
   Field ('participantesId',db.participantes,requires=IS_NOT_EMPTY())
   )
   
db.auth_user.nombre.requires = IS_NOT_EMPTY(error_message=auth.messages.is_empty)
db.auth_user.nombre.requires = IS_LENGTH(maxsize=25)
db.auth_user.apellido.requires = IS_NOT_EMPTY(error_message=auth.messages.is_empty)
db.auth_user.apellido.requires = IS_LENGTH(maxsize=25)
db.auth_user.password.requires = CRYPT(key=auth.settings.hmac_key)
db.auth_user.password.requires = IS_LENGTH(maxsize=25)
db.auth_user.nick.requires = IS_NOT_IN_DB(db, db.auth_user.nick)
db.auth_user.nick.requires = IS_LENGTH(maxsize=25)
db.auth_user.registration_id.requires = IS_NOT_IN_DB(db, db.auth_user.registration_id)
db.auth_user.email.requires = (IS_EMAIL(error_message=auth.messages.invalid_email),
                               IS_NOT_IN_DB(db, db.auth_user.email))
auth.define_tables(migrate = settings.migrate)


   

   
db.apuestas.maquina_id.requires = IS_IN_DB(db, db.maquinas.id, '%(id)s')
db.apuestas.eventos_id.requires = IS_IN_DB(db, db.eventos.id, '%(nombre)s')
db.apuestas.participantes_id.requires = IS_IN_DB(db, db.participantes.id, '%(nombre)s')

db.e_a_p.participantesId.requires = IS_IN_DB(db, db.participantes.id, '%(nombre)s')
db.e_a_p.eventosId.requires = IS_IN_DB(db, db.eventos.id, '%(nombre)s')
db.e_a_p.apuestaId.requires = IS_IN_DB(db, db.apuestas.id, '%(fechaApuesta)s')

## configure email
mail=auth.settings.mailer
mail.settings.server = 'logging' or 'smtp.gmail.com:587'
mail.settings.sender = 'you@gmail.com'
mail.settings.login = 'username:password'

## configure auth policy
auth.settings.registration_requires_verification = False
auth.settings.registration_requires_approval = False
auth.settings.reset_password_requires_verification = True

#########################################################################
## Define your tables below (or better in another model file) for example
##
## >>> db.define_table('mytable',Field('myfield','string'))
##
## Fields can be 'string','text','password','integer','double','boolean'
##       'date','time','datetime','blob','upload', 'reference TABLENAME'
## There is an implicit 'id integer autoincrement' field
## Consult manual for more options, validators, etc.
##
## More API examples for controllers:
##
## >>> db.mytable.insert(myfield='value')
## >>> rows=db(db.mytable.myfield=='value').select(db.mytable.ALL)
## >>> for row in rows: print row.id, row.myfield
#########################################################################

mail.settings.server = settings.email_server
mail.settings.sender = settings.email_sender
mail.settings.login = settings.email_login
