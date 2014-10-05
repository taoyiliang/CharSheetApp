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
public abstract class LimitAttribute extends Attribute{
  protected Integer limit;
  public String attribtype = "upper limit";
  
  public void setModifier(Integer mod)
  {
    limit=mod;
  }
}
