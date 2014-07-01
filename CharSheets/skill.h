#ifndef SKILL_H
#define SKILL_H

#include <string>
using namespace std;

class Skill
{
public:
  string name;
  bool trained;
  bool master;
  int misc;
  Skill(){};
};

class Ability
{
public:
  string name;
  int val,mod;
  Ability(){};
};

#endif // SKILL_H
