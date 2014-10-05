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
public class Effect {
  //contains groups of Attributes all from a single source
  String source,notes,name;
  List<Attribute> attributes = new ArrayList<>();
  
  public Effect(){}
  public Effect(List<Attribute> newattrs)
  {
    attributes = newattrs;
  }
  
  public void addAttribute(Attribute attr)
  {
    attributes.add(attr);
  }
  
  public void removeAttribute(Attribute attr)
  {
    attributes.remove(attr);
  }
  
  public void writeXML(Document doc,Element elem)
  {
    elem.setAttribute("name",name);
    elem.setAttribute("notes",notes);
    elem.setAttribute("source",source);
    for (Attribute attrib:attributes)
    {
      attrib.writeXML(doc, elem);
    }
  }
    public void readXML(Document doc,Node node)
    {
      NamedNodeMap nodeMap = node.getAttributes();
      for (int i=0;i<nodeMap.getLength();i++)
      {
        if      (null != nodeMap.item(i).getNodeName())switch (nodeMap.item(i).getNodeName()) {
          case "name"  :name   = nodeMap.item(i).getTextContent();break;
          case "notes" :notes  = nodeMap.item(i).getTextContent();break;
          case "source":source = nodeMap.item(i).getTextContent();break;
        }
      }
      NodeList nodeList = node.getChildNodes();
      for (int i=0;i<nodeList.getLength();i++)
      {
        Node newnode = nodeList.item(i);
        if (null != newnode.getNodeName()) switch (newnode.getNodeName())
        {
          case "GenericAttribute":
            GenericAttribute gattr = new GenericAttribute();
            gattr.readXML(doc, newnode);
            attributes.add(gattr);
            break;
          case "ModAttribute":
            ModAttribute mattr = new ModAttribute();
            mattr.readXML(doc, newnode);
            attributes.add(mattr);
            break;
          case "UpperLimitAttribute":
            UpperLimitAttribute ulattr = new UpperLimitAttribute();
            ulattr.readXML(doc, newnode);
            attributes.add(ulattr);
            break;
          case "LowerLimitAttribute":
            LowerLimitAttribute llattr = new LowerLimitAttribute();
            llattr.readXML(doc, newnode);
            attributes.add(llattr);
            break;
        }
      }
    }
    
    @Override
    public String toString()
    {
      String str = "{name="+name+", notes="+notes+", source="+source;
      for (Attribute attrib:attributes)
      {
        str+=", attrib["+String.valueOf(attrib)+"]";
      }
      str+="}";
      return str;
    }
}
