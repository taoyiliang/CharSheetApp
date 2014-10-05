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
public class ModAttribute extends Attribute
{
  protected Integer to_add;
  public String attribtype = "modifier";
  
  public void setModifier(Integer mod)
  {
    to_add=mod;
  }
  
  public Integer apply(Integer mod)
  {
    return to_add + mod;
  }
}
