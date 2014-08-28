/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package character.sheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author TaoYiLiang
 */
public class Roll {
  
  //index String is the name of the source (STR mod, enh bonus, Bless, etc)
  public HashMap<String,String> dice = new HashMap<>();
  public HashMap<String,Integer> scalars = new HashMap<>();
  public HashMap<String,Integer> reslist = new HashMap<>();
  public Integer res;
  
  public Roll(){}
  public void addRoll(String source, String roll)
  {
    dice.put(source,roll);
  }
  public void addMod(String source, Integer scalar)
  {
    scalars.put(source,scalar);
  }
  
  public HashMap<String, Integer> roll(Roller rlr)
  {
    res = 0;
    for (String key : dice.keySet())
    {
      String[] rollsplit = dice.get(key).split("d");
      Integer num = 1;
      System.out.println(dice.get(key));
      if (!rollsplit[0].isEmpty()){num = Integer.valueOf(rollsplit[0]);}
      Integer size = Integer.valueOf(dice.get(key).split("d")[1]);
      Integer roll = rlr.roll(num, size);
      res+=roll;
      reslist.put(key+" ("+dice.get(key)+")",roll); 
    }
    for (String key : scalars.keySet())
    {
      res+=Integer.valueOf(scalars.get(key));
      reslist.put(key,Integer.valueOf(scalars.get(key)));
    }
    reslist.put("result",res);
    return reslist;
  }
  
  public void addList(String label,List<String> entries)
  {
    for (String entry:entries)
    {
      if (entry.contains("d"))
      {
        addRoll(label,entry);
      }
      else //TODO test for ability to become an integer!
      {
        addMod(label,Integer.valueOf(entry));
      }
    }
  }
}
