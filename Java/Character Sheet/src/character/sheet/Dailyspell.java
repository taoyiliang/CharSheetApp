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
 * @author TaoYiLiang
 */
public class Dailyspell extends Spell{
  private Integer level_taken;
  private Boolean available = false;
  public Spell spell;
  
  Dailyspell(Spell newspell)
  {
    spell = newspell;
  }
  public void use(){available=false;}
  public void refresh(){available=true;}
  public void empower(Integer level){level_taken = level;}
 
  
  
  
  @Override
  public void writeXML(Document doc,Element elem)
  {
    spell.writeXML(doc,elem);
    elem.setAttribute("level_taken", String.valueOf(level_taken));
    elem.setAttribute("available", String.valueOf(available));
    for (Spell spell : spells) {
      spell.writeXML(doc, elem);
    }
    for (Attribute attribute : attributes) {
      attribute.writeXML(doc, elem);
    }
  }
  
  public void readXML(Document doc,Node node)
  {
    spell.readXML(doc,node);
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
      }
    }
  }
  
  @Override
  public String toString()
  {
    String str="";
    str+=super.toString().substring(1,-1);
    str+=", level_taken="+String.valueOf(level_taken);
    str+=", available="+String.valueOf(available);
    return str+"}";
  }
}
