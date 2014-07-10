#include "charwindow.h"
#include <QApplication>

#include <iostream>
#include <vector>
#include <cmath>

#include "character.h"
#include "roller.h"
#include "tools.h"
#include "spell.h"
#include "factory.h"

int main(int argc, char *argv[])
{
    //gui crap
    //QApplication a(argc, argv);

    Roller roller;
    Factory factory(roller);

    //test spell db
    //SpellDatabase spelldb=factory.spelldb();
    //spelldb.loadSpells("spells.xml");
    //std::cout<<spelldb.spells[spelldb.spells.size()-1]<<std::endl;

    //test character
    Character bob=factory.loadCharacter("bob.xml");
    //std::cout<<bob<<" "<<std::endl;
    bob.writeXML();

    //test roller
    //vector<int> val = roller.parseMod("3d8+2+-1");

    //gui crap
    //CharWindow w;
    //w.show();

    return 0;//a.exec();
}
