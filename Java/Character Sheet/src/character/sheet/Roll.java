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
  public HashMap<String,List<Object> > dice = new HashMap<>();
  public HashMap<String,Integer> scalars = new HashMap<>();
  public HashMap<String,Integer> reslist = new HashMap<>();
  public Integer res=0;
  public Integer secondroll=0;
  public Integer crit=0;
  
  public Roll(){}
  public void addRoll(String source, String roll,Integer adv,Boolean crit,Integer mincrit)
  {
    adv     = adv     != null ? adv     : 0;
    crit    = crit    != null ? crit    : false;
    mincrit = mincrit != null ? mincrit : 20;
    if (dice.containsKey(source))
    {
      source+="_1";
    }
    List<Object> putlist = new ArrayList<>();
    putlist.add(roll   );
    putlist.add(adv    );
    putlist.add(crit   );
    putlist.add(mincrit);
    dice.put(source,putlist);
  }
  public void addMod(String source, Integer scalar)
  {
    if (scalars.containsKey(source))
    {
      source+="_1";
    }
    scalars.put(source,scalar);
  }
  
  public HashMap<String, Integer> roll(Roller rlr)
  {
    res = 0;
    for (String key : dice.keySet())
    {
      List<Object> rollist = dice.get(key);
      String sroll    =                 String.valueOf(rollist.get(0) );
      Integer adv     = Integer.valueOf(String.valueOf(rollist.get(1)));
      Boolean ccrit    = Boolean.valueOf(String.valueOf(rollist.get(2)));
      Integer mincrit = Integer.valueOf(String.valueOf(rollist.get(3)));
      
      String[] rollsplit = sroll.split("d");
      Integer num = 1;
      if (!rollsplit[0].isEmpty()){num = Integer.valueOf(rollsplit[0]);}
      Integer size = Integer.valueOf(sroll.split("d")[1]);
      List<Integer> rollL = rlr.roll(num, size,adv,ccrit,mincrit);
      Integer roll = rollL.get(0);
      res+= roll;
      reslist.put(key+" ("+sroll+")",roll);
      if (adv!=0)
      {
        reslist.put(key+" (2nd)",rollL.get(2));
      }
      if (ccrit)
      {
        crit = rollL.get(1);
      }
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
    Integer i = 0;
    for (String entry:entries)
    {
      i+=1;
      if (entry.contains("d"))
      {
        addRoll(label+"_"+String.valueOf(i),entry,0,false,20);
      }
      else //TODO test for ability to become an integer!
      {
        addMod(label+"_"+String.valueOf(i),Integer.valueOf(entry));
      }
    }
  }
}
