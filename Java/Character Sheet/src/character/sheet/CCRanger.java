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
public class CCRanger extends CClass{
  public CCRanger(){}
  
  @Override
  public void setSubclass(String str)
  {
    switch (str)
    {
      case "Hunter"     :subclass = new SCCHunter()     ;break;
      case "BeastMaster":subclass = new SCCBeastMaster();break;
    }
  }
  
  public void doLevelUp(Integer lvl)
  {
    //TODO
  }
}

class SCCHunter extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}

class SCCBeastMaster extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}