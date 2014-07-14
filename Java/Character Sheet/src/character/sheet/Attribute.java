/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package character.sheet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
        
/**
 *
 * @author TaoYiLiang
 */
public class Attribute {
    public String name,details;
    public Integer level;
    
    public Attribute(){}
    public void writeXML(Document doc,Element elem)
    {
        elem.setAttribute("name",name);
        elem.setAttribute("details",details);
        elem.setAttribute("level", String.valueOf(level));
    }
    public void readXML(Document doc,Node node)
    {
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i=0;i<nodeMap.getLength();i++)
        {
          if      (null != nodeMap.item(i).getNodeName())switch (nodeMap.item(i).getNodeName()) {
            case "name"   :name   =                nodeMap.item(i).getTextContent() ;break;
            case "details":details=                nodeMap.item(i).getTextContent() ;break;
            case "level"  :level  =Integer.valueOf(nodeMap.item(i).getTextContent());break;
            }
        }
    }
    
    @Override
    public String toString()
    {
      String str = "{name="+name+", level="+String.valueOf(level)+", details="+details+"}";
      return str;
    }
}
