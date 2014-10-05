/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package character.sheet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author PaulTalbot
 */
public class Skill 
{
    public String name;
    public Boolean trained;
    public Boolean master;
    public Integer expert;
    public String ability;
    
    public Skill(){};
    
    public void writeXML(Document doc,Element elem)
    {
        elem.setAttribute("name"   ,               name    );
        elem.setAttribute("trained",String.valueOf(trained));
        elem.setAttribute("master" ,String.valueOf(master) );
        elem.setAttribute("expert" ,String.valueOf(expert) );
        elem.setAttribute("ability",               ability );
    }
    public void readXML(Document doc,Node node)
    {
      NamedNodeMap nodeMap = node.getAttributes();
      for (int i=0;i<nodeMap.getLength();i++)
      {
        if (null != nodeMap.item(i).getNodeName())switch (nodeMap.item(i).getNodeName()) 
        {
          case "name"   :name   =                nodeMap.item(i).getTextContent() ;break;
          case "trained":trained=Boolean.valueOf(nodeMap.item(i).getTextContent());break;
          case "master" :master =Boolean.valueOf(nodeMap.item(i).getTextContent());break;
          case "expert" :expert =Integer.valueOf(nodeMap.item(i).getTextContent());break;
          case "ability":ability=                nodeMap.item(i).getTextContent() ;break;
        }
      }
    }
    
    @Override
    public String toString()
    {
      String str = "{name="+name+
                   ", trained="+String.valueOf(trained)+
                   ", master=" +String.valueOf(master)+
                   ", expert=" +String.valueOf(expert)+
                   ", ability="+ability+"}";
      return str;
    }
}
