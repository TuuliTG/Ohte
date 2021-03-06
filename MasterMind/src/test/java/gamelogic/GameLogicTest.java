package gamelogic;

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

import mastermind.gamelogic.GameLogic;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author tgtuuli
 */
public class GameLogicTest {
    private GameLogic game;
    
    @Before
    public void setUp() {
        game = new GameLogic();
        
    }
    
    @Test
    public void feedbackTest1() {
        String[] c = {"Blue", "Blue", "Blue", "Blue"};
        game.setCode(c);
        String[] g = {"Blue", "Blue", "Blue", "Blue"};
        game.setGuess(g);
        int[] feedback = game.getFeedback();
        int[] expectedFeedback = {1, 1, 1, 1};
        assertArrayEquals(expectedFeedback, feedback);
        assertTrue(game.gameIsOver());
    }
    
    @Test
    public void feedbackTest2() {
        String[] c = {"Blue", "Red", "Yellow", "Black"};
        game.setCode(c);
        String[] g = {"Blue", "Blue", "Black", "White"};
        game.setGuess(g);
        int[] feedback = game.getFeedback();
        int onesExp = 1;
        int twosExp = 2;
        int zerosExp = 1;
        int ones = 0;
        int twos = 0;
        int zeros = 0;
        
        for (int i = 0; i < 4; i++) {
            if (feedback[i] == 1) {
                ones++;
            } else if (feedback[i] == 2) {
                twos++;
            } else if (feedback[i] == 0) {
                zeros++;
            }
        }
         assertEquals(onesExp, ones);
         assertEquals(twosExp, twos);
         assertEquals(zerosExp, zeros);
    }
    
    @Test
    public void feedbackTest3() {
        String[] c = {"Blue", "Blue", "Blue", "Blue"};
        game.setCode(c);
        String[] g = {"White", "White", "Black", "Red"};
        game.setGuess(g);
        int[] feedback = game.getFeedback();
        int[] expectedFeedback = {2, 2, 2, 2};
        assertArrayEquals(expectedFeedback, feedback);
        assertFalse(game.gameIsOver());
    }
    
    @Test
    public void feedbackTest4() {
        String[] c = {"Blue", "Green", "Yellow", "Red"};
        game.setCode(c);
        String[] g = {"Green", "Yellow", "Red", "Blue"};
        game.setGuess(g);
        int[] feedback = game.getFeedback();
        int[] expectedFeedback = {0, 0, 0, 0};
        assertArrayEquals(expectedFeedback, feedback);
        assertFalse(game.gameIsOver());
    }
    
}
