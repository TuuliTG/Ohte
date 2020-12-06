package mastermindTest;

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

import mastermind.domain.Code;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author tgtuuli
 */
public class CodeTest {
    private Code code;
    @Before
    public void setUp() {
        code = new Code();
        code.setRandomCode();
    }
    
    @Test
    public void getCodeGivesRightSizeArray() {
        
        
        assertEquals(4, code.getCode().length);
    }
    
    @Test
    public void codeHasColors() {
        boolean hasColors = true;
        for (int i = 0; i < 4; i++) {
            if(!isColor(code.getCode()[i])) {
                hasColors = false;
            }
        }
        assertTrue(hasColors);
    }
    
    private boolean isColor(String s) {
        boolean isColor = false;
        String[] colors = {"Red", "Green", "Yellow", "Blue", "Black", "White"};
        for (int i = 0; i < colors.length; i++) {
            if (s.equals(colors[i])) {
                isColor = true;
                return isColor;
            }
                
        }
        return isColor;
    }
    
}
