/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nz.ac.aut.dns3948.finalfantasyrpggui.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import nz.ac.aut.dns3948.finalfantasyrpggui.ui.Button;
import nz.ac.aut.dns3948.finalfantasyrpggui.ui.Frame;
import nz.ac.aut.dns3948.finalfantasyrpggui.ui.Label;

/**
 * Class EndMenu
 * A class that represents the GUI ending menu of the RPG game
 * @author dns3948 GavinC
 */
public class EndMenu extends nz.ac.aut.dns3948.finalfantasyrpggui.ui.Frame {
    private JPanel endPnl = new JPanel();
    private JTextArea endTxt = new JTextArea();
    private Label endTitle = null;
    
    /**
     * Constructor for class EndMenu
     * @param isWon true if the player defeats the final boss otherwise false
     */
    public EndMenu(boolean isWon){
        super("End Menu");
        this.setLayout(null);
        endPnl.setBackground(Color.WHITE);
        this.add(endPnl);
        createEndPnl();
        endTxt.setAlignmentX(CENTER_ALIGNMENT);
        if (isWon){
            playMusic("WinGame");
            endTxt.setText("Congratulations you have won \nThanks for playing =D");
        }
        else {
            playMusic("GameOver");
            endTxt.setText("Your character has died from battle,\n Game over =(");
        }
    }

    /**
     * Creates the end panel and adds the end panel components
     */
    public void createEndPnl(){
        endPnl.setLayout(null);
        createTitle();
        addBtn();
        createEndTxt();
        endPnl.setBounds(1, 1, 800, 800);
    }

    /**
     * Creates the end panel title
     */
    public void createTitle(){
        endTitle = new Label("The End", 600,200, 120,50);
        Font titleFont = new Font("Castellar", Font.BOLD, 100);
        endTitle.setForeground(Color.LIGHT_GRAY);
        endTitle.setFont(titleFont);
        endPnl.add(endTitle);
    }

    /**
     * Creates and adds the end panel buttons
     */
    public void addBtn(){
        createNewGame(); // Add the new game button
        createLoadGame(); // Add the load game button
        createExitGame(); // // Add the exit game button
    }

    /**
     * Create and add the end panel ending text
     */
    public void createEndTxt(){
        endTxt.setBounds(50, 300, 700,100);
        Font newFont = new Font("Castellar", Font.BOLD, 20);
        endTxt.setFont(newFont);
        endTxt.setForeground(Color.LIGHT_GRAY);
        endTxt.setEditable(false);
        endPnl.add(endTxt);
    }

    /**
     * Create and add the new game button to the end panel
     */
    public void createNewGame(){
        Button newGame = new Button("Restart Game", 180, 50, 280, 380);
        newGame.setForeground(Color.LIGHT_GRAY);
        newGame.setBackground(Color.WHITE);
        newGame.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // Starts a new game and goes to character selection
                Frame charSelect = new TypeMenu();{
                charSelect.pack();
                charSelect.setVisible(true);
                closeMusic();
                dispose();
                }
            }
        });
        endPnl.add(newGame);

    }

    /**
     * Create and add the load game button to the end panel
     */
    public void createLoadGame(){
        Button loadGame = new Button("Load Game", 160, 50, 290, 420);
        loadGame.setForeground(Color.lightGray);
        loadGame.setBackground(Color.white);
        loadGame.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
               LoadMenu loadGame = new LoadMenu();
               loadGame.pack();
               loadGame.setVisible(true);
               closeMusic();
               dispose();
           }
        });
        endPnl.add(loadGame);
    }

    /**
     * Create and add the exit game button to the end panel
     */
    public void createExitGame(){
        Button exitGame = new Button("Exit Game", 160, 50, 290, 460);
        exitGame.setForeground(Color.lightGray);
        exitGame.setBackground(Color.white);
        exitGame.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // Creates an exit option dialog
                setExitDialog();
            }
        });
        endPnl.add(exitGame);
    }
}
