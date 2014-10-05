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
public class CCDruid extends CClass{
  public CCDruid(){}
  
  @Override
  public void setSubclass(String str)
  {
    switch (str)
    {
      case "Land":subclass = new SCCLand();break;
      case "Moon":subclass = new SCCMoon();break;
    }
  }
  
  public void doLevelUp(Integer lvl)
  {
    //TODO
  }
}


class SCCLand extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}


class SCCMoon extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}