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
public class CCGeneric extends CClass{
  public CCGeneric(){}
  
  @Override
  public void setSubclass(String str)
  {
    switch (str)
    {
      case "Generic":subclass = new SCCGeneric();break;
    }
  }
  
  public void doLevelUp(Integer lvl)
  {
    //TODO
  }
}


class SCCGeneric extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}