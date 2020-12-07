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
package mastermind.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import mastermind.domain.Player;

/**
 * Takes care of saving and retrieving player related information to/from database.
 * @author tgtuuli
 */
public class FilePlayerDao implements PlayerDao {
    private List<Player> players;
    private String file;

    public FilePlayerDao(String file) throws Exception {
        players = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                Player p = new Player(parts[0], Double.parseDouble(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                players.add(p);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }
    
    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Player p : players) {
                writer.write(p.getName() + "; " + p.getBestTime() + ";" + p.getBestScore() + ";" + p.getAmountOfGamesWon() + ";" + "\n");
            }
        }
    }
    /**
     * Retrieves all players from the database.
     * @return List of all players
     */
    @Override
    public  List<Player> getAll() {
        return players;
    }
    /**
     * Retrieves a player from database by name 
     * @param name
     * @return player or if not found, null
     */
    @Override
    public Player findByName(String name) {
        return players.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst().orElse(null);
        
    }
    
    /**
     * Adds a player to the database.
     * @param player
     * @return
     * @throws Exception 
     */
    @Override
    public Player create(Player player) throws Exception {
        players.add(player);
        save();
        return player;
    }
    
    /**
     * Sets a new best score to the player p-
     * @param p player
     * @param score new best score
     * @throws Exception 
     */
    @Override
    public void setBestScore(Player p, int score) throws Exception {
        for (Player player : players) {
            if (player.equals(p)) {
                player.setBestScore(score);
            }
        }
        save();
    }
    
    /**
     * Sets a new best time to the player.
     * @param p player
     * @param time new best time
     * @throws Exception 
     */
    @Override
    public void setBestTime(Player p, double time) throws Exception {
        for (Player player : players) {
            if (player.equals(p)) {
                player.setBestTime(time);
            }
        }
        save();
    }
    
    /**
     * Increases the amount of games won for the player by one. 
     * @param p player
     * @throws Exception 
     */
    @Override
    public void addOneGamePlayed(Player p) throws Exception {
        for (Player player : players) {
            if (player.equals(p)) {
                player.addGamesWon();
            }
        }
        save();
    }
    
}
