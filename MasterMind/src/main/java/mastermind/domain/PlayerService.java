/*
 * The MIT License
 *
 * Copyright 2020 tgtuuli.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package mastermind.domain;

import java.util.List;
import mastermind.dao.GameDao;
import mastermind.dao.PlayerDao;

/**
 *
 * @author tgtuuli
 */
public class PlayerService {
    
    private PlayerDao playerDao;
    private Player currentPlayer;
    private GameDao gameDao;

    public PlayerService(PlayerDao playerDao, GameDao gameDao) {
        this.playerDao = playerDao;
        this.gameDao = gameDao;
    }
    
    public boolean login(String name) {
        Player player = playerDao.findByName(name);
        if (player == null) {
            return false;
        }
        
        currentPlayer = player;
        return true;
    }
    
    public boolean createPlayer(String name) {
        if (playerDao.findByName(name) != null) {
            return false;
        } 
        
        Player p = new Player(name);
        try {
            Player createdP = playerDao.create(p);
            System.out.println("created player " + createdP.getName());
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public void setCurrentPlayer(Player p) {
        this.currentPlayer = p;
    }

    public Player getCurrentPlayer() {
        
        return currentPlayer;
    }
    
    public void addGame(double time, int guesses) {
        Player player = getCurrentPlayer();
        Game game = new Game(player.getName(), time, guesses);
        game.setScore();
        try {
            gameDao.create(game);
        } catch (Exception e) {
        }
        if(player.getAmountOfGamesWon() == 0 || player.getBestTime() > time) {
            try {
                playerDao.setBestTime(player, time);
            } catch (Exception e) {
            }
        }
        if (player.getAmountOfGamesWon() == 0 || player.getBestScore() < game.getScore()) {
            try {
                playerDao.setBestScore(player, game.getScore());
            } catch (Exception e) {
            }
        }
        try {
            playerDao.addOneGamePlayed(player);
        } catch (Exception e) {
        }
    }
    
    public List<Game> getBestGames () {
        System.out.println("getting games from database (playerService)");
        return gameDao.findAllSortedByScore();
    }
    
    
  
    
    
            
}
