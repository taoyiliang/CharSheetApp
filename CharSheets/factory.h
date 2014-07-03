#ifndef FACTORY_H
#define FACTORY_H

#include <cstring>

#include "character.h"
#include "roller.h"
#include "skill.h"
#include "item.h"
#include "race.h"
#include "cclass.h"
#include "attribute.h"
#include "spell.h"
#include "factory.h"
#include "tools.h"
#include "rapidxml.hpp"
#include "rapidxml_utils.hpp"
#include "rapidxml_print.hpp"

using namespace rapidxml;

class Factory
{
public:
    Roller roller;
    Factory(Roller r);
    Character loadCharacter(char* filename);
    Character character(string playername);
    Skill skill();
    Ability ability();
    Item item();
    Weapon weapon();
    Race race();
    CClass cclass();
    Attribute attr();
    Spell spell();
    Spellbook spellbook();
};

#endif // FACTORY_H
