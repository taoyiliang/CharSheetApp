#include "charwindow.h"
#include <QApplication>

#include <iostream>
#include <vector>
#include <cmath>

#include "character.h"
#include "roller.h"
#include "vecmod.h"
#include "factory.h"

int main(int argc, char *argv[])
{
    //QApplication a(argc, argv);
    Roller roller;
    Factory factory(roller);
    Character graak=factory.character("john");
    vector<int> val = roller.parseMod("3d8+2+-1");
    std::cout<<graak.player<<" "<<val<<std::endl;
    //CharWindow w;
    //w.show();

    return 0;//a.exec();
}
