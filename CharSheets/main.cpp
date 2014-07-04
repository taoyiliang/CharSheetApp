#include "charwindow.h"
#include <QApplication>

#include <iostream>
#include <vector>
#include <cmath>

#include "character.h"
#include "roller.h"
#include "tools.h"
#include "factory.h"

int main(int argc, char *argv[])
{
    //QApplication a(argc, argv);
    Roller roller;
    Factory factory(roller);
    Character bob=factory.loadCharacter("bob.xml");
    //vector<int> val = roller.parseMod("3d8+2+-1");
    //std::cout<<bob<<" "<<std::endl;
    bob.writeXML();
    //CharWindow w;
    //w.show();

    return 0;//a.exec();
}
