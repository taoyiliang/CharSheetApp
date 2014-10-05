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
public class CCSorcerer extends CClass{
  public CCSorcerer(){}
  
  @Override
  public void setSubclass(String str)
  {
    switch (str)
    {
      case "DraconicBloodline":subclass = new SCCDraconicBloodline();break;
      case "WildMagic"        :subclass = new SCCWildMagic()        ;break;
    }
  }
  
  public void doLevelUp(Integer lvl)
  {
    //TODO
  }
}

class SCCDraconicBloodline extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}

class SCCWildMagic extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}