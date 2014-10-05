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
public class CCMonk extends CClass{
  public CCMonk(){}
  
  @Override
  public void setSubclass(String str)
  {
    switch (str)
    {
      case "OpenHand"    :subclass = new SCCOpenHand()    ;break;
      case "Shadow"      :subclass = new SCCShadow()      ;break;
      case "FourElements":subclass = new SCCFourElements();break;
    }
  }
  
  public void doLevelUp(Integer lvl)
  {
    //TODO
  }
}


class SCCOpenHand extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}

class SCCShadow extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}

class SCCFourElements extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}