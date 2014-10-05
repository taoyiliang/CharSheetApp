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
public class CCWarlock extends CClass{
  public CCWarlock(){}
  
  @Override
  public void setSubclass(String str)
  {
    switch (str)
    {
      case "Archfey"    :subclass = new SCCArchfey()    ;break;
      case "Fiend"      :subclass = new SCCFiend()      ;break;
      case "GreatOldOne":subclass = new SCCGreatOldOne();break;
    }
  }
  
  public void doLevelUp(Integer lvl)
  {
    //TODO
  }
}

class SCCArchfey extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}

class SCCFiend extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}

class SCCGreatOldOne extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}