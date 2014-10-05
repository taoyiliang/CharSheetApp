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
public class LowerLimitAttribute extends LimitAttribute{

  
  @Override
  public Integer apply(Integer mod)
  {
    return Math.min(limit, mod);
  }
}
