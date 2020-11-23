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
package mastermind.ui;

import java.util.ArrayList;
import mastermind.domain.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author tgtuuli
 */
public class MasterMindApplication2 extends Application {
    
    public static final int TILE_SIZE = 50;
    public static final int PIECE_SIZE = 15;
    public static final int WIDTH = 5;
    public static final int HEIGHT = 12;
    
     
    private Pane root = new Pane();
    
    private Board board = new Board(root);
    private GameLogic game = new GameLogic();

    
    
    @Override
    public void start(Stage primaryStage) {
        
        Button acceptGuessButton = new Button();
        acceptGuessButton.setText("Accept Guess");
        acceptGuessButton.setOnAction(e -> {
            String[] guess = board.guessedColors();
            game.setGuess(guess);
            this.setFeedBackPieces();
            board.setNextActiveRow();
        });
        
        acceptGuessButton.setLayoutX(20);
        acceptGuessButton.setLayoutY(HEIGHT*TILE_SIZE + 20);
        root.getChildren().add(acceptGuessButton);
        
        
        mouseEvents();
        Scene scene = new Scene(root);
        primaryStage.setTitle("MASTERMIND");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void setFeedBackPieces() {
        int[] feedback = game.getFeedback();
        boolean gameIsOver = game.isGameIsOver();
        if (gameIsOver) {
            Label gameover = board.getGameOver();
            gameover.setText("GAME OVER");
            
        }
        Tile t = board.getCurrentActiveFeedbackTile();
        System.out.println(t.getPlace());
        t.setFillLight();
        Piece[] feedbackPieces = t.getFeedbackPieces();
        for (int i = 0; i < 4; i++) {
            if (feedback[i] == 1) {
                feedbackPieces[i].setFeebackPieceColor(Color.BLACK);
            } else if (feedback[i] == 0) {
                feedbackPieces[i].setFeebackPieceColor(Color.WHITE);
            } else if (feedback[i] == 2) {
                
            }
        }
    }
    
    private void mouseEvents() {
        root.setOnMouseMoved(e -> {
            Tile t = findTile(e.getX(), e.getY());
            if (t!= null) {
                t.setFillLight();
                board.setOtherOnRowBrown(t.getPlace());
            } else {
                board.setAllBrown();
            }
            
        });
        root.setOnMouseClicked(e -> {
            Tile t = findTile(e.getX(), e.getY());
            Piece p = t.getPiece();
            p.setNextColor();
        });
    }
    
     private Tile findTile(double x, double y) {
        Tile[][] tiles = board.getTiles();
        int row = board.getActiveRow();
        //System.out.println("current active row: " + row);
        if (y > row*TILE_SIZE && y < row*TILE_SIZE + TILE_SIZE) {
            int tileNr = (int) x / TILE_SIZE;
            if (tileNr < 5) {
                return tiles[tileNr][row];
            }
        }
        return null;
    }
    
    private String getFeedback(String[] guess) {
        int nrOfCorrects = 0;
        game.setGuess(guess);
        int[] feedback = game.getFeedback();
        String answer = "";
        for (int i = 0; i < 4; i++) {
            if (feedback[i] == 0) {
                System.out.println(feedback[i]);
                answer += "right color, wrong position \n";
            } else if (feedback[i] == 1) {
                answer += "right color, right position \n";
                nrOfCorrects++;
            } else if (feedback[i] == 2) {
                answer += "wrong color, wrong position \n";
            }
                
        }
        if(nrOfCorrects == 4) {
            return "YOU HAVE SOLVED THE CODE";
        }
        return answer;
    }
    
    private String endGame() {
        String result = "";
        String[] code = game.getCode();
        for (int i = 0; i < 4; i++) {
            result += code[i] + "\n";
        }
        return result;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
