/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package character.sheet;

import java.util.ArrayList;
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
public abstract class Race {
  public String name,size;
  public Integer speed;
  public List<Effect> effects  = new ArrayList<>();
  public Subrace subrace;
  
  public Race(){}
  
  public void writeXML(Document doc,Element elem)
  {
    elem.setAttribute("name"  ,               name   );
    elem.setAttribute("size"  ,               size   );
    elem.setAttribute("speed" ,String.valueOf(speed ));
    for (Effect eff : effects) {
      Element newnode = doc.createElement("attribute");
      eff.writeXML(doc, newnode);
      elem.appendChild(newnode);
    }
    if (subrace!=null)
    {
      Element newnode = doc.createElement("subrace");
      subrace.writeXML(doc, newnode);
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
        case "name"  :name  =                 nodeMap.item(i).getTextContent() ;break;
        case "size"  :size  =                 nodeMap.item(i).getTextContent() ;break;
        case "speed" :speed = Integer.valueOf(nodeMap.item(i).getTextContent());break;
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
        //case "subrace": //TODO do in individual races
        //  subrace = new Subrace(this);
        //  subrace.readXML(doc,snode);
        //break;
      }
    }
  }
    
    
  @Override
  public String toString()
  {
    String str = "{name="+name+", size="+size+", speed="+String.valueOf(speed);
    for (Effect eff: effects)
    {
      str+=", effect["+eff.toString()+"]";
    }
    if (subrace!=null){str+=", subrace: "+subrace.toString();}
    return str+"}";
  }
}
