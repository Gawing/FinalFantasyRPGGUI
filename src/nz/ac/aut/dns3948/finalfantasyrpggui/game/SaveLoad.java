/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nz.ac.aut.dns3948.finalfantasyrpggui.game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import nz.ac.aut.dns3948.finalfantasyrpggui.ui.Dialog;

/**
 * A class that lets the player save and load their game from a text file
 * @author dns3948 GavinC
 */
public class SaveLoad {
    File charDir = new File("Saved Games");
    private boolean confirmSav;
    private static boolean confirmLoad;
    private static Player player;

    /**
     * Constructor for class SaveLoad
     */
    public SaveLoad(){  
        if (!charDir.exists()){ // Create saved games directory
            charDir.mkdirs();
        }
        confirmSav = false;
    }

    /**
     * Confirms that the user has saved their player to a text file
     * @return true if the save is successful otherwise false
     */
    public boolean confirmSave(){
        return confirmSav;
    }

    /**
     * Confirms that the user has load their player to a text file
     * @return true if the load is successful otherwise false
     */
    public boolean confirmLoad(){
        return confirmLoad;
    }

    /**
     * Saves the game player details
     * @param newP the saved player
     */
    public static void saveGame(Player newP){
        try {
        player = newP;
        File charFile = new File("Saved Games/" + player.getName() + ".txt");
        BufferedWriter writer = new BufferedWriter( new FileWriter(charFile));
        String character = player.toString();
        writer.write(character);
        writer.flush();
        writer.close();       
        }
        catch (IOException ex){
            //System.err.println("IO Exception" + ex);
        }

    }

    /**
     * List the save game directory contents
     * @return the save game file names
     * @throws IOException
     */
    public String listDir() throws IOException{
        String[] listGames = charDir.list();
        String fileName = "";
        if (listGames == null || listGames.length == 0) {
            fileName = "There are no saved games";
        }
        else{
            fileName = "*******Saved Games*******\n";
            for (int i = 0; i < listGames.length; i++) {
            fileName = fileName + listGames[i] + "\n";
            }
            confirmSav = true;
        }
        return fileName;
    }

    /**
     * Load the saved game details
     * @param gameName the intended file to be loaded
     * @return true if the load is successful other false
     */
    public boolean loadGame(String gameName){
        try{
            FileReader fr = new FileReader("Saved Games/" + gameName + ".txt");
            Scanner scan = new Scanner(fr);
            scan.useDelimiter(",");
            ItemInventory item = new ItemInventory();
            Player newPlayer = new Player(item);
            while(scan.hasNext()){ // sets the player according to saved details
                newPlayer.setName(scan.next());
                newPlayer.setType(scan.next());
                int health = Integer.parseInt(scan.next());
                newPlayer.setHealth(health);
                int magicPoints = Integer.parseInt(scan.next());
                newPlayer.setMP(magicPoints);
                int str = Integer.parseInt(scan.next());
                newPlayer.setDmg(str);
                int magic = Integer.parseInt(scan.next());
                newPlayer.setMagicStr(magic);
                int level = Integer.parseInt(scan.next());
                newPlayer.setPlayerLvl(level);
                int nxtLvl = Integer.parseInt(scan.next());
                newPlayer.setNextLvl(nxtLvl);
                //break;
                player = newPlayer;
            }
            confirmLoad = true;
        }
        catch(FileNotFoundException ex){
            // Error Dialog indicating that the file doesnt exist
            Dialog errorDialog = new Dialog("File does not exist");
            errorDialog.showErrorMsg();
            confirmLoad = false;
        }
        return confirmLoad;
    }

    /**
     * Gets the player that have been loaded
     * @return the loaded player
     */
    public Player getPlayer(){
        return player;
    }

}
