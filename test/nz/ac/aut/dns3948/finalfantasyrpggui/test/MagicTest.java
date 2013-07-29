package nz.ac.aut.dns3948.finalfantasyrpggui.test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import nz.ac.aut.dns3948.finalfantasyrpggui.game.Magic;
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
 * @author  dns3948 GavinC
 */
public class MagicTest {
    ItemInventory inventTest = new ItemInventory();
    Player charTest = new Player(inventTest);
    Magic magicTest;

    public MagicTest() {
        charTest.setType("Spellcaster");
        charTest.assignAttribute();
        magicTest = new Magic(charTest, charTest.getMP(), false);
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
     * Test that the magic has been used
     */
    @Test
    public void testConfirmMagicUse(){
        magicTest.useMagic("Blizzard");
        assertTrue(magicTest.getConfirmDmg());
        assertTrue(magicTest.getConfirmMag());
    }

    /**
     * Test that the magic has been denied
     */
    @Test
    public void testDenyMagicUse(){
        magicTest.useMagic("Ultima");
        assertFalse(magicTest.getConfirmDmg());
        assertFalse(magicTest.getConfirmMag());
    }

    /**
     * Test the result of using elemental magic
     */
    @Test
    public void testUseElemental(){
        magicTest.useMagic("Thunder");
        assertEquals(25, magicTest.getPlayerMP());
    }

    /**
     * Test the result of using holy magic
     */
    @Test
    public void testUseHoly(){
        magicTest.useHoly();
        assertEquals(10, magicTest.getPlayerMP());
    }

    /**
     * Test the result of using ultima magic
     */
    @Test
    public void testUseUltima(){
        magicTest = new Magic(charTest, 120, false);
        magicTest.useUltima();
        assertEquals(20, magicTest.getPlayerMP());
    }

    /**
     * Test the effects of a SpellCaster in overdrive
     */
    @Test
    public void testSpellcasterOD(){

        magicTest = new Magic(charTest, charTest.getMP(), true);
        assertTrue(magicTest.spellOverdrive());
        magicTest.useElemental("fire");
        assertEquals(1,magicTest.getMpCost());
        magicTest.useHoly();
        assertEquals(5,magicTest.getMpCost());
        magicTest.useUltima();
        assertEquals(20,magicTest.getMpCost());
    }

}