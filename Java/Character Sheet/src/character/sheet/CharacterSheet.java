/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character.sheet;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import javax.swing.UIManager;

/**
 *
 * @author Reid
 */
public class CharacterSheet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Charsheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Charsheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Charsheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Charsheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        System.out.println("Hi.");
        ParseTools parser = new ParseTools();
        Character bob = new Character("DnD Dave");
        File charfile = new File("bob.xml");
        bob.readXML(charfile);
        System.out.println(parser.classToString(bob));

        System.out.println(String.valueOf(bob.getAbilityMod("STR")));
        //System.out.println(String.valueOf(Integer.valueOf(2)));
        //Create charsheet, charsheet manager, and toolwindow manager
        final Charsheet charsheet = new Charsheet();
        CharSheetManager manager = new CharSheetManager();
        //ToolWindow toolwindow = new ToolWindow();
        //ToolWindowManager toolmanager = new ToolWindowManager();
        
        //assign charsheet and character
        manager.setCharSheet(charsheet);
        manager.setCharacter(bob);
        //toolmanager.setToolWindow(toolwindow);
        //toolmanager.setCharacter(bob);

        //run Character Sheet
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                charsheet.setVisible(true);
                charsheet.setLocationRelativeTo(null);
            }
        });
        
    }
    
}
