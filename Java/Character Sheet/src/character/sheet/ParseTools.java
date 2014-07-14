/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package character.sheet;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TaoYiLiang
 */
public class ParseTools {
  public ParseTools(){}
  
  public HashMap<String,Integer> readMap(String str)
  {
    HashMap<String,Integer> newmap = new HashMap<>();
    List<String> list = Arrays.asList(str.substring(1,str.length()-2).split(","));
    for (String list1 : list) {
      String name = list1.split("=")[0];
      Integer val = Integer.valueOf(list1.split("=")[1]);
      newmap.put(name,val);
    }
    return newmap;
  }
  
  public String classToString(Object item)
  {
    String str="PRINTOUT: "+String.valueOf(item)+"\n";
    Field[] fields = item.getClass().getFields();
    for (Field field: fields)
    {
      try {
        String name = field.getName();
        Object value = field.get(item);
        str+= "  "+name+": "+value.toString()+"\n";
      } catch (IllegalArgumentException ex) {
        Logger.getLogger(ParseTools.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex) {
        Logger.getLogger(ParseTools.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    str+="END PRINTOUT "+String.valueOf(item)+"\n";
    return str;
  }
}
