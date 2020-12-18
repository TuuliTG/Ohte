/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author tgtuuli
 */
public class PlayerServiceTest {
    
    FakePlayerDao playerDao;
    FakeGameDao gameDao;
    PlayerService playerService;
    
    @Before
    public void setUp() {
        playerDao = new FakePlayerDao();
        gameDao = new FakeGameDao();
        playerService = new PlayerService(playerDao, gameDao);
    }
    
    @Test
    public void ifPlayerNameFoundLoginSuccessfull() {
        boolean result = playerService.login("Testi");
        assertTrue(result);
        assertEquals("Testi", playerService.getCurrentPlayer().getName());
    }
    
    @Test
    public void ifPlayerDoesntExistLoginFail() {
        boolean result = playerService.login("rtyui");
        assertFalse(result);
    }
    
    @Test
    public void newPlayerGetsCreatedWhenPlayerNotFound() {
        boolean result = playerService.createPlayer("New Player");
        assertTrue(result);
        assertEquals(2, playerDao.getAll().size());
    }
    
    @Test
    public void existingPlayerDoesntGetCreated() {
        boolean result = playerService.createPlayer("Testi");
        assertFalse(result);
        assertEquals(1, playerDao.getAll().size());
    }
    
    @Test
    public void addGameToLoggedInPlayer() {
        playerService.createPlayer("Gamer");
        playerService.login("Gamer");
        playerService.addGame(25000.0, 7);
        
        assertEquals(25000.0, playerService.getCurrentPlayer().getBestTime(), 5);
    }
    
    @Test
    public void getBestGamesIsInRightOrder() {
        Game g1 = new Game("a1", 100, 150.0, 4);
        Game g2 = new Game("a2", 200, 400.0, 8);
        Game g3 = new Game("a3", 10000, 3000.0, 2);
        try {
            gameDao.create(g2);
            gameDao.create(g3);
            gameDao.create(g1);
        } catch (Exception e) {
            
        }
        List<Game> games = playerService.getBestGames();
        assertEquals(10000, games.get(0).getScore());
        
    }
    
    @Test
    public void setCurrentPlayerSetsPlayer() {
        
        playerService.setCurrentPlayer(new Player("new player"));
        assertEquals("new player", playerService.getCurrentPlayer().getName());
    }
    
}
