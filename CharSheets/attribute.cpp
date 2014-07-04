#include "attribute.h"

void Attribute::xml_write(xml_document<> * doc, xml_node<> * node)
{
  //xml_node<> *node = doc->allocate_node(node_element, "skill");
  //node->append_node(node);
  xml_attribute<> *attr;
  char *node_name = doc->allocate_string(name.c_str());
  attr = doc->allocate_attribute("name",node_name); node->append_attribute(attr);
  node_name = doc->allocate_string(details.c_str());
  attr = doc->allocate_attribute("details",node_name); node->append_attribute(attr);
  attr = doc->allocate_attribute("level",double2char(&*doc,level)); node->append_attribute(attr);
}

void Attribute::xml_read(xml_document<>* doc,xml_node<>* node)
{
  for (xml_attribute<> *attr=node->first_attribute();attr;attr=attr->next_attribute())
  {
    if      (!strcmp(attr->name(),"name"))   {name   = string(attr->value())      ;}
    else if (!strcmp(attr->name(),"details")){details= string(attr->value())      ;}
    else if (!strcmp(attr->name(),"level"))  {level  = stoi(string(attr->value()));}
  }
}
