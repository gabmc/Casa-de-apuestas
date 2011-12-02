#!/usr/bin/env python
# coding: utf8
from gluon import *
from datetime import *
from time import *

class log:
    def __init__(self):
        self.__path = "C:/Python32/web2py_win/web2py/applications/Casa_de_Apuestas/static/bitacora.log"

    def logear(self, info):
        try:
            f = open(self.__path, "a")
        except(IOError), e:
            f = open(self.__path, "w")
        f.write(strftime("%Y-%b-%d %H:%M:%S",localtime()) + " ---   " + info + "\n")
        f.close()
