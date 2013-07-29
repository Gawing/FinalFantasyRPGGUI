/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nz.ac.aut.dns3948.finalfantasyrpggui.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import nz.ac.aut.dns3948.finalfantasyrpggui.game.Battle;
import nz.ac.aut.dns3948.finalfantasyrpggui.game.Player;
import nz.ac.aut.dns3948.finalfantasyrpggui.ui.Button;
import nz.ac.aut.dns3948.finalfantasyrpggui.ui.Dialog;
import nz.ac.aut.dns3948.finalfantasyrpggui.ui.ImageCreator;
import nz.ac.aut.dns3948.finalfantasyrpggui.ui.Label;

/**
 * Class BattleMenu
 * A class that represents the battle GUI for the RPG game
 * @author dns3948 GavinC
 */
public class BattleMenu extends nz.ac.aut.dns3948.finalfantasyrpggui.ui.Frame {
    private Player player;
    private Battle battle;
    JPanel monsImg = new JPanel();
    JPanel title = new JPanel();
    JPanel charStatusPnl = new JPanel();
    JPanel actionPnl = new JPanel();
    JPanel magPnl = new JPanel();
    JPanel itmPnl = new JPanel();
    JTextArea batStatus = null;
    Label itmSel = new Label("",1,1,1,1);
    Label magLbl = new Label("",1,1,1,1);
    private Dialog gameDialog = null;
    private ImageCreator monsImage = null;
    private ImageCreator battleChar = null;
    private JTextArea outputTxt = new JTextArea("");
    Font btnFont = new Font("Castellar", Font.BOLD, 12);
    boolean isBoss = false;
    boolean bossOD = false;
    private JLabel eleFLbl = new JLabel();
    private JLabel eleBLbl = new JLabel();
    private JLabel eleTLbl = new JLabel();
    private JLabel holyLbl = new JLabel();
    private JLabel ultiLbl = new JLabel();
    private JLabel potLbl = new JLabel();
    private JLabel stoneLbl = new JLabel();
    private JLabel uStoneLbl = new JLabel();

    /**
     * Constructor for class BattleMenu
     * @param the user's player, the battle class with the user's player
     * as a parameter
     */
    public BattleMenu(Player newP, boolean boss){
        super("Battle Menu");
        player = newP;
        battle = new Battle(player);
        isBoss = boss;
        if (isBoss){ // Generate boss battle music and output
            outputTxt.setText(battle.genBoss());
            playMusic("BossBattle");
        }
        else { // Generate monster battle music and output
            outputTxt.setText(battle.genMons());
            playMusic(getBatMidi());
        }
        outputTxt.setText(outputTxt.getText() + "\nFor more information on "
        + "battle commands,\nAccess the Game/Battle Menu in the Help Menu tab"
        + "\nFor Items and Magic information, Access the Magic/Item Help");
        this.setLayout(new BorderLayout());
        monsImg.setLayout(null);
        createPanel();
        setPanel();
        this.setPreferredSize(new Dimension(900,1000));
    }

    /**
     * Gets the battle music midi name dependent on the generated monster
     * @return battle midi name
     */
    public String getBatMidi(){
        String battleMusic = "";
        if(battle.getMonsName().equalsIgnoreCase("Valefor")){
           battleMusic = "Fight1";
        }
        else if(battle.getMonsName().equalsIgnoreCase("Ifrit")){
           battleMusic = "Fight2";
        }
        else if(battle.getMonsName().equalsIgnoreCase("Shiva")){
           battleMusic = "Fight3";
        }
        return battleMusic;
    }

    /**
     * Set the BattleMenu frame layout
     */
    public void setPanel(){
        this.getContentPane().add(title, BorderLayout.NORTH);
        this.getContentPane().add(monsImg, BorderLayout.CENTER);
        this.getContentPane().add(charStatusPnl, BorderLayout.WEST);
        this.getContentPane().add(actionPnl, BorderLayout.SOUTH);
    }

    /**
     * Creates the panel components for the BattleMenu
     */
    public void createPanel(){
        createTitle(); // Create the title label
        createMonsterImg(); // Create the monster image
        createStatusPnl(); // Create the character status panel
        createActionPnl(); // Create the action panel
    }

    /**
     * Creates the panel for the BattleMenu title
     */
    public void createTitle(){
        title.setLayout(null);
        title.setBackground(Color.BLACK);
        title.setPreferredSize(new Dimension(1000, 150));
        ImageCreator titleImg = new ImageCreator("BattleLogo.jpg");
        title.add(titleImg);
        titleImg.setBounds(1, 1, 900, 120);
        createMonsLbl();
    }

