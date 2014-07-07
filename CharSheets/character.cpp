#include "character.h"
#include <typeinfo>
Character::Character(Roller r,string playername)
    :player(playername),
     roller(r)
{
  alignment.push_back(0);
  alignment.push_back(0);
}

void Character::addCurrency(vector<int> curr)
{
  gp+=curr[0];
  sp+=curr[1];
  cp+=curr[2];
}
void Character::addCurrency(double curr)
{
  gp += int(curr);
  curr = 100*(int(curr) % 1);
  sp += int(curr);
  curr = 100*(int(curr) % 1);
  cp += int(curr);
}

int Character::rollInit(Weapon wpn, bool first, bool prep, int misc, bool adv)
{
   // do die roll
   return 0;//TODO
}

int Character::rollAttack(Weapon wpn, bool adv, int misc)
{
   return 0;//TODO
}

int Character::rollDamage(Weapon wpn, int crits, int misc)
{
   return 0;//TODO
}

int Character::rollSkill(Skill skill, bool adv, int misc)
{
   return 0;//TODO
}

int Character::rollSave(string abil, bool adv, int misc)
{
   return 0;//TODO
}

void Character::writeXML()
{
  //ofstream outfile;
  xml_document<> doc;
  xml_node<> *node = doc.allocate_node(node_element, "character");
  doc.append_node(node);
  xml_attribute<> *attr;
  char *node_name;
  /////////////////////////////////////////basics///////////////////////////////////////////
  node_name = doc.allocate_string(name.c_str());
  attr = doc.allocate_attribute("name"  ,node_name); node->append_attribute(attr);
  node_name = doc.allocate_string(player.c_str());
  attr = doc.allocate_attribute("player",node_name); node->append_attribute(attr);
  node_name = doc.allocate_string(gender.c_str());
  attr = doc.allocate_attribute("gender",node_name); node->append_attribute(attr);
  node_name = doc.allocate_string(deity.c_str());
  attr = doc.allocate_attribute("deity" ,node_name); node->append_attribute(attr);
  node_name = doc.allocate_string(hair.c_str());
  attr = doc.allocate_attribute("hair"  ,node_name); node->append_attribute(attr);
  node_name = doc.allocate_string(eyes.c_str());
  attr = doc.allocate_attribute("eyes"  ,node_name); node->append_attribute(attr);

  attr = doc.allocate_attribute("height"  ,double2char(&doc,height      )); node->append_attribute(attr);
  attr = doc.allocate_attribute("weight"  ,double2char(&doc,weight      )); node->append_attribute(attr);
  attr = doc.allocate_attribute("age"     ,double2char(&doc,age         )); node->append_attribute(attr);
  attr = doc.allocate_attribute("lawful"  ,double2char(&doc,alignment[0])); node->append_attribute(attr);
  attr = doc.allocate_attribute("good"    ,double2char(&doc,alignment[1])); node->append_attribute(attr);

  attr = doc.allocate_attribute("gp",double2char(&doc,gp   )); node->append_attribute(attr);
  attr = doc.allocate_attribute("sp",double2char(&doc,sp   )); node->append_attribute(attr);
  attr = doc.allocate_attribute("cp",double2char(&doc,cp   )); node->append_attribute(attr);
  attr = doc.allocate_attribute("xp",double2char(&doc,curxp)); node->append_attribute(attr);

  string hp("");
  hp+=to_string(curxp)+" "+to_string(maxhp);
  attr = doc.allocate_attribute("hp",hp.c_str()); node->append_attribute(attr);

  string names(" ");
  string values(" ");
  for (size_t i=0;i<sp_counter_names.size();i++)
  {
    names+=string(sp_counter_names[i])+" ";
    values+=to_string(sp_counter_vals[i])+" ";
  }
  //cout << names.c_str()<<" "<<values.c_str()<<endl;
  node_name = doc.allocate_string(names.c_str());
  attr = doc.allocate_attribute("sp_counter_names",node_name); node->append_attribute(attr);
  attr = doc.allocate_attribute("sp_counter_vals" ,values.c_str()); node->append_attribute(attr);

  attr = doc.allocate_attribute("STR",double2char(&doc,STR)); node->append_attribute(attr);
  attr = doc.allocate_attribute("DEX",double2char(&doc,DEX)); node->append_attribute(attr);
  attr = doc.allocate_attribute("CON",double2char(&doc,CON)); node->append_attribute(attr);
  attr = doc.allocate_attribute("INT",double2char(&doc,INT)); node->append_attribute(attr);
  attr = doc.allocate_attribute("WIS",double2char(&doc,WIS)); node->append_attribute(attr);
  attr = doc.allocate_attribute("CHA",double2char(&doc,CHA)); node->append_attribute(attr);

  string langs("");
  for (size_t i=0;i<languages.size();i++)
  {
    langs+=languages[i]+" ";
  }
  node_name = doc.allocate_string(langs.c_str());
  attr = doc.allocate_attribute("languages",node_name); node->append_attribute(attr);

  string vis("");
  for (size_t i=0;i<vision.size();i++)
  {
    vis+=vision[i]+" ";
  }
  node_name = doc.allocate_string(vis.c_str());
  attr = doc.allocate_attribute("vision",node_name); node->append_attribute(attr);

  string idl("");
  for (size_t i=0;i<ideals.size();i++)
  {
    idl+=ideals[i];
    if (i!=ideals.size()-1){idl+=" | ";}
  }
  node_name = doc.allocate_string(idl.c_str());
  attr = doc.allocate_attribute("ideals",node_name); node->append_attribute(attr);

  string flw("");
  for (size_t i=0;i<flaws.size();i++)
  {
    flw+=flaws[i];
    if (i!=flaws.size()-1){flw+=" | ";}
  }
  node_name = doc.allocate_string(flw.c_str());
  attr = doc.allocate_attribute("flaws",node_name); node->append_attribute(attr);

  string note("");
  for (size_t i=0;i<notes.size();i++)
  {
    note+=notes[i];
    if (i!=notes.size()-1){note+=" | ";}
  }
  node_name = doc.allocate_string(note.c_str());
  attr = doc.allocate_attribute("notes",node_name); node->append_attribute(attr);
  /////////////////////////////////////////classes///////////////////////////////////////////
  xml_node<> *snode;
  for (size_t i=0;i<skills.size();i++)
  {
    snode = doc.allocate_node(node_element,"skill");
    skills[i].xml_write(&doc,&*snode);
    node->append_node(snode);
  }
  for (size_t i=0;i<attributes.size();i++)
  {
    snode = doc.allocate_node(node_element,"attribute");
    attributes[i].xml_write(&doc,&*snode);
    node->append_node(snode);
  }
  for (size_t i=0;i<equipment.size();i++)
  {
    snode = doc.allocate_node(node_element,"item");
    equipment[i].xml_write(&doc,&*snode);
    node->append_node(snode);
  }
  for (size_t i=0;i<weapons.size();i++)
  {
    snode = doc.allocate_node(node_element,"weapon");
    weapons[i].xml_write(&doc,&*snode);
    node->append_node(snode);
  }
  for (size_t i=0;i<armors.size();i++)
  {
    snode = doc.allocate_node(node_element,"armor");
    armors[i].xml_write(&doc,&*snode);
    node->append_node(snode);
  }

  snode = doc.allocate_node(node_element,"cclass");
  cclass.xml_write(&doc,&*snode);
  node->append_node(snode);

  ofstream outfile;
  outfile.open(name+"2.xml");
  outfile << doc;
  outfile.close();

}
