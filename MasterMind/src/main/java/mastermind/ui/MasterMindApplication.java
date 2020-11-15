
package mastermind.ui;

import java.util.Scanner;
import mastermind.domain.Code;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 *
 * @author tgtuuli
 */

public class MasterMindApplication extends Application {

    @Override
    public void start(Stage ikkuna) {
        ikkuna.setTitle("Hei Maailma!");
        ikkuna.show();
    }

    

    
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Welcome to Master Mind");
        System.out.println("Do you want to start?[Y/N]");
        
        String answer = scan.nextLine();
        if (answer.equals("Y") || answer.equals("y")) {
            start();
        }
        
    }
    
    public static void start() {
        Scanner scan = new Scanner(System.in);
        String[] guess = new String[4];
        GameLogic g = new GameLogic();
        int round = 0;
        while (g.isGameIsOver() == false) {
            round++;
            System.out.println("ROUND " + round);
            System.out.println("want to give up ?? Y/N");
            String q = scan.nextLine();
            if (q.equals("Y")) {
                break;
            }
            System.out.println("Make your guess:");
            String board = "[1][2][3][4]";
            for (int i = 0; i < 4; i++) {
                System.out.println(i+1 + ":[red, blue, yellow, green, white, black]");

                String answer = scan.nextLine();
                guess[i]=answer;
                board = board.replaceAll(Integer.toString(i+1), answer);
                System.out.println(board);

            }
            
            System.out.println("");
            System.out.println("--------------------------");
            g.setGuess(guess);
            int[] feedback = g.getFeedback();

            System.out.println("feedback :");
            for (int i = 0 ; i < 4; i++) {
                System.out.print(feedback[i] + ", " );
            }
            System.out.println("");
            System.out.println("");
            
        }
        
        System.out.println("THE RIGHT ANSWER IS");
        System.out.println("-----------------------");

        for (int i = 0 ; i < 4; i++) {
            System.out.print(g.getCode()[i] + ", " );
        }
        
        
    }
    
    
}
