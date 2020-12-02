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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import mastermind.domain.Constants;
import mastermind.domain.Options;
import mastermind.domain.Piece;
import mastermind.domain.Tile;
import mastermind.gamelogic.GameLogic;

/**
 *
 * @author tgtuuli
 */
public class GameScene {
    
    private BorderPane bPane;
    private Label roundLabel;
    private Label guessesLeftLabel;
    private Label playerLabel;
    private Label gameOver;
    private ImageView arrow;
    private GameLogic game;
    private Button acceptGuessButton;
    private HBox hBox;
    private Pane pane;
    private Stage window;
    private Scene gameScene, optionsScene;
    private ChoiceBox<Integer> guessesChoicebox;
    private Board board;
    private Options newGameOptions;
    private StopWatch timer;
    private String player;
    private boolean programIsStarting;

    public GameScene(Stage stage, String name, boolean programIsStarting) {
        this.window = stage;
        board = new Board();
        this.player = name;
        newGameOptions = new Options();
        if (programIsStarting) {
            NewGameWindow newGameWindow = new NewGameWindow();
            newGameWindow.show(window);
        } else {
            this.newGame();
        }
    }
    
    public GameScene(Stage stage, Options options, String player) {
        this.window = stage;
        board = new Board();
        this.player = player;
        newGameOptions = options;
        this.newGame();
    }
    
    
    public void moveArrowDown() {
        this.arrow.setY(arrow.getY() + Constants.TILE_SIZE);
    }
    
    private void setUpGameScene() {
        //System.out.println("showing scene");
        
        setMenuBar();
        mouseEvents();
        
        gameScene = new Scene(bPane);
        window.setTitle("MASTERMIND");
        window.setScene(gameScene);
        
        window.show();
    }
    
    private void setMenuBar() {
        Menu gameMenu = new Menu("Game");
        MenuItem newGame = new MenuItem("New Game");
        newGame.setOnAction(e -> {
            this.timer.getTimeline().stop();
            NewGameWindow newGameWindow = new NewGameWindow();
            newGameWindow.show(window);
        });
        gameMenu.getItems().add(newGame);
        MenuItem endGame = new MenuItem("End Game");
        endGame.setOnAction(e -> {
            window.close();
        });
        gameMenu.getItems().add(endGame);
        MenuItem options = new MenuItem("Options...");
        
        options.setOnAction(e -> optionsMenuActions());
        gameMenu.getItems().add(options);
        
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(gameMenu);
        this.bPane.setTop(menuBar);
    }
       
    private void mouseEvents() {
        
        bPane.setOnMouseMoved(e -> {
            Tile t = board.findTile(e.getX(), e.getY());
            if (t != null) {
                t.setFillLight();
                this.board.setOtherOnRowBrown(t.getPlace());
            } else {
                this.board.setAllBrown();
            }
            
        });
        bPane.setOnMouseClicked(e -> {
            Tile t = this.board.findTile(e.getX(), e.getY());
            Piece p = t.getPiece();
            p.setNextColor();
        });
    }
        
    private void setUpLabels() {
        roundLabel = new Label();
        playerLabel = new Label(player);
        
        guessesLeftLabel = new Label();
       
        gameOver = new Label();
        roundLabel.setText("ROUND: " + 1);
        roundLabel.setPadding(new Insets(10));
       
        hBox.getChildren().add(roundLabel);
        
        guessesLeftLabel.setText("Guesses left: " + this.newGameOptions.getHeight());
        guessesLeftLabel.setPadding(new Insets(10));
        
        hBox.getChildren().add(guessesLeftLabel);
        hBox.getChildren().add(playerLabel);
        
        
        hBox.getChildren().add(gameOver);
        
    }
          
