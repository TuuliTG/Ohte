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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mastermind.domain.Player;
import mastermind.domain.PlayerService;

/**
 * Shows the New Game window to the player.
 * @author tgtuuli
 */
public class NewGameWindow {
    
    private BorderPane layout;
    private Button newGameButton, quitGameButton;
    private HBox hbox1;
    private HBox hbox2;
    private Label playerNameLabel, errorLabel;
    private PlayerService playerService;
    private Stage newGameWindow;
    private Stage primaryStage;
    private StopWatch timer;
    private TextField playernameInput;
    private VBox vbox;
    
    
    public NewGameWindow(PlayerService playerService) {
        this.playerService = playerService;
        newGameWindow = new Stage();
        layout = new BorderPane();
        hbox1 = new HBox();
        hbox2 = new HBox();
        vbox = new VBox();
        this.playernameInput = new TextField();
        this.errorLabel = new Label();
        this.playerNameLabel = new Label();
        setUpLabels();
        setUpButtons();
        setUptextField();
        vbox.getChildren().add(hbox1);
        vbox.getChildren().add(errorLabel);
        vbox.getChildren().add(hbox2);
        layout.setCenter(vbox);
    }
    
    private void setUpLabels() {
        playerNameLabel.setText("Player Name:");
        playerNameLabel.setPadding(new Insets(20));
        hbox1.getChildren().add(playerNameLabel);
    }
    
    private void setUptextField() {
        hbox1.getChildren().add(playernameInput);
        
    }
    private void setUpButtons() {
        newGameButton = new Button("Start New Game");
        newGameButton.setOnAction(e -> {
            String name = playernameInput.getText();
            if (name.length() < 3) {
                this.errorLabel.setText("Name too short");
            } else if (name.length() > 15) {
                this.errorLabel.setText("Name too long");
            } else {
                boolean created = this.playerService.createPlayer(name);
                System.out.println("new player created : " + created);
                this.playerService.login(name);
                Player p = playerService.getCurrentPlayer();
                this.newGameWindow.close();
                primaryStage.close();
                GameScene newGameScene = new GameScene(new Stage(), playerService, false);
                
            }
        });
        hbox2.getChildren().add(newGameButton);
    }
    
    /**
     * Shows the new Game Window
     * @param primaryStage 
     */
    public void show(Stage primaryStage) {
        System.out.println("showing window");
        this.primaryStage = primaryStage;
        Scene newGameScene = new Scene(layout, 300, 300);
        newGameWindow.setScene(newGameScene);
        newGameWindow.setTitle("New Game");
        newGameWindow.initModality(Modality.APPLICATION_MODAL);
        newGameWindow.showAndWait();
    }
    
    
}
