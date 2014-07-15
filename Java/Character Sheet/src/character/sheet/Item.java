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
public class Item {
    public String name,desc;
    public double weight;
    public List<Attribute> attributes = new ArrayList<>();
    public Item(){}
    
    public void writeXML(Document doc,Element elem)
    {
        elem.setAttribute("name"  ,               name   );
        elem.setAttribute("desc"  ,               desc   );
        elem.setAttribute("weight",String.valueOf(weight));
      for (Attribute attribute : attributes) 
      {
        Element newnode = doc.createElement("attribute");
        attribute.writeXML(doc, newnode);
        elem.appendChild(newnode);
      }
    }
    public void readXML(Document doc,Node node)
    {
      NamedNodeMap nodeMap = node.getAttributes();
      for (int i=0;i<nodeMap.getLength();i++)
      {
        switch (nodeMap.item(i).getNodeName()){
              case "name"       :name   =                nodeMap.item(i).getTextContent() ;break;
              case "description":desc   =                nodeMap.item(i).getTextContent() ;break;
              case "weight"     :weight = Double.valueOf(nodeMap.item(i).getTextContent());break;
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
        }
      }
    }
    
    @Override
    public String toString()
    {
      String str = "{name="+name+", weight="+String.valueOf(weight)+", description="+desc;
      for (Attribute attr: attributes)
      {
        str+=", attr["+attr.toString()+"]";
      }
      return str+"}";
    }
}
