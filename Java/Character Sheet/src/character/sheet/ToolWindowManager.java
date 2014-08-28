/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character.sheet;

import java.awt.Component;
import java.awt.Font;
import java.util.EmptyStackException;
import java.util.List;
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

    public void rollInit() {
        
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
        Boolean prepared;

        adv = -1*(toolWindow.cbAdvantageInit.getSelectedIndex()-1);
        label = "Miscellaneous";

        initMiscRoll = toolWindow.txtMiscModInit.getText();
        
        //TODO need functionality for weaponUsed

        //Parse input into parts, place into list
        List<String> rollInputs = roller.parseRolls(initMiscRoll);

        Roll roll = new Roll();

        roll.addList(label, rollInputs);
        roll.addRoll(label, "1d10", adv, null, null);
        roll.roll(roller);

        total = String.valueOf(roll.res);
        
        updateRollComponents(roll);

        //Set display
        toolWindow.lblRollResult.setText(total);
        toolWindow.txtRollOverride.setText(total);
        toolWindow.lblRollType.setText("Initiative");
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

        adv = -1*(toolWindow.cbAdvantageAttack.getSelectedIndex()-1);

        label = "Miscellaneous";

        attMiscRoll = toolWindow.txtMiscModAttack.getText();

        //TODO need functionality for weaponUsed
        
        //Parse input into parts, place into list
        List<String> rollInputs = roller.parseRolls(attMiscRoll);

        Roll roll = new Roll();

        roll.addList(label, rollInputs);
        roll.roll(roller);

        total = String.valueOf(roll.res);
        
        updateRollComponents(roll);

        //Set display
        toolWindow.lblRollResult.setText(total);
        toolWindow.txtRollOverride.setText(total);
        toolWindow.lblRollType.setText("Attack");
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

        labelMisc = "Miscellaneous";
        labelClass = "Class Misc";
        weaponUsed = "FIX WEAPON";
        criticals = toolWindow.cbCriticalsDamage.getSelectedIndex();
        //TODO need functionality for criticals dropdown
        
        //TODO need functionality for weaponUsed
        
        dmgMiscRoll = toolWindow.txtMiscModDamage.getText();
        dmgClassRoll = toolWindow.txtClassModDamage.getText();

        //Parse input into parts, place into list
        List<String> rollMiscInputs = roller.parseRolls(dmgMiscRoll);
        List<String> rollClassInputs = roller.parseRolls(dmgClassRoll);

        Roll roll = new Roll();

        roll.addList(labelMisc, rollMiscInputs);
        roll.addList(labelClass, rollClassInputs);
        roll.roll(roller);

        total = String.valueOf(roll.res);
        
        updateRollComponents(roll);

        //Set display
        toolWindow.lblRollResult.setText(total);
        toolWindow.txtRollOverride.setText(total);
        toolWindow.lblRollType.setText("Damage " + weaponUsed);
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
        Integer adv;

        adv = -1*(toolWindow.cbAdvantageGeneric.getSelectedIndex()-1);
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
    
    public void submitRoll(){
        
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
    
    public void refreshHP() {
        toolWindow.txtCurrHPVal.setText(String.valueOf(character4.curhp));
        toolWindow.txtHPMaxVal.setText(String.valueOf(character4.maxhp));
    }
}
