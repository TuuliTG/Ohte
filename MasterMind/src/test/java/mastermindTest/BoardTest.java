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
package mastermindTest;

import javafx.scene.layout.Pane;
import mastermind.domain.Board;
import mastermind.domain.Tile;
import mastermind.domain.Constants;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author tgtuuli
 */
public class BoardTest {
    private Board b;
    @Before
    public void setUp() {
        b = new Board(new Pane());
    }
    
    @Test
    public void boardGetsCreatedTest() {
        
        
        assertNotNull(b);
    }
    
    @Test
    public void boardHasRightAmountOfTilesTest() {
        Tile[][] tiles = b.getTiles();
        int x = tiles.length;
        int y = tiles[0].length;
        assertEquals(Constants.WIDTH, x);
        assertEquals(Constants.HEIGHT, y);
    }
    
}
