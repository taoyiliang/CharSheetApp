/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character.sheet;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Reid
 */
public class ToolWindowManager {

    public ToolWindow toolWindow;
    private Character character4;
    public Roller roller;
    public Integer damageReduction = 0;
    Integer abilMod = 0;
    String saveAbil;
    Integer abilUsed;
    String rollHistType;
    Integer rollHistNum;

    public ToolWindowManager() {
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
        refreshWeaponList();
        refreshHP();
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
    
    JTable tblWeapons = new javax.swing.JTable();

    public void refreshWeaponList() {
        tblWeapons.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{ /*{"Axe of Awesomeness", "Does cool stuff", 1, 4, "Yes"},
                 {null, null, null, null, null}*/},
                new String[]{
                    "Weapon"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean[]{
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblWeapons.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblWeapons.getTableHeader().setReorderingAllowed(false);

        toolWindow.spWeapon.setViewportView(tblWeapons);

        if (tblWeapons.getColumnModel().getColumnCount() > 0) {
            tblWeapons.getColumnModel().getColumn(0).setPreferredWidth(300);
        }

        DefaultTableModel model = (DefaultTableModel) tblWeapons.getModel();
        model.addRow(new Object[]{"Simple Action"});

        //add items to list
        for (Weapon item : character4.weapons) {
            addWeaponToWeaponsList(item);
        }
        tblWeapons.setRowSelectionInterval(0, 0);
    }

    public void addWeaponToWeaponsList(Weapon newItem) {
        DefaultTableModel model = (DefaultTableModel) tblWeapons.getModel();
        model.addRow(new Object[]{newItem.name});

    }

    public void rollInit() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

        if (toolWindow.tblRollBreakdown.getColumnModel().getColumnCount() > 0) {
            toolWindow.tblRollBreakdown.getColumnModel().getColumn(0).setPreferredWidth(120);
            toolWindow.tblRollBreakdown.getColumnModel().getColumn(1).setPreferredWidth(1);
            toolWindow.tblRollBreakdown.getColumnModel().getColumn(0).setCellRenderer(new MyCellRenderer());
            toolWindow.tblRollBreakdown.getColumnModel().getColumn(1).setCellRenderer(new MyCellRenderer());
        }

        String initMiscRoll;
        String weaponUsed;
        String total;
        String label;
        Integer adv;
        Boolean firstRound;
        Integer firstRoundMod;
        Boolean prepared;
        Integer preparedMod;
        Integer rowSelected;
        Weapon weapon;
        Weapon wpn;
        Integer notPrepared;
        Integer weaponInit;

        adv = -1 * (toolWindow.cbAdvantageInit.getSelectedIndex() - 1);
        label = "Miscellaneous";
        firstRoundMod = -character4.getAbilityMod("DEX");
        firstRound = toolWindow.chkFirstRound.isSelected();
        prepared = toolWindow.chkPrepared.isSelected();
        initMiscRoll = toolWindow.txtMiscModInit.getText();

        //TODO need functionality for weaponUsed
        rowSelected = tblWeapons.getSelectedRow();

        if (rowSelected == 0) {
            wpn = new Weapon();
            wpn.setDefaults();
            weapon = wpn;
        } else {
            weapon = character4.weapons.get(rowSelected - 1);
        }

        preparedMod = weapon.prep;
        //notPrepared = weapon.notprep;
        weaponUsed = weapon.name;
        weaponInit = weapon.init;

        //Parse input into parts, place into list
        List<String> rollInputs = roller.parseRolls(initMiscRoll);

        Roll roll = new Roll();

        roll.addList(label, rollInputs);
        roll.addRoll("Base Initiative", "1d10", adv, null, null);
        roll.addMod("Weapon Initiative", weaponInit);
        if (firstRound == Boolean.TRUE) {
            roll.addMod("DEX Mod", firstRoundMod);
        } else {

        }
        if (prepared == Boolean.TRUE) {

        } else {
            roll.addMod("Not Prepared", preparedMod);
        }

        roll.roll(roller);

        total = String.valueOf(roll.res);

        updateRollComponents(roll);

        //Set display
        toolWindow.lblRollResult.setText(total);
        toolWindow.txtRollOverride.setText(total);
        toolWindow.lblRollType.setText("Initiative (" + weaponUsed + ")");

        rollHistType = "Initiative (" + weaponUsed + ")";
        rollHistNum = roll.res;
        updateRollHistory();
    }

    public void rollAttack() {

        if (toolWindow.tblRollBreakdown.getColumnModel().getColumnCount() > 0) {
            toolWindow.tblRollBreakdown.getColumnModel().getColumn(0).setPreferredWidth(120);
            toolWindow.tblRollBreakdown.getColumnModel().getColumn(1).setPreferredWidth(1);
            toolWindow.tblRollBreakdown.getColumnModel().getColumn(0).setCellRenderer(new MyCellRenderer());
            toolWindow.tblRollBreakdown.getColumnModel().getColumn(1).setCellRenderer(new MyCellRenderer());
        }

        String attMiscRoll;
        String weaponUsed;
        String total;
        String label;
        Integer adv;
        Integer proficiency;
        Integer rowSelected;
        Weapon weapon;
        Weapon wpn;
        Integer minCrit;
        Integer crit;

        adv = -1 * (toolWindow.cbAdvantageAttack.getSelectedIndex() - 1);

        label = "Miscellaneous";
        proficiency = character4.proficiency();

        attMiscRoll = toolWindow.txtMiscModAttack.getText();

        rowSelected = tblWeapons.getSelectedRow();

        if (rowSelected == 0) {
            wpn = new Weapon();
            wpn.setDefaults();
            weapon = wpn;
        } else {
            weapon = character4.weapons.get(rowSelected - 1);
        }

        minCrit = weapon.crit;
        weaponUsed = weapon.name;
        //Parse input into parts, place into list
        List<String> rollInputs = roller.parseRolls(attMiscRoll);

        Roll roll = new Roll();

        roll.addList(label, rollInputs);
        roll.addRoll("Base Attack", "d20", adv, Boolean.TRUE, minCrit);
        if (weapon.prof == Boolean.TRUE) {
            roll.addMod("Proficiency", proficiency);
        } else {

        }
        roll.roll(roller);

        crit = roll.crit;

        total = String.valueOf(roll.res);

        updateRollComponents(roll);

        //Set display
        toolWindow.txtRollOverride.setText(total);
        toolWindow.lblRollType.setText("Attack (" + weaponUsed + ")");
        if (crit == 1) {
            toolWindow.lblRollResult.setText(total + "   CRIT!");
            toolWindow.lblRollResult.setForeground(Color.blue);
        } else if (crit == -1) {
            toolWindow.lblRollResult.setText(total + "   FAIL!");
            toolWindow.lblRollResult.setForeground(Color.red);
        } else {
            toolWindow.lblRollResult.setText(total);
            toolWindow.lblRollResult.setForeground(Color.black);
        }

        rollHistType = "Attack (" + weaponUsed + ")";
        rollHistNum = roll.res;
        updateRollHistory();
    }

    public void rollDamage() {

        if (toolWindow.tblRollBreakdown.getColumnModel().getColumnCount() > 0) {
            toolWindow.tblRollBreakdown.getColumnModel().getColumn(0).setPreferredWidth(120);
            toolWindow.tblRollBreakdown.getColumnModel().getColumn(1).setPreferredWidth(1);
            toolWindow.tblRollBreakdown.getColumnModel().getColumn(0).setCellRenderer(new MyCellRenderer());
            toolWindow.tblRollBreakdown.getColumnModel().getColumn(1).setCellRenderer(new MyCellRenderer());
        }

        String dmgMiscRoll;
        String dmgClassRoll;
        String weaponUsed;
        String total;
        String labelMisc;
        String labelClass;
        Integer criticals;
        Weapon weapon;
        Weapon wpn;
        Integer rowSelected;
        Integer proficiency;

        labelMisc = "Miscellaneous";
        labelClass = "Class Misc";
        criticals = toolWindow.cbCriticalsDamage.getSelectedIndex();
        proficiency = character4.proficiency();
        //TODO need functionality for criticals dropdown

        rowSelected = tblWeapons.getSelectedRow();

        if (rowSelected == 0) {
            wpn = new Weapon();
            wpn.setDefaults();
            weapon = wpn;
        } else {
            weapon = character4.weapons.get(rowSelected - 1);
        }

        weaponUsed = weapon.name;

        dmgMiscRoll = toolWindow.txtMiscModDamage.getText();
        dmgClassRoll = toolWindow.txtClassModDamage.getText();

        //Parse input into parts, place into list
        List<String> rollMiscInputs = roller.parseRolls(dmgMiscRoll);
        List<String> rollClassInputs = roller.parseRolls(dmgClassRoll);

        Roll roll = new Roll();

        if (weapon.prof == Boolean.TRUE) {
            roll.addMod("Proficiency", proficiency);
        } else {

        }

        if (criticals == 1) {
            roll.addWeaponDamage(weapon, 1);
        } else if (criticals == 2) {
            roll.addWeaponDamage(weapon, 2);
        } else if (criticals == 3) {
            roll.addWeaponDamage(weapon, 3);
        } else if (criticals == 4) {
            roll.addWeaponDamage(weapon, 4);
        } else {
            roll.addWeaponDamage(weapon, 0);
        }

        roll.addList(labelMisc, rollMiscInputs);
        roll.addList(labelClass, rollClassInputs);

        roll.roll(roller);

        total = String.valueOf(roll.res);

        updateRollComponents(roll);

        //Set display
        toolWindow.lblRollResult.setText(total);
        toolWindow.txtRollOverride.setText(total);
        toolWindow.lblRollType.setText("Damage (" + weaponUsed + ")");

        rollHistType = "Damage (" + weaponUsed + ")";
        rollHistNum = roll.res;
        updateRollHistory();
    }

    public void rollGenericRoller() {

        if (toolWindow.tblRollBreakdown.getColumnModel().getColumnCount() > 0) {
            toolWindow.tblRollBreakdown.getColumnModel().getColumn(0).setPreferredWidth(120);
            toolWindow.tblRollBreakdown.getColumnModel().getColumn(1).setPreferredWidth(1);
            toolWindow.tblRollBreakdown.getColumnModel().getColumn(0).setCellRenderer(new MyCellRenderer());
            toolWindow.tblRollBreakdown.getColumnModel().getColumn(1).setCellRenderer(new MyCellRenderer());
        }

        String genRoll;
        String genType;
        String total;
        String label;

        label = "Generic";

        genRoll = toolWindow.txtGenericRollInput.getText();
        genType = toolWindow.txtGenericRollWhatFor.getText();

        //Parse input into parts, place into list
        List<String> rollInputs = roller.parseRolls(genRoll);

        Roll roll = new Roll();

        roll.addList(label, rollInputs);

        roll.roll(roller);

        total = String.valueOf(roll.res);

        updateRollComponents(roll);

        //Set display
        toolWindow.lblRollResult.setText(total);
        toolWindow.txtRollOverride.setText(total);
        toolWindow.lblRollType.setText(genType);

        rollHistType = genType;
        rollHistNum = roll.res;
        updateRollHistory();

    }

    public void updateRollComponents(Roll roll) {
        DefaultTableModel modelClear;
        modelClear = (DefaultTableModel) toolWindow.tblRollBreakdown.getModel();
        modelClear.setRowCount(0);

        for (String key : roll.reslist.keySet()) {
            if (key == "result") {
                continue;
            }
            String label = key;
            Integer res = roll.reslist.get(key);

            toolWindow.tblRollBreakdown.getModel();
            DefaultTableModel model = (DefaultTableModel) toolWindow.tblRollBreakdown.getModel();
            model.addRow(new Object[]{label, res});

        }
    }

    public void updateRollHistory() {
        DefaultTableModel model = (DefaultTableModel) toolWindow.tblRollHistory.getModel();
        model.insertRow(0, new Object[]{rollHistType, rollHistNum});
    }

    public void submitRoll() {

    }

    public void refreshDR() {
        Integer type;
        type = toolWindow.cbDamageType.getSelectedIndex();

        if (type == 1) {
            damageReduction = character4.resistance.get("bludgeon");
        } else if (type == 2) {
            damageReduction = character4.resistance.get("piercing");
        } else if (type == 3) {
            damageReduction = character4.resistance.get("slashing");
        } else if (type == 4) {
            damageReduction = character4.resistance.get("acid");
        } else if (type == 5) {
            damageReduction = character4.resistance.get("cold");
        } else if (type == 6) {
            damageReduction = character4.resistance.get("fire");
        } else if (type == 7) {
            damageReduction = character4.resistance.get("force");
        } else if (type == 8) {
            damageReduction = character4.resistance.get("lightning");
        } else if (type == 9) {
            damageReduction = character4.resistance.get("necrotic");
        } else if (type == 10) {
            damageReduction = character4.resistance.get("poison");
        } else if (type == 11) {
            damageReduction = character4.resistance.get("psychic");
        } else if (type == 12) {
            damageReduction = character4.resistance.get("radiant");
        } else if (type == 13) {
            damageReduction = character4.resistance.get("thunder");
        } else {
            damageReduction = 0;
        }
        toolWindow.txtResistance.setText(String.valueOf(damageReduction));

    }

    private final static String newline = "\n";

    public void refreshNotes() {
        String notes = new String();
        toolWindow.txtGenericRollInput.setText(notes);
        for (int i = 0; i < character4.notes.size(); i++) {
            notes += character4.notes.get(i).trim() + newline;
        }

        toolWindow.taNotes.setText(notes);

    }

    public void refreshHP() {
        toolWindow.txtCurrHPVal.setText(String.valueOf(character4.curhp));
        toolWindow.txtHPMaxVal.setText(String.valueOf(character4.maxhp));
    }

    public void takeDamage() {
        character4.curhp -= Integer.parseInt(String.valueOf(toolWindow.txtDamage.getText())) - damageReduction;
        toolWindow.txtDamage.setText(null);
        refreshHP();
    }

    public void heal() {
        character4.curhp += Integer.parseInt(String.valueOf(toolWindow.txtHeal.getText()));
        toolWindow.txtHeal.setText(null);
        refreshHP();
    }

    public void rollSave() {
        if (toolWindow.tblRollBreakdown.getColumnModel().getColumnCount() > 0) {
            toolWindow.tblRollBreakdown.getColumnModel().getColumn(0).setPreferredWidth(120);
            toolWindow.tblRollBreakdown.getColumnModel().getColumn(1).setPreferredWidth(1);
            toolWindow.tblRollBreakdown.getColumnModel().getColumn(0).setCellRenderer(new MyCellRenderer());
            toolWindow.tblRollBreakdown.getColumnModel().getColumn(1).setCellRenderer(new MyCellRenderer());
        }

        String saveMiscRoll;
        String total;
        String label;
        Integer adv;

        adv = -1 * (toolWindow.cbAdvantageSave.getSelectedIndex() - 1);

        label = "Miscellaneous";

        saveMiscRoll = toolWindow.txtMiscModSave.getText();

        //Parse input into parts, place into list
        List<String> rollInputs = roller.parseRolls(saveMiscRoll);

        Roll roll = new Roll();

        roll.addList(label, rollInputs);
        roll.addRoll("Base Save", "d20", adv, null, null);

        roll.addMod(saveAbil + " Mod", abilMod);

        roll.roll(roller);

        total = String.valueOf(roll.res);

        updateRollComponents(roll);

        //Set display
        toolWindow.lblRollResult.setText(total);
        toolWindow.txtRollOverride.setText(total);
        toolWindow.lblRollType.setText("Save (" + saveAbil + ")");

        rollHistType = "Save (" + saveAbil + ")";
        rollHistNum = roll.res;
        updateRollHistory();
    }

    public void refreshSaveMod() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        abilUsed = toolWindow.cbSave.getSelectedIndex();

        if (abilUsed == 1) {
            abilMod = character4.getAbilityMod("STR");
            saveAbil = "Strength";
        } else if (abilUsed == 2) {
            abilMod = character4.getAbilityMod("DEX");
            saveAbil = "Dexterity";
        } else if (abilUsed == 3) {
            abilMod = character4.getAbilityMod("CON");
            saveAbil = "Constitution";
        } else if (abilUsed == 4) {
            abilMod = character4.getAbilityMod("INT");
            saveAbil = "Intelligence";
        } else if (abilUsed == 5) {
            abilMod = character4.getAbilityMod("WIS");
            saveAbil = "Wisdom";
        } else if (abilUsed == 6) {
            abilMod = character4.getAbilityMod("CHA");
            saveAbil = "Charisma";
        } else {
            abilMod = 0;
            saveAbil = "Not Set";
        }
        toolWindow.txtSaveMod.setText(String.valueOf(abilMod));
    }

}
