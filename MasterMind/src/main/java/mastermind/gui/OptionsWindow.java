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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    private Scene optionsScene;
    private Options options;
    private Boolean isNewGame;

    public OptionsWindow() {
        options = new Options();
    }
    
    
    
    public boolean displayOptions() {
        this.optionsWindow = new Stage();
        optionsWindow.initModality(Modality.APPLICATION_MODAL);
        optionsWindow.setTitle("Options");
        
        BorderPane optionsBPane = new BorderPane();
        this.optionsScene = new Scene(optionsBPane, 300, 300);
        
        
        
        Button startGameButton = new Button("Start game");
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            this.isNewGame = false;
            optionsWindow.close();
                });
        
        Label guessSizeLabel = new Label("How many guesses");
        
        guessesChoicebox = new ChoiceBox<>(); 
        guessesChoicebox.getItems().addAll(6,8,10,12,14);
        guessesChoicebox.setValue(12);
        
        
        startGameButton.setOnAction(e -> {
            
            newGameWithOptions();
            this.isNewGame = true;
            
            
            this.optionsWindow.close();
        });
        
        
        HBox hbox2 = new HBox();
        hbox2.getChildren().add(guessSizeLabel);
        hbox2.getChildren().add(guessesChoicebox);
        
        HBox hbox = new HBox();
        hbox.getChildren().add(startGameButton);
        hbox.getChildren().add(closeButton);
        
        optionsBPane.setBottom(hbox);
        optionsBPane.setTop(hbox2);
        optionsWindow.setScene(optionsScene);
        optionsWindow.showAndWait();
        return isNewGame;
        
    }
    
    private void newGameWithOptions() {
        int numberOfGuesses = guessesChoicebox.getValue();
        options.setHeight(numberOfGuesses);
        
    }

    public Options getOptions() {
        return options;
    }
    
    
    
}
