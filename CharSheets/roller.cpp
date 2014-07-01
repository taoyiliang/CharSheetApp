#include "roller.h"
#include <iostream>

//using namespace std;

Roller::Roller():
    seed(std::chrono::system_clock::now().time_since_epoch().count()),
    gen(seed),
    dist(0,1)
{
  /*mt19937 gen(seed);
  uniform_real_distribution<> dist(0,1);
  uniform_int_distribution<int> d100(1,100);
  uniform_int_distribution<int> d20(1,20);
  uniform_int_distribution<int> d12(1,12);
  uniform_int_distribution<int> d10(1,10);
  uniform_int_distribution<int> d8(1,8);
  uniform_int_distribution<int> d6(1,6);
  uniform_int_distribution<int> d4(1,4);
  uniform_int_distribution<int> d2(1,2);
  this->gen = gen;
  this->dist = dist;
  this->d100 = d100;
  this->d20  = d20;
  this->d12  = d12;
  this->d10  = d10;
  this->d8   = d8;
  this->d6   = d6;
  this->d4   = d4;
  this->d2   = d2;*/
}

int Roller::roll(int die)
{
  int val=floor(dist(gen)*die+1);
  /*switch(die){
    case 100: val = d100(gen);
    case  20: val =  d20(gen);
    case  12: val =  d12(gen);
    case  10: val =  d10(gen);
    case   8: val =   d8(gen);
    case   6: val =   d6(gen);
    case   4: val =   d4(gen);
    case   2: val =   d2(gen);
    default: val = floor(dist(gen)*die+1);
  }*/
  return val;
}

int Roller::parseRoll(string str)
{
  string del = "d";
  int num = stoi(str.substr(0,str.find(del)));
  int die = stoi(str.erase(0,str.find(del)+del.length()));
  //std::cout<<num<<" "<<die<<std::endl;
  int tot = 0;
  for (size_t i=0;i<num;i++){
    tot+= roll(die);
  }
  return tot;
}

vector<int> Roller::parseMod(string str)
{
  //int tot = 0;
  string del = "+";
  string entry;
  vector<int> parts;
  parts.push_back(0);
  int val;
  while (str.find(del)!=string::npos){
    entry = str.substr(0,str.find(del));
    if (entry.length()>0){
      if (str.find("d")!=string::npos){val = parseRoll(entry);}
      else {val=stoi(entry);}
      parts[0]+= val;
      parts.push_back(val);
    }
    str = str.erase(0,str.find(del)+del.length());
    //std::cout<<"entry, str: "<<entry<<", "<<str<<std::endl;
  }
  if (str.length()>0){
    if (str.find("d")!=string::npos){val = parseRoll(str);}
    else {val=stoi(str);}
  }
  parts[0]+= val;
  parts.push_back(val);
  return parts;
}
