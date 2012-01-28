#!/usr/bin/python
# -*- coding: utf-8 -*-
"""
Modulo de pruebas realizado por: Juan Perozo, Gary Bustillos y Hector Sam
"""

import sys
import os
web2py_path = '/home/hector/Desktop/web2py_src/web2py'
sys.path.append(os.path.realpath(web2py_path))
os.chdir(web2py_path)

import unittest
import copy
from gluon.globals import *#Request, Response, Session
from gluon.shell import *#exec_environment
from gluon.storage import Storage

test_db = DAL('sqlite://testing.sqlite')
for tablename in db.tables:
    table_copy = [copy.copy(f) for f in db[tablename]]
    test_db.define_table(tablename, *table_copy)

class TestDefaultController(unittest.TestCase):
    def setUp(self):
        request = Request()
        request.application = "Casa_de_Apuestas"
        request.controller = "default"
        environment = env("Casa_de_Apuestas", import_models=True,c=request.controller)
        execfile('applications/Casa_de_Apuestas/controllers/default.py', environment) 
        self.controller = Storage(environment)
    
    def testCrearCategoria(self):
        control = Storage()
        self.controller.request.env.request_method = 'POST'
        self.controller.request.vars = Storage(nombre='Concurso de Belleza', 
        descripcion='Prueba del crear Categoria')
        self.controller.crearCategoria()
        form = self.controller.response._vars['form']
        self.assertEqual(form.errors, control)  
    
    def testCrearEvento(self):
        control = Storage()
        self.controller.request.env.request_method = 'POST'
        self.controller.request.vars = Storage(categoria_id = 27, 
        nombre = 'Fin del Mundo',fecha = '2012-12-21', 
        descripcion = 'Esperado por los Mayas', horaInicio = '12:00:00',
        admiteTablaResultado = False, permiteEmpate = False)
        self.controller.crearEvento()
        form = self.controller.response._vars['form']
        self.assertEqual(form.errors, control)
        
        
    def testCrearParticipante(self):
        control = Storage()
        self.controller.request.env.request_method = 'POST'
        self.controller.request.vars = Storage(nombre='Juan Perozo', 
        descripcion='Atletico e Inteligente', categoria_id='27')
        self.controller.crearParticipante()
        form = self.controller.response._vars['form']
        self.assertEqual(form.errors, control)
    
    
    def testCrearUsuario(self):
        control = Storage()
        self.controller.request.env.request_method = 'POST'
        self.controller.request.vars = Storage(nick = 'qckzr', 
        nombre = 'Hector', apellido = 'Sam', email = 'ssam_88@gmail.com', 
       password = 'infamee')
        self.controller.crearUsuario()
        form = self.controller.response._vars['form']
        self.assertEqual(form.errors, control)
        
    def testShowCategoria(self):
        control = Storage()
        self.controller.request.env.request_method = 'POST'
        self.controller.request.vars = Storage(nombre='Modificando Salon de Belleza', 
        descripcion='Probando la modificacion',id = 27)
        self.controller.showCategoria()
        form = self.controller.response._vars['form']
        self.assertEqual(form.errors, control)
    
    def testShowParticipante(self):
        control = Storage()
        self.controller.request.env.request_method = 'POST'
        self.controller.request.vars = Storage(id = 7,
        nombre='Modificando Leones del Caracas', descripcion='modificando al tremendo equipo', categoria_id='27')
        self.controller.showParticipante()
        form = self.controller.response._vars['form']
        self.assertEqual(form.errors, control)
    
    def testShowEvento(self):
        control = Storage()
        self.controller.request.env.request_method = 'POST'
        self.controller.request.vars = Storage(id =3, categoria_id = 29, 
        nombre = 'Modificando el Fin del Mundo',fecha = '2012-12-21', 
        descripcion = 'Esperado por los Mayas', horaInicio = '12:00:00',
        admiteTablaResultado = False, permiteEmpate = False)
        self.controller.showEvento()
        form = self.controller.response._vars['form']
        self.assertEqual(form.errors, control)
    
    def testShowUsuario(self):
        control = Storage()
        self.controller.request.env.request_method = 'POST'
        self.controller.request.vars = Storage(id = 14,nick = 'admin modificado', 
        nombre = 'HectoR', apellido = 'SaM', email = 'hfsam88@gmail.com', 
       password = 'infamee')
        self.controller.crearUsuario()
        form = self.controller.response._vars['form']
        self.assertEqual(form.errors, control)
        
    def testMostrarCategoria(self):
        pass
        
    def testMostrarEvento(self):
        pass
    
    def testMostrarParticipante(self):
        pass
    
    def testMostrarUsuario(self):
        pass    
        
    def verResultado1():
        pass
    def verResultado2():
        pass
        
    def mostrar():
        pass
    
    def verProximosEventos1():
        pass
    def verProximosEventos2():
        pass

def suite():

    suite = unittest.TestSuite()

    suite.addTest(unittest.makeSuite(TestDefaultController))

    return suite
    
if __name__ == '__main__':
    unittest.TextTestRunner(verbosity=2).run(suite())