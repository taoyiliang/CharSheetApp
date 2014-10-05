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
public class GenericAttribute extends Attribute
{
  public String attribtype = "generic";
  
  public void setModifier(Integer mod){}
  public Integer apply(Integer mod){return mod;}
  
}
