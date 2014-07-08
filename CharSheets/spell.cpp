#include "spell.h"
#include <iostream>

DailySpell::DailySpell():avail(false),use_level(0)
{}

DailySpell::DailySpell(Spell s):cclasses(s.cclasses),
                                materials(s.materials),
                                name(s.name),
                                type(s.type),
                                save(s.save),
                                damage(s.damage),
                                dmgtype(s.dmgtype),
                                empowered(s.empowered),
                                short_desc(s.short_desc),
                                long_desc(s.long_desc),
                                range(s.range),
                                casttime(s.casttime),
                                duration(s.duration),
                                num_result(s.num_results),
                                level(s.level),
                                init(s.init),
                                ritual(s.ritual),
                                concentration(s.concentration),
                                halfdmg(s.halfdmg),
                                avail(false),
                                use_level(0)
{}

void SpellDatabase::loadSpells(char * filename)
{
  file<> xmlfile(filename);
  xml_document<> doc;
  doc.parse<0>(xmlfile.data());
  Spell spell;
  for (xml_node<> *node = doc.first_node();node;node=node->next_sibling())
  {
    spell = Spell();
    spell.xml_read(&doc,&*node);
    spells.push_back(spell);
  }
}

void Spellbook::xml_write(xml_document<> * doc, xml_node<> * node )
{
  xml_attribute<> *attr;
  xml_node<> *snode;
  char *node_name = doc->allocate_string(name.c_str());
  attr = doc->allocate_attribute("name",node_name); node->append_attribute(attr);
  for (size_t i=0; i<spells.size();i++)
  {
    snode = doc->allocate_node(node_element,"spell");
    spells[i].xml_write(&*doc,&*snode);
    node->append_node(snode);
  }
}

void Spellbook::xml_read (xml_document<> * doc, xml_node<> * node )
{
  for (xml_attribute<> *attr=node->first_attribute();attr;attr=attr->next_attribute())
  {
    if (!strcmp(attr->name(),"name")){name = string(attr->value());}
  }
  for (xml_node<> *snode=node->first_node();snode;snode=snode->next_sibling())
  {
    Spell newspell;
    newspell.xml_read(&*doc,&*snode);
    spells.push_back(newspell);
  }
}

void DailySpell::xml_write(xml_document<> * doc, xml_node<> * node )
{
  Spell::xml_write(&*doc,&*node);
  xml_attribute<> *attr;
  attr = doc->allocate_attribute("uselevel",double2char(&*doc,use_level   )); node->append_attribute(attr);
  attr = doc->allocate_attribute("avail"  ,double2char(&*doc,avail        )); node->append_attribute(attr);
}

void Spell::xml_write (xml_document<> * doc, xml_node<> * node )
{
  xml_attribute<> *attr;
  char *node_name = doc->allocate_string(name.c_str());
  attr = doc->allocate_attribute("name"      ,node_name); node->append_attribute(attr);
  node_name = doc->allocate_string(type      .c_str());
  attr = doc->allocate_attribute("kind"      ,node_name); node->append_attribute(attr);
  node_name = doc->allocate_string(casttime  .c_str());
  attr = doc->allocate_attribute("casttime"  ,node_name); node->append_attribute(attr);
  node_name = doc->allocate_string(duration  .c_str());
  attr = doc->allocate_attribute("duration"  ,node_name); node->append_attribute(attr);
  node_name = doc->allocate_string(range     .c_str());
  attr = doc->allocate_attribute("range"     ,node_name); node->append_attribute(attr);
  node_name = doc->allocate_string(save      .c_str());
  attr = doc->allocate_attribute("save"      ,node_name); node->append_attribute(attr);
  node_name = doc->allocate_string(damage    .c_str());
  attr = doc->allocate_attribute("damage"    ,node_name); node->append_attribute(attr);
  node_name = doc->allocate_string(dmgtype   .c_str());
  attr = doc->allocate_attribute("dmgtype"   ,node_name); node->append_attribute(attr);
  node_name = doc->allocate_string(num_result.c_str());
  attr = doc->allocate_attribute("number"    ,node_name); node->append_attribute(attr);
  node_name = doc->allocate_string(empowered .c_str());
  attr = doc->allocate_attribute("empower"   ,node_name); node->append_attribute(attr);
  node_name = doc->allocate_string(short_desc.c_str());
  attr = doc->allocate_attribute("shortdesc" ,node_name); node->append_attribute(attr);
  node_name = doc->allocate_string(long_desc .c_str());
  attr = doc->allocate_attribute("longdesc"  ,node_name); node->append_attribute(attr);

  attr = doc->allocate_attribute("level"  ,double2char(&*doc,level        )); node->append_attribute(attr);
  attr = doc->allocate_attribute("ritual" ,double2char(&*doc,ritual       )); node->append_attribute(attr);
  attr = doc->allocate_attribute("init"   ,double2char(&*doc,init         )); node->append_attribute(attr);
  attr = doc->allocate_attribute("conc"   ,double2char(&*doc,concentration)); node->append_attribute(attr);
  attr = doc->allocate_attribute("halfdmg",double2char(&*doc,halfdmg      )); node->append_attribute(attr);

  string strcc("");
  for (size_t i=0;i<cclasses.size();i++){strcc+=cclasses[i]+" ";}
  node_name = doc->allocate_string(strcc.c_str());
  attr = doc->allocate_attribute("cclass",node_name); node->append_attribute(attr);

  string strmat("");
  for (size_t i=0;i<materials.size();i++){strmat+=materials[i]+" ";}
  node_name = doc->allocate_string(strmat.c_str());
  attr = doc->allocate_attribute("components",node_name); node->append_attribute(attr);

}

