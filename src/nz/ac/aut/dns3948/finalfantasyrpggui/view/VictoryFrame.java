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
import nz.ac.aut.dns3948.finalfantasyrpggui.game.Battle;
import nz.ac.aut.dns3948.finalfantasyrpggui.game.Player;
import nz.ac.aut.dns3948.finalfantasyrpggui.ui.Button;
import nz.ac.aut.dns3948.finalfantasyrpggui.ui.ImageCreator;

/**
 * Class VictoryFrame
 * A class that represents that the player has defeated a monster
 * and gains experience
 * @author dns3948 GavinC
 */
public class VictoryFrame extends nz.ac.aut.dns3948.finalfantasyrpggui.ui.Frame {
    private Battle gameB;
    Player player;
    JPanel vicPanel = new JPanel();

    /**
     * Constructor for class VictoryFrame
     * @param battle the battle where the player defeated the monster
     */
    public VictoryFrame(Battle battle){
        super("Victory!");
        gameB = battle;
        this.setLayout(null);
        playMusic("Victory");
        createPnl();
        this.add(vicPanel);
        vicPanel.setBounds(1,1, 500, 500);
        this.setPreferredSize(new Dimension(500,500));
    }

    /**
     * Creates the victory panel and sets it in the frame
     */
    public void createPnl(){
        vicPanel.setLayout(null);
        vicPanel.setBackground(Color.WHITE);
        try {
            ImageCreator vicImg = new ImageCreator("Victory.jpg");
            vicPanel.add(vicImg);
            vicImg.setBounds(150, 20, 300, 300);
        }
        catch (NullPointerException ex){
            // Show Exception has been caught
            // System.out.println("Caught exception: " + ex);
        }
        createComp();
    }

    /**
     * Creates the panel components
     */
    public void createComp(){
        // Shows the battle result
        JTextArea batResult = new JTextArea(gameB.endBattle());
        player = gameB.getPlayer();
        //Check player status
        //System.out.println(player.toString());
        Font txtFont = new Font("Castellar", Font.ITALIC, 18);
        batResult.setFont(txtFont);
        batResult.setEditable(false);
        vicPanel.add(batResult);
        batResult.setBounds(10,250, 480,100);
        createBtn(); // Creates the continue button
    }

    /**
     * Creates the continue button in the panel
     */
    public void createBtn(){
        Button vicBtn = new Button("Continue",200,50,250,350);
        vicBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                closeMusic(); // Closes midi and goes to the main menu
                MainMenu menu = new MainMenu(player);
                // Check player status
                // System.out.println(player.toString());
                menu.pack();
                menu.setVisible(true);
                dispose();
            }
        });
        vicPanel.add(vicBtn);
    }

}
