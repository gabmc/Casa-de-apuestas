#!/usr/bin/env python
# coding: utf8
from gluon import *

class ExcepcionesPropias(Exception):
    
    def __init__(self,mensaje):
        self.mensaje = mensaje
        
    def __str__(self):
        return repr(self.mensaje)
    
class CategoriaError(ExcepcionesPropias):
    pass