    /**
     * Creates the label for the monster name
     */
    public void createMonsLbl(){
        JLabel monsLbl = null;
        // Create boss name
        if (battle.getMonsName().equalsIgnoreCase("bahamut")){
            monsLbl = new JLabel("Final Boss: Bahamut", JLabel.CENTER);
            monsLbl.setForeground(Color.WHITE);
        }
        // Create monster name
        else {
            monsLbl = new JLabel("Monster: " + battle.getMonsName(),
            JLabel.CENTER);
            if (battle.getMonsName().equalsIgnoreCase("Ifrit")){
               monsLbl.setForeground(Color.RED);
            }
            else if (battle.getMonsName().equalsIgnoreCase("Shiva")){
               monsLbl.setForeground(Color.BLUE);
            }
            else {
               monsLbl.setForeground(Color.ORANGE);
            }
        }
        Font newFont = new Font("Castellar", Font.BOLD, 24);
        monsLbl.setFont(newFont);
        title.add(monsLbl);
        monsLbl.setBounds(1, 125, 900, 20);
    }

    /**
     * Create the monster image in the BattleMenu
     */
    public void createMonsterImg(){
        monsImg.setBorder(new LineBorder(Color.BLACK));
        try { // Create the monster image
            if (battle.getMonsName().equalsIgnoreCase("Valefor")){
                monsImage = new ImageCreator("Valefor.jpg");
            }
            else if (battle.getMonsName().equalsIgnoreCase("Ifrit")){
                monsImage = new ImageCreator("Ifrit.jpg");
            }
            else if (battle.getMonsName().equalsIgnoreCase("Shiva")){
                monsImage = new ImageCreator("Shiva.jpg");
            }
            else {
                monsImage = new ImageCreator("Boss.jpg");
            }
            monsImg.add(monsImage);
            monsImage.setBounds(1,1,600,450);
        }
        catch (NullPointerException ex){
            // Show Exception's been caught
            // System.out.println("Caught exception: " + ex);
        }
        monsImg.setPreferredSize(new Dimension(600,450));
    }
    
    /**
     * Creates the character status panel in the BattleMenu
     */
    public void createStatusPnl(){
        charStatusPnl.setLayout(null);
        charStatusPnl.setBackground(Color.WHITE);
        charStatusPnl.setBorder(new LineBorder(Color.BLACK));
        addBattleImg();
        addStatus();
        charStatusPnl.setPreferredSize(new Dimension(300,450));
    }

    /**
     * Creates the character battle image in the character status panel
     */
    public void addBattleImg(){
        boolean checkOD = battle.getOverDrive();
        try {
            if (player.getType().equalsIgnoreCase("Warrior")){
            createWarImg(checkOD);
            }
            else if (player.getType().equalsIgnoreCase("Marksman")){
                createMarkImg(checkOD);
            }
            else {
                createSpellImg(checkOD);
            }
        }
        catch (NullPointerException ex){
            // Show Exception's been caught
            // System.out.println("Caught exception: " + ex);
        }
        charStatusPnl.add(battleChar);
        battleChar.setBounds(1,1,297, 250);
    }

    /**
     * Creates the warrior type battle image dependant on the overdrive mode
     * @param overdrive mode
     */
    public void createWarImg(boolean oDrive){
        if (oDrive){ // Set the warrior overdrive image
            battleChar = new ImageCreator("WarriorOD.jpg");
        }
        else { // Set the warrior normal image
            battleChar = new ImageCreator("WarriorB.jpg");
        }
    }

    /**
     * Creates the marksman type battle image dependant on the overdrive mode
     * @param overdrive mode
     */
    public void createMarkImg(boolean oDrive){
        if (oDrive){ // Set the marksman overdrive image
            battleChar = new ImageCreator("MarksmanOD.jpg");
        }
        else { // Set the marksman normal image
            battleChar = new ImageCreator("MarksmanB.jpg");
        }
    }

    /**
     * Creates the spellCaster type battle image dependant on the overdrive mode
     * @param overdrive mode
     */
    public void createSpellImg(boolean oDrive){
        if (oDrive){ // Set the spellcaster overdrive image
            battleChar = new ImageCreator("SpellcasterOD.jpg");
        }
        else { // Set the spellcaster normal image
            battleChar = new ImageCreator("SpellcasterB.jpg");
        }
    }

    /**
     * Add the character status details in the character status panel
     */
    public void addStatus(){
        batStatus = new JTextArea(battle.checkBattleSts());
        Font statFont = new Font("Castellar", Font.ITALIC, 14);
        batStatus.setFont(statFont);
        batStatus.setEditable(false);
        batStatus.setForeground(Color.WHITE);
        batStatus.setBackground(Color.BLACK);
        charStatusPnl.add(batStatus);
        batStatus.setBounds(0,250,300,200);
    }

