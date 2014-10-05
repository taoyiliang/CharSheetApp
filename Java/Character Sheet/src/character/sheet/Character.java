/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package character.sheet;

import com.google.common.collect.Iterables;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.ceil;
import static java.lang.Math.floor;

import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



/**
 *
 * @author TaoYiLiang
 */
public class Character
{
    public String name,gender,player,deity,hair,eyes;
    public Double height,weight;
    public Integer age,curxp,pp,gp,sp,curhp,temphp;
    public Integer maxhp;
    public Integer STR,DEX,CON,INT,WIS,CHA;
    public Integer lawful,good;
    
    public List<String> special_names   = new ArrayList<>();
    public List<Integer> special_values = new ArrayList<>();
    public List<String> vision    = new ArrayList<>();
    public List<String> languages = new ArrayList<>();
    public List<String> traits    = new ArrayList<>();
    public List<String> ideals    = new ArrayList<>();
    public List<String> flaws     = new ArrayList<>();
    public List<String> bonds     = new ArrayList<>();
    public List<String> notes     = new ArrayList<>();
    public HashMap<String,Integer> resistance = new HashMap<>();

    public List<Skill>     skills     = new ArrayList<>();
    public List<Effect>    effects    = new ArrayList<>();
    public List<Effect>    feats      = new ArrayList<>();
    public List<Item>      equipment  = new ArrayList<>();
    public List<Weapon>    weapons    = new ArrayList<>();
    public List<Armor>     armor      = new ArrayList<>();
    
    public CClass cclass;
    public Race   race;
    public Spellbook spellbook = new Spellbook();
    public Spellbook dailyspells = new Spellbook();

    public List<Roll> rollhist     = new ArrayList<>();
    
