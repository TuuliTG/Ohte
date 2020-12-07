/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.domain;

import java.util.ArrayList;

/**
 * A player class for saving player data to the database. 
 * Stores information about the player: name, best time, best score, amount of games won.
 * The List of games played is not yet functional in the program.
 * @author tgtuuli
 */
public class Player {
    
    private String name;
    private double bestTime;
    private int bestScore;
    private int amountOfGamesWon;
    private ArrayList<Game> gamesPlayed;

    public Player(String name) {
        this.name = name;
        this.amountOfGamesWon = 0;
        this.gamesPlayed = new ArrayList<>();
    }

    public Player(String name, double bestTime, int bestScore, int numberOfGamesWon) {
        this.name = name;
        this.bestTime = bestTime;
        this.bestScore = bestScore;
        this.gamesPlayed = new ArrayList<>();
        this.amountOfGamesWon = numberOfGamesWon;
    }
    
    public String getName() {
        return name;
    }

    public double getBestTime() {
        return bestTime;
    }

    public ArrayList<Game> getGamesPlayed() {
        return gamesPlayed;
    }

    public void setBestTime(double bestTime) {
        this.bestTime = bestTime;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public int getBestScore() {
        return bestScore;
    }
    
    public void addGamesWon() {
        this.amountOfGamesWon++;
    }

    public int getAmountOfGamesWon() {
        return amountOfGamesWon;
    }
    
    
    
    
    
    
    
    
    
    
    
}
