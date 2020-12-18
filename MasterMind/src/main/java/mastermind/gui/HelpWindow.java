/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.gui;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Shows game instructions
 * @author tgtuuli
 */
public class HelpWindow {
    
    private Button closeButton;
    private TextArea textArea;
    private final Stage primaryStage;
    private Scene gameScene, helpScene;
    private final BorderPane layout;
    private final StopWatch timer;

    public HelpWindow(Stage primaryStage, Scene gameScene, StopWatch timer) {
        this.primaryStage = primaryStage;
        this.timer = timer;
        this.gameScene = gameScene;
        this.layout = new BorderPane();
        this.setInstructions();
        this.setUpButtons();
    }
    
    private void setUpButtons() {
        this.closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            this.primaryStage.setScene(gameScene);
            this.timer.getTimeline().play();
        });
        this.layout.setBottom(closeButton);
    }
    
    private void setInstructions() {
        this.textArea = new TextArea();
        
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            
            InputStream inputStream = classLoader.getResourceAsStream("public/instructions.txt");
            InputStreamReader iReader = new InputStreamReader(inputStream);
           
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            String text = "";
            while ((line = reader.readLine()) != null) {
                text += line + "\n";
            }
            this.textArea.setText(text);
        } catch (Exception e) {
            System.out.println("file not found. " + e.getMessage());
        }
        
    }
    /**
     * Sets the scene for showing the help window.
     */
    public void show() {
        this.textArea.setEditable(false);
        this.textArea.setMinWidth(primaryStage.getWidth() - 10);
        this.textArea.setMinHeight(primaryStage.getHeight() - 100);
        this.textArea.setPadding(new Insets(10));
        this.textArea.setWrapText(true);
        this.layout.setTop(textArea);
        BorderPane.setAlignment(closeButton, Pos.CENTER);
        BorderPane.setMargin(closeButton, new Insets(0, 0, 50, 0));
        this.helpScene = new Scene(layout, this.primaryStage.getWidth(), this.primaryStage.getHeight());
        this.primaryStage.setScene(helpScene);
    }
    
    
}
