/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nz.ac.aut.dns3948.finalfantasyrpggui.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import nz.ac.aut.dns3948.finalfantasyrpggui.view.HelpMenu;
import nz.ac.aut.dns3948.finalfantasyrpggui.view.LoadMenu;
import nz.ac.aut.dns3948.finalfantasyrpggui.view.StartMenu;


/**
 * Class Frame
 * A class that creates a custom frame for the RPG game
 * @author dns3948 GavinC
 */
public abstract class Frame extends JFrame {
    JMenuBar menu;
    AudioCreator gMusic;
    Dialog gameDialog = null;
    HelpMenu helpGame = new HelpMenu("Null");

    /**
     * Constructor for class Frame
     * @param title frame menu title name
     */
    public Frame(String title){
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createFileBar();
        createHelpBar();
        gMusic = new AudioCreator("null");
        this.setResizable(false);
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(800, 800));
    }

    /**
     * Creates the menu bar and file menu for the frame and adds the menu item
     * in the file menu
     */
    public void createFileBar(){
        menu = new JMenuBar();
        setJMenuBar(menu);
	JMenu fileMenu = new JMenu("File");
	addMenu(fileMenu);
        fileMenu.add(createNewGameItem());     
        fileMenu.add(createLoadGameItem());
        fileMenu.add(createExitGameItem());
    }

    /**
     * Creates the new game menu item
     * @return new game menu item
     */
    public JMenuItem createNewGameItem(){
        JMenuItem newGame = new JMenuItem("New Final Fantasy RPG");
        newGame.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                setNewDialog();
            }
        });
        return newGame;
    }

    /**
     * Creates the load game menu item
     * @return load game menu
     */
    public JMenuItem createLoadGameItem(){
        JMenuItem loadGame = new JMenuItem("Load Game");
        loadGame.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
               gameDialog = new Dialog("Are you sure you want to load a game?\n"
               + "You will lose this game file");
               gameDialog.showOptionDialog();
               if (gameDialog.getConfirmation()){
                   LoadMenu loadGame = new LoadMenu();
                   loadGame.pack();
                   loadGame.setVisible(true);
                   dispose();
                   try {
                       closeMusic();
                   }
                   catch (NullPointerException ex){

                   }
               }
           }
        });
        return loadGame;
    }

    /**
     * Creates the exit game menu item
     * @return exit game menu
     */
    public JMenuItem createExitGameItem(){
        JMenuItem exitGame = new JMenuItem("Exit Game");
        exitGame.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                setExitDialog();
            }
        });
        return exitGame;
    }

    /**
     * Creates the help menu item in the menu bar and adds the menu item
     */
    public void createHelpBar(){
        JMenu helpMenu = new JMenu("Help");
        addMenu(helpMenu);
        helpMenu.add(createGameHelp());     
        helpMenu.add(createCharHelp());
        helpMenu.add(createInteractHelp());
    }

    /**
     * Creates the game/battle information menu item
     * @return the game/battle information menu item
     */
    public JMenuItem createGameHelp(){
        JMenuItem gameHelp = new JMenuItem("Game/Battle Information");
        gameHelp.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               helpGame.dispose();
               helpGame = new HelpMenu("Game/Battle Help");
               helpGame.pack();
               helpGame.setVisible(true);
            }
        });
        return gameHelp;
    }

    /**
     * Creates the character information menu item
     * @return the character information menu item
     */
    public JMenuItem createCharHelp(){
        JMenuItem charHelp = new JMenuItem("Character Information");
        charHelp.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               helpGame.dispose();
               helpGame = new HelpMenu("Character Help");
               helpGame.pack();
               helpGame.setVisible(true);
            }
        });

        return charHelp;
    }

    /**
     * Creates the magic/item information menu item
     * @return the magic/item information menu item
     */
    public JMenuItem createInteractHelp(){
        JMenuItem magItmHelp = new JMenuItem("Magic/Items Help");
        magItmHelp.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               helpGame.dispose();
               helpGame = new HelpMenu("Magic/Item Help");
               helpGame.pack();
               helpGame.setVisible(true);
            }
        });
        return magItmHelp;
    }

    /**
     * Adds a menu bar in the frame menu
     * @param newMenu menu bar to be added
     */
    public void addMenu(JMenu newMenu){
        menu.add(newMenu);
    }

    /**
     * Creates an AudioCreator class and plays a midi sound file
     * @param midiName the selected midi file to be played
     */
    public void playMusic(String midiName){
        gMusic = new AudioCreator(midiName);
        gMusic.createMusic();
    }

    /**
     * Stops and closes the midi
     */
    public void closeMusic(){
        gMusic.closeMusic();
    }

    /**
     * Set an option dialog where it closes the game
     */
    public void setExitDialog(){
          gameDialog = new Dialog("Do you want to exit this game?");
          gameDialog.showOptionDialog();
          if (gameDialog.getConfirmation()) {
              System.exit(0);
          }
          else {
          }
    }

    /**
     * Set an option dialog  where it starts a new game
     */
    public void setNewDialog(){
         gameDialog = new Dialog("Do you want to start a new game?");
          gameDialog.showOptionDialog();
          if (gameDialog.getConfirmation()) {
              JFrame frame = new StartMenu();
              frame.pack();
              frame.setVisible(true);
              dispose();
              try {
                  closeMusic();
              }
              catch (NullPointerException ex){

              }
          }
    }
}







