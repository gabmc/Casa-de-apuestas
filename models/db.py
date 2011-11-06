# -*- coding: utf-8 -*-

#########################################################################
## This scaffolding model makes your app work on Google App Engine too
## File is released under public domain and you can use without limitations
#########################################################################

if not request.env.web2py_runtime_gae:     
    ## if NOT running on Google App Engine use SQLite or other DB
    db = DAL('sqlite://storage.sqlite')
    db.define_table('categorias',
        Field('nombre'),
        Field('descripcion', 'text'))
    db.define_table('eventos',    
       Field('categoria_id', db.categorias),
       Field('nombre'),
       Field('fecha','date'),
       Field('descripcion', 'text'),
       Field('relacionpago','string'))
  ##  db.image.title.requires = IS_NOT_IN_DB(db, db.image.title)
  
    db.define_table('participantes',
        Field('nombre'),
        Field('descripcion','text')
    )
    
    db.define_table('eventos_participantes',
        Field('eventos_id',db.eventos),
        Field('participantes_id',db.participantes),
        Field('limite_apuesta','integer'),
        primarykey=['eventos_id','participantes_id'],
        migrate=False

    )
    
    db.define_table('usuarios',
        Field('nombre'),
        Field('apellido'),
        Field('correo'),
        Field('password','password')
    )
    
    db.define_table('maquinas',
        Field('usuario_id',db.usuarios),
        Field('estado','boolean'),
        Field('ubicacion','string'),
        Field('anho','integer'),
        Field('descripcion','text')
    )
    
      
  
    db.eventos.categoria_id.requires = IS_IN_DB(db, db.categorias.id, '%(nombre)s')
    db.eventos.nombre.requires = IS_NOT_EMPTY()
    db.eventos.fecha.requires = IS_NOT_EMPTY()
    db.eventos.descripcion.requires = IS_NOT_EMPTY()
    db.eventos.relacionpago.requires = IS_NOT_EMPTY()
    
    
    db.participantes.nombre.requires = IS_NOT_EMPTY()
    db.participantes.descripcion.requires = IS_NOT_EMPTY()
    
    db.usuarios.nombre.requires = IS_NOT_EMPTY()
    db.usuarios.apellido.requires = IS_NOT_EMPTY()
    db.usuarios.correo.requires = IS_NOT_EMPTY()
    db.usuarios.correo.requires = IS_EMAIL()    
    db.usuarios.password.requires = IS_NOT_EMPTY()
    
    db.maquinas.usuario_id.requires = IS_IN_DB(db, db.usuarios.id, '%(correo)s')
    db.maquinas.estado.requires = IS_NOT_EMPTY()    
    db.maquinas.ubicacion.requires = IS_NOT_EMPTY()
    db.maquinas.anho.requires = IS_NOT_EMPTY()
    db.maquinas.descripcion.requires = IS_NOT_EMPTY() 
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

## create all tables needed by auth if not custom tables

########################################
db.define_table('auth_user',
    Field('username', type='string',
          label=T('Username')),
    Field('first_name', type='string',
          label=T('First Name')),
    Field('last_name', type='string',
          label=T('Last Name')),
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
    format='%(username)s',
    migrate=settings.migrate)


db.auth_user.first_name.requires = IS_NOT_EMPTY(error_message=auth.messages.is_empty)
db.auth_user.last_name.requires = IS_NOT_EMPTY(error_message=auth.messages.is_empty)
db.auth_user.password.requires = CRYPT(key=auth.settings.hmac_key)
db.auth_user.username.requires = IS_NOT_IN_DB(db, db.auth_user.username)
db.auth_user.registration_id.requires = IS_NOT_IN_DB(db, db.auth_user.registration_id)
db.auth_user.email.requires = (IS_EMAIL(error_message=auth.messages.invalid_email),
                               IS_NOT_IN_DB(db, db.auth_user.email))
auth.define_tables(migrate = settings.migrate) 

## configure email
mail=auth.settings.mailer
mail.settings.server = 'logging' or 'smtp.gmail.com:587'
mail.settings.sender = 'you@gmail.com'
mail.settings.login = 'username:password'

## configure auth policy
auth.settings.registration_requires_verification = False
auth.settings.registration_requires_approval = False
auth.settings.reset_password_requires_verification = True

## if you need to use OpenID, Facebook, MySpace, Twitter, Linkedin, etc.
## register with janrain.com, write your domain:api_key in private/janrain.key
from gluon.contrib.login_methods.rpx_account import use_janrain
use_janrain(auth,filename='private/janrain.key')

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