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
public class EffectsManager {

    public Effects effects;
    private Character character;

    public void setEffectsWindow(Effects newwindow) {
        effects = newwindow;
        newwindow.setManager(this);
        refreshAppliesSub1();
        refreshAppliesSub2();
        refreshAppliesSub3();
        refreshAppliesSub4();
        refreshAppliesSub5();
        refreshAppliesSub6();
    }
    public void setCharacter(Character newchar) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        if (effects != null) {
            character = newchar;
        } else {
            throw new EmptyStackException();
        }
    }

//    String[] names = character.resistance.values().toArray(new String[character.resistance.values().size()]);

    public void refreshAppliesSub1() {
        effects.cbAppliesSub1.removeAllItems();
        //Ability, Save, Skill, Resistance, MaxHP, AC, Init
        if (effects.cbAppliesTo1.getItemAt(effects.cbAppliesTo1.getSelectedIndex()) == "Ability") {
            effects.cbAppliesSub1.addItem("Strength");
            effects.cbAppliesSub1.addItem("Dexterity");
            effects.cbAppliesSub1.addItem("Constitution");
            effects.cbAppliesSub1.addItem("Intelligence");
            effects.cbAppliesSub1.addItem("Wisdom");
            effects.cbAppliesSub1.addItem("Charisma");
        } else if (effects.cbAppliesTo1.getItemAt(effects.cbAppliesTo1.getSelectedIndex()) == "Save") {
            effects.cbAppliesSub1.addItem("Fortitude");
            effects.cbAppliesSub1.addItem("Reflex");
            effects.cbAppliesSub1.addItem("Will");
        } else if (effects.cbAppliesTo1.getItemAt(effects.cbAppliesTo1.getSelectedIndex()) == "Skill") {
            for (Skill skill : character.skills) {
                effects.cbAppliesSub1.addItem(skill.name);
            }
        } else if (effects.cbAppliesTo1.getItemAt(effects.cbAppliesTo1.getSelectedIndex()) == "Resistance") {
            for (String name : character.resistance.keySet()) {
                effects.cbAppliesSub1.addItem(name);
            }
        } else {
            effects.cbAppliesSub1.addItem("---");
        }

    }
    public void refreshAppliesSub2() {
        effects.cbAppliesSub2.removeAllItems();
        //Ability, Save, Skill, Resistance, MaxHP, AC, Init
        if (effects.cbAppliesTo2.getItemAt(effects.cbAppliesTo2.getSelectedIndex()) == "Ability") {
            effects.cbAppliesSub2.addItem("Strength");
            effects.cbAppliesSub2.addItem("Dexterity");
            effects.cbAppliesSub2.addItem("Constitution");
            effects.cbAppliesSub2.addItem("Intelligence");
            effects.cbAppliesSub2.addItem("Wisdom");
            effects.cbAppliesSub2.addItem("Charisma");
        } else if (effects.cbAppliesTo2.getItemAt(effects.cbAppliesTo2.getSelectedIndex()) == "Save") {
            effects.cbAppliesSub2.addItem("Fortitude");
            effects.cbAppliesSub2.addItem("Reflex");
            effects.cbAppliesSub2.addItem("Will");
        } else if (effects.cbAppliesTo2.getItemAt(effects.cbAppliesTo2.getSelectedIndex()) == "Skill") {
            for (Skill skill : character.skills) {
                effects.cbAppliesSub2.addItem(skill.name);
            }
        } else if (effects.cbAppliesTo2.getItemAt(effects.cbAppliesTo2.getSelectedIndex()) == "Resistance") {
            for (String name : character.resistance.keySet()) {
                effects.cbAppliesSub2.addItem(name);
            }
        } else {
            effects.cbAppliesSub2.addItem("---");
        }

    }
    public void refreshAppliesSub3() {
        effects.cbAppliesSub3.removeAllItems();
        //Ability, Save, Skill, Resistance, MaxHP, AC, Init
        if (effects.cbAppliesTo3.getItemAt(effects.cbAppliesTo3.getSelectedIndex()) == "Ability") {
            effects.cbAppliesSub3.addItem("Strength");
            effects.cbAppliesSub3.addItem("Dexterity");
            effects.cbAppliesSub3.addItem("Constitution");
            effects.cbAppliesSub3.addItem("Intelligence");
            effects.cbAppliesSub3.addItem("Wisdom");
            effects.cbAppliesSub3.addItem("Charisma");
        } else if (effects.cbAppliesTo3.getItemAt(effects.cbAppliesTo3.getSelectedIndex()) == "Save") {
            effects.cbAppliesSub3.addItem("Fortitude");
            effects.cbAppliesSub3.addItem("Reflex");
            effects.cbAppliesSub3.addItem("Will");
        } else if (effects.cbAppliesTo3.getItemAt(effects.cbAppliesTo3.getSelectedIndex()) == "Skill") {
            for (Skill skill : character.skills) {
                effects.cbAppliesSub3.addItem(skill.name);
            }
        } else if (effects.cbAppliesTo3.getItemAt(effects.cbAppliesTo3.getSelectedIndex()) == "Resistance") {
            for (String name : character.resistance.keySet()) {
                effects.cbAppliesSub3.addItem(name);
            }
        } else {
            effects.cbAppliesSub3.addItem("---");
        }

    }
    public void refreshAppliesSub4() {
        effects.cbAppliesSub4.removeAllItems();
        //Ability, Save, Skill, Resistance, MaxHP, AC, Init
        if (effects.cbAppliesTo4.getItemAt(effects.cbAppliesTo4.getSelectedIndex()) == "Ability") {
            effects.cbAppliesSub4.addItem("Strength");
            effects.cbAppliesSub4.addItem("Dexterity");
            effects.cbAppliesSub4.addItem("Constitution");
            effects.cbAppliesSub4.addItem("Intelligence");
            effects.cbAppliesSub4.addItem("Wisdom");
            effects.cbAppliesSub4.addItem("Charisma");
        } else if (effects.cbAppliesTo4.getItemAt(effects.cbAppliesTo4.getSelectedIndex()) == "Save") {
            effects.cbAppliesSub4.addItem("Fortitude");
            effects.cbAppliesSub4.addItem("Reflex");
            effects.cbAppliesSub4.addItem("Will");
        } else if (effects.cbAppliesTo4.getItemAt(effects.cbAppliesTo4.getSelectedIndex()) == "Skill") {
            for (Skill skill : character.skills) {
                effects.cbAppliesSub4.addItem(skill.name);
            }
        } else if (effects.cbAppliesTo4.getItemAt(effects.cbAppliesTo4.getSelectedIndex()) == "Resistance") {
            for (String name : character.resistance.keySet()) {
                effects.cbAppliesSub4.addItem(name);
            }
        } else {
            effects.cbAppliesSub4.addItem("---");
        }

    }
    public void refreshAppliesSub5() {
        effects.cbAppliesSub5.removeAllItems();
        //Ability, Save, Skill, Resistance, MaxHP, AC, Init
        if (effects.cbAppliesTo5.getItemAt(effects.cbAppliesTo5.getSelectedIndex()) == "Ability") {
            effects.cbAppliesSub5.addItem("Strength");
            effects.cbAppliesSub5.addItem("Dexterity");
            effects.cbAppliesSub5.addItem("Constitution");
            effects.cbAppliesSub5.addItem("Intelligence");
            effects.cbAppliesSub5.addItem("Wisdom");
            effects.cbAppliesSub5.addItem("Charisma");
        } else if (effects.cbAppliesTo5.getItemAt(effects.cbAppliesTo5.getSelectedIndex()) == "Save") {
            effects.cbAppliesSub5.addItem("Fortitude");
            effects.cbAppliesSub5.addItem("Reflex");
            effects.cbAppliesSub5.addItem("Will");
        } else if (effects.cbAppliesTo5.getItemAt(effects.cbAppliesTo5.getSelectedIndex()) == "Skill") {
            for (Skill skill : character.skills) {
                effects.cbAppliesSub5.addItem(skill.name);
            }
        } else if (effects.cbAppliesTo5.getItemAt(effects.cbAppliesTo5.getSelectedIndex()) == "Resistance") {
            for (String name : character.resistance.keySet()) {
                effects.cbAppliesSub5.addItem(name);
            }
        } else {
            effects.cbAppliesSub5.addItem("---");
        }

    }
    public void refreshAppliesSub6() {
        effects.cbAppliesSub6.removeAllItems();
        //Ability, Save, Skill, Resistance, MaxHP, AC, Init
        if (effects.cbAppliesTo6.getItemAt(effects.cbAppliesTo6.getSelectedIndex()) == "Ability") {
            effects.cbAppliesSub6.addItem("Strength");
            effects.cbAppliesSub6.addItem("Dexterity");
            effects.cbAppliesSub6.addItem("Constitution");
            effects.cbAppliesSub6.addItem("Intelligence");
            effects.cbAppliesSub6.addItem("Wisdom");
            effects.cbAppliesSub6.addItem("Charisma");
        } else if (effects.cbAppliesTo6.getItemAt(effects.cbAppliesTo6.getSelectedIndex()) == "Save") {
            effects.cbAppliesSub6.addItem("Fortitude");
            effects.cbAppliesSub6.addItem("Reflex");
            effects.cbAppliesSub6.addItem("Will");
        } else if (effects.cbAppliesTo6.getItemAt(effects.cbAppliesTo6.getSelectedIndex()) == "Skill") {
            for (Skill skill : character.skills) {
                effects.cbAppliesSub6.addItem(skill.name);
            }
        } else if (effects.cbAppliesTo6.getItemAt(effects.cbAppliesTo6.getSelectedIndex()) == "Resistance") {
            for (String name : character.resistance.keySet()) {
                effects.cbAppliesSub6.addItem(name);
            }
        } else {
            effects.cbAppliesSub6.addItem("---");
        }

    }
    public void saveAndAddAnother() {
        
    }
    
    public void saveAndExit() {
        
        
        effects.dispose();
    }

}
