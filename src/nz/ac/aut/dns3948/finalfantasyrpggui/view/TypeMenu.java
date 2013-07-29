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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import nz.ac.aut.dns3948.finalfantasyrpggui.game.ItemInventory;
import nz.ac.aut.dns3948.finalfantasyrpggui.game.Player;
import nz.ac.aut.dns3948.finalfantasyrpggui.ui.ImageCreator;
import nz.ac.aut.dns3948.finalfantasyrpggui.ui.Label;
import nz.ac.aut.dns3948.finalfantasyrpggui.ui.Button;
import nz.ac.aut.dns3948.finalfantasyrpggui.ui.Dialog;

/**
 * Class TypeMenu
 * A class that represents the character selection GUI for the RPG game
 * @author dns3948 GavinC
 */
public class TypeMenu extends nz.ac.aut.dns3948.finalfantasyrpggui.ui.Frame {

    private JPanel charPanel = new JPanel();
    private JPanel namePanel = new JPanel();
    private ImageCreator titleImage;
    private Dialog gameDialog = null;
    private JTextField charName;
    private JTextField charType;
    private boolean confirmName = false;
    private boolean confirmType = false;
    private String playerName;
    private String playerType;

    /**
     * Constructor for class TypeMenu
     */
    public TypeMenu(){
        super("Character Selection");
        this.getContentPane().setLayout(new BorderLayout());
        try {
            titleImage = new ImageCreator("WorldLogo.jpg");
            titleImage.createBackground();         
        }
        catch(NullPointerException ex){
            //Show Exception has been caught
            //System.out.println("Caught exception: " + ex);
        }
        this.getContentPane().add(titleImage, BorderLayout.NORTH);
        createCharacterPanel(); // Adds the character panel
        createInfoPanel(); // Adds the character info panel
        this.getContentPane().add(charPanel, BorderLayout.CENTER);
        this.getContentPane().add(namePanel, BorderLayout.SOUTH);
        this.pack();
        this.setVisible(true);
    }        

    /**
     * Creates and adds the character panel and its components to the frame
     */
    public void createCharacterPanel(){
        charPanel.setLayout(null);
        setTitle(); // Set the title instructions
        addCharImage(); // Sets the character images
        addCharBtn(); // Sets the character selection buttons
        charPanel.setPreferredSize(new Dimension(800, 500));
        charPanel.setBackground(Color.WHITE);
    }

    /**
     * Creates and adds the character info panel
     */
    public void createInfoPanel(){
        namePanel.setLayout(new GridLayout(3,3,2,2));
        addInfoPanelLbl();
        namePanel.setPreferredSize(new Dimension(800, 150));
        namePanel.setBorder(new EtchedBorder());
        namePanel.setBackground(Color.WHITE);
    }

    /**
     * Sets the title instruction in the panel
     */
    public void setTitle(){
        Label title = new Label("Please select a character type: ",
                400, 20, 160, 20);
        Label subTitle = new Label("For more information on characters and "
        + "abilities - access the character info in the help menu"
                , 800, 60, 20, 40);
        Font newFont = new Font("Castellar", Font.ITALIC, 12);
        subTitle.setFont(newFont);
        charPanel.add(title);
        charPanel.add(subTitle);
    }

    /**
     * Creates and sets the character images in the panel
     */
    public void addCharImage(){
        try { // Creates warrior image
            ImageCreator warriorImage = new ImageCreator("Warrior.jpg");
            charPanel.add(warriorImage);
            warriorImage.setBounds(50, 120, 116, 200);
            // Creates marksman image
            ImageCreator marksImage = new ImageCreator("Marksman.jpg");
            charPanel.add(marksImage);
            marksImage.setBounds(300, 110, 182, 215);
            // Creates spellcaster image
            ImageCreator spellImage = new ImageCreator("Spellcaster.jpg");
            charPanel.add(spellImage);
            spellImage.setBounds(560, 110, 120, 216);
        }
        catch (NullPointerException ex){
            //Show Exception has been caught
            //System.out.println("Caught exception: " + ex);
        }      
    }

    /**
     * Adds the character buttons to the panel
     */
    public void addCharBtn(){
        createWarBtn(); // Adds warrior button
        createMarkBtn(); // Adds marksman button
        createSpellBtn(); // Adds spellcaster button
    }

