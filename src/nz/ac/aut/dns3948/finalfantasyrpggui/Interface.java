package nz.ac.aut.dns3948.finalfantasyrpggui;

import nz.ac.aut.dns3948.finalfantasyrpggui.view.StartMenu;

/**
 * Class Interface
 * A class to start the RPG game
 * @author dns3948 GavinC
 */
public class Interface {

    /**
     * Starts the game
     * @param args
     */
    public static void main(String[] args) {
        StartMenu newGame = new StartMenu();
        newGame.pack();
        newGame.setVisible(true);
    }
}



