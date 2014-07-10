#ifndef RACE_H
#define RACE_H

#include <string>
#include <vector>
#include <memory>
#include <cstring>

#include "rapidxml.hpp"
#include "rapidxml_print.hpp"
#include "rapidxml_utils.hpp"
#include "tools.h"
#include "attribute.h"

using namespace std;

class Race
{
public:
  string name,size;
  int speed;
  vector<int> bonus_abil;
  vector<Attribute> attributes;
  shared_ptr<Race> subrace;
  Race(){}
  void xml_write(xml_document<> * doc, xml_node<> * node );
  void xml_read (xml_document<> * doc, xml_node<> * node );
};

#endif // RACE_H
