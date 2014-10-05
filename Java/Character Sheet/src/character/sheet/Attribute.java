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
public abstract class Attribute {
    public String name,details;
    public String source;
    public String attribtype = "generic";
    public Boolean active=false;
    List<String> applies = new ArrayList<>();
    //TODO appliesTo() and apply() functions
    
    public Attribute(){}
    public Attribute(List<String> applications)
    {
      applies = applications;
    }
    
    public void addApplication(String str)
    {
      applies.add(str);
    }
    public void removeApplication(String str)
    {
      applies.remove(str);
    }
    
    public void setModifier(Object obj)
    {
      //overwrite locally
    }
    
    public Boolean appliesTo(String str)
    {
      return applies.contains(str);
    /*possible types:
    damage
    initiative
    movement speed
    attack
    DTH
    resistance
    saves (all 6)
    skill checks (any skill)
    misc
    Note that these all add or subtract integers, I think...
    */
    }

    public abstract Integer apply(Integer mod);
    
    
    public void writeXML(Document doc,Element elem)
    {
      ParseTools parser = new ParseTools();
      elem.setAttribute("name",name);
      elem.setAttribute("details",details);
      elem.setAttribute("source",details);
      elem.setAttribute("applies",parser.strjoin(applies,",, "));
    }
    public void readXML(Document doc,Node node)
    {
      NamedNodeMap nodeMap = node.getAttributes();
      for (int i=0;i<nodeMap.getLength();i++)
      {
        if      (null != nodeMap.item(i).getNodeName())switch (nodeMap.item(i).getNodeName()) {
          case "name"   :name   = nodeMap.item(i).getTextContent() ;break;
          case "details":details= nodeMap.item(i).getTextContent() ;break;
          case "source" :source = nodeMap.item(i).getTextContent() ;break;
          case "applies":applies= Arrays.asList(nodeMap.item(i).getTextContent().split(","));break;
        }
      }
    }
    
    @Override
    public String toString()
    {
      String str = "{name="+name+", details="+details+", source="+source+", "+String.valueOf(applies)+"}";
      return str;
    }
}
