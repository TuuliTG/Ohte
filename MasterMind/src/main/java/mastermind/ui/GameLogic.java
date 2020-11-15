
package mastermind.ui;

import java.util.HashMap;
import mastermind.domain.Code;

/**
 * 
 * 
 */
public class GameLogic {
    
    private Code code;
    private String[] guess = new String[4];
    private HashMap<String, Integer> coloursAndOccurances;
    private boolean gameIsOver;

    public GameLogic() {
        code = new Code();
        gameIsOver = false;

    }
    
    public void setCode(Code code) {
        this.code = code;
    }

    public void setGuess(String[] guess) {
        this.guess = guess;
    }
    /**
     *  black (right color, right position) is marked 1 and 
     * white (right color, wrong position) is marked 0
     * wrong colour and wrong position is marked with 2
     * @return int[] feedback, array size is 4
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
    
    public boolean isGameIsOver() {
        return gameIsOver;
    }
    
}