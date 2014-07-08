#ifndef SPELL_H
#define SPELL_H

#include <vector>
#include <string>
#include <cstring>

#include "tools.h"
#include "rapidxml.hpp"
#include "rapidxml_utils.hpp"
#include "rapidxml_print.hpp"

using namespace std;
using namespace rapidxml;

class Spell
{
public:
  vector<string> cclasses,materials;
  string name,type,save,damage,dmgtype,empowered,short_desc,long_desc,range;
  string casttime,duration,num_result;
  int level,init;
  bool ritual,concentration,halfdmg;

  Spell(){};
  void xml_write(xml_document<> * doc, xml_node<> * node );
  void xml_read (xml_document<> * doc, xml_node<> * node );
};

class DailySpell:public Spell
{
public:
  int use_level;
  bool avail;
  DailySpell();
  DailySpell(Spell s);
  void xml_write(xml_document<> * doc, xml_node<> * node );
  void xml_read (xml_document<> * doc, xml_node<> * node );
};


class Spellbook
{
public:
  string name;
  vector<Spell> spells;
  void addSpell(Spell);
  Spellbook(){}
  void xml_write(xml_document<> * doc, xml_node<> * node );
  void xml_read (xml_document<> * doc, xml_node<> * node );
};

class SpellDatabase
{
public:
  vector<Spell> spells;
  SpellDatabase(){};
  void loadSpells(char *filename);
};


inline ostream& operator<<(ostream& stream, const Spell spell)
{
  stream << "***** Spell Printout *****\n";
  stream << "Name: "          << spell.name          << "\n";
  stream << "  Class: "       << spell.cclasses      << "\n";
  stream << "  Level: "       << spell.level         << "\n";
  stream << "  Type: "        << spell.type          << "\n";
  stream << "  Ritual: "      << spell.ritual        << "\n";
  stream << "  Init: "        << spell.init          << "\n";
  stream << "  Cast Time: "   << spell.casttime      << "\n";
  stream << "  Duration: "    << spell.duration      << "\n";
  stream << "  Range: "       << spell.range         << "\n";
  stream << "  Concentrate: " << spell.concentration << "\n";
  stream << "  Save: "        << spell.save          << "\n";
  stream << "  Damage: "      << spell.damage        << "\n";
  stream << "  Damage Type: " << spell.dmgtype       << "\n";
  stream << "  Half Damage: " << spell.halfdmg       << "\n";
  stream << "  Number: "      << spell.num_result    << "\n";
  stream << "  Materials: "   << spell.materials     << "\n";
  stream << "  Empower: "     << spell.empowered     << "\n";
  stream << "  Short Desc: "  << spell.short_desc    << "\n";
  stream << "  Long Desc: "   << spell.long_desc     << "\n";
  stream << "***** End Spell Printout *****\n";
  return stream;
}

#endif // SPELL_H