    /**
     * Creates the action panel
     */
    public void createActionPnl(){
        actionPnl.setLayout(null);
        actionPnl.setBackground(Color.WHITE);
        setActionPnl();
        actionPnl.setPreferredSize(new Dimension(500,350));
    }

    /**
     * Adds and sets the components to the action panel
     */
    public void setActionPnl(){
        addOutputLbl(); // Add the output label
        addScanBtn(); // Add the scan button
        addAttackBtn(); // Add the attack button
        addRecovBtn(); // Add the recover mp button
        addEscapeBtn(); // Add the escape battle button
        addMagPnl(); // Add the magic panel button
        addItmPnl(); // Add the item panel button
        addLabel(); // Add the magic/item title label
    }

    /**
     * Adds the output label to the action panel
     */
    public void addOutputLbl(){
        Font outputFont = new Font("Castellar", Font.BOLD, 14);
        outputTxt.setFont(outputFont);
        outputTxt.setForeground(Color.WHITE);
        outputTxt.setBackground(Color.BLACK);
        outputTxt.setBorder(new LineBorder(Color.BLACK));
        outputTxt.setEditable(false);
        outputTxt.setBounds(1,1,900,90);
        actionPnl.add(outputTxt);
    }

    /**
     * Adds the scan button to the action panel
     */
    public void addScanBtn(){
        Button scanBtn = new Button("Scan " + battle.getMonsName(), 200, 30, 40, 120);
        scanBtn.setFont(btnFont);
        scanBtn.addActionListener(new ActionListener(){
            // Sets the monsters details to the output
            @Override
            public void actionPerformed(ActionEvent e){
                outputTxt.setText(battle.scanMonster());
            }
        });
        actionPnl.add(scanBtn);
    }

    /**
     * Adds the attack button to the action panel
     */
    public void addAttackBtn(){
        Button attackBtn = new Button("Attack", 200, 30, 40, 170);
        attackBtn.setFont(btnFont);
        attackBtn.addActionListener(new ActionListener(){
             // Sets the attack information to the output
            @Override
            public void actionPerformed(ActionEvent e){
                outputTxt.setText(battle.playerAttack());
                checkEvent();
            }
        });
        actionPnl.add(attackBtn);
    }

    /**
     * Adds the recover button to the action panel
     */
    public void addRecovBtn(){
        Button recovBtn = new Button("Recover MP", 200, 30, 40, 220);
        recovBtn.setFont(btnFont);
        recovBtn.addActionListener(new ActionListener(){
            // Player recovers MP and sets the information to the output
            @Override
            public void actionPerformed(ActionEvent e){
                outputTxt.setText(battle.recoverMagic());
                checkEvent();
            }
        });
        actionPnl.add(recovBtn);
    }

    /**
     * Adds the escape button to the action panel
     */
    public void addEscapeBtn(){
        Button escapeBtn = new Button("Escape Battle", 200, 30, 40, 270);
        escapeBtn.setFont(btnFont);
        escapeBtn.addActionListener(new ActionListener(){
           // Creates option dialog to confirm if the player wants to
           // escape the battle
           @Override
           public void actionPerformed(ActionEvent e){
               gameDialog = new Dialog("Are you sure you want to exit the battle?");
               gameDialog.showOptionDialog();
               // User confirms to exit, System disposes the battle menu and
               // creates the main menu
               if (gameDialog.getConfirmation()){
                   closeMusic();
                   gameDialog = new Dialog(battle.escapeBattle());
                   gameDialog.showPlainDialog();
                   MainMenu gameMenu = new MainMenu(player);
                   gameMenu.pack();
                   gameMenu.setVisible(true);
                   dispose();
               }
           }
        });
        actionPnl.add(escapeBtn);
    }

    /**
     * Creates the magic and item title label in the action panel
     */
    public void addLabel(){
        // Create magic label
        Label magMenuLbl = new Label("Magic Menu ", 300,20,350,100 );
        Font newFont = new Font("Castellar", Font.BOLD, 12);
        magMenuLbl.setFont(newFont);
        // Create item label
        Label itmLbl = new Label("Item Inventory ", 300,20,650,100 );
        itmLbl.setFont(newFont);
        actionPnl.add(magMenuLbl);
        actionPnl.add(itmLbl);
    }

