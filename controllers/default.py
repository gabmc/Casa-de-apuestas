# -*- coding: utf-8 -*-
### required - do no delete
"""
Este Modulo corresponde al de controladores segun MVC, los metodos aqui contenidos
son los encargados de invocar a las vistas, eso esta configurado en el framework
"""
from gluon.tools import Service
servicioWeb = Service()
from log import *
log = log()

def user(): return dict(form=auth())
def download(): return response.download(request,db)
def call(): return service()

### end requires

def index():
    return dict()
    
@auth.requires_login()
def indexAdmin():
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
            response.flash = 'Categoria Creada'
        elif form.errors:
            response.flash = 'Error en el formulario'
        return response.render(dict(form=form, categorias=categorias))

def crearEvento():
        eventos = db().select(db.eventos.ALL)
        form = crud.create(db.eventos)
        #if form.accepts(request.vars, formname=None):
#        if form.process().accepted:
#            db.commit()        
#            from log import *
#            log = log()
#            log.logear("Evento creado: "+form.vars.nombre)
#            response.flash = 'Evento Creado'
#        elif form.errors:
#            response.flash = 'Error en el formulario'
        return response.render(dict(form=form,eventos=eventos))
        
def obtenerCategoria():
        if not request.vars.categorias:
            return ''
        patron = request.vars.categorias.capitalize() + '%'
        seleccionado = db(db.categorias.nombre.like(patron)).select()
        return ''.join([DIV(k.nombre,
                     _onclick="jQuery('#categorias').val('%s');" % k.nombre +
                     "document.getElementById('sugerencias').innerHTML = '';" +
                     "document.getElementById('eventos_categoria_id').value='%s';"%k.id,
                     _onmouseover="this.style.backgroundColor='yellow'",
                     _onmouseout="this.style.backgroundColor='white'"
                     ).xml() for k in seleccionado])

def crearParticipante():
        participantes = db().select(db.participantes.ALL)
        form = crud.create(db.participantes)
        #if form.accepts(request.vars, formname=None):
#        if form.process().accepted:
#            db.commit()        
#            from log import *
#            log = log()
#            log.logear("Participante creado: "+form.vars.nombre)
#            response.flash = 'Participante Creado'
#        elif form.errors:
#            response.flash = 'Error en el formulario'
        return response.render(dict(form=form,participantes=participantes))

def crearEventoParticipante():
        eventosparticipantes = db().select(db.eventosparticipantes.ALL)
        form = crud.create(db.eventosparticipantes)
        #if form.accepts(request.vars, formname=None):
#        if form.process().accepted:
#            db.commit()        
#            from log import *
#            log = log()
#            log.logear("EventoParticipante creado: "+form.vars.nombre)
#            response.flash = 'EventoParticipante Creado'
#        elif form.errors:
#            response.flash = 'Error en el formulario'
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
            response.flash = 'Usuario Creado'
        elif form.errors:
            response.flash = 'Error en el formulario'
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

def mostrarEventoParticipante():
        eventos = db().select(db.eventos.ALL, orderby = db.eventos.id)
        return response.render(dict(eventos= eventos))
        
    
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
        elif form.errors:
            response.flash = 'Error en el formulario'
        return response.render(dict(form = form))

def showCategoria():
        form =crud.update(db.categorias, request.args(0), next = URL('mostrarCategoria'))
        #if form.accepts(request.vars, formname=None):
        if form.process().accepted:
            db.commit()        
            from log import *
            log = log()
            log.logear("Categoria modificada: "+form.vars.nombre)          
        elif form.errors:
            response.flash = 'Error en el formulario'
        return response.render(dict(form = form))

def showParticipante():
        form = crud.update(db.participantes, request.args(0),next = URL('mostrarParticipante'))
        #if form.accepts(request.vars, formname=None):
        if form.process().accepted:
            db.commit()        
            from log import *
            log = log()
            log.logear("Participante modificado: "+form.vars.nombre)            
        elif form.errors:
            response.flash = 'Error en el formulario'
        return response.render(dict(form = form)) 

def showEventoParticipante1():
    participantes= db((db.eventosparticipantes.eventos_id == request.args(0)) & (db.participantes.id==db.eventosparticipantes.participantes_id)).select()
    return dict(participantes=participantes,evento_id=request.args(0))
    
def showEventoParticipante2():
    form = crud.update(db.eventosparticipantes,request.args(0),next=URL('mostrarEventoParticipante'))
    if form.process().accepted:
            db.commit()        
            from log import *
            log = log()
            log.logear("EventoParticipante modificado: "+form.vars.nombre)            
    elif form.errors:
            response.flash = 'Error en el formulario'
    return response.render(dict(form = form)) 
    
