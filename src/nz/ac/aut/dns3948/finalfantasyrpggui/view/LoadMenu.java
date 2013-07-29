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
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import nz.ac.aut.dns3948.finalfantasyrpggui.game.Player;
import nz.ac.aut.dns3948.finalfantasyrpggui.game.SaveLoad;
import nz.ac.aut.dns3948.finalfantasyrpggui.ui.Button;
import nz.ac.aut.dns3948.finalfantasyrpggui.ui.Dialog;


/**
 * A class that allow users to load their saved players
 * @author dns3948 GavinC
 */
public class LoadMenu extends JFrame{
    private SaveLoad loadGame = new SaveLoad();
    private Player player;
    private JTextArea savedGames = new JTextArea();
    private JPanel loadPnl = new JPanel();
    Font txtFont = new Font("Castellar", Font.BOLD, 12);
    JTextField txtInput = new JTextField();

    /**
     * Constructor for class LoadMenu
     */
    public LoadMenu(){
        super("Load Menu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        loadPnl.setBounds(1, 1, 500, 800);
        createLoadPnl();
        savedGames.setEditable(false);
        this.add(loadPnl);
        this.setPreferredSize(new Dimension(500, 800));
    }

    /**
     * Create and set the load panel for the load menu
     */
    public void createLoadPnl(){
        loadPnl.setLayout(null);
        loadPnl.setBackground(Color.WHITE);
        createLoadTxt(); // Create the directory files output
        createLoadBtn(); // Create the load button
        createInput(); // Create the input file text field
    }

    /**
     * Create the output for the directory files
     */
    public void createLoadTxt(){
        try {
            savedGames.setText(loadGame.listDir());
        }
        catch (IOException ex) {
            // Show Exception has been caught
            // System.out.println("Caught exception: " + ex);
        }
        savedGames.setFont(txtFont);
        savedGames.setBounds(1,1,500, 400);
        loadPnl.add(savedGames);
    }

    /**
     * Create and add the input file text field
     */
    public void createInput(){
        JLabel lblTxt = new JLabel("Enter your load file here (without .txt):");
        lblTxt.setBounds(1,570 , 500, 20);
        lblTxt.setFont(txtFont);
        txtInput.setBounds(1,600 , 500, 50);
        txtInput.setFont(txtFont);;
        loadPnl.add(lblTxt);
        loadPnl.add(txtInput);
    }

    /**
     * Create  and add the load button
     */
    public void createLoadBtn(){
        Button conBtn = new Button("Select File", 200, 80, 20, 660);
        conBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                loadGame.loadGame(txtInput.getText());
                if (loadGame.confirmLoad()){ // If load is successful
                    Dialog loadConfirm = new Dialog("Load Complete");
                    // Displays a plain message that game is loaded
                    loadConfirm.showPlainDialog();
                    player = loadGame.getPlayer();
                    // Creates the main menu and exits the load menu
                    MainMenu newMenu = new MainMenu(player);
                    newMenu.pack();
                    newMenu.setVisible(true);
                    dispose();
                }
                else{
                    txtInput.setText("");
                }
            }
        });
        loadPnl.add(conBtn);
        createExitBtn();
    }

    /**
     * Create the exit button
     */
    public void createExitBtn(){
        Button exitBtn = new Button("Exit", 200, 80, 260, 660);
        exitBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // Display the starting game menu and exits the load menu
                StartMenu startGame = new StartMenu();
                startGame.pack();
                startGame.setVisible(true);
                dispose();
            }
        });
        loadPnl.add(exitBtn);
    }
}
