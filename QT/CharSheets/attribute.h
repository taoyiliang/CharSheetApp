#ifndef ATTRIBUTE_H
#define ATTRIBUTE_H

#include <cstring>
#include <string>
#include <vector>

#include "rapidxml.hpp"
#include "rapidxml_print.hpp"
#include "rapidxml_utils.hpp"
#include "tools.h"

using namespace std;
using namespace rapidxml;

class Attribute
{
public:
  string name,details;
  int level;
  Attribute(){}
  void xml_write(xml_document<> * doc, xml_node<> * node);
  void xml_read (xml_document<> * doc, xml_node<> * node );
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
