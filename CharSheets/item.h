#ifndef ITEM_H
#define ITEM_H

#include <string>
#include <cstring>
#include <string>
#include <vector>

#include "rapidxml.hpp"
#include "rapidxml_print.hpp"
#include "rapidxml_utils.hpp"
#include "tools.h"
#include "attribute.h"

using namespace std;

class Item
{
public:
  string name,desc;
  double weight;
  vector<Attribute> attributes;
  Item(){}
  void xml_write(xml_document<> * doc, xml_node<> * node);
  void xml_read (xml_document<> * doc, xml_node<> * node );
};

class Weapon:public Item
{
public:
  string dmg,type;
  int init,prep,enh,crit,range;
  bool prof,master;
  Weapon(){}
  void xml_write(xml_document<> * doc, xml_node<> * node);
  void xml_read (xml_document<> * doc, xml_node<> * node );
};

class Armor:public Item
{
public:
  string type;
  vector<int> resistances;
  int maxdex,durability;
  Armor(){}
  void xml_write(xml_document<> * doc, xml_node<> * node);
  void xml_read (xml_document<> * doc, xml_node<> * node );
};


#endif // ITEM_H
