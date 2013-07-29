/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nz.ac.aut.dns3948.finalfantasyrpggui.ui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

/**
 * Class Label
 * a class to create a custom label component
 * @author dns3948 GavinC
 */
public class Label extends JLabel {

    private Color fgColor = Color.darkGray;
    private Color bgColor = Color.white;
    private Font font;

    /**
     * Constructor for class Label
     * @param textButton the button name
     * @param sizeX the button length size
     * @param sizeY the button height size
     * @param locatX the button x coordinate
     * @param locatY the button y coordinate
     */
    public Label(String text, int sizeX, int sizeY, int locatX, int locatY){
        font = new Font("Castellar", font.BOLD, 18);
        this.setFont(font);
        this.setBounds(locatX, locatY, sizeX, sizeY);
        this.setText(text);
        this.setForeground(fgColor);
        this.setBackground(bgColor);
        this.setBorder(null);
    }

    /**
     * Gets the background color of the label
     * @return the background color
     */
    public Color getBgColor(){
        return bgColor;
    }

    /**
     * Sets the background color
     * @param newBg the background color
     */
    public void changeBgColor(Color newBg){
        bgColor = newBg;
        this.setForeground(bgColor);
    }

    /**
     * Gets the foreground color of the label
     * @return the foreground color
     */
    public Color getFgColor(){
        return fgColor;
    }
    /**
     * Sets the foreground color
     * @param newFg the foreground color
     */
    public void changeFgColor(Color newFg){
        fgColor = newFg;
        this.setForeground(fgColor);
    }

    /**
     * Gets the button font of the label
     * @param the button font
     */
    @Override
    public Font getFont(){
        return font;
    }

    /**
     * Sets the button font
     * @param newFont the label font
     */
    @Override
    public void setFont(Font newFont){
        font = newFont;
    }
}


