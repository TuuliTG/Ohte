/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.domain;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tgtuuli
 */
public class PlayerTest {
    
    @Test
    public void contstructorTest1() {
        Player p = new Player("AA");
        assertEquals("AA", p.getName());
        assertEquals(0, p.getAmountOfGamesWon());
        assertTrue(p.getGamesPlayed().isEmpty());
    }
    
    @Test
    public void contstructorTest2() {
        Player p = new Player("AA", 100.0, 150, 5);
        assertEquals("AA", p.getName());
        assertEquals(100.0, p.getBestTime(), 10);
        assertEquals(150, p.getBestScore());
        assertEquals(5, p.getAmountOfGamesWon());
    }
    
}
