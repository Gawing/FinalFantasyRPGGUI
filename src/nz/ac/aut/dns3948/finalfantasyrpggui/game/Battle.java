package nz.ac.aut.dns3948.finalfantasyrpggui.game;

import java.util.Random;

/**
 * Class Battle
 * A class that conducts the battle and battle interactions
 * @author dns3948 GavinC
 */
public class Battle {
    private int playerMp;               
    private int playerHp;              
    private int playerMagic;            
    private int playerStr;              
    private int nextLevel;              
    private int playerLvl;
    private int monsHealth;
    private boolean overDrive;
    private Player player;
    private Monster monster = new Monster();
    private Magic magic;
    private boolean bossRecover;

    /**
     * Constructor for class Battle
     * @param newP the battle player
     */
    public Battle(Player newP){
        player = newP;
        playerMp = player.getMP();
        playerHp = player.getHealth();
        playerMagic = player.getMagicStr();
        playerStr = player.getDamage();
        nextLevel = player.getNextLvl();
        playerLvl = player.getPlayerLvl();
        overDrive = false;
        bossRecover = false;
    }

    /**
     * Displays monster's health
     * @return monster's health points
     */
    public int getMonsHealth(){  
        return monsHealth;
    }

    /**
     * Decreases the monster's health by the damage made by the user's character
     * @param dmg damage taken
     */
    public void decMonsHealth(int dmg){
        monsHealth -= dmg;
    }

    /**
     * Displays the monster's name
     * @return monster's name
     */
    public String getMonsName(){
        return monster.getName();
    }

   /**
    * Players battle HP
    * @return player's battle HP
    */
    public int getPlayerHP(){
        return playerHp;
    }

   /**
    * Set the players battle HP
    * @param newHP the new HP being set
    */
    public void setPlayerHP(int newHp){
        playerHp = newHp;
    }

    /**
     * Players battle MP
     * @return player's battle MP
     */
    public int getPlayerMP(){
        return playerMp;
    }

    /**
     * Set the player's MP
     * @param the new MP being set
     */
    public void setPlayerMP(int newMp){
        playerMp = newMp;
    }

    /**
     * Players battle Strength
     * @return player's battle Strength
     */
    public int getPlayerStr(){
        return playerStr;
    }

    /**
     * Players battle Magic
     * @return player's battle Magic
     */
    public int getPlayerMagic(){
        return playerMagic;
    }

    /**
     * Monster's Damage
     * @return monster's damage
     */
    public int getMonsDmg(){
        return monster.getDamage();
    }

    /**
     * Generate Monster
     */
    public String genMons(){
        String genMons = monster.genMonster(playerLvl);
        monsHealth = monster.getHealth();
        return genMons;
    }

    /**
     * Generate Boss
     */
    public String genBoss(){
        String boss = monster.genBoss();
        monsHealth = monster.getHealth();
        return boss;
    }

    /**
     * See if the boss recovered HP or not
     * @return if the boss recovered
     */
    public boolean regenBoss(){
        return bossRecover;
    }

    /**
     * Display the character's battle status
     */
    public String checkBattleSts(){
        String odMode = "";
        if (overDrive){
            odMode = "Overdrive Mode: On!!";
        }
        else {
            odMode = "Overdrive Mode: Off";
        }
        String status = ("Name: " + player.getName() + " \nCurrent Health: " +
        playerHp + "/" + player.getHealth() + " \nCurrent MP: " + playerMp + "/"
        + player.getMP() + " \nStrength: " + playerStr + "\nMagic: "
        + playerMagic + "\nLevel: "+ playerLvl + "\nNext Level: " + nextLevel
        + "\n" + odMode);
        return status;
    }

    /**
     * Scan the monster to reveal the monsters detail
     * @return monster details.
     */
    public String scanMonster(){
        String monsScan = monster.getDetails();
        monsScan = monsScan +"\nCurrent Health: " + monsHealth +
        "/" + monster.getHealth();
        return monsScan;
    }

