/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.domain;

import java.util.ArrayList;

/**
 *
 * @author tgtuuli
 */
public class Peg {
    
    private String color;
    private String[] possibleColors;

    public Peg(int numberOfPossibleColors) {
        this.possibleColors = new String[numberOfPossibleColors];
    }
    
    
    public void setColor(String color){
        boolean colorIsAllowed = false;
        for (int i = 0; i < possibleColors.length; i++) {
            if (possibleColors[i].equals(color)) {
                colorIsAllowed = true;
            }
        }
        if (colorIsAllowed) {
            this.color = color;
        }
    }
    
}
