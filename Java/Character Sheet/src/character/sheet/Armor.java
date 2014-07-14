/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package character.sheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
public class Armor extends Item{
  public String type,size;
  public Integer maxdex,durability;
  public HashMap<String,Integer> resistance = new HashMap<>();
  public Armor()
  {
    resistance.put("acid"     ,0);
    resistance.put("bludgeon" ,0);
    resistance.put("cold"     ,0);
    resistance.put("fire"     ,0);
    resistance.put("force"    ,0);
    resistance.put("lightning",0);
    resistance.put("necrotic" ,0);
    resistance.put("piercing" ,0);
    resistance.put("poison"   ,0);
    resistance.put("psychic"  ,0);
    resistance.put("radiant"  ,0);
    resistance.put("slashing" ,0);
    resistance.put("thunder"  ,0);
  }
  
  @Override
  public void writeXML(Document doc,Element elem)
  {
    super.writeXML(doc, elem);
    elem.setAttribute("type"      ,               type                );
    elem.setAttribute("size"      ,               size                );
    elem.setAttribute("maxdex"    ,String.valueOf(maxdex             ));
    elem.setAttribute("durability",String.valueOf(durability         ));
    elem.setAttribute("resistance",String.valueOf(resistance.values()));
  }
  
  //TODO this isn't working yet.  Resists aren't reading in, and super isn't reading in.
  @Override
  public void readXML(Document doc,Node node)
  {
    NamedNodeMap nodeMap = node.getAttributes();
    for (int i=0;i<nodeMap.getLength();i++)
    {
      switch (nodeMap.item(i).getNodeName()){
        case "type"      :type       =                 nodeMap.item(i).getTextContent() ;break;
        case "size"      :size       =                 nodeMap.item(i).getTextContent() ;break;
        case "maxdex"    :maxdex     = Integer.valueOf(nodeMap.item(i).getTextContent());break;
        case "durability":durability = Integer.valueOf(nodeMap.item(i).getTextContent());break;
        case "resistance":
          ParseTools parsetool = new ParseTools();
          String entry = nodeMap.item(i).getTextContent();
          resistance.putAll(parsetool.readMap(entry));
          break;
      }
    }
    /*NodeList nodeList = node.getChildNodes();
    for (int i=0;i<nodeList.getLength();i++)
    {
      switch (nodeList.item(i).getNodeName())
      {
       case "attribute":
          Attribute attr = new Attribute();
          attr.readXML(doc, node);
          attributes.add(attr);
        break;
      }
    }*/
  }
  
  @Override
  public String toString()
  {
    String str = super.toString();
    str = str.substring(1,str.length()-1);
    str+=", type="+type+", size="+size;
    str+=", maxdex="     +String.valueOf(maxdex    );
    str+=", durability=" +String.valueOf(durability);
    str+=", resistance="   +String.valueOf(resistance);
    return str+"}";
  }
}
