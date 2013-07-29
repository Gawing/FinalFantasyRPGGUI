/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nz.ac.aut.dns3948.finalfantasyrpggui.game;

import java.util.ArrayList;
import nz.ac.aut.dns3948.finalfantasyrpggui.ui.Dialog;

/**
 * Class ItemInventory
 * A class that stores and uses the game items
 * @author dns3948 GavinC
 */
public class ItemInventory {
    private ArrayList<Item> invent = new ArrayList();
    int potionCount = 0;
    int stoneCount = 0;
    int ultiCount = 0;
    boolean hasItem = false;
    private Dialog gameDialog = null;

    /**
     * Constructor for class ItemInventory
     */
    public ItemInventory(){
    }

    /**
     * Add an item to the inventory
     * @param newItem the added item
     * @return the item added
     */
    public String addItem(Item newItem){   
        if (newItem.getName().equalsIgnoreCase("Potion")){
                potionCount++;
            }
            else if (newItem.getName().equalsIgnoreCase("MagicStone")){
                stoneCount++;
            }
            else if (newItem.getName().equalsIgnoreCase("UltimaStone")){
                ultiCount++;
            }
        invent.add(newItem);
        hasItem = true;
        return newItem.getName();
    }

    /**
     * Use an item in an item inventory
     * @param itemName name of the item to be used
     */
    public void useItem(String itemName){
        hasItem = false;
        // Check if the inventory is empty
        if (invent.isEmpty()){
            gameDialog = new Dialog("No item in inventory");
            gameDialog.showErrorMsg();
        }
        else { // Search for the item and if found removes it from the inventory
            for (Item item: invent){
                if (item.getName().equalsIgnoreCase(itemName)){
                    invent.remove(item);
                    hasItem = true;
                    break;
                }
                else {
                    hasItem = false;
                }
            }     
            if (!hasItem){  // If the inventory doesn't have the item
                gameDialog = new Dialog("Does not have Item");
                gameDialog.showErrorMsg();
            }
        }
        // Decrease the item count
        if (itemName.equalsIgnoreCase("Potion") && !hasItem == false){
            potionCount--;
        }
        else if (itemName.equalsIgnoreCase("MagicStone") && !hasItem == false){
            stoneCount--;
        }
        else if (itemName.equalsIgnoreCase("UltimaStone") && !hasItem == false){
            ultiCount--;
        }
    }

    /**
     * Check if inventory has item
     * @return true if has item otherwise false
     */
    public boolean hasItem(){
        return hasItem;
    }

    /**
     * Get the amount of potion in the inventory
     * @return potion amount
     */
    public int getPotion(){
        return potionCount;
    }

    /**
     * Get the amount of magic stone in the inventory
     * @return magic stone amount
     */
    public int getStone(){
        return stoneCount;
    }

    /**
     * Get the amount of ultima stone in the inventory
     * @return ultima stone amount
     */
    public int getUlti(){
        return ultiCount;
    }

    /**
     * Display the item inventory
     * @return the item inventory
     */
    public String displayList(){ 
        String itemInvent = "*******Item Inventory*******\n\n"
        + "Potion: " + potionCount + "\n\nMagicStone:  "
        + stoneCount + "\n\nUltimaStone: "+ ultiCount;
        return itemInvent;
    }

}
