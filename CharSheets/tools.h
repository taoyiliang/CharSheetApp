#ifndef TOOLS_H
#define TOOLS_H

#include <string>
#include <vector>
#include <stdio.h>
#include "rapidxml.hpp"
#include "rapidxml_print.hpp"
#include "rapidxml_utils.hpp"

using namespace std;
using namespace rapidxml;

template<class T>
std::ostream& operator<<(std::ostream& stream, const std::vector<T>& content)
{
    //stream << "[";
    if (content.size() > 0)
    {
        stream << content[0];

        for (std::size_t i = 1; i < content.size(); ++i)
            stream << " " << content[i];
    }

    return stream;// << "]";
}


inline vector<string> strsplit(string str,string d)
{
  vector<string> vec;
  string entry;
  while (str.find(d)!=string::npos){
    entry = str.substr(0,str.find(d));
    if (entry.length()>0){
      vec.push_back(entry);
    }
    str=str.erase(0,str.find(d)+d.length());
  }
  if (str.length()>0){
    vec.push_back(str);
  }
  return vec;
}

inline char* double2char(xml_document<> *doc,double value)
{
  char tmpval[1000];
  sprintf(tmpval,"%f",value);
  return doc->allocate_string(tmpval);
}
inline char* double2char(xml_document<> *doc,int value)
{
  char tmpval[1000];
  sprintf(tmpval,"%i",value);
  return doc->allocate_string(tmpval);
}
#endif // TOOLS_H

