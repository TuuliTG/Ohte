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
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import mastermind.domain.Game;
import mastermind.domain.Player;

/**
 * Takes care of saving and retrieving game related information to/from database.
 * @author tgtuuli
 */
public class FileGameDao implements GameDao {
    
    private List<Game> gamesPlayed;
    private String file;

    public FileGameDao(String file) throws Exception {
        gamesPlayed = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");                
                Game game = new Game(parts[0], Integer.parseInt(parts[1]), Double.parseDouble(parts[2]), Integer.parseInt(parts[3]));
                gamesPlayed.add(game);
            }
        } catch (Exception e) {
            System.out.println("exception in reading game.txt file");
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }
    /**
     * Adds a game to the database
     * @param game
     * @return game
     * @throws Exception 
     */
    @Override
    public Game create(Game game) throws Exception {
        gamesPlayed.add(game);
        save();
        return game;
    }
    /**
     * Finds all games from the database and organizes them in descending order by score.
     * @return List of games
     */
    @Override
    public List<Game> findAllSortedByScore() {
        Collections.sort(gamesPlayed);
        Collections.reverse(gamesPlayed);
        return gamesPlayed;
                
    }
    
    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Game g : gamesPlayed) {
                writer.write(g.getPlayerName() + ";" + g.getScore() + ";" + g.getTime() + ";" + g.getGuesses() + ";" + "\n");
            }
        }
    }
    
}
