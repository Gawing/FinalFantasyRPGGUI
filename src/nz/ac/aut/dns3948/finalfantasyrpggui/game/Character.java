package nz.ac.aut.dns3948.finalfantasyrpggui.game;

/**
 * Class Character
 * A class that is an abstract for the characters in the RPG game
 * @author dns3948 GavinC
 */
public abstract class Character {

   private String charName;
   private int charHp;
   private int maxDmg;

   /**
    * Constructor for class Character
    */
   public Character(){
     charHp = 0;
     maxDmg = 0;
    }

   /**
    * Get the character's health
    * @return character's health
    */
   public int getHealth(){
       return charHp;
   }

   /**
    * Get the character's damage
    * @return character's damage
    */
   public int getDamage(){
       return maxDmg;
   }

   /**
    * Get the character's name
    * @return character's name
    */
   public String getName(){
       return charName;
   }

   /**
    * Set the character's damage
    * @param newDmg the new set damage
    */
   public void setDmg(int newDmg){
       maxDmg = newDmg;
   }

   /**
    * Set the character's health
    * @param newHealth the new set health
    */
   public void setHealth(int newHealth){
       charHp = newHealth;
   }

   /**
    * Set the character's name
    * @param newName the new set character name
    */
   public void setName(String newName){
       charName = newName;
   }

}
