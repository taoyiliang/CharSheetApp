#include "character.h"
#include "item.h"
#include "skill.h"

Character::Character(Roller r,string playername)
    :player(playername),
     roller(r)
{}

void Character::addCurrency(vector<int> curr)
{
  gp+=curr[0];
  sp+=curr[1];
  cp+=curr[2];
}
void Character::addCurrency(double curr)
{
  gp += int(curr);
  curr = 100*(int(curr) % 1);
  sp += int(curr);
  curr = 100*(int(curr) % 1);
  cp += int(curr);
}

int Character::rollInit(Weapon wpn, bool first, bool prep, int misc, bool adv)
{
   // do die roll
   return 0;//TODO
}

int Character::rollAttack(Weapon wpn, bool adv, int misc)
{
   return 0;//TODO
}

int Character::rollDamage(Weapon wpn, int crits, int misc)
{
   return 0;//TODO
}

int Character::rollSkill(Skill skill, bool adv, int misc)
{
   return 0;//TODO
}

int Character::rollSave(Ability abil, bool adv, int misc)
{
   return 0;//TODO
}

vector<string> Character::vectorize()
{
  vector<string> s;
  s.push_back(name);
  s.push_back(gender);
  s.push_back(player);
  s.push_back(deity);
  s.push_back(hair);
  s.push_back(eyes);

  s.push_back(to_string(height));
  s.push_back(to_string(weight));
  s.push_back(to_string(age));


}
