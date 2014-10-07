/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character.sheet;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author Reid
 */
public class EffectsManager {

    public EffectsManager(Object obj) {
        applyEffectTo = obj;
    }
    public EffectsWindow effectsWin;
    private Character character;
    private Object applyEffectTo;

    List<JComboBox> effTypes = new ArrayList<>();
    List<JComboBox> effApplyTypes = new ArrayList<>();
    List<JComboBox> effApplySubs = new ArrayList<>();
    List<JTextField> effNames = new ArrayList<>();
    List<JTextField> effNotes = new ArrayList<>();
    List<JSpinner> effQtys = new ArrayList<>();

    public void setEffectsWindow(EffectsWindow newwindow) {
        effectsWin = newwindow;
        newwindow.setManager(this);
        refreshAppliesSub1();
        refreshAppliesSub2();
        refreshAppliesSub3();
        refreshAppliesSub4();
        refreshAppliesSub5();
        refreshAppliesSub6();

        effTypes.add(effectsWin.cbType1);
        effTypes.add(effectsWin.cbType2);
        effTypes.add(effectsWin.cbType3);
        effTypes.add(effectsWin.cbType4);
        effTypes.add(effectsWin.cbType5);
        effTypes.add(effectsWin.cbType6);

        effApplyTypes.add(effectsWin.cbAppliesTo1);
        effApplyTypes.add(effectsWin.cbAppliesTo2);
        effApplyTypes.add(effectsWin.cbAppliesTo3);
        effApplyTypes.add(effectsWin.cbAppliesTo4);
        effApplyTypes.add(effectsWin.cbAppliesTo5);
        effApplyTypes.add(effectsWin.cbAppliesTo6);

        effApplySubs.add(effectsWin.cbAppliesSub1);
        effApplySubs.add(effectsWin.cbAppliesSub2);
        effApplySubs.add(effectsWin.cbAppliesSub3);
        effApplySubs.add(effectsWin.cbAppliesSub4);
        effApplySubs.add(effectsWin.cbAppliesSub5);
        effApplySubs.add(effectsWin.cbAppliesSub6);

        effNames.add(effectsWin.txtName1);
        effNames.add(effectsWin.txtName2);
        effNames.add(effectsWin.txtName3);
        effNames.add(effectsWin.txtName4);
        effNames.add(effectsWin.txtName5);
        effNames.add(effectsWin.txtName6);

        effNotes.add(effectsWin.txtNotes1);
        effNotes.add(effectsWin.txtNotes2);
        effNotes.add(effectsWin.txtNotes3);
        effNotes.add(effectsWin.txtNotes4);
        effNotes.add(effectsWin.txtNotes5);
        effNotes.add(effectsWin.txtNotes6);

        effQtys.add(effectsWin.spinQty1);
        effQtys.add(effectsWin.spinQty2);
        effQtys.add(effectsWin.spinQty3);
        effQtys.add(effectsWin.spinQty4);
        effQtys.add(effectsWin.spinQty5);
        effQtys.add(effectsWin.spinQty6);

    }

    public void setCharacter(Character newchar) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        if (effectsWin != null) {
            character = newchar;
        } else {
            throw new EmptyStackException();
        }
    }

