/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.UI;

import java.util.HashMap;
import mastermind.Code;

/**
 *
 * @author tgtuuli
 */
public class GamePlay {
    
    private Code code;
    private String[] guess = new String[4];
    private HashMap<String, Integer> coloursAndOccurances;
    private boolean gameIsOver;

    public GamePlay() {
        this.coloursAndOccurances = new HashMap<>();
        code = new Code();
        coloursAndOccurances.put("red", 0);
        coloursAndOccurances.put("green", 0);
        coloursAndOccurances.put("yellow", 0);
        coloursAndOccurances.put("blue", 0);
        coloursAndOccurances.put("white", 0);
        coloursAndOccurances.put("black", 0);
        gameIsOver = false;

    }
    
    public void setCode(Code code) {
        this.code = code;
        for (int i = 0; i < 4; i++) {
            coloursAndOccurances.put(code.getCode()[i], coloursAndOccurances.get(code.getCode()[i])+1);
        }
    }

    public void setGuess(String[] guess) {
        this.guess = guess;
    }
    /**
     *  black (right color, right position) is marked 1 and 
     * white (right color, wrong position) is marked 0
     * wrong colour and wrong position is marked with 2
     * @return 
     */
    public int[] getFeedback() {
        int[] feedback = new int[4];
        int nrOfCorrects = 0;
        
        String[] copycode = new String[4];
        System.arraycopy(code.getCode(), 0, copycode, 0, 4);
        
        for (int i = 0; i < 4; i++) {
            if (code.getCode()[i].equals(guess[i])){
                feedback[i]=1;
                nrOfCorrects++;
                guess[i]= "*";
                copycode[i] = "*";                
            }
        }
        /*
        for (int i = 0; i < 4; i++) {
            System.out.print(copycode[i] + " ");
        }
        System.out.println("");
        */
        for (int i = 0; i < 4; i++) {
            if (!guess[i].equals("*")){
                for (int j = 0; j < 4; j++) {
                    if (copycode[j].equals(guess[i])) {
                        feedback[i] = 0;
                        copycode[j] = "*";
                        break;
                    }
                    feedback[i] = 2;
                }
            }
            /*
             for (int j = 0; j < 4; j++) {
               System.out.print(copycode[j] + " ");
            }
            System.out.println("");
*/
            
        }
        if (nrOfCorrects == 4) {
            System.out.println("YOU HAVE SOLVED THE CODE");
            gameIsOver = true;
        }
        return feedback;
    }
    
    public void printOccurances() {
        System.out.println("red " + this.coloursAndOccurances.get("red"));
        System.out.println("blue" + this.coloursAndOccurances.get("blue"));
        System.out.println("white " + this.coloursAndOccurances.get("white"));
    }

    public boolean isGameIsOver() {
        return gameIsOver;
    }
    
}
