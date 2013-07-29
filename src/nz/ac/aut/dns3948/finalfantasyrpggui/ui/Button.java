/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nz.ac.aut.dns3948.finalfantasyrpggui.ui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

/**
 * Class Button
 * A class that creates a custom button component
 * @author dns3948
 */
public class Button extends JButton {

    private Color fgColor = Color.white;
    private Color bgColor = Color.darkGray;
    private Font font;

    /**
     * Constructor for class Button
     * @param text Button name
     * @param sizeX length size
     * @param sizeY height size
     * @param locatX x coordinate
     * @param locatY y coordinate
     */
    public Button(String text, int sizeX, int sizeY, int locatX, int locatY){
        font = new Font("Castellar", font.BOLD, 18);
        this.setFont(font);
        this.setBounds(locatX, locatY, sizeX, sizeY);
        this.setText(text);
        this.setForeground(fgColor);
        this.setBackground(bgColor);
        this.setBorder(null);
    }

    /**
     * Gets the background color of the button
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
     * Gets the foreground color of the button
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
     * Gets the font of the button
     * @return the button font
     */
    @Override
    public Font getFont(){
        return font;
    }

    /**
     * Sets the button font
     * @param newFont the new set button font
     */
    @Override
    public void setFont(Font newFont){
        font = newFont;
    }

}