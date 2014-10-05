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
public class CCBard extends CClass{
  
  public CCBard(){}
  
  @Override
  public void setSubclass(String str)
  {
    switch (str)
    {
      case "Lore" :subclass = new SCCLore() ;break;
      case "Valor":subclass = new SCCValor();break;
    }
  }
  
  public void doLevelUp(Integer lvl)
  {
    //TODO
  }
}

class SCCLore extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}

class SCCValor extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}