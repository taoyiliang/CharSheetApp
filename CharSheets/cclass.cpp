#include "cclass.h"

#include <iostream>

void CClass::xml_write(xml_document<> * doc, xml_node<> * node)
{
  xml_attribute<> *attr;
  char *node_name = doc->allocate_string(name.c_str());
  attr = doc->allocate_attribute("name",node_name); node->append_attribute(attr);

  string strprof(" ");
  for (size_t i=0; i<profs.size();i++)
  {
    strprof+=string(profs[i])+"|";
  }
  node_name = doc->allocate_string(strprof.c_str());
  attr = doc->allocate_attribute("profs",node_name); node->append_attribute(attr);

  string strskills(" ");
  for (size_t i=0; i<skills.size();i++)
  {
    strskills+=string(skills[i])+"|";
  }
  node_name = doc->allocate_string(strskills.c_str());
  attr = doc->allocate_attribute("skills",node_name); node->append_attribute(attr);

  xml_node<> *snode;
  for (size_t i=0;i<attributes.size();i++)
  {
    snode = doc->allocate_node(node_element,"attribute");
    attributes[i].xml_write(&*doc,&*snode);
    node->append_node(snode);
  }

  if (subclass)
  {
    snode = doc->allocate_node(node_element,"subclass");
    (*subclass).xml_write(&*doc,&*snode);
    node->append_node(snode);
  }
}

void CClass::xml_read (xml_document<> * doc, xml_node<> * node)
{
  for (xml_attribute<> *attr=node->first_attribute();attr;attr=attr->next_attribute())
  {
    if      (!strcmp(attr->name(),"name"))   {name   = string(attr->value());}
    else if (!strcmp(attr->name(),"profs"))
    {
      vector<string> strprofs = strsplit(string(attr->value()),"|");
      profs=move(strprofs);
    }
    else if (!strcmp(attr->name(),"skills"))
    {
      vector<string> strskills = strsplit(string(attr->value()),"|");
      skills=move(strskills);
    }
  }
  for (xml_node<> *snode=node->first_node();snode;snode=snode->next_sibling())
  {
    if (!strcmp(snode->name(),"attribute"))
    {
      Attribute newattr;
      newattr.xml_read(&*doc,&*snode);
      attributes.push_back(newattr);
    }
    else if (!strcmp(snode->name(),"subclass"))
    {
      subclass = unique_ptr<CClass>(new CClass());
      (*subclass).xml_read(&*doc,&*snode);
    }
  }
}
