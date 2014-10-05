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
public class CCPaladin extends CClass{
  public CCPaladin(){}
  
  @Override
  public void setSubclass(String str)
  {
    switch (str)
    {
      case "Devotion" :subclass = new SCCDevotion() ;break;
      case "Ancients" :subclass = new SCCAncients() ;break;
      case "Vengeance":subclass = new SCCVengeance();break;
    }
  }
  
  public void doLevelUp(Integer lvl)
  {
    //TODO
  }
}

class SCCDevotion extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}

class SCCAncients extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}

class SCCVengeance extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}