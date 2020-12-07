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

/**
 * A game that is won will be saved as Game-object. 
 * @author tgtuuli
 */
public class Game implements Comparable<Game> {
    private String playerName;
    private double time;
    private int guesses;
    private int score;

    public Game(String playerName, int score, double time, int guesses) {
        this.playerName = playerName;
        this.time = time;
        this.guesses = guesses;
        this.score = score;
        
    }

    public Game(String playerName, double time, int guesses) {
        this.playerName = playerName;
        this.time = time;
        this.guesses = guesses;
        
    }
    
    
    /**
     * Sets the score according to time amount of time and guesses spent.
     */
    public void setScore() {
        
        int points = 2000;
        int timePoints = getTimePoints();
        int reduction = (guesses - 1) * 150;  
        points -= reduction;
        points = points + timePoints;
        
        this.score = points;
    }
    
    private int getTimePoints() {
        int i = 100;
        int points = 6000;
        while (i < time) {
            i += 100;
            points -= 1;
        }
        System.out.println("points from time " + points);
        return points;
        
    }

    public int getScore() {
        return score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getGuesses() {
        return guesses;
    }

    public double getTime() {
        return time;
    }

    @Override
    public int compareTo(Game game) {
        return this.score - game.score;
    }
    
     
}
