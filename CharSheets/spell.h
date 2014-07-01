#ifndef SPELL_H
#define SPELL_H

#include <vector>
#include <string>
using namespace std;

class Spell
{
public:
public:
  vector<string> cclasses,materials;
  string name,type,save,damage,empowered,short_desc,long_desc;
  int level,init,range,num_result;
  bool ritual,concentration,halfdmg;
  double duration;

  Spell(){}
};

class Spellbook
{
public:
  string name;
  vector<Spell> spells;
  void addSpell(Spell);
  Spellbook(){}
};


#endif // SPELL_H
