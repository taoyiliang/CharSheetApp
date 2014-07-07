#ifndef CCLASS_H
#define CCLASS_H

#include <string>
#include <vector>
#include <memory>

#include "rapidxml.hpp"
#include "rapidxml_print.hpp"
#include "rapidxml_utils.hpp"
#include "tools.h"
#include "attribute.h"

using namespace std;
using namespace rapidxml;

class CClass
{
public:
  string name;
  int hitdie;
  vector<string> profs;
  vector<string> skills;
  vector<Attribute> attributes;
  shared_ptr<CClass> subclass;
  CClass(){}
  vector<Attribute> levelAttributes(int);
  void xml_write(xml_document<> * doc, xml_node<> * node);
  void xml_read (xml_document<> * doc, xml_node<> * node );
};


#endif // CCLASS_H
