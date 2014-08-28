/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package character.sheet;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TaoYiLiang
 */
public class Roller {
  private final SecureRandom _rand = new SecureRandom();
  
  public Roller()
  {
    byte[] bytes = new byte[512];
    _rand.nextBytes(bytes);
  }
  
  public Integer randInt(Integer a, Integer b)
  {
    Integer res = _rand.nextInt(b-a+1);
    res+=a;
    return res;
  }
  
  public Integer rollSimple(Integer num,Integer size)
  {
    Integer res = 0;
    for (int i=0;i<num;i++)
    {
      res+=randInt(1,size);
    }
    return res;
  } //do it better in roll below
  
  public List<Integer> roll(Integer num, Integer size, Integer Adv, Boolean checkCrit, Integer mincrit)
  {
    List<Integer> ret = new ArrayList<>();
    ret.add(0); //index 0 is result
    ret.add(0); //index 1 is crit status
    ret.add(0); //index 2 is alternate roll
    Integer rollres = rollSimple(1,size);
    if (Adv!=0)
    {
      Integer rollres1 = rollres;
      Integer rollres2 = rollSimple(1,size);
      if (Adv==1)
      {
        ret.set(0,Math.max(rollres1, rollres2));
        ret.set(2,Math.min(rollres1, rollres2));
      }
      else if (Adv==-1)
      {
        ret.set(2,Math.max(rollres1, rollres2));
        ret.set(0,Math.min(rollres1, rollres2));
      }
    }
    if (checkCrit)
    {
      if (rollres>=mincrit)
      {
        ret.set(1,1); //it crit!
      }
      else if (rollres==1)
      {
        ret.set(1,-1);
      }
    }
    return ret;
  }
  
  public List<String> parseRolls(String str)
  {
    List<String> res = new ArrayList<>();
    //first, parse by plus
    for (String entry:str.split("\\+"))
    {
      //if minus in there, break it up
      if (entry.contains("-"))
      {
        for (String e:entry.split("-"))
        {
          if (!e.isEmpty()){res.add(e.trim());};
        }
      }
      else
      {
        if (!entry.isEmpty()){res.add(entry.trim());}
      }
    }
    return res;
  }
}