    /**
     * The player attack
     * @return the player attack information.
     */
    public String playerAttack(){
        decMonsHealth(playerStr);
        String playerAttack = player.getName() + " dealt " + playerStr + " "
        + "damage to " + monster.getName() +"\n";
        if (monsHealth > 0){
          playerAttack += monsAttack();
        }
        else{
        }
        return playerAttack;
    }

    /**
     * See if the boss recovered HP or not
     * @return true if boss recovers otherwise false
     */
    public boolean checkHealth(){
        boolean charDeath = false;
        if(playerHp <= 0){
            charDeath = true;
        }
        return charDeath;
    }

    /**
     * Get the monster limit to see if the monster unleashes their special attack
     * @return monster limit
     */
    public int getMonsLimit(){
        int monsLimit = monster.getHealth() / 10;
        return monsLimit;
    }

    /**
     * The monster attack
     * @return monster attack information
     */
    public String monsAttack(){
        // Determine Monsters Overdrive(Special Attack)
        String monsAttack = "";
        if (getMonsHealth() <= getMonsLimit()){
          int monsOverdrive = getMonsDmg() * 3;
          if (getMonsName().equalsIgnoreCase("Valefor")){
              monsAttack = "Valefor executes a Sonic Ray dealing " +
              monsOverdrive + " damage to " + player.getName();
          }
          else if (getMonsName().equalsIgnoreCase("Ifrit")){
              monsAttack = ("Ifrit erupts a volcano and strikes with HellFire "
              + "dealing " + monsOverdrive + " damage to " + player.getName());
          }
          else if (getMonsName().equalsIgnoreCase("Shiva")){
              monsAttack = ("Shiva freezes the area and uses Diamond Dust "
              + "dealing " + monsOverdrive + " damage to " + player.getName());
          }
          else {
              monsAttack = ("Bahumut takes flight and gathers energy from "
              + "the stars and \nunleashes Mega Flare dealing  " + monsOverdrive
              + " damage to " + player.getName());
          }
          playerHp -= monsOverdrive;
        }
        else{
            // Boss regenerates HP
            if (monster.getName().equalsIgnoreCase("Bahamut") && monsHealth < 5000){
                bossRecover = false;
                Random bossRegen = new Random();
                int regen = bossRegen.nextInt(2);
                if (regen == 0){
                    monsAttack += monsAttack + "Bahamut regenerates...\n";
                    monsHealth += 100;
                    bossRecover = true;
                    if(monsHealth > 5000){
                        monsHealth = 5000;
                    }
                }
            }
            playerHp -= getMonsDmg(); // Monsters Attack
            monsAttack += monster.getName()+ " retaliates with a " +
            monster.getDamage() + " attack to " + player.getName();
        }
        checkOverDrive(); // Check if player can activate overdrive mode
        return monsAttack;
    }
    
    /*
     * Creates a new Magic instance with the current player, player's Mp and
     *  the player overdrive status
     */
    public void createMag(){
       magic = new Magic(player,playerMp,overDrive);
    }

    /**
     * The MP cost of an elemental magic
     * @return the MP cost
     */
    public int getElemental(){
        int element;
        createMag();
        magic.setElemental();
        element = magic.getMpCost();
        return element;
    }

    /**
     * The MP cost of the holy magic
     * @return the MP cost
     */
    public int getHoly(){
        int holy;
        createMag();
        magic.setHoly();
        holy = magic.getMpCost();
        return holy;
    }

    /**
     * The MP cost of the Ultima magic
     * @return the MP cost
     */
    public int getUlti(){
        int ulti;
        createMag();
        magic.setUltima();
        ulti = magic.getMpCost();
        return ulti;
    }

    /**
     * The character's magic attack
     * @param the magic that was selected
     * @return the magic attack information
     */
    public String useMagic(String magicName){
        createMag();
        String magicAttack = magic.useMagic(magicName);
        if (magic.getConfirmDmg()){
            monsHealth -= magic.getMagicDmg();
            playerMp = magic.getPlayerMP();
            magicAttack += "\ndealing " + magic.getMagicDmg() + " damage to "
            + monster.getName() + "\n";
            if (monsHealth > 0 ){
                magicAttack += monsAttack();
            }
        }
        return magicAttack;
    }

