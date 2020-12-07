/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import mastermind.domain.Player;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author tgtuuli
 */
public class FilePlayerDaoTest {
    
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    File playerFile;
    PlayerDao playerDao;
    
    @Before
    public void setUp() throws Exception {
        playerFile = testFolder.newFile("testfile_users.txt");
        try (FileWriter file = new FileWriter(playerFile.getAbsolutePath())) {
            file.write("Test player;25000.0;6500;3");
        }
        
        playerDao = new FilePlayerDao(playerFile.getAbsolutePath());
    }
    
    @Test
    public void playersAreReadCorrectlyFromFile() {
        List<Player> players = playerDao.getAll();
        assertEquals(1, players.size());
        Player p = players.get(0);
        assertEquals("Test player", p.getName());
        assertEquals(25000.0, p.getBestTime(), 5);
        assertEquals(6500, p.getBestScore());
        assertEquals(3, p.getAmountOfGamesWon());
    }
    
    @Test
    public void playerIsFoundFromDatabase() {
        Player p = playerDao.findByName("Test player");
        assertEquals("Test player", p.getName());
    }
    
    @Test
    public void nonExistingPlayerIsNull() {
        Player p = playerDao.findByName("123");
        assertEquals(null, p);
    }
    
    @Test
    public void creatingANewPlayerReturnsPlayer() {
        Player p = new Player("Test2");
        Player created = new Player("testtest");
        try {
            created = playerDao.create(p);
        } catch (Exception e) {
            
        }
        
        assertEquals(p.getName(), created.getName());
        
    }
    
    @Test
    public void setBestScoreSetsScore() {
        Player p = playerDao.findByName("Test player");
        try {
            playerDao.setBestScore(p, 8000);
        } catch (Exception e) {
            
        }
        
        p = playerDao.findByName("Test player");
        assertEquals(8000, p.getBestScore());
        
    }
    
    @Test
    public void setBestTimeSetsTime() {
        Player p = playerDao.findByName("Test player");
        try {
            playerDao.setBestTime(p, 20000.0);
        } catch (Exception e) {
            
        }
        
        p = playerDao.findByName("Test player");
        assertEquals(20000.0, p.getBestTime(), 5);
    }
    
    @Test
    public void addOneGamePlayedAddsOneGame() {
        Player p = playerDao.findByName("Test player");
        try {
            playerDao.addOneGamePlayed(p);
        } catch (Exception e) {
            
        }
        
        p = playerDao.findByName("Test player");
        assertEquals(4, p.getAmountOfGamesWon());
    }
}
