/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nz.ac.aut.dns3948.finalfantasyrpggui.ui;

import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Class ImageCreator
 * A class that creates the images for the RPG game
 * @author dns3948 GavinC
 */
public class ImageCreator extends JPanel {
    private Image image;
    private Graphics bgImage;
    private ImageIcon newImage;

    /**
     * Constructor for class ImageCreator
     * @param img the name of the image file
     */
    public ImageCreator(String img){
        newImage = new ImageIcon(getClass().getResource(img));
        image = newImage.getImage();
    }

    /**
     * Creates a background with the selected image
     */
    public void createBackground(){
        Dimension size = new Dimension(image.getWidth(null),
        image.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
        paintComponent(bgImage);
    }

    /**
     * Paint the component with the selected image
     * @param g the Graphics class which draws the image onto the component
     */
    @Override
    public void paintComponent(Graphics g){
        g.drawImage(image, 0, 0, null);
    }

    /**
     * Get the image as an ImageIcon
     * @return the image
     */
    public ImageIcon getIcon(){
        return newImage;
    }

}
