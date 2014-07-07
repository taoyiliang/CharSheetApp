#include "item.h"

#include <iostream>

void Item::xml_write(xml_document<> * doc, xml_node<> * node)
{
  xml_attribute<> *attr;
  char *node_name = doc->allocate_string(name.c_str());
  attr = doc->allocate_attribute("name",node_name); node->append_attribute(attr);
  node_name = doc->allocate_string(desc.c_str());
  attr = doc->allocate_attribute("description",node_name); node->append_attribute(attr);
  attr = doc->allocate_attribute("weight",double2char(&*doc,weight)); node->append_attribute(attr);
  xml_node<> *snode;
  for (size_t i=0;i<attributes.size();i++)
  {
    snode = doc->allocate_node(node_element,"attribute");
    attributes[i].xml_write(&*doc,&*snode);
    node->append_node(snode);
  }
}

void Item::xml_read(xml_document<>* doc,xml_node<>* node)
{
  for (xml_attribute<> *attr=node->first_attribute();attr;attr=attr->next_attribute())
  {
    if      (!strcmp(attr->name(),"name"))   {name   = string(attr->value())        ;}
    else if (!strcmp(attr->name(),"description")){desc= string(attr->value())       ;}
    else if (!strcmp(attr->name(),"weight"))  {weight  = stod(string(attr->value()));}
  }
  for (xml_node<> *snode=node->first_node();snode;snode=snode->next_sibling())
  {

   if (!strcmp(snode->name(),"attribute"))
    {
      Attribute newattr;
      newattr.xml_read(&*doc,&*snode);
      attributes.push_back(newattr);
    }
  }
}

void Weapon::xml_write(xml_document<> * doc, xml_node<> * node)
{
  Item::xml_write(&*doc,&*node);
  xml_attribute<> *attr;
  char *node_name = doc->allocate_string(dmg.c_str());
  attr = doc->allocate_attribute("dmg",node_name); node->append_attribute(attr);
  node_name = doc->allocate_string(type.c_str());
  attr = doc->allocate_attribute("type"  ,node_name); node->append_attribute(attr);
  attr = doc->allocate_attribute("init"  ,double2char(&*doc,init )); node->append_attribute(attr);
  attr = doc->allocate_attribute("prep"  ,double2char(&*doc,prep )); node->append_attribute(attr);
  attr = doc->allocate_attribute("enh"   ,double2char(&*doc,enh  )); node->append_attribute(attr);
  attr = doc->allocate_attribute("crit"  ,double2char(&*doc,crit )); node->append_attribute(attr);
  attr = doc->allocate_attribute("range" ,double2char(&*doc,range)); node->append_attribute(attr);
  attr = doc->allocate_attribute("prof"  ,double2char(&*doc,prof)); node->append_attribute(attr);
  attr = doc->allocate_attribute("master",double2char(&*doc,master)); node->append_attribute(attr);

}

void Weapon::xml_read(xml_document<>* doc,xml_node<>* node)
{
  Item::xml_read(&*doc,&*node);
  for (xml_attribute<> *attr=node->first_attribute();attr;attr=attr->next_attribute())
  {
    if      (!strcmp(attr->name(),"type"  )){type  = string(attr->value())      ;}
    else if (!strcmp(attr->name(),"dmg"   )){dmg   = string(attr->value())      ;}
    else if (!strcmp(attr->name(),"init"  )){init  = stoi(string(attr->value()));}
    else if (!strcmp(attr->name(),"prep"  )){prep  = stoi(string(attr->value()));}
    else if (!strcmp(attr->name(),"enh"   )){enh   = stoi(string(attr->value()));}
    else if (!strcmp(attr->name(),"crit"  )){crit  = stoi(string(attr->value()));}
    else if (!strcmp(attr->name(),"range" )){range = stoi(string(attr->value()));}
    else if (!strcmp(attr->name(),"prof"  )){prof  = strcmp(attr->value(),"0")  ;}
    else if (!strcmp(attr->name(),"master")){master= strcmp(attr->value(),"0")  ;}
  }
}


void Armor::xml_write(xml_document<> * doc, xml_node<> * node)
{
  Item::xml_write(&*doc,&*node);
  xml_attribute<> *attr;
  char *node_name = doc->allocate_string(type.c_str());
  attr = doc->allocate_attribute("type"  ,node_name); node->append_attribute(attr);
  string res("");
  cout<<resistances<<endl;
  for (size_t i=0;i<resistances.size();i++)
  {
    res+=to_string(resistances[i])+" ";
  }
  node_name = doc->allocate_string(res.c_str());
  attr = doc->allocate_attribute("resistances",node_name)                    ; node->append_attribute(attr);
  attr = doc->allocate_attribute("maxdex"     ,double2char(&*doc,maxdex))    ; node->append_attribute(attr);
  attr = doc->allocate_attribute("durability" ,double2char(&*doc,durability)); node->append_attribute(attr);
}

void Armor::xml_read(xml_document<>* doc,xml_node<>* node)
{
  Item::xml_read(&*doc,&*node);
  for (xml_attribute<> *attr=node->first_attribute();attr;attr=attr->next_attribute())
  {
    if      (!strcmp(attr->name(),"type"       )){type       = string(attr->value());}
    else if (!strcmp(attr->name(),"resistances"))
    {
      vector<int> res;
      vector<string> strres = strsplit(string(attr->value())," ");
      for (size_t i=0;i<strres.size();i++){res.push_back(stoi(strres[i]));}
      resistances=move(res);
    }
    else if (!strcmp(attr->name(),"maxdex"     )){maxdex     = stoi(string(attr->value()));}
    else if (!strcmp(attr->name(),"durability" )){durability = stoi(string(attr->value()));}
  }
}
