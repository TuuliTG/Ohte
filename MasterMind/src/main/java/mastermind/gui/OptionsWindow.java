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

import com.sun.imageio.plugins.common.I18N;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mastermind.domain.Options;

/**
 *
 * @author tgtuuli
 */
public class OptionsWindow {
    
    private ChoiceBox<Integer> guessesChoicebox;
    private Stage optionsWindow;
    private Stage primaryStage;
    private Scene optionsScene, gameScene;
    private Options options;
    private Boolean isNewGame;
    private StopWatch timer;
    private Button startGameButton, closeButton; 
    private HBox buttonsBox, optionsBox;
    private VBox vbox;
    private Label guessSizeLabel, titleLabel;
    private BorderPane optionsBPane;
    

    public OptionsWindow(StopWatch timer, Stage primaryStage, Scene gameScene) {
        this.primaryStage = primaryStage;
        optionsBPane = new BorderPane();
        this.gameScene = gameScene;
        this.timer = timer;
        options = new Options();
        vbox = new VBox();
        optionsBox = new HBox();
        buttonsBox = new HBox();
    }
    
    public boolean displayOptions() {
        
        this.optionsScene = new Scene(optionsBPane, primaryStage.getWidth(), primaryStage.getHeight());
        setUpLabels();
        setUpChoiceBox();
        
        Insets insets = new Insets(10);
        BorderPane.setMargin(buttonsBox, insets);
        BorderPane.setMargin(vbox, insets);
        optionsBPane.setCenter(buttonsBox);
        optionsBPane.setTop(vbox);
        this.setUpButtons();
        
        vbox.getChildren().add(optionsBox);
        this.primaryStage.setScene(optionsScene);
        
        return isNewGame;
        
    }
    
    private void setUpLabels() {
        guessSizeLabel = new Label("How many guesses");
        guessSizeLabel.setPadding(new Insets(10));
        optionsBox.getChildren().add(guessSizeLabel);
        
        titleLabel = new Label("OPTIONS");
        vbox.getChildren().add(titleLabel);
        
    }
    
    private void setUpChoiceBox() {
        guessesChoicebox = new ChoiceBox<>(); 
        guessesChoicebox.getItems().addAll(6, 8, 10, 12, 14);
        guessesChoicebox.setValue(12);
        optionsBox.getChildren().add(guessesChoicebox);
    }
    
    private void setUpButtons() {
        startGameButton = new Button("Start a new game");
        startGameButton.setPadding(new Insets(10));
        closeButton = new Button("Close");
        closeButton.setPadding(new Insets(10));
        closeButton.setOnAction(e -> {
            this.isNewGame = false;
            timer.getTimeline().play();
            primaryStage.setScene(gameScene);
        });
        startGameButton.setOnAction(e -> {
            setNewOptions();
            this.isNewGame = true;
            this.primaryStage.close();
            GameScene newGameScene = new GameScene(new Stage(), options, "name");
            //this.primaryStage.setScene(newGameScene.getGameScene());
        });
        buttonsBox.getChildren().add(startGameButton);
        buttonsBox.getChildren().add(closeButton);
    }
    
    private void setNewOptions() {
        int numberOfGuesses = guessesChoicebox.getValue();
        options.setHeight(numberOfGuesses);
        
    }

    public Options getOptions() {
        return options;
    }
    
    
    
}
