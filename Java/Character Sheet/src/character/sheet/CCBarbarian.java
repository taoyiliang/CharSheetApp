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
public class CCBarbarian extends CClass
{
  public CCBarbarian(){}
  
  @Override
  public void setSubclass(String str)
  {
    switch (str)
    {
      case "Berserker"   :subclass = new SCCBerserker()   ;break;
      case "TotemWarrior":subclass = new SCCTotemWarrior();break;
    }
  }
  
  @Override
  public void doLevelUp(Integer lvl)
  {
    //TODO
  }
}

class SCCBerserker extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}

class SCCTotemWarrior extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}