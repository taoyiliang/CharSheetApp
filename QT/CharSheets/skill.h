#ifndef SKILL_H
#define SKILL_H

#include <stdio.h>
#include <string>
#include <cstring>

#include "rapidxml.hpp"
#include "rapidxml_print.hpp"
#include "rapidxml_utils.hpp"
#include "tools.h"




using namespace std;
using namespace rapidxml;

class Skill
{
public:
  string name;
  bool trained;
  bool master;
  int misc;
  void xml_write(xml_document<>* doc, xml_node<> *node);
  void xml_read (xml_document<>* doc, xml_node<> *node );
  Skill(){}
};



inline std::ostream& operator<<(std::ostream& stream, const Skill skill)
{
    stream << skill.name<<" "<<skill.trained<<" "<<skill.master<<" "<<skill.misc;
    return stream;
}

#endif // SKILL_H
