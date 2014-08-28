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

    public void rollInit(){
        String initMiscRoll;
        String weaponUsed;
        String total;
        String label;
        String adv;
        Boolean firstRound;
        Boolean prepared;
        
        label = "Initiative";
        
        initMiscRoll = String.valueOf(toolWindow.txtMiscModInit);
        
        
        //Parse input into parts, place into list
        List<String> rollInputs = roller.parseRolls(initMiscRoll);
        
        Roll roll = new Roll();
        
        roll.addList(label, rollInputs);
        roll.roll(roller);
        
        total = String.valueOf(roll.res);
        
        //Set display
        toolWindow.lblRollResult.setText(total);
        toolWindow.txtRollOverride.setText(total);
        toolWindow.lblRollType.setText("Initiative");
    }
    
    public void rollAttack(){
        String attMiscRoll;
        String weaponUsed;
        String total;
        String label;
        String adv;
        
        
        label = "Attack";
        
        attMiscRoll = String.valueOf(toolWindow.txtMiscModAttack);
        
        
        //Parse input into parts, place into list
        List<String> rollInputs = roller.parseRolls(attMiscRoll);
        
        Roll roll = new Roll();
        
        roll.addList(label, rollInputs);
        roll.roll(roller);
        
        total = String.valueOf(roll.res);
        
        //Set display
        toolWindow.lblRollResult.setText(total);
        toolWindow.txtRollOverride.setText(total);
        toolWindow.lblRollType.setText("Attack");
    }
    
    public void rollGenericRoller(){
        String genRoll;
        String genType;
        String total;
        String label;
        String adv;
        
        label = "Generic";
        
        genRoll = toolWindow.txtGenericRollInput.getText();
        genType = toolWindow.txtGenericRollWhatFor.getText();
        
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
