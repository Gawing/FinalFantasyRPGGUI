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
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import nz.ac.aut.dns3948.finalfantasyrpggui.ui.Button;


/**
 * Class HelpMenu
 * A class that provides information about the game
 * @author dns3948
 */
public class HelpMenu extends JFrame{
    JPanel helpPnl = new JPanel();
    JTextField helpTxt = new JTextField();
    File helpFile;
    FileReader txtReader;
    String help = "";

    /**
     * Constructor for class HelpMenu
     * @param help
     */
    public HelpMenu(String help){
        super(help);
        this.setLayout(null);
        helpPnl.setLayout(null);
        this.help = help;
        createHelpPnl();
        setHelpTxt();
        createContinueBtn();
        helpPnl.setBackground(Color.WHITE);
        helpPnl.add(helpTxt);
        this.add(helpPnl);
        helpPnl.setBounds(1, 1, 900, 800);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(900,800));
        
    }
    /**
     * Create the help panel depending on the game help
     */
    public void createHelpPnl(){
        if (help.equalsIgnoreCase("Game/Battle Help")){
            createGHelp();
        }
        else if (help.equalsIgnoreCase("Character Help")){
            createChHelp();
        }
        else if (help.equalsIgnoreCase("Magic/Item Help")){
            createMagItem();
        }
    }

    /**
     * Creates the Game/Battle Help panel
     */
    public void createGHelp(){
        try {

            helpFile = new File("Game Information/GameBattle.txt");
            txtReader = new FileReader(helpFile);
            helpTxt.read(txtReader, helpFile.toString());
            helpTxt.setEditable(false);     
        }
        catch (IOException ex){
            // Show Exception's been caught
            // System.out.println("Caught exception: " + ex);
        }   
    }

    /**
     * Creates the Magic/Item Help panel
     */
    public void createMagItem(){
        try {
            helpFile = new File("Game Information/MagicItem.txt");
            txtReader = new FileReader(helpFile);
            helpTxt.read(txtReader, helpFile.toString());
            helpTxt.setEditable(false);
        }
        catch (IOException ex){
            // Show Exception's been caught
            // System.out.println("Caught exception: " + ex);
        }
    }

    /**
     * Creates the Character Help panel
     */
    public void createChHelp(){
        try {
            helpFile = new File("Game Information/Character.txt");
            txtReader = new FileReader(helpFile);
            helpTxt.read(txtReader, helpFile.toString());
            helpTxt.setEditable(false);
        }
        catch (IOException ex){
            // Show Exception's been caught
            // System.out.println("Caught exception: " + ex);
        }
    }

    /**
     * Creates and add the continue button
     */
    public void createContinueBtn(){
        Button conBtn = new Button("Continue", 200, 50, 400, 710);
        conBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });
        helpPnl.add(conBtn);
    }

    /**
     * Set the help text area
     */
    public void setHelpTxt(){
        helpTxt.setBackground(Color.WHITE);
        Font txtFont = new Font("Castellar", Font.ITALIC, 12);
        helpTxt.setFont(txtFont);
        helpTxt.setBounds(1, 1, 900, 700);
    }

}
