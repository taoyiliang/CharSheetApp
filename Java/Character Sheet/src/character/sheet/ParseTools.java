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
    
    List<String> list = Arrays.asList(str.substring(1,str.length()-1).split(","));
    for (String list1 : list) {
      String name = list1.split("=")[0].trim();
      Integer val = Integer.valueOf(list1.split("=")[1].trim());
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
        System.out.print("name: "+name);
        Object value = field.get(item);
        System.out.println("  val: "+value);
        str+= "  "+name+": "+value.toString()+"\n";
      } catch (IllegalArgumentException | IllegalAccessException ex) {
        Logger.getLogger(ParseTools.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    str+="END PRINTOUT "+String.valueOf(item)+"\n";
    return str;
  }
  
  public static <T> String strjoin(List<T> list, String delim)
  {
    String str="";
    if (list.size()>0){str+=list.get(0);}
    for (int i=1;i<list.size();i++)
    {
      str+=delim+list.get(i).toString();
    }
    return str;
  }
}
