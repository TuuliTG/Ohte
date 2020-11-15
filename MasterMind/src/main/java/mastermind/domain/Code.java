
package mastermind.domain;

import java.util.Random;

/**
 * 
 * 
 */
public class Code {
    
    private String[] code;
    private String[] colors = {"red", "green", "yellow", "blue", "black", "white"};
    private boolean blanksAreAllowed;

    public Code() {
        this.code = new String[4];
       
    }
    
   /**
    * Sets the Codemakers code
    * colours from left to right
    */ 
    public void setRandomCode() {
        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            int next = rand.nextInt(6);
            code[i]=colors[next];
        }
        
    }

    public String[] getCode() {
        return code;
    }
    
}