#!/usr/bin/env python
# coding: utf8
from gluon import *

class Participante:
   def __init__(self,nombreParticipante,descripcionParticipante,limiteParticipante):
       self.__nombre = nombre
       self.__descripcion = descripcionParticipante
       self.__limite = limiteParticipante
   
   def setNombre(self,nombreParticipante):
       self.__nombre = nombreParticipante
   
   def getNombre(self):
       return self.__nombre
   
   def setDescripcion(self,descripcionParticipante):
       self.__descripcion = descripcionParticipante
   
   def getDescripcion(self):
       return self.__descripcion
       
   def setLimite(self,limiteParticipante):
       self.__limite = limiteParticipante
   
   def getLimite(self):
       return self.__limite