    /**
     * Creates the magic panel in the action panel
     */
    public void addMagPnl(){
        magPnl.setLayout(new GridLayout(6,2,2,2));
        magPnl.setBackground(Color.WHITE);
        magPnl.setBorder(new LineBorder(Color.BLACK));
        setMagPnl();
        actionPnl.add(magPnl);
        magPnl.setBounds(280, 120, 270, 200);
    }

    /**
     * Sets and add the components in the magic panel
     */
    public void setMagPnl(){
        addMagBtn("Fire"); // Adds the fire magic button
        setEleLbl("Fire"); // Adds the fire magic label
        addMagBtn("Blizzard"); // Adds the blizzard magic button
        setEleLbl("Blizzard"); // Adds the blizzard magic label
        addMagBtn("Thunder"); // Adds the thunder magic button
        setEleLbl("Thunder"); // Adds the thunder magic label
        addMagBtn("Holy"); // Adds the holy magic button
        addHolyLbl(); // Adds the holy magic label
        addMagBtn("Ultima"); // Adds the ultima magic button
        addUltiLbl(); // Adds the ultima magic label
        addMagSel(); // Adds the magic selection label
        addSelectBtn(); // Adds the select magic button
    }

    /**
     * Adds the magic button in the magic panel
     * @param magicName magic button name
     */
    public void addMagBtn(String magicName){
        final String magName = magicName;
        Button magBtn = new Button(magName,1,1,1,1);
        magBtn.setFont(btnFont);
        magBtn.addActionListener(new ActionListener(){
            //Sets the magic name to the magic label
            @Override
            public void actionPerformed(ActionEvent e){
                magLbl.setText(magName);
            }
        });
        magPnl.add(magBtn);
    }

    /**
     * Set the elemental MP cost label
     * @param magName the magic name
     */
    public void setEleLbl(String magName){
        if (magName.equalsIgnoreCase("Fire")){
            eleFLbl = new JLabel("Cost " + battle.getElemental() + " MP");
            eleFLbl.setFont(btnFont);
            magPnl.add(eleFLbl);     
        }
        else if (magName.equalsIgnoreCase("Blizzard")){
            eleBLbl = new JLabel("Cost " + battle.getElemental() + " MP");
            eleBLbl.setFont(btnFont);
            magPnl.add(eleBLbl);
        }
        else {
            eleTLbl = new JLabel("Cost " + battle.getElemental() + " MP");
            eleTLbl.setFont(btnFont);
            magPnl.add(eleTLbl);
        }
    }

    /**
     * Set the MP cost labels
     */
    public void setMagLbl(){
        eleFLbl.setText("Cost " + battle.getElemental() + " MP");
        eleBLbl.setText("Cost " + battle.getElemental() + " MP");
        eleTLbl.setText("Cost " + battle.getElemental() + " MP");
        holyLbl.setText("Cost " + battle.getHoly() + " MP");
        ultiLbl.setText("Cost " + battle.getUlti() + " MP");
    }

    /**
     * Add the holy magic MP cost label
     */
    public void addHolyLbl(){
        holyLbl = new JLabel("Cost " + battle.getHoly() + " MP");
        holyLbl.setFont(btnFont);
        magPnl.add(holyLbl);
    }

    /**
     * Add the ultima magic MP cost label
     */
    public void addUltiLbl(){
        ultiLbl = new JLabel("Cost " + battle.getUlti() + " MP");
        ultiLbl.setFont(btnFont);
        magPnl.add(ultiLbl);
    }

    /**
     * Add the magic select label in the magic panel
     */
    public void addMagSel(){
        magLbl.setFont(btnFont);
        magPnl.add(magLbl);
    }

