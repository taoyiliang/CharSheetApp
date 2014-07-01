#ifndef ATTRIBUTE_H
#define ATTRIBUTE_H

#include <string>
#include <vector>

#include "character.h"

using namespace std;

class Attribute
{
public:
  string name;
  string details;
  Attribute(){}
};

/*class Bond
{
public:
  vector<Character> chars;
  string text;
  bool completed;
  int xp;
  Bond(){}
};*/

#endif // ATTRIBUTE_H
