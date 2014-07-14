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
public class Subrace extends Race
{
  private String subrace;
  public Race race;
  public Subrace(Race race)
  {
    this.race = race;
    this.subrace = null;
  }
}