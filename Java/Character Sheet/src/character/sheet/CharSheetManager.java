/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character.sheet;

import java.awt.*;
import java.util.EmptyStackException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Reid
 */
public class CharSheetManager {

    private Charsheet charsheet;
    private Character character;

    public void setCharSheet(Charsheet newsheet) {
        charsheet = newsheet;
        
    }

    public void setCharacter(Character newchar) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        if (charsheet != null) {
            character = newchar;
            charsheet.setCharacter(newchar);
            refreshCharacter();
        } else {
            throw new EmptyStackException();
        }
    }

    public void refreshCharacter() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        refreshCharacterName();
        refreshCharacteristics();
        refreshAbilityScores();
        refreshExperience();
        refreshMoney();
        refreshHP();
        refreshCarriedWeight();
        refreshSpeed();
        refreshDTH();
        refreshSpecialVision();
        refreshLanguages();

        refreshEquipment();
        refreshAttributesTab();
        refreshSkillsTab();
        refreshAboutTab();
    }

    public void refreshCharacterName() {
        charsheet.lblCharName.setText(character.name);
    }

    //----------Character Tab-----------
    public void refreshCharacteristics() {
        charsheet.lblRace.setText(character.race.name);
        charsheet.lblClass.setText(character.cclass.name);
        charsheet.lbl2ndClass.setText("Punkster"/*character.cclass.subclass.name*/);
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

    public void refreshAbilityScores() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        //Abilities
        charsheet.lblSTRVal.setText(String.valueOf(character.STR));
        charsheet.lblDEXVal.setText(String.valueOf(character.DEX));
        charsheet.lblCONVal.setText(String.valueOf(character.CON));
        charsheet.lblINTVal.setText(String.valueOf(character.INT));
        charsheet.lblWISVal.setText(String.valueOf(character.WIS));
        charsheet.lblCHAVal.setText(String.valueOf(character.CHA));
        //Ability Mods

        charsheet.lblSTRModVal.setText(String.valueOf(character.getAbilityMod("STR")));
        charsheet.lblDEXModVal.setText(String.valueOf(character.getAbilityMod("DEX")));
        charsheet.lblCONModVal.setText(String.valueOf(character.getAbilityMod("CON")));
        charsheet.lblINTModVal.setText(String.valueOf(character.getAbilityMod("INT")));
        charsheet.lblWISModVal.setText(String.valueOf(character.getAbilityMod("WIS")));
        charsheet.lblCHAModVal.setText(String.valueOf(character.getAbilityMod("CHA")));
    }

    public void refreshExperience() {
        charsheet.lblExpLevel.setText(String.valueOf(character.getLevel()));
        charsheet.lblCurrentXP.setText(String.valueOf(character.curxp));
        charsheet.lblNextLvlXP.setText(String.valueOf(character.getNextLevelXP()));
    }

    public void refreshMoney() {
        charsheet.lblGoldVal.setText(String.valueOf(character.pp));
        charsheet.lblSilverVal.setText(String.valueOf(character.gp));
        charsheet.lblCopperVal.setText(String.valueOf(character.sp));
        charsheet.lblTotalMoneyVal.setText(String.valueOf(character.totalCurrency()));
        //Total money calc? or in a var?
    }

    public void refreshHP() {
        charsheet.lblCurrHPVal.setText(String.valueOf(character.curhp));
        charsheet.lblMaxHPVal.setText(String.valueOf(character.maxhp));
    }

    public void refreshCarriedWeight() {
        charsheet.lblCarriedWeightVal.setText(String.valueOf(character.getCarrying()));
        charsheet.lblWeightCapacityVal.setText(String.valueOf(character.getCarryCapacity()));
    }

    public void refreshSpeed() {
        charsheet.lblSpeedVal.setText(String.valueOf(character.getSpeed()));
    }

    public void refreshDTH() {
        charsheet.lblDifficultyToHitVal.setText(String.valueOf(character.getAC()));
    }

    public void refreshSpecialVision() {
        charsheet.lblSpecialVisionVal.setText(String.valueOf(character.vision)
                .substring(1, String.valueOf(character.vision).length() - 1));
    }

    public void refreshLanguages() {
        charsheet.lblLanguagesVal.setText(String.valueOf(character.languages)
                .substring(1, String.valueOf(character.languages).length() - 1));
    }

    //------------Equipment Tab-------
    public javax.swing.JScrollPane jScrollPane7;
    public javax.swing.JTable tblEquip;

    public void refreshEquipment() {

        tblEquip = new javax.swing.JTable();

        tblEquip.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{ /*{"Axe of Awesomeness", "Does cool stuff", 1, 4, "Yes"},
                 {null, null, null, null, null}*/},
                new String[]{
                    "Item", "Item Description", "Qty", "Weight", "Worn"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        tblEquip.getTableHeader().setReorderingAllowed(false);

        jScrollPane7 = new javax.swing.JScrollPane();
        jScrollPane7.setViewportView(tblEquip);

        if (tblEquip.getColumnModel().getColumnCount() > 0) {
            tblEquip.getColumnModel().getColumn(1).setPreferredWidth(300);
            tblEquip.getColumnModel().getColumn(2).setPreferredWidth(5);
            tblEquip.getColumnModel().getColumn(3).setPreferredWidth(5);
            tblEquip.getColumnModel().getColumn(4).setPreferredWidth(5);
        }
        javax.swing.GroupLayout pnlEquipmentLayout = new javax.swing.GroupLayout(charsheet.pnlEquipment);
        charsheet.pnlEquipment.setLayout(pnlEquipmentLayout);
        Component jButton1 = new JButton();
        pnlEquipmentLayout.setHorizontalGroup(
                pnlEquipmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
                .addGroup(pnlEquipmentLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlEquipmentLayout.setVerticalGroup(
                pnlEquipmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlEquipmentLayout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
        );
        for (Item item : character.equipment) {
            addItemsToEquipment(item);
        }
        DefaultTableModel model = (DefaultTableModel) tblEquip.getModel();
        model.addRow(new Object[]{"", "------------------------Weapons------------------------", "", "", ""});

        for (Weapon item : character.weapons) {
            addItemsToEquipment(item);
        }
        model = (DefaultTableModel) tblEquip.getModel();
        model.addRow(new Object[]{"", "--------------------------Armor--------------------------", "", "", ""});

        for (Armor item : character.armor) {
            addItemsToEquipment(item);
        }
    }

    public void addItemsToEquipment(Item newItem) {
        DefaultTableModel model = (DefaultTableModel) tblEquip.getModel();
        model.addRow(new Object[]{newItem.name, newItem.desc, newItem.quantity, newItem.weight, "test"});
    }

    public void addItemsToEquipment(Weapon newItem) {
        DefaultTableModel model = (DefaultTableModel) tblEquip.getModel();
        model.addRow(new Object[]{newItem.name, newItem.desc, newItem.quantity, newItem.weight, newItem.equipped});
    }

    public void addItemsToEquipment(Armor newItem) {
        DefaultTableModel model = (DefaultTableModel) tblEquip.getModel();
        model.addRow(new Object[]{newItem.name, newItem.desc, newItem.quantity, newItem.weight, newItem.equipped});
    }

    //Create Cell renderer for word wrapping
    Font f = new Font("SansSerif", Font.PLAIN, 11);

    public class MyCellRenderer extends JTextArea implements TableCellRenderer {

        public MyCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(String.valueOf(value));
            setSize(table.getColumnModel().getColumn(column).getWidth(),
                    getPreferredSize().height);
            if (table.getRowHeight(row) != getPreferredSize().height) {
                table.setRowHeight(row, getPreferredSize().height);
            }
            this.setFont(f);
            return this;
        }
    }

    //------------Attributes Tab-----------
    public javax.swing.JTable tblFeats;
    public javax.swing.JTable tblRaceFeatures;

    //public javax.swing.JScrollPane sbFeats;
    public void refreshAttributesTab() {
        refreshFeatsTab();
        refreshRaceFeaturesTab();
        refreshClassAttributesTab();
        refreshEffectsTab();
    }

    public void refreshFeatsTab() {
        //Feats list
        Integer featSize = character.cclass.effects.size() /*+ character.cclass.subclass.attributes.size()*/;

        charsheet.sbFeats.setViewportView(charsheet.pnlFeats);

        javax.swing.JScrollPane saFeats;

        tblFeats = new javax.swing.JTable();

        tblFeats.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{//{"Extra Hit", 1, "Does cool Stuff that goes on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and evntually stops"},
                /*{"Awesomely Fast", 3, "This feat makes it so that you are considered Awesomely Fast and can move up to 10 extra feet each round"}*/},
                new String[]{
                    "Feat Name", "Lvl", "Description"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        tblFeats.getTableHeader().setReorderingAllowed(false);

        saFeats = new javax.swing.JScrollPane();
        saFeats.setViewportView(tblFeats);

        if (tblFeats.getColumnModel().getColumnCount() > 0) {
            tblFeats.getColumnModel().getColumn(0).setPreferredWidth(75);
            tblFeats.getColumnModel().getColumn(1).setPreferredWidth(10);
            tblFeats.getColumnModel().getColumn(2).setPreferredWidth(375);
            tblFeats.getColumnModel().getColumn(0).setCellRenderer(new MyCellRenderer());
            tblFeats.getColumnModel().getColumn(1).setCellRenderer(new MyCellRenderer());
            tblFeats.getColumnModel().getColumn(2).setCellRenderer(new MyCellRenderer());
        }
        //Make not selectable
        tblFeats.setFocusable(false);
        tblFeats.setRowSelectionAllowed(false);

        javax.swing.GroupLayout pnlFeatsLayout = new javax.swing.GroupLayout(charsheet.pnlFeats);
        charsheet.pnlFeats.setLayout(pnlFeatsLayout);
        pnlFeatsLayout.setHorizontalGroup(
                pnlFeatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(saFeats, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addGroup(pnlFeatsLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFeatsLayout.setVerticalGroup(
                pnlFeatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlFeatsLayout.createSequentialGroup()
                        .addComponent(saFeats, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
        );

        //Insert data into Feats table
        for (int i = 0; i < featSize; i++) {
            for (Effect newFeat : character.feats) {
                addFeatToFeats(newFeat);
            }
        }
    }

    //Populate Race Features Table
    public void refreshRaceFeaturesTab() {
        //Create Race Features table
        charsheet.sbRaceFeatures.setViewportView(charsheet.pnlRaceFeatures);

        tblRaceFeatures = new javax.swing.JTable();
        tblRaceFeatures.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{//{"Extra Hit", 1, "Does cool Stuff that goes on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and evntually stops"},
                /*{"Awesomely Fast", 3, "This feat makes it so that you are considered Awesomely Fast and can move up to 10 extra feet each round"}*/},
                new String[]{
                    "Race Feature Name", "Description"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        tblRaceFeatures.getTableHeader().setReorderingAllowed(false);

        JScrollPane saRaceFeatures = new javax.swing.JScrollPane();
        saRaceFeatures.setViewportView(tblRaceFeatures);

        if (tblRaceFeatures.getColumnModel().getColumnCount() > 0) {
            tblRaceFeatures.getColumnModel().getColumn(0).setPreferredWidth(75);
            tblRaceFeatures.getColumnModel().getColumn(1).setPreferredWidth(350);
            tblRaceFeatures.getColumnModel().getColumn(0).setCellRenderer(new MyCellRenderer());
            tblRaceFeatures.getColumnModel().getColumn(1).setCellRenderer(new MyCellRenderer());
        }
        //Make not selectable
        tblRaceFeatures.setFocusable(false);
        tblRaceFeatures.setRowSelectionAllowed(false);

        javax.swing.GroupLayout pnlRaceFeaturesLayout = new javax.swing.GroupLayout(charsheet.pnlRaceFeatures);
        charsheet.pnlRaceFeatures.setLayout(pnlRaceFeaturesLayout);
        pnlRaceFeaturesLayout.setHorizontalGroup(
                pnlRaceFeaturesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(saRaceFeatures, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addGroup(pnlRaceFeaturesLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlRaceFeaturesLayout.setVerticalGroup(
                pnlRaceFeaturesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlRaceFeaturesLayout.createSequentialGroup()
                        .addComponent(saRaceFeatures, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
        );

        //Insert Data into Race Features table
        Integer raceFeatureSize = character.race.effects.size();
        Integer subraceFeatureSize = character.race.subrace.effects.size();

        for (int i = 0; i < raceFeatureSize; i++) {
            for (Effect newFeature : character.race.effects) {
                addRaceFeatureToFeatures(newFeature);
            }
        }
        for (int i = 0; i < subraceFeatureSize; i++) {
            for (Effect newFeature : character.race.subrace.effects) {
                addRaceFeatureToFeatures(newFeature);
            }
        }
    }

    public void refreshClassAttributesTab() {

    }

    public void refreshEffectsTab() {

    }

    public void addFeatToFeats(Effect newFeat) {
        tblFeats.getModel();
        DefaultTableModel model = (DefaultTableModel) tblFeats.getModel();
        for (Attribute attrib : newFeat.attributes) {
        model.addRow(new Object[]{attrib.name, attrib.source, attrib.details});
        }
    }

    public void addRaceFeatureToFeatures(Effect newFeature) {
        tblRaceFeatures.getModel();
        DefaultTableModel model = (DefaultTableModel) tblRaceFeatures.getModel();
        for (Attribute attrib : newFeature.attributes) {
        model.addRow(new Object[]{attrib.name, attrib.details});
        }
    }

    //------------Skills Tab----------
    public void refreshSkillsTab() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        //System.out.println("showing skills: " + String.valueOf(character.skills.size()));
        String str = "STR";
        String dex = "DEX";
        String con = "CON";
        String inte = "INT";
        String wis = "WIS";
        //String cha = "CHA";

        charsheet.tblSkillSTR.setFocusable(false);
        charsheet.tblSkillSTR.setRowSelectionAllowed(false);

        charsheet.tblSkillDEX.setFocusable(false);
        charsheet.tblSkillDEX.setRowSelectionAllowed(false);

        charsheet.tblSkillCON.setFocusable(false);
        charsheet.tblSkillCON.setRowSelectionAllowed(false);

        charsheet.tblSkillINT.setFocusable(false);
        charsheet.tblSkillINT.setRowSelectionAllowed(false);

        charsheet.tblSkillWIS.setFocusable(false);
        charsheet.tblSkillWIS.setRowSelectionAllowed(false);

        charsheet.tblSkillCHA.setFocusable(false);
        charsheet.tblSkillCHA.setRowSelectionAllowed(false);

        for (Skill skill : character.skills) {

            String s = String.valueOf(skill.ability);
            if (s.equals(str)) {
                charsheet.tblSkillSTR.getModel();
                DefaultTableModel model = (DefaultTableModel) charsheet.tblSkillSTR.getModel();
                model.addRow(new Object[]{skill.name, character.getSkillMod(skill)});
            } else if (s.equals(dex)) {
                charsheet.tblSkillDEX.getModel();
                DefaultTableModel model2 = (DefaultTableModel) charsheet.tblSkillDEX.getModel();
                model2.addRow(new Object[]{skill.name, character.getSkillMod(skill)});
            } else if (s.equals(con)) {
                charsheet.tblSkillCON.getModel();
                DefaultTableModel model3 = (DefaultTableModel) charsheet.tblSkillCON.getModel();
                model3.addRow(new Object[]{skill.name, character.getSkillMod(skill)});
            } else if (s.equals(inte)) {
                charsheet.tblSkillINT.getModel();
                DefaultTableModel model4 = (DefaultTableModel) charsheet.tblSkillINT.getModel();
                model4.addRow(new Object[]{skill.name, character.getSkillMod(skill)});
            } else if (s.equals(wis)) {
                charsheet.tblSkillWIS.getModel();
                DefaultTableModel model5 = (DefaultTableModel) charsheet.tblSkillWIS.getModel();
                model5.addRow(new Object[]{skill.name, character.getSkillMod(skill)});
            } else /*if (s == "CHA")*/ {
                charsheet.tblSkillCHA.getModel();
                DefaultTableModel model6 = (DefaultTableModel) charsheet.tblSkillCHA.getModel();
                model6.addRow(new Object[]{skill.name, character.getSkillMod(skill)});
            }
        }
    }

    //------------Spellbook Tab-------
    //------------About Tab-----------
    private final static String newline = "\n";

    public void refreshAboutTab() {
        String trait = new String();
        String ideal = new String();
        String bond = new String();
        String flaw = new String();

        for (int i = 0; i < character.traits.size(); i++) {
            trait += character.traits.get(i).trim() + newline;
        }
        for (int i = 0; i < character.ideals.size(); i++) {
            ideal += character.ideals.get(i).trim() + newline;
        }
        for (int i = 0; i < character.bonds.size(); i++) {
            bond += character.bonds.get(i).trim() + newline;
        }
        for (int i = 0; i < character.flaws.size(); i++) {
            flaw += character.flaws.get(i).trim() + newline;
        }

        charsheet.taTrait.setText(trait);
        charsheet.taIdeal.setText(ideal);
        charsheet.taBond.setText(bond);
        charsheet.taFlaw.setText(flaw);
    }
}