    /**
     * The character chooses to use an item
     * @param the selected item
     * @return the item use result
     */
    public String useItem(String itemName){
       boolean useItem =  player.useItem(itemName);
       String itmUse = "";
       if (useItem){
           if (itemName.equalsIgnoreCase("Potion")){
             itmUse = usePotion();
           }
           else if (itemName.equalsIgnoreCase("MagicStone")){
              itmUse = useStone();
           }
           else {
               itmUse = useUltimaStone();
           }
           if (monsHealth > 0){
           itmUse += monsAttack();
           }
       }   
       return itmUse;
    }

    /**
     * The character uses a Potion
     * @return the Potion effect
     */
    public String usePotion(){
        String potionEff = "";
        playerHp += 200;
        if (playerHp > player.getHealth()){
            playerHp = player.getHealth();
        }   
        potionEff = player.getName() + " recovers 200 HP\n";
        return potionEff;
    }

    /**
     * The character uses a Magic Stone
     * @return the Magic Stone effect
     */
    public String useStone(){
        String stoneEffect = "";
        int stoneDmg = playerMagic * 2;
        String stoneName;
        Random nameStone = new Random();
        int stoneNum = nameStone.nextInt(2);
        if (stoneNum == 0){
            stoneName = "Fire";
        }
        else if (stoneNum == 1){
            stoneName = "Blizzard";
        }
        else {
            stoneName = "Thunder";
        }
        monsHealth -= stoneDmg;
        stoneEffect = "The MagicStone unleashed the power of " + stoneName +
                " dealing " + stoneDmg + " damage to " + monster.getName() +"\n";
        return stoneEffect;
    }

    /**
     * The character uses a Ultima Stone
     * @return the Ultima Stone effect
     */
    public String useUltimaStone(){
        String stoneEffect;
        int stoneDmg = playerMagic * 10;
        monsHealth -= stoneDmg;
        stoneEffect = "The UltimaStone unleashed a hint of the power of Ultima "
                + "dealing " + stoneDmg + " damage to " + monster.getName() + "\n";
        return stoneEffect;
    }

     /**
     * Recovers the characters MP due to their player level
     * @return effects of recovering MP
     */
    public String recoverMagic(){
        String magRecov = "";
        int recovPoints = playerLvl * 5;
        playerMp += recovPoints;
        if (playerMp > player.getMP()){
            playerMp = player.getMP();
        }
        magRecov = player.getName() + " recovers " + recovPoints + " MP\n";
        magRecov += monsAttack();
        return magRecov;
    }

    /**
     * Character overdrive status
     * @return overdrive status
     */
    public boolean getOverDrive(){
        return overDrive;
    }

    /**
     * Character's overdrive limit
     * @return overdrive limit
     */
    public int getOdLimit(){
        int odLimit = player.getHealth() / 10;
        return odLimit;
    }

   /**
    * Check if character goes into overdrive mode when health is 10% or less
    */
    public void checkOverDrive(){
        if (playerHp <= getOdLimit() && overDrive == false ){
            overDrive = true;
            applyOdMode();
        }
    }

    /**
     * Apply the overdrive mode effects
     */
    public void applyOdMode(){

        if (player.getType().equalsIgnoreCase("Warrior")){
            playerStr *= 4;
        }
        else if (player.getType().equalsIgnoreCase("Marksman")){
            playerStr *= 2;
            playerMagic *=2;
        }
    }

    /**
     * Character escapes the battle
     * @return the escape battle information
     */
    public String escapeBattle(){
        String escape = player.getName() + " escapes from battle\n"
        + player.getName() + " did not gain any experience";
        return escape;
    }

    /**
     * Gets the character in the battle
     * @return The character
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * The end battle result when the character defeats a monster
     * @return the battle result
     */
    public String endBattle(){
        String battleResult = "You have defeated " + getMonsName();
        Item newItem = new Item();
        String itemName = newItem.getName();
        battleResult += "\nObtained a " + itemName + "\n";
        player.addItem(newItem);
        if (player.getPlayerLvl() < 3){
            player.decNextLvl(100);
        }
        else {
            player.decNextLvl(250);
        }
        battleResult+= player.incPlaylvl();
        return battleResult;
    }

}
