/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package character.sheet;

/**
 *
 * @author TaoYiLiang
 */
public class CCRogue extends CClass{
  public CCRogue(){}
  
  @Override
  public void setSubclass(String str)
  {
    switch (str)
    {
      case "Thief"          :subclass = new SCCThief()          ;break;
      case "Assassin"       :subclass = new SCCAssassin()       ;break;
      case "ArcaneTrickster":subclass = new SCCArcaneTrickster();break;
    }
  }
  
  public void doLevelUp(Integer lvl)
  {
    //TODO
  }
}

class SCCThief extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}

class SCCAssassin extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}

class SCCArcaneTrickster extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}