def showUsuario():
        form = crud.update(db.auth_user, request.args(0), next = URL('mostrarUsuario'))
        #if form.accepts(request.vars, formname=None):
        if form.process().accepted:
            db.commit()        
            from log import *
            log = log()
            log.logear("Usuario modificado: "+form.vars.nombre)           
        elif form.errors:
            response.flash = 'Error en el formulario'
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
#    q = dao.generarArchivoActualizacionMD5(dao.generarMensajeMD5(db().select(db.eventos.ALL)), dao2.generarMensajeMD5(db().select(db.categorias.ALL)), dao3.generarMensajeMD5(db().select(db.participantes.ALL)), dao4.generarMensajeMD5(db().select(db.eventosparticipantes.ALL)), dao5.generarMensajeMD5(db().select(db.auth_user.ALL)))
    s = dao.generarArchivoActualizacionXML(dao.generarFormatoXml(db().select(db.eventos.ALL),'eventos'), dao2.generarFormatoXml(db().select(db.categorias.ALL),'categorias'), dao3.generarFormatoXml(db().select(db.participantes.ALL),'participantes'), dao4.generarFormatoXml(db().select(db.eventosparticipantes.ALL),'eventosparticipantes'), dao5.generarFormatoXml(db().select(db.auth_user.ALL),'auth_user'))
#    return response.render(dict(q = q, s = s))   
    return response.render(dict(s = s))   
    
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

#Servicio Web

@servicioWeb.soap('enviarApuesta', returns={'result':bool},args={'listaApuestas':[{'apuesta':{'idEvento':int, 'idParticipante':int, 'montoApuesta':int, 'fechaApuesta':str, 'maquinaid':int}}]})
def enviarApuesta(listaApuestas):
    for i in range(0,len(listaApuestas)):
        db.apuestas.insert(eventos_id = listaApuestas[i]['apuesta']['idEvento'],participantes_id = listaApuestas[i]['apuesta']['idParticipante'],montoApuesta = listaApuestas[i]['apuesta']['montoApuesta'], fechaApuesta = listaApuestas[i]['apuesta']['fechaApuesta'], maquina_id = listaApuestas[i]['apuesta']['maquinaid'])
    return True

#@servicioWeb.soap('sencillo',returns={'result':str},args={'parameter':str})
#def sencillo(parameter):
#    return parameter
    
def call():
    return servicioWeb()
    
def moduloReportes():
    return dict()
    
def apuestasPorEventos1():
    import datetime
    fechaActual=datetime.datetime.now()
    eventos =db(db.eventos.fecha>fechaActual).select()
    return dict(eventos=eventos)

def apuestasPorEventos2():
    
    apuestas = db(db.apuestas.eventos_id==request.args(0)).select()
    evento = db(db.eventos.id==request.args(0)).select()
    from gluon.contrib.pyfpdf import FPDF, HTMLMixin
    import datetime
    fechaActual=datetime.date.today()

    class MyFPDF(FPDF, HTMLMixin):
        def header(this):
                # Logo
#                this.image('logo_pb.png',10,8,33) FALTA ACOMODAR UN LOGO JEJE
                # Arial bold 15
                this.set_font('Arial','B',15)
                this.cell(0,10,'Casa de Apuestas LOGO',0,1,'L')
                this.cell(0,10,'Fecha: '+str(fechaActual),0,1,'L')
                # Move to the right
                this.cell(80)
                # Title

                for i in evento:
                    this.cell(30,10,'Evento: '+i.nombre,0,2,'C')
                    this.cell(30,10,'Apuestas Realizadas',0,1,'C')
                # Line break
                this.ln(5)

        # Page footer
        def footer(this):
                # Position at 1.5 cm from bottom
                this.set_y(-15)
                # Arial italic 8
                this.set_font('Arial','I',8)
                # Page number
#                this.cell(0,10,'Page '+str(this.PageNo())+'/{nb}',0,0,'C')

# Instanciation of inherited class 
#    rows = [THEAD(TR(TH("Fecha Apuesta ",_width="70%"), TH("Monto Apuesta",_width="30%"), TH("Maquina ",_width="30%")))]4
    rows = THEAD(TR(TH("Fecha Apuesta ",_width="33%",_border="1"), TH("Monto Apuesta",_width="33%",_border="1"), TH("Maquina ",_width="33%",_border="1")))
    col = []
    cont = 0
    for i in apuestas:
        cont +=1
        bg = cont % 2 and "#F0F0F0" or "#FFFFFF"
        col.append(TR(TD(i.fechaApuesta,_width="33%",_align="center"),TD(i.montoApuesta, _align="center",_border="1"),TD(i.maquina_id, _align="center"),_bgcolor=bg,_border="1"))
    cuerpo =TBODY(*col)
    table = TABLE(_border="1",_align="center",_width="100%",*[rows,cuerpo])
    pdf = MyFPDF()
    pdf.alias_nb_pages()
    pdf.add_page()
    pdf.set_font('Times','',12)
    pdf.write_html(str(XML(table, sanitize=False)))

