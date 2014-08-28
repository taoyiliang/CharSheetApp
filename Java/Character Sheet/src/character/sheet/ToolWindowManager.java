/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character.sheet;

import java.util.EmptyStackException;
import java.util.List;

/**
 *
 * @author Reid
 */
public class ToolWindowManager {

    public ToolWindow toolWindow;
    private Character character4;
    public Roller roller;
    
    public ToolWindowManager(){
        roller = new Roller();
    }
    
    public void setToolWindow(ToolWindow newwindow) {
        toolWindow = newwindow;
        newwindow.setManager(this);
    }

    
    
    public void setCharacter(Character newchar) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        if (toolWindow != null) {
            character4 = newchar;
            refreshToolWindow();
        } else {
            throw new EmptyStackException();
        }
    }

    public void refreshToolWindow() {
        refreshNotes();
        
    }

    public void rollGenericRoller(){
        String genRoll;
        String genType;
        String total;
        String label;
        
        
        label = "Generic";
        
        genRoll = String.valueOf(toolWindow.txtGenericRollInput);
        genType = String.valueOf(toolWindow.txtGenericRollWhatFor);
        
        //Parse input into parts, place into list
        List<String> rollInputs = roller.parseRolls(genRoll);
        
        Roll roll = new Roll();
        
        roll.addList(label, rollInputs);
        roll.roll(roller);
        
        total = String.valueOf(roll.res);
        
        //Set display
        toolWindow.lblRollResult.setText(total);
        toolWindow.txtRollOverride.setText(total);
        toolWindow.lblRollType.setText(genType);
    }
    
    
    private final static String newline = "\n";

    public void refreshNotes() {
        String notes = new String();
        toolWindow.txtGenericRollInput.setText(notes);
        for (int i = 0; i < character4.notes.size(); i++) {
            notes += character4.notes.get(i).trim() + newline;
        }

        toolWindow.taNotes.setText(notes);
        System.out.println(toolWindow.taNotes);
        
    }
}
