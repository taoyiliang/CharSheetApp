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
    private Character character4;

    public void setToolWindow(ToolWindow newwindow) {
        toolWindow = newwindow;
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
