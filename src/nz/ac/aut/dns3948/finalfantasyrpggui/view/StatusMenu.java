/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nz.ac.aut.dns3948.finalfantasyrpggui.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import nz.ac.aut.dns3948.finalfantasyrpggui.game.Player;
import nz.ac.aut.dns3948.finalfantasyrpggui.ui.Button;
import nz.ac.aut.dns3948.finalfantasyrpggui.ui.ImageCreator;
import nz.ac.aut.dns3948.finalfantasyrpggui.ui.Label;

/**
 * Class StatusMenu
 * A class to display the character attributes
 * @author dns3948 GavinC
 */
public class StatusMenu extends nz.ac.aut.dns3948.finalfantasyrpggui.ui.Frame {
    private String charType;
    private Player player;
    private JPanel statusPnl = new JPanel();

    /**
     * Constructor for class StatusMenu
     * @param newP the player in the game
     * @param charType the player's type
     */
    public StatusMenu(Player newP, String charType){
        super("Character Status");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        player = newP;
        this.charType = charType;
        this.setLayout(null);
        statusPnl.setBackground(Color.white);
        statusPnl.setPreferredSize(new Dimension(400,400));
        this.add(statusPnl);
        statusPnl.setBounds(1,1,400,400);
        createMenu();
        this.setPreferredSize(new Dimension(400,400));
    }

    /**
     * Creates  and sets the status panel and adds the panel components
     */
    public void createMenu(){
        statusPnl.setLayout(null);
        JTextArea charStatus = new JTextArea();
        charStatus.setText(player.displayStats());
        Font newFont = new Font("Castellar", Font.ITALIC, 14);
        charStatus.setFont(newFont);
        charStatus.setEditable(false);
        statusPnl.add(charStatus);
        charStatus.setBounds(180,70, 200, 280);
        addCharacterIcon(); // Adds the character icon image
        addTitle(); // Adds the status panel title
        createBtn(); // Adds the status panel buttons
    }

    /**
     * Adds the character image icon
     */
    public void addCharacterIcon(){
        ImageCreator charIcon = null;
        try {
            if (player.getType().equalsIgnoreCase("Warrior")){
                charIcon = new ImageCreator("WarriorIcon.jpg");
            }
            else if (player.getType().equalsIgnoreCase("Marksman")){
                charIcon = new ImageCreator("MarksmanIcon.jpg");
            }
            else {
                charIcon = new ImageCreator("SpellcasterIcon.jpg");
            }
        }
        catch (NullPointerException ex){
            //Show Exception has been caught
            //System.out.println("Caught exception: " + ex);
        }
        statusPnl.add(charIcon);
        charIcon.setBounds(20, 70, 200, 200);
    }

    /**
     * Creates and adds the status panel title
     */
    public void addTitle(){
        Label title = new Label("Character Status", 300, 30, 70, 10);
        statusPnl.add(title);
    }

    /**
     * Creates the continue button in the status panel
     */
    public void createBtn(){
        Button conBtn = new Button("Continue", 150, 30, 10, 300);
        Font newFont = new Font("Castellar", Font.PLAIN, 12);
        conBtn.setFont(newFont);
        conBtn.setForeground(Color.white);
        conBtn.setBackground(Color.darkGray);
        conBtn.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
               dispose(); // Disposes the status menu
           }
        });
        statusPnl.add(conBtn);
    }
}
