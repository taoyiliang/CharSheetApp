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
public abstract class Subclass extends CClass
{
  public CClass cclass;
  public Subclass(){}
  public Subclass(CClass cclass)
  {
    this.cclass = cclass;
    this.subclass = null;
  }
  public void setSubclass(String str){};
  
}
