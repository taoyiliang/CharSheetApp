#ifndef ITEM_H
#define ITEM_H

#include <string>

using namespace std;

class Item
{
public:
  string name,desc;
  Item(){}
};

class Weapon:public Item
{
public:
  string dmg,type;
  int init,prep,enh,crit,range;
  bool prof,master;
  Weapon(){}
};

#endif // ITEM_H
