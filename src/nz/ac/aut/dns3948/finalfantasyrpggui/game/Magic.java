/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nz.ac.aut.dns3948.finalfantasyrpggui.game;

import nz.ac.aut.dns3948.finalfantasyrpggui.ui.Dialog;

/**
 * Class Magic
 * A class that creates the magic and magic interactions in the game
 * @author dns3948 GavinC
 */
public class Magic {
    private Player playChar;
    private int playMP;
    private int magicDmg;
    private int mpCost;
    private boolean confirmMagic;
    private boolean odMode;
    private boolean confirmDmg;
    private Dialog gameDialog;

    /**
     * Constructor for class Magic
     * @param newP the player in the battle,
     * @param playerMP player's battle MP,
     * @param  overDrive  players overdrive status
     */
    public Magic(Player newP, int playerMP, boolean overDrive){
        playChar = newP;
        playMP = playerMP;
        magicDmg = 0;
        confirmMagic = false;
        odMode = overDrive;
        confirmDmg = false; 
    }


    /**
     * Confirms if the player has enough MP to use the selected magic
     */
    public void confirmMagic(){
        String mpAlert = "";
        if (playMP >= mpCost){ //Player has enough MP to use the magic
            confirmMagic = true;
            playMP -= mpCost;
        }
        else { //Player does not have enough MP to use the magic
            confirmMagic = false;
            mpAlert = playChar.getName() + " does not enough MP";
            gameDialog = new Dialog(mpAlert);
            gameDialog.showErrorMsg();
        }   
    }

    /**
     * See if the player can use the selected magic
     * @return true if the player can otherwise false
     */
    public boolean getConfirmMag(){
        return confirmMagic;
    }

    /**
     * Confirms if the magic damage is created
     * @return true if the damage is created otherwise false
     */
    public boolean getConfirmDmg(){
        return confirmDmg;
    }

    /**
     * Gets the current players MP in the magic class
     * @return players MP
     */
    public int getPlayerMP(){
        return playMP;
    }

    /**
     * Gets the MP cost of a magic
     * @return MP cost
     */
    public int getMpCost(){
        return mpCost;
    }
    
    /**
     * Gets the magic damage
     * @return the magic damage
     */
    public int getMagicDmg(){
        return magicDmg;
    }

    /**
     * Check if the player is a SpellCaster and is in overdrive mode
     * @return true if the player is otherwise false
     */
    public boolean spellOverdrive(){
        boolean overDrive;
        if (odMode == true && playChar.getType().equalsIgnoreCase("Spellcaster")){
        overDrive = true;
        }
        else {
            overDrive = false;
        }
        return overDrive;
    }

    /**
     * Uses the selected magic
     * @param the magic selected
     * @return the information on the magic used 
     */
    public String useMagic(String magicName){
        String magicAttack = "";
        if (magicName.equalsIgnoreCase("Fire") 
            || magicName.equalsIgnoreCase("Thunder")
            || magicName.equalsIgnoreCase("Blizzard")){
            magicAttack = useElemental(magicName);
        }
        else if (magicName.equalsIgnoreCase("Holy")){
            magicAttack = useHoly();
        }
        else {
            magicAttack = useUltima();
        }
        return magicAttack;
    }

    /**
     * Sets the MP cost for elemental magic
     */
    public void setElemental(){
        if (spellOverdrive()){
            mpCost = 1;
        }
        else {
            mpCost = 5;
        }
    }

    /**
     * Sets the MP cost for the holy magic
     */
    public void setHoly(){
        if (spellOverdrive()){
            mpCost = 5;
        }
        else {
            mpCost = 20;
        }
    }

    /**
     * Sets the MP cost for the ultima magic
     */
    public void setUltima(){
        if (spellOverdrive()){
            mpCost = 20;
        }
        else {
            mpCost = 100;
        }
    }

    /**
     * Uses an elemental magic
     * @param the elemental magic selected
     * @return the information on the elemental magic used
     */
    public String useElemental(String magicName){
        setElemental(); // Sets the elemental magic MP cost
        confirmMagic(); // Confirms that the magic can be used
        String magicAtk = "";
        if(getConfirmMag()){
            magicDmg = playChar.getMagicStr() * 2;
            if(magicName.equalsIgnoreCase("Fire")){
                magicAtk = playChar.getName()
                + " scorches the monster with Fire";
            }
            else if(magicName.equalsIgnoreCase("Blizzard")){
                 magicAtk = playChar.getName()
                 + " freezes the monster with Blizzard";
            }
            else{
                 magicAtk = playChar.getName()
                 + " strikes the monster with Thunder";
            }
            confirmDmg = true;
        }
        else{
            confirmDmg = false;
        }
        return magicAtk;
    }
   
    /**
     * Uses the holy magic
     * @return the information on the holy magic attack
     */
    public String useHoly(){
        setHoly(); // Sets the holy magic MP cost
        confirmMagic(); // Confirms that the magic can be used
        String holyAtk = "";
        if (getConfirmMag()){
            holyAtk = playChar.getName() + " summons a white powerful light "
            + "called Holy";
            magicDmg = playChar.getMagicStr() * 5;
            confirmDmg = true;
        }
        else{
            confirmDmg = false;
        }
        return holyAtk;
    }

    /**
     * Uses the ultima magic
     * @return the information on the ultima magic attack
     */
    public String useUltima(){
        setUltima(); // Sets the holy magic MP cost
        confirmMagic(); // Confirms that the magic can be used
        String ultiAtk = "";
        if(getConfirmMag()){
            ultiAtk = playChar.getName() + " unleashes Ultima, "
            + "the ulimate magic which destroys anything in its path\n ";
            magicDmg = playChar.getMagicStr() * 20;
            confirmDmg = true;
        }
        else{
            confirmDmg = false;
        }
        return ultiAtk;
    }
}
