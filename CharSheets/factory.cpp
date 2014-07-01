#include "factory.h"

Factory::Factory(Roller r):roller(r){}

Character Factory::character(string playername)
{
  return Character(roller,playername);
}

Skill Factory::skill()
{
  return Skill();
}

Ability Factory::ability()
{
  return Ability();
}

Item Factory::item()
{
  return Item();
}

Weapon Factory::weapon()
{
  return Weapon();
}

Race Factory::race()
{
  return Race();
}

CClass Factory::cclass()
{
  return CClass();
}

Attribute Factory::attr()
{
  return Attribute();
}

Spell Factory::spell()
{
  return Spell();
}

Spellbook Factory::spellbook()
{
  return Spellbook();
}