    /**
     * Creates and add the warrior button
     */
    public void createWarBtn(){
        Button warBtn = new Button("Warrior", 150, 50, 20, 350);
        warBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                charType.setText("Warrior"); // Set character select field
                // to Warrior
            }
        });
        charPanel.add(warBtn);      
    }

    /**
     * Creates and add the marksman button
     */
    public void createMarkBtn(){
        Button markBtn = new Button("Marksman", 150, 50, 280, 350);
        markBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                charType.setText("Marksman"); // Set character select field
                // to Marksman
            }
        });
        charPanel.add(markBtn);
    }

    /**
     * Creates and add the spellCaster button
     */
    public void createSpellBtn(){
        Button spellBtn = new Button("Spellcaster", 150, 50, 545, 350);
        spellBtn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){
                charType.setText("Spellcaster"); // Set character select field
                // to Spellcaster
            }
        });
        charPanel.add(spellBtn);      
    }

    /**
     * Creates the info panel labels
     */
    public void addInfoPanelLbl(){
        // Creates name label
        Label nameLbl = new Label("Enter a name for your character: ",1,1,1,1);
        Font newFont = new Font("Castellar", Font.BOLD, 12);
        nameLbl.setFont(newFont);
        namePanel.add(nameLbl);
        charName = new JTextField(); // Creates name text field
        charName.setFont(newFont);
        charName.setBackground(Color.lightGray);
        namePanel.add(charName);
        // Creates type label
        Label typeLbl = new Label("Character Type: ",1,1,1,1);
        typeLbl.setFont(newFont);
        namePanel.add(typeLbl);
        charType = new JTextField(); // Creates type text field
        charType.setBorder(new EtchedBorder());
        charType.setEditable(false);
        charType.setFont(newFont);
        charType.setBackground(Color.lightGray);
        namePanel.add(charType);
        addStartBtn(); // Add start game button
    }

    /**
     * Adds start game button
     */
    public void addStartBtn(){
        Button startGame = new Button("Start Game", 1,1,1,1);
        startGame.setBorder(new EtchedBorder());
        startGame.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                checkConfirmation(); // Check player Name and Type confirmation
                //Check confirmation
                //System.out.println(confirmName + " " + confirmType);
                //System.out.println(playerName + " " + playerType);
                if (confirmName == true && confirmType == true){
                    // Creates player with the name and type inputs
                    ItemInventory item = new ItemInventory();
                    Player newPlayer = new Player(item);
                    newPlayer.setName(playerName);
                    newPlayer.setType(playerType);
                    newPlayer.assignAttribute(); // Assign the type attributes
                    // Goes to main menu
                    MainMenu mainGame = new MainMenu(newPlayer);
                    mainGame.pack();
                    mainGame.setVisible(true);
                    dispose();
                }
            }
        });
        namePanel.add(startGame);
        addExitGame();
    }

    /**
     * Add exit game button
     */
    public void addExitGame(){
        Button exitGame = new Button("Exit Game", 1,1,1,1);
        exitGame.setBorder(new EtchedBorder());
        exitGame.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // Creates option dialog to check exit confirmation
                 setExitDialog();
            }
        });
        namePanel.add(exitGame);
    }

    /**
     * Gets name confirmation
     * @return true is name is valid otherwise false
     */
    public boolean getConfirmName(){
        return confirmName;
    }

    /**
     * Gets type confirmation
     * @return true is type is valid otherwise false
     */
    public boolean getConfirmType(){
        return confirmType;
    }

    /**
     * Check that the name and type is valid
     */
    public void checkConfirmation(){
        if (!charName.getText().matches("^[\\w]+$")){ // Name has invalid characters
            gameDialog = new Dialog("Name can only have letters or numbers."
                    + "Please re-enter");
            if (confirmName != true){ // Shows error message
                gameDialog.showErrorMsg();
                charName.setText("");
            }
        }
        else if (charName.getText().length() <=3 ||
                charName.getText().length() > 15){ // Name has invalid length
                gameDialog = new Dialog("Name length is between 4-15 characters."
                    + "Please re-enter");
            if (confirmName != true){
                gameDialog.showErrorMsg(); // Shows error message
                charName.setText("");
            }
        }
        else {
            charName.setForeground(Color.blue); // Show name is accepted
            playerName = charName.getText();
            charName.setText("Name Accepted");
            charName.setEditable(false);
            confirmName = true;
        }

        if (charType.getText().equalsIgnoreCase("Warrior") ||
           charType.getText().equalsIgnoreCase("Marksman") ||
           charType.getText().equalsIgnoreCase("Spellcaster")){
            playerType = charType.getText();
            charType.setForeground(Color.blue);
            confirmType = true; // Shows type is accepted
        }
        else {
            gameDialog = new Dialog("Please select a character type");
            gameDialog.showErrorMsg(); // Type is left blank
            if (confirmType != true){
                confirmType = false;
            }
        }
    }
}

