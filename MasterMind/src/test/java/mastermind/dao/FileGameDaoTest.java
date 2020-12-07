/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import mastermind.domain.Game;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author tgtuuli
 */
public class FileGameDaoTest {
    
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    File gameFile;
    GameDao gameDao;
    
    @Before
    public void setUp() throws Exception {
        gameFile = testFolder.newFile("testfile_games.txt");
        try (FileWriter file = new FileWriter(gameFile.getAbsolutePath())) {
            file.write("Test player;6500;25000.0;7;\n");
            file.write("Test player2;9000;20000.0;6;\n");
            file.write("Test player3;10000;15000.0;5;\n");
        } 
        
        gameDao = new FileGameDao(gameFile.getAbsolutePath());
    }
    
    @Test
    public void findAllSortedByScoreFindsAllGames() {
        List<Game> games = gameDao.findAllSortedByScore();
        assertEquals(3, games.size());
    }
    
    @Test
    public void findAllSortedByScoreSortsGames() {
        List<Game> games = gameDao.findAllSortedByScore();
        assertEquals(10000, games.get(0).getScore());
        assertEquals(9000, games.get(1).getScore());
        assertEquals(6500, games.get(2).getScore());
    }
    
    @Test
    public void createCreatesAGameAndSavesIt() {
        Game g = new Game("Testtest", 10000, 2000.0, 4);
        Game created = new Game("234", 567, 5678);
        try {
            created = gameDao.create(g);
        } catch (Exception e) {
            
        }
        
        assertEquals("Testtest", created.getPlayerName());
        
        List<Game> games = gameDao.findAllSortedByScore();
        assertEquals(4, games.size());
        
        
    }
    
}
