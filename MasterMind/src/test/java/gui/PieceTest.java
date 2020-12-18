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
package gui;

import javafx.scene.paint.Color;
import mastermind.gui.Piece;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author tgtuuli
 */
public class PieceTest {
    
    private Piece p;
    @Before
    public void setUp() {
        p = new Piece(20, Color.GREY, 10, 10);
    }
    
    @Test
    public void createdPieceHasColorIndex0Test() {
        int index = p.getColorIndex();
        assertEquals(0, index);
    }
    
    @Test
    public void nextColorIsBlackTest() {
        Color expected = Color.BLACK;
        p.setNextColor();
        assertEquals(expected, p.getColor());
        
    }
    
    @Test
    public void afterLastColorNextIsGrey() {
        Color expected = Color.GREY;
        for (int i = 0; i < 7; i++) {
            p.setNextColor();
        }
        assertEquals(expected, p.getColor());
    }
    
}
