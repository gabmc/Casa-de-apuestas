#!/usr/bin/env python
# coding: utf8
from gluon import *

class Participante:
   def __init__(self,nombreParticipante,descripcionParticipante,limiteParticipante):
       self.nombre=nombre
       self.descripcion=descripcionParticipante
       self.limite=limiteParticipante
   
   def setNombre(self,nombreParticipante):
       self.nombre=nombreParticipante
   
   def getNombre(self):
       return self.nombre
   
   def setDescripcion(self,descripcionParticipante):
       self.descripcion=descripcionParticipante
   
   def getDescripcion(self):
       return self.descripcion
       
   def setLimite(self,limiteParticipante):
       self.limite=limiteParticipante
   
   def getLimite(self):
       return self.limite