    private void setUpButtons() {
        this.acceptGuessButton = new Button();
        acceptGuessButton.setText("Accept Guess");
        acceptGuessButton.setPadding(new Insets(10));
        acceptGuessButton.setOnAction(e -> acceptButtonActions());
        
        acceptGuessButton.setLayoutX(Constants.WIDTH * Constants.TILE_SIZE + 100);
        acceptGuessButton.setLayoutY(20);
        //root.getChildren().add(acceptGuessButton);
        hBox.getChildren().add(acceptGuessButton);
    }
           
    private void setArrowSign() throws FileNotFoundException {
        Image image = new Image(new FileInputStream("public/arrowLeft.png"));
        
        arrow = new ImageView(image);
        arrow.setX(Constants.TILE_SIZE * Constants.WIDTH + 10);
        arrow.setY(this.board.getPane().getLayoutY() - 10);
        arrow.setFitHeight(70);
        arrow.setFitWidth(70);
        arrow.setRotate(180);
        this.board.getPane().getChildren().add(arrow);
        
    }
            
    private void newGame() {
        int height = newGameOptions.getHeight();
        timer = new StopWatch();
        board.setHeight(height);
        board.setUpScene();
        Pane boardPane = board.getPane();
        Insets insets = new Insets(10);
        hBox = new HBox(8);
        bPane = new BorderPane();
        this.bPane.setBottom(boardPane);
        BorderPane.setMargin(boardPane, insets);
        BorderPane.setMargin(hBox, insets);
        setUpButtons();
        this.setUpLabels();
        try {
            this.setArrowSign();
        } catch (Exception e) {
            System.out.println("Image not found");
        }
        this.bPane.setCenter(hBox);
        this.startTimer();
        this.setUpGameScene();
        
    }
    
    private void startTimer() {
        
        if (timer.getTimeline() != null) {
            timer.setMinutes(Duration.ZERO);
            timer.getTimeSeconds().set((int) timer.getSeconds().toMinutes());
            
        } else {
            handleTimer();
        }
        
        this.hBox.getChildren().add(timer.getMinutesLabel());
        this.hBox.getChildren().add(timer.getSecondsLabel());
    }
    
    private void optionsMenuActions() {
        this.timer.getTimeline().pause();
        OptionsWindow optionsWindow = new OptionsWindow(timer, window, gameScene);
        boolean isNewGame = optionsWindow.displayOptions();

        if (isNewGame) {
            System.out.println("starting a ne game");
            this.newGameOptions = optionsWindow.getOptions();
            this.window.close();
            
            this.newGame();
        }
    }
    
    private void acceptButtonActions() {
        String[] guess = this.board.guessedColors();
        board.getGame().setGuess(guess);
        this.board.giveFeedback();
        this.board.setNextActiveRow();
        this.roundLabel.setText("ROUND: " + (board.getActiveRow() + 1));
        this.guessesLeftLabel.setText("Guesses left: " + board.getGuessesLeft());
        
        if (board.getGuessesLeft() == 0 || board.gameIsover()) {
            this.timer.getTimeline().stop();
            GameOverWindow gameOverWindow = new GameOverWindow(board.gameIsover(), timer);
            gameOverWindow.showGameOverWindow(window);

        }
        moveArrowDown();
    }
    
    private void handleTimer() {
        timer.setTimeline(new Timeline(
                new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        Duration duration = ((KeyFrame) t.getSource()).getTime();
                        timer.setMinutes(timer.getMinutes().add(duration));
                        timer.setSeconds(timer.getSeconds().add(duration));
                        timer.setMilliSeconds(timer.getMilliSeconds().add(duration));
                        
                        if (timer.getSeconds().greaterThan(Duration.seconds(60))) {
                            timer.setSeconds(Duration.ZERO);
                        }
                        timer.getTimeMinutes().set((int) timer.getMinutes().toMinutes());
                        timer.getTimeSeconds().set((int) timer.getSeconds().toSeconds());

                    }
                })
            ));
            
        timer.getTimeline().setCycleCount(Timeline.INDEFINITE);
        timer.getTimeline().play();
    }

    public Scene getGameScene() {
        return gameScene;
    }
    
    
}
