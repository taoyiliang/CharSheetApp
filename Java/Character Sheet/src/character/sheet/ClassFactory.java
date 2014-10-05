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
public class ClassFactory {
  public List<String> known = new ArrayList<>();
  
  public ClassFactory()  //TODO load available classes?
  {
    known.add("Barbarian");
  }
  
  public CClass newClass(String str)
  {
    CClass cls = new CCGeneric();
    switch (str)
    {
      case "Barbarian": cls = new CCBarbarian();break;
      case "Bard"     : cls = new CCBard()     ;break;
      case "Cleric"   : cls = new CCCleric()   ;break;
      case "Druid"    : cls = new CCDruid()    ;break;
      case "Fighter"  : cls = new CCFighter()  ;break;
      case "Monk"     : cls = new CCMonk()     ;break;
      case "Paladin"  : cls = new CCPaladin()  ;break;
      case "Ranger"   : cls = new CCRanger()   ;break;
      case "Rogue"    : cls = new CCRogue()    ;break;
      case "Sorcerer" : cls = new CCSorcerer() ;break;
      case "Wizard"   : cls = new CCWizard()   ;break;
    }
    return cls;
  }
  
  public CClass XMLnewClass(Document doc,Node node)
  {
    String classtype = "";
    NamedNodeMap nodeMap = node.getAttributes();
    for (int i=0;i<nodeMap.getLength();i++)
    {
      if (nodeMap.item(i).getNodeName()==String.valueOf("type"))
      {
        classtype=nodeMap.item(i).getNodeValue();
      }
    }
    return newClass(classtype);
  }
    
}