    /**
     * Add the magic selection button to the magic panel
     */
    public void addSelectBtn(){
        Button magSel = new Button("Select Magic", 1,1,1,1);
        magSel.setFont(btnFont);
        magSel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // Creates an error message when no magic is selected
                if (magLbl.getText().equals("")){
                    gameDialog = new Dialog("Please select a magic");
                    gameDialog.showErrorMsg();
                } 
                else { // Uses the selected magic and prints the magic attack results in
                //the output label
                    outputTxt.setText(battle.useMagic(magLbl.getText()));
                    checkEvent();
                }
            }
        });
        magPnl.add(magSel);
    }

    /**
     * Adds and sets the item panel to the action panel
     */
    public void addItmPnl(){
        itmPnl.setLayout(new GridLayout(4,2,2,2));
        itmPnl.setBorder(new LineBorder(Color.black));
        itmPnl.setBackground(Color.white);
        setItmPnl();
        itmPnl.setBounds(600, 120, 250, 200);
        actionPnl.add(itmPnl);   
    }

    /**
     * Sets the components in the item panel
     */
    public void setItmPnl(){
        addItmBtn("Potion"); // Add potion button
        addItmLbl("Potion"); // Add potion label
        addItmBtn("MagicStone"); // Add magic stone button
        addItmLbl("MagicStone"); // Add magic stone label
        addItmBtn("UltimaStone"); // Add ultima stone button
        addItmLbl("UltimaStone"); // Add ultima stone label
        addItmSel(); // Add item selection label
        addSelBtn(); // Add item selection button
    }

    /**
     * Adds the item button to the item panel
     * @param itmName the item name
     */
    public void addItmBtn(String itmName){
        final String itemNm = itmName;
        Button itmBtn = new Button(itemNm,1,1,1,1);
        itmBtn.setFont(btnFont);
        itmBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // Set the item name as the output text
                itmSel.setText(itemNm);
            }
        });
        itmPnl.add(itmBtn);
    }

    /**
     * Adds the item label to the item panel
     * @param itmName the item name
     */
    public void addItmLbl(String itemName){
        if (itemName.equalsIgnoreCase("Potion")){
            potLbl = new JLabel(": " + player.getPotion());
            potLbl.setFont(btnFont);
            itmPnl.add(potLbl);
        }
        else if (itemName.equalsIgnoreCase("MagicStone")){
            stoneLbl = new JLabel(": " + player.getStone());
            stoneLbl.setFont(btnFont);
            itmPnl.add(stoneLbl);
        }
        else {
            uStoneLbl = new JLabel(": " + player.getUltiStone());
            uStoneLbl.setFont(btnFont);
            itmPnl.add(uStoneLbl);
        }            
    }

    /**
     * Set the item amount on the item labels
     */
    public void setItmLbl(){
        potLbl.setText(": " + player.getPotion());
        stoneLbl.setText(": " + player.getStone());
        uStoneLbl.setText(": " + player.getUltiStone());
    }

    /**
     * Add the item select label to the item panel
     */
    public void addItmSel(){
        itmSel.setFont(btnFont);
        itmPnl.add(itmSel);
    }
    /**
     * Add the item selection button to the item panel
     */
    public void addSelBtn(){
        Button selBtn = new Button("Select Item", 1,1,1,1);
        selBtn.setFont(btnFont);
        selBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // Creates an error message if the item select label is blank
                if (itmSel.getText() == null){
                    gameDialog = new Dialog("Please select an item");
                    gameDialog.showErrorMsg();
                }
                else { // Sets the item use information in the output label
                    outputTxt.setText(battle.useItem(itmSel.getText()));
                    checkEvent();
                }
           }
        });
       itmPnl.add(selBtn);
    }

    /**
     * Check if the monster or the player been defeated
     */
    public void checkEvent(){
        if (battle.getMonsHealth() <= 0){ // If the monster is defeated
            gameDialog = new Dialog("You have defeated " + battle.getMonsName());
            gameDialog.showPlainDialog(); // indicates that the player has
            // defeated the monster
            if (isBoss){
                defeatBoss(); // When the player defeats the boss
            }
            else {
                defeatMonster(); // When the player defeats a monster
            }
        }
        else if (battle.getPlayerHP() <=0 ){ // If the player is defeated
            gameDialog = new Dialog("You have been defeated by " 
            + battle.getMonsName());
            gameDialog.showPlainDialog(); // indicates that the monster has
            // defeated the player
            gameOver(); // When the player is defeated
        }
        else {
            updateBattle(); // when the player did not defeat the monster
        }
    }

    /**
     * The player defeats the monster and moves to the victory menu
     */
    public void defeatMonster(){
        closeMusic();
        VictoryFrame newV = new VictoryFrame(this.battle);
        newV.pack();   
        newV.setVisible(true);
        dispose();
    }

    /**
     * The player defeats the boss and moves to the end menu
     */
    public void defeatBoss(){
        closeMusic();
        EndMenu endGame = new EndMenu(true);
        endGame.pack();
        endGame.setVisible(true);
        dispose();
    }

    /**
     * The player is defeated and moves to the end menu
     */
    public void gameOver(){
        closeMusic();
        EndMenu endGame = new EndMenu(false);
        endGame.pack();
        endGame.setVisible(true);
        dispose();
    }

    /**
     * Updates the battle menu due to the battle interaction
     */
    public void updateBattle(){
        batStatus.setText(battle.checkBattleSts()); // Re-sets the players
        // battle status
        addBattleImg(); // Re-adds the characters battle image
        createMonsterImg(); // Re-adds the monsters battle image
        setMagLbl(); // Re-sets the magic MP cost
        setItmLbl(); // Re-sets the item amount in the inventory
    }

}
        