#    pdf.cell(0,10,rows,0,1)
#    pdf.cell(0,10,'Fecha Apuesta',0,0)
#    pdf.cell(60,10,'Monto Apuesta',0,0)
#    pdf.cell(0,10,'Maquina',0,1)
#    for i in apuestas:
#        pdf.cell(0,10,'Apuesta:'+str(i.fechaApuesta),0,1)
#        pdf.cell(0,10,'Apuesta:'+str(i.montoApuesta),0,1)
 #   pdf.output('apuestas.pdf','F')
        # prepare PDF to download:
    
    
    response.headers['Content-Type']='application/pdf'
    return pdf.output(dest='S')
    
  
    
def apuestasPorEventos3():
    
    response.title = "web2py sample report"
    
    # include a google chart (download it dynamically!)
    url = "http://chart.apis.google.com/chart?cht=p3&chd=t:60,40&chs=500x200&chl=Hello|World&.png"
    chart = IMG(_src=url, _width="250",_height="100")

    # create a small table with some data:
    rows = [THEAD(TR(TH("Key",_width="70%"), TH("Value",_width="30%"))),
            TBODY(TR(TD("Hello"),TD("60")), 
                  TR(TD("World"),TD("40")))]
    
    table = TABLE(*rows)

    from gluon.contrib.pyfpdf import FPDF, HTMLMixin

        # create a custom class with the required functionalities 
    class MyFPDF(FPDF, HTMLMixin):
            def header(self): 
                "hook to draw custom page header (logo and title)"
               # logo=os.path.join(request.env.web2py_path,"gluon","contrib","pyfpdf","tutorial","logo_pb.png")
              #  self.image(logo,10,8,33)
                self.set_font('Arial','B',15)
                self.cell(65) # padding
                self.cell(60,10,response.title,1,0,'C')
                self.ln(20)
                
            def footer(self):
                "hook to draw custom page footer (printing page numbers)"
                self.set_y(-15)
                self.set_font('Arial','I',8)
                txt = 'Pagina %s de %s' % (self.page_no(), self.alias_nb_pages())
                self.cell(0,10,txt,0,0,'C')
                    
    pdf=MyFPDF()
        # create a page and serialize/render HTML objects
    pdf.add_page()
    pdf.write_html(str(XML(table, sanitize=False)))
    pdf.write_html(str(XML(CENTER(chart), sanitize=False)))
        # prepare PDF to download:
    response.headers['Content-Type']='application/pdf'
    return pdf.output(dest='S')
    
def report():
    response.title = "web2py sample report"
    
    # include a google chart (download it dynamically!)
    url = "http://chart.apis.google.com/chart?cht=p3&chd=t:60,40&chs=500x200&chl=Hello|World&.png"
    chart = IMG(_src=url, _width="250",_height="100")

    # create a small table with some data:
    rows = [THEAD(TR(TH("Key",_width="70%"), TH("Value",_width="30%"))),
            TBODY(TR(TD("Hello"),TD("60")), 
                  TR(TD("World"),TD("40")))]
    table = TABLE(*rows)

    if request.extension=="pdf":
        from gluon.contrib.pyfpdf import FPDF, HTMLMixin

        # create a custom class with the required functionalities 
        class MyFPDF(FPDF, HTMLMixin):
            def header(self): 
                "hook to draw custom page header (logo and title)"
     #           logo=os.path.join(request.env.web2py_path,"gluon","contrib","pyfpdf","tutorial","logo_pb.png")
      #          self.image(logo,10,8,33)
                self.set_font('Arial','B',15)
                self.cell(65) # padding
                self.cell(60,10,response.title,1,0,'C')
                self.ln(20)
                
            def footer(self):
                "hook to draw custom page footer (printing page numbers)"
                self.set_y(-15)
                self.set_font('Arial','I',8)
                txt = 'Page %s of %s' % (self.page_no(), self.alias_nb_pages())
                self.cell(0,10,txt,0,0,'C')
                    
        pdf=MyFPDF()
        # create a page and serialize/render HTML objects
        pdf.add_page()
        pdf.write_html(str(XML(table, sanitize=False)))
        pdf.write_html(str(XML(CENTER(chart), sanitize=False)))
        # prepare PDF to download:
        response.headers['Content-Type']='application/pdf'
        return pdf.output(dest='S')
    else:
        # normal html view:
        return dict(chart=chart, table=table)