//    String[] names = character.resistance.values().toArray(new String[character.resistance.values().size()]);
    public void refreshAppliesSub1() {
        effectsWin.cbAppliesSub1.removeAllItems();
        //Ability, Save, Skill, Resistance, MaxHP, AC, Init
        if (effectsWin.cbAppliesTo1.getItemAt(effectsWin.cbAppliesTo1.getSelectedIndex()) == "Ability") {
            effectsWin.cbAppliesSub1.addItem("Strength");
            effectsWin.cbAppliesSub1.addItem("Dexterity");
            effectsWin.cbAppliesSub1.addItem("Constitution");
            effectsWin.cbAppliesSub1.addItem("Intelligence");
            effectsWin.cbAppliesSub1.addItem("Wisdom");
            effectsWin.cbAppliesSub1.addItem("Charisma");
            effectsWin.cbAppliesSub1.addItem("All");
        } else if (effectsWin.cbAppliesTo1.getItemAt(effectsWin.cbAppliesTo1.getSelectedIndex()) == "Save") {
            effectsWin.cbAppliesSub1.addItem("Fortitude");
            effectsWin.cbAppliesSub1.addItem("Reflex");
            effectsWin.cbAppliesSub1.addItem("Will");
            effectsWin.cbAppliesSub1.addItem("All");
        } else if (effectsWin.cbAppliesTo1.getItemAt(effectsWin.cbAppliesTo1.getSelectedIndex()) == "Skill") {
            for (Skill skill : character.skills) {
                effectsWin.cbAppliesSub1.addItem(skill.name);

            }
            effectsWin.cbAppliesSub1.addItem("All");
        } else if (effectsWin.cbAppliesTo1.getItemAt(effectsWin.cbAppliesTo1.getSelectedIndex()) == "Resistance") {
            for (String name : character.resistance.keySet()) {
                effectsWin.cbAppliesSub1.addItem(name);
            }
        } else if (effectsWin.cbAppliesTo1.getItemAt(effectsWin.cbAppliesTo1.getSelectedIndex()) == "Attack") {
            effectsWin.cbAppliesSub1.addItem("Attack");
        } else if (effectsWin.cbAppliesTo1.getItemAt(effectsWin.cbAppliesTo1.getSelectedIndex()) == "Damage") {
            effectsWin.cbAppliesSub1.addItem("Damage");
        } else if (effectsWin.cbAppliesTo1.getItemAt(effectsWin.cbAppliesTo1.getSelectedIndex()) == "Max HP") {
            effectsWin.cbAppliesSub1.addItem("Max HP");
        } else if (effectsWin.cbAppliesTo1.getItemAt(effectsWin.cbAppliesTo1.getSelectedIndex()) == "AC") {
            effectsWin.cbAppliesSub1.addItem("AC");
        } else if (effectsWin.cbAppliesTo1.getItemAt(effectsWin.cbAppliesTo1.getSelectedIndex()) == "Initiative") {
            effectsWin.cbAppliesSub1.addItem("Initiative");
        } else if (effectsWin.cbAppliesTo1.getItemAt(effectsWin.cbAppliesTo1.getSelectedIndex()) == "Speed") {
            effectsWin.cbAppliesSub1.addItem("Speed");
        } //Max HP AC Initiative Speed
        else {
            effectsWin.cbAppliesSub1.addItem("---");
        }

    }

    public void refreshAppliesSub2() {
        effectsWin.cbAppliesSub2.removeAllItems();
        //Ability, Save, Skill, Resistance, MaxHP, AC, Init
        if (effectsWin.cbAppliesTo2.getItemAt(effectsWin.cbAppliesTo2.getSelectedIndex()) == "Ability") {
            effectsWin.cbAppliesSub2.addItem("Strength");
            effectsWin.cbAppliesSub2.addItem("Dexterity");
            effectsWin.cbAppliesSub2.addItem("Constitution");
            effectsWin.cbAppliesSub2.addItem("Intelligence");
            effectsWin.cbAppliesSub2.addItem("Wisdom");
            effectsWin.cbAppliesSub2.addItem("Charisma");
            effectsWin.cbAppliesSub6.addItem("All");
        } else if (effectsWin.cbAppliesTo2.getItemAt(effectsWin.cbAppliesTo2.getSelectedIndex()) == "Save") {
            effectsWin.cbAppliesSub2.addItem("Fortitude");
            effectsWin.cbAppliesSub2.addItem("Reflex");
            effectsWin.cbAppliesSub2.addItem("Will");
            effectsWin.cbAppliesSub2.addItem("All");
        } else if (effectsWin.cbAppliesTo2.getItemAt(effectsWin.cbAppliesTo2.getSelectedIndex()) == "Skill") {
            for (Skill skill : character.skills) {
                effectsWin.cbAppliesSub2.addItem(skill.name);

            }
            effectsWin.cbAppliesSub2.addItem("All");
        } else if (effectsWin.cbAppliesTo2.getItemAt(effectsWin.cbAppliesTo2.getSelectedIndex()) == "Resistance") {
            for (String name : character.resistance.keySet()) {
                effectsWin.cbAppliesSub2.addItem(name);
            }
        } else if (effectsWin.cbAppliesTo2.getItemAt(effectsWin.cbAppliesTo2.getSelectedIndex()) == "Attack") {
            effectsWin.cbAppliesSub2.addItem("Attack");
        } else if (effectsWin.cbAppliesTo2.getItemAt(effectsWin.cbAppliesTo2.getSelectedIndex()) == "Damage") {
            effectsWin.cbAppliesSub2.addItem("Damage");
        } else if (effectsWin.cbAppliesTo2.getItemAt(effectsWin.cbAppliesTo2.getSelectedIndex()) == "Max HP") {
            effectsWin.cbAppliesSub2.addItem("Max HP");
        } else if (effectsWin.cbAppliesTo2.getItemAt(effectsWin.cbAppliesTo2.getSelectedIndex()) == "AC") {
            effectsWin.cbAppliesSub2.addItem("AC");
        } else if (effectsWin.cbAppliesTo2.getItemAt(effectsWin.cbAppliesTo2.getSelectedIndex()) == "Initiative") {
            effectsWin.cbAppliesSub2.addItem("Initiative");
        } else if (effectsWin.cbAppliesTo2.getItemAt(effectsWin.cbAppliesTo2.getSelectedIndex()) == "Speed") {
            effectsWin.cbAppliesSub2.addItem("Speed");
        } else {
            effectsWin.cbAppliesSub2.addItem("---");
        }

    }

    public void refreshAppliesSub3() {
        effectsWin.cbAppliesSub3.removeAllItems();
        //Ability, Save, Skill, Resistance, MaxHP, AC, Init
        if (effectsWin.cbAppliesTo3.getItemAt(effectsWin.cbAppliesTo3.getSelectedIndex()) == "Ability") {
            effectsWin.cbAppliesSub3.addItem("Strength");
            effectsWin.cbAppliesSub3.addItem("Dexterity");
            effectsWin.cbAppliesSub3.addItem("Constitution");
            effectsWin.cbAppliesSub3.addItem("Intelligence");
            effectsWin.cbAppliesSub3.addItem("Wisdom");
            effectsWin.cbAppliesSub3.addItem("Charisma");
            effectsWin.cbAppliesSub3.addItem("All");
        } else if (effectsWin.cbAppliesTo3.getItemAt(effectsWin.cbAppliesTo3.getSelectedIndex()) == "Save") {
            effectsWin.cbAppliesSub3.addItem("Fortitude");
            effectsWin.cbAppliesSub3.addItem("Reflex");
            effectsWin.cbAppliesSub3.addItem("Will");
            effectsWin.cbAppliesSub3.addItem("All");
        } else if (effectsWin.cbAppliesTo3.getItemAt(effectsWin.cbAppliesTo3.getSelectedIndex()) == "Skill") {
            for (Skill skill : character.skills) {
                effectsWin.cbAppliesSub3.addItem(skill.name);
            }
            effectsWin.cbAppliesSub3.addItem("All");
        } else if (effectsWin.cbAppliesTo3.getItemAt(effectsWin.cbAppliesTo3.getSelectedIndex()) == "Resistance") {
            for (String name : character.resistance.keySet()) {
                effectsWin.cbAppliesSub3.addItem(name);
            }
        } else if (effectsWin.cbAppliesTo3.getItemAt(effectsWin.cbAppliesTo3.getSelectedIndex()) == "Attack") {
            effectsWin.cbAppliesSub3.addItem("Attack");
        } else if (effectsWin.cbAppliesTo3.getItemAt(effectsWin.cbAppliesTo3.getSelectedIndex()) == "Damage") {
            effectsWin.cbAppliesSub3.addItem("Damage");
        } else if (effectsWin.cbAppliesTo3.getItemAt(effectsWin.cbAppliesTo3.getSelectedIndex()) == "Max HP") {
            effectsWin.cbAppliesSub3.addItem("Max HP");
        } else if (effectsWin.cbAppliesTo3.getItemAt(effectsWin.cbAppliesTo3.getSelectedIndex()) == "AC") {
            effectsWin.cbAppliesSub3.addItem("AC");
        } else if (effectsWin.cbAppliesTo3.getItemAt(effectsWin.cbAppliesTo3.getSelectedIndex()) == "Initiative") {
            effectsWin.cbAppliesSub3.addItem("Initiative");
        } else if (effectsWin.cbAppliesTo3.getItemAt(effectsWin.cbAppliesTo3.getSelectedIndex()) == "Speed") {
            effectsWin.cbAppliesSub3.addItem("Speed");
        } else {
            effectsWin.cbAppliesSub3.addItem("---");
        }

    }

    public void refreshAppliesSub4() {
        effectsWin.cbAppliesSub4.removeAllItems();
        //Ability, Save, Skill, Resistance, MaxHP, AC, Init
        if (effectsWin.cbAppliesTo4.getItemAt(effectsWin.cbAppliesTo4.getSelectedIndex()) == "Ability") {
            effectsWin.cbAppliesSub4.addItem("Strength");
            effectsWin.cbAppliesSub4.addItem("Dexterity");
            effectsWin.cbAppliesSub4.addItem("Constitution");
            effectsWin.cbAppliesSub4.addItem("Intelligence");
            effectsWin.cbAppliesSub4.addItem("Wisdom");
            effectsWin.cbAppliesSub4.addItem("Charisma");
            effectsWin.cbAppliesSub4.addItem("All");
        } else if (effectsWin.cbAppliesTo4.getItemAt(effectsWin.cbAppliesTo4.getSelectedIndex()) == "Save") {
            effectsWin.cbAppliesSub4.addItem("Fortitude");
            effectsWin.cbAppliesSub4.addItem("Reflex");
            effectsWin.cbAppliesSub4.addItem("Will");
            effectsWin.cbAppliesSub4.addItem("All");
        } else if (effectsWin.cbAppliesTo4.getItemAt(effectsWin.cbAppliesTo4.getSelectedIndex()) == "Skill") {
            for (Skill skill : character.skills) {
                effectsWin.cbAppliesSub4.addItem(skill.name);
            }
            effectsWin.cbAppliesSub4.addItem("All");
        } else if (effectsWin.cbAppliesTo4.getItemAt(effectsWin.cbAppliesTo4.getSelectedIndex()) == "Resistance") {
            for (String name : character.resistance.keySet()) {
                effectsWin.cbAppliesSub4.addItem(name);
            }
        } else if (effectsWin.cbAppliesTo4.getItemAt(effectsWin.cbAppliesTo4.getSelectedIndex()) == "Attack") {
            effectsWin.cbAppliesSub4.addItem("Attack");
        } else if (effectsWin.cbAppliesTo4.getItemAt(effectsWin.cbAppliesTo4.getSelectedIndex()) == "Damage") {
            effectsWin.cbAppliesSub4.addItem("Damage");
        } else if (effectsWin.cbAppliesTo4.getItemAt(effectsWin.cbAppliesTo4.getSelectedIndex()) == "Max HP") {
            effectsWin.cbAppliesSub4.addItem("Max HP");
        } else if (effectsWin.cbAppliesTo4.getItemAt(effectsWin.cbAppliesTo4.getSelectedIndex()) == "AC") {
            effectsWin.cbAppliesSub4.addItem("AC");
        } else if (effectsWin.cbAppliesTo4.getItemAt(effectsWin.cbAppliesTo4.getSelectedIndex()) == "Initiative") {
            effectsWin.cbAppliesSub4.addItem("Initiative");
        } else if (effectsWin.cbAppliesTo4.getItemAt(effectsWin.cbAppliesTo4.getSelectedIndex()) == "Speed") {
            effectsWin.cbAppliesSub4.addItem("Speed");
        } else {
            effectsWin.cbAppliesSub4.addItem("---");
        }

    }

    public void refreshAppliesSub5() {
        effectsWin.cbAppliesSub5.removeAllItems();
        //Ability, Save, Skill, Resistance, MaxHP, AC, Init
        if (effectsWin.cbAppliesTo5.getItemAt(effectsWin.cbAppliesTo5.getSelectedIndex()) == "Ability") {
            effectsWin.cbAppliesSub5.addItem("Strength");
            effectsWin.cbAppliesSub5.addItem("Dexterity");
            effectsWin.cbAppliesSub5.addItem("Constitution");
            effectsWin.cbAppliesSub5.addItem("Intelligence");
            effectsWin.cbAppliesSub5.addItem("Wisdom");
            effectsWin.cbAppliesSub5.addItem("Charisma");
            effectsWin.cbAppliesSub5.addItem("All");
        } else if (effectsWin.cbAppliesTo5.getItemAt(effectsWin.cbAppliesTo5.getSelectedIndex()) == "Save") {
            effectsWin.cbAppliesSub5.addItem("Fortitude");
            effectsWin.cbAppliesSub5.addItem("Reflex");
            effectsWin.cbAppliesSub5.addItem("Will");
            effectsWin.cbAppliesSub5.addItem("All");
        } else if (effectsWin.cbAppliesTo5.getItemAt(effectsWin.cbAppliesTo5.getSelectedIndex()) == "Skill") {
            for (Skill skill : character.skills) {
                effectsWin.cbAppliesSub5.addItem(skill.name);
            }
            effectsWin.cbAppliesSub5.addItem("All");
        } else if (effectsWin.cbAppliesTo5.getItemAt(effectsWin.cbAppliesTo5.getSelectedIndex()) == "Resistance") {
            for (String name : character.resistance.keySet()) {
                effectsWin.cbAppliesSub5.addItem(name);
            }
        } else if (effectsWin.cbAppliesTo5.getItemAt(effectsWin.cbAppliesTo5.getSelectedIndex()) == "Attack") {
            effectsWin.cbAppliesSub5.addItem("Attack");
        } else if (effectsWin.cbAppliesTo5.getItemAt(effectsWin.cbAppliesTo5.getSelectedIndex()) == "Damage") {
            effectsWin.cbAppliesSub5.addItem("Damage");
        } else if (effectsWin.cbAppliesTo5.getItemAt(effectsWin.cbAppliesTo5.getSelectedIndex()) == "Max HP") {
            effectsWin.cbAppliesSub5.addItem("Max HP");
        } else if (effectsWin.cbAppliesTo5.getItemAt(effectsWin.cbAppliesTo5.getSelectedIndex()) == "AC") {
            effectsWin.cbAppliesSub5.addItem("AC");
        } else if (effectsWin.cbAppliesTo5.getItemAt(effectsWin.cbAppliesTo5.getSelectedIndex()) == "Initiative") {
            effectsWin.cbAppliesSub5.addItem("Initiative");
        } else if (effectsWin.cbAppliesTo5.getItemAt(effectsWin.cbAppliesTo5.getSelectedIndex()) == "Speed") {
            effectsWin.cbAppliesSub5.addItem("Speed");
        } else {
            effectsWin.cbAppliesSub5.addItem("---");
        }

    }

    public void refreshAppliesSub6() {
        effectsWin.cbAppliesSub6.removeAllItems();
        //Ability, Save, Skill, Resistance, MaxHP, AC, Init
        if (effectsWin.cbAppliesTo6.getItemAt(effectsWin.cbAppliesTo6.getSelectedIndex()) == "Ability") {
            effectsWin.cbAppliesSub6.addItem("Strength");
            effectsWin.cbAppliesSub6.addItem("Dexterity");
            effectsWin.cbAppliesSub6.addItem("Constitution");
            effectsWin.cbAppliesSub6.addItem("Intelligence");
            effectsWin.cbAppliesSub6.addItem("Wisdom");
            effectsWin.cbAppliesSub6.addItem("Charisma");
            effectsWin.cbAppliesSub6.addItem("All");

        } else if (effectsWin.cbAppliesTo6.getItemAt(effectsWin.cbAppliesTo6.getSelectedIndex()) == "Save") {
            effectsWin.cbAppliesSub6.addItem("Fortitude");
            effectsWin.cbAppliesSub6.addItem("Reflex");
            effectsWin.cbAppliesSub6.addItem("Will");
            effectsWin.cbAppliesSub6.addItem("All");
        } else if (effectsWin.cbAppliesTo6.getItemAt(effectsWin.cbAppliesTo6.getSelectedIndex()) == "Skill") {
            for (Skill skill : character.skills) {
                effectsWin.cbAppliesSub6.addItem(skill.name);
            }
            effectsWin.cbAppliesSub6.addItem("All");
        } else if (effectsWin.cbAppliesTo6.getItemAt(effectsWin.cbAppliesTo6.getSelectedIndex()) == "Resistance") {
            for (String name : character.resistance.keySet()) {
                effectsWin.cbAppliesSub6.addItem(name);
            }
        } else if (effectsWin.cbAppliesTo6.getItemAt(effectsWin.cbAppliesTo6.getSelectedIndex()) == "Attack") {
            effectsWin.cbAppliesSub6.addItem("Attack");
        } else if (effectsWin.cbAppliesTo6.getItemAt(effectsWin.cbAppliesTo6.getSelectedIndex()) == "Damage") {
            effectsWin.cbAppliesSub6.addItem("Damage");
        } else if (effectsWin.cbAppliesTo6.getItemAt(effectsWin.cbAppliesTo6.getSelectedIndex()) == "Max HP") {
            effectsWin.cbAppliesSub6.addItem("Max HP");
        } else if (effectsWin.cbAppliesTo6.getItemAt(effectsWin.cbAppliesTo6.getSelectedIndex()) == "AC") {
            effectsWin.cbAppliesSub6.addItem("AC");
        } else if (effectsWin.cbAppliesTo6.getItemAt(effectsWin.cbAppliesTo6.getSelectedIndex()) == "Initiative") {
            effectsWin.cbAppliesSub6.addItem("Initiative");
        } else if (effectsWin.cbAppliesTo6.getItemAt(effectsWin.cbAppliesTo6.getSelectedIndex()) == "Speed") {
            effectsWin.cbAppliesSub6.addItem("Speed");
        } else {
            effectsWin.cbAppliesSub6.addItem("---");
        }

    }

    public void saveAndAddAnother() {

    }

    public void saveEffect() {
        //UpperLimitAttribute, ModAttribute, LowerLimitAttribute, GenericAttribute
        //Type, Name, ApplyType, AppluSub, qty, notes
        //Attribute = new attribute (look up syntax for attribute)
        //Effect eff = new Effect(list of attributes)
        //object.ApplyTo.add Effect eff
        //Max HP AC Initiative Speed
        /*
         damage
         initiative
         movement speed
         attack
         DTH
         resistance
         saves (all 6)
         skill checks (any skill)
         misc
         */

        List<String> effTypeVals = new ArrayList<>();
        List<String> effNameVals = new ArrayList<>();
        List<String> effApplyTypeVals = new ArrayList<>();
        List<String> effApplySubVals = new ArrayList<>();
        List<Integer> effQtyVals = new ArrayList<>();
        List<String> effNoteVals = new ArrayList<>();

        for (int i = 0; i < effTypes.size(); i++) {
            if (effApplyTypes.get(i).getSelectedIndex() != 0) {
                effTypeVals.add(String.valueOf(effTypes.get(i).getSelectedItem()));
                effNameVals.add(effNames.get(i).getText());
                effApplyTypeVals.add(String.valueOf(effApplyTypes.get(i).getSelectedItem()));
                effApplySubVals.add(String.valueOf(effApplySubs.get(i).getSelectedItem()));
                effQtyVals.add((Integer) effQtys.get(i).getValue());
                effNoteVals.add(effNotes.get(i).getText());
            }
        }
        System.out.println("Previous Success");
        List<Attribute> attribs = new ArrayList<>();
        System.out.println("Case Loop " + String.valueOf(effTypeVals.size()));
        for (int j = 0; j < effTypeVals.size(); j++) {
            Attribute attrib;
            switch (effTypeVals.get(j)) {
                case "Add":
                    attrib = new ModAttribute();
                    break;
                case "Limit - Upper":
                    attrib = new UpperLimitAttribute();
                    break;
                case "Limit - Lower":
                    attrib = new LowerLimitAttribute();
                    break;
                default:
                    attrib = new GenericAttribute();
                    break;
            }
            attrib.setModifier(effQtyVals.get(j));
            attrib.details = effNoteVals.get(j);
            attrib.name = effNameVals.get(j);
            attrib.active = true;
            attrib.source = effectsWin.txtEffectParentName.getText();
            attribs.add(attrib);
            //attribs.add(new Attribute(attrib));
        }
        System.out.println("Print Loop" + String.valueOf(attribs.size()));
        for (int k = 0; k < attribs.size(); k++) {
            System.out.println(attribs.get(k).name);
        }

        Effect eff = new Effect(attribs);

        

    }

    public void closeEffects() {
        effectsWin.dispose();
    }

}
