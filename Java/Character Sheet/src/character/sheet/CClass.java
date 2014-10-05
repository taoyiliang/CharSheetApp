/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package character.sheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author TaoYiLiang
 */
public abstract class CClass {
  public Integer level;
  public String name, hitdie;
  public List<String> profs  = new ArrayList<>();
  public List<String> skills  = new ArrayList<>();
  public List<Effect> effects  = new ArrayList<>();
  public Subclass subclass;
  
  public CClass(){}
  
  public abstract void setSubclass(String str);
  
  public void levelUp()
  {
    Integer lvl = level+1;
    doLevelUp(lvl);
    subclass.doLevelUp(lvl);
  }
  public abstract void doLevelUp(Integer lvl);
    
  public Integer getProficiency(Integer level)
  {
    Integer prof;
    return level;
  }
  
  public void writeXML(Document doc,Element elem)
  {
    elem.setAttribute("name"  ,               name   );
    elem.setAttribute("level" ,String.valueOf(level) );
    elem.setAttribute("hitdie",String.valueOf(hitdie));
    elem.setAttribute("profs" ,String.valueOf(profs ).substring(1,String.valueOf(profs ).length()-1));
    elem.setAttribute("skills",String.valueOf(skills).substring(1,String.valueOf(skills).length()-1));
    for (Effect eff : effects) {
      Element newnode = doc.createElement("effect");
      eff.writeXML(doc, newnode);
      elem.appendChild(newnode);
    }
    if (subclass!=null)
    {
      Element newnode = doc.createElement("subclass");
      subclass.writeXML(doc, newnode);
      elem.appendChild(newnode);
    }
  }
  
  public void readXML(Document doc,Node node)
  {
    NamedNodeMap nodeMap = node.getAttributes();
    for (int i=0;i<nodeMap.getLength();i++)
    {
      switch (nodeMap.item(i).getNodeName()) 
      {
        case "name"  :name   =                 nodeMap.item(i).getTextContent()            ;break;
        case "hitdie":hitdie =                 nodeMap.item(i).getTextContent()            ;break;
        case "level" :level  = Integer.valueOf(nodeMap.item(i).getTextContent())           ;break;
        case "profs" :profs  = Arrays.asList(  nodeMap.item(i).getTextContent().split(","));break;
        case "skills":skills = Arrays.asList(  nodeMap.item(i).getTextContent().split(","));break;
      }
    }
    NodeList nodeList = node.getChildNodes();
    for (int i=0;i<nodeList.getLength();i++)
    {
      Node snode = nodeList.item(i);
      switch (snode.getNodeName())
      {
        case "effect":
          Effect eff = new Effect();
          eff.readXML(doc, snode);
          effects.add(eff);
        break;
        /*case "subclass": //DO THIS in specific classes, not abstract
          subclass = new Subclass(this);//TODO how to read in subclass?
          subclass.readXML(doc,snode);
        break;*/
      }
    }
  }
    
    
  @Override
  public String toString()
  {
    String str = "{name="+name+", hitdie="+String.valueOf(hitdie)+", profs="+String.valueOf(profs)
            +", skills="+String.valueOf(skills);
    for (Effect eff: effects)
    {
      str+=", effect["+eff.toString()+"]";
    }
    if (subclass!=null){str+=", subclass: "+subclass.toString();}
    return str+"}";
  }
}