    public Character(String playername)
    {
      player=playername;
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
    
    ////////////////////////////////////////////////////////////////
    //                   GETTERS                                  //
    ////////////////////////////////////////////////////////////////
    public Double getCarryCapacity()
    {
      Double tot = 0.;
      switch(race.size)
      {
        case "tiny" :tot*=0.5 ;break;
        case "small":tot*=0.75;break;
        case "large":tot*=1.25;break;
        case "huge" :tot*=1.5 ;break;
        case "gargantuan":tot*=2.0;break;
      }
      return tot;
    }
    
    public Double getCarrying()
    {
      Double tot=0.;
      for (Item item:Iterables.unmodifiableIterable(Iterables.concat(equipment,weapons,armor)))
      {
        tot+=item.weight;
      }
      return tot;
    }
    
    public Integer getSpeed()
    {
      Integer tot=race.speed;
      List<Attribute> applies = getApplicableAttributes("speed");
      tot=applyAttributes(applies,tot);
      return tot;
    }
    
    public Integer getLevel()
    {
      Integer tot = cclass.level;
      return tot; //TODO multiclassing!
    }
    
    public Integer getNextLevelXP()
    {
      return 0; //TODO FIXME
    }
    
    public Integer getInit() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException
    {
      Integer tot = getAbilityMod("DEX");
      List<Attribute> applies = getApplicableAttributes("init");
      tot=applyAttributes(applies,tot);
      return tot;
    }
    
    public Integer getAbilityMod(String ablName) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException
    {
      //get base ability
      Field ablf = this.getClass().getDeclaredField(ablName);
      Integer tot=ablf.getInt(this);
      //look for attributes that might modify it
      List<Attribute> applies = getApplicableAttributes(ablName);
      tot=applyAttributes(applies,tot);
      //calcualte mod
      return (int) floor((tot-10.)/2.);      
    }
    
    public Integer getSavingMod(String ablName) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException
    {
      //get base ability
      Integer tot = getAbilityMod(ablName);
      //look for attributes that might modify it
      List<Attribute> applies = getApplicableAttributes(ablName+"save");
      tot=applyAttributes(applies,tot);
      return tot;
    }
    
    public Integer getSkillMod(Skill skill) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException
    {
      //get basic stat
      Integer i = skills.indexOf(skill);
      Integer tot=0;
      if (skills.get(i).trained)
      {
        tot+=proficiency();
        if (skills.get(i).master){tot+=proficiency();}
      }
      tot+=skills.get(i).expert;
      tot+=getAbilityMod(skills.get(i).ability);
      //get attribute mods
      List<Attribute> applies = getApplicableAttributes(skill.name);
      tot=applyAttributes(applies,tot);
      return tot;
    }
    
    public Integer getResistance(String res)
    {
      Integer tot = resistance.get(res);
      List<Attribute> applies = getApplicableAttributes(res);
      tot=applyAttributes(applies,tot);
      return tot;
    }
    
    public Integer getTotalHP()
    {
      Integer tot = maxhp;
      List<Attribute> applies = getApplicableAttributes("maxhp");
      tot=applyAttributes(applies,tot);       
      return tot;
    }
    
    public Integer getAC()
    {
      Integer tot = 10;
      List<Attribute> applies = getApplicableAttributes("maxhp");
      tot=applyAttributes(applies,tot);
      return tot;
    }
    
    public Integer proficiency()
    {
      Integer prof;
      Integer lvl = getLevel();
      if      (lvl <= 4 ){prof=2;}
      else if (lvl <= 8 ){prof=3;}
      else if (lvl <= 12){prof=4;}
      else if (lvl <= 16){prof=5;}
      else                 {prof=6;}
      List<Attribute> applies = getApplicableAttributes("prof");
      prof=applyAttributes(applies,prof);
      return prof;
      // TODOD someday...return cclass.getProficiency(level);
    }
    
    public Double totalCurrency()
    {
      return 100*pp+gp+sp/100.;
    }

    ////////////////////////////////////////////////////////////////
    //                   UTILITY                                  //
    ////////////////////////////////////////////////////////////////
    public List<Item> getAllEquipped()
    {
      List<Item> equip = new ArrayList<>();
      for (Item item:equipment)
      {
        if (item.equipped){equip.add(item);}
      }
      for (Item item:weapons)
      {
        if (item.equipped){equip.add(item);}
      }
      for (Item item:armor)
      {
        if (item.equipped){equip.add(item);}
      }
      return equip;
    }
    
    public List<Attribute> getApplicableAttributes(String needapply)
    {
      List<Attribute> applicable = new ArrayList<>();
      //temporary/status effects
      for (Effect eff:effects)
      {
        for (Attribute attrib:eff.attributes){if (attrib.appliesTo(needapply) && attrib.active){applicable.add(attrib);}}
      }
      //equipment
      for (Item item: getAllEquipped())
      {
        for (Attribute attrib:item.effect.attributes){if (attrib.appliesTo(needapply) && attrib.active){applicable.add(attrib);}}
      }
      //race, class
      for (Effect eff:race.effects)
      {
        for (Attribute attrib:eff.attributes){if (attrib.appliesTo(needapply) && attrib.active){applicable.add(attrib);}}
      }
      for (Effect eff:cclass.effects)
      {
        for (Attribute attrib:eff.attributes){if (attrib.appliesTo(needapply) && attrib.active){applicable.add(attrib);}}
      }
      return applicable;
    }
     
    public Integer applyAttributes(List<Attribute> attribs,Integer mod)
    {
      for (Attribute attrib:attribs){mod=attrib.apply(mod);}
      return mod;
    }
    
    public void addXp(int xp){curxp+=xp;}
    
    public void addToHistory(Roll roll)
    {
      rollhist.add(0,roll);
      if (rollhist.size()>20)
      {
        rollhist = rollhist.subList(0, 20);
      }
    }
    
    //Lawful and good parses////////////////////////////////////////////
    public String lawfulParse() {
        String strLawful = "";
        if (lawful > 66) {
            strLawful += "L (" + lawful + ")";
        } else if (lawful > 32) {
            strLawful += "N (" + lawful + ")";
        } else {
            strLawful += "C (" + lawful + ")";
        }
        return strLawful;
    }
    public String goodParse() {
        String strGood = "";
        if (good > 66) {
            strGood += "G (" + good + ")";
        } else if (good > 32) {
            strGood += "N (" + good + ")";
        } else {
            strGood += "E (" + good + ")";
        }
        return strGood;
    }
    
    // XML /////////////////////////////////////////////////////////////
    public void readXML(File file)
    {
      try{
      DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                                 .newDocumentBuilder();
      Document doc = dBuilder.parse(file);
      //NodeList nodeList = doc.getChildNodes().item(0).getChildNodes();
      Node charnode = doc.getChildNodes().item(0);
      NodeList nodeList = charnode.getChildNodes();
      NamedNodeMap attrList = charnode.getAttributes();
      
      ClassFactory classfactory = new ClassFactory();
      RaceFactory racefactory = new RaceFactory();
      
      for (int i=0;i<attrList.getLength();i++)
      {
        Node attr = attrList.item(i);
        if (null != attr.getNodeName())switch (attr.getNodeName()) {
              case "name"  :name   = attr.getTextContent();break;
              case "player":player = attr.getTextContent();break;
              case "gender":gender = attr.getTextContent();break;
              case "deity" :deity  = attr.getTextContent();break;
              case "hair"  :hair   = attr.getTextContent();break;
              case "eyes"  :eyes   = attr.getTextContent();break;
              case "height":height = Double .valueOf(attr.getTextContent());break;
              case "weight":weight = Double .valueOf(attr.getTextContent());break;
              case "age"   :age    = Integer.valueOf(attr.getTextContent());break;
              case "lawful":lawful = Integer.valueOf(attr.getTextContent());break;
              case "good"  :good   = Integer.valueOf(attr.getTextContent());break;
              
              case "pp"    :pp    =Integer.valueOf(attr.getTextContent());break;
              case "gp"    :gp    =Integer.valueOf(attr.getTextContent());break;
              case "sp"    :sp    =Integer.valueOf(attr.getTextContent());break;
              case "xp"    :curxp =Integer.valueOf(attr.getTextContent());break;
              case "hp"    :curhp =Integer.valueOf(attr.getTextContent());break;
              case "temphp":temphp=Integer.valueOf(attr.getTextContent());break;
              case "maxhp" :maxhp =Integer.valueOf(attr.getTextContent());break;
                  
              case "special_names": special_names = Arrays.asList(attr.getTextContent().split(","));break;
              case "special_values":
                  List<String> strvals = Arrays.asList(attr.getTextContent().split(","));
                  for(String s:strvals) special_values.add(Integer.valueOf(s));break;
                  
              case "STR":STR=Integer.valueOf(attr.getTextContent());break;
              case "DEX":DEX=Integer.valueOf(attr.getTextContent());break;
              case "CON":CON=Integer.valueOf(attr.getTextContent());break;
              case "INT":INT=Integer.valueOf(attr.getTextContent());break;
              case "WIS":WIS=Integer.valueOf(attr.getTextContent());break;
              case "CHA":CHA=Integer.valueOf(attr.getTextContent());break;
                  
              case "languages": languages = Arrays.asList(attr.getTextContent().split(","));break;
              case "vision"   : vision    = Arrays.asList(attr.getTextContent().split(","));break;
              case "traits"   : traits    = Arrays.asList(attr.getTextContent().split(",,"));break;
              case "ideals"   : ideals    = Arrays.asList(attr.getTextContent().split(",,"));break;
              case "bonds"    : bonds     = Arrays.asList(attr.getTextContent().split(",,"));break;
              case "flaws"    : flaws     = Arrays.asList(attr.getTextContent().split(",,"));break;
              case "notes"    : notes     = Arrays.asList(attr.getTextContent().split(",,"));break;
          }
      }
      
      for (int i=0;i<nodeList.getLength();i++)
      {
        Node node = nodeList.item(i);
        if (null != node.getNodeName())switch (node.getNodeName()) 
        {
          case "skill":
            Skill skill = new Skill();
            skill.readXML(doc, node);
            skills.add(skill);
            break;
          case "effect":
            Effect effect = new Effect();
            effect.readXML(doc, node);
            effects.add(effect);
            break;
         case "feat":
            Effect feat = new Effect();
            feat.readXML(doc, node);
            feats.add(feat);
            break;
          case "item":
            Item item = new Item();
            item.readXML(doc,node);
            equipment.add(item);
            break;
          case "weapon":
            Weapon newweapon = new Weapon();
            newweapon.readXML(doc,node);
            weapons.add(newweapon);
            break;
          case "armor":
            Armor newarmor = new Armor();
            newarmor.readXML(doc,node);
            armor.add(newarmor);
            break;
          case "cclass": //need a classmaking factory for this
            CClass newclass = classfactory.XMLnewClass(doc,node);
            newclass.readXML(doc,node);
            cclass= newclass;
            break;
          case "race":
            Race newrace = racefactory.XMLnewRace(doc,node);
            newrace.readXML(doc,node);
            race= newrace;
            break;
          case "spellbook":
            Spellbook newbook = new Spellbook();
            newbook.readXML(doc,node);
            spellbook = newbook;
            break;
          case "daily":
            Spellbook daybook = new Spellbook();
            daybook.readXML(doc,node);
            dailyspells = daybook;
            break;
        }
      }
      } catch (Exception ex){ ex.printStackTrace();}
    }
    
    public int writeXML(File file) throws ParserConfigurationException, TransformerConfigurationException, TransformerException
    {
      ParseTools parser = new ParseTools();
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.newDocument();
      Element character = doc.createElement("character");
      doc.appendChild(character);
      Attr attr;
      attr = doc.createAttribute("name"  );attr.setValue(name  );character.setAttributeNode(attr);
      attr = doc.createAttribute("player");attr.setValue(player);character.setAttributeNode(attr);
      attr = doc.createAttribute("gender");attr.setValue(gender);character.setAttributeNode(attr);
      attr = doc.createAttribute("deity" );attr.setValue(deity );character.setAttributeNode(attr);
      attr = doc.createAttribute("hair"  );attr.setValue(hair  );character.setAttributeNode(attr);
      attr = doc.createAttribute("eyes"  );attr.setValue(eyes  );character.setAttributeNode(attr);
          
      attr = doc.createAttribute("height");attr.setValue(String.valueOf(height));character.setAttributeNode(attr);
      attr = doc.createAttribute("weight");attr.setValue(String.valueOf(weight));character.setAttributeNode(attr);
      attr = doc.createAttribute("age"   );attr.setValue(String.valueOf(age   ));character.setAttributeNode(attr);
      attr = doc.createAttribute("lawful");attr.setValue(String.valueOf(lawful));character.setAttributeNode(attr);
      attr = doc.createAttribute("good"  );attr.setValue(String.valueOf(good  ));character.setAttributeNode(attr);
           
      attr = doc.createAttribute("gp"   ) ;attr.setValue(String.valueOf(gp   )) ;character.setAttributeNode(attr);
      attr = doc.createAttribute("sp"   ) ;attr.setValue(String.valueOf(sp   )) ;character.setAttributeNode(attr);
      attr = doc.createAttribute("pp"   ) ;attr.setValue(String.valueOf(pp   )) ;character.setAttributeNode(attr);
      attr = doc.createAttribute("curxp") ;attr.setValue(String.valueOf(curxp)) ;character.setAttributeNode(attr);
      attr = doc.createAttribute("curhp") ;attr.setValue(String.valueOf(curhp)) ;character.setAttributeNode(attr);
      attr = doc.createAttribute("temphp");attr.setValue(String.valueOf(temphp));character.setAttributeNode(attr);
      attr = doc.createAttribute("maxhp") ;attr.setValue(String.valueOf(maxhp)) ;character.setAttributeNode(attr);
           
      attr = doc.createAttribute("special_names" );attr.setValue(String.valueOf(special_names ));character.setAttributeNode(attr);
      attr = doc.createAttribute("special_values");attr.setValue(String.valueOf(special_values));character.setAttributeNode(attr);
      
      
      attr = doc.createAttribute("STR");attr.setValue(String.valueOf(STR));character.setAttributeNode(attr);
      attr = doc.createAttribute("DEX");attr.setValue(String.valueOf(DEX));character.setAttributeNode(attr);
      attr = doc.createAttribute("CON");attr.setValue(String.valueOf(CON));character.setAttributeNode(attr);
      attr = doc.createAttribute("INT");attr.setValue(String.valueOf(INT));character.setAttributeNode(attr);
      attr = doc.createAttribute("WIS");attr.setValue(String.valueOf(WIS));character.setAttributeNode(attr);
      attr = doc.createAttribute("CHA");attr.setValue(String.valueOf(CHA));character.setAttributeNode(attr);
      
      attr = doc.createAttribute("languages");attr.setValue(parser.strjoin(languages,", "));character.setAttributeNode(attr);
      attr = doc.createAttribute("vision"   );attr.setValue(parser.strjoin(vision   ,", "));character.setAttributeNode(attr);
      attr = doc.createAttribute("traits"   );attr.setValue(parser.strjoin(traits   ,",, "));character.setAttributeNode(attr);
      attr = doc.createAttribute("ideals"   );attr.setValue(parser.strjoin(ideals   ,",, "));character.setAttributeNode(attr);
      attr = doc.createAttribute("bonds"    );attr.setValue(parser.strjoin(bonds    ,",, "));character.setAttributeNode(attr);
      attr = doc.createAttribute("flaws"    );attr.setValue(parser.strjoin(flaws    ,",, "));character.setAttributeNode(attr);
      attr = doc.createAttribute("notes"    );attr.setValue(parser.strjoin(notes    ,",, "));character.setAttributeNode(attr);
    
      for (Skill skill:skills)
      {
        Element newnode = doc.createElement("skill");
        skill.writeXML(doc, newnode);
        character.appendChild(newnode);
      }
      for (Effect eff:effects)
      {
        Element newnode = doc.createElement("effect");
        eff.writeXML(doc, newnode);
        character.appendChild(newnode);
      }
      for (Effect eff:feats)
      {
        Element newnode = doc.createElement("feat");
        eff.writeXML(doc, newnode);
        character.appendChild(newnode);
      }
      for (Item item:equipment)
      {
        Element newnode = doc.createElement("item");
        item.writeXML(doc, newnode);
        character.appendChild(newnode);
      }
      for (Weapon weapon:weapons)
      {
        Element newnode = doc.createElement("weapon");
        weapon.writeXML(doc, newnode);
        character.appendChild(newnode);
      }
      for (Armor aarmor:armor)
      {
        Element newnode = doc.createElement("armor");
        aarmor.writeXML(doc, newnode);
        character.appendChild(newnode);
      }
      
      Element newnode;
      newnode = doc.createElement("cclass"   );cclass     .writeXML(doc, newnode);character.appendChild(newnode);
      newnode = doc.createElement("race"     );race       .writeXML(doc, newnode);character.appendChild(newnode);
      newnode = doc.createElement("spellbook");spellbook  .writeXML(doc, newnode);character.appendChild(newnode);
      newnode = doc.createElement("daily"    );dailyspells.writeXML(doc, newnode);character.appendChild(newnode);
      
      TransformerFactory transfactory = TransformerFactory.newInstance();
      Transformer transformer = transfactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","4");
      DOMSource source = new DOMSource(doc);
      try
      {
        file.createNewFile();
      } 
      catch (IOException ioe){}
      StreamResult result = new StreamResult(file);
      StreamResult res = new StreamResult(System.out);
      
      transformer.transform(source, result);
      System.out.println("Successfully saved.");
      return 0;
      
    }
}


/*
  TODO
  initialize resistance to all 0s: acid, bludgeon, cold, fire, force, lightning, 
                necrotic, piercing, poison, psuchic, radiant, slashing, thunder
*/