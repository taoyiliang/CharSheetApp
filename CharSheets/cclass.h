#ifndef CCLASS_H
#define CCLASS_H

#include <string>
#include <vector>

#include "attribute.h"

using namespace std;

class CClass
{
public:
  string name;
  int hitdie;
  vector<string> profs;
  vector<string> skills;
  vector<Attribute> attributes;
  CClass(){}
  vector<Attribute> levelAttributes(int);
};

#endif // CCLASS_H
