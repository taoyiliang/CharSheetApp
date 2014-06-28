#-------------------------------------------------
#
# Project created by QtCreator 2014-06-28T11:22:32
#
#-------------------------------------------------

TARGET = DnDCppLib
TEMPLATE = lib

DEFINES += DNDCPPLIB_LIBRARY

SOURCES += dndcpplib.cpp \
    character.cpp \
    item.cpp

HEADERS += dndcpplib.h\
        dndcpplib_global.h \
    character.h \
    item.h

unix {
    target.path = /usr/lib
    INSTALLS += target
}
