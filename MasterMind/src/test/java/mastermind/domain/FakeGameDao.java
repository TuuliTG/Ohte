/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.print.Collation;
import mastermind.dao.GameDao;
import mastermind.domain.Game;

/**
 *
 * @author tgtuuli
 */
public class FakeGameDao implements GameDao {
    
    private List<Game> gamesPlayed = new ArrayList<>();

    public FakeGameDao() {
        gamesPlayed.add(new Game("Test", 1500, 10000.0, 6));
    }
    
    
    @Override
    public Game create(Game game) throws Exception {
        gamesPlayed.add(game);
        return game;
    }

    @Override
    public List<Game> findAllSortedByScore() {
        Collections.sort(gamesPlayed);
        Collections.reverse(gamesPlayed);
        return gamesPlayed;
    }
    
}
