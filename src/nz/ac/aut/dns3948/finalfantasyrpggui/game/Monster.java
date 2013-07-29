/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nz.ac.aut.dns3948.finalfantasyrpggui.game;

import java.util.Random;

/**
 * Class Monster
 * A class that generates the monsters in the game
 * @author dns3948 GavinC
 */
public class Monster extends Character {

   private Random monster;
   private String monsDesc;

   /**
    * Constructor for class Monster
    */
   public Monster(){

      super();
      monsDesc = " ";
      monster = new Random();
   }

   /**
    * Generates a random monster
    * @param num the monster's level
    * @return the generated monster information
    */
   public String genMonster(int num){
       String genMon = "";
       int monsGen = monster.nextInt(4);
       int monsHealth = num * 100;
       int monsDmg = num * 10;
       if (monsGen <=1){ // Generates the monster Valefor
           setName("Valefor");
           setHealth(monsHealth + 150);
           setDmg(monsDmg);
           monsDesc = "A wyvern descended from above, uses a devastating attack\n"
                   + "called Sonic Ray when near death";
       }
       else if (monsGen == 3){ // Generates the monster Shiva
           setName("Shiva");
           setHealth(monsHealth + 50);
           setDmg(monsDmg + 20);
           monsDesc = "A ice queen that resides at the top of the earth, "
           + "Beware of her Diamond Dust";
       }
       else {
           setName("Ifrit"); // Generates the monster Ifrit
           setHealth(monsHealth + 100);
           setDmg(monsDmg + 15);
           monsDesc = "A beast that emerged from Hell, his Hellfire is a force "
           + "to be reckon with";
       }
       genMon = " You have encountered " + getName();
       return genMon;
   }

   /**
    * Generates the final boss
    * @return the final boss information
    */
   public String genBoss(){
       String newBoss = "";
       setName("Bahamut");
       setHealth(5000);
       setDmg(100);
       monsDesc = "A Dragon that awoke from the depth of the earth, " +
               "Beat it to win the game";

       newBoss = getName() + " emerges.......";
       return newBoss;
   }

   /**
    * Gets the monsters details including name, description and damage
    * @return the monsters details
    */
   public String getDetails(){
       String monsDetails = "Monster: " + getName() + "\nDetails: " + monsDesc
       + "\nDamage: " + getDamage();
       return monsDetails;
   }

   /**
    * Gets the monster description
    * @return the monster description
    */
   public String getMonsDesc(){
       return monsDesc;
   }

}
