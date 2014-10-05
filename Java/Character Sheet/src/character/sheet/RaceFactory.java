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

/**
 *
 * @author PaulTalbot
 */
public class RaceFactory {
  public List<String> known = new ArrayList<>();
  
  public RaceFactory()  //TODO load available classes?
  {
    known.add("Human");
  }
  
  public Race newRace(String str)
  {
    Race race = new CRHuman();
    switch (str)
    {
      case "Dwarf"     : race = new CRDwarf()     ;break;
      case "Elf"       : race = new CRElf()       ;break;
      case "Halfling"  : race = new CRHalfling()  ;break;
      case "Human"     : race = new CRHuman()     ;break;
      case "Dragonborn": race = new CRDragonborn();break;
      case "Gnome"     : race = new CRGnome()     ;break;
      case "HalfElf"   : race = new CRHalfElf()   ;break;
      case "HalfOrc"   : race = new CRHalfOrc()   ;break;
      case "Tiefling"  : race = new CRTiefling()  ;break;
    }
    return race;
  }
  
  public Race XMLnewRace(Document doc,Node node)
  {
    String racetype = "";
    NamedNodeMap nodeMap = node.getAttributes();
    for (int i=0;i<nodeMap.getLength();i++)
    {
      if (nodeMap.item(i).getNodeName()==String.valueOf("type"))
      {
        racetype=nodeMap.item(i).getNodeValue();
      }
    }
    return newRace(racetype);
  }
    
}
