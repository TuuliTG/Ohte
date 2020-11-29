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

import mastermind.domain.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


/**
 *
 * @author tgtuuli
 */
public class MasterMindApplication2 extends Application {
    
    private Stage window;
    private Board board;
    private BorderPane borderPane;
    private Scene gameScene, optionsScene;
            
    public MasterMindApplication2() {
        
        borderPane = new BorderPane();
        board = new Board(borderPane);
        
        
    }
    
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        this.setUpGameScene();
        window.show();
    }
    
    private void mouseEvents() {
        
        borderPane.setOnMouseMoved(e -> {
            Tile t = findTile(e.getX(), e.getY());
            if (t != null) {
                t.setFillLight();
                board.setOtherOnRowBrown(t.getPlace());
            } else {
                board.setAllBrown();
            }
            
        });
        borderPane.setOnMouseClicked(e -> {
            Tile t = findTile(e.getX(), e.getY());
            Piece p = t.getPiece();
            p.setNextColor();
        });
    }
    
    private Tile findTile(double x, double y) {
        Tile[][] tiles = board.getTiles();
        int row = board.getActiveRow();
        double boardsStartingPoint = board.getPane().getLayoutY();
        if (y > boardsStartingPoint + row * Constants.TILE_SIZE && y < boardsStartingPoint + row * Constants.TILE_SIZE + Constants.TILE_SIZE) {
            int tileNr = (int) x / Constants.TILE_SIZE;
            if (tileNr < 4) {
                return tiles[tileNr][row];
            }
        }
        return null;
    }
    
        
    private void setMenuBar() {
        Menu gameMenu = new Menu("Game");
        MenuItem newGame = new MenuItem("New Game");
        
        newGame.setOnAction(e -> {
            window.close();
            
            this.borderPane = new BorderPane();
            this.board = new Board(borderPane);
            this.setUpGameScene();
            window.show();
        });
        gameMenu.getItems().add(newGame);
        MenuItem endGame = new MenuItem("End Game");
        endGame.setOnAction(e -> {
            window.close();
        });
        
        gameMenu.getItems().add(endGame);
        
        MenuItem options = new MenuItem("Options...");
        options.setOnAction(e -> {
            showOptionsPage();
        });
        gameMenu.getItems().add(options);
        
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(gameMenu);
        this.borderPane.setTop(menuBar);
    }
    
    private void showOptionsPage() {
        setUpOptionsScene();
        window.setScene(optionsScene);
    }
    
    private void setUpGameScene() {
        setMenuBar();
        mouseEvents();
        gameScene = new Scene(borderPane);
        window.setTitle("MASTERMIND");
        window.setScene(gameScene);
    }
    
    private void setUpOptionsScene() {
        BorderPane optionsBPane = new BorderPane();
        optionsScene = new Scene(optionsBPane, 300, 300);
        
        Button startGameButton = new Button("Start game");
        startGameButton.setOnAction(e -> {
            this.borderPane = new BorderPane();
            this.board = new Board(borderPane);
            setUpGameScene();
            });
        optionsBPane.setCenter(startGameButton);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
