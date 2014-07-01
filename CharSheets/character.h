#ifndef CHARACTER_H
#define CHARACTER_H

#include <vector>
#include <string>

#include "skill.h"
#include "item.h"
#include "race.h"
#include "cclass.h"
#include "attribute.h"
#include "spell.h"
#include "roller.h"

using namespace std;

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

    //rest of members
    vector<Skill> skills;
    vector<string> vision;
    vector<string> languages;

    vector<Attribute> attributes;

    Spellbook spellbook;
    vector<Spell> daily_spells;

    vector<Item> equipment;
    vector<Item> weapons;

    vector<string> ideals;
    vector<string> flaws;
    //vector<Bond> bonds;

    vector<string> notes;

    //Character companion; TODO

    Roller roller;

    Character(){};
    Character(Roller,string);
    void addXp(int xp){curxp+=xp;}
    void addCurrency(vector<int>);
    void addCurrency(double);
    int rollInit(Weapon,bool,bool,int,bool);
    int rollAttack(Weapon,bool,int);
    int rollDamage(Weapon,int,int);
    int rollSkill(Skill,bool,int);
    int rollSave(Ability,bool,int);
    vector<string> vectorize();
};
#endif // CHARACTER_H
