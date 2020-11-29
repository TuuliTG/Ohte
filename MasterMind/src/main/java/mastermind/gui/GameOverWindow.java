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

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author tgtuuli
 */
public class GameOverWindow {
    public void showGameOverWindow() {
        Stage gameOverWindow = new Stage();
        gameOverWindow.setTitle("GAME OVER");
        gameOverWindow.initModality(Modality.APPLICATION_MODAL);
        StackPane layout = new StackPane();
        Scene gameOverScene = new Scene(layout);
        
        Button newGameButton = new Button("New Game");
        newGameButton.setOnAction(e -> {
            
        });
        
        Button quitGameButton = new Button("Quit");
        
        HBox gameOverBox = new HBox(10);
        gameOverBox.getChildren().add(newGameButton);
        gameOverBox.getChildren().add(quitGameButton);
        
        layout.getChildren().add(gameOverBox);
        
        gameOverWindow.setScene(gameOverScene);
        gameOverWindow.showAndWait();
        
    }
}
