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
package mastermind.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mastermind.domain.PlayerService;
/**
 *
 * @author tgtuuli
 */
public class GameOverWindow {
    
    private boolean codeSolved;
    private BorderPane layout;
    private Button newGameButton, quitGameButton, closeWindowButton;
    private HBox gameOverBox;
    private Label gameWonLabel, noGuessesLeftLabel, timeLabel;
    private PlayerService playerService;
    private Stage gameOverWindow;
    private Stage primaryStage;
    private StopWatch timer;
    private VBox vbox;

    public GameOverWindow(boolean codeSolved, StopWatch timer, PlayerService playerService) {
        gameOverWindow = new Stage();
        layout = new BorderPane();
        this.codeSolved = codeSolved;
        this.timer = timer;
        this.playerService = playerService;
        vbox = new VBox();
        
    }
    
    
    
    public void showGameOverWindow(Stage primaryStage) {
        this.primaryStage = primaryStage;
        
        gameOverWindow.initStyle(StageStyle.UNDECORATED);
        
        gameOverWindow.setTitle("GAME OVER");
        gameOverWindow.initModality(Modality.APPLICATION_MODAL);
        
        
        Scene gameOverScene = new Scene(layout, 400, 400);
        
        gameOverBox = new HBox();
        gameOverBox.setPadding(new Insets(20));
        
        layout.setCenter(gameOverBox);
        setUpLabels();
        
        layout.setStyle("-fx-background-color: CORNSILK; -fx-border-style:solid; -fx-border-width: 2; -fx-border-color:black;");
        setUpButtons();
        
        gameOverWindow.setScene(gameOverScene);
        gameOverWindow.showAndWait();
    }
    
    private void setUpLabels() {
        this.gameWonLabel = new Label("CONGRATULATIONS! YOU HAVE SOLVED THE CODE!");
        gameWonLabel.setPadding(new Insets(20));
        System.out.println("your time in milliseconds " + timer.getMilliSeconds());
        this.timeLabel = new Label("Your time: " + (int) timer.getMinutes().toMinutes() + ":" + (int) timer.getSeconds().toSeconds());
        this.timeLabel.setPadding(new Insets(20));
        
        vbox.getChildren().add(this.gameWonLabel);
        vbox.getChildren().add(timeLabel);
        
        this.noGuessesLeftLabel = new Label("SORRY! NO GUESSES LEFT!");
        noGuessesLeftLabel.setPadding(new Insets(20));
        System.out.println("your time in milliseconds " + timer.getMilliSeconds());

        if (codeSolved) {
            layout.setTop(vbox);
        } else {
            layout.setTop(noGuessesLeftLabel);
        }
        
        
    }
    
    private void setUpButtons() {
        newGameButton = new Button("New Game");
        newGameButton.setPadding(new Insets(10));
        newGameButton.setOnAction(e -> {
            gameOverWindow.close();
            primaryStage.close();
            GameScene newGameScene = new GameScene(new Stage(), playerService, false);
        });
        quitGameButton = new Button("Quit");
        quitGameButton.setPadding(new Insets(10));
        quitGameButton.setOnAction(e -> {
            gameOverWindow.close();
            primaryStage.close();
        });
        closeWindowButton = new Button("Close");
        closeWindowButton.setPadding(new Insets(10));
        closeWindowButton.setOnAction(e -> {
            gameOverWindow.close();
        });
        gameOverBox.getChildren().add(newGameButton);
        gameOverBox.getChildren().add(closeWindowButton);
        gameOverBox.getChildren().add(quitGameButton);
    }
}
