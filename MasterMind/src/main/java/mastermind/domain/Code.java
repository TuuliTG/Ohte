/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.domain;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author tgtuuli
 */
public class Code {
    
    private String[] code;
    private String[] colors = {"red", "green", "yellow", "blue", "black", "white"};
    private boolean blanksAreAllowed;

    public Code() {
        this.code = new String[4];
       
    }
   /**
    * Sets the codemakers code
    * colours from left to right
    * @param c1
    * @param c2
    * @param c3
    * @param c4 
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
