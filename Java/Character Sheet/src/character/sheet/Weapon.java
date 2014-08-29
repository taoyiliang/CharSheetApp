/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package character.sheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author TaoYiLiang
 */
public class Weapon extends Item{
  public String dmg,dmgtype;
  public Integer init,prep,enh,crit,range;
  public Boolean prof,master;
  public Weapon(){}
  
  public void setDefaults()
  {
    name = "Simple Action";
    dmg = "0";
    dmgtype = "none";
    init=0;
    prep=0;
    enh=0;
    crit=20;
    range=0;
    prof=false;
    master=false;
  }
  
  @Override
  public void writeXML(Document doc,Element elem)
  {
    super.writeXML(doc, elem);
    elem.setAttribute("dmg"    ,               dmg    );
    elem.setAttribute("dmgtype",               desc   );
    elem.setAttribute("init"   ,String.valueOf(init  ));
    elem.setAttribute("prep"   ,String.valueOf(prep  ));
    elem.setAttribute("enh"    ,String.valueOf(enh   ));
    elem.setAttribute("crit"   ,String.valueOf(crit  ));
    elem.setAttribute("range"  ,String.valueOf(range ));
    elem.setAttribute("prof"   ,String.valueOf(prof  ));
    elem.setAttribute("master" ,String.valueOf(master));
  }
  
  @Override
  public void readXML(Document doc,Node node)
  {
    NamedNodeMap nodeMap = node.getAttributes();
    super.readXML(doc, node);
    for (int i=0;i<nodeMap.getLength();i++)
    {
      switch (nodeMap.item(i).getNodeName()){
        case "dmg"    :dmg    =                 nodeMap.item(i).getTextContent() ;break;
        case "dmgtype":dmgtype=                 nodeMap.item(i).getTextContent() ;break;
        case "init"   :init   = Integer.valueOf(nodeMap.item(i).getTextContent());break;
        case "prep"   :prep   = Integer.valueOf(nodeMap.item(i).getTextContent());break;
        case "enh"    :enh    = Integer.valueOf(nodeMap.item(i).getTextContent());break;
        case "crit"   :crit   = Integer.valueOf(nodeMap.item(i).getTextContent());break;
        case "range"  :range  = Integer.valueOf(nodeMap.item(i).getTextContent());break;
        case "prof"   :prof   = Boolean.valueOf(nodeMap.item(i).getTextContent());break;
        case "master" :master = Boolean.valueOf(nodeMap.item(i).getTextContent());break;
      }
    }
    /*NodeList nodeList = node.getChildNodes();
    for (int i=0;i<nodeList.getLength();i++)
    {
     switch (nodeList.item(i).getNodeName())
      {
       case "attribute":
          Attribute attr = new Attribute();
          attr.readXML(doc, node);
          attributes.add(attr);
        break;
      }
    }*/
  }
    
  @Override
  public String toString()
  {
    String str = super.toString();
    str = str.substring(1,str.length()-1);
    str+=", dmg="+dmg+", dmgtype="+dmgtype;
    str+=", init="  +String.valueOf(init  );
    str+=", prep="  +String.valueOf(prep  );
    str+=", enh="   +String.valueOf(enh   );
    str+=", crit="  +String.valueOf(crit  );
    str+=", range=" +String.valueOf(range );
    str+=", prof="  +String.valueOf(prof  );
    str+=", master="+String.valueOf(master);
    return str+"}";
  }
}
