/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package character.sheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author TaoYiLiang
 */
public class CClass {
    public String name;
    public Integer hitdie;
    List<String> profs  = new ArrayList<>();
    List<String> skills  = new ArrayList<>();
    List<Attribute> attributes  = new ArrayList<>();
    public CClass(){}
    
    // TODO public List<String> levelAttributes(Integer level){}
    
    public void writeXML(Document doc,Element elem)
    {
        elem.setAttribute("name"  ,               name   );
        elem.setAttribute("hitdie",String.valueOf(hitdie));
        elem.setAttribute("profs" ,String.valueOf(profs ));
        elem.setAttribute("skills",String.valueOf(skills ));
        for (int i=0;i<attributes.size();i++)
        {
          attributes.get(i).writeXML(doc,elem);
        }
    }
    public void readXML(Document doc,Node node)
    {
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i=0;i<nodeMap.getLength();i++)
        {
          switch (nodeMap.item(i).getNodeName()) {
            case "name"  :name   =                 node.getTextContent()            ;break;
            case "hitdie":hitdie = Integer.valueOf(node.getTextContent())           ;break;
            case "profs" :profs  = Arrays.asList(  node.getTextContent().split(","));break;
            case "skills":skills = Arrays.asList(  node.getTextContent().split(","));break;
            }
        }
    }
}
