#include "skill.h"

void Skill::xml_write(xml_document<> * doc, xml_node<> * node)
{
  //xml_node<> *node = doc->allocate_node(node_element, "skill");
  //pnode->append_node(node);
  xml_attribute<> *attr;
  char *node_name = doc->allocate_string(name.c_str());
  attr = doc->allocate_attribute("name",node_name); node->append_attribute(attr);
  attr = doc->allocate_attribute("trained",double2char(&*doc,trained)); node->append_attribute(attr);
  attr = doc->allocate_attribute("master",double2char(&*doc,master)); node->append_attribute(attr);
  attr = doc->allocate_attribute("misc",double2char(&*doc,misc)); node->append_attribute(attr);
}

void Skill::xml_read(xml_document<>* doc,xml_node<>* node)
{
  for (xml_attribute<> *attr=node->first_attribute();attr;attr=attr->next_attribute())
  {
    if (!strcmp(attr->name(),"name")){name=string(attr->value());}
    if (!strcmp(attr->name(),"trained")){trained= strcmp(attr->value(),"0");}
    if (!strcmp(attr->name(),"master")){master= strcmp(attr->value(),"0");}
    if (!strcmp(attr->name(),"misc")){misc= stoi(string(attr->value()));}
    //else if (!strcmp(attr->name(),"trained"))
  }
}
