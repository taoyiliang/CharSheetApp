/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package character.sheet;

import java.util.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.reflect.Field;



/**
 *
 * @author TaoYiLiang
 */
public class Character
{
    public String name,gender,player,deity,hair,eyes;
    public double height,weight,carrying,capacity,speed;
    public int age,level,curxp,nextxp,init,gp,sp,cp,curhp,maxhp,DTH;
    public int STR,DEX,CON,INT,WIS,CHA,strmod,dexmod,conmod,intmod,wismod,chamod;
    public int lawful,good;
    
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

    public List<Attribute> attributes = new ArrayList<>();
    public List<Item>      equipment  = new ArrayList<>();
    public List<Weapon>    weapons    = new ArrayList<>();
    public List<Armor>     armor    = new ArrayList<>();
    /* TODO
    Race race;
    CClass class;
    Spellbook spellbook;
    Spellbook dailyspells;
    Roller roller;
    
    List<Skill>     skills     = new ArrayList<Skill>;
    List<Attribute> attributes = new ArrayList<Attributes>;
    List<Item>      equipment  = new ArrayList<Item>;
    List<Weapon>    weapons    = new ArrayList<Weapon>;
    List<Armor>     armor      = new ArrayList<Armor>;
    
    List<Minion>    minions    = new ArrayList<Minion>;
    
    public void addCurrency(ArrayList<Integer> newcurr){}
    public void addCurrency(double newcurr){}
    
    public Integer rollInit(Roller,Action)
    public Integer rollAttack(Roller,Action)
    public Integer rollDamage(Roller,Action)
    public Integer rollSkill(Skill,Action)
    public Integer rollSave(String)
    
    public void addToSpellbook(Spellbook,Spell)
    
    public void writeXML();
    
    
     - Action class needs defining, with subs useWeapon, simple, castSpell
    public Integer rollInit(Action,
    */
    public Character(String playername){player=playername;}
    public void addXp(int xp){curxp+=xp;}
    
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
              
              case "gp"    :gp   =Integer.valueOf(attr.getTextContent());break;
              case "sp"    :sp   =Integer.valueOf(attr.getTextContent());break;
              case "cp"    :cp   =Integer.valueOf(attr.getTextContent());break;
              case "xp"    :curxp=Integer.valueOf(attr.getTextContent());break;
              case "hp"    :curhp=Integer.valueOf(attr.getTextContent());break;
              case "maxhp" :maxhp=Integer.valueOf(attr.getTextContent());break;
                  
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
          case "attribute":
            Attribute attr = new Attribute();
            attr.readXML(doc, node);
            attributes.add(attr);
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
        }
      }
      } catch (Exception ex){ ex.printStackTrace();}
    }
    
}


/*
  TODO
  initialize resistance to all 0s: acid, bludgeon, cold, fire, force, lightning, 
                necrotic, piercing, poison, psuchic, radiant, slashing, thunder
*/