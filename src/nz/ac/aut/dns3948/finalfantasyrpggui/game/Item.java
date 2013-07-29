/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nz.ac.aut.dns3948.finalfantasyrpggui.game;

import java.util.Random;

/**
 * Class Item
 * A class to create the items in the game
 * @author dns3948 GavinC
 */
public class Item {
    private String itemName;
    private Random itemGen = new Random();

    /**
    * Constructor for class Item
    */
    public Item(){
        
        int createItem = itemGen.nextInt(20); //Generate random item
        if (createItem > 10){
            itemName = "Potion";
        }
        else if (createItem <= 10 && createItem != 0 ){
            itemName = "MagicStone";
        }
        else {
            itemName = "UltimaStone";
        }
        // Check item creation integer
        // System.out.println(createItem);
   }

    /**
     * Gets the Item name
     * @return item name
     */
    public String getName(){
        return itemName;
    }
}
