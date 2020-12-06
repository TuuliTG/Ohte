package mastermind.domain;

import java.util.ArrayList;
import java.util.List;
import mastermind.dao.PlayerDao;
import mastermind.domain.Player;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author tgtuuli
 */
public class FakePlayerDao implements PlayerDao {
    
    List<Player> players = new ArrayList<>();

    public FakePlayerDao() {
        players.add(new Player("Testi", 10000.0, 12345, 1));
    }
    

    @Override
    public Player findByName(String name) {
        return players.stream().filter(p -> p.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Player> getAll() {
        return players;
    }

    @Override
    public void setBestScore(Player p, int score) throws Exception {
        for (Player player : players) {
            if (player.equals(p)) {
                player.setBestScore(score);
            }
        }
    }

    @Override
    public void setBestTime(Player p, double time) throws Exception {
        for (Player player : players) {
            if (player.equals(p)) {
                player.setBestTime(time);
            }
        }
    }

    @Override
    public void addOneGamePlayed(Player p) throws Exception {
        for (Player player : players) {
            if (player.equals(p)) {
                player.addGamesWon();
            }
        }
    }

    @Override
    public Player create(Player player) throws Exception {
        players.add(player);
        return player;
    }
    
    
}
