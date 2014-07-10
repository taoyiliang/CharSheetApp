#include "race.h"
#include <iostream>
void Race::xml_write(xml_document<> * doc, xml_node<> * node)
{
  xml_attribute<> *attr;
  char *node_name = doc->allocate_string(name.c_str());
  attr = doc->allocate_attribute("name",node_name); node->append_attribute(attr);
  node_name       = doc->allocate_string(size.c_str());
  attr = doc->allocate_attribute("size",node_name); node->append_attribute(attr);

  attr = doc->allocate_attribute("speed",double2char(&*doc,speed));node->append_attribute(attr);

  string strbon(" ");
  for (size_t i=0; i<bonus_abil.size();i++)
  {
    strbon+=string(double2char(&*doc,bonus_abil[i]))+" ";
  }
  node_name = doc->allocate_string(strbon.c_str());
  attr = doc->allocate_attribute("bonus_abil",node_name); node->append_attribute(attr);

  xml_node<> *snode;
  for (size_t i=0;i<attributes.size();i++)
  {
    snode = doc->allocate_node(node_element,"attribute");
    attributes[i].xml_write(&*doc,&*snode);
    node->append_node(snode);
  }

  if (subrace)
  {
    snode = doc->allocate_node(node_element,"subrace");
    (*subrace).xml_write(&*doc,&*snode);
    node->append_node(snode);
  }
}

void Race::xml_read (xml_document<> * doc, xml_node<> * node)
{
          cout<<"here"<<endl;
  for (xml_attribute<> *attr=node->first_attribute();attr;attr=attr->next_attribute())
  {
    if      (!strcmp(attr->name(),"name" )){name   = string(attr->value());}
    else if (!strcmp(attr->name(),"size" )){size   = string(attr->value());}
    else if (!strcmp(attr->name(),"speed")){speed  = stoi(attr->value());}

    else if (!strcmp(attr->name(),"bonus_abil"))
    {
      vector<int> abils;
      vector<string> strbon = strsplit(string(attr->value())," ");
      for (size_t i=0;i<strbon.size();i++){abils.push_back(stoi(strbon[i]));}
      bonus_abil = move(abils);
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
    else if (!strcmp(snode->name(),"subrace"))
    {
      subrace = unique_ptr<Race>(new Race());
      (*subrace).xml_read(&*doc,&*snode);
    }
  }
}
