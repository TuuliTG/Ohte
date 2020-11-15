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

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author tgtuuli
 */
public class MasterMindApplication2 extends Application {
    
    private GameLogic game = new GameLogic();
    
    
    @Override
    public void start(Stage primaryStage) {
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        //grid.setStyle("-fx-background-color: Brown");
        
        Label l1 = new Label();
        l1.setShape(new Circle(5));
        l1.setStyle("-fx-background-color: Grey");
        l1.setText("  ");
        Label l2 = new Label();
        l2.setShape(new Circle(5));
        l2.setStyle("-fx-background-color: Grey");
        l2.setText("  ");
        Label l3 = new Label();
        l3.setShape(new Circle(5));
        l3.setStyle("-fx-background-color: Grey");
        l3.setText("  ");
        Label l4 = new Label();
        l4.setShape(new Circle(5));
        l4.setStyle("-fx-background-color: Grey");
        l4.setText("  ");
        
        ChoiceBox<String> cb1 = new ChoiceBox();
        ChoiceBox<String> cb2 = new ChoiceBox();
        ChoiceBox<String> cb3 = new ChoiceBox();
        ChoiceBox<String> cb4 = new ChoiceBox();
        
        String[] varit = {"Red", "Blue", "Black", "Green", "White", "Yellow"};
        
        cb1.getItems().addAll(varit);
        cb1.getItems().add("valitse väri 1");
        cb2.getItems().addAll(varit);
        cb2.getItems().add("valitse väri 2");
        cb3.getItems().addAll(varit);
        cb3.getItems().add("valitse väri 3");
        cb4.getItems().addAll(varit);
        cb4.getItems().add("valitse väri 4");
        
        
        cb1.setValue("valitse väri 1");
        cb2.setValue("valitse väri 2");
        cb3.setValue("valitse väri 3");
        cb4.setValue("valitse väri 4");
        
        String[] colors = new String[4];
        
        cb1.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            colors[0]= newValue;
            l1.setStyle("-fx-background-color: " + newValue);
        });
        cb2.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            l2.setStyle("-fx-background-color: " + newValue);
            colors[1] = newValue;
                });
        cb3.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            l3.setStyle("-fx-background-color: " + newValue);
            colors[2] = newValue;
                });
        cb4.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->  {
            l4.setStyle("-fx-background-color: " + newValue);
            colors[3] = newValue;
                });
        
        
        Label feedbackLabel = new Label();
        
        //Start a new game button THIS IS NOT READY!!
        
        Button startButton = new Button();
        startButton.setText("Start a New Game");
        startButton.setOnAction(e -> {
            primaryStage.close();
            Scene scene = new Scene(grid, 700, 700);

            primaryStage.setTitle("TESTIPELI");
            primaryStage.setScene(scene);
            primaryStage.show();
        });
        
        //submit Button gets feedback
        
        Button submitButton = new Button();
        submitButton.setText("Submit!");
        submitButton.setOnAction(e -> {
            String feedback = getFeedback(colors);
            feedbackLabel.setText(feedback);
            String testPrint = "";
            for (int i = 0; i < 4; i++) {
                testPrint += colors[i] + " : ";
            }
            System.out.println(testPrint);
                });
        
        Label resultLabel = new Label();
        
        // End game button prints the right answer
        
        Button endGameButton = new Button();
        endGameButton.setText("End the Game");
        
        endGameButton.setOnAction(e -> {
            resultLabel.setText(endGame());
        });
       
        GridPane.setConstraints(l1, 1, 4);
        GridPane.setConstraints(l2, 2, 4);
        GridPane.setConstraints(l3, 3, 4);
        GridPane.setConstraints(l4, 4, 4);
        GridPane.setConstraints(cb1, 1, 5);
        GridPane.setConstraints(cb2, 2, 5);
        GridPane.setConstraints(cb3, 3, 5);
        GridPane.setConstraints(cb4, 4, 5);
        GridPane.setConstraints(submitButton, 1, 6);
        GridPane.setConstraints(feedbackLabel, 1, 7);
        GridPane.setConstraints(endGameButton, 1, 8);
        GridPane.setConstraints(resultLabel, 1, 9);
        //GridPane.setConstraints(startButton, 3, 8);
        
        grid.getChildren().add(l1);
        grid.getChildren().add(l2);
        grid.getChildren().add(l3);
        grid.getChildren().add(l4);
        grid.getChildren().add(cb1);
        grid.getChildren().add(cb2);
        grid.getChildren().add(cb3);
        grid.getChildren().add(cb4);
        grid.getChildren().add(submitButton);
        grid.getChildren().add(feedbackLabel);
        grid.getChildren().add(endGameButton);
        grid.getChildren().add(resultLabel);
        //grid.getChildren().add(startButton);
        
        
        Scene scene = new Scene(grid, 700, 700);
        
        primaryStage.setTitle("TESTIPELI");
        primaryStage.setScene(scene);
        primaryStage.show();
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
