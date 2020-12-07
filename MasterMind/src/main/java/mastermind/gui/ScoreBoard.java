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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mastermind.domain.Game;
import mastermind.domain.PlayerService;

/**
 * Shows the Score board to the player.
 * @author tgtuuli
 */
public class ScoreBoard {
    
    private BorderPane layout;
    private Button closeButton;
    private PlayerService playerService;
    private Scene gameScene, scoreBoardScene;
    private Stage primaryStage;
    private StopWatch timer;
    private TableView table;
    private VBox vbox;
    

    public ScoreBoard(Stage primaryStage, StopWatch timer, PlayerService playerService, Scene gameScene) {
        this.primaryStage = primaryStage;
        this.playerService = playerService;
        this.gameScene = gameScene;
        this.timer = timer;
        this.vbox = new VBox();
        this.setUpTable();
        this.setUpButton();
    }
    
    private void setUpTable() {
        table = new TableView();
        table.setItems(getGames());
        TableColumn<Game, String> playerColumn = new TableColumn<>("Player");
        playerColumn.setMinWidth(150);
        playerColumn.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        TableColumn<Game, Integer> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setMinWidth(100);
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        TableColumn<Game, Double> timeColumn = new TableColumn<>("Time");
        timeColumn.setMinWidth(100);
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        TableColumn<Game, Integer> guessesColumn = new TableColumn<>("Guesses");
        guessesColumn.setMinWidth(70);
        guessesColumn.setCellValueFactory(new PropertyValueFactory<>("guesses"));
        table.getColumns().addAll(playerColumn, scoreColumn, timeColumn, guessesColumn);
        table.setPadding(new Insets(20));
        this.vbox.getChildren().add(table);
    }
    
    private void setUpButton() {
        closeButton = new Button("BackTo Game");
        closeButton.setOnAction(e -> {
            this.timer.getTimeline().play();
            this.primaryStage.setScene(gameScene);
        });
        
        this.vbox.getChildren().add(closeButton);
    }
    
    private ObservableList<Game> getGames() {
        ObservableList<Game> games = FXCollections.observableArrayList();
        games.addAll(playerService.getBestGames());
        return games;
    }
    
    public void displayScoreBoard() {
        this.layout = new BorderPane();
        layout.setCenter(vbox);
        this.scoreBoardScene = new Scene(layout, primaryStage.getWidth(), primaryStage.getHeight());
        this.primaryStage.setScene(scoreBoardScene);
        
    }
    
    
    
    
}
