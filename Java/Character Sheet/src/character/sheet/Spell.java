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
public class Spell {
  
  String name,type,save,damage,dmgtype,empowered,short_desc,long_desc,range,casttime,duration,num_results;
  Integer level,init;
  Boolean ritual,concentration,halfsave;
  public List<String> cclasses = new ArrayList<>();
  public List<String> materials = new ArrayList<>();
  
  Spell(){}
  
  public void writeXML(Document doc,Element elem)
  {
    elem.setAttribute("name"       ,name       );
    elem.setAttribute("type"       ,type       );
    elem.setAttribute("save"       ,save       );
    elem.setAttribute("damage"     ,damage     );
    elem.setAttribute("dmgtype"    ,dmgtype    );
    elem.setAttribute("empowered"  ,empowered  );
    elem.setAttribute("short_desc" ,short_desc );
    elem.setAttribute("long_desc"  ,long_desc  );
    elem.setAttribute("range"      ,range      );
    elem.setAttribute("casttime"   ,casttime   );
    elem.setAttribute("duration"   ,duration   );
    elem.setAttribute("num_results",num_results);
    
    elem.setAttribute("level"         ,String.valueOf(level)        );
    elem.setAttribute("init"          ,String.valueOf(init)         );
    elem.setAttribute("ritual"        ,String.valueOf(ritual)       );
    elem.setAttribute("concentration" ,String.valueOf(concentration));
    elem.setAttribute("halfsave"      ,String.valueOf(halfsave)     );
    
    elem.setAttribute("cclasses"  ,String.valueOf(cclasses ));
    elem.setAttribute("materials" ,String.valueOf(materials));
  }
  
  public void readXML(Document doc,Node node)
  {
    NamedNodeMap nodeMap = node.getAttributes();
    for (int i=0;i<nodeMap.getLength();i++)
    {
      switch (nodeMap.item(i).getNodeName()) 
      {
        case "name"       :name       = nodeMap.item(i).getTextContent() ;break;
        case "type"       :type       = nodeMap.item(i).getTextContent() ;break;
        case "save"       :save       = nodeMap.item(i).getTextContent() ;break;
        case "damage"     :damage     = nodeMap.item(i).getTextContent() ;break;
        case "dmgtype"    :dmgtype    = nodeMap.item(i).getTextContent() ;break;
        case "empowered"  :empowered  = nodeMap.item(i).getTextContent() ;break;
        case "short_desc" :short_desc = nodeMap.item(i).getTextContent() ;break;
        case "long_desc"  :long_desc  = nodeMap.item(i).getTextContent() ;break;
        case "range"      :range      = nodeMap.item(i).getTextContent() ;break;
        case "casttime"   :casttime   = nodeMap.item(i).getTextContent() ;break;
        case "duration"   :duration   = nodeMap.item(i).getTextContent() ;break;
        case "num_results":num_results= nodeMap.item(i).getTextContent() ;break;
          
        case "level"        :level        = Integer.valueOf(nodeMap.item(i).getTextContent());break;
        case "init"         :init         = Integer.valueOf(nodeMap.item(i).getTextContent());break;
        case "ritual"       :ritual       = Boolean.valueOf(nodeMap.item(i).getTextContent());break;
        case "concentration":concentration= Boolean.valueOf(nodeMap.item(i).getTextContent());break;
        case "halfsave"     :halfsave     = Boolean.valueOf(nodeMap.item(i).getTextContent());break;
          
        case "cclasses" :cclasses = Arrays.asList(  nodeMap.item(i).getTextContent().split(","));break;
        case "materials":materials= Arrays.asList(  nodeMap.item(i).getTextContent().split(","));break;
      }
    }
    /*NodeList nodeList = node.getChildNodes();
    for (int i=0;i<nodeList.getLength();i++)
    {
      Node snode = nodeList.item(i);
      switch (snode.getNodeName())
      {
        case "attribute":
          Attribute attr = new Attribute();
          attr.readXML(doc, snode);
          attributes.add(attr);
        break;
        case "subrace":
          subrace = new Subrace(this);
          subrace.readXML(doc,snode);
        break;
      }
    }*/
  }
    
    
  @Override
  public String toString()
  {
    String str = "{"+
            "name="         +name+
            ", type="       +type+
            ", save="       +save+
            ", damage="     +damage+
            ", dmgtype="    +dmgtype+
            ", empowered="  +empowered+
            ", range="      +range+
            ", casttime="   +casttime+
            ", duration="   +duration+
            ", num_results="+num_results+
            ", short_desc=" +short_desc+
            ", long_desc="  +long_desc+
            ", level="        +String.valueOf(level)+
            ", init="         +String.valueOf(init)+
            ", ritual="       +String.valueOf(ritual)+
            ", concentration="+String.valueOf(concentration)+
            ", halfsave="     +String.valueOf(halfsave)+
            ", cclasses="     +String.valueOf(cclasses)+
            ", materials="    +String.valueOf(materials);
    
    return str+"}";
  }
}
