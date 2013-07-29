package nz.ac.aut.dns3948.finalfantasyrpggui.test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import nz.ac.aut.dns3948.finalfantasyrpggui.game.Player;
import nz.ac.aut.dns3948.finalfantasyrpggui.game.ItemInventory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dns3948
 */
public class PlayerTest {
    ItemInventory testInvent = new ItemInventory();
    Player testPlayer = new Player(testInvent);

    public PlayerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test generate warrior type
     */
    @Test
    public void testGenWar(){
        testPlayer.setType("Warrior");
        testPlayer.assignAttribute();
        assertEquals(200, testPlayer.getHealth());
        assertEquals(50, testPlayer.getDamage());
        assertEquals(10, testPlayer.getMP());
        assertEquals(5, testPlayer.getMagicStr());
    }

    /**
     * Test generate spellcaster type
     */
    @Test
    public void testGenSpell(){
        testPlayer.setType("spellcaster");
        testPlayer.assignAttribute();
        assertEquals(120, testPlayer.getHealth());
        assertEquals(25, testPlayer.getDamage());
        assertEquals(30, testPlayer.getMP());
        assertEquals(20, testPlayer.getMagicStr());
    }

    /**
     * Test generate marksman type
     */
    @Test
    public void testGenMarks(){
        testPlayer.setType("marksman");
        testPlayer.assignAttribute();
        assertEquals(100, testPlayer.getHealth());
        assertEquals(50, testPlayer.getDamage());
        assertEquals(15, testPlayer.getMP());
        assertEquals(20, testPlayer.getMagicStr());
    }

    /**
     * Test display the details of the character
     */
    @Test
    public void testDisplayStats(){
        testPlayer.setName("Test");
        testPlayer.setType("warrior");
        testPlayer.assignAttribute();
        assertEquals("Test,warrior,200,10,50,5,1,200", testPlayer.toString());
    }

    /**
     * Test reset the next level experience
     */
    @Test
    public void testResetLvl(){
        testPlayer.setPlayerLvl(2);
        testPlayer.resetlvl();
        assertEquals(400,testPlayer.getNextLvl());
    }

    /**
     * Test increase warrior level
     */
    @Test
    public void testIncreaseWarLvl(){
        testPlayer.setType("warrior");
        testPlayer.assignAttribute();
        testPlayer.setNextLvl(0);
        testPlayer.incPlaylvl();
        assertEquals(300, testPlayer.getHealth());
        assertEquals(80, testPlayer.getDamage());
        assertEquals(15, testPlayer.getMagicStr());
        assertEquals(20, testPlayer.getMP());
        assertEquals(2, testPlayer.getPlayerLvl());
        assertEquals(400, testPlayer.getNextLvl());
    }

    /**
     * Test increase spellcaster level
     */
    @Test
    public void testIncreaseSpellLvl(){
        testPlayer.setType("spellcaster");
        testPlayer.assignAttribute();
        testPlayer.setNextLvl(0);
        testPlayer.incPlaylvl();
        assertEquals(170, testPlayer.getHealth());
        assertEquals(35, testPlayer.getDamage());
        assertEquals(40, testPlayer.getMagicStr());
        assertEquals(50, testPlayer.getMP());
        assertEquals(2, testPlayer.getPlayerLvl());
        assertEquals(400, testPlayer.getNextLvl());
    }

    /**
     * Test increase marksman level
     */
    @Test
    public void testIncreaseMarkLvl(){
        testPlayer.setType("marksman");
        testPlayer.assignAttribute();
        testPlayer.setNextLvl(0);
        testPlayer.incPlaylvl();
        assertEquals(175, testPlayer.getHealth());
        assertEquals(75, testPlayer.getDamage());
        assertEquals(35, testPlayer.getMagicStr());
        assertEquals(30, testPlayer.getMP());
        assertEquals(2, testPlayer.getPlayerLvl());
        assertEquals(400, testPlayer.getNextLvl());
    }

}