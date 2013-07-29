/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nz.ac.aut.dns3948.finalfantasyrpggui.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import nz.ac.aut.dns3948.finalfantasyrpggui.game.Battle;
import nz.ac.aut.dns3948.finalfantasyrpggui.game.Player;
import nz.ac.aut.dns3948.finalfantasyrpggui.game.SaveLoad;
import nz.ac.aut.dns3948.finalfantasyrpggui.ui.ImageCreator;
import nz.ac.aut.dns3948.finalfantasyrpggui.ui.Button;
import nz.ac.aut.dns3948.finalfantasyrpggui.ui.Dialog;

/**
 * Class MainMenu
 * A class that represents the GUI main menu of the RPG game
 * @author dns3948 GavinC
 */
public class MainMenu extends nz.ac.aut.dns3948.finalfantasyrpggui.ui.Frame {

    private Player player;
    private JPanel mainInteract = new JPanel();
    private JPanel mainInvent = new JPanel();
    JPanel imgPanel = new JPanel();
    private Dialog gameDialog = null;
    private StatusMenu statusMenu;

    /**
     * Constructor for class MainMenu
     * @param newP the player in the game
     */
    public MainMenu(Player newP){

        super("Final Fantasy RPG - Main Realm");
        player = newP;
        statusMenu = new StatusMenu(player ,player.getType());
        this.getContentPane().setLayout(new BorderLayout());
        playMusic("Main");
        addPanels();
        addImage();
        this.getContentPane().add(imgPanel, BorderLayout.NORTH);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Add the panel components to the main menu frame
     */
    public void addPanels(){
        createInteractPanel(); // Adds and create the interaction panel
        createInventPanel(); // Adds and create the item inventory
    }

    /**
     * Add and set the image panel
     */
    public void addImage(){
        try {
            imgPanel.setLayout(null);
            ImageCreator worldImg = new ImageCreator("world.jpg");
            worldImg.setBounds(1,1,800,450);
            imgPanel.add(worldImg);
            imgPanel.setPreferredSize(new Dimension(800,450));
        }
        catch (NullPointerException ex){
            // Show Exception's been caught
            // System.out.println("Caught exception: " + ex);
        }  
    }

    /**
     * Adds and creates the interaction panel and components
     */
    public void createInteractPanel(){
        mainInteract.setLayout(new FlowLayout(FlowLayout.CENTER,25,25));
        mainInteract.setBackground(Color.white);
        mainInteract.setPreferredSize(new Dimension(800,100));
        addInteractBtn();
        this.getContentPane().add(mainInteract,BorderLayout.SOUTH);
    }

    /**
     * Adds the interaction buttons to the interaction panel
     */
    public void addInteractBtn(){
        Color fgColor = Color.darkGray;
        Color bgColor = Color.white;
        createStatusBtn(fgColor,bgColor); // Adds the status button
        createBattleBtn(fgColor,bgColor); // Adds the battle button
        createBossBtn(fgColor,bgColor); // Adds the boss button
        createSaveBtn(fgColor,bgColor); // Adds the save button
        createExitBtn(fgColor,bgColor); // Adds the exit button
    }

    /**
     * Adds and creates the status button
     * @param fg foreground color of the button
     * @param bg background color of the button
     */
    public void createStatusBtn(Color fg, Color bg){
        Button statusBtn = new Button("Check " + player.getName()+" Status"
                ,150,50,1,1);
        statusBtn.setForeground(fg);
        statusBtn.setBackground(bg);
        statusBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)throws NullPointerException{
                // Creates the status menu
                statusMenu.dispose();
                statusMenu = new StatusMenu(player ,player.getType());
                statusMenu.pack();
                statusMenu.setVisible(true);           
            }
        });
        mainInteract.add(statusBtn);
    }

     /**
     * Adds and creates the battle button
     * @param fg foreground color of the button
     * @param bg background color of the button
     */
    public void createBattleBtn(Color fg, Color bg){
        Button battleBtn = new Button("Explore World",150,50,1,1);
        battleBtn.setForeground(fg);
        battleBtn.setBackground(bg);
        battleBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // Creates goes into a battle menu
                BattleMenu battleMenu = new BattleMenu(player,false);
                battleMenu.pack();
                battleMenu.setVisible(true);
                closeMusic();
                dispose();
            }
        });
        mainInteract.add(battleBtn);
    }

     /**
     * Adds and creates the boss button
     * @param fg foreground color of the button
     * @param bg background color of the button
     */
    public void createBossBtn(Color fg, Color bg){
        Button bossBtn = new Button("Fight Final Boss",150,50,1,1);
        bossBtn.setForeground(fg);
        bossBtn.setBackground(bg);
        bossBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // Creates a option dialog to confirm if the user wants to fight
                String msg = "Are you sure you want to fight the Final Boss?";
                gameDialog = new Dialog(msg);
                gameDialog.showOptionDialog();
                if (gameDialog.getConfirmation() == true){
                    // Creates and goes into a battle menu with the boss
                    closeMusic();
                    BattleMenu bossFight = new BattleMenu(player,true);
                    bossFight.pack();
                    bossFight.setVisible(true);
                    dispose();
                }
            }
        });
        mainInteract.add(bossBtn);
    }

    /**
     * Adds and creates the save button
     * @param fg foreground color of the button
     * @param bg background color of the button
     */
    public void createSaveBtn(Color fg, Color bg){
        Button saveBtn = new Button("Save Game",150,50,1,1);
        saveBtn.setForeground(fg);
        saveBtn.setBackground(bg);
        saveBtn.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
              SaveLoad saveGame = new SaveLoad();
              saveGame.saveGame(player);
              gameDialog = new Dialog("Save Completed\nSaved as " +
              player.getName() + ".txt");
              gameDialog.showPlainDialog();         
           }
        });
        mainInteract.add(saveBtn);
    }

    /**
     * Adds and creates the exit button
     * @param fg foreground color of the button
     * @param bg background color of the button
     */
    public void createExitBtn(Color fg, Color bg){
        Button exitBtn = new Button("Exit Game",150,50,1,1);
        exitBtn.setForeground(fg);
        exitBtn.setBackground(bg);
        exitBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // Create an option dialog for exit confirmation
                setExitDialog();
            }
        });
        mainInteract.add(exitBtn);
    }

    /**
     * Creates the item inventory panel with the panel components
     */
    public void createInventPanel(){
        mainInvent.setBackground(Color.white);
        mainInvent.setLayout(null);
        addItemInvent(); // Adds item inventory
        addCharPic(); // Adds character image
        this.getContentPane().add(mainInvent, BorderLayout.CENTER);
    }

    /**
     * Creates and set the item inventory to the item inventory panel
     */
    public void addItemInvent(){
        JTextArea itemInvent = new JTextArea();
        itemInvent.setText(player.displayInvent());
        Font newFont = new Font("Castellar", Font.PLAIN, 18);
        itemInvent.setBounds(300, 25, 400, 350);
        itemInvent.setFont(newFont);
        itemInvent.setEditable(false);
        mainInvent.add(itemInvent);
    }

    /**
     * Creates and set the character image
     */
    public void addCharPic(){
        ImageCreator charImg = null;
        try{
            if (player.getType().equalsIgnoreCase("Warrior")){
                charImg = new ImageCreator("WarriorMain.jpg");
                charImg.setBounds(150, 1, 200, 300);
            }
            else if (player.getType().equalsIgnoreCase("Marksman")){
                charImg = new ImageCreator("MarksmanMain.jpg");
                charImg.setBounds(50, 1, 250, 300);
            }
            else {
                charImg = new ImageCreator("SpellcasterMain.jpg");
                charImg.setBounds(120, 1, 200, 300);
            }
        }
        catch (NullPointerException ex){
            //Show Exception has been caught
            //System.out.println("Caught exception: " + ex);
        }
        mainInvent.add(charImg);
    }
}
