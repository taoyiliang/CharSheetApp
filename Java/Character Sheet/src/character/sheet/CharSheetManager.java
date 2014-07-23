/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character.sheet;

import java.awt.*;
import java.util.EmptyStackException;
import java.util.List;
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

    public void setCharacter(Character newchar) {
        if (charsheet != null) {
            character = newchar;
            refreshCharacter();
        } else {
            throw new EmptyStackException();
        }
    }

    public void refreshCharacter() {
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

    public void refreshCharacterName() {
        charsheet.lblCharName.setText(character.name);
    }

    //----------Character Tab-----------
    public void refreshCharacteristics() {
        charsheet.lblRace.setText(character.race.name);
        charsheet.lblClass.setText(character.cclass.name);
        charsheet.lbl2ndClass.setText(character.cclass.subclass.name);
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

    public void refreshAbilityScores() {
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

    public void refreshExperience() {
        charsheet.lblExpLevel.setText(String.valueOf(character.level));
        charsheet.lblCurrentXP.setText(String.valueOf(character.curxp));
        charsheet.lblNextLvlXP.setText(String.valueOf(character.nextxp));
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
        charsheet.lblCarriedWeightVal.setText(String.valueOf(character.carrying));
        charsheet.lblWeightCapacityVal.setText(String.valueOf(character.capacity));
    }

    public void refreshSpeed() {
        charsheet.lblSpeedVal.setText(String.valueOf(character.speed));
    }

    public void refreshDTH() {
        charsheet.lblDifficultyToHitVal.setText(String.valueOf(character.DTH));
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
        model.addRow(new Object[]{newItem.name, newItem.desc, newItem.quantity, newItem.weight, newItem.worn});
    }

    public void addItemsToEquipment(Armor newItem) {
        DefaultTableModel model = (DefaultTableModel) tblEquip.getModel();
        model.addRow(new Object[]{newItem.name, newItem.desc, newItem.quantity, newItem.weight, newItem.worn});
    }

    //Create Cell renderer for word wrapping
    Font f = new Font("SansSerif", Font.PLAIN, 11);
    
    public class MyCellRenderer extends JTextArea implements TableCellRenderer {
     public MyCellRenderer() {
       setLineWrap(true);
       setWrapStyleWord(true);
    }

   public Component getTableCellRendererComponent(JTable table, Object
           value, boolean isSelected, boolean hasFocus, int row, int column) {
       setText(String.valueOf(value)); //or something in value, like value.getNote()...
       setSize(table.getColumnModel().getColumn(column).getWidth(),
               getPreferredSize().height);
       if (table.getRowHeight(row) != getPreferredSize().height) {
               table.setRowHeight(row, getPreferredSize().height);
       }
       this.setFont(f);
       return this;
   }
} 
    
    //------------Feats Tab-----------
    //public javax.swing.JScrollPane sbFeats;

    public void refreshFeatsTab() {
        charsheet.lblClassAbilityVal.setText(String.valueOf(""));

        //Feats list
        //charsheet.sbFeats = new javax.swing.JScrollPane();
        JScrollPane scrollbox;
        JTextArea textarea;
        JLabel lblName;
        JLabel lblLvl;
        JPanel pnlLabels;
        //JPanel pnlFeatGroup;
        
        Integer featSize = character.cclass.attributes.size() + character.cclass.subclass.attributes.size();
        //charsheet.pnlFeats.setLayout(new java.awt.);
        SpringLayout layout = new SpringLayout();
        //GridLayout glayout = new GridLayout(0, 1, 5, 5);
        GridLayout glayoutLbls;
        //GridLayout glayoutFeat;
        charsheet.pnlFeats.setLayout(layout);
        charsheet.sbFeats.setViewportView(charsheet.pnlFeats);
        
        javax.swing.JScrollPane saFeats;
        javax.swing.JTable tblFeats;
        
        tblFeats = new javax.swing.JTable();

        tblFeats.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{ {"Extra Hit", 1, "Does cool Stuff that goes on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and on and doesn't stop"},
                 {"Awesomely Fast", 3, "This feat makes it so that you are considered Awesomely Fast and can move up to 10 extra feet each round"}},
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
            tblFeats.getColumnModel().getColumn(1).setPreferredWidth(2);
            tblFeats.getColumnModel().getColumn(2).setPreferredWidth(425);
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
                .addComponent(saFeats, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
                .addGroup(pnlFeatsLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFeatsLayout.setVerticalGroup(
                pnlFeatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlFeatsLayout.createSequentialGroup()
                        .addComponent(saFeats, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
        );
        //Font f = new Font("SansSerif", Font.PLAIN, 12);
        //tblFeats.setFont(f);
        
        for (int i = 0; i < 10; i++) {
            
            
            
            
            
            /*
            scrollbox = new JScrollPane();
            //scrollbox.setSize(300, 200);
            textarea = new JTextArea();
            textarea.setColumns(20);
            textarea.setRows(5);
            //textarea.setEditable(false);
            //textarea.setSize(100, 50);
            lblName = new JLabel();
            lblName.setText("Test Name"); 
            lblName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            //lblName.setPreferredSize(new Dimension(5,10));
            lblLvl = new JLabel();
            lblLvl.setText(String.valueOf("Level " + i));
            
            pnlLabels = new JPanel();
            
            /*
            pnlFeatGroup = new JPanel();
            
            
            glayoutFeat = new GridLayout(1, 2, 2, 2);
            */
            /*
            glayoutLbls = new GridLayout(2, 1, 0, 0);
            
            pnlLabels.setLayout(glayoutLbls);
            
            //pnlLabels.setSize(25, 10);
            pnlLabels.add(lblName);
            pnlLabels.add(lblLvl);
            */
            /*
            //scrollbox.add(textarea);
            scrollbox.setViewportView(textarea);
            
            pnlFeatGroup.setLayout(glayoutFeat);
            pnlFeatGroup.add(pnlLabels);
            pnlFeatGroup.add(scrollbox);
            
            charsheet.pnlFeats.add(pnlFeatGroup);
            charsheet.sbFeats.setViewportView(pnlFeatGroup);*/
            //charsheet.sbFeats.setViewportView(pnlAllFeats);
            //pnlFeatGroup.setVisible(true);
            /*
            charsheet.pnlFeats.add(lblName);
            charsheet.pnlFeats.add(lblLvl);
            //charsheet.pnlFeats.add(pnlLabels);
            charsheet.pnlFeats.add(scrollbox);
            layout.putConstraint(SpringLayout.WEST, scrollbox, 5, SpringLayout.EAST, lblLvl);
            
            /*
            layout.putConstraint(SpringLayout.WEST, pnlLabels, 5, SpringLayout.WEST, charsheet.pnlFeats);
            layout.putConstraint(SpringLayout.NORTH, pnlLabels, 5, SpringLayout.NORTH, charsheet.pnlFeats);
            layout.putConstraint(SpringLayout.WEST, scrollbox, 10, SpringLayout.EAST, charsheet.pnlFeats);
            layout.putConstraint(SpringLayout.NORTH, scrollbox, 5, SpringLayout.NORTH, charsheet.pnlFeats);
            */
            //charsheet.pnlFeats.setVisible(true);
            
            
        }
        //SpringUtilities.makeCompactGrid(charsheet.pnlFeats, 10, 2, 3, 3, 0, 10);
    }

    //------------Skills Tab----------
    //------------Spellbook Tab-------
    //------------About Tab-----------
    public void refreshAboutTab() {
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
