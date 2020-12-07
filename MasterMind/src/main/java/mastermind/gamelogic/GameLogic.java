
package mastermind.gamelogic;

import java.util.Random;
import mastermind.domain.Code;

/**
 * This class is responsible for interaction between the codemaker and the codebreaker.
 * 
 * 
 */
public class GameLogic {
    
    private Code code;
    private String[] guess = new String[4];
    private boolean gameIsOver;

    public GameLogic() {
        code = new Code();
        code.setRandomCode();
        gameIsOver = false;

    }
    
    public void setGuess(String[] guess) {
        
        this.guess = guess;
        
    }
    /**
     * Gives feedback for the player. 
     * Black: (right color, right position) is marked 1. 
     * White (right color, wrong position) is marked 0. 
     * Wrong colour and wrong position is marked with 2.
     * If all four colors are right, sets the boolean gameIsOver true. 
     * @return int[] feedback, array size is 4
     */
    public int[] getFeedback() {
        int[] feedback = new int[4];
        int nrOfCorrects = 0;
        
        String[] copyGuess = new String[4];
        System.arraycopy(guess, 0, copyGuess, 0, 4);
        
        String[] copycode = new String[4];
        System.arraycopy(code.getCode(), 0, copycode, 0, 4);
        
        for (int i = 0; i < 4; i++) {
            if (code.getCode()[i].equals(guess[i])) {
                feedback[i] = 1;
                nrOfCorrects++;
                copyGuess[i] = "*";
                copycode[i] = "*";                
            }
        }
        for (int i = 0; i < 4; i++) {
            if (!copyGuess[i].equals("*")) {
                for (int j = 0; j < 4; j++) {
                    if (copycode[j].equals(copyGuess[i])) {
                        feedback[i] = 0;
                        copycode[j] = "*";
                        break;
                    }
                    feedback[i] = 2;
                }
            }

        }
        if (nrOfCorrects == 4) {
            gameIsOver = true;
        }
        feedback = shuffle(feedback);
        return feedback;
    }
    
    
    public boolean gameIsOver() {
        return gameIsOver;
    }

    public String[] getCode() {
        return code.getCode();
    }
    
    private int[] shuffle(int[] array) {
        int index, temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            index =  random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
        return array;
    }
    
    public void setCode(String[] colors) {
        this.code.setCode(colors);
    }
    
}
