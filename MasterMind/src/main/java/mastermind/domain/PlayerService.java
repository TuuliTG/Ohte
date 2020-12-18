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
 * This class is a bridge between the user interface and the database. 
 * 
 * @author tgtuuli
 */
public class PlayerService {
    
    private final PlayerDao playerDao;
    private Player currentPlayer;
    private final GameDao gameDao;

    public PlayerService(PlayerDao playerDao, GameDao gameDao) {
        this.playerDao = playerDao;
        this.gameDao = gameDao;
    }
    /**
     * If player name exists in the database, logs the player in. 
     * @param name player name
     * @return true if log in successful, else false
     */
    public boolean login(String name) {
        Player player = playerDao.findByName(name);
        if (player == null) {
            return false;
        }
        
        currentPlayer = player;
        return true;
    }
    /**
     * Creates a new player if name doesn't already exist in the database. 
     * @param name player name
     * @return true if player is created, else false
     */
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
    /**
     * Adds a new game to the player. 
     * If the score or time is better that previous best scores/times
     * a new best score/time will be added to the player.
     * @param time 
     * @param guesses amount of guesses 
     */
    public void addGame(double time, int guesses) {
        Player player = getCurrentPlayer();
        Game game = new Game(player.getName(), time, guesses);
        game.setScore();
        try {
            gameDao.create(game);
        } catch (Exception e) {
        }
        if (player.getAmountOfGamesWon() == 0 || player.getBestTime() > time) {
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
    
    public List<Game> getBestGames() {
        return gameDao.findAllSortedByScore();
    }
    
    
  
    
    
            
}
