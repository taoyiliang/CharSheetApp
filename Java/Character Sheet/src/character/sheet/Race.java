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
public class Race {
  public String name,size;
  public Integer speed;
  public List<Attribute> attributes  = new ArrayList<>();
  public Subrace subrace;
  
  public Race(){}
  
  public void writeXML(Document doc,Element elem)
  {
    elem.setAttribute("name"  ,               name   );
    elem.setAttribute("size"  ,               size   );
    elem.setAttribute("speed" ,String.valueOf(speed ));
    for (Attribute attribute : attributes) {
      attribute.writeXML(doc, elem);
    }
    subrace.writeXML(doc, elem);
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
    }
  }
    
    
  @Override
  public String toString()
  {
    String str = "{name="+name+", size="+size+", speed="+String.valueOf(speed);
    for (Attribute attr: attributes)
    {
      str+=", attr["+attr.toString()+"]";
    }
    if (subrace!=null){str+=", subrace: "+subrace.toString();}
    return str+"}";
  }
}
