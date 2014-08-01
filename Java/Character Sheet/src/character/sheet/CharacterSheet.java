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
    public static void main(String[] args) throws IllegalAccessException {
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

        //Create charsheet and manager
        Charsheet charsheet = new Charsheet();
        CharSheetManager manager = new CharSheetManager();

        //assign charsheet and character
        manager.setCharSheet(charsheet);
        manager.setCharacter(bob);

        //run Character Sheet
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                charsheet.setVisible(true);
                charsheet.setLocationRelativeTo(null);
            }
        });
        
    }
    
}
