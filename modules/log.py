#!/usr/bin/env python
# coding: utf8
from gluon import *
from datetime import *
from time import *

class log:

    def logear(self, info):
        f = open("D:/bitacora.log", "a")
        f.write(strftime("%Y-%b-%d %H:%M:%S",localtime()) + " ---   " + info + "\n")
        f.close()