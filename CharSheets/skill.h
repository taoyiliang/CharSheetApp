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

inline std::ostream& operator<<(std::ostream& stream, const Skill skill)
{
    stream << skill.name<<" "<<skill.trained<<" "<<skill.master<<" "<<skill.misc;
    return stream;
}

#endif // SKILL_H
