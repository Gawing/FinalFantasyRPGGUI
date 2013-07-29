package nz.ac.aut.dns3948.finalfantasyrpggui.test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import nz.ac.aut.dns3948.finalfantasyrpggui.game.Battle;
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
public class BattleTest {
    private ItemInventory invent = new ItemInventory();
    private Player player = new Player(invent);
    private Battle battleTest;

    public BattleTest() {
        player.setType("Warrior");
        player.assignAttribute();
        battleTest = new Battle(player);
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
     * Test generate monster Valefor
     */
    @Test
    public void testGenValefor(){
        battleTest.genMons();
        while (!battleTest.getMonsName().equalsIgnoreCase("Valefor")){
            battleTest.genMons();
        }
        assertEquals("Valefor", battleTest.getMonsName());
        assertEquals(250, battleTest.getMonsHealth());
        assertEquals(10,battleTest.getMonsDmg());
    }

    /**
     * Test generate monster Shiva
     */
    @Test
    public void testGenShiva(){
        battleTest.genMons();
        while (!battleTest.getMonsName().equalsIgnoreCase("Shiva")){
            battleTest.genMons();
        }
        assertEquals("Shiva", battleTest.getMonsName());
        assertEquals(150, battleTest.getMonsHealth());
        assertEquals(30,battleTest.getMonsDmg());

    }

    /**
     * Test generate monster Ifrit
     */
    @Test
    public void testGenIfrit(){
        battleTest.genMons();
        while (!battleTest.getMonsName().equalsIgnoreCase("Ifrit")){
            battleTest.genMons();
        }
        assertEquals("Ifrit", battleTest.getMonsName());
        assertEquals(200, battleTest.getMonsHealth());
        assertEquals(25,battleTest.getMonsDmg());
    }

    /**
     * Test generate monster boss
     */
    @Test
    public void testBossGen(){
        battleTest.genBoss();
        assertEquals("Bahamut", battleTest.getMonsName());
        assertEquals(5000,battleTest.getMonsHealth());
        assertEquals(100,battleTest.getMonsDmg());
    }

    /*
     * Test result of a player attack
     */
    @Test
    public void testPlayerAttack(){
        battleTest.genMons();
        battleTest.playerAttack();
        if (battleTest.getMonsName().equalsIgnoreCase("Valefor")){
            assertEquals(200, battleTest.getMonsHealth());
        }
        else if (battleTest.getMonsName().equalsIgnoreCase("Ifrit")){
            assertEquals(150, battleTest.getMonsHealth());
        }
        else {
            assertEquals(100, battleTest.getMonsHealth());
        }
    }

    /**
     * Test attacking the boss
     */
    @Test
    public void testAtkBoss(){
        battleTest.genBoss();
        battleTest.playerAttack();
        if (battleTest.regenBoss()){
            assertEquals(5000,battleTest.getMonsHealth());
        }
        else {
            assertEquals(4950,battleTest.getMonsHealth());
        }
    }

    /**
     * Test the result of monster attack
     */
    @Test
    public void testMonsterAtk(){
        battleTest.genMons();
        battleTest.monsAttack();
        if (battleTest.getMonsName().equalsIgnoreCase("Valefor")){
            assertEquals(190, battleTest.getPlayerHP());
        }
        else if (battleTest.getMonsName().equalsIgnoreCase("Ifrit")){
            assertEquals(175, battleTest.getPlayerHP());
        }
        else {
            assertEquals(170, battleTest.getPlayerHP());
        }
    }

    /**
     * Test the result of a boss attack
     */
    @Test
    public void testBossAtk(){
        battleTest.genBoss();
        battleTest.monsAttack();
        assertEquals(100, battleTest.getPlayerHP());
    }

    /**
     * Test boss recovery
     */
    @Test
    public void testBossRecover(){
        battleTest.genBoss();
        battleTest.playerAttack();
        
        if (!battleTest.regenBoss()){
            testBossRecover();
        }
        else {
            assertEquals(5000,battleTest.getMonsHealth());
        }
    }

    /**
     * Test the result of using elemental magic
     */
    @Test
    public void testElementalMagic(){
        battleTest.genMons();
        battleTest.useMagic("Fire");
        if (battleTest.getMonsName().equalsIgnoreCase("Valefor")){
            assertEquals(240, battleTest.getMonsHealth());
        }
        else if (battleTest.getMonsName().equalsIgnoreCase("Ifrit")){
            assertEquals(190, battleTest.getMonsHealth());
        }
        else {
            assertEquals(140, battleTest.getMonsHealth());
        }
    }

    /**
     * Test character has no MP
     */
    @Test
    public void testNoMP(){
        battleTest.genMons();
        battleTest.useMagic("holy");
        if (battleTest.getMonsName().equalsIgnoreCase("Valefor")){
            assertEquals(250, battleTest.getMonsHealth());
        }
        else if (battleTest.getMonsName().equalsIgnoreCase("Ifrit")){
            assertEquals(200, battleTest.getMonsHealth());
        }
        else {
            assertEquals(150, battleTest.getMonsHealth());
        }
    }

    /**
     * Test the result of a holy magic attack
     */
    @Test
    public void testHolyMagic(){
        battleTest.genMons();
        battleTest.setPlayerMP(100);
        battleTest.useMagic("holy");
        if (battleTest.getMonsName().equalsIgnoreCase("Valefor")){
            assertEquals(225, battleTest.getMonsHealth());
        }
        else if (battleTest.getMonsName().equalsIgnoreCase("Ifrit")){
            assertEquals(175, battleTest.getMonsHealth());
        }
        else {
            assertEquals(125, battleTest.getMonsHealth());
        }
    }

    /**
     * Test the result of a ultima attack
     */
    @Test
    public void testUltima(){
        battleTest.genMons();
        battleTest.setPlayerMP(100);
        battleTest.useMagic("ultima");
        if (battleTest.getMonsName().equalsIgnoreCase("Valefor")){
            assertEquals(150, battleTest.getMonsHealth());
        }
        else if (battleTest.getMonsName().equalsIgnoreCase("Ifrit")){
            assertEquals(100, battleTest.getMonsHealth());
        }
        else {
            assertEquals(50, battleTest.getMonsHealth());
        }

    }

    /**
     * Test the result of using a potion
     */
    @Test
    public void testUsePotion(){
        battleTest.genMons();
        battleTest.monsAttack();
        battleTest.usePotion();
        assertEquals(200, battleTest.getPlayerHP());

    }

    /**
     * Test the result of using a magic stone
     */
    @Test
    public void testUseStone(){
        battleTest.genMons();
        battleTest.useStone();
        if (battleTest.getMonsName().equalsIgnoreCase("Valefor")){
            assertEquals(240, battleTest.getMonsHealth());
        }
        else if (battleTest.getMonsName().equalsIgnoreCase("Ifrit")){
            assertEquals(190, battleTest.getMonsHealth());
        }
        else {
            assertEquals(140, battleTest.getMonsHealth());
        }

    }

    /**
     * Test the result using a ultima stone
     */
    @Test
    public void testUltiStone(){
        battleTest.genMons();
        battleTest.useUltimaStone();
        if (battleTest.getMonsName().equalsIgnoreCase("Valefor")){
            assertEquals(200, battleTest.getMonsHealth());
        }
        else if (battleTest.getMonsName().equalsIgnoreCase("Ifrit")){
            assertEquals(150, battleTest.getMonsHealth());
        }
        else {
            assertEquals(100, battleTest.getMonsHealth());
        }

    }

    /**
     * Test warrior overdrive
     */
    @Test
    public void testWarOD(){
        battleTest.setPlayerHP(player.getHealth() /10);
        battleTest.checkOverDrive();
        assertTrue(battleTest.getOverDrive());
        assertEquals(200, battleTest.getPlayerStr());
    }

    /**
     * Test the player recovering MP
     */
    @Test
    public void testRecoverMP(){
        battleTest.setPlayerMP(5);
        battleTest.genMons();
        battleTest.recoverMagic();
        assertEquals(10, battleTest.getPlayerMP());
    }

    /**
     * Test the marksman overdrive
     */
    @Test
    public void testMarksOD(){
        ItemInventory item = new ItemInventory();
        Player hybridTest = new Player(item);
        hybridTest.setType("marksman");
        hybridTest.assignAttribute();
        battleTest = new Battle(hybridTest);
        battleTest.setPlayerHP(hybridTest.getHealth() /10);
        battleTest.checkOverDrive();
        assertTrue(battleTest.getOverDrive());
        assertEquals(100, battleTest.getPlayerStr());
        assertEquals(40, battleTest.getPlayerMagic());
    }

    /**
     * Test the result of a monster special attack
     */
    @Test
    public void testMonsSpecAtk(){
        battleTest.genMons();
        if (battleTest.getMonsName().equalsIgnoreCase("Valefor")){
            battleTest.decMonsHealth(240);
            battleTest.monsAttack();
            assertEquals(170, battleTest.getPlayerHP());
        }
        else if (battleTest.getMonsName().equalsIgnoreCase("Ifrit")){
            battleTest.decMonsHealth(190);
            battleTest.monsAttack();
            assertEquals(125, battleTest.getPlayerHP());
        }
        else {
            battleTest.decMonsHealth(140);
            battleTest.monsAttack();
            assertEquals(110, battleTest.getPlayerHP());
        }
    }

    /**
     * Test the result of a boss special attack
     */
    @Test
    public void testBossSpecAtk(){
        battleTest.setPlayerHP(400);
        battleTest.genBoss();
        battleTest.decMonsHealth(4900);
        battleTest.monsAttack();
        assertEquals(100, battleTest.getPlayerHP());
    }

    /**
     * Test if the player is death
     */
    @Test
    public void checkDeath(){
        battleTest.setPlayerHP(0);
        assertTrue(battleTest.checkHealth());
    }

}