/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nz.ac.aut.dns3948.finalfantasyrpggui.view;

import java.awt.Color;
import nz.ac.aut.dns3948.finalfantasyrpggui.ui.Button;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import nz.ac.aut.dns3948.finalfantasyrpggui.ui.Dialog;
import nz.ac.aut.dns3948.finalfantasyrpggui.ui.Frame;
import nz.ac.aut.dns3948.finalfantasyrpggui.ui.ImageCreator;

/**
 * Class StartMenu
 * A class that represents the starting menu GUI of the RPG game
 * @author dns3948 GavinC
 */
public class StartMenu extends nz.ac.aut.dns3948.finalfantasyrpggui.ui.Frame {
    
    private ImageCreator bgImage;
    private Button newGame;
    private Button loadGame;
    private Button exitGame;
    private Dialog gameDialog = null;

    /**
     * Constructor for class StartMenu
     */
    public StartMenu(){
        super("Final Fantasy RPG");
        try {
            bgImage = new ImageCreator("FFStartMenu.jpg");
            bgImage.createBackground();
        }
        catch (NullPointerException ex){
            // Show Exception has been caught
            // System.out.println("Caught exception: " + ex);
        }
        playMusic("Prelude");
        this.setLayout(null);
        addButtons();
        this.add(bgImage);
        this.setPreferredSize(new Dimension(800,700));
        this.pack();
        this.setVisible(true);

    }

    /**
     * Add button components
     */
    public void addButtons(){
        createNewGameBtn(); // Adds the new game button
        createLoadGameBtn(); // Adds the load game button
        createExitGameBtn(); // Adds the exit game button
    }

    /**
     * Creates the new game button
     */
    public void createNewGameBtn(){
        newGame = new Button("New Game", 160, 50, 290, 380);
        newGame.setForeground(Color.lightGray);
        newGame.setBackground(Color.white);
        newGame.addActionListener(new ActionListener(){     
            @Override
            public void actionPerformed(ActionEvent e){
                // Creates the type menu for character selection
                Frame charSelect = new TypeMenu();{
                charSelect.pack();
                charSelect.setVisible(true);
                closeMusic();
                dispose();
                }
            }
        });
        this.add(newGame);
    }

    /**
     * Creates the load game button
     */
    public void createLoadGameBtn(){
        loadGame = new Button("Load Game", 160, 50, 290, 420);
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
        this.add(loadGame);
    }

    /**
     * Creates the exit game button
     */
    public void createExitGameBtn(){
        exitGame = new Button("Exit Game", 160, 50, 290, 460);
        exitGame.setForeground(Color.lightGray);
        exitGame.setBackground(Color.white);
        exitGame.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // Creates an option dialog for exit confirmation
                setExitDialog();
            }
        });
        this.add(exitGame);
    }

}