void DailySpell::xml_read(xml_document<> * doc, xml_node<> * node)
{
  Spell::xml_read(&*doc,&*node);
  for (xml_attribute<> *attr=node->first_attribute();attr;attr=attr->next_attribute())
  {
    if      (!strcmp(attr->name(),"uselevel"  )){use_level    =stoi(attr->value())        ;}
    else if (!strcmp(attr->name(),"avail"     )){avail        =stoi(attr->value())        ;}
  }
}

void Spell::xml_read (xml_document<> * doc, xml_node<> * node)
{
  for (xml_attribute<> *attr=node->first_attribute();attr;attr=attr->next_attribute())
  {
    //std::cout<<attr->name()<<","<<attr->value()<<std::endl;
    if      (!strcmp(attr->name(),"cclass"    )){cclasses     =strsplit(attr->value(),",");}
    else if (!strcmp(attr->name(),"name"      )){name         =string(attr->value())      ;}
    else if (!strcmp(attr->name(),"level"     )){level        =stoi(attr->value())        ;}

    else if (!strcmp(attr->name(),"kind"      )){type         =string(attr->value())      ;}
    else if (!strcmp(attr->name(),"ritual"    )){ritual       =stoi(attr->value())        ;}
    else if (!strcmp(attr->name(),"init"      )){init         =stoi(attr->value())        ;}
    else if (!strcmp(attr->name(),"casttime"  )){casttime     =string(attr->value())      ;}
    else if (!strcmp(attr->name(),"duration"  )){duration     =string(attr->value())      ;}
    else if (!strcmp(attr->name(),"range"     )){range        =string(attr->value())      ;}
    else if (!strcmp(attr->name(),"conc"      )){concentration=stoi(attr->value())        ;}
    else if (!strcmp(attr->name(),"save"      )){save         =string(attr->value())      ;}
    else if (!strcmp(attr->name(),"damage"    )){damage       =string(attr->value())      ;}
    else if (!strcmp(attr->name(),"dmgtype"   )){dmgtype      =string(attr->value())      ;}
    else if (!strcmp(attr->name(),"halfdmg"   )){halfdmg      =stoi(attr->value())        ;}
    else if (!strcmp(attr->name(),"number"    )){num_result   =string(attr->value())      ;}
    else if (!strcmp(attr->name(),"components")){materials    =strsplit(attr->value(),",");}
    else if (!strcmp(attr->name(),"empower"   )){empowered    =string(attr->value())      ;}
    else if (!strcmp(attr->name(),"shortdesc" )){short_desc   =string(attr->value())      ;}
    else if (!strcmp(attr->name(),"longdesc"  )){long_desc    =string(attr->value())      ;}
  }
}


