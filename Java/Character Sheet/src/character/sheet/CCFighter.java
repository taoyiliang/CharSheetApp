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
public class CCFighter extends CClass{
  public CCFighter(){}
  
  @Override
  public void setSubclass(String str)
  {
    switch (str)
    {
      case "Champion"      :subclass = new SCCChampion()      ;break;
      case "BattleMaster"  :subclass = new SCCBattleMaster()  ;break;
      case "EldritchKnight":subclass = new SCCEldritchKnight();break;
    }
  }
  
  public void doLevelUp(Integer lvl)
  {
    //TODO
  }
}

class SCCChampion extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}

class SCCBattleMaster extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}

class SCCEldritchKnight extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}