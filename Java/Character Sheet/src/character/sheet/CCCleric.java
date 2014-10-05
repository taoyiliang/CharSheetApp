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
public class CCCleric extends CClass{
  public CCCleric(){}
  
  @Override
  public void setSubclass(String str)
  {
    switch (str)
    {
      case "Knowledge":subclass = new SCCKnowledge();break;
      case "Life"     :subclass = new SCCLife()     ;break;
      case "Light"    :subclass = new SCCLight()    ;break;
      case "Nature"   :subclass = new SCCNature()   ;break;
      case "Tempest"  :subclass = new SCCTempest()  ;break;
      case "Trickery" :subclass = new SCCTrickery() ;break;
      case "War"      :subclass = new SCCWar()      ;break;
    }
  }
  
  public void doLevelUp(Integer lvl)
  {
    //TODO
  }
}


class SCCKnowledge extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}

class SCCLife extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}

class SCCLight extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}

class SCCNature extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}

class SCCTempest extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}

class SCCTrickery extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}

class SCCWar extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}