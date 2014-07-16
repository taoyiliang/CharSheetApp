/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package character.sheet;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.EmptyStackException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Reid
 */
public class CharSheetManager 
{
    private Charsheet charsheet;
    private Character character;
    
    
    public void setCharSheet(Charsheet newsheet)
    {
        charsheet = newsheet;
    }
    public void setCharacter(Character newchar)
    {
        if (charsheet!=null)
        {
        character = newchar;
        refreshCharacter();
        }
        else
        {
            throw new EmptyStackException();
        }
    }
    public void refreshCharacter()
    {
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
        refreshFeatsTab();
        refreshAboutTab();
    }
    public void refreshCharacterName()
    {
        charsheet.lblCharName.setText(character.name);
    }
    //----------Character Tab-----------
    public void refreshCharacteristics()
    {
        charsheet.lblRace.setText(character.race.name);
        charsheet.lblClass.setText(character.cclass.name);
        charsheet.lbl2ndClass.setText("");
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
    public void refreshAbilityScores()
    {
        //Abilities
        charsheet.lblSTRVal.setText(String.valueOf(character.STR));
        charsheet.lblDEXVal.setText(String.valueOf(character.DEX));
        charsheet.lblCONVal.setText(String.valueOf(character.CON));
        charsheet.lblINTVal.setText(String.valueOf(character.INT));
        charsheet.lblWISVal.setText(String.valueOf(character.WIS));
        charsheet.lblCHAVal.setText(String.valueOf(character.CHA));
        
        //Ability Mods
        charsheet.lblSTRModVal.setText(String.valueOf(character.strmod));
        charsheet.lblDEXModVal.setText(String.valueOf(character.dexmod));
        charsheet.lblCONModVal.setText(String.valueOf(character.conmod));
        charsheet.lblINTModVal.setText(String.valueOf(character.intmod));
        charsheet.lblWISModVal.setText(String.valueOf(character.wismod));
        charsheet.lblCHAModVal.setText(String.valueOf(character.chamod));
        
    }
    public void refreshExperience()
    {
        charsheet.lblExpLevel.setText(String.valueOf(character.level));
        charsheet.lblCurrentXP.setText(String.valueOf(character.curxp));
        charsheet.lblNextLvlXP.setText(String.valueOf(character.nextxp));
    }
    public void refreshMoney()
    {
        charsheet.lblGoldVal.setText(String.valueOf(character.gp));
        charsheet.lblSilverVal.setText(String.valueOf(character.sp));
        charsheet.lblCopperVal.setText(String.valueOf(character.cp));
        charsheet.lblTotalMoneyVal.setText("");
        //Total money calc? or in a var?
    }
    public void refreshHP()
    {
        charsheet.lblCurrHPVal.setText(String.valueOf(character.curhp));
        charsheet.lblMaxHPVal.setText(String.valueOf(character.maxhp));
    }
    public void refreshCarriedWeight()
    {
        charsheet.lblCarriedWeightVal.setText(String.valueOf(character.carrying));
        charsheet.lblWeightCapacityVal.setText(String.valueOf(character.capacity));
    }
    public void refreshSpeed()
    {
        charsheet.lblSpeedVal.setText(String.valueOf(character.speed));
    }
    public void refreshDTH()
    {
        charsheet.lblDifficultyToHitVal.setText(String.valueOf(character.DTH));
    }
    public void refreshSpecialVision()
    {
        charsheet.lblSpecialVisionVal.setText(String.valueOf(character.vision)
                .substring(1, String.valueOf(character.vision).length() - 1));
    }
    public void refreshLanguages()
    {
        charsheet.lblLanguagesVal.setText(String.valueOf(character.languages)
                .substring(1, String.valueOf(character.languages).length() - 1));
    }
    
    
    
    //------------Equipment Tab-------
    
    public javax.swing.JScrollPane jScrollPane7;
    public javax.swing.JTable tblEquip;
    
    
    public void refreshEquipment() {

        
        tblEquip = new javax.swing.JTable();
        
        tblEquip.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    /*{"Axe of Awesomeness", "Does cool stuff", 1, 4, "Yes"},
                    {null, null, null, null, null}*/
                },
                new String[]{
                    "Item", "Item Description", "Qty", "Weight", "Worn"
                }
        ) 
        
        {
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
        for (Item item:character.equipment)
        {
            addItemsToEquipment(item);
        }
        DefaultTableModel model = (DefaultTableModel) tblEquip.getModel();
        model.addRow(new Object[]{"", "------------------------Weapons------------------------", "", "", ""});
    
        for (Weapon item:character.weapons)
        {
            addItemsToEquipment(item);
        }
        model = (DefaultTableModel) tblEquip.getModel();
        model.addRow(new Object[]{"", "--------------------------Armor--------------------------", "", "", ""});
    
        for (Armor item:character.armor)
        {
            addItemsToEquipment(item);
        }
    }
    public void addItemsToEquipment(Item newItem)
    {
        DefaultTableModel model = (DefaultTableModel) tblEquip.getModel();
        model.addRow(new Object[]{newItem.name, newItem.desc, newItem.quantity, newItem.weight, "test"});
    }
    public void addItemsToEquipment(Weapon newItem)
    {
        DefaultTableModel model = (DefaultTableModel) tblEquip.getModel();
        model.addRow(new Object[]{newItem.name, newItem.desc, newItem.quantity, newItem.weight, newItem.worn});
    }
    public void addItemsToEquipment(Armor newItem)
    {
        DefaultTableModel model = (DefaultTableModel) tblEquip.getModel();
        model.addRow(new Object[]{newItem.name, newItem.desc, newItem.quantity, newItem.weight, newItem.worn});
    }
    
    
    //------------Feats Tab-----------
    public void refreshFeatsTab() {
        charsheet.lblClassAbilityVal.setText(String.valueOf(""));
    }
    //------------Skills Tab----------
    //------------Spellbook Tab-------

    //------------About Tab-----------
    public void refreshAboutTab()
    {
        charsheet.taTrait.setText(String.valueOf(character.traits)
        .substring(1, String.valueOf(character.traits).length() - 1));
        charsheet.taIdeal.setText(String.valueOf(character.ideals)
        .substring(1, String.valueOf(character.ideals).length() - 1));
        charsheet.taBond.setText(String.valueOf(character.bonds)
        .substring(1, String.valueOf(character.bonds).length() - 1));
        charsheet.taFlaw.setText(String.valueOf(character.flaws)
        .substring(1, String.valueOf(character.flaws).length() - 1));  
    }
}

