#ifndef RACE_H
#define RACE_H

#include <string>
#include <vector>

#include "attribute.h"

using namespace std;

class Race
{
public:
  string name;
  int size,speed;
  vector<int> bonus_abil;
  vector<Attribute> attributes;
  vector<Race> subraces;
  Race(){}
};

#endif // RACE_H
