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
public class CCWizard extends CClass{
  public CCWizard(){}
  
  @Override
  public void setSubclass(String str)
  {
    switch (str)
    {
      case "Abjuration"   :subclass = new SCCAbjuration()   ;break;
      case "Conjuration"  :subclass = new SCCConjuration()  ;break;
      case "Divination"   :subclass = new SCCDivination()   ;break;
      case "Enchantment"  :subclass = new SCCEnchantment()  ;break;
      case "Evocation"    :subclass = new SCCEvocation()    ;break;
      case "Illusion"     :subclass = new SCCIllusion()     ;break;
      case "Necromancy"   :subclass = new SCCNecromancy()   ;break;
      case "Transmutation":subclass = new SCCTransmutation();break;
    }
  }
  
  public void doLevelUp(Integer lvl)
  {
    //TODO
  }
}


class SCCAbjuration extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}

class SCCConjuration extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}

class SCCDivination extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}

class SCCEnchantment extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}

class SCCEvocation extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}

class SCCIllusion extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}

class SCCNecromancy extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}

class SCCTransmutation extends Subclass
{
  @Override
  public void doLevelUp(Integer lvl)
  {
    //todo
  }
}