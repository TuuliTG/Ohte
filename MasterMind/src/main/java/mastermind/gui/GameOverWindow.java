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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 *
 * @author tgtuuli
 */
public class GameOverWindow {
    
    private Stage gameOverWindow;
    private Stage primaryStage;
    private Button newGameButton, quitGameButton;
    private HBox gameOverBox;
    
    public void showGameOverWindow(Stage primaryStage) {
        this.primaryStage = primaryStage;
        gameOverWindow = new Stage();
        gameOverWindow.initStyle(StageStyle.UNDECORATED);
        
        gameOverWindow.setTitle("GAME OVER");
        gameOverWindow.initModality(Modality.APPLICATION_MODAL);
        
        BorderPane layout = new BorderPane();
        Scene gameOverScene = new Scene(layout, 200, 200);
        
        gameOverBox = new HBox();
        gameOverBox.setPadding(new Insets(20));
        
        layout.setCenter(gameOverBox);
        
        layout.setStyle("-fx-background-color: CORNSILK; -fx-border-style:solid; -fx-border-width: 2; -fx-border-color:black;");
        
        gameOverWindow.setScene(gameOverScene);
        gameOverWindow.showAndWait();
    }
    
    private void setUpButtons() {
        newGameButton = new Button("New Game");
        newGameButton.setPadding(new Insets(10));
        newGameButton.setOnAction(e -> {
            GameScene gameScene = new GameScene(new Stage());
        });
        
        quitGameButton = new Button("Quit");
        quitGameButton.setPadding(new Insets(10));
        quitGameButton.setOnAction(e -> {
            gameOverWindow.close();
            primaryStage.close();
        });
        
        gameOverBox.getChildren().add(newGameButton);
        gameOverBox.getChildren().add(quitGameButton);
    }
}
