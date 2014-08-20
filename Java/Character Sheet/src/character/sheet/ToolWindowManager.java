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
public class ToolWindowManager {

    private ToolWindow toolWindow;
    private Character character;

    public void setToolWindow(ToolWindow newwindow) {
        toolWindow = newwindow;
    }

    public void setCharacter(Character newchar) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        if (toolWindow != null) {
            character = newchar;
            refreshToolWindow();
        } else {
            throw new EmptyStackException();
        }
    }

    public void refreshToolWindow() {
        refreshNotes();
    }
    
    private final static String newline = "\n";
    
    public void refreshNotes() {
        //for (int i=0; i<character.notes.size(); i++) {
        //    character.notes.
        //}
        String notes = new String();
        
        for (int i=0; i<character.notes.size(); i++) {
            notes += character.notes.get(i) + newline;
        }
        toolWindow.taNotes.setLineWrap(true);
        toolWindow.taNotes.setWrapStyleWord(true);
        
        toolWindow.taNotes.setText(notes);
        
                //.substring(1, String.valueOf(character.notes).length() - 1));
    }
}
