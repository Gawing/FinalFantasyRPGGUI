/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nz.ac.aut.dns3948.finalfantasyrpggui.game;

/**
 * Class Player
 * A class that represents the user's player
 * @author dns3948 GavinC
 */
public class Player extends Character{
    private String type;
    private int magicStr;
    private int nextLevel;
    private int playerLevel;
    private int magicPoints;
    private ItemInventory item;
    
    /**
     * Constructor for class Player
     * @param playItm players item inventory
     */
    public Player(ItemInventory playItm){
        super();   
        type = "";
        item = playItm;
        magicStr = 0;
        nextLevel = 200;
        playerLevel = 1;
        magicPoints = 0;
    }

    /**
     * Set the player's level
     * @param nextLvl the player's set level
     */
    public void setNextLvl(int nextLvl){
        nextLevel = nextLvl;
    }

    /**
     * Gets the magic level
     * @return the magic level
     */
    public int getMagicStr(){
       return magicStr;
    }
    
    /**
     * Set the magic level
     * @param mag the magic set level
     */
    public void setMagicStr(int mag){
        magicStr = mag;
    }
    
    /**
     * Get the player's MP (magic points)
     * @return the player's MP
     */
    public int getMP(){
        return magicPoints;
    }
    
    /**
     * Set the player's MP
     * @param newMP the player's new set MP
     */
    public void setMP(int newMP){
        magicPoints = newMP;
    }
    
    /**
     * Increases the player's MP
     * @param newPoints the intended points to increase
     */
    public void incMagPoints(int newPoints){
        magicPoints += newPoints;
    }

    /**
     * Gets the player type
     * @return the player type
     */
    public String getType(){
        return type;
    }
    
    /**
     * Set the player type
     * @param newType the player new set type
     */
    public void setType(String newType){
        if (newType.equalsIgnoreCase("Warrior") ||
        newType.equalsIgnoreCase("Spellcaster") ||
        newType.equalsIgnoreCase("Marksman")){
          type = newType;
        }  
    }

    /**
     * Gets the player's next level
     * @return the player's next level
     */
    public int getNextLvl(){
        return nextLevel;
    }

    /**
     * Decreases the player's next level
     * @param newLvl the player's new set level
     */
    public void decNextLvl(int newLvl){
        nextLevel-= newLvl;
    }

    /**
     * Reset the players next level
     */
    public void resetlvl(){
        nextLevel = playerLevel * 200;
    }

    /**
     * Gets the player's level
     * @return player level
     */
    public int getPlayerLvl(){
        return playerLevel;
    }

    /**
     * Sets the player's level
     * @param the player's new level
     */
    public void setPlayerLvl(int newLvl){
        playerLevel = newLvl;
    }

    /**
     * Increase the player level
     * @return the player level information
     */
    public String incPlaylvl(){
        String levelUp;
        if (nextLevel <= 0){
            playerLevel++;
            levelUp = "Your character has leveled up!!\n";
            //Increase warrior attribute
            if (getType().equalsIgnoreCase("Warrior")){
               setHealth(getHealth() + 100);
               setDmg(getDamage() + 30);
               magicStr += 10;
               incMagPoints(10);
            }
            //Increase spellcaster attribute
            else if (getType().equalsIgnoreCase("Spellcaster")){
               setHealth(getHealth() + 50);
               setDmg(getDamage() + 10);
               magicStr += 20;
               incMagPoints(20);
            }
            //Increase marksman attribute
            else {
               setHealth(getHealth() + 75);
               setDmg(getDamage() + 25);
               magicStr += 15;
               incMagPoints(15);
            }
            resetlvl();
        }
        else {
            levelUp = "Your character has gained experience\n";
        }
        //Check player status
        //System.out.println(player.toString());
        return levelUp;
    }

    /**
     * Gets the player information details
     * @return the player's details
     */
    public String displayStats(){
        String stats = ("Name: " + getName() + "\n\nType: " + type +  " \n\nHP "
        + "(Health Points): " + getHealth() + "\n\nMP (Magic Points): " +
        magicPoints + "\n\nStrength: " + getDamage() +" \n\nMagic: " + magicStr +
        " \n\nLevel: " +  playerLevel + " \n\nNext Level: " + nextLevel);
        return stats;
    }

    /**
     * Assign the initial attributes to players depending on the players type
     */
    public void assignAttribute(){
        if (type.equalsIgnoreCase("Warrior")) {
            setHealth(200);
            setDmg(50);
            magicPoints = 10;
            magicStr = 5;
        }
        else if (type.equalsIgnoreCase("Marksman")) {
            setHealth(100);
            setDmg(50);
            magicPoints = 15;
            magicStr = 20;
        }
        else if (type.equalsIgnoreCase("Spellcaster")){
            setHealth(120);
            setDmg(25);
            magicPoints = 30;
            magicStr = 20;
        }
    }

    /**
     * Display the player's item inventory
     * @return the item inventory
     */
    public String displayInvent(){
        return item.displayList();
    }

    /**
     * Add a new item into the player's item inventory
     * @param newItem the new item to be added
     */
    public void addItem(Item newItem){
        item.addItem(newItem);
    }

    /**
     * Use an item in the player's item inventory
     * @param itemName the selected items name
     * @return true if the inventory has the item otherwise false
     */
    public boolean useItem(String itemName){
        item.useItem(itemName);
        return item.hasItem();
    }

    /**
     * Gets the amount of potion in the item inventory
     * @return the potion amount
     */
    public int getPotion(){
        return item.getPotion();
    }

    /**
     * Gets the amount of magic stone in the item inventory
     * @return the magic stone amount
     */
    public int getStone(){
        return item.getStone();
    }

    /**
     * Gets the amount of ultima stone in the item inventory
     * @return the ultima stone amount
     */
    public int getUltiStone(){
        return item.getUlti();
    }

    /**
     * The player ToString details
     * @return player details
     */
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(getName());
        builder.append(",");
        builder.append(type);
        builder.append(",");
        builder.append(getHealth());
        builder.append(",");
        builder.append(magicPoints);
        builder.append(",");
        builder.append(getDamage());
        builder.append(",");
        builder.append(magicStr);
        builder.append(",");
        builder.append(playerLevel);
        builder.append(",");
        builder.append(nextLevel);
        return builder.toString();
    }

}
