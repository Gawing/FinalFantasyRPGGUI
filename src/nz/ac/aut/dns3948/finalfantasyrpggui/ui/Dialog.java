/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nz.ac.aut.dns3948.finalfantasyrpggui.ui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Class Dialog
 * A class that creates the dialogs in the RPG game
 * @author dns3948
 */
public class Dialog extends JOptionPane {

    JFrame frame = new JFrame();
    boolean confirm = false;
    ImageCreator optionIcon;
    ImageIcon newIcon;

    /**
     * Constructor for class Dialog
     * @param msg the error/confirmation message in the dialog
     */
    public Dialog(String msg){
        try {
            optionIcon = new ImageCreator("OptionIcon.jpg");
            newIcon = optionIcon.getIcon();
        }
        catch (NullPointerException ex){
            //Show Exception has been caught
            //System.out.println("Caught exception: " + ex);
        }
        message = msg;
    }

    /**
     * Creates and show an error dialog
     */
    public void showErrorMsg(){
        showMessageDialog(frame, message, "FFRPG Dialog", ERROR_MESSAGE);
    }

    /**
     * Creates and show a option dialog
     */
    public void showOptionDialog(){
        Object[] options = {"Yes, GO GO GO >=O!", "No way! >=]"};
        int answer = showOptionDialog(frame, message,"FFRPG Dialog",
        YES_NO_OPTION,PLAIN_MESSAGE, newIcon, options, options[0]);
        if (answer == JOptionPane.YES_OPTION) {
            confirm = true;
        }
        else {
            frame.dispose();
        }
    }

    /**
     * Creates and show a plain message dialog
     */
    public void showPlainDialog(){
        showMessageDialog(frame, message, "FFRPG Dialog", PLAIN_MESSAGE);
    }

    /**
     * Gets the user's confirmation when the user selects the option dialog
     * @return true if the user selects Yes otherwise false
     */
    public boolean getConfirmation(){
        return confirm;
    }
    
}
