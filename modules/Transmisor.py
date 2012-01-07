#!/usr/bin/env python
# coding: utf8
from gluon.tools import Service
service = Service()

class ServicioWeb:

    def __init__(self,db):
        self.__db = db
