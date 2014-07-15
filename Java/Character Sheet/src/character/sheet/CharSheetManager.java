/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package character.sheet;

import java.util.EmptyStackException;


/**
 *
 * @author Reid
 */
public class CharSheetManager 
{
    private Charsheet charsheet;
    private Character character;
    
    
    public void setCharSheet(Charsheet newsheet)
    {
        charsheet = newsheet;
    }
    public void setCharacter(Character newchar)
    {
        if (charsheet!=null)
        {
        character = newchar;
        refreshCharacter();
        }
        else
        {
            throw new EmptyStackException();
        }
    }
    public void refreshCharacter()
    {
        refreshCharacterInfo();
    }
    public void refreshCharacterInfo()
    {
        charsheet.lblRace.setText(character.race.name);
        charsheet.lblClass.setText(character.cclass.name);
        charsheet.lbl2ndClass.setText("");
        charsheet.lblHeight.setText(String.valueOf(character.height));
        charsheet.lblWeight.setText(String.valueOf(character.weight));
        charsheet.lblSize.setText(character.race.size);
        charsheet.lblAge.setText(String.valueOf(character.age));
        charsheet.lblGender.setText(character.gender);
        charsheet.lblHair.setText(character.hair);
        charsheet.lblPlayerName.setText(character.player);
        
        String Alignment = character.lawfulParse() + " " + character.goodParse();
        
        charsheet.lblAlignment.setText(Alignment);
        charsheet.lblDeity.setText(character.deity);
        charsheet.lblEyes.setText(character.eyes);
    }
}

