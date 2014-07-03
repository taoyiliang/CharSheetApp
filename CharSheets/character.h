#ifndef CHARACTER_H
#define CHARACTER_H

#include <vector>
#include <string>
#include <fstream>
#include <iostream>
#include <cstring>

#include "rapidxml.hpp"
#include "rapidxml_utils.hpp"
#include "rapidxml_print.hpp"

#include "xmlwriter.h"

#include "skill.h"
#include "item.h"
#include "race.h"
#include "cclass.h"
#include "attribute.h"
#include "spell.h"
#include "roller.h"
#include "character.h"
#include "item.h"
#include "skill.h"

#include "tools.h"
using namespace std;
using namespace rapidxml;

class Character
{
  public:
    //basics
    string name,gender,player,deity,hair,eyes;
    double height,weight;
    int age;
    vector<int> alignment;

    Race race;
    CClass cclass;

    //xp, gold, init, special, hp
    int level,curxp,nextxp,init,gp,sp,cp,curhp,maxhp;
    vector<string> sp_counter_names;
    vector<int> sp_counter_vals;

    vector<int> lvlxp; //TODO set up levels

    //physical stats
    int STR,DEX,CON,INT,WIS,CHA;
    int strmod,dexmod,conmod,intmod,wismod,chamod;
    int DTH,res_slash,res_pierce,res_bludgeon;
    float carrying,capacity,speed;
    vector<string> vision;
    vector<string> languages;

    vector<string> ideals;
    vector<string> flaws;
    //vector<Bond> bonds;
    vector<string> notes;

    //rest of members
    vector<Skill> skills;

    vector<Attribute> attributes;

    Spellbook spellbook;
    vector<Spell> daily_spells;

    vector<Item> equipment;
    vector<Item> weapons;



    //Character companion; TODO

    Roller roller;

    Character(){}
    Character(Roller,string);
    void addXp(int xp){curxp+=xp;}
    void addCurrency(vector<int>);
    void addCurrency(double);
    int rollInit(Weapon,bool,bool,int,bool);
    int rollAttack(Weapon,bool,int);
    int rollDamage(Weapon,int,int);
    int rollSkill(Skill,bool,int);
    int rollSave(Ability,bool,int);
    void writeXML();

};

inline std::ostream& operator<<(std::ostream& stream, const Character& c)
{
  //general character traits
  stream <<"\nCHARACTER OUTPUT\n";
  stream << "| [Basics]\n";
  stream << "|   Name  : "<<c.name      <<"\n";
  stream << "|   Player: "<<c.player    <<"\n";
  stream << "|   Gender: "<<c.gender    <<"\n";
  stream << "|   Deity : "<<c.deity     <<"\n";
  stream << "|   Hair  : "<<c.hair      <<"\n";
  stream << "|   Eyes  : "<<c.eyes      <<"\n";
  stream << "|   Height: "<<c.height    <<"\n";
  stream << "|   Weight: "<<c.weight    <<"\n";
  stream << "|   Age   : "<<c.age       <<"\n";
  stream << "|   Align : "<<c.alignment <<"\n";
  stream << "| []\n| \n";
  //xp,gp,etc
  stream << "| [Counters]\n";
  stream << "|   HP            : "<<c.curhp<<" "<<c.maxhp   <<"\n";
  stream << "|   XP            : "<<c.curxp               <<"\n";
  stream << "|   Currency      : "<<c.gp<<" "<<c.sp<<" "<<c.cp<<"\n";
  stream << "|   SpecialNames  : "<<c.sp_counter_names    <<"\n";
  stream << "|   SpecialValues : "<<c.sp_counter_vals     <<"\n";
  stream << "| []\n| \n";
  //stats
  stream << "| [Attributes]\n";
  stream << "|   Abilities: "<<c.STR<<" "<<c.DEX<<" "<<c.CON<<" "
                             <<c.INT<<" "<<c.WIS<<" "<<c.CHA<<"\n";
  stream << "|   Languages: "<<c.languages  <<"\n";
  stream << "|   Vision   : "<<c.vision     <<"\n";
  stream << "| []\n| \n";
  //skills
  stream << "| [Skills]\n";
  //for (size_t i=0;i<skills.size();i++)
  //{
  //  stream << "  "<<skills[i]<<"\n";
 // }
  stream << "| []\n| \n";

  stream <<"END CHARACTER OUTPUT\n";
  return stream;
}
#endif // CHARACTER_H
