/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package character.sheet;

import java.io.File;
import java.lang.reflect.Field;

/**
 *
 * @author Reid
 */
public class CharacterSheet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IllegalAccessException {
        System.out.println("Hi.");
        ParseTools parser = new ParseTools();
        Character bob = new Character("DnD Dave");
        File charfile = new File("bob.xml");
        bob.readXML(charfile);
        System.out.println(parser.classToString(bob));

    }
    
}
