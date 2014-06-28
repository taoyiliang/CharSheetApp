#-------------------------------------------------
#
# Project created by QtCreator 2014-06-27T19:09:15
#
#-------------------------------------------------

QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = #DnDCharacterSheets
TEMPLATE = app
DEPENDPATH += ../CharLib
INCLUDEPATH += ../CharLib
LIBS +=  -L../CharLib/debug -ltestLib
DEFINES += CHARACTERLIBRARY_LIBRARY

SOURCES += main.cpp\
        mainwindow.cpp

HEADERS  += mainwindow.h

FORMS    += mainwindow.ui
