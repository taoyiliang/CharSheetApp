#-------------------------------------------------
#
# Project created by QtCreator 2014-07-01T10:29:33
#
#-------------------------------------------------

QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = CharSheets
TEMPLATE = app

CONFIG += c++11

SOURCES += main.cpp\
        charwindow.cpp \
    character.cpp \
    spell.cpp \
    roller.cpp \
    factory.cpp \
    xmlwriter.cpp \
    skill.cpp \
    attribute.cpp \
    item.cpp \
    cclass.cpp \
    race.cpp

HEADERS  += charwindow.h \
    character.h \
    item.h \
    skill.h \
    race.h \
    attribute.h \
    cclass.h \
    spell.h \
    roller.h \
    factory.h \
    tools.h \
    xmlwriter.h

FORMS    += charwindow.ui \
    genericroller.ui

OTHER_FILES += \
    ../build-CharSheets-Desktop_Qt_5_3_0_MinGW_32bit-Debug/bob.xml
