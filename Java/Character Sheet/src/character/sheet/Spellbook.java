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
public class Spellbook {
  public String name;
  public List<Spell> spells = new ArrayList<>();
  public List<Attribute> attributes = new ArrayList<>();
  
  public Spellbook(){}
  
  public void writeXML(Document doc,Element elem)
  {
    elem.setAttribute("name", name);
    for (Spell spell : spells) 
    {
      Element newnode = doc.createElement("spell");
      spell.writeXML(doc, newnode);
      elem.appendChild(newnode);
    }
    for (Attribute attribute : attributes) {
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
      switch (nodeMap.item(i).getNodeName()) 
      {
        case "name": name = nodeMap.item(i).getTextContent();break;
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
        case "spell":
          Spell newspell = new Spell();
          newspell.readXML(doc,snode);
          spells.add(newspell);
        break;
      case "daily":
          Dailyspell dayspell = new Dailyspell(new Spell());
          dayspell.readXML(doc,snode);
          spells.add(dayspell);
        break;
      }
    }
  }
  
  @Override
  public String toString()
  {
    String str="";
    str+="{name: "+name;
    for (Attribute attr: attributes)
    {
      str+=", attr["+attr.toString()+"]";
    }
    for (Spell spell: spells)
    {
      str+=", spell["+spell.toString()+"]";
    }
    return str+"}";
  }
}
