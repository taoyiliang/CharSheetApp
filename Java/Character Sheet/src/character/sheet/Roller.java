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
  
  public Integer roll(Integer num,Integer size)
  {
    Integer res = 0;
    for (int i=0;i<num;i++)
    {
      res+=randInt(1,size);
    }
    return res;
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
