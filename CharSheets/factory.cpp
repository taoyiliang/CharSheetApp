#include "factory.h"


#include <iostream>

Factory::Factory(Roller r):roller(r){}

Character Factory::loadCharacter(char *filename)
{
   file<> xmlfile(filename);
   xml_document<> doc;
   doc.parse<0>(xmlfile.data());
   Character bob = character("dud");
   //print(cout,doc,0);
   xml_node<> *node = doc.first_node(); //character
   for (xml_attribute<> *attr=node->first_attribute();attr;attr=attr->next_attribute())
   {
     ///////////////////////////////////BASICS//////////////////////////////////////
     if      (!strcmp(attr->name(),"name"  )){bob.name  =string(attr->value());}
     else if (!strcmp(attr->name(),"player")){bob.player=string(attr->value());}
     else if (!strcmp(attr->name(),"gender")){bob.gender=string(attr->value());}
     else if (!strcmp(attr->name(),"deity" )){bob.deity =string(attr->value());}
     else if (!strcmp(attr->name(),"hair"  )){bob.hair  =string(attr->value());}
     else if (!strcmp(attr->name(),"eyes"  )){bob.eyes  =string(attr->value());}
     else if (!strcmp(attr->name(),"height"))
       {string strheight(attr->value());bob.height = stod(strheight);}
     else if (!strcmp(attr->name(),"weight"))
       {string strweight(attr->value());bob.weight = stod(strweight);}
     else if (!strcmp(attr->name(),"age"))
       {string strage(attr->value());bob.age = stoi(strage);}
     else if (!strcmp(attr->name(),"lawful"))
       {string strlawful(attr->value());bob.alignment[0]=stoi(strlawful);}
     else if (!strcmp(attr->name(),"good"))
       {string strgood(attr->value());bob.alignment[1]=stoi(strgood);}

     else if (!strcmp(attr->name(),"gp"))
       {string strgp(attr->value());bob.gp=stoi(strgp);}
     else if (!strcmp(attr->name(),"sp"))
       {string strsp(attr->value());bob.sp=stoi(strsp);}
     else if (!strcmp(attr->name(),"cp"))
       {string strcp(attr->value());bob.cp=stoi(strcp);}
     else if (!strcmp(attr->name(),"xp"))
       {string strxp(attr->value());bob.curxp=stoi(strxp);}

     else if (!strcmp(attr->name(),"hp"))
     {
       vector<string> strhp = strsplit(string(attr->value())," ");
       bob.curhp=stoi(strhp[0]);
       bob.maxhp=stoi(strhp[1]);
     }
     else if (!strcmp(attr->name(),"sp_counter_names"))
     {
       vector<string> strspecn = strsplit(string(attr->value())," ");
       for (size_t i=0;i<strspecn.size();i++)
       {
         if (strspecn[i].length()<3){strspecn[i]=" "+strspecn[i]+" ";}
       }
       bob.sp_counter_names=move(strspecn);
     }
     else if (!strcmp(attr->name(),"sp_counter_vals"))
     {
       vector<int> vals;
       vector<string> strspecv = strsplit(string(attr->value())," ");
       for (size_t i=0;i<strspecv.size();i++)
       {
         vals.push_back(stoi(strspecv[i]));
       }
       bob.sp_counter_vals=vals;
     }

     else if (!strcmp(attr->name(),"STR"))
       {string strSTR(attr->value());bob.STR=stoi(strSTR);}
     else if (!strcmp(attr->name(),"DEX"))
       {string strDEX(attr->value());bob.DEX=stoi(strDEX);}
     else if (!strcmp(attr->name(),"CON"))
       {string strCON(attr->value());bob.CON=stoi(strCON);}
     else if (!strcmp(attr->name(),"INT"))
       {string strINT(attr->value());bob.INT=stoi(strINT);}
     else if (!strcmp(attr->name(),"WIS"))
       {string strWIS(attr->value());bob.WIS=stoi(strWIS);}
     else if (!strcmp(attr->name(),"CHA"))
       {string strCHA(attr->value());bob.CHA=stoi(strCHA);}

     else if (!strcmp(attr->name(),"languages"))
     {
       vector<string> strlang = strsplit(string(attr->value())," ");
       bob.languages=strlang;
     }
     else if (!strcmp(attr->name(),"vision"))
     {
       vector<string> strvis = strsplit(string(attr->value())," ");
       bob.vision=strvis;
     }
     else if (!strcmp(attr->name(),"ideals"))
     {
       vector<string> stridl = strsplit(string(attr->value()),"|");
       bob.ideals=stridl;
     }
     else if (!strcmp(attr->name(),"flaws"))
     {
       vector<string> strflaw = strsplit(string(attr->value()),"|");
       bob.flaws=strflaw;
     }
     else if (!strcmp(attr->name(),"notes"))
     {
       vector<string> strnote = strsplit(string(attr->value()),"|");
       bob.notes=strnote;
     }

     else {cout<<"Not found: "<<attr->name()<<": "<<attr->value()<<endl;}
   }
   /////////////////////////////////Member Functions///////////////////////////////


   return bob;
}

Character Factory::character(string playername)
{
  return Character(roller,playername);
}

Skill Factory::skill()
{
  return Skill();
}

Ability Factory::ability()
{
  return Ability();
}

Item Factory::item()
{
  return Item();
}

Weapon Factory::weapon()
{
  return Weapon();
}

Race Factory::race()
{
  return Race();
}

CClass Factory::cclass()
{
  return CClass();
}

Attribute Factory::attr()
{
  return Attribute();
}

Spell Factory::spell()
{
  return Spell();
}

Spellbook Factory::spellbook()
{
  return Spellbook();